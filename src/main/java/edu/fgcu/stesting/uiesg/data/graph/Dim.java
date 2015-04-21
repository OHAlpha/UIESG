package edu.fgcu.stesting.uiesg.data.graph;

import java.awt.geom.Dimension2D;

@SuppressWarnings( "javadoc" )
public class Dim extends Dimension2D {
	
	double w;
	
	double h;

	public Dim() {
		setSize(0,0);
	}

	/**
	 * @param w
	 * @param h
	 */
	protected Dim( double w, double h ) {
		super();
		this.w = w;
		this.h = h;
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
	}

}
