package edu.fgcu.stesting.uiesg.data.mock;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import edu.fgcu.stesting.uiesg.data.GraphOutputData;
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatistic;
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatisticType;

@SuppressWarnings( "javadoc" )
public class UIEfficiencyStatisticTypeMock extends UIEfficiencyStatisticType {

	public UIEfficiencyStatisticTypeMock() throws DuplicateTypeException {}

	@Override
	public String getDescription() {
		return "A mock statistic type";
	}

	@Override
	public UIEfficiencyStatistic calculate( GraphOutputData graph ) {
		return createStatistic("mock statistic");
	}

	@Override
	public UIEfficiencyStatistic create( DataInputStream in ) {
		try {
			in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return createStatistic("mock statistic");
	}

	@Override
	public Class<?> getValueType() {
		return String.class;
	}

	@Override
	public void write( UIEfficiencyStatistic statistic, DataOutputStream out ) {
		try {
			out.write(53);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
