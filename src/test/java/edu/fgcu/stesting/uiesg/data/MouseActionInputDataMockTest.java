package edu.fgcu.stesting.uiesg.data;

import java.awt.Point;
import java.awt.event.MouseEvent;

import org.junit.*;

import static org.junit.Assert.*;

// TODO: javadoc
@SuppressWarnings( "javadoc" )
public class MouseActionInputDataMockTest {

	MouseActionInputData MAID, MAIDB, MAIDC,MAIDD;

	public MouseActionInputDataMockTest() {
	}

	@BeforeClass
	public static void setup() {
		MAIDFactory.init(MAIDFactory.MOCK);
	}

	@Before
	public void before() {
		MAID = MAIDFactory.newInstance();
		MAID.addPoint(new Point(0, 1), new Point(2, 0),
				1l, MouseEvent.MOUSE_CLICKED);
		MAID.addPoint(new Point(1, 2), new Point(4, 2),
				2l, MouseEvent.MOUSE_CLICKED);
		MAID.addPoint(new Point(2, 3), new Point(6, 4),
				3l, MouseEvent.MOUSE_CLICKED);
		MAIDB = MAIDFactory.newInstance();
		MAIDB.addPoint(new Point(0, 1), new Point(2, 0),
				1l, MouseEvent.MOUSE_CLICKED);
		MAIDB.addPoint(new Point(1, 2), new Point(4, 2),
				2l, MouseEvent.MOUSE_CLICKED);
		MAIDC = MAIDFactory.newInstance();
		MAIDC.addPoint(new Point(0, 1), new Point(2, 0),
				1l, MouseEvent.MOUSE_CLICKED);
		MAIDC.addPoint(new Point(1, 2), new Point(4, 2),
				2l, MouseEvent.MOUSE_CLICKED);
		MAIDC.addPoint(new Point(2, 3), new Point(7, 4),
				3l, MouseEvent.MOUSE_CLICKED);
		MAIDD = MAIDFactory.newInstance();
		MAIDD.addPoint(new Point(0, 1), new Point(2, 0),
				1l, MouseEvent.MOUSE_CLICKED);
		MAIDD.addPoint(new Point(1, 2), new Point(4, 2),
				2l, MouseEvent.MOUSE_CLICKED);
		MAIDD.addPoint(new Point(2, 3), new Point(6, 4),
				3l, MouseEvent.MOUSE_CLICKED);
	}

	@After
	public void after() {
	}

	@AfterClass
	public static void teardown() {
	}

	@Test
	public void testSize() {
		// throw new RuntimeException("test not implemented");
		// add a bunch of points and see if the size is correct
		MAID.addPoint(new Point(0, 1), new Point(2, 0),
				4l, MouseEvent.MOUSE_CLICKED);
		MAID.addPoint(new Point(1, 2), new Point(4, 2),
				5l, MouseEvent.MOUSE_CLICKED);
		MAID.addPoint(new Point(2, 3), new Point(6, 4),
				6l, MouseEvent.MOUSE_CLICKED);

		// test to see if there are three elements in the list
		assertEquals(6, MAID.size());

	}

	// // add method to test negative x and y values
	// @Test( expected = IllegalArgumentException.class )
	// public void testAddPointFault() {
	// // add a point with negative value to get the error exception
	// MAID.addPoint(new Point(-2, -6), new Point(1, 2),
	// System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);
	//
	// }

	/***
	 * test that adds points for browserLocation and pagePosition, a time, and a
	 * mouse action
	 * 
	 */
	@Test
	public void testAddPoint() {

		// add point to list and check last point to see if it is the one we
		// added
		MAID.addPoint(new Point(0, 1), new Point(2, 0),
				System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);
		// MAID.addPoint(new Point(1,2), new Point(4,2),
		// System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);
		// MAID.addPoint(new Point(2,3), new Point(6,4),
		// System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);

		// create new MAID
		edu.fgcu.stesting.uiesg.data.MouseActionInputData.Point p = MAID
				.getPoint(MAID.size() - 1);
		// test x value of broswer location
		assertEquals(0, p.browserLocation.getX(), .01);
		// test y value of browser location
		assertEquals(1, p.browserLocation.getY(), .01);
		// test x value of pageposition
		assertEquals(2, p.pagePosition.getX(), .01);
		// test y value of pageposition
		assertEquals(0, p.pagePosition.getY(), .01);

	}
	
	@Test
	public void testEquals() {
		assertEquals(MAID,MAIDD);
		assertNotEquals(MAID,MAIDB);
		assertNotEquals(MAID,MAIDC);
	}

}