package edu.fgcu.stesting.uiesg.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
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
import edu.fgcu.stesting.uiesg.data.graph.MouseClickNode;
import edu.fgcu.stesting.uiesg.data.graph.MouseDragEdge;
import edu.fgcu.stesting.uiesg.data.graph.MouseMoveEdge;

@SuppressWarnings( "javadoc" )
@RunWith( value = Parameterized.class )
public class MouseGraphEdgeUnitTest extends MouseGraphActionUnitTest {

	static MouseClickNode node = new MouseClickNode(3l, 0, 0);

	@Parameters
	public static Collection<Object[]> dataParameters() {
		List<Object[]> params = new LinkedList<>();
		params.add(new Object[] {
				new MouseMoveEdge(2l, new double[] { 0, 0, 2, 3, 5, 5 }),
				GODFactory.EDGE, GODFactory.MOVE, 2l, null, null, 0.1400347391,
				new Dim(.5, .5), new Rectangle2D.Double(0, 0, 5, 5),
				MouseGraphEdge.PATH_TYPE_LINEAR, null, null, null, null, null,
				null });
		Rectangle2D a = new Rectangle2D.Double(0, 0, 5, 5), b = new Rectangle2D.Double(
				1, 1, 2, 2);
		double e = 7.2111025509, f = 10;
		Dim v = new Dim(19. / 3, 19. / 3), w = new Dim();
		Point2D s = new Point2D.Double(0, 0), t = new Point2D.Double(1, 1), d = new Point2D.Double(
				5, 5), c = new Point2D.Double(4, 4);
		params.add(new Object[] {
				new MouseDragEdge(5l, new double[] { 0, 0, 2, 3, 5, 5 }),
				GODFactory.EDGE, GODFactory.DRAG, 5l, null, null, 0.1400347391,
				new Dim(.5, .5), new Rectangle2D.Double(0, 0, 5, 5),
				MouseGraphEdge.PATH_TYPE_LINEAR, null,
				new MouseDragEdge(4l, s, d, b, e, v),
				new MouseDragEdge(4l, s, d, a, f, v),
				new MouseDragEdge(4l, s, d, a, e, w),
				new MouseDragEdge(4l, t, d, a, e, w),
				new MouseDragEdge(4l, s, c, a, e, w) });
		for (int i = 0; i < params.size(); i++)
			params.get(i)[10] = params.get((i + 1) % params.size())[0];
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

	MouseGraphEdge edge, sub, ran, err, var, src, dst;

	public MouseGraphEdgeUnitTest( MouseGraphAction action, int type,
			int subType, long timestamp, MouseGraphAction previous,
			MouseGraphAction next, double error, Dimension2D variance,
			Rectangle2D range, int pathType, MouseGraphEdge sub,
			MouseGraphEdge ran, MouseGraphEdge err, MouseGraphEdge var,
			MouseGraphEdge src, MouseGraphEdge dst ) {
		super(action, type, subType, timestamp, previous, next, error,
				variance, range);
		this.pathType = pathType;
		this.edge = (MouseGraphEdge) action;
		this.sub = sub;
		this.ran = ran;
		this.err = err;
		this.src = src;
		this.dst = dst;
	}

	@Test
	public void testPathType() {
		assertEquals(pathType, edge.getPathType());
	}

	@Test( expected = AssertionError.class )
	public void testDiffType() {
		assertNotEquals(edge, node);
		edge.assertEquals(node, true);
	}

	@Test( expected = AssertionError.class )
	public void testDiffSubType() {
		assertNotEquals(edge, sub);
		edge.assertEquals(sub, true);
	}

	@Test( expected = AssertionError.class )
	public void testDiffRanType() {
		assertNotEquals(edge, ran);
		edge.assertEquals(ran, true);
	}

	@Test( expected = AssertionError.class )
	public void testDiffErrType() {
		assertNotEquals(edge, err);
		edge.assertEquals(err, true);
	}

	@Test( expected = AssertionError.class )
	public void testDiffVarType() {
		assertNotEquals(edge, var);
		edge.assertEquals(var, true);
	}

	@Test( expected = AssertionError.class )
	public void testDiffSrcType() {
		assertNotEquals(edge, src);
		edge.assertEquals(src, true);
	}

	@Test( expected = AssertionError.class )
	public void testDiffDstType() {
		assertNotEquals(edge, dst);
		edge.assertEquals(dst, true);
	}

}
