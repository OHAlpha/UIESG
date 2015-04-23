package edu.fgcu.stesting.uiesg.data.mock;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import edu.fgcu.stesting.uiesg.data.GraphOutputData;
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatistic;
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatisticType;

@SuppressWarnings( "javadoc" )
public class UIEfficiencyStatisticTypeMock extends UIEfficiencyStatisticType {

	@Override
	public String getDescription() {
		return "A mock statistic type";
	}

	@Override
	public UIEfficiencyStatistic calculate( GraphOutputData graph ) {
		return createStatistic("mock statistic");
	}

	@Override
	public UIEfficiencyStatistic create( DataInputStream in ) throws IOException {
		in.read();
		return createStatistic("mock statistic");
	}

	@Override
	public Class<?> getValueType() {
		return String.class;
	}

	@Override
	public void write( UIEfficiencyStatistic statistic, DataOutputStream out ) throws IOException {
		out.write(53);
	}

}
