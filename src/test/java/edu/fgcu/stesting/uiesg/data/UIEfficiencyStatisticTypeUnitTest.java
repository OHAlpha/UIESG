/**
 * 
 */
package edu.fgcu.stesting.uiesg.data;

import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * A test for implementations of UIEfficiencyStatisticType.
 * 
 * @author oalpha
 *
 */
@SuppressWarnings( "javadoc" )
public class UIEfficiencyStatisticTypeUnitTest {

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
	public void testRegister() {
		FakeType t = new FakeType("three");
		assertTrue( t.register() );
		assertEquals(t,UIEfficiencyStatisticType.getByName("three"));
	}

	@Test
	public void testRegisterDuplicate() {
		FakeType t = new FakeType("two");
		assertFalse( t.register() );
	}

	@Test
	public void testGetByName() {
		assertNotNull(UIEfficiencyStatisticType.getByName("one"));
	}

	@Test
	public void testCalculateStatistics() {
	}

}