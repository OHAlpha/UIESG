package edu.fgcu.stesting.uiesg.data;

import java.awt.Point;
import java.awt.event.MouseEvent;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

// TODO: javadoc
@SuppressWarnings( "javadoc" )
public class GraphOutputDataUnitTest {

	private static MouseActionInputData maid;

	private static int o = 4, s = 3, n = 7;

	private static MouseGraphAction[] as;

	private static String[] types = { "Node", "Edge" }, subTypes = { "ENTER",
			"HOVER", "CLICK", "EXIT", "MOVE", "DRAG" };

	/**
	 * Initializes SiteEfficiencyData.
	 */
	@BeforeClass
	public static void setup() {
		SiteEfficiencyData.init("tmp/datafiles");
		MAIDFactory.init(MAIDFactory.MOCK);
		GODFactory.init(GODFactory.IMPLEMENTATION, GODFactory.MOCK);
		maid = MAIDFactory.newInstance();
		maid.addPoint(new Point(30, 0), new Point(30, 0),
				System.currentTimeMillis(), MouseEvent.MOUSE_ENTERED);
		maid.addPoint(new Point(30, 10), new Point(30, 10),
				System.currentTimeMillis(), MouseEvent.MOUSE_MOVED);
		maid.addPoint(new Point(30, 30), new Point(30, 30),
				System.currentTimeMillis(), MouseEvent.MOUSE_MOVED);
		maid.addPoint(new Point(30, 50), new Point(30, 50),
				System.currentTimeMillis(), MouseEvent.MOUSE_MOVED);
		maid.addPoint(new Point(30, 50), new Point(30, 50),
				System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);
		maid.addPoint(new Point(40, 50), new Point(40, 50),
				System.currentTimeMillis(), MouseEvent.MOUSE_MOVED);
		maid.addPoint(new Point(50, 50), new Point(50, 50),
				System.currentTimeMillis(), MouseEvent.MOUSE_MOVED);
		maid.addPoint(new Point(60, 50), new Point(60, 50),
				System.currentTimeMillis(), MouseEvent.MOUSE_MOVED);
		maid.addPoint(new Point(50, 30), new Point(50, 30),
				System.currentTimeMillis() + 2000, MouseEvent.MOUSE_MOVED);
		maid.addPoint(new Point(40, 10), new Point(40, 10),
				System.currentTimeMillis() + 2000, MouseEvent.MOUSE_MOVED);
		maid.addPoint(new Point(30, 0), new Point(30, 0),
				System.currentTimeMillis() + 2000, MouseEvent.MOUSE_MOVED);
		maid.addPoint(new Point(30, 0), new Point(30, 0),
				System.currentTimeMillis() + 2000, MouseEvent.MOUSE_EXITED);
		as = new MouseGraphAction[] {
				GODFactory.newGraphAction(GODFactory.NODE, GODFactory.ENTER,
						30, 0),
				GODFactory.newGraphAction(GODFactory.EDGE, GODFactory.MOVE, 30,
						0, 30, 50),
				GODFactory.newGraphAction(GODFactory.NODE, GODFactory.CLICK,
						30, 50),
				GODFactory.newGraphAction(GODFactory.EDGE, GODFactory.MOVE, 30,
						50, 60, 50),
				GODFactory.newGraphAction(GODFactory.NODE, GODFactory.HOVER,
						60, 50),
				GODFactory.newGraphAction(GODFactory.EDGE, GODFactory.MOVE, 60,
						50, 30, 0),
				GODFactory.newGraphAction(GODFactory.NODE, GODFactory.EXIT, 30,
						0) };
		for (int i = 0; i < as.length; i++) {
			MouseGraphAction a = as[i];
			System.out.printf("\t%d: ( type, subType ) = ( %s, %s )\n", i,
					types[a.getType()], subTypes[a.getSubType()]);
		}
	}

	private GraphOutputData godA, godB;

	@Before
	public void before() {
		godA = GODFactory.newInstance();
		for (int i = 0; i < n; i++)
			godA.addAction(as[i]);
		godB = GODFactory.newInstance(maid.iterate());
	}

	@After
	public void after() {
	}

	@AfterClass
	public static void teardown() {
	}

	@Test( expected = RuntimeException.class )
	public void testConstructorDefault() {
		godA.getAction(n);
	}

	@Test
	public void testConstructorIterator() {
		int rn = godB.order() + godB.size();
		for (int i = 0; i < rn; i++) {
			MouseGraphAction a = godB.getAction(i);
			System.out.printf("\t%d: ( type, subType ) = ( %s, %s )\n", i,
					types[a.getType()], subTypes[a.getSubType()]);
		}
		assertEquals("godB.order() + godB.size() must be " + n, rn, n);
		for (int i = 0; i < n; i++) {
			int et = as[i].getType();
			int rt = godB.getAction(i).getType();
			int es = as[i].getSubType();
			int rs = godB.getAction(i).getSubType();
			assertEquals("type returned incorrect value at index " + i
					+ ", returned " + types[rt] + ", expected " + types[et],
					rt, et);
			assertEquals("subType returned incorrect value at index " + i
					+ ", returned " + subTypes[rs] + ", expected "
					+ subTypes[es], rs, es);
		}
	}

	@Test
	public void testOrder() {
		assertEquals("godA.order() must be " + o, godA.order(), o);
		assertEquals("godB.order() must be " + o, godB.order(), o);
	}

	@Test
	public void testSize() {
		assertEquals("godA.size() must be " + s, godA.size(), s);
		assertEquals("godB.size() must be " + s, godB.size(), s);
	}

	@Test
	public void testAddEdge() {
		godA.addAction(GODFactory.newGraphAction(GODFactory.EDGE,
				GODFactory.MOVE, 2, 3, 4, 5));
		assertEquals("godA.order() must be " + o, godA.order(), o);
		assertEquals("godA.size() must be " + (s + 1), godA.size(), s + 1);
	}

	@Test
	public void testAddNode() {
		godA.addAction(GODFactory.newGraphAction(GODFactory.NODE,
				GODFactory.ENTER, 3, 5));
		assertEquals("godA.order() must be " + (o + 1), godA.order(), o + 1);
		assertEquals("godA.size() must be " + s, godA.size(), s);
	}

	@Test( expected = RuntimeException.class )
	public void testAddActionLocked() {
		godB.addAction(GODFactory.newGraphAction(GODFactory.NODE,
				GODFactory.ENTER));
	}

	@Test( expected = RuntimeException.class )
	public void testLock() {
		godA.lock();
		godA.addAction(GODFactory.newGraphAction(GODFactory.NODE,
				GODFactory.ENTER));
	}

	@Test
	public void testIndexOf() {
		for (int i = 0; i < n; i++)
			assertEquals("indexof returned incorrect value", i,
					godA.indexOf(as[i]));
	}

	@Test
	public void testGetAction() {
		for (int i = 0; i < n; i++)
			assertEquals("getAction returned incorrect value", as[i],
					godA.getAction(i));
	}

	@Test( expected = IllegalArgumentException.class )
	public void testGetNodeAsEdge() {
		godA.getNode(1);
	}

	@Test
	public void testGetNode() {
		for (int i = 0; i < n; i += 2)
			assertEquals("getAction returned incorrect value", as[i],
					godA.getAction(i));
	}

	@Test( expected = IllegalArgumentException.class )
	public void testGetEdgeAsNode() {
		godA.getEdge(0);
	}

	@Test
	public void testGetEdge() {
		for (int i = 1; i < n; i += 2)
			assertEquals("getAction returned incorrect value", as[i],
					godA.getAction(i));
	}

}