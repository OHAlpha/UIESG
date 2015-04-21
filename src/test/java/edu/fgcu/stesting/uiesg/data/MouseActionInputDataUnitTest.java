package edu.fgcu.stesting.uiesg.data;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.NoSuchElementException;

import org.junit.*;

import edu.fgcu.stesting.uiesg.data.imp.MouseActionInputDataImp;
import static org.junit.Assert.*;

// TODO: javadoc
@SuppressWarnings( "javadoc" )
public class MouseActionInputDataUnitTest {

	MouseActionInputDataImp MAID;

	public MouseActionInputDataUnitTest() {
	}

	@BeforeClass
	public static void setup() {

	}

	@Before
	public void before() {
		MAID = new MouseActionInputDataImp();
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
				System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);
		MAID.addPoint(new Point(1, 2), new Point(4, 2),
				System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);
		MAID.addPoint(new Point(2, 3), new Point(6, 4),
				System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);

		// test to see if there are three elements in the list
		assertEquals(3, MAID.size());

	}

	/***
	 * method to test latest timestamp while rawData is empty
	 */
	@Test( expected = NoSuchElementException.class )
	public void testTimeStamp() {

		// rawData should be empty which will throw an error
		MAID.latestTimestamp();

	}

	// add method to test negative x and y values
	@Test( expected = IllegalArgumentException.class )
	public void testAddPointFault() {
		// add a point with negative value to get the error exception
		MAID.addPoint(new Point(-2, -6), new Point(1, 2),
				System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);

	}

	/***
	 * test that adds points to the list and then gets the range based on the X
	 * and Y values
	 * 
	 */
	@Test
	public void testGetRange() {

		// add test points in a given range
		MAID.addPoint(new Point(0, 1), new Point(2, 0),
				System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);
		MAID.addPoint(new Point(1, 2), new Point(4, 2),
				System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);
		MAID.addPoint(new Point(2, 3), new Point(6, 4),
				System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);
		// create rectangle2D object to hold results from MAID.getRange()
		Rectangle2D r = MAID.getRange(false);

		// test the values returned by getRange with a delta of .01
		// testing X values
		assertEquals(0, r.getX(), .01);
		assertEquals(1, r.getY(), .01);
		assertEquals(2, r.getWidth(), .01);
		assertEquals(2, r.getHeight(), .01);

		// test getRange with true which should return the pagePosition instead
		// of the browserLocation
		r = MAID.getRange(true);
		assertEquals(2, r.getX(), .01);
		assertEquals(0, r.getY(), .01);
		assertEquals(4, r.getWidth(), .01);
		assertEquals(4, r.getHeight(), .01);

		// Rectangle2D.Double(minX, minY, maxX - minX, maxY - minY);

	}

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
				.getRawData().get(MAID.size() - 1);
		// test x value of broswer location
		assertEquals(0, p.browserLocation.getX(), .01);
		// test y value of browser location
		assertEquals(1, p.browserLocation.getY(), .01);
		// test x value of pageposition
		assertEquals(2, p.pagePosition.getX(), .01);
		// test y value of pageposition
		assertEquals(0, p.pagePosition.getY(), .01);

	}

	@Test( expected = IllegalArgumentException.class )
	public void TestAddPointFault() {
		// the errors that this is testing for
		/*
		 * if (type != MOUSE_EXITED && (browserPoint.getX() < 0 ||
		 * browserPoint.getY() < 0)) throw new IllegalArgumentException(); if
		 * (pagePoint.getX() < 0 || pagePoint.getY() < 0) throw new
		 * IllegalArgumentException(); if (rawData.size() > 0) if (timestamp <
		 * latestTimestamp()) throw new IllegalArgumentException(); if (type <
		 * MOUSE_FIRST || type > MOUSE_LAST) throw new
		 * IllegalArgumentException();
		 */
		// addPoint( Point2D browserPoint, Point2D pagePoint, long timestamp,
		// int type )
		// throw error for wrong type, x < 0, and y < 0
		MAID.addPoint(new Point(-1, 9), new Point(0, 0),
				System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);
		// throw error for pagepoint being less than 0
		MAID.addPoint(new Point(1, 10), new Point(-1, 2),
				System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);
		// create a legitimate point and then throw error for timestamp being
		// less than current timestamp of point
		MAID.addPoint(new Point(1, 1), new Point(1, 1),
				System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);
		MAID.addPoint(new Point(0, 1), new Point(2, 0), 0,
				MouseEvent.MOUSE_CLICKED);
		// throw an error for the type being less than mouse_first
		MAID.addPoint(new Point(1, 1), new Point(1, 1),
				System.currentTimeMillis(), 0);

	}

}