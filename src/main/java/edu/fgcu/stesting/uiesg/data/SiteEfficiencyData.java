package edu.fgcu.stesting.uiesg.data;

import java.awt.geom.Point2D;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;

import edu.fgcu.stesting.uiesg.data.MouseActionInputData.Point;
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatisticType.UIEfficiencyStatistics;
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
	 * The collection to map domains to SED instances.
	 */
	protected static NavigableMap<String, SiteEfficiencyData> domains = new TreeMap<>();

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
	 * Returns all the available domain names from the collection.
	 * 
	 * @return the set of domain names.
	 */
	public static NavigableSet<String> getAvailableDomains() {
		return domains.navigableKeySet();
	}

	/**
	 * Removes all domains from the collection.
	 */
	protected static void clean() {
		domains.clear();
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
		public NavigableMap<String, UIEfficiencyStatistic> statistics;

	}

	/**
	 * The domain of this SED
	 */
	protected final String domain;

	/**
	 * The MAIDs, GODs and UIESs for this SED
	 */
	protected List<DataSet> data;

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
		} else {
			data = new ArrayList<>();
		}
		// System.out.println("\t\t\t" + domain + ".isLoaded() = " +
		// isLoaded());
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

		// return false if SED is already loaded
		if (isLoaded()) {
			;
			// System.out.println("\t\talready loaded");
			return false;
		}

		// datafile
		File file = dataFile();

		// check for existence
		if (!file.exists()) {
			;
			// System.out.println("\t\t"+file.getAbsolutePath()+"\n\t"+file.getName()+" does not exist");
			return false;
		}

		data = new ArrayList<>();

		// read in data
		try (DataInputStream in = new DataInputStream(new BufferedInputStream(
				new FileInputStream(file)))) {

			// read number of DataSets
			int size = in.readInt();

			// read each DataSet
			for (int i = 0; i < size; i++) {

				// create DataSet
				DataSet d = new DataSet();

				// mask is a bit vector
				// bit 0 is on when a MAID object exists
				// bit 1 is on when a GOD object exists
				// bit 2 is on when UIES objects exist
				int mask = in.readInt();

				// read MAID if it exists
				if ((mask & 0x01) > 0) {

					// create MAID
					d.mouseData = MAIDFactory.newInstance();

					// read number of Points
					int points = in.readInt();

					// read each Point
					for (int j = 0; j < points; j++) {

						// read positions
						double bx = in.readDouble(), by = in.readDouble(), px = in
								.readDouble(), py = in.readDouble();

						// read timestamp
						long time = in.readLong();

						// read type
						int type = in.readInt();

						// add Point
						d.mouseData.addPoint(new Point2D.Double(bx, by),
								new Point2D.Double(px, py), time, type);

					}

				}

				// read GOD if it exists
				if ((mask & 0x02) > 0) {

					// create GOD
					d.graphData = GODFactory.newInstance();

					// read number of actions
					int actions = in.readInt();
					// System.out.println(actions + " actions");

					// read actions
					for (int j = 0; j < actions; j++) {

						// read action
						MouseGraphAction action = GODFactory.read(in);
						// System.out.println("\t" + action);

						// read previous
						int prev = in.readInt();

						// set previous
						if (prev > -1 && j > 0) {
							MouseGraphAction previous = d.graphData
									.getAction(prev);
							if (previous != null) {
								previous.setNext(action);
								action.setPrevious(previous);
							}
						}

						// add action to graph
						d.graphData.addAction(action);
						// System.out.println("\t"
						// + (d.graphData.order() + d.graphData.size())
						// + " actions read");

					}
					// System.out.println((d.graphData.order() + d.graphData
					// .size()) + " actions read");

				}

				// read UIESs if they exist
				if ((mask & 0x04) > 0) {

					// create map
					d.statistics = new TreeMap<>();

					// read number of statistics
					int stats = in.readInt();

					// read statistics
					for (int j = 0; j < stats; j++) {

						// read type
						String type = in.readUTF();

						// read statistic
						d.statistics
								.put(type, UIEfficiencyStatistics.getType(type)
										.create(in));

					}

				}

				data.add(d);

			}

			return true;
		} catch (IOException e) {

			e.printStackTrace();

			// System.err.println("error loading");
			data = null;
			return false;

		}

	}

	/**
	 * Saves the data and pages to file and returns true if successful and false
	 * otherwise.
	 * 
	 * @return whether the save was successful
	 */
	public boolean unloadData() {

		// return false if SED is not loaded
		if (!isLoaded()) {
			;
			// System.out.println("\t\tnot loaded");
			return false;
		}

		// datafile
		File file = dataFile();

		// write in data
		try (DataOutputStream out = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream(file)))) {

			// write number of DataSets
			int size = data.size();
			out.writeInt(size);

			// write each DataSet
			for (int i = 0; i < size; i++) {

				// create DataSet
				DataSet d = data.get(i);

				// mask is a bit vector
				// bit 0 is on when a MAID object exists
				// bit 1 is on when a GOD object exists
				// bit 2 is on when UIES objects exist
				int mask = d.mouseData == null ? 0 : 1;
				mask |= d.graphData == null ? 0 : 2;
				mask |= d.statistics == null ? 0 : 4;
				out.writeInt(mask);

				// write MAID if it exists
				if (d.mouseData != null) {

					// write number of Points
					int points = d.mouseData.size();
					out.writeInt(points);

					// write each Point
					for (Iterator<Point> it = d.mouseData.iterate(); it
							.hasNext();) {

						// get Point
						Point p = it.next();

						// write positions
						out.writeDouble(p.browserLocation.getX());
						out.writeDouble(p.browserLocation.getY());
						out.writeDouble(p.pagePosition.getX());
						out.writeDouble(p.pagePosition.getY());

						// write timestamp
						out.writeLong(p.timestamp);

						// write type
						out.writeInt(p.type);

					}

				}

				// write GOD if it exists
				if (d.graphData != null) {

					// create GOD
					d.graphData = GODFactory.newInstance();

					// write number of actions
					int actions = d.graphData.order() + d.graphData.size();
					out.writeInt(actions);

					// write actions
					for (int j = 0; j < actions; j++) {

						// write action
						MouseGraphAction action = d.graphData.getAction(j);
						GODFactory.write(action, out);

						// write previous
						int prev = d.graphData.indexOf(action.getPrevious());
						out.writeInt(prev);

					}

				}

				// write UIESs if they exist
				if (d.statistics != null) {

					// write number of statistics
					int stats = d.statistics.size();
					out.writeInt(stats);

					// write statistics
					for (Iterator<String> it = d.statistics.keySet().iterator(); it
							.hasNext();) {

						// write type
						String type = it.next();
						out.writeUTF(type);

						// write statistic
						d.statistics.get(type).write(out);

					}

				}

			}

			out.flush();
			out.close();

			data = null;

			return true;
		} catch (IOException e) {

			e.printStackTrace();

			return false;

		}

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
	 * Returns the domain of this SED.
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
		// create a new dataset and add it to the collection "data" and then in
		// data create a new MAID in that dataset
		if (data == null)
			return null;
		DataSet d = new DataSet();
		// add mousedata to the dataset
		d.mouseData = MAIDFactory.newInstance();
		data.add(d);

		return d.mouseData;
	}

	/**
	 * Creates a GOD instance for each MAID in the data.
	 */
	public void compileMouseData() {
		if (data == null)
			throw new RuntimeException("sed is not loaded");

		// go through all the datasets if MAID is not null, but GOD is null then
		// create a new GOD based on the MAID
		for (Iterator<DataSet> it = data.iterator(); it.hasNext();) {
			DataSet d = it.next();
			if (d.mouseData != null) {
				if (d.graphData == null) {
					GraphOutputDataImp GOD = new GraphOutputDataImp(
							d.mouseData.iterate());// creates a new instance of
													// GOD based on MAID
					d.graphData = GOD;
				}
			}
		}
	}

	/**
	 * Creates UIESs for each GOD in the data and each UIEfficiencyStatisticType
	 * in memory.
	 */
	public void calculateStatistics() {
		if (data == null)
			throw new RuntimeException("sed is not loaded");

		// if there is a statistics that is null and a GOD that is not null then
		// create a statistics based on the GOD
		for (Iterator<DataSet> it = data.iterator(); it.hasNext();) {
			DataSet d = it.next();
			if (d.mouseData.size() > 0)
				if (d.statistics == null) {
					if (d.graphData != null) {
						d.graphData = GODFactory.newInstance(d.mouseData
								.iterate());
					}
					// statistics type will already exist. a static method
					// calculate all statistics will be added
					d.statistics = UIEfficiencyStatistics.calculateStatistics(
							d.mouseData, d.graphData);

				}
		}
	}

	/**
	 * Returns the maid at the specified index.
	 * 
	 * @param i
	 *            the index
	 * @return the maid
	 */
	public MouseActionInputData getMouseData( int i ) {
		return data.get(i).mouseData;
	}

	/**
	 * Returns the god at the specified index.
	 * 
	 * @param i
	 *            the index
	 * @return the god
	 */
	public GraphOutputData getGraphData( int i ) {
		return data.get(i).graphData;
	}

	/**
	 * Returns the statistics at the specified index.
	 * 
	 * @param i
	 *            the index
	 * @return the statistics
	 */
	public NavigableMap<String, UIEfficiencyStatistic> getStatistics( int i ) {
		return data.get(i).statistics;
	}

	@Override
	@SuppressWarnings( "javadoc" )
	public String toString() {
		return "SED( domain: " + domain + ", #datasets: " + data.size() + " )";
	}

	/**
	 * Returns the number of data sets.
	 * 
	 * @return the number of sets
	 */
	public int size() {
		return data.size();
	}

	/**
	 * Returns the data set at the given index.
	 * 
	 * @param i
	 *            the index of the desired data set
	 * @return the number of data set
	 */
	public DataSet getSet( int i ) {
		return data.get(i);
	}

}
