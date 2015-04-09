package edu.fgcu.stesting.uiesg.data;

import java.awt.Point;
import java.io.InputStream;
import java.util.Iterator;

import edu.fgcu.stesting.uiesg.data.imp.GraphOutputDataImp;
import edu.fgcu.stesting.uiesg.data.imp.MouseActionInputDataImp;
import edu.fgcu.stesting.uiesg.data.mock.GraphOutputDataMock;
import edu.fgcu.stesting.uiesg.data.mock.MouseGraphEdgeMock;
import edu.fgcu.stesting.uiesg.data.mock.MouseGraphNodeMock;

// TODO: javadoc
@SuppressWarnings( "javadoc" )
public class GODFactory {

	// TODO: javadoc=
	public static final int MOCK = 0;

	// TODO: javadoc
	public static final int IMPLEMENTATION = 1;

	// TODO: javadoc
	public static final int NODE = 0;

	// TODO: javadoc
	public static final int EDGE = 1;

	// TODO: javadoc
	public static final int ENTER = 0;

	// TODO: javadoc
	public static final int HOVER = 1;

	// TODO: javadoc
	public static final int CLICK = 2;

	// TODO: javadoc
	public static final int EXIT = 3;

	// TODO: javadoc
	public static final int MOVE = 0;

	// TODO: javadoc
	public static final int DRAG = 1;

	// TODO: javadoc
	protected static int godMode;

	// TODO: javadoc
	protected static int actionMode;

	// TODO: javadoc
	public static GraphOutputData newInstance() {
		return godMode == MOCK ? new GraphOutputDataMock()
				: new GraphOutputDataImp();
	}

	// TODO: javadoc
	public static GraphOutputData newInstance(
			Iterator<MouseActionInputDataImp.Point> mouseData ) {
		return godMode == MOCK ? new GraphOutputDataMock(mouseData)
				: new GraphOutputDataImp(mouseData);
	}

	// TODO: javadoc
	public static void init( int mode ) {
		GODFactory.godMode = mode;
		GODFactory.actionMode = mode;
	}

	// TODO: javadoc
	public static void init( int godMode, int actionMode ) {
		GODFactory.godMode = godMode;
		GODFactory.actionMode = actionMode;
	}

	public static MouseGraphAction newGraphAction( int type, int subType,
			Object... params ) {
		if (actionMode == MOCK) {
			if (type == NODE) {
				return new MouseGraphNodeMock(new Point((int) params[0],
						(int) params[1]));
			} else if (type == EDGE) {
				return new MouseGraphEdgeMock(new Point((int) params[0],
						(int) params[1]), new Point((int) params[2],
						(int) params[3]));
			} else
				return null;
		} else if (actionMode == IMPLEMENTATION) {
			return null;
		}
		return null;
	}

	// TODO: javadoc
	public static MouseGraphAction read( InputStream in ) {
		throw new RuntimeException("method not implemented");
		// TODO
	}

}