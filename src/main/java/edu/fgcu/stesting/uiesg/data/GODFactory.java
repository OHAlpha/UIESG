package edu.fgcu.stesting.uiesg.data;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import edu.fgcu.stesting.uiesg.data.graph.MouseClickNode;
import edu.fgcu.stesting.uiesg.data.graph.MouseDragEdge;
import edu.fgcu.stesting.uiesg.data.graph.MouseHoverNode;
import edu.fgcu.stesting.uiesg.data.graph.MouseMoveEdge;
import edu.fgcu.stesting.uiesg.data.graph.MouseWindowNode;
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

	public static MouseGraphAction newGraphAction( long timestamp, int type,
			int subType, Object... params ) {
		if (actionMode == MOCK) {
			;
			// System.out.println("action type is "+type);
			if (type == NODE) {
				return new MouseGraphNodeMock(timestamp, type, subType,
						new Point(((Number) params[0]).intValue(),
								((Number) params[1]).intValue()));
			} else if (type == EDGE) {
				return new MouseGraphEdgeMock(timestamp, type, subType,
						new Point(((Number) params[0]).intValue(),
								((Number) params[1]).intValue()), new Point(
								((Number) params[2]).intValue(),
								((Number) params[3]).intValue()));
			} else {
				;
				// System.out.println("action type cannot be "+type);
				return null;
			}
		} else if (actionMode == IMPLEMENTATION) {
			if (type == NODE) {
				if (subType == CLICK) {
					return new MouseClickNode(timestamp,
							((Number) params[0]).doubleValue(),
							((Number) params[1]).doubleValue());
				} else if (subType == HOVER) {
					return new MouseHoverNode(timestamp, (double[]) params[0]);
				} else if (subType == ENTER || subType == EXIT) {
					return new MouseWindowNode(timestamp, subType == ENTER,
							((Number) params[0]).doubleValue(),
							((Number) params[1]).doubleValue());
				} else
					return null;
			} else if (type == EDGE) {
				if (subType == MOVE)
					return new MouseMoveEdge(timestamp, (double[]) params[0]);
				else if (subType == DRAG)
					return new MouseDragEdge(timestamp, (double[]) params[0]);
				else
					return null;
			} else {
				;
				// System.out.println("action type cannot be "+type);
				return null;
			}
		}
		return null;
	}

	// TODO: javadoc
	public static MouseGraphAction read( InputStream i ) throws IOException {
		DataInputStream in = new DataInputStream(i);
		if (actionMode == MOCK) {
			long l = in.readLong();
			int t = in.readInt(), s = in.readInt();
			if (t == NODE)
				return newGraphAction(l, t, s, in.readDouble(), in.readDouble());
			else
				return newGraphAction(l, t, s, in.readDouble(),
						in.readDouble(), in.readDouble(), in.readDouble());
		} else
			return null;
	}

	// TODO: javadoc
	public static void write( MouseGraphAction action, OutputStream o )
			throws IOException {
		DataOutputStream out = new DataOutputStream(o);
		if (actionMode == MOCK) {
			out.writeLong(action.getTimestamp());
			out.writeInt(action.getType());
			out.writeInt(action.getSubType());
			if (action.getType() == NODE) {
				Point2D a = ((MouseGraphNode) action).getLocation();
				out.writeDouble(a.getX());
				out.writeDouble(a.getY());
			} else {
				Point2D a = ((MouseGraphEdge) action).getSource();
				Point2D b = ((MouseGraphEdge) action).getDest();
				out.writeDouble(a.getX());
				out.writeDouble(a.getY());
				out.writeDouble(b.getX());
				out.writeDouble(b.getY());
			}
		}
	}

}