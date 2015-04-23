package edu.fgcu.stesting.uiesg.data.mock;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import edu.fgcu.stesting.uiesg.data.MouseGraphEdge;
import edu.fgcu.stesting.uiesg.data.MouseGraphNode;

@SuppressWarnings( "javadoc" )
public class MouseGraphEdgeMock extends AbstractMouseGraphActionMock implements
		MouseGraphEdge {

	private Line2D line;

	public MouseGraphEdgeMock( long timestamp, int t, int st, Point s, Point d ) {
		super(timestamp, t, st);
		line = new Line2D.Double(s, d);
	}

	@Override
	public Rectangle2D getRange() {
		return line.getBounds2D();
	}

	@Override
	public int getPathType() {
		return PATH_TYPE_LINEAR;
	}

	@Override
	public Object getPathParameters() {
		return new Point2D[] { line.getP1(), line.getP2() };
	}

	@Override
	public MouseGraphNode asNode() {
		throw new IllegalArgumentException();
	}

	@Override
	public MouseGraphEdge asEdge() {
		return this;
	}

	@Override
	public Point2D getSource() {
		return line.getP1();
	}

	@Override
	public Point2D getDest() {
		return line.getP2();
	}

	@Override
	public boolean equals( Object o ) {
		if (super.equals(o)) {
			MouseGraphEdgeMock mge = (MouseGraphEdgeMock) o;
			return mge.getSource().equals(line.getP1())
					&& mge.getDest().equals(line.getP2());
		} else
			return false;
	}

}
