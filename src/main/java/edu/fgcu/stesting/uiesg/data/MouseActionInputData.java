package edu.fgcu.stesting.uiesg.data;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.fgcu.stesting.uiesg.data.imp.MouseActionInputDataImp;

/**
 * MouseActionInputData (MAID) is a container for the raw mouse data. Instances
 * will store the locations, timestamps and types of all MouseEvents generated
 * by the browser window.
 * 
 * @author oalpha
 *
 */
public interface MouseActionInputData {

	// TODO: javadoc
	@SuppressWarnings( "javadoc" )
	public static class MAIDFactory {

		public static final int MOCK = 0;

		public static final int IMPLEMENTATION = 1;

		protected static int mode;

		public static MouseActionInputData newInstance() {
			return mode == MOCK ? new MouseActionInputDataMock()
					: new MouseActionInputDataImp();
		}

		public static void init( int mode ) {
			MAIDFactory.mode = mode;
		}

	}

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

}
