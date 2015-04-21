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
	 * @param timestamp
	 * @param previous
	 * @param next
	 */
	protected MouseHoverNode( long timestamp, MouseGraphAction previous,
			MouseGraphAction next ) {
		super(timestamp, previous, next);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param timestamp
	 */
	protected MouseHoverNode( long timestamp ) {
		super(timestamp);
		// TODO Auto-generated constructor stub
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