package edu.fgcu.stesting.uiesg.data;

import org.junit.BeforeClass;

/**
 * Integration tests for the GOD class using implementations of the
 * MouseGraphAction interface.
 * 
 * @author oalpha
 *
 */
// TODO: javadoc
public class GraphOutputDataIntegrationTest extends GraphOutputDataUnitTest {

	/**
	 * Initializes SiteEfficiencyData.
	 */
	@BeforeClass
	public static void setup() {
		setup(MAIDFactory.IMPLEMENTATION, GODFactory.IMPLEMENTATION,
				GODFactory.IMPLEMENTATION);
	}

}