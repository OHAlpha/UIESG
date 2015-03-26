package edu.fgcu.stesting.uiesg.data;

import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

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
	public static class DuplicateTypeException extends Exception {

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

	// TODO: javadoc
	@SuppressWarnings( "javadoc" )
	public static class UIEfficiencyStatistics {
		
		protected static Map<String, UIEfficiencyStatisticType> types = new TreeMap<>();

		public static Map<String, UIEfficiencyStatistic> calculateStatistics(
				GraphOutputData graphData ) {
			Map<String, UIEfficiencyStatistic> statistics = new TreeMap<>();
			for( String type : types.keySet() )
				statistics.put(type, types.get(type).calculate(graphData));
			return statistics;
		}
		
		public static void addType( UIEfficiencyStatisticType type ) throws DuplicateTypeException {
			if( types.containsKey(type.getName()))
				throw new DuplicateTypeException(type.getName());
			types.put(type.getName(), type);
		}
		
		public static UIEfficiencyStatisticType getType( String type ) {
			return types.get(type);
		}

	}

	/**
	 * Returns an UIEST instance with the specified name if it exists.
	 * 
	 * @param name
	 *            the name of UIEST requested
	 * @return the UIEST with the specified name
	 */
	public static UIEfficiencyStatisticType getByName( String name ) {
		return UIEfficiencyStatistics.getType(name);
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
	protected UIEfficiencyStatisticType( String name )
			throws DuplicateTypeException {
		UIEfficiencyStatistics.addType(this);
		this.name = name;
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
	public abstract UIEfficiencyStatistic calculate( GraphOutputData graph );

	/**
	 * Creates a UIES instance of this type from an input stream.
	 * 
	 * @param in
	 *            the stream of data to read
	 * @return the UIES instance
	 */
	public abstract UIEfficiencyStatistic create( InputStream in );

	/**
	 * The type of value this type creates.
	 * 
	 * @return the class type
	 */
	public abstract Class<?> getValueType();

}