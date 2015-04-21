package edu.fgcu.stesting.uiesg.data.graph;

import java.awt.Shape;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import edu.fgcu.stesting.uiesg.data.GODFactory;
import edu.fgcu.stesting.uiesg.data.MouseGraphAction;
import edu.fgcu.stesting.uiesg.data.MouseGraphNode;

/**
 * Represents dragging of the mouse in the browser window. The previous and next
 * actions will be either other MouseDragEdges or MouseClickNodes.
 * 
 * @author oalpha
 *
 */
public class MouseDragEdge extends AbstractMouseGraphEdge {

	/**
	 * @param timestamp
	 * @param previous
	 * @param next
	 */
	protected MouseDragEdge( long timestamp, MouseGraphAction previous,
			MouseGraphAction next ) {
		super(timestamp, previous, next);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param timestamp
	 */
	protected MouseDragEdge( long timestamp ) {
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
	public MouseGraphAction getPrevious() {
		throw new RuntimeException("method not implemented");
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public MouseGraphNode getNext() {
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
	public int getPathType() {
		throw new RuntimeException("method not implemented");
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public Object getPathParameters() {
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
		return GODFactory.DRAG;
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public Point2D getSource() {
		throw new RuntimeException("method not implemented");
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public Point2D getDest() {
		throw new RuntimeException("method not implemented");
	}

}