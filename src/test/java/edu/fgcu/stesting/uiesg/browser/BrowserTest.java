package edu.fgcu.stesting.uiesg.browser;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.*;

import edu.fgcu.stesting.uiesg.data.SiteEfficiencyData;

/***
 * 
 * Performs unit tests for the browser class
 * 
 * 
 * @author beoliver
 *
 */
@SuppressWarnings( "javadoc" )
public class BrowserTest {

	Browser browser;
	

	public BrowserTest() {

	}

	@BeforeClass
	public static void setup() {

	}

	@Before
	public void before() {
		browser = new Browser();

	}

	@After
	public void after() {

	}

	@AfterClass
	public static void teardown() {

	}

	// unit tests for the browser

	@Test
	public void testStart() {
		// code to test browser start function
		throw new RuntimeException("test not implemented");

	}

	@Test
	public void testUpdatePage() {
		// code to test updatePage returns boolean btw
		// test if (sed == null || !sed.getDomain().equalsIgnoreCase(domain))

		browser.sed = null;
		boolean tst = true;
		URL url;
		try {
			url = new URL("https://www.google.com");

			boolean bool = browser.updatePage(url);
			assertEquals(tst, bool);
			assertEquals(url.getHost(), browser.sed.getDomain());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

} // end BrowserTest class
