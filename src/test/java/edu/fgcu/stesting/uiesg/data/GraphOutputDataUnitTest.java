package edu.fgcu.stesting.uiesg.data;

import static org.junit.Assert.assertNotEquals;

import org.junit.BeforeClass;
import org.junit.Test;

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

	@Test
	public void testEquals() {
		if (!godB.equals(godE))
			godB.assertEquals(godE, true);
	}

	@Test
	public void testNotEquals() {
		assertNotEquals(godB + " should not be 5", godB, 5);
		assertNotEquals(godB + " should not be " + godC, godB, godC);
		assertNotEquals(godB + " should not be " + godD, godB, godD);
	}

	@Test( expected = AssertionError.class )
	public void testEqualsC() {
		godB.assertEquals(godC,true);
	}

	@Test( expected = AssertionError.class )
	public void testEqualsD() {
		godB.assertEquals(godD,true);
	}

}