package edu.fgcu.stesting.uiesg.data;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * UIEfficiencyStatistic (UIES) is a container for a specific metric for the
 * efficiency of the user interface.
 * 
 * @author oalpha
 *
 */
public class UIEfficiencyStatistic {

	/**
	 * The type and generator of this UIES instance.
	 */
	private final UIEfficiencyStatisticType type;

	/**
	 * The metric value.
	 */
	private final Object value;

	/**
	 * Constructs a with the specified type and value.
	 * 
	 * @param type
	 *            the type of the statistic
	 * @param value
	 *            the value of the statistic
	 */
	protected UIEfficiencyStatistic( UIEfficiencyStatisticType type,
			Object value ) {
		this.type = type;
		this.value = value;
	}

	/**
	 * Returns the type of this instance.
	 * 
	 * @return the type
	 */
	public UIEfficiencyStatisticType getType() {
		return type;
	}

	/**
	 * Returns the value of this instance.
	 * 
	 * @return the value
	 */
	public Object getUIValue() {
		return value;
	}

	/**
	 * Writes this statistic to a stream.
	 * 
	 * @param out
	 *            the output stream
	 * @throws IOException if the save fails
	 */
	public void write( DataOutputStream out ) throws IOException {
		type.write(this, out);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	@SuppressWarnings( "javadoc" )
	public boolean equals( Object o ) {
		if (o instanceof UIEfficiencyStatistic) {
			UIEfficiencyStatistic uies = (UIEfficiencyStatistic) o;
			return uies.type == type && uies.getUIValue().equals(value);
		} else
			return false;
	}

}