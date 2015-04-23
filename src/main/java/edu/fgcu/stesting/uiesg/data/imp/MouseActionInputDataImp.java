package edu.fgcu.stesting.uiesg.data.imp;

import static java.awt.event.MouseEvent.MOUSE_FIRST;
import static java.awt.event.MouseEvent.MOUSE_EXITED;
import static java.awt.event.MouseEvent.MOUSE_LAST;

import java.awt.geom.Point2D;
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
	 * Constructs a standard MAID instance.
	 */
	public MouseActionInputDataImp() {
		this.rawData = new ArrayList<Point>();
	}

	/**
	 * Returns the number of points recorder.
	 * 
	 * @return the number of points
	 */
	@Override
	public int size() {
		return rawData.size();
	}

	/**
	 * Returns the timestamp of the latest point added if any exist.
	 * 
	 * @return the latest timestamp
	 * @throws NoSuchElementException
	 *             if no points have been added.
	 */
	@Override
	public long latestTimestamp() throws NoSuchElementException {

		if (rawData.isEmpty())
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
	@Override
	public void addPoint( Point2D browserPoint, Point2D pagePoint,
			long timestamp, int type ) throws IllegalArgumentException {

		if (type != MOUSE_EXITED
				&& (browserPoint.getX() < 0 || browserPoint.getY() < 0))
			throw new IllegalArgumentException();
		if (pagePoint.getX() < 0 || pagePoint.getY() < 0)
			throw new IllegalArgumentException();
		if (rawData.size() > 0)
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
	@Override
	public Iterator<Point> iterate() {
		return rawData.iterator();
	}

	@Override
	@SuppressWarnings( "javadoc" )
	public String toString() {
		return "MAID( #points: " + rawData.size() + " )";
	}

	@Override
	@SuppressWarnings( "javadoc" )
	public boolean equals( Object o ) {
		if (o instanceof MouseActionInputData) {
			MouseActionInputData maid = (MouseActionInputData) o;
			return assertEquals(maid, false);
		}
		return false;
	}

	@Override
	@SuppressWarnings( "javadoc" )
	public boolean assertEquals( MouseActionInputData mouseData, boolean error )
			throws AssertionError {
		int i = 0;
		for (Iterator<Point> it = mouseData.iterate(); it.hasNext(); i++) {
			Point e = rawData.get(i), r = it.next();
			// if( !e.equals(r) )
			// throw new AssertionError("point at index " + i + " should be " +
			// e + " but is " + r);
			if( !e.assertEquals(r, false) )
			return false;
		}
		return true;
	}

	@Override
	@SuppressWarnings( "javadoc" )
	public Point getPoint( int i ) {
		return rawData.get(i);
	}

}
