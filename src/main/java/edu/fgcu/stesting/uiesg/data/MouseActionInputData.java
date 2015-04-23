package edu.fgcu.stesting.uiesg.data;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * MouseActionInputData (MAID) is a container for the raw mouse data. Instances
 * will store the locations, timestamps and types of all MouseEvents generated
 * by the browser window.
 * 
 * @author oalpha
 *
 */
public interface MouseActionInputData {

	/**
	 * An encapsulation of the location timestamp and type of a mouse event.
	 * 
	 * @author oalpha
	 *
	 */
	public static class Point {

		/**
		 * The location of the event on the browser window. x,y in the browser
		 */
		public Point2D browserLocation;

		/**
		 * The location of the event on the page. This is effected by scrolling.
		 * x,y in the page
		 */
		public Point2D pagePosition;

		/**
		 * The time the event occurred.
		 */
		public long timestamp;

		/**
		 * The type of event. Can be any of types specified in MouseEvent.
		 * 
		 * @see java.awt.event.MouseEvent
		 */
		public int type;

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		@SuppressWarnings( "javadoc" )
		public boolean equals( Object o ) {
			if (o instanceof Point) {
				Point p = (Point) o;
				return assertEquals(p, false);
			} else
				return false;
		}

		@SuppressWarnings( "javadoc" )
		public boolean assertEquals( Point p, boolean error )
				throws AssertionError {
			if (!browserLocation.equals(p.browserLocation))
				if (error)
					throw new AssertionError("browserLocation should be "
							+ browserLocation + " but is " + p.browserLocation);
				else
					return false;
			if (!pagePosition.equals(p.pagePosition))
				if (error)
					throw new AssertionError("pagePosition should be "
							+ pagePosition + " but is " + p.pagePosition);
				else
					return false;
			if (timestamp != p.timestamp)
				if (error)
					throw new AssertionError("timestamp should be " + timestamp
							+ " but is " + p.timestamp);
				else
					return false;
			if (type != p.type)
				if (error)
					throw new AssertionError("type should be " + type
							+ " but is " + p.type);
				else
					return false;
			else
				return true;
		}

		@Override
		@SuppressWarnings( "javadoc" )
		public String toString() {
			return "Point( type: " + type + ", timestamp: " + timestamp
					+ " point: (" + browserLocation.getX() + ","
					+ browserLocation.getY() + ")-(" + browserLocation.getX()
					+ "," + browserLocation.getY() + ") )";
		}

	}

	/**
	 * Returns the number of points recorder.
	 * 
	 * @return the number of points
	 */
	int size();

	/**
	 * Returns the box bounding the points in rawData.
	 * 
	 * @param page
	 *            true if the box should bound position instead of location.
	 * 
	 * @return the bounding box
	 */
	Rectangle2D getRange( boolean page );

	/**
	 * Returns the timestamp of the latest point added if any exist.
	 * 
	 * @return the latest timestamp
	 * @throws NoSuchElementException
	 *             if no points have been added.
	 */
	long latestTimestamp() throws NoSuchElementException;

	/**
	 * Records a mouse event.
	 * 
	 * @param browserPoint
	 *            the point on the browser
	 * @param pagePoint
	 *            the point on the page
	 * @param timestamp
	 *            the time of the event
	 * @param type
	 *            the type of event
	 * @throws IllegalArgumentException
	 *             if either pagePoint or browserPoint contains negative values,
	 *             if timestamp is younger than the current latest timestamp or
	 *             if type is not one of the allowed types specified in
	 *             MouseEvent.
	 */
	void addPoint( Point2D browserPoint, Point2D pagePoint, long timestamp,
			int type ) throws IllegalArgumentException;

	/**
	 * Iterates through rawData. Used by GODFactories to compile into graph
	 * data.
	 * 
	 * @return the iterator
	 */
	Iterator<Point> iterate();

	/**
	 * Throws an error if this maid is not equivalent to the specified maid.
	 * 
	 * @param maid
	 *            the maid to test
	 * @param error
	 *            if an error should be thrown
	 * @throws AssertionError
	 *             if these actions are not equivalent
	 * @return if these maids are equal
	 */
	boolean assertEquals( MouseActionInputData maid, boolean error )
			throws AssertionError;

}
