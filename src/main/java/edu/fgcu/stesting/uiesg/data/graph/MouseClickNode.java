package edu.fgcu.stesting.uiesg.data.graph;

import java.awt.geom.Rectangle2D;

import edu.fgcu.stesting.uiesg.data.GODFactory;

/**
 * Represents an click event in the browser window.
 * 
 * @author oalpha
 *
 */
public class MouseClickNode extends AbstractMouseGraphNode {

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
		error = 0;
		variance = new Dim();
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