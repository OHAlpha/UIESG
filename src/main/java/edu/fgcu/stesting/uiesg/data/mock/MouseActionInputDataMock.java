package edu.fgcu.stesting.uiesg.data.mock;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.fgcu.stesting.uiesg.data.MouseActionInputData;

// TODO: javadoc
@SuppressWarnings( "javadoc" )
public class MouseActionInputDataMock implements MouseActionInputData {

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Rectangle2D getRange( boolean page ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long latestTimestamp() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addPoint( Point2D browserPoint, Point2D pagePoint,
			long timestamp, int type ) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<Point> iterate() {
		// TODO Auto-generated method stub
		return null;
	}

}