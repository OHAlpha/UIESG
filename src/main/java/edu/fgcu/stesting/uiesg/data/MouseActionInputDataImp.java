package edu.fgcu.stesting.uiesg.data;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.awt.event.MouseEvent.*;

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
	protected MouseActionInputDataImp(List<Point> rawData) {
		this.rawData = rawData;
	}

	/**
	 * Constructs a standard MAID instance.
	 */
	public MouseActionInputDataImp() {
		rawData = new LinkedList<Point>();
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
	 *            true if the box should bound pagePosition instead of
	 *            browserLocation.
	 * 
	 * @return the bounding box
	 */
	public Rectangle2D getRange(boolean page) {

		// iterate through the arraylist to find the minimums and maximums to
		// create the box
		Point temp = rawData.get(0);
		double minX = (page ? temp.browserLocation : temp.pagePosition).getX();
		double minY = (page ? temp.browserLocation : temp.pagePosition).getY();
		double maxX = (page ? temp.browserLocation : temp.pagePosition).getX();
		double maxY = (page ? temp.browserLocation : temp.pagePosition).getY();
		for (int i = 1; i < rawData.size(); i++) {
			Point tmp = rawData.get(i);
			if ((page ? tmp.browserLocation : tmp.pagePosition).getX() < minX) // find
																				// smallest
				minX = (page ? tmp.browserLocation : tmp.pagePosition).getX();
			if ((maxX < (page ? tmp.browserLocation : tmp.pagePosition).getX())) // find
																					// largest
				maxX = (page ? tmp.browserLocation : tmp.pagePosition).getX();
			if ((page ? tmp.browserLocation : tmp.pagePosition).getY() < minY)
				minY = (page ? tmp.browserLocation : tmp.pagePosition).getY();
			if ((maxY < (page ? tmp.browserLocation : tmp.pagePosition).getY()))
				maxY = (page ? tmp.browserLocation : tmp.pagePosition).getY();
		}

		return new Rectangle2D.Double(minX, minY, maxX - minX, maxY - minY);

	}

	/**
	 * Returns the timestamp of the latest point added if any exist.
	 * 
	 * @return the latest timestamp
	 * @throws NoSuchElementException
	 *             if no points have been added.
	 */
	public long latestTimestamp() throws NoSuchElementException {

		if (rawData == null)
			throw new NoSuchElementException("No element");
		else {
			Point item = rawData.get(rawData.size() - 1);
			return item.timestamp;
		}

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
	public void addPoint(Point2D browserPoint, Point2D pagePoint,
			long timestamp, int type) throws IllegalArgumentException {
		// throw new RuntimeException("method not implemented");
		if (browserPoint.getX() < 0 || browserPoint.getY() < 0)
			throw new IllegalArgumentException();
		if (pagePoint.getX() < 0 || pagePoint.getY() < 0)
			throw new IllegalArgumentException();
		if (timestamp < latestTimestamp())
			throw new IllegalArgumentException();
		if (type < MOUSE_FIRST || type > MOUSE_LAST)
			throw new IllegalArgumentException();

		Point p = new Point();
		p.browserLocation = browserPoint;
		p.pagePosition = pagePoint;
		p.timestamp = timestamp;
		p.type = type;

		rawData.add(p);

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