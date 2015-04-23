package edu.fgcu.stesting.uiesg.data.graph;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import edu.fgcu.stesting.uiesg.data.GODFactory;

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
	 *            the time of the action
	 * @param data
	 *            the x and y values of the hover points
	 */
	public MouseDragEdge( long timestamp, double[] data ) {
		super(timestamp);
		calc(data);
	}

	/**
	 * @param timestamp
	 *            the time of the action
	 * @param source
	 *            the starting point
	 * @param dest
	 *            the ending point
	 * @param range
	 *            the range
	 * @param error
	 *            the error
	 * @param variance
	 *            the variance of position
	 */
	public MouseDragEdge( long timestamp, Point2D source, Point2D dest,
			Rectangle2D range, double error, Dimension2D variance ) {
		super(timestamp, source, dest, range, error, variance);
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

}