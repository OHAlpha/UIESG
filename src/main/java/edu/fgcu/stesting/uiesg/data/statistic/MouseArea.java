package edu.fgcu.stesting.uiesg.data.statistic;

import java.awt.geom.Rectangle2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
		if (n == 0)
			return createStatistic(0.0);
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
	public UIEfficiencyStatistic create( InputStream in ) {
		try {
			return createStatistic((in instanceof DataInputStream ? (DataInputStream) in
					: new DataInputStream(in)).readDouble());
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
	public void write( UIEfficiencyStatistic statistic, OutputStream out ) {
		try {
			(out instanceof DataOutputStream ? (DataOutputStream) out
					: new DataOutputStream(out)).writeDouble((Double) statistic
					.getValue());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
