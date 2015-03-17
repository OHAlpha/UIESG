package edu.fgcu.stesting.uiesg.data;

import java.net.URL;
import java.util.Collection;
import java.util.Map;

/**
 * SiteDataSet is a container for all domain specific data generated and used by
 * the UIESG.
 * 
 * @author oalpha
 *
 */
public class SiteDataSet {

	/**
	 * DuplicateDomainException signifies that the domain for which a SED is
	 * being created for already is referenced by an existing SED.
	 * 
	 * @author oalpha
	 *
	 */
	public class DuplicateDomainException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = -7294874443006338512L;

		/**
		 * Constructs an instance for the given domain
		 * 
		 * @param domain
		 *            the domain to which the exception refers
		 */
		public DuplicateDomainException(String domain) {
			super(domain);
			// TODO Auto-generated constructor stub
		}

	}

	/**
	 * Returns an SED for the specified domain if it exists.
	 * 
	 * @param domain
	 *            the domain for which a SED is requested
	 * @return the SED for the specified domain
	 */
	public static SiteDataSet getForDomain(String domain) {
		throw new RuntimeException("method not implemented");
	}

	/**
	 * Constructs an instance for a specific domain. The instance of most each
	 * represent a unique domain. If an instance already exists for a given
	 * domain, an exception will be thrown.
	 * 
	 * @param domain
	 *            the domain for which this SED will store data
	 * 
	 * @exception DuplicateDomainException
	 *                thrown if a SED for the specified domain already exists
	 */
	public SiteDataSet(String domain) throws DuplicateDomainException {
		this.domain = domain;
		data = null;
		pages = null;
		// TODO
	}

	/**
	 * A wrapper for a MAID, GOD and a map of UIESs. It is solely for the
	 * simplification of manipulation the data collections so there is no need
	 * for any functionality.
	 * 
	 * @author oalpha
	 *
	 */
	class DataSet {

		/**
		 * The MAID of this set
		 */
		public MouseActionInputData mouseData;

		/**
		 * The GOD of this set
		 */
		public GraphOutputData graphData;

		/**
		 * The UIESs of this set
		 */
		public Map<String, UIEfficiencyStatistic> statistics;

	}

	/**
	 * The domain of this SED
	 */
	private final String domain;

	/**
	 * The MAIDs, GODs and UIESs for this SED
	 */
	@SuppressWarnings("unused")
	private Collection<DataSet> data;

	/**
	 * The contexts per page in the domain of this SED
	 */
	@SuppressWarnings("unused")
	private Map<URL, PageContext> pages;

	/**
	 * Loads the data and pages from file and returns true if successful and
	 * false otherwise.
	 * 
	 * @return whether the load was successful
	 */
	public boolean loadData() {
		throw new RuntimeException("method not implemented");
		// TODO
	}

	/**
	 * Saves the data and pages to file and returns true if successful and false
	 * otherwise.
	 * 
	 * @return whether the save was successful
	 */
	public boolean unloadData() {
		throw new RuntimeException("method not implemented");
		// TODO
	}

	/**
	 * Whether the data and pages resides in memory or on disk.
	 * 
	 * @return true if the data and pages reside in memory and false if on disk
	 */
	public boolean isLoaded() {
		throw new RuntimeException("method not implemented");
		// TODO
	}

	/**
	 * Returns th domain of this SED.
	 * 
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
		// TODO
	}

	/**
	 * Creates and returns a new MAID instance.
	 * 
	 * @return the newly created MAID instance
	 */
	public MouseActionInputData newMouseData() {
		throw new RuntimeException("method not implemented");
		// TODO
	}

	/**
	 * Creates a GOD instance for each MAID in the data.
	 */
	public void compileMouseData() {
		throw new RuntimeException("method not implemented");
		// TODO
	}

	/**
	 * Creates UIESs for each GOD in the data and each UIEfficiencyStatisticType
	 * in memory.
	 */
	public void calculateStatistics() {
		throw new RuntimeException("method not implemented");
		// TODO
	}

	/**
	 * Returns a PageContext for the specified URL if it exists.
	 * 
	 * @param url
	 *            the address of the desired PageContext
	 * @return the PageContext instance or null if it does not exist.
	 */
	public PageContext getForURL(URL url) {
		throw new RuntimeException("method not implemented");
		// TODO

	}

}