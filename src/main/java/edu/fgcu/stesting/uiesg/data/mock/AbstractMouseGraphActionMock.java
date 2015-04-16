package edu.fgcu.stesting.uiesg.data.mock;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.io.OutputStream;

import edu.fgcu.stesting.uiesg.data.graph.AbstractMouseGraphAction;

@SuppressWarnings( "javadoc" )
public abstract class AbstractMouseGraphActionMock extends AbstractMouseGraphAction {
	
	private int type;
	
	private int subType;

	/**
	 * @param type
	 * @param subType
	 */
	protected AbstractMouseGraphActionMock( int type, int subType ) {
		super();
		this.type = type;
		this.subType = subType;
	}

	@Override
	public double getError() {
		return 0;
	}

	@Override
	public Dimension2D getVariance() {
		return new Dimension(0,0);
	}

	/* (non-Javadoc)
	 * @see edu.fgcu.stesting.uiesg.data.MouseGraphAction#write(java.io.OutputStream)
	 */
	@Override
	public void write( OutputStream out ) {}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public int getSubType() {
		return subType;
	}

}