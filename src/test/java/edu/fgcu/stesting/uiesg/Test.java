package edu.fgcu.stesting.uiesg;

import static org.junit.Assert.assertTrue;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import edu.fgcu.stesting.uiesg.data.GraphOutputDataIntegrationTest;
import edu.fgcu.stesting.uiesg.data.GraphOutputDataMockTest;
import edu.fgcu.stesting.uiesg.data.GraphOutputDataUnitTest;
import edu.fgcu.stesting.uiesg.data.MouseActionInputDataMockTest;
import edu.fgcu.stesting.uiesg.data.MouseActionInputDataUnitTest;
import edu.fgcu.stesting.uiesg.data.MouseGraphEdgeUnitTest;
import edu.fgcu.stesting.uiesg.data.MouseGraphNodeUnitTest;
import edu.fgcu.stesting.uiesg.data.SiteEfficiencyDataIntegrationTest;
import edu.fgcu.stesting.uiesg.data.SiteEfficiencyDataUnitTest;
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatisticTypeUnitTest;
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatisticsUnitTest;

/**
 * A JUnit test suite that runs all the unit tests.
 * 
 * @author oalpha
 *
 */
@RunWith( value = org.junit.runners.Suite.class )
@Suite.SuiteClasses( value = { GraphOutputDataMockTest.class,
		MouseActionInputDataMockTest.class, GraphOutputDataUnitTest.class,
		MouseActionInputDataUnitTest.class, MouseGraphNodeUnitTest.class,
		MouseGraphEdgeUnitTest.class, SiteEfficiencyDataUnitTest.class,
		UIEfficiencyStatisticsUnitTest.class,
		UIEfficiencyStatisticTypeUnitTest.class,
		GraphOutputDataIntegrationTest.class,
		SiteEfficiencyDataIntegrationTest.class, } )
public class Test {

	/**
	 * A fake test to keep jacoco from adding an extra 0% class to the coverage
	 * report.
	 */
	@org.junit.Test
	public void testing() {
		assertTrue(true);
	}

}
