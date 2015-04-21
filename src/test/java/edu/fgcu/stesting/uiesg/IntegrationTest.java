package edu.fgcu.stesting.uiesg;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import edu.fgcu.stesting.uiesg.data.GraphOutputDataIntegrationTest;
import edu.fgcu.stesting.uiesg.data.SiteEfficiencyDataIntegrationTest;

/**
 * A jUnit test suite that runs all the Integration tests.
 * 
 * @author oalpha
 *
 */
@RunWith( value = org.junit.runners.Suite.class )
@Suite.SuiteClasses( value = { GraphOutputDataIntegrationTest.class,
		SiteEfficiencyDataIntegrationTest.class, } )
public class IntegrationTest {

}
