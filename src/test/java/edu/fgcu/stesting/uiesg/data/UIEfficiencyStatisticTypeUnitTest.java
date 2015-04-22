package edu.fgcu.stesting.uiesg.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatisticType.DuplicateTypeException;
import edu.fgcu.stesting.uiesg.data.statistic.NodesPerMinute;

@SuppressWarnings( "javadoc" )
@RunWith( value = Parameterized.class )
public class UIEfficiencyStatisticTypeUnitTest {
	
	static GraphOutputData god;

	@Parameters
	public static Collection<Object[]> dataParameters() {
		god = GODFactory.newInstance(null);
		try {
			List<Object[]> params = new LinkedList<>();
			params.add(new Object[] { "NodesPerMinute", new NodesPerMinute() });
			return params;
		} catch (DuplicateTypeException e) {
			return null;
		}
	}
	
	String name;
	
	UIEfficiencyStatisticType type;

	public UIEfficiencyStatisticTypeUnitTest( String name, UIEfficiencyStatisticType type ) {
		this.type = type;
		this.name = name;
	}
	
	@Test
	public void testGetName() {
		assertEquals("name should be " + name,name,type.getName());
	}
	
	@Test
	public void testGetDescription() {
		assertNotNull("description should not be null",type.getDescription());
	}
	
	@Test
	public void testRegister() {
		assertTrue("register should succeed",type.register());
		assertFalse("register should fail",type.register());
	}
	
	@Test
	public void testCalculate() {
		UIEfficiencyStatistic s = type.calculate(god);
		assertNotNull("description should not be null",s);
		assertEquals("type should equal stat.type",type, s.getType());
	}

}
