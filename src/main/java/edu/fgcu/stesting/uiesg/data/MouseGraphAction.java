package edu.fgcu.stesting.uiesg.data;

import java.awt.Shape;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.io.OutputStream;

/**
 * Represents an MouseEvent or group of MouseEvents in the browser window.
 * 
 * @author oalpha
 *
 */
public interface MouseGraphAction {

	@SuppressWarnings( "javadoc" )
	public static final String[] types = { "Node", "Edge" };

	@SuppressWarnings( "javadoc" )
	public static final String[] subTypes = { "ENTER", "HOVER", "CLICK",
			"EXIT", "MOVE", "DRAG" };

	// TODO: javadoc
	@SuppressWarnings( "javadoc" )
	public static final int NODE_TYPE_MASK = 0x00ff;

	// TODO: javadoc
	@SuppressWarnings( "javadoc" )
	public static final int EDGE_TYPE_MASK = 0xff00;

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
	MouseGraphAction getNext();

	/**
	 * The action occurring immediately before this one. For MouseGraphEdges
	 * this is usually a MouseGraphNode and NouseGraphNodes this is usually a
	 * MouseGraphEdge. Exceptions include a user hovering before a click or
	 * executing a complicated movement that the GOD instance breaks up into
	 * multiple regressions.
	 * 
	 * @param previous
	 *            the previous action
	 */
	void setPrevious( MouseGraphAction previous );

	/**
	 * The action occurring immediately after this one.
	 * 
	 * @param next
	 *            the next action
	 */
	void setNext( MouseGraphAction next );

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

	// TODO: javadoc
	@SuppressWarnings( "javadoc" )
	void write( OutputStream out );

	/**
	 * Returns this action cast to a node. If this action is an edge, an
	 * IllegalArgumentException will be thrown.
	 * 
	 * @return this action as a node
	 */
	MouseGraphNode asNode();

	/**
	 * Returns this action cast to a edge. If this action is an node, an
	 * IllegalArgumentException will be thrown.
	 * 
	 * @return this action as a edge
	 */
	MouseGraphEdge asEdge();

	/**
	 * Returns the integral type of this action.
	 * 
	 * @return the type
	 */
	int getType();

	/**
	 * Returns the integral sub type of this action.
	 * 
	 * @return the sub type
	 */
	int getSubType();

	/**
	 * Throws an error if this action is not equivalent to the specified action.
	 * 
	 * @param action
	 *            the action to test
	 * @param error
	 *            if an error should be thrown
	 * @throws AssertionError
	 *             if these actions are not equivalent
	 * @return if these actions are equal
	 */
	boolean assertEquals( MouseGraphAction action, boolean error )
			throws AssertionError;

}