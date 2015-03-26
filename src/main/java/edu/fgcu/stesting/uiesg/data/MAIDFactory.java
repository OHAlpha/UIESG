package edu.fgcu.stesting.uiesg.data;

import edu.fgcu.stesting.uiesg.data.imp.MouseActionInputDataImp;
import edu.fgcu.stesting.uiesg.data.mock.MouseActionInputDataMock;

// TODO: javadoc
@SuppressWarnings( "javadoc" )
public class MAIDFactory {

	public static final int MOCK = 0;

	public static final int IMPLEMENTATION = 1;

	protected static int mode;

	public static MouseActionInputData newInstance() {
		return mode == MOCK ? new MouseActionInputDataMock()
				: new MouseActionInputDataImp();
	}

	public static void init( int mode ) {
		MAIDFactory.mode = mode;
	}

}