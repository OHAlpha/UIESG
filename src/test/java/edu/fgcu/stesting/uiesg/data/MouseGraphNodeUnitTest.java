package edu.fgcu.stesting.uiesg.data;

import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

import edu.fgcu.stesting.uiesg.data.MouseGraphAction;
import edu.fgcu.stesting.uiesg.data.MouseGraphActionUnitTest;
import edu.fgcu.stesting.uiesg.data.graph.Dim;
import edu.fgcu.stesting.uiesg.data.graph.MouseClickNode;
import edu.fgcu.stesting.uiesg.data.graph.MouseHoverNode;
import edu.fgcu.stesting.uiesg.data.graph.MouseMoveEdge;
import edu.fgcu.stesting.uiesg.data.graph.MouseWindowNode;

@SuppressWarnings( "javadoc" )
@RunWith( value = Parameterized.class )
public class MouseGraphNodeUnitTest extends MouseGraphActionUnitTest {

	static MouseMoveEdge edge = new MouseMoveEdge(2l, new double[] { 0, 0, 2,
			3, 5, 5 });

	@Parameters
	public static Collection<Object[]> dataParameters() {
		List<Object[]> params = new LinkedList<>();
		params.add(new Object[] { new MouseWindowNode(1l, true, 0, 0),
				GODFactory.NODE, GODFactory.ENTER, 1l, null, null, 0,
				new Dim(), new Rectangle2D.Double(), null, null, null, null });
		params.add(new Object[] { new MouseClickNode(3l, 0, 0),
				GODFactory.NODE, GODFactory.CLICK, 3l, null, null, 0,
				new Dim(), new Rectangle2D.Double(), null, null, null, null });
		Rectangle2D a = new Rectangle2D.Double(0, 0, 5, 5), b = new Rectangle2D.Double(
				1, 1, 2, 2);
		double e = 7.2111025509, f = 10;
		Dim v = new Dim(19. / 3, 19. / 3), w = new Dim();
		params.add(new Object[] {
				new MouseHoverNode(4l, new double[] { 0, 0, 2, 3, 5, 5 }),
				GODFactory.NODE, GODFactory.HOVER, 4l, null, null, e, v,
				new Rectangle2D.Double(0, 0, 5, 5), null,
				new MouseHoverNode(4l, b, e, v),
				new MouseHoverNode(4l, a, f, v),
				new MouseHoverNode(4l, a, e, w) });
		params.add(new Object[] { new MouseWindowNode(6l, false, 0, 0),
				GODFactory.NODE, GODFactory.EXIT, 6l, null, null, 0, new Dim(),
				new Rectangle2D.Double(), null, null, null, null });
		for (int i = 0; i < params.size(); i++)
			params.get(i)[9] = params.get((i + 1) % params.size())[0];
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

	MouseGraphNode node, sub, ran, err, var;

	public MouseGraphNodeUnitTest( MouseGraphAction action, int type,
			int subType, long timestamp, MouseGraphAction previous,
			MouseGraphAction next, double error, Dimension2D variance,
			Rectangle2D range, MouseGraphNode sub, MouseGraphNode ran,
			MouseGraphNode err, MouseGraphNode var ) {
		super(action, type, subType, timestamp, previous, next, error,
				variance, range);
		this.node = (MouseGraphNode) action;
		this.sub = sub;
		this.ran = ran;
		this.err = err;
		this.var = var;
	}

	@Test( expected=AssertionError.class )
	public void testDiffType() {
		assertNotEquals(node, edge);
		node.assertEquals(edge, true);
	}

	@Test( expected=AssertionError.class )
	public void testDiffSubType() {
		assertNotEquals(node, sub);
		node.assertEquals(sub, true);
	}

	@Test( expected=AssertionError.class )
	public void testDiffRanType() {
		assertNotEquals(node, ran);
		node.assertEquals(ran, true);
	}

	@Test( expected=AssertionError.class )
	public void testDiffErrType() {
		assertNotEquals(node, err);
		node.assertEquals(err, true);
	}

	@Test( expected=AssertionError.class )
	public void testDiffVarType() {
		assertNotEquals(node, var);
		node.assertEquals(err, true);
	}

}
