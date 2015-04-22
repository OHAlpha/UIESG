package edu.fgcu.stesting.uiesg.data.mock;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import edu.fgcu.stesting.uiesg.data.MouseActionInputData;

// TODO: javadoc
@SuppressWarnings( "javadoc" )
public class MouseActionInputDataMock implements MouseActionInputData {

	List<Point> points;

	{
		points = new LinkedList<>();
	}

	@Override
	public int size() {
		return points.size();
	}

	@Override
	public Rectangle2D getRange( boolean page ) {
		return null;
	}

	@Override
	public long latestTimestamp() throws NoSuchElementException {
		if (points.isEmpty())
			throw new NoSuchElementException();
		return points.get(points.size() - 1).timestamp;
	}

	@Override
	public void addPoint( Point2D browserPoint, Point2D pagePoint,
			long timestamp, int type ) throws IllegalArgumentException {
		Point p = new Point();
		p.browserLocation = browserPoint;
		p.pagePosition = pagePoint;
		p.timestamp = timestamp;
		p.type = type;
		points.add(p);
	}

	@Override
	public Iterator<Point> iterate() {
		return points.iterator();
	}

	@Override
	public boolean equals( Object o ) {
		if (o instanceof MouseActionInputDataMock) {
			MouseActionInputDataMock maid = (MouseActionInputDataMock) o;
			try {
				assertEquals(maid, false);
				return true;
			} catch (AssertionError ex) {
				return false;
			}
		} else
			return false;
	}

	@Override
	public boolean assertEquals( MouseActionInputData mouseData, boolean error )
			throws AssertionError {
		if (mouseData.size() != points.size())
			throw new AssertionError("mouseData.size() should be "
					+ points.size() + " but is " + mouseData.size());
		int i = 0;
		for( Iterator<Point> it = mouseData.iterate(); it.hasNext(); i++ ) {
			Point e = points.get(i), r = it.next();
			if( !e.equals(r) )
				throw new AssertionError("point at index " + i + " should be "
						+ e + " but is " + r);
		}
		return true;
	}

}