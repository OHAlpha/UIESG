package edu.fgcu.stesting.uiesg.data;

import java.awt.geom.Rectangle2D;

/**
 * This class represents a clickable element on a page such as a hyperlink.
 * 
 * @author oalpha
 *
 */
public class MouseTarget {

	/**
	 * The type of this element. Valid values are: TODO: enumerate MouseTarget
	 * types
	 */
	@SuppressWarnings( "unused" )
	private final int type;

	/**
	 * The rectangular boundaries of this target.
	 */
	@SuppressWarnings( "unused" )
	private Rectangle2D bounds;

	/**
	 * Constructs a MouseTarget of the specified type.
	 * 
	 * @param type
	 *            the type of this instance
	 * @throws IllegalArgumentException
	 *             if the specified type is not of the allowed types
	 */
	public MouseTarget( int type ) throws IllegalArgumentException {
		throw new RuntimeException("constructor not implemented");
	}

	/**
	 * Returns the type of this instance.
	 * 
	 * @return the type
	 */
	public int getType() {
		throw new RuntimeException("method not implemented");
	}

	/**
	 * Returns the clickable region of this target.
	 * 
	 * @return the boundary
	 */
	public Rectangle2D getBounds() {
		throw new RuntimeException("method not implemented");
	}

	/**
	 * Sets the bounds of this target if the target is not "static". If this
	 * method is called, the target should persist only as long as the owning
	 * page is opened in the browser window.
	 * 
	 * @param bounds
	 *            the bounds of this target
	 */
	public void setBounds( Rectangle2D bounds ) {
		throw new RuntimeException("method not implemented");
	}

	/**
	 * Returns whether this target is "stable". This will return true for as
	 * long as setBounds() is not called on this target. This is called by the
	 * PageContext owner to determine whether or not the target should persist.
	 * 
	 * @return if this target is "static"
	 */
	public boolean isStatic() {
		throw new RuntimeException("method not implemented");
	}

}