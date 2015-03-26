package edu.fgcu.stesting.uiesg.data;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;


import java.awt.geom.Rectangle2D;

import org.junit.*;

import static org.junit.Assert.*;


public class MouseActionInputDataUnitTest {
    
    public MouseActionInputDataUnitTest() {
    }
    
    @BeforeClass
    public static void setup() {
    	
    	
    }
    
    @Before
    public void before() {}
    
    @After
    public void after() {}
    
    @AfterClass
    public static void teardown() {}
    
    
    @Test
    public void testSize() {
        //throw new RuntimeException("test not implemented");
        // add a bunch of points and see if the size is correct
    	MouseActionInputDataImp MAID = new MouseActionInputDataImp();    	
    	MAID.addPoint(new Point(0,1), new Point(2,0), System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);
    	MAID.addPoint(new Point(1,2), new Point(4,2), System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);
    	MAID.addPoint(new Point(2,3), new Point(6,4), System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);
    	assertEquals(3,MAID.size());
    	
    	
    }
    
    // add method to test latest timestamp with null data
    
    // add method to test negative x and y values
    
    @Test
    public void testGetRange() {
       // add a bunch of points in a given range
    	MouseActionInputDataImp MAID = new MouseActionInputDataImp();    	
    	MAID.addPoint(new Point(0,1), new Point(2,0), System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED); // browser location first and then page position
    	MAID.addPoint(new Point(1,2), new Point(4,2), System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);
    	MAID.addPoint(new Point(2,3), new Point(6,4), System.currentTimeMillis(), MouseEvent.MOUSE_CLICKED);
    	Rectangle2D r = MAID.getRange(false);
    	
    	//assertEquals(0, 1, 2, 2, MAID.getRange(false));
    	assertEquals(0, r.getX(), .01);
    	assertEquals(1, r.getY(), .01);
    	assertEquals(2, r.getWidth(), .01);
    	assertEquals(2, r.getHeight(), .01);
    	// Rectangle2D.Double(minX, minY, maxX - minX, maxY - minY);
    	
    }

    @Test
    public void testAddPoint() {
       // throw new RuntimeException("test not implemented");
        // add point to list and check last point to see if it is the one we added
    }
    
}