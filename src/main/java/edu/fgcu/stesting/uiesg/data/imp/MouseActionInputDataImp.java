package edu.fgcu.stesting.uiesg.data.imp;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import edu.fgcu.stesting.uiesg.data.MouseActionInputData;

/**
 * MouseActionInputData (MAID) is a container for the raw mouse data. Instances
 * will store the locations, timestamps and types of all MouseEvents generated
 * by the browser window.
 * 
 * @author oalpha
 *
 */
public class MouseActionInputDataImp implements MouseActionInputData {

	/**
	 * The rawData recorded by the browser.
	 */
	private List<Point> rawData;

	/**
	 * Constructs a MAID instance with an existing collection. This is for
	 * testing purposes.
	 * 
	 * @param rawData
	 *            the collection to be used in place of the default.
	 */
	protected MouseActionInputDataImp( List<Point> rawData ) {
		this.rawData = rawData;
	}

	/**
	 * Constructs a standard MAID instance.
	 */
	public MouseActionInputDataImp() {
		rawData = new ArrayList<Point>();
	}

	/**
	 * Returns the number of points recorder.
	 * 
	 * @return the number of points
	 */
	public int size() {
		return rawData.size();
	}

	/**
	 * Returns the box bounding the points in rawData.
	 * 
	 * @param page
	 *            true if the box should bound position instead of location.
	 * 
	 * @return the bounding box
	 */
	public Rectangle2D getRange( boolean page ) {
		throw new RuntimeException("method not implemented");
		// create box from points 
		
		
	}

	/**
	 * Returns the timestamp of the latest point added if any exist.
	 * 
	 * @return the latest timestamp
	 * @throws NoSuchElementException
	 *             if no points have been added.
	 */
	public long latestTimestamp() throws NoSuchElementException {
		throw new RuntimeException("method not implemented");
		// System.getCurrentTime();
	}

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
	public void addPoint( Point2D browserPoint, Point2D pagePoint,
			long timestamp, int type ) throws IllegalArgumentException {
		throw new RuntimeException("method not implemented");
	}

	/**
	 * Iterates through rawData. Used by GODFactories to compile into graph
	 * data.
	 * 
	 * @return the iterator
	 */
	public Iterator<Point> iterate() {
		throw new RuntimeException("method not implemented");
	}

}