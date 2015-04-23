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

import edu.fgcu.stesting.uiesg.data.graph.Dim;
import edu.fgcu.stesting.uiesg.data.graph.MouseClickNode;
import edu.fgcu.stesting.uiesg.data.graph.MouseDragEdge;
import edu.fgcu.stesting.uiesg.data.graph.MouseHoverNode;
import edu.fgcu.stesting.uiesg.data.graph.MouseMoveEdge;
import edu.fgcu.stesting.uiesg.data.graph.MouseWindowNode;

@SuppressWarnings( "javadoc" )
@RunWith( value = Parameterized.class )
public class MouseGraphActionUnitTest {

	@Parameters
	public static Collection<Object[]> dataParameters() {
		List<Object[]> params = new LinkedList<>();
		params.add(new Object[] { new MouseWindowNode(1l, true, 0, 0),
				GODFactory.NODE, GODFactory.ENTER, 1l, null, null, 0,
				new Dim(), new Rectangle2D.Double() });
		params.add(new Object[] {
				new MouseMoveEdge(2l, new double[] { 0, 0, 2, 3, 5, 5 }),
				GODFactory.EDGE, GODFactory.MOVE, 2l, null, null, 0.1400347391,
				new Dim(.5, .5), new Rectangle2D.Double(0, 0, 5, 5) });
		params.add(new Object[] { new MouseClickNode(3l, 0, 0),
				GODFactory.NODE, GODFactory.CLICK, 3l, null, null, 0,
				new Dim(), new Rectangle2D.Double() });
		params.add(new Object[] {
				new MouseHoverNode(4l, new double[] { 0, 0, 2, 3, 5, 5 }),
				GODFactory.NODE, GODFactory.HOVER, 4l, null, null,
				7.2111025509, new Dim(19. / 3, 19. / 3),
				new Rectangle2D.Double(0, 0, 5, 5) });
		params.add(new Object[] {
				new MouseDragEdge(5l, new double[] { 0, 0, 2, 3, 5, 5 }),
				GODFactory.EDGE, GODFactory.DRAG, 5l, null, null, 0.1400347391,
				new Dim(.5, .5), new Rectangle2D.Double(0, 0, 5, 5) });
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

	MouseGraphAction action;

	int type, subType;

	long timestamp;

	MouseGraphAction previous, next;

	double error;

	Dimension2D variance;

	Rectangle2D range;

	public MouseGraphActionUnitTest( MouseGraphAction action, int type,
			int subType, long timestamp, MouseGraphAction previous,
			MouseGraphAction next, double error, Dimension2D variance,
			Rectangle2D range ) {
		this.action = action;
		this.type = type;
		this.subType = subType;
		this.timestamp = timestamp;
		this.previous = previous;
		this.next = next;
		this.error = error;
		this.variance = variance;
		this.range = range;
	}

	@Test
	public void testGetType() {
		assertEquals(type, action.getType());
		assertEquals(subType, action.getSubType());
	}

	@Test
	public void testSequene() {
		assertEquals(previous, action.getPrevious());
		assertEquals(next, action.getNext());
	}

	@Test
	public void testDeviance() {
		assertEquals(error, action.getError(), .0001);
		assertEquals(variance.getWidth(), action.getVariance().getWidth(),
				.0001);
		assertEquals(variance.getHeight(), action.getVariance().getHeight(),
				.0001);
	}

	@Test
	public void testRange() {
		assertEquals(range.getX(), action.getRange().getX(), .0001);
		assertEquals(range.getY(), action.getRange().getY(), .0001);
		assertEquals(range.getWidth(), action.getRange().getWidth(), .0001);
		assertEquals(range.getHeight(), action.getRange().getHeight(), .0001);
	}

	@Test
	public void testAs() {
		if (type == GODFactory.NODE)
			action.asNode();
		else
			action.asEdge();
	}

	@Test( expected = IllegalArgumentException.class )
	public void testAsNot() {
		if (type == GODFactory.NODE)
			action.asEdge();
		else
			action.asNode();
	}

}