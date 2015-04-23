package edu.fgcu.stesting.uiesg.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import edu.fgcu.stesting.uiesg.data.UIEfficiencyStatisticType.UIEfficiencyStatistics;
import edu.fgcu.stesting.uiesg.data.mock.UIEfficiencyStatisticTypeMock;
import edu.fgcu.stesting.uiesg.data.statistic.DirectionVariance;
import edu.fgcu.stesting.uiesg.data.statistic.MouseArea;
import edu.fgcu.stesting.uiesg.data.statistic.NodesPerMinute;
import edu.fgcu.stesting.uiesg.data.statistic.PositionVariance;

@SuppressWarnings( "javadoc" )
@RunWith( value = Parameterized.class )
public class UIEfficiencyStatisticTypeUnitTest {

	static MouseActionInputData maid;

	static GraphOutputData god;

	@Parameters
	public static Collection<Object[]> dataParameters() {
		UIEfficiencyStatistics.reset();
		maid = MAIDFactory.newInstance();
		maid.addPoint(new Point2D.Double(0, 0), new Point2D.Double(0, 0),
				System.currentTimeMillis(), MouseEvent.MOUSE_MOVED);
		maid.addPoint(new Point2D.Double(3, 6), new Point2D.Double(3, 6),
				System.currentTimeMillis(), MouseEvent.MOUSE_MOVED);
		maid.addPoint(new Point2D.Double(5, 7), new Point2D.Double(5, 7),
				System.currentTimeMillis(), MouseEvent.MOUSE_MOVED);
		maid.addPoint(new Point2D.Double(11, 3), new Point2D.Double(11, 3),
				System.currentTimeMillis(), MouseEvent.MOUSE_MOVED);
		maid.addPoint(new Point2D.Double(12, 14), new Point2D.Double(12, 14),
				System.currentTimeMillis(), MouseEvent.MOUSE_MOVED);
		maid.addPoint(new Point2D.Double(50, 7), new Point2D.Double(50, 7),
				System.currentTimeMillis(), MouseEvent.MOUSE_MOVED);
		maid.addPoint(new Point2D.Double(11, 30), new Point2D.Double(11, 30),
				System.currentTimeMillis(), MouseEvent.MOUSE_MOVED);
		maid.addPoint(new Point2D.Double(15, 77), new Point2D.Double(15, 77),
				System.currentTimeMillis(), MouseEvent.MOUSE_MOVED);
		maid.addPoint(new Point2D.Double(30, 5), new Point2D.Double(30, 5),
				System.currentTimeMillis(), MouseEvent.MOUSE_MOVED);
		god = GODFactory.newInstance(null);
		List<Object[]> params = new LinkedList<>();
		params.add(new Object[] { "Mock",
				new UIEfficiencyStatisticTypeMock("Mock") });
		params.add(new Object[] { "NodesPerMinute", new NodesPerMinute() });
		params.add(new Object[] { "MouseArea", new MouseArea() });
		params.add(new Object[] { "PositionVariance", new PositionVariance() });
		params.add(new Object[] { "DirectionVariance", new DirectionVariance() });
		return params;
	}

	String name;

	UIEfficiencyStatisticType type;

	public UIEfficiencyStatisticTypeUnitTest( String name,
			UIEfficiencyStatisticType type ) {
		this.type = type;
		this.name = name;
	}

	@Test
	public void testGetName() {
		assertEquals("name should be " + name, name, type.getName());
	}

	@Test
	public void testGetDescription() {
		assertNotNull("description should not be null", type.getDescription());
	}

	@Test
	public void testRegister() {
		assertTrue("register should succeed", type.register());
		assertFalse("register should fail", type.register());
	}

	@Test
	public void testCalculate() {
		UIEfficiencyStatistic s = type.calculate(maid, god);
		assertNotNull("statistic should not be null", s);
		assertEquals("type should equal stat.type", type, s.getType());
		assertTrue("value should be of type type.valuetype", type
				.getValueType().isInstance(s.getUIValue()));
	}

	@SuppressWarnings( "resource" )
	@Test
	public void testStream() throws IOException {
		UIEfficiencyStatistic s = type.calculate(maid, god);
		assertNotNull("statistic should not be null", s);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(
				bout));
		s.write(out);
		out.flush();
		byte[] data = bout.toByteArray();
		out.close();
		DataInputStream in = new DataInputStream(new BufferedInputStream(
				new ByteArrayInputStream(data)));
		UIEfficiencyStatistic t = type.create(in);
		in.close();
		assertEquals(s, t);
	}

}
