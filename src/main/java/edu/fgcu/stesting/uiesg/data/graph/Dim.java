package edu.fgcu.stesting.uiesg.data.graph;

import java.awt.geom.Dimension2D;

@SuppressWarnings( "javadoc" )
public class Dim extends Dimension2D {

	double w;

	double h;

	public Dim() {
		setSize(0, 0);
	}

	/**
	 * @param w
	 * @param h
	 */
	public Dim( double w, double h ) {
		setSize(w, h);
	}

	@Override
	public double getWidth() {
		return w;
	}

	@Override
	public double getHeight() {
		return h;
	}

	@Override
	public void setSize( double width, double height ) {
		this.w = width;
		this.h = height;
		if( Double.isNaN(width) )
			throw new IllegalArgumentException("width cannot be NaN");
		if( Double.isNaN(height) )
			throw new IllegalArgumentException("height cannot be NaN");
	}

	public boolean equals( Object o ) {
		if (o instanceof Dimension2D) {
			Dimension2D d = (Dimension2D) o;
			return w == d.getWidth() && h == d.getHeight();
		}
		return false;
	}

	public String toString() {
		return "Dim(" + w + "," + h + ")";
	}

}
