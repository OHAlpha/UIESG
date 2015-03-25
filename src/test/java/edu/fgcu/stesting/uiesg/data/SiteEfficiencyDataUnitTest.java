package edu.fgcu.stesting.uiesg.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.fgcu.stesting.uiesg.data.MouseActionInputData.MAIDFactory;
import edu.fgcu.stesting.uiesg.data.SiteEfficiencyData.DataSet;
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
		MAIDFactory.init( MAIDFactory.IMPLEMENTATION);
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
		for( int i = 0; i < 5; i++ ) {
			DataSet ds = new DataSet();
			ds.mouseData = MAIDFactory.newInstance();
			fgcu.data.add(ds);
		}
		
		// file for wiki's prewritten data file
		File wIn = new File(dir, "wikipedia.org.sed");
		
		// file where wiki's data file should reside
		File wOut = new File(SiteEfficiencyData.dataFileDir,
				"wikipedia.org.sed");
		
		// copy data file
		Files.copy(wIn.toPath(), wOut.toPath(),
				StandardCopyOption.REPLACE_EXISTING);
		
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
		// TODO: testLoadDataFileExists
		
		// load wiki
		boolean success = wiki.loadData();
		
		// make sure wiki is loaded
		assertTrue("wiki.loadData() must return true", success);
		assertTrue("wiki.isLoaded() must be true", wiki.isLoaded());
		
		// test if data is correct
		// TODO: testLoadDataFileExists: test if wiki loaded correctly
		
		throw new RuntimeException("test not implemented");
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
		
		//  file for wiki's data file
		File wDat = new File(SiteEfficiencyData.dataFileDir,
				"wikipedia.org.sed");
		
		// delete data file
		wDat.delete();
		
		// attempt to unload wiki
		boolean success = wiki.unloadData();
		
		// verify success
		assertFalse("wiki.unloadData() must return false",success);
		assertFalse("wiki.isLoaded() must be false", wiki.isLoaded());
		
		// test for data file's existence
		assertFalse("wiki's data file must not exist",wDat.exists());
		
	}

	/**
	 * Unloads fgcu after it is loaded.
	 */
	@Test
	public void testUnloadDataLoaded() {
		// TODO: testUnloadDataLoaded
		
		//  file for wiki's data file
		File wDat = new File(SiteEfficiencyData.dataFileDir,
				"wikipedia.org.sed");
		
		// delete data file
		wDat.delete();
		
		// attempt to unload wiki
		boolean success = wiki.unloadData();
		
		// verify success
		assertTrue("wiki.unloadData() must return true",success);
		assertFalse("wiki.isLoaded() must be false", wiki.isLoaded());
		
		// test for data file's existence
		assertTrue("wiki's data file must exist",wDat.exists());
		
		// verify data file's content
		// TODO: testUnloadDataNotLoaded: verify data file's content
		
		throw new RuntimeException("test not implemented");
	}

	/**
	 * Queries wiki.isLoaded before it is loaded
	 */
	@Test
	public void testIsLoadedNotLoaded() {
		
		// check if wiki is loaded
		assertFalse("wiki.isLoaded() must return false",wiki.isLoaded());
		
	}

	/**
	 * Queries wiki.isLoaded after it is loaded and fgcu.isLoaded
	 */
	@Test
	public void testIsLoadedLoaded() {

		// check if fgcu is loaded
		assertTrue("fgcu.isLoaded() must return true",fgcu.isLoaded());
		
		// load wiki
		wiki.loadData();

		// check if wiki is loaded
		assertFalse("wiki.isLoaded() must return false",wiki.isLoaded());
		
	}

	/**
	 * Calls getDomain on wiki and fgcu
	 */
	public void getDomain() {
		
		// check fgcu's domain
		assertEquals("fgcu.getDomain() must return fgcu",fgcu.getDomain(),"fgcu.edu");
		
		// check wiji's domain
		assertEquals("wiki.getDomain() must return wiki",wiki.getDomain(),"wikipedia.org");
		
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
		assertNotNull("fgcu.newMouseData() must not return null",maid);
		
		// make sure fgcu.data is 1 larger than size
		assertEquals("fgcu.data.size() must have incremented by one",size+1,fgcu.data.size());
		
		// make sure last DataSet contains maid
		assertEquals("",((List<DataSet>)fgcu.data).get(size).mouseData,maid);
		
	}

	/**
	 * Calls compileMouseData on fgcu( already loaded ).
	 */
	@Test
	public void testCompileMouseDataLoaded() {
		// TODO: testCompileMouseDataLoaded
		
		// compile mouse data
		fgcu.compileMouseData();
		
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