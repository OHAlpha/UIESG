package edu.fgcu.stesting.uiesg.data.graph;

import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;

import edu.fgcu.stesting.uiesg.data.GODFactory;
import edu.fgcu.stesting.uiesg.data.MouseGraphAction;

/**
 * Represents the user's inactivity in the browser window.
 * 
 * @author oalpha
 *
 */
public class MouseHoverNode extends AbstractMouseGraphNode {

	/**
	 * @param timestamp
	 *            the time of the action
	 * @param previous
	 *            the previous action
	 * @param next
	 *            the next action
	 * @param data
	 *            the x and y values of the hover points
	 */
	public MouseHoverNode( long timestamp, MouseGraphAction previous,
			MouseGraphAction next, double[] data ) {
		super(timestamp, previous, next);
		calc( data );
	}

	/**
	 * @param timestamp
	 *            the time of the action
	 * @param data
	 *            the x and y values of the hover points
	 */
	public MouseHoverNode( long timestamp, double[] data ) {
		super(timestamp);
		calc( data );
	}

	/**
	 * @param timestamp
	 *            the time of the action
	 * @param range the range
	 * @param error the error
	 * @param variance the variance of position
	 */
	public MouseHoverNode( long timestamp, Rectangle2D range, double error,
			Dimension2D variance ) {
		super(timestamp, range, error, variance);
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