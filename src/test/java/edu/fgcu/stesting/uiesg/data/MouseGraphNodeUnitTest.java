package edu.fgcu.stesting.uiesg.data;

import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import edu.fgcu.stesting.uiesg.data.MouseGraphAction;
import edu.fgcu.stesting.uiesg.data.MouseGraphActionUnitTest;
import edu.fgcu.stesting.uiesg.data.graph.Dim;
import edu.fgcu.stesting.uiesg.data.graph.MouseClickNode;
import edu.fgcu.stesting.uiesg.data.graph.MouseHoverNode;
import edu.fgcu.stesting.uiesg.data.graph.MouseWindowNode;

@SuppressWarnings( "javadoc" )
@RunWith( value = Parameterized.class )
public class MouseGraphNodeUnitTest extends MouseGraphActionUnitTest {

	@Parameters
	public static Collection<Object[]> dataParameters() {
		List<Object[]> params = new LinkedList<>();
		params.add(new Object[] { new MouseWindowNode(1l, true, 0, 0),
				GODFactory.NODE, GODFactory.ENTER, 1l, null, null, 0,
				new Dim(), new Rectangle2D.Double() });
		params.add(new Object[] { new MouseClickNode(3l, 0, 0),
				GODFactory.NODE, GODFactory.CLICK, 3l, null, null, 0,
				new Dim(), new Rectangle2D.Double() });
		params.add(new Object[] {
				new MouseHoverNode(4l, new double[] { 0, 0, 2, 3, 5, 5 }),
				GODFactory.NODE, GODFactory.HOVER, 4l, null, null,
				7.2111025509, new Dim(19. / 3, 19. / 3),
				new Rectangle2D.Double(0, 0, 5, 5) });
		params.add(new Object[] { new MouseWindowNode(6l, false, 0, 0),
				GODFactory.NODE, GODFactory.EXIT, 6l, null, null, 0, new Dim(),
				new Rectangle2D.Double() });
		for (int i = 0; i < params.size() - 1; i++) {
			((MouseGraphAction) params.get(i)[0])
					.setNext((MouseGraphAction) params.get(i + 1)[0]);
			params.get(i)[5] = params.get(i + 1)[0];
		}
		for (int i = 1; i < params.size(); i++) {
			((MouseGraphAction) params.get(i)[0])
					.setPrevious((MouseGraphAction) params.get(i - 1)[0]);
			params.get(i)[4] = params.get(i - 1)[0];
		}
		return params;
	}
	
	MouseGraphNode node;

	public MouseGraphNodeUnitTest( MouseGraphAction action, int type,
			int subType, long timestamp, MouseGraphAction previous,
			MouseGraphAction next, double error, Dimension2D variance,
			Rectangle2D range ) {
		super(action, type, subType, timestamp, previous, next, error,
				variance, range);
		this.node = (MouseGraphNode) action;
	}

}
