package edu.fgcu.stesting.uiesg.data;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import edu.fgcu.stesting.uiesg.data.imp.GraphOutputDataImp;

/**
 * SiteEfficiencyData (SED) is a container for all domain specific data
 * generated and used by the UIESG.
 * 
 * @author oalpha
 *
 */
public class SiteEfficiencyData {

	/**
	 * DuplicateDomainException signifies that the domain for which a SED is
	 * being created for already is referenced by an existing SED.
	 * 
	 * Since the constructor has been made protected, this exception is no longer
	 * needed.
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
		public DuplicateDomainException( String domain ) {
			super(domain);
			// TODO Auto-generated constructor stub
		}

	}

	/**
	 * The collection to map domains to SED instances.
	 */
	protected static Map<String, SiteEfficiencyData> domains = new TreeMap<>();

	/**
	 * Returns an SED for the specified domain if it exists. If none exists, one
	 * will be created.
	 * 
	 * @param domain
	 *            the domain for which a SED is requested
	 * @return the SED for the specified domain
	 */
	public static SiteEfficiencyData getForDomain( String domain ) {
		if (domains.containsKey(domain))
			return domains.get(domain);
		else {
			SiteEfficiencyData sed = new SiteEfficiencyData(domain);
			domains.put(domain, sed);
			return sed;
		}
	}

	/**
	 * The folder to store and lookup data files.
	 */
	protected static File dataFileDir;

	/**
	 * Initializes the data file directory for loading and unloading of SED
	 * instances.
	 * 
	 * @param directory
	 *            the folder to store and lookup data files
	 * @return if the supplied directory is valid
	 */
	public static boolean init( String directory ) {
		dataFileDir = new File(directory);
		if (dataFileDir.exists())
			if (dataFileDir.isFile()) {
				dataFileDir = null;
				return false;
			} else
				return true;
		else {
			boolean success = dataFileDir.mkdirs();
			if (success)
				return true;
			else {
				dataFileDir = null;
				return false;
			}
		}
	}

	/**
	 * A wrapper for a MAID, GOD and a map of UIESs. It is solely for the
	 * simplification of manipulation the data collections so there is no need
	 * for any functionality.
	 * 
	 * @author oalpha
	 *
	 */
	protected static class DataSet {

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
	protected final String domain;

	/**
	 * The MAIDs, GODs and UIESs for this SED
	 */
	protected Collection<DataSet> data;

	/**
	 * The contexts per page in the domain of this SED
	 */
	@SuppressWarnings( "unused" )
	protected Map<String, PageContext> pages;

	/**
	 * Constructs an instance for a specific domain. Each instance must each
	 * represent a unique domain. The initial state of the SED is determined by
	 * whether or not a data file already exists. If one does, the SED will not
	 * be usable until explicitly loaded. Otherwise, the SED will be initialized
	 * as an empty SED and calls to isLoaded will return true until unloaded.
	 * Also, if the SED is initialized, a PageContext for the homepage, that is
	 * the URL consisting solely of the domain, will be automatically created.
	 * If it is not initialized, when it is loaded, a PageContext for the
	 * homepage will be created if it does not already exist.
	 * 
	 * @param domain
	 *            the domain for which this SED will store data
	 */
	protected SiteEfficiencyData( String domain ) {
		this.domain = domain;
		File df = dataFile();
		if (df.exists()) {
			data = null;
			pages = null;
		}
		else {
			data = new ArrayList<>();
			pages = new TreeMap<>();
		}
	}

	/**
	 * Returns the file pointing to the ( possibly nonexistent ) data file for
	 * this domain.
	 * 
	 * @return the File
	 */
	protected File dataFile() {
		return new File(dataFileDir, domain + ".sed");
	}

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
		return data != null;
	}

	/**
	 * Returns th domain of this SED.
	 * 
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
		// TODO
		// 
		
	}

	/**
	 * Creates and returns a new MAID instance.
	 * 
	 * @return the newly created MAID instance
	 */
	public MouseActionInputData newMouseData() {
		// create a new dataset and add it to the collection "data" and then in data create a new MAID in that dataset
		//create new instance of dataset and then do data.add(someName)
		DataSet d = new DataSet();
		// add mousedata to the dataset
		//d.mouseData = new MAIDFactory.newInstance(); MAIDFactory isn't setup yet. 
		data.add(d);
		
		return d.mouseData;
	}

	/**
	 * Creates a GOD instance for each MAID in the data.
	 */
	public void compileMouseData() {
		
		// go through all the datasets if MAID is not null, but GOD is null then create a new GOD based on the MAID
		for (Iterator<DataSet> it = data.iterator(); it.hasNext();){
			DataSet d = it.next();
			if (d.mouseData != null){
				if (d.graphData == null){
					//GraphOutputDataImp GOD = new GraphOutputDataImp(d.mouseData); creates a new instance of GOD based on MAID
				}				
			}
		}
	}

	/**
	 * Creates UIESs for each GOD in the data and each UIEfficiencyStatisticType
	 * in memory.
	 */
	public void calculateStatistics() {
		
		// if there is a statistics that is null and a GOD that is not null then create a statistics based on the GOD
		for (Iterator<DataSet> it = data.iterator(); it.hasNext();){
			DataSet d = it.next();
			if (d.statistics == null){
				if (d.graphData != null)
					
			}
		}
	}
	

	/**
	 * Returns a PageContext for the specified URL if it exists.
	 * 
	 * @param url
	 *            the address of the desired PageContext
	 * @return the PageContext instance or null if it does not exist.
	 */
	public PageContext getForURL( URL url ) {
		throw new RuntimeException("method not implemented");
		// TODO

	}

}
