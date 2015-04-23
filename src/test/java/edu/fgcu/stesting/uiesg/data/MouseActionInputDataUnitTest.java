package edu.fgcu.stesting.uiesg.data;

import org.junit.*;

import static org.junit.Assert.*;

// TODO: javadoc
@SuppressWarnings( "javadoc" )
public class MouseActionInputDataUnitTest extends MouseActionInputDataMockTest {

	public MouseActionInputDataUnitTest() {
	}

	@BeforeClass
	public static void setup() {
		MAIDFactory.init(MAIDFactory.IMPLEMENTATION);
	}
	
	@Test
	public void testToString() {
		assertEquals("MAID( #points: " + MAID.size() + " )",MAID.toString());
	}

}