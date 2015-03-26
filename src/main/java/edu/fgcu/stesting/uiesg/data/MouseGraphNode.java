package edu.fgcu.stesting.uiesg.data;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

/**
 * Represents a static MouseEvent in the browser window such as a click.
 * 
 * @author oalpha
 *
 */
public interface MouseGraphNode extends MouseGraphAction {

	/**
	 * Returns the location of the event. For clicks, enters and exits, this is
	 * exact. For hovers, this is approximate.
	 * 
	 * @return the location
	 */
	Point2D getLocation();

	/**
	 * The action occurring immediately before this one. For NouseGraphNodes
	 * this is usually a MouseGraphEdge. Exceptions include a user hovering
	 * before a click.
	 * 
	 * @return the previous action
	 */
	@Override
	public MouseGraphAction getPrevious();

	/**
	 * Returns how much the actual mouse data deviated from the regression. For
	 * nodes, this is the length of the actual path.
	 * 
	 * @return the error
	 */
	@Override
	public double getError();

	/**
	 * Returns the variance of the actual path. For nodes this is the variance
	 * of the position of the path.
	 * 
	 * @return the variance
	 */
	@Override
	public Dimension2D getVariance();

}