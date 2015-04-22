package edu.fgcu.stesting.uiesg.data.mock;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;

import edu.fgcu.stesting.uiesg.data.MouseGraphAction;
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
				&& ((AbstractMouseGraphAction) o).getTimestamp() == getTimestamp()
				&& ((AbstractMouseGraphAction) o).getType() == getType()
				&& ((AbstractMouseGraphAction) o).getSubType() == getSubType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.fgcu.stesting.uiesg.data.MouseGraphAction#assertEquals(edu.fgcu.stesting
	 * .uiesg.data.MouseGraphAction)
	 */
	@Override
	public void assertEquals( MouseGraphAction action ) throws AssertionError {
		if (!equals(action))
			throw new AssertionError("not equal");
	}

	public String toString() {
		return types[getType()] + "-" + subTypes[getSubType()] + "( range: "
				+ getRange() + ", error: " + getError() + ", variance: ("
				+ getVariance().getWidth() + "," + getVariance().getHeight()
				+ ") )";
	}

}