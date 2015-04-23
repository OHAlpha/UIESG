package edu.fgcu.stesting.uiesg;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import edu.fgcu.stesting.uiesg.data.GraphOutputDataMockTest;
import edu.fgcu.stesting.uiesg.data.GraphOutputDataUnitTest;
import edu.fgcu.stesting.uiesg.data.MouseActionInputDataMockTest;
import edu.fgcu.stesting.uiesg.data.MouseActionInputDataUnitTest;
import edu.fgcu.stesting.uiesg.data.MouseGraphActionUnitTest;
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
		MouseActionInputDataMockTest.class,
		GraphOutputDataUnitTest.class,
		MouseActionInputDataUnitTest.class,
		MouseGraphActionUnitTest.class,
		SiteEfficiencyDataUnitTest.class,
		UIEfficiencyStatisticsUnitTest.class,
		UIEfficiencyStatisticTypeUnitTest.class, } )
public class UnitTest {

}
