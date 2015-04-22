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
public abstract class AbstractMouseGraphNode extends AbstractMouseGraphAction
		implements MouseGraphNode {

	/**
	 * The area hovered over.
	 */
	protected Rectangle2D range;

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
	protected AbstractMouseGraphNode( long timestamp,
			MouseGraphAction previous, MouseGraphAction next ) {
		super(timestamp, previous, next);
	}

	/**
	 * @param timestamp
	 */
	protected AbstractMouseGraphNode( long timestamp ) {
		super(timestamp);
	}

	protected void calc( double[] data ) {
		double nx = data[0], ny = data[1], xx = data[0], xy = data[1];
		error = 0;
		double lx = nx, ly = ny;
		double sx = nx, sy = ny;
		for (int i = 2; i < data.length; i += 2) {
			double tx = data[i], ty = data[i + 1];
			nx = tx < nx ? tx : nx;
			ny = ty < ny ? ty : ny;
			xx = tx > xx ? tx : xx;
			xy = ty > xy ? ty : xy;
			double dx = tx - lx, dy = ty - ly;
			error += Math.sqrt(dx * dx + dy * dy);
			sx += tx;
			sy += ty;
			lx = tx;
			ly = ty;
		}
		range = new Rectangle2D.Double(nx, ny, xx - nx, xy - ny);
		sx /= data.length / 2;
		sy /= data.length / 2;
		double vx = 0, vy = 0;
		for (int i = 0; i < data.length; i += 2) {
			double tx = data[i], ty = data[i + 1];
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
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.fgcu.stesting.uiesg.data.MouseGraphAction#asEdge()
	 */
	@Override
	public MouseGraphEdge asEdge() {
		throw new IllegalArgumentException("This action is a node");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.fgcu.stesting.uiesg.data.MouseGraphAction#getType()
	 */
	@Override
	public int getType() {
		return GODFactory.NODE;
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
	public Point2D getLocation() {
		return new Point2D.Double(range.getX(), range.getY());
	}

	@Override
	public double getError() {
		return error;
	}

	@Override
	public Dimension2D getVariance() {
		return variance;
	}

}
