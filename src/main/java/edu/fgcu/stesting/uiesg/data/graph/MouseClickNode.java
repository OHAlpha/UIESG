package edu.fgcu.stesting.uiesg.data.graph;

import java.awt.Shape;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import edu.fgcu.stesting.uiesg.data.GODFactory;
import edu.fgcu.stesting.uiesg.data.MouseGraphAction;

/**
 * Represents an click event in the browser window.
 * 
 * @author oalpha
 *
 */
public class MouseClickNode extends AbstractMouseGraphNode {

	/**
	 * The point clicked.
	 */
	private Rectangle2D range;

	/**
	 * @param timestamp
	 *            the time of the action
	 * @param previous
	 *            the previous action
	 * @param next
	 *            the next action
	 * @param x
	 *            the x value of the click point
	 * @param y
	 *            the y value of the click point
	 */
	public MouseClickNode( long timestamp, MouseGraphAction previous,
			MouseGraphAction next, double x, double y ) {
		super(timestamp, previous, next);
		range = new Rectangle2D.Double(x, y, 0, 0);
	}

	/**
	 * @param timestamp
	 *            the time of the action
	 * @param x
	 *            the x value of the click point
	 * @param y
	 *            the y value of the click point
	 */
	public MouseClickNode( long timestamp, double x, double y ) {
		super(timestamp);
		range = new Rectangle2D.Double(x, y, 0, 0);
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public Rectangle2D getRange() {
		return range;
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public Shape getArea() {
		return range;
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public Point2D getLocation() {
		return new Point2D.Double(range.getX(), range.getY());
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public double getError() {
		return 0;
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public Dimension2D getVariance() {
		return new Dim();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.fgcu.stesting.uiesg.data.MouseGraphAction#getType()
	 */
	@Override
	@SuppressWarnings( "javadoc" )
	public int getSubType() {
		return GODFactory.CLICK;
	}

}