package edu.fgcu.stesting.uiesg.data;

import static org.junit.Assert.assertEquals;

import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import edu.fgcu.stesting.uiesg.data.MouseGraphAction;
import edu.fgcu.stesting.uiesg.data.MouseGraphActionUnitTest;
import edu.fgcu.stesting.uiesg.data.graph.Dim;
import edu.fgcu.stesting.uiesg.data.graph.MouseDragEdge;
import edu.fgcu.stesting.uiesg.data.graph.MouseMoveEdge;

@SuppressWarnings( "javadoc" )
@RunWith( value = Parameterized.class )
public class MouseGraphEdgeUnitTest extends MouseGraphActionUnitTest {

	@Parameters
	public static Collection<Object[]> dataParameters() {
		List<Object[]> params = new LinkedList<>();
		params.add(new Object[] {
				new MouseMoveEdge(2l, new double[] { 0, 0, 2, 3, 5, 5 }),
				GODFactory.EDGE, GODFactory.MOVE, 2l, null, null, 0.1400347391,
				new Dim(.5, .5), new Rectangle2D.Double(0, 0, 5, 5), MouseGraphEdge.PATH_TYPE_LINEAR });
		params.add(new Object[] {
				new MouseDragEdge(5l, new double[] { 0, 0, 2, 3, 5, 5 }),
				GODFactory.EDGE, GODFactory.DRAG, 5l, null, null, 0.1400347391,
				new Dim(.5, .5), new Rectangle2D.Double(0, 0, 5, 5), MouseGraphEdge.PATH_TYPE_LINEAR });
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
	
	int pathType;
	
	MouseGraphEdge edge;

	public MouseGraphEdgeUnitTest( MouseGraphAction action, int type,
			int subType, long timestamp, MouseGraphAction previous,
			MouseGraphAction next, double error, Dimension2D variance,
			Rectangle2D range, int pathType ) {
		super(action, type, subType, timestamp, previous, next, error,
				variance, range);
		this.pathType = pathType;
		this.edge = (MouseGraphEdge) action;
	}
	
	@Test
	public void testPathType() {
		assertEquals( pathType, edge.getPathType());
	}

}
