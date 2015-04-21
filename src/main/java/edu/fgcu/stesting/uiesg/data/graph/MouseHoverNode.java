package edu.fgcu.stesting.uiesg.data.graph;

import java.awt.Shape;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import edu.fgcu.stesting.uiesg.data.GODFactory;
import edu.fgcu.stesting.uiesg.data.MouseGraphAction;
import edu.fgcu.stesting.uiesg.data.MouseGraphNode;

/**
 * Represents the user's inactivity in the browser window.
 * 
 * @author oalpha
 *
 */
public class MouseHoverNode extends AbstractMouseGraphNode {

	/**
	 * The area hovered over.
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
	protected MouseHoverNode( long timestamp, MouseGraphAction previous,
			MouseGraphAction next, double[] data ) {
		super(timestamp, previous, next);
		double nx = data[0], ny = data[1], xx = data[0], xy = data[1];
		range = new Rectangle2D.Double(nx, ny, xx - nx, xy - ny);
	}

	/**
	 * @param timestamp
	 *            the time of the action
	 * @param x
	 *            the x value of the click point
	 * @param y
	 *            the y value of the click point
	 */
	protected MouseHoverNode( long timestamp, double[] data ) {
		super(timestamp);
		double nx = data[0], ny = data[1], xx = data[0], xy = data[1];
		range = new Rectangle2D.Double(nx, ny, xx - nx, xy - ny);
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public long getTimestamp() {
		throw new RuntimeException("method not implemented");
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public MouseGraphNode getNext() {
		throw new RuntimeException("method not implemented");
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public Rectangle2D getRange() {
		throw new RuntimeException("method not implemented");
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public Shape getArea() {
		throw new RuntimeException("method not implemented");
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public Point2D getLocation() {
		throw new RuntimeException("method not implemented");
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public MouseGraphAction getPrevious() {
		throw new RuntimeException("method not implemented");
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public double getError() {
		throw new RuntimeException("method not implemented");
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public Dimension2D getVariance() {
		throw new RuntimeException("method not implemented");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.fgcu.stesting.uiesg.data.MouseGraphAction#getType()
	 */
	@Override
	@SuppressWarnings( "javadoc" )
	public int getSubType() {
		return GODFactory.HOVER;
	}

}