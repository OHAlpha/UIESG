package edu.fgcu.stesting.uiesg.data;

import java.io.InputStream;

// TODO: javadoc
@SuppressWarnings( "javadoc" )
public class MouseGraphActionFactory {

	// TODO: javadoc
	public static final int MOCK = 0;

	// TODO: javadoc
	public static final int IMPLEMENTATION = 1;

	// TODO: javadoc
	protected static int mode;

	// TODO: javadoc
	public static MouseGraphAction create( int type, Object... params ) {
		throw new RuntimeException("method not implemented");
		// TODO
	}

	// TODO: javadoc
	public static MouseGraphAction read( InputStream in ) {
		throw new RuntimeException("method not implemented");
		// TODO
	}

	// TODO: javadoc
	public static void init( int mode ) {
		MouseGraphActionFactory.mode = mode;
	}

}