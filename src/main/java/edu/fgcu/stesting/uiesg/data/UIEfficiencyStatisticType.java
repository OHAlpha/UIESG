package edu.fgcu.stesting.uiesg.data;

import edu.fgcu.stesting.uiesg.data.imp.GraphOutputDataImp;

/**
 * UIEfficiencyStatisticType (UIEST) is a generator for a specific metric for
 * the efficiency of the user interface.
 * 
 * @author oalpha
 *
 */
public abstract class UIEfficiencyStatisticType {

	/**
	 * DuplicateTypeException signifies that a UIEST instance with the specified
	 * name already exists.
	 * 
	 * @author oalpha
	 *
	 */
	public class DuplicateTypeException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = -7294874443006338512L;

		/**
		 * Constructs an instance for the given name
		 * 
		 * @param name
		 *            the name to which the exception refers
		 */
		public DuplicateTypeException( String name ) {
			super(name);
			// TODO Auto-generated constructor stub
		}

	}

	/**
	 * Returns an UIEST instance with the specified name if it exists.
	 * 
	 * @param name
	 *            the name of UIEST requested
	 * @return the UIEST with the specified name
	 */
	public static SiteEfficiencyData getForDomain(String name) {
		throw new RuntimeException("method not implemented");
		// TODO
	}

	/**
	 * The name of this type of metric.
	 */
	@SuppressWarnings( "unused" )
	private final String name;

	/**
	 * Constructs a UIEST instance of the specified name. This method should not
	 * complete if a UIEST instance already exists with the specified name.
	 * 
	 * @param name
	 *            the name of this type
	 * @throws DuplicateTypeException
	 *             if an instance with the specified name already exists
	 */
	protected UIEfficiencyStatisticType( String name ) throws DuplicateTypeException {
		throw new RuntimeException("constructor not implemented");
	}

	/**
	 * Returns the name of this type of metric.
	 * 
	 * @return the name
	 */
	public abstract String getName();

	/**
	 * Returns the description of this type of metric.
	 * 
	 * @return the description
	 */
	public abstract String getDescription();

	/**
	 * Creates a UIES instance of this type from the given graph.
	 * 
	 * @param graph
	 *            the graph data to analyze
	 * @return the UIES instance
	 */
	public abstract UIEfficiencyStatistic calculate( GraphOutputDataImp graph );

	/**
	 * The type of value this type creates.
	 * 
	 * @return the class type
	 */
	public abstract Class<?> getValueType();

}