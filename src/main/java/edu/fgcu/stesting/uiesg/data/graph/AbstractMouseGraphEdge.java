package edu.fgcu.stesting.uiesg.data.graph;

import java.awt.Shape;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import edu.fgcu.stesting.uiesg.data.GODFactory;
import edu.fgcu.stesting.uiesg.data.MouseGraphAction;
import edu.fgcu.stesting.uiesg.data.MouseGraphEdge;
import edu.fgcu.stesting.uiesg.data.MouseGraphNode;

/**
 * Provides the casting functions' implementations for nodes.
 * 
 * @author oalpha
 *
 */
@SuppressWarnings( "javadoc" )
public abstract class AbstractMouseGraphEdge extends AbstractMouseGraphAction
		implements MouseGraphEdge {

	/**
	 * The area hovered over.
	 */
	protected Rectangle2D range;

	/**
	 * The starting point.
	 */
	protected Point2D source;

	/**
	 * The ending point.
	 */
	protected Point2D dest;

	/**
	 * The error calculation.
	 */
	protected double error;

	/**
	 * The variance calculation.
	 */
	protected Dimension2D variance;

	/**
	 * @param timestamp
	 * @param previous
	 * @param next
	 */
	protected AbstractMouseGraphEdge( long timestamp,
			MouseGraphAction previous, MouseGraphAction next ) {
		super(timestamp, previous, next);
	}

	/**
	 * @param timestamp
	 */
	protected AbstractMouseGraphEdge( long timestamp ) {
		super(timestamp);
	}

	/**
	 * @param timestamp
	 * @param range
	 * @param error
	 * @param variance
	 */
	protected AbstractMouseGraphEdge( long timestamp, Point2D source,
			Point2D dest, Rectangle2D range, double error, Dimension2D variance ) {
		super(timestamp);
		this.range = range;
		this.error = error;
		this.variance = variance;
		this.source = source;
		this.dest = dest;
	}

	protected void calc( double[] data ) {
		source = new Point2D.Double(data[0], data[1]);
		dest = new Point2D.Double(data[data.length - 2], data[data.length - 1]);
		double nx = data[0], ny = data[1], xx = data[0], xy = data[1];
		error = 0;
		double lx = nx, ly = ny;
		double sx = nx, sy = ny;
		double[] ds = new double[data.length - 2];
		for (int i = 2; i < data.length; i += 2) {
			double tx = data[i], ty = data[i + 1];
			nx = tx < nx ? tx : nx;
			ny = ty < ny ? ty : ny;
			xx = tx > xx ? tx : xx;
			xy = ty > xy ? ty : xy;
			double dx = tx - lx, dy = ty - ly;
			ds[i - 2] = dx;
			ds[i - 1] = dy;
			error += Math.sqrt(dx * dx + dy * dy);
			sx += dx;
			sy += xy;
			lx = tx;
			ly = ty;
		}
		double Dx = data[data.length - 2] - data[0], Dy = data[data.length - 1]
				- data[1];
		error = Math.abs(error - Math.sqrt(Dx * Dx + Dy * Dy));
		// TODO: calculate error based on area instead of difference of length
		range = new Rectangle2D.Double(nx, ny, xx - nx, xy - ny);
		sx /= data.length / 2;
		sy /= data.length / 2;
		double vx = 0, vy = 0;
		for (int i = 0; i < ds.length; i += 2) {
			double tx = ds[i], ty = ds[i + 1];
			double dx = tx - sx, dy = ty - sy;
			vx += dx * dx;
			vy += dy * dy;
		}
		variance = new Dim(vx / (data.length / 2 - 1), vy
				/ (data.length / 2 - 1));
		range = new Rectangle2D.Double(nx, ny, xx - nx, xy - ny);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.fgcu.stesting.uiesg.data.MouseGraphAction#asNode()
	 */
	@Override
	public MouseGraphNode asNode() {
		throw new IllegalArgumentException("This action is an edge");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.fgcu.stesting.uiesg.data.MouseGraphAction#asEdge()
	 */
	@Override
	public MouseGraphEdge asEdge() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.fgcu.stesting.uiesg.data.MouseGraphAction#getType()
	 */
	@Override
	public int getType() {
		return GODFactory.EDGE;
	}

	@Override
	public Rectangle2D getRange() {
		return range;
	}

	@Override
	public Shape getArea() {
		return range;
	}

	@Override
	public Point2D getSource() {
		return source;
	}

	@Override
	public Point2D getDest() {
		return dest;
	}

	@Override
	public double getError() {
		return error;
	}

	@Override
	public Dimension2D getVariance() {
		return variance;
	}

	public boolean equals( Object o ) {
		if (getClass().isInstance(o)) {
			AbstractMouseGraphEdge e = (AbstractMouseGraphEdge) o;
			if (getType() != e.getType())
				return false;
			if (getSubType() != e.getSubType())
				return false;
			if (!range.equals(e.range))
				return false;
			if (error != e.error)
				return false;
			if (!variance.equals(e.variance))
				return false;
			if (!source.equals(e.source))
				return false;
			if (!dest.equals(e.dest))
				return false;
			return true;
		} else
			return false;
	}

	public void assertEquals( MouseGraphAction action ) throws AssertionError {
		if (getType() != action.getType())
			throw new AssertionError("not the same type");
		if (getSubType() != action.getSubType())
			throw new AssertionError("not the same subType");
		if (!getClass().isInstance(action))
			throw new AssertionError("not an AbstractMouseGraphEdge");
		AbstractMouseGraphEdge e = (AbstractMouseGraphEdge) action;
		if (!range.equals(e.range))
			throw new AssertionError("range should be " + range + " but is "
					+ e.range);
		if (error != e.error)
			throw new AssertionError("error should be " + error + " but is "
					+ e.error);
		if (!variance.equals(e.variance))
			throw new AssertionError("variance should be " + variance
					+ " but is " + e.variance);
		if (!source.equals(e.source))
			throw new AssertionError("source should be " + source + " but is "
					+ e.source);
		if (!dest.equals(e.dest))
			throw new AssertionError("dest should be " + dest + " but is "
					+ e.dest);
	}

	public String toString() {
		return types[getType()] + "-" + subTypes[getSubType()] + "( range: "
				+ range + ", error: " + error + ", variance: ("
				+ variance.getWidth() + "," + variance.getHeight()
				+ "), source: (" + source.getX() + "," + source.getY()
				+ "), dest: (" + dest.getX() + "," + dest.getY() + ") )";
	}

}
