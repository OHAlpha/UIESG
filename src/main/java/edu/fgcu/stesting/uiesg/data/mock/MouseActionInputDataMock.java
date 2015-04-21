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
			if (maid.points.size() != points.size())
				return false;
			for (int i = 0; i < points.size(); i++)
				if (!maid.points.get(i).equals(points.get(i)))
					return false;
			return true;
		} else
			return false;
	}

}