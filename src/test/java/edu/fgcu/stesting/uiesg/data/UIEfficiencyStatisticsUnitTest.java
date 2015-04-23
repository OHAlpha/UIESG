/**
 * 
 */
package edu.fgcu.stesting.uiesg.data;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatisticType.DuplicateTypeException;
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatisticType.UIEfficiencyStatistics;
import edu.fgcu.stesting.uiesg.data.mock.UIEfficiencyStatisticTypeMock;

/**
 * A test for implementations of UIEfficiencyStatisticType.
 * 
 * @author oalpha
 *
 */
@SuppressWarnings( "javadoc" )
public class UIEfficiencyStatisticsUnitTest {

	@BeforeClass
	public static void setup() {
		MAIDFactory.init(MAIDFactory.MOCK);
		GODFactory.init(GODFactory.MOCK);
		new UIEfficiencyStatisticTypeMock("one").register();
		new UIEfficiencyStatisticTypeMock("two").register();
	}

	@Test
	public void testAddType() throws DuplicateTypeException {
		UIEfficiencyStatisticTypeMock t = new UIEfficiencyStatisticTypeMock("three");
		UIEfficiencyStatistics.addType(t);
		assertEquals(t,UIEfficiencyStatisticType.getByName("three"));
	}

	@Test( expected = DuplicateTypeException.class )
	public void testAddDuplicateType() throws DuplicateTypeException {
		UIEfficiencyStatisticTypeMock t = new UIEfficiencyStatisticTypeMock("two");
		UIEfficiencyStatistics.addType(t);
	}

	@Test
	public void testGetType() {
		assertNotNull(UIEfficiencyStatistics.getType("one"));
	}

	@Test
	public void testCalculateStatistics() {
	}

}