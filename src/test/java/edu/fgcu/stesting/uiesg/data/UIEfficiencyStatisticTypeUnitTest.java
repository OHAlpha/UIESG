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
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatisticType.UIEfficiencyStatistics;
import edu.fgcu.stesting.uiesg.data.statistic.NodesPerMinute;

@SuppressWarnings( "javadoc" )
@RunWith( value = Parameterized.class )
public class UIEfficiencyStatisticTypeUnitTest {

	@Parameters
	public static Collection<Object[]> dataParameters() {
		try {
			List<Object[]> params = new LinkedList<>();
			params.add(new Object[] { new NodesPerMinute() });
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
		assertEquals("description should not be null",name,type.getName());
	}
	
	@Test
	public void testGetDescription() {
		assertNotNull("description should not be null",type.getDescription());
	}
	
	@Test
	public void testRegister() {
		assertTrue("description should not be null",type.register());
		assertFalse("description should not be null",type.register());
	}

}
