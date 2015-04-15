package edu.fgcu.stesting.uiesg.data.mock;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import edu.fgcu.stesting.uiesg.data.MouseGraphEdge;

@SuppressWarnings( "javadoc" )
public class MouseGraphEdgeMock extends AbstractMouseGraphActionMock implements
		MouseGraphEdge {
	
	private Line2D line;
	
	public MouseGraphEdgeMock( Point s, Point d ) {
		line = new Line2D.Double(s, d);
	}

	@Override
	public Rectangle2D getRange() {
		return line.getBounds2D();
	}

	@Override
	public Shape getArea() {
		return line;
	}

	@Override
	public int getPathType() {
		return PATH_TYPE_LINEAR;
	}

	@Override
	public Object getPathParameters() {
		return new Point2D[] { line.getP1(), line.getP2() };
	}

}