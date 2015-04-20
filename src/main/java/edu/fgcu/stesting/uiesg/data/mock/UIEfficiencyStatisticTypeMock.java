package edu.fgcu.stesting.uiesg.data.mock;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import edu.fgcu.stesting.uiesg.data.GraphOutputData;
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatistic;
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatisticType;

@SuppressWarnings( "javadoc" )
public class UIEfficiencyStatisticTypeMock extends UIEfficiencyStatisticType {

	public UIEfficiencyStatisticTypeMock() throws DuplicateTypeException {
		super();
	}

	@Override
	public String getName() {
		return "Mock";
	}

	@Override
	public String getDescription() {
		return "A mock statistic type";
	}

	@Override
	public UIEfficiencyStatistic calculate( GraphOutputData graph ) {
		return createStatistic(this, "mock statistic");
	}

	@Override
	public UIEfficiencyStatistic create( InputStream in ) {
		try {
			in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return createStatistic(this, "mock statistic");
	}

	@Override
	public Class<?> getValueType() {
		return String.class;
	}

	@Override
	public void write( UIEfficiencyStatistic statistic, OutputStream out ) {
		try {
			out.write(53);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
