package edu.fgcu.stesting.uiesg.data.graph;

import java.awt.geom.Rectangle2D;

import edu.fgcu.stesting.uiesg.data.GODFactory;

/**
 * Represents the mouse entering or exiting the browser window.
 * 
 * @author oalpha
 *
 */
public class MouseWindowNode extends AbstractMouseGraphNode {
	
	/**
	 * Whether this action was an enter.
	 */
	private boolean enter;

	/**
	 * @param timestamp
	 *            the time of the action
	 * @param enter whether this action was an enter
	 * @param x
	 *            the x value of the click point
	 * @param y
	 *            the y value of the click point
	 */
	public MouseWindowNode( long timestamp, boolean enter, double x, double y ) {
		super(timestamp);
		range = new Rectangle2D.Double(x, y, 0, 0);
		error = 0;
		variance = new Dim();
		this.enter = enter;
	}
	
	/**
	 * @return whether this action was an enter
	 */
	public boolean isEntered() {
		return enter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.fgcu.stesting.uiesg.data.MouseGraphAction#getType()
	 */
	@Override
	@SuppressWarnings( "javadoc" )
	public int getSubType() {
		return enter ? GODFactory.ENTER : GODFactory.EXIT;
	}

}