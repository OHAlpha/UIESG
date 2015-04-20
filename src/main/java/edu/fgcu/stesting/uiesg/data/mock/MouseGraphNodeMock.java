package edu.fgcu.stesting.uiesg.data.mock;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import edu.fgcu.stesting.uiesg.data.MouseGraphEdge;
import edu.fgcu.stesting.uiesg.data.MouseGraphNode;

@SuppressWarnings( "javadoc" )
public class MouseGraphNodeMock extends AbstractMouseGraphActionMock implements
		MouseGraphNode {

	private Rectangle range;

	public MouseGraphNodeMock( int t, int st, Point p ) {
		super(t, st);
		range = new Rectangle(p, new Dimension(0, 0));
	}

	@Override
	public Rectangle2D getRange() {
		return range;
	}

	@Override
	public Shape getArea() {
		return range;
	}

	@Override
	public Point2D getLocation() {
		return range.getLocation();
	}

	@Override
	public MouseGraphNode asNode() {
		return this;
	}

	@Override
	public MouseGraphEdge asEdge() {
		throw new IllegalArgumentException();
	}

}
