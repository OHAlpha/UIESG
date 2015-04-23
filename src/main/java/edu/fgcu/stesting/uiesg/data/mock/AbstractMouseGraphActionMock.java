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
		MouseGraphAction a = (MouseGraphAction) o;
		return assertEquals(a, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.fgcu.stesting.uiesg.data.MouseGraphAction#assertEquals(edu.fgcu.stesting
	 * .uiesg.data.MouseGraphAction)
	 */
	@Override
	public boolean assertEquals( MouseGraphAction action, boolean error )
			throws AssertionError {
		if (!getClass().isInstance(action))
			if (error)
				throw new AssertionError("not same class");
			else
				return false;
		if (action.getTimestamp() != getTimestamp())
			if (error)
				throw new AssertionError("timestamp should be "
						+ getTimestamp() + " but is " + action.getTimestamp());
			else
				return false;
		if (action.getType() != getType())
			if (error)
				throw new AssertionError("type should be " + getType()
						+ " but is " + action.getType());
			else
				return false;
		if (action.getSubType() != getSubType())
			if (error)
				throw new AssertionError("subType should be " + getSubType()
						+ " but is " + action.getSubType());
			else
				return false;
		return true;
	}

	public String toString() {
		return types[getType()] + "-" + subTypes[getSubType()] + "( range: "
				+ getRange() + ", error: " + getError() + ", variance: ("
				+ getVariance().getWidth() + "," + getVariance().getHeight()
				+ ") )";
	}

}