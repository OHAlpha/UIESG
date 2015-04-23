package edu.fgcu.stesting.uiesg.data.statistic;

import java.awt.geom.Dimension2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import edu.fgcu.stesting.uiesg.data.GraphOutputData;
import edu.fgcu.stesting.uiesg.data.MouseActionInputData;
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatistic;
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatisticType;
import edu.fgcu.stesting.uiesg.data.graph.Dim;

@SuppressWarnings( "javadoc" )
public class DirectionVariance extends UIEfficiencyStatisticType {

	@Override
	public String getDescription() {
		return "the variance of the mouse direction of movement. High values indicate "
				+ "erratic movement of the mouse, characteristic of a poor "
				+ "ui design.";
	}

	@Override
	public UIEfficiencyStatistic calculate( MouseActionInputData maid,
			GraphOutputData graph ) {
		double sumX = 0, sumY = 0;
		int mS = maid.size();
		double[] dS = new double[2 * (mS - 1)];
		sumX += maid.getPoint(0).browserLocation.getX();
		sumY += maid.getPoint(0).browserLocation.getY();
		for (int i = 1; i < mS; i++) {
			dS[2 * (i - 1)] = maid.getPoint(i).browserLocation.getX()
					- maid.getPoint(i - 1).browserLocation.getX();
			dS[2 * (i - 1)+1] = maid.getPoint(i).browserLocation.getY()
					- maid.getPoint(i - 1).browserLocation.getY();
			sumX += dS[2 * (i - 1)];
			sumY += dS[2 * (i - 1)+1];
		}
		mS--;
		double meanX = sumX / mS, meanY = sumY / mS;
		double devX = 0, devY = 0;
		for (int i = 0; i < mS; i++) {
			double dX = maid.getPoint(i).browserLocation.getX() - meanX;
			double dY = maid.getPoint(i).browserLocation.getY() - meanY;
			devX += dX * dX;
			devY += dY * dY;
		}
		return createStatistic(new Dim(devX / (mS - 1), devY / (mS - 1)));
	}

	@Override
	public UIEfficiencyStatistic create( DataInputStream in )
			throws IOException {
		return createStatistic(new Dim(in.readDouble(), in.readDouble()));
	}

	@Override
	public Class<?> getValueType() {
		return Dimension2D.class;
	}

	@Override
	public void write( UIEfficiencyStatistic statistic, DataOutputStream out )
			throws IOException {
		Dimension2D v = (Dimension2D) statistic.getValue();
		out.writeDouble(v.getWidth());
		out.writeDouble(v.getHeight());
	}

}
