package edu.fgcu.stesting.uiesg.data;

import java.io.DataOutputStream;
import java.io.OutputStream;

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
	 * @param type the type of the statistic
	 * @param value the value of the statistic
	 */
	protected UIEfficiencyStatistic( UIEfficiencyStatisticType type, Object value ) {
		throw new RuntimeException("constructor not implemented");
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
	public Object getValue() {
		return value;
	}

	public void write( OutputStream out ) {
		// TODO Auto-generated method stub
		
	}

}