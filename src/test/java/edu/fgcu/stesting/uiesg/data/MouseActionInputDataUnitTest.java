package edu.fgcu.stesting.uiesg.data;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import org.junit.*;

import edu.fgcu.stesting.uiesg.data.imp.MouseActionInputDataImp;
import static org.junit.Assert.*;

// TODO: javadoc
@SuppressWarnings( "javadoc" )
public class MouseActionInputDataUnitTest {

	public MouseActionInputDataUnitTest() {
	}

	@BeforeClass
	public static void setup() {

	}

	@Before
	public void before() {
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
		MouseActionInputDataImp MAID = new MouseActionInputDataImp();
		MAID.addPoint(new Point(0, 1), new Point(2, 0),
				System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);
		MAID.addPoint(new Point(1, 2), new Point(4, 2),
				System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);
		MAID.addPoint(new Point(2, 3), new Point(6, 4),
				System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);
		assertEquals(3, MAID.size());

	}

	// add method to test latest timestamp with null data

	// add method to test negative x and y values

	@Test
	public void testGetRange() {

		// create a new MAID
		MouseActionInputDataImp MAID = new MouseActionInputDataImp();
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

		// Rectangle2D.Double(minX, minY, maxX - minX, maxY - minY);

	}

	@Test
	public void testAddPoint() {
		
		// add point to list and check last point to see if it is the one we
		// added
		MouseActionInputDataImp MAID = new MouseActionInputDataImp();
		MAID.addPoint(new Point(0, 1), new Point(2, 0),
				System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);
		// MAID.addPoint(new Point(1,2), new Point(4,2),
		// System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);
		// MAID.addPoint(new Point(2,3), new Point(6,4),
		// System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);

		// create new MAID
		edu.fgcu.stesting.uiesg.data.MouseActionInputData.Point p = MAID.getRawData()
				.get(MAID.size() - 1);
		// test x value of broswer location
		assertEquals(0, p.browserLocation.getX(), .01);
		// test y value of browser location
		assertEquals(1, p.browserLocation.getY(), .01);
		// test x value of pageposition
		assertEquals(2, p.pagePosition.getX(), .01);
		// test y value of pageposition
		assertEquals(0, p.pagePosition.getY(), .01);

	}

}