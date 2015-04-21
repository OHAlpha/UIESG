package edu.fgcu.stesting.uiesg;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import edu.fgcu.stesting.uiesg.data.GraphOutputDataUnitTest;
import edu.fgcu.stesting.uiesg.data.MouseActionInputDataUnitTest;
import edu.fgcu.stesting.uiesg.data.SiteEfficiencyDataUnitTest;

/**
 * A JUnit test suite that runs all the unit tests.
 * 
 * @author oalpha
 *
 */
@RunWith( value = org.junit.runners.Suite.class )
@Suite.SuiteClasses( value = { GraphOutputDataUnitTest.class,
		MouseActionInputDataUnitTest.class,
		// PageContextUnitTest.class,
		SiteEfficiencyDataUnitTest.class, } )
public class UnitTest {

}
