/**
 * 
 */
package edu.fgcu.stesting.uiesg.data;

import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatisticType.DuplicateTypeException;
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatisticType.UIEfficiencyStatistics;

/**
 * A test for implementations of UIEfficiencyStatisticType.
 * 
 * @author oalpha
 *
 */
@SuppressWarnings( "javadoc" )
public class UIEfficiencyStatisticsUnitTest {

	private static class FakeType extends UIEfficiencyStatisticType {

		String name;

		FakeType( String name ) {
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getDescription() {
			return "";
		}

		@Override
		public UIEfficiencyStatistic calculate( GraphOutputData graph ) {
			return this.createStatistic(0);
		}

		@Override
		public UIEfficiencyStatistic create( InputStream in ) {
			return this.createStatistic(0);
		}

		@Override
		public Class<?> getValueType() {
			return Integer.class;
		}

		@Override
		public void write( UIEfficiencyStatistic statistic, OutputStream out ) {}

	}

	@BeforeClass
	public static void setup() {
		MAIDFactory.init(MAIDFactory.MOCK);
		GODFactory.init(GODFactory.MOCK);
		new FakeType("one").register();
		new FakeType("two").register();
	}

	@Test
	public void testAddType() throws DuplicateTypeException {
		FakeType t = new FakeType("three");
		UIEfficiencyStatistics.addType(t);
		assertEquals(t,UIEfficiencyStatisticType.getByName("three"));
	}

	@Test( expected = DuplicateTypeException.class )
	public void testAddDuplicateType() throws DuplicateTypeException {
		FakeType t = new FakeType("two");
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