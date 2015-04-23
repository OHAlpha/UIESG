package edu.fgcu.stesting.uiesg.data;

import org.junit.BeforeClass;

// TODO: javadoc
@SuppressWarnings( "javadoc" )
public class GraphOutputDataUnitTest extends GraphOutputDataMockTest {

	/**
	 * Initializes SiteEfficiencyData.
	 */
	@BeforeClass
	public static void setup() {
		setup(MAIDFactory.MOCK, GODFactory.IMPLEMENTATION, GODFactory.MOCK);
	}

}