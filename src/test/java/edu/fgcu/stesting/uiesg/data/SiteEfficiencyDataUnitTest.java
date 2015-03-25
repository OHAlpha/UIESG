package edu.fgcu.stesting.uiesg.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Performs unit tests for class, SiteEfficiencyData.
 * 
 * @author oalpha
 *
 */
public class SiteEfficiencyDataUnitTest {

	/**
	 * The directory where prewritten data files reside.
	 */
	static File dir = new File(
			"src/test/java/edu/fgcu/stesting/uiesg/data/datafiles");

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
		SiteEfficiencyData.init("tmp/datafiles");
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
		fgcu = SiteEfficiencyData.getForDomain("fgcu.edu");
		File wIn = new File(dir, "wikipedia.org.sed");
		File wOut = new File(SiteEfficiencyData.dataFileDir,
				"wikipedia.org.sed");
		Files.copy(wIn.toPath(), wOut.toPath(),
				StandardCopyOption.REPLACE_EXISTING);
		wiki = SiteEfficiencyData.getForDomain("wikipedia.org");
	}

	/**
	 * Deletes wikipedia data file in SED's datafile dir.
	 */
	@After
	public void after() {
		File wDat = new File(SiteEfficiencyData.dataFileDir,
				"wikipedia.org.sed");
		if (wDat.exists())
			wDat.delete();
	}

	/**
	 * Calls SiteEfficiencyData.getForDomain with a domain for which a SED does
	 * not yet exist.
	 */
	@Test
	public void testgetForDomainNotExists() {
		SiteEfficiencyData sed = SiteEfficiencyData
				.getForDomain("www.google.com");
		assertNotNull("getForDomain() must return a non-null value", sed);
		assertEquals("getForDomain( d ).getDomain() must equal d",
				"www.google.com", sed.getDomain());
	}

	/**
	 * Calls SiteEfficiencyData.getForDomain with a domain for which a SED does
	 * exist but the data file does not.
	 */
	@Test
	public void testgetForDomainExistsNoFile() {
		SiteEfficiencyData sed = SiteEfficiencyData.getForDomain("fgcu.edu");
		assertNotNull("getForDomain() must return a non-null value", sed);
		assertEquals("getForDomain(\"fgcu.edu\") must return fgcu", sed, fgcu);
		assertTrue("getForDomain(\"fgcu.edu\").isLoaded() must be true",
				sed.isLoaded());
	}

	/**
	 * Calls SiteEfficiencyData.getForDomain with a domain for which a SED does
	 * exist and the data file exists.
	 */
	@Test
	public void testgetForDomainExistsWithFile() {
		assertNotNull("getForDomain() must return a non-null value", wiki);
		assertFalse("getForDomain(\"wikipedia.org\").isLoaded() must be false",
				wiki.isLoaded());
	}

	/**
	 * Attempts to load an already loaded site.
	 */
	@Test
	public void testLoadDataFileLoaded() {
		boolean success = fgcu.loadData();
		assertFalse("fgcu.loadData() must return false", success);
	}

	/**
	 * Loads site data from a prewritten file.
	 */
	@Test
	public void testLoadDataFileExists() {
		// TODO: load data
		boolean success = wiki.loadData();
		assertTrue("wiki.loadData() must return true", success);
		// test if data is correct
		throw new RuntimeException("test not implemented");
	}

	/**
	 * Attempts to load site data from a nonexistent file.
	 */
	@Test
	public void testLoadDataFileNotExists() {
		// TODO: load data file not exists
		File wDat = new File(SiteEfficiencyData.dataFileDir,
				"wikipedia.org.sed");
		wDat.delete();
		boolean success = wiki.loadData();
		assertFalse("fgcu.loadData() must return false", success);
		throw new RuntimeException("test not implemented");
	}

	/**
	 * Attempts to unload wiki before it is loaded.
	 */
	@Test
	public void testUnloadDataNotLoaded() {
		// TODO: unload data
		throw new RuntimeException("test not implemented");
	}

	/**
	 * Unloads fgcu after it is loaded.
	 */
	@Test
	public void testUnloadDataLoaded() {
		// TODO: unload data
		throw new RuntimeException("test not implemented");
	}

	/**
	 * Queries wiki.isLoaded before it is loaded
	 */
	@Test
	public void testIsLoadedNotLoaded() {
		// TODO: is loaded
		throw new RuntimeException("test not implemented");
	}

	/**
	 * Queries wiki.isLoaded after it is loaded and fgcu.isLoaded
	 */
	@Test
	public void testIsLoadedLoaded() {
		// TODO: is loaded
		throw new RuntimeException("test not implemented");
	}

	/**
	 * Calls getDomainon wiki and fgcu
	 */
	public void getDomain() {
		throw new RuntimeException("test not implemented");
	}

	/**
	 * Calls newMouseData on fgcu( already loaded ).
	 */
	@Test
	public void testNewMouseDataLoaded() {
		// TODO: new mouse data
		throw new RuntimeException("test not implemented");
	}

	/**
	 * Calls compileMouseData on fgcu( already loaded ).
	 */
	@Test
	public void testCompileMouseDataLoaded() {
		// TODO: compile mouse data
		throw new RuntimeException("test not implemented");
	}

	/**
	 * Calls calculateStatistics on fgcu( already loaded ).
	 */
	@Test
	public void testCalculateStatisticsLoaded() {
		// TODO: calculate statistics
		throw new RuntimeException("test not implemented");
	}

	/**
	 * Calls newMouseData on wiki( not loaded ).
	 */
	@Test
	public void testNewMouseDataNotLoaded() {
		// TODO: new mouse data
		throw new RuntimeException("test not implemented");
	}

	/**
	 * Calls compileMouseData on wiki( not loaded ).
	 */
	@Test
	public void testCompileMouseDataNotLoaded() {
		// TODO: compile mouse data
		throw new RuntimeException("test not implemented");
	}

	/**
	 * Calls calculateStatistics on wiki( not loaded ).
	 */
	@Test
	public void testCalculateStatisticsNotLoaded() {
		// TODO: calculate statistics
		throw new RuntimeException("test not implemented");
	}

	/**
	 * Requests a page that does not exist.
	 */
	public void testGetForURLPageNotExists() {
		// TODO: get for url
		throw new RuntimeException("test not implemented");
	}

	/**
	 * Requests a page that exists.
	 */
	public void testGetForURLPageExists() {
		// TODO: get for url
		throw new RuntimeException("test not implemented");
	}

}