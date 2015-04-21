package edu.fgcu.stesting.uiesg;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * A JUnit test suite that runs all the unit tests.
 * 
 * @author oalpha
 *
 */
@RunWith( value = org.junit.runners.Suite.class )
@Suite.SuiteClasses( value = { UnitTest.class, IntegrationTest.class, } )
public class Test {

}
