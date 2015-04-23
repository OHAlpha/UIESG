package edu.fgcu.stesting.uiesg.data;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import edu.fgcu.stesting.uiesg.data.statistic.DirectionVariance;
import edu.fgcu.stesting.uiesg.data.statistic.MouseArea;
import edu.fgcu.stesting.uiesg.data.statistic.NodesPerMinute;
import edu.fgcu.stesting.uiesg.data.statistic.PositionVariance;

/**
 * Integration tests for the SED class using implementations of MAID, GOD, UIEST
 * and PageContext.
 * 
 * @author oalpha
 *
 */
public class SiteEfficiencyDataIntegrationTest extends SiteEfficiencyDataUnitTest {

	/**
	 * Initializes SiteEfficiencyData.
	 */
	@BeforeClass
	public static void setup() {
		if (!dir.exists())
			dir.mkdirs();
		SiteEfficiencyData.init("tmp/datafiles");
		new NodesPerMinute().register();
		new MouseArea().register();
		new PositionVariance().register();
		new DirectionVariance().register();
		setup(MAIDFactory.IMPLEMENTATION,GODFactory.IMPLEMENTATION);
	}

	/**
	 * Delete the test data file
	 */
	@AfterClass
	public static void tearDown() {
		File file = new File(dir, "wikipedia.org.sed");
		file.delete();
	}

}