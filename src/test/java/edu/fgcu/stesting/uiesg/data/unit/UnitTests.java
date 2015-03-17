package edu.fgcu.stesting.uiesg.data.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith (value=org.junit.runners.Suite.class)
@Suite.SuiteClasses(value={
 		SiteDataSetTest.class,
 		MouseActionInputDataTest.class,
 		GraphOutputDataTest.class,
 		UIEfficiencyStatisticTest.class,
 	}
)
public class UnitTests {

}
