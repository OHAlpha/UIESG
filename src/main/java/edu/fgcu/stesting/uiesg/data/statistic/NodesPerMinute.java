package edu.fgcu.stesting.uiesg.data.statistic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import edu.fgcu.stesting.uiesg.data.GraphOutputData;
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatistic;
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatisticType;
import edu.fgcu.stesting.uiesg.data.MouseActionInputData;

@SuppressWarnings( "javadoc" )
public class NodesPerMinute extends UIEfficiencyStatisticType {

	@Override
	public String getDescription() {
		return "Calculates the average number of nodes ( clicks and hovers ) "
				+ "per minute. A high value indicates a lot of movement and "
				+ "a lot of navigation through pages which can be indicative "
				+ "of a poor website design.";
	}

	@Override
	public UIEfficiencyStatistic calculate( MouseActionInputData maid, GraphOutputData graph ) {
		int o = graph.order(), s = graph.size();
		long b = graph.getAction(0).getTimestamp(), e = graph.getAction(
				o + s - 1).getTimestamp();
		return createStatistic(o * 60. * 1000. / (e - b));
	}

	@Override
	public UIEfficiencyStatistic create( DataInputStream in ) throws IOException {
		return createStatistic(in.readDouble());
	}

	@Override
	public Class<?> getValueType() {
		return Double.class;
	}

	@Override
	public void write( UIEfficiencyStatistic statistic, DataOutputStream out ) throws IOException {
		out.writeDouble((Double) statistic.getUIValue());
	}

}
