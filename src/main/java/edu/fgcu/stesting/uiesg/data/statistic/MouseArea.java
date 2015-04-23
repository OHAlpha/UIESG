package edu.fgcu.stesting.uiesg.data.statistic;

import java.awt.geom.Rectangle2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import edu.fgcu.stesting.uiesg.data.GraphOutputData;
import edu.fgcu.stesting.uiesg.data.MouseGraphAction;
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatistic;
import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatisticType;

@SuppressWarnings( "javadoc" )
public class MouseArea extends UIEfficiencyStatisticType {

	@Override
	public String getDescription() {
		return "The area containing the mouse movement. Generally, good "
				+ "designs have less area, however, this may be inaccurate "
				+ "because using the toolbar will raise the area though it "
				+ "has nothing to do with the site's ui.";
	}

	@Override
	public UIEfficiencyStatistic calculate( GraphOutputData graph ) {
		int n = graph.numActions();
		MouseGraphAction a = graph.getAction(0);
		Rectangle2D range = a.getRange().getBounds2D();
		for (int i = 1; i < n; i++) {
			a = graph.getAction(i);
			Rectangle2D r = a.getRange();
			range.setRect(
					r.getMinX() < range.getMinX() ? r.getMinX() : range
							.getMinX(),
					r.getMinY() < range.getMinY() ? r.getMinY() : range
							.getMinY(),
					r.getMaxX() > range.getMaxX() ? r.getMaxX() : range
							.getMaxX(),
					r.getMaxY() > range.getMaxY() ? r.getMaxY() : range
							.getMaxY());
		}
		return createStatistic(range.getWidth() * range.getHeight());
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
		out.writeDouble((Double) statistic
				.getValue());
	}

}
