package edu.fgcu.stesting.uiesg.data;

import java.awt.Point;
import java.awt.event.MouseEvent;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static edu.fgcu.stesting.uiesg.data.MouseGraphAction.types;
import static edu.fgcu.stesting.uiesg.data.MouseGraphAction.subTypes;

// TODO: javadoc
@SuppressWarnings( "javadoc" )
public class GraphOutputDataMockTest {

	private static MouseActionInputData maid;

	private static MouseActionInputData maid2;

	private static MouseActionInputData maid3;

	private static int o = 6, s = 4, n = o + s;

	private static MouseGraphAction[] as;

	/**
	 * Initializes SiteEfficiencyData.
	 */
	@BeforeClass
	public static void setup() {
		setup(MAIDFactory.MOCK, GODFactory.MOCK, GODFactory.MOCK);
	}

	protected static void setup( int maidM, int godM, int mgaM ) {
		SiteEfficiencyData.init("tmp/datafiles");
		MAIDFactory.init(maidM);
		GODFactory.init(godM, mgaM);
		int[][] data = new int[][] { { 30, 0, 0, MouseEvent.MOUSE_ENTERED },
				{ 30, 10, 1, MouseEvent.MOUSE_MOVED },
				{ 30, 30, 2, MouseEvent.MOUSE_MOVED },
				{ 30, 50, 3, MouseEvent.MOUSE_MOVED },
				{ 30, 50, 4, MouseEvent.MOUSE_CLICKED },
				{ 30, 50, 5, MouseEvent.MOUSE_PRESSED },
				{ 40, 50, 6, MouseEvent.MOUSE_DRAGGED },
				{ 50, 50, 7, MouseEvent.MOUSE_DRAGGED },
				{ 60, 50, 8, MouseEvent.MOUSE_DRAGGED },
				{ 60, 50, 9, MouseEvent.MOUSE_RELEASED },
				{ 60, 50, 10, MouseEvent.MOUSE_CLICKED },
				{ 50, 30, 2011, MouseEvent.MOUSE_MOVED },
				{ 50, 25, 2012, MouseEvent.MOUSE_MOVED },
				{ 50, 24, 3018, MouseEvent.MOUSE_MOVED },
				{ 50, 23, 3023, MouseEvent.MOUSE_MOVED },
				{ 40, 10, 3024, MouseEvent.MOUSE_MOVED },
				{ 30, 0, 3025, MouseEvent.MOUSE_MOVED },
				{ 30, 0, 3026, MouseEvent.MOUSE_EXITED } };
		maid = MAIDFactory.newInstance();
		for (int i = 0; i < data.length; i++)
			maid.addPoint(new Point(data[i][0], data[i][1]), new Point(
					data[i][0], data[i][1]), data[i][2], data[i][3]);
		maid2 = MAIDFactory.newInstance();
		for (int i = 0; i < data.length - 1; i++)
			maid2.addPoint(new Point(data[i][0], data[i][1]), new Point(
					data[i][0], data[i][1]), data[i][2], data[i][3]);
		maid3 = MAIDFactory.newInstance();
		for (int i = 0; i < data.length; i++)
			maid3.addPoint(new Point(data[i][0], 10 + data[i][1]), new Point(
					data[i][0], 10 + data[i][1]), data[i][2], data[i][3]);
		as = new MouseGraphAction[] {
				GODFactory.newGraphAction(0, GODFactory.NODE, GODFactory.ENTER,
						30, 0),
				GODFactory.newGraphAction(1, GODFactory.EDGE, GODFactory.MOVE,
						new double[] { 30, 0, 30, 50 }),
				GODFactory.newGraphAction(4, GODFactory.NODE, GODFactory.CLICK,
						30, 50),
				GODFactory.newGraphAction(6, GODFactory.EDGE, GODFactory.DRAG,
						new double[] { 30, 50, 60, 50 }),
				GODFactory.newGraphAction(10, GODFactory.NODE,
						GODFactory.CLICK, 60, 50),
				GODFactory.newGraphAction(2011, GODFactory.NODE,
						GODFactory.HOVER, new double[] { 60, 50 }),
				GODFactory.newGraphAction(2012, GODFactory.EDGE,
						GODFactory.MOVE, new double[] { 60, 50, 50, 25 }),
				GODFactory.newGraphAction(2012, GODFactory.NODE,
						GODFactory.HOVER, new double[] { 50, 25, 50, 23 }),
				GODFactory.newGraphAction(2014, GODFactory.EDGE,
						GODFactory.MOVE, new double[] { 50, 23, 30, 0 }),
				GODFactory.newGraphAction(2017, GODFactory.NODE,
						GODFactory.EXIT, 30, 0) };
	}

	protected GraphOutputData godA, godB, godC, godD, godE;

	@Before
	public void before() {
		godA = GODFactory.newInstance();
		for (int i = 0; i < n; i++)
			godA.addAction(as[i]);
		godB = GODFactory.newInstance(maid.iterate());
		godC = GODFactory.newInstance(maid2.iterate());
		godD = GODFactory.newInstance(maid3.iterate());
		godE = GODFactory.newInstance(maid.iterate());
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
			@SuppressWarnings( "unused" )
			MouseGraphAction a = godB.getAction(i);
			;
			// System.out.printf("\t%d: ( type, subType ) = ( %s, %s )\n", i,
			// types[a.getType()], subTypes[a.getSubType()]);
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
		godA.addAction(GODFactory.newGraphAction(System.currentTimeMillis(),
				GODFactory.EDGE, GODFactory.MOVE, new double[] { 2, 3, 4, 5 }));
		assertEquals("godA.order() must be " + o, godA.order(), o);
		assertEquals("godA.size() must be " + (s + 1), godA.size(), s + 1);
	}

	@Test
	public void testAddNode() {
		godA.addAction(GODFactory.newGraphAction(System.currentTimeMillis(),
				GODFactory.NODE, GODFactory.ENTER, 3, 5));
		assertEquals("godA.order() must be " + (o + 1), godA.order(), o + 1);
		assertEquals("godA.size() must be " + s, godA.size(), s);
	}

	@Test( expected = RuntimeException.class )
	public void testAddActionLocked() {
		godB.addAction(GODFactory.newGraphAction(System.currentTimeMillis(),
				GODFactory.NODE, GODFactory.ENTER));
	}

	@Test( expected = RuntimeException.class )
	public void testLock() {
		godA.lock();
		godA.addAction(GODFactory.newGraphAction(System.currentTimeMillis(),
				GODFactory.NODE, GODFactory.ENTER));
	}

	@Test
	public void testIndexOf() {
		for (int i = 0; i < n; i++)
			assertEquals(as[i] + ".indexof should return " + i, i,
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
		for (int i : new int[] { 0, 2, 4, 5, 7 })
			as[i].assertEquals(godA.getNode(i), true);
	}

	@Test( expected = IllegalArgumentException.class )
	public void testGetEdgeAsNode() {
		godA.getEdge(0);
	}

	@Test
	public void testGetEdge() {
		for (int i : new int[] { 1, 3, 6 })
			as[i].assertEquals(godA.getEdge(i), true);
	}

	@Test
	public void testEquals() {
		godB.assertEquals(godE, true);
		assertTrue(godB.equals(godE));
	}

	@Test( expected = AssertionError.class )
	public void testEqualsC() {
		assertNotEquals(godB + " should not be " + godC, godB, godC);
		godB.assertEquals(godC,true);
	}

	@Test( expected = AssertionError.class )
	public void testEqualsD() {
		assertNotEquals(godB + " should not be " + godD, godB, godD);
		godB.assertEquals(godD,true);
	}

}