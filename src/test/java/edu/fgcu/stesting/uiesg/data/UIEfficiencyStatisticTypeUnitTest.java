package edu.fgcu.stesting.uiesg.data;

import static org.junit.Assert.assertNotNull;

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
			new NodesPerMinute().register();
			List<Object[]> params = new LinkedList<>();
			for (String t : UIEfficiencyStatistics.types.keySet())
				params.add(new Object[] { UIEfficiencyStatistics.getType(t) });
			return params;
		} catch (DuplicateTypeException e) {
			return null;
		}
	}
	
	UIEfficiencyStatisticType type;

	public UIEfficiencyStatisticTypeUnitTest( UIEfficiencyStatisticType type ) {
		this.type = type;
	}
	
	@Test
	public void testGetDescription() {
		assertNotNull("description should not be null",type.getDescription());
	}

}
