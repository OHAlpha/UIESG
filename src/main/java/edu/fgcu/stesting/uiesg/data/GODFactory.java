package edu.fgcu.stesting.uiesg.data;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
	public static final int MOVE = 4;

	// TODO: javadoc
	public static final int DRAG = 5;

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
				return new MouseGraphNodeMock(type, subType, new Point(
						((Number) params[0]).intValue(),
						((Number) params[1]).intValue()));
			} else if (type == EDGE) {
				return new MouseGraphEdgeMock(type, subType, new Point(
						((Number) params[0]).intValue(),
						((Number) params[1]).intValue()), new Point(
						((Number) params[2]).intValue(),
						((Number) params[3]).intValue()));
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

	// TODO: javadoc
	public static void write( MouseGraphAction action, OutputStream o ) {
		DataOutputStream out = new DataOutputStream( o );
		if( actionMode == IMPLEMENTATION ) {
			try {
				out.write(action.getType());
				out.write(action.getSubType());
				if( action.getType() == NODE ) {
					Point2D a = ((MouseGraphNode)action).getLocation();
					out.writeDouble(a.getX());
					out.writeDouble(a.getY());
				}
				else {
					Point2D a = ((MouseGraphEdge)action).getSource();
					Point2D b = ((MouseGraphEdge)action).getDest();
					out.writeDouble(a.getX());
					out.writeDouble(a.getY());
					out.writeDouble(b.getX());
					out.writeDouble(b.getY());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}