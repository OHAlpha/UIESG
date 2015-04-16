package edu.fgcu.stesting.uiesg.data;

import java.awt.geom.Dimension2D;

/**
 * Represents movement of the mouse in the browser window.
 * 
 * @author oalpha
 *
 */
public interface MouseGraphEdge extends MouseGraphAction {
	
	/**
	 * Represents that the movement can be generalized as a straight line.
	 */
	public static int PATH_TYPE_ANY = 0;
	
	/**
	 * Represents that the movement can be generalized as a straight line.
	 */
	public static int PATH_TYPE_LINEAR = 1;

	/**
	 * Returns the type of regression the movement takes. Valid values are:
	 * TODO: enumerate path types
	 * 
	 * @return the type of regression
	 */
	int getPathType();

	/**
	 * Returns the parameters for the path. This is path type dependent but
	 * usually consists of an array of Point2D objects.
	 * 
	 * @return the parameters of the path
	 */
	Object getPathParameters();

	/**
	 * The action occurring immediately before this one. For MouseGraphEdges
	 * this is usually a MouseGraphNode. Exceptions include a user executing a
	 * complicated movement that the GOD instance breaks up into multiple
	 * regressions.
	 * 
	 * @return the previous action
	 */
	@Override
	public MouseGraphAction getPrevious();

	/**
	 * Returns how much the actual mouse data deviated from the regression. For
	 * edges, this the area between the actual path and the regression path.
	 * 
	 * @return the error
	 */
	@Override
	public double getError();

	/**
	 * Returns the variance of the actual path. For edges this is the variance
	 * of the velocity of the path.
	 * 
	 * @return the variance
	 */
	@Override
	public Dimension2D getVariance();

}