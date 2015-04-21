package edu.fgcu.stesting.uiesg.data.mock;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;

import edu.fgcu.stesting.uiesg.data.graph.AbstractMouseGraphAction;

@SuppressWarnings( "javadoc" )
public abstract class AbstractMouseGraphActionMock extends
		AbstractMouseGraphAction {

	private int type;

	private int subType;

	/**
	 * @param type
	 * @param subType
	 */
	protected AbstractMouseGraphActionMock( long timestamp, int type,
			int subType ) {
		super(timestamp);
		this.type = type;
		this.subType = subType;
	}

	@Override
	public double getError() {
		return 0;
	}

	@Override
	public Dimension2D getVariance() {
		return new Dimension(0, 0);
	}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public int getSubType() {
		return subType;
	}

	@Override
	public boolean equals( Object o ) {
		return getClass().isInstance(o)
				&& ((AbstractMouseGraphAction) o).getTimestamp() == getTimestamp();
	}

}