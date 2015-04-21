package edu.fgcu.stesting.uiesg.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.fgcu.stesting.uiesg.data.SiteEfficiencyData.DataSet;
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatisticType.DuplicateTypeException;
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatisticType.UIEfficiencyStatistics;
import edu.fgcu.stesting.uiesg.data.imp.GraphOutputDataImp;
import edu.fgcu.stesting.uiesg.data.imp.MouseActionInputDataImp;
import edu.fgcu.stesting.uiesg.data.statistic.NodesPerMinute;

/**
 * Integration tests for the SED class using implementations of MAID, GOD, UIEST
 * and PageContext.
 * 
 * @author oalpha
 *
 */
public class SiteEfficiencyDataIntegrationTest {

	/**
	 * The directory where prewritten data files reside.
	 */
	static File dir = new File(
			"src/test/java/edu/fgcu/stesting/uiesg/data/datafiles");

	/**
	 * The IMPLEMENTATION maid to use for testing.
	 */
	static MouseActionInputData mm;

	/**
	 * The IMPLEMENTATION god to use for testing.
	 */
	static GraphOutputData mg;

	/**
	 * The IMPLEMENTATION uiest to use for testing.
	 */
	static UIEfficiencyStatisticType mt;

	/**
	 * The IMPLEMENTATION uies to use for testing.
	 */
	static UIEfficiencyStatistic ms;

	/**
	 * A SED for "fgcu.edu".
	 */
	SiteEfficiencyData fgcu;

	/**
	 * A SED for "wikipedia.org".
	 */
	SiteEfficiencyData wiki;

	/**
	 * Initializes SiteEfficiencyData.
	 */
	@BeforeClass
	public static void setup() {
		if (!dir.exists())
			dir.mkdirs();
		SiteEfficiencyData.init("tmp/datafiles");
		MAIDFactory.init(MAIDFactory.IMPLEMENTATION);
		GODFactory.init(GODFactory.IMPLEMENTATION);
		try {
			new NodesPerMinute();
		} catch (DuplicateTypeException e) {
			e.printStackTrace();
		}

		// write data to data folder
		File file = new File(dir, "wikipedia.org.sed");
		try (DataOutputStream out = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream(file)));) {
			out.writeInt(1);
			out.writeInt(0x07);
			mm = new MouseActionInputDataImp();
			out.writeInt(mm.size());
			for (Iterator<MouseActionInputData.Point> it = mm.iterate(); it
					.hasNext();) {
				MouseActionInputData.Point p = it.next();
				out.writeDouble(p.browserLocation.getX());
				out.writeDouble(p.browserLocation.getY());
				out.writeDouble(p.pagePosition.getX());
				out.writeDouble(p.pagePosition.getY());
				out.writeLong(p.timestamp);
				out.writeInt(p.type);
			}
			mg = new GraphOutputDataImp(mm.iterate());
			out.writeInt(mg.order() + mg.size());
			for (int i = 0; i < mg.order() + mg.size(); i++) {
				MouseGraphAction ma = mg.getAction(i);
				GODFactory.write(ma, out);
				out.writeInt(mg.indexOf(ma.getPrevious()));
			}
			out.writeInt(1);
			mt = UIEfficiencyStatistics.getType("NodesPerMinute");
			ms = mt.calculate(mg);
			out.writeUTF(mt.getName());
			ms.write(out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(file.length());
	}

	/**
	 * Creates SEDs to use for testing and copies wikipedia data file to SED's
	 * datafile dir.
	 * 
	 * @throws IOException
	 *             if copy fails
	 */
	@Before
	public void before() throws IOException {

		// create fgcu
		fgcu = SiteEfficiencyData.getForDomain("fgcu.edu");

		// create some MAID instances
		for (int i = 0; i < 5; i++) {
			DataSet ds = new DataSet();
			ds.mouseData = MAIDFactory.newInstance();
			fgcu.data.add(ds);
		}

		// file for wiki's prewritten data file
		File wIn = new File(dir, "wikipedia.org.sed");

		// file where wiki's data file should reside
		File wOut = new File(SiteEfficiencyData.dataFileDir,
				"wikipedia.org.sed");
		// System.out.println(wOut.getAbsolutePath());

		// copy data file
		Files.copy(wIn.toPath(), wOut.toPath(),
				StandardCopyOption.REPLACE_EXISTING);
		// System.out.println(wOut.exists());

		// create wiki
		wiki = SiteEfficiencyData.getForDomain("wikipedia.org");

	}

	/**
	 * Deletes wikipedia data file in SED's datafile dir.
	 */
	@After
	public void after() {

		// file for wiki's data file
		File wDat = new File(SiteEfficiencyData.dataFileDir,
				"wikipedia.org.sed");

		// delete file if it exists
		if (wDat.exists())
			wDat.delete();

		// remove all domains
		SiteEfficiencyData.clean();

	}

	/**
	 * Delete the test data file
	 */
	@AfterClass
	public static void tearDown() {
		File file = new File(dir, "wikipedia.org.sed");
		file.delete();
	}

	/**
	 * Calls SiteEfficiencyData.getForDomain with a domain for which a SED does
	 * not yet exist.
	 */
	@Test
	public void testGetForDomainNotExists() {

		// create SED for google
		SiteEfficiencyData sed = SiteEfficiencyData
				.getForDomain("www.google.com");

		// check for existence
		assertNotNull("getForDomain() must return a non-null value", sed);

		// check domain value
		assertEquals("getForDomain( d ).getDomain() must equal d",
				"www.google.com", sed.getDomain());

	}

	/**
	 * Calls SiteEfficiencyData.getForDomain with a domain for which a SED does
	 * exist but the data file does not.
	 */
	@Test
	public void testGetForDomainExistsNoFile() {

		// create SED for fgcu
		SiteEfficiencyData sed = SiteEfficiencyData.getForDomain("fgcu.edu");

		// check for existence
		assertNotNull("getForDomain() must return a non-null value", sed);

		// check domain value
		assertEquals("getForDomain(\"fgcu.edu\") must return fgcu", sed, fgcu);

		// make sure sed == fgcu
		assertTrue("getForDomain(\"fgcu.edu\").isLoaded() must be true",
				sed.isLoaded());

	}

	/**
	 * Calls SiteEfficiencyData.getForDomain with a domain for which a SED does
	 * exist and the data file exists.
	 */
	@Test
	public void testGetForDomainExistsWithFile() {

		// make sure wiki, created in before, exists
		assertNotNull("getForDomain() must return a non-null value", wiki);

		// make sure wiki was not loaded
		assertFalse("getForDomain(\"wikipedia.org\").isLoaded() must be false",
				wiki.isLoaded());

	}

	/**
	 * Attempts to load an already loaded site.
	 */
	@Test
	public void testLoadDataFileLoaded() {

		// attempt to load fgcu
		boolean success = fgcu.loadData();

		// make sure call failed
		assertFalse("fgcu.loadData() must return false", success);

	}

	/**
	 * Loads site data from a prewritten file.
	 */
	@Test
	public void testLoadDataFileExists() {

		// load wiki
		boolean success = wiki.loadData();

		// make sure wiki is loaded
		assertTrue("wiki.loadData() must return true", success);
		assertTrue("wiki.isLoaded() must be true", wiki.isLoaded());

		// test if data is correct
		assertEquals("wiki.size() must return 1", wiki.size(), 1);

		DataSet ds = wiki.getSet(0);
		assertNotNull("mouse data should not be null", ds.mouseData);
		assertEquals(ds.mouseData, mm);
		assertNotNull("graph data should not be null", ds.graphData);
		assertEquals(ds.graphData.order(), mg.order());
		assertEquals(ds.graphData.size(), mg.size());
		assertEquals(ds.graphData, mg);
		assertNotNull("statistics should not be null", ds.statistics);
		assertEquals("statistics should have 1 statistic",
				ds.statistics.size(), 1);
		assertTrue(ds.statistics.containsKey(mt.getName()));
		assertEquals(ds.statistics.get(mt.getName()), ms);

	}

	/**
	 * Attempts to load site data from a nonexistent file.
	 */
	@Test
	public void testLoadDataFileNotExists() {

		// file for wiki's data file
		File wDat = new File(SiteEfficiencyData.dataFileDir,
				"wikipedia.org.sed");

		// delete data file
		wDat.delete();

		// attempt to load wiki
		boolean success = wiki.loadData();

		// make sure call failed
		assertFalse("fgcu.loadData() must return false", success);

	}

	/**
	 * Attempts to unload wiki before it is loaded.
	 */
	@Test
	public void testUnloadDataNotLoaded() {

		// file for wiki's data file
		File wDat = new File(SiteEfficiencyData.dataFileDir,
				"wikipedia.org.sed");

		// delete data file
		wDat.delete();

		// attempt to unload wiki
		boolean success = wiki.unloadData();

		// verify success
		assertFalse("wiki.unloadData() must return false", success);
		assertFalse("wiki.isLoaded() must be false", wiki.isLoaded());

		// test for data file's existence
		assertFalse("wiki's data file must not exist", wDat.exists());

	}

	/**
	 * Unloads fgcu after it is loaded.
	 */
	@Test
	public void testUnloadDataLoaded() {

		assertTrue(wiki.loadData());

		// file for wiki's data file
		File wDat = new File(SiteEfficiencyData.dataFileDir,
				"wikipedia.org.sed");

		// delete data file
		wDat.delete();

		// attempt to unload wiki
		boolean success = wiki.unloadData();

		// verify success
		assertTrue("wiki.unloadData() must return true", success);
		assertFalse("wiki.isLoaded() must be false", wiki.isLoaded());

		// test for data file's existence
		assertTrue("wiki's data file must exist", wDat.exists());

		// verify data file's content
		File exp = new File(dir, "wikipedia.org.sed");
		assertEquals(exp.length(), wDat.length());
		try (BufferedInputStream inE = new BufferedInputStream(
				new FileInputStream(exp));
				BufferedInputStream inA = new BufferedInputStream(
						new FileInputStream(wDat));) {
			byte[] e = new byte[1024], a = new byte[1024];
			while (inE.read(e) > 0) {
				inA.read(a);
				assertTrue(Arrays.equals(e, a));
			}
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Queries wiki.isLoaded before it is loaded
	 */
	@Test
	public void testIsLoadedNotLoaded() {

		// check if wiki is loaded
		assertFalse("wiki.isLoaded() must return false", wiki.isLoaded());

	}

	/**
	 * Queries wiki.isLoaded after it is loaded and fgcu.isLoaded
	 */
	@Test
	public void testIsLoadedLoaded() {

		// check if fgcu is loaded
		assertTrue("fgcu.isLoaded() must return true", fgcu.isLoaded());

		// load wiki
		wiki.loadData();

		// check if wiki is loaded
		assertTrue("wiki.isLoaded() must return true", wiki.isLoaded());

	}

	/**
	 * Calls getDomain on wiki and fgcu
	 */
	public void getDomain() {

		// check fgcu's domain
		assertEquals("fgcu.getDomain() must return fgcu", fgcu.getDomain(),
				"fgcu.edu");

		// check wiji's domain
		assertEquals("wiki.getDomain() must return wiki", wiki.getDomain(),
				"wikipedia.org");

	}

	/**
	 * Calls newMouseData on fgcu( already loaded ).
	 */
	@Test
	public void testNewMouseDataLoaded() {

		// record size of fgcu.data
		int size = fgcu.data.size();

		// create MAID
		MouseActionInputData maid = fgcu.newMouseData();

		// make sure it is non-null
		assertNotNull("fgcu.newMouseData() must not return null", maid);

		// make sure fgcu.data is 1 larger than size
		assertEquals("fgcu.data.size() must have incremented by one", size + 1,
				fgcu.data.size());

		// make sure last DataSet contains maid
		assertEquals("", fgcu.data.get(size).mouseData, maid);

	}

	/**
	 * Calls compileMouseData on fgcu( already loaded ).
	 */
	@Test
	public void testCompileMouseDataLoaded() {

		// compile mouse data
		fgcu.compileMouseData();

		// iterate through fgcu.data
		for (Iterator<DataSet> it = fgcu.data.iterator(); it.hasNext();) {
			DataSet d = it.next();
			if (d.mouseData != null)
				// check for existence of graph data
				assertNotNull(
						"graph data for non-null mouse data must also be non-null",
						d.graphData);
		}

	}

	/**
	 * Calls calculateStatistics on fgcu( already loaded ).
	 */
	@Test
	public void testCalculateStatisticsLoaded() {

		// calculate statistics
		fgcu.calculateStatistics();

		// iterate through fgcu.data
		for (Iterator<DataSet> it = fgcu.data.iterator(); it.hasNext();) {
			DataSet d = it.next();
			if (d.graphData != null)
				// check for existence of graph data
				assertNotNull(
						"statistics for non-null graph data must also be non-null",
						d.statistics);
		}

	}

	/**
	 * Calls newMouseData on wiki( not loaded ).
	 */
	@Test
	public void testNewMouseDataNotLoaded() {

		assertNull("wiki.newMouseData must return null", wiki.newMouseData());

	}

	/**
	 * Calls compileMouseData on wiki( not loaded ).
	 */
	@Test( expected = RuntimeException.class )
	public void testCompileMouseDataNotLoaded() {

		wiki.compileMouseData();

	}

	/**
	 * Calls calculateStatistics on wiki( not loaded ).
	 */
	@Test( expected = RuntimeException.class )
	public void testCalculateStatisticsNotLoaded() {

		wiki.calculateStatistics();

	}

}