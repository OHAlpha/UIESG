package edu.fgcu.stesting.uiesg.data.graph;

import java.awt.Shape;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represents an MouseEvent or group of MouseEvents in the browser window.
 * 
 * @author oalpha
 *
 */
public interface MouseGraphAction {

	public static class MouseGraphActionFactory {

		public static final int MOCK = 0;

		public static final int IMPLEMENTATION = 1;

		protected static int mode;
		
		public static MouseGraphAction create( int type, Object... params ) {
			throw new RuntimeException("method not implemented");
			// TODO
		}
		
		public static MouseGraphAction read( InputStream in ) {
			throw new RuntimeException("method not implemented");
			// TODO
		}

		public static void init( int mode ) {
			MouseGraphActionFactory.mode = mode;
		}

	}
	
	public static final int NODE_TYPE_MASK = 0x00ff;
	
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
	 * @return the previous action
	 */
	void setPrevious( MouseGraphAction previous );

	/**
	 * The action occurring immediately after this one.
	 * 
	 * @return the next action
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
	
	void write( OutputStream out );

}