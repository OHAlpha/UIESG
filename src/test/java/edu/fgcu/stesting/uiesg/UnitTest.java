package edu.fgcu.stesting.uiesg;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import edu.fgcu.stesting.uiesg.data.GraphOutputDataUnitTest;
import edu.fgcu.stesting.uiesg.data.MouseActionInputDataUnitTest;
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
@Suite.SuiteClasses( value = { GraphOutputDataUnitTest.class,
		MouseActionInputDataUnitTest.class,
		SiteEfficiencyDataUnitTest.class,
		UIEfficiencyStatisticsUnitTest.class,
		UIEfficiencyStatisticTypeUnitTest.class, } )
public class UnitTest {

}
