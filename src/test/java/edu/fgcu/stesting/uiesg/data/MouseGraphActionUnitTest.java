package edu.fgcu.stesting.uiesg.data;

import static org.junit.Assert.assertEquals;

import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@SuppressWarnings( "javadoc" )
@RunWith( value = Parameterized.class )
public class MouseGraphActionUnitTest {

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