package edu.fgcu.stesting.uiesg.data;

import java.util.NoSuchElementException;

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

	/***
	 * method to test latest timestamp while rawData is empty
	 */
	@Test( expected = NoSuchElementException.class )
	public void testTimeStamp() {

		// rawData should be empty which will throw an error
		MAID.latestTimestamp();

	}
	
	@Test
	public void testToString() {
		assertEquals("MAID( #points: " + MAID.size() + " )",MAID.toString());
	}

}