package edu.fgcu.stesting.uiesg.data.graph;

import java.awt.Shape;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;

/**
 * Represents an MouseEvent or group of MouseEvents in the browser window.
 * 
 * @author oalpha
 *
 */
public interface MouseGraphAction {

	/**
	 * Returns the timestamp of the MouseEvent or the earliest MouseEvent if
	 * this action represents multiple.
	 * 
	 * @return the timestamp
	 */
	long getTimestamp();

	/**
	 * The action occurring immediately before this one. For MouseGraphEdges
	 * this is usually a MouseGraphNode and NouseGraphNodes this is usually a
	 * MouseGraphEdge. Exceptions include a user hovering before a click or
	 * executing a complicated movement that the GOD instance breaks up into
	 * multiple regressions.
	 * 
	 * @return the previous action
	 */
	MouseGraphAction getPrevious();

	/**
	 * The action occurring immediately after this one.
	 * 
	 * @return the next action
	 */
	MouseGraphNode getNext();

	/**
	 * Returns how much the actual mouse data deviated from the regression. For
	 * edges, this the area between the actual path and the regression path. For
	 * nodes, this is the length of the actual path.
	 * 
	 * @return the error
	 */
	double getError();

	/**
	 * Returns the variance of the actual path. For edges this is the variance
	 * of the velocity of the path and for nodes this is the variance of the
	 * position of the path.
	 * 
	 * @return the variance
	 */
	Dimension2D getVariance();

	/**
	 * Returns the rectangular bounds of the path.
	 * 
	 * @return the bounds
	 */
	Rectangle2D getRange();

	/**
	 * Returns the shape of minimal area bounding the path.
	 * 
	 * @return the bounds
	 */
	Shape getArea();

}