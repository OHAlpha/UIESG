package edu.fgcu.stesting.uiesg.data.statistic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import edu.fgcu.stesting.uiesg.data.GraphOutputData;
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatistic;
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatisticType;

@SuppressWarnings( "javadoc" )
public class NodesPerMinute extends UIEfficiencyStatisticType {

	public NodesPerMinute() throws DuplicateTypeException {
		super();
	}

	@Override
	public String getDescription() {
		return "Calculates the average number of nodes ( clicks and hovers ) "
				+ "per minute. A high value indicates a lot of movement and "
				+ "a lot of navigation through pages which can be indicative "
				+ "of a poor website design.";
	}

	@Override
	public UIEfficiencyStatistic calculate( GraphOutputData graph ) {
		int o = graph.order(), s = graph.size();
		long b = graph.getAction(0).getTimestamp(), e = graph.getAction(
				o + s - 1).getTimestamp();
		return createStatistic(o * 60. * 1000. / (e - b));
	}

	@Override
	public UIEfficiencyStatistic create( InputStream i ) {
		DataInputStream in = new DataInputStream(i);
		try {
			return createStatistic(in.readDouble());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<?> getValueType() {
		return Double.class;
	}

	@Override
	public void write( UIEfficiencyStatistic statistic, OutputStream o ) {
		DataOutputStream out = new DataOutputStream(o);
		try {
			out.writeDouble((Double) statistic.getValue());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
