package edu.fgcu.stesting.uiesg.data.imp;

import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.fgcu.stesting.uiesg.data.GODFactory;
import edu.fgcu.stesting.uiesg.data.GraphOutputData;
import edu.fgcu.stesting.uiesg.data.MouseActionInputData;
import edu.fgcu.stesting.uiesg.data.MouseActionInputData.Point;
import edu.fgcu.stesting.uiesg.data.MouseGraphAction;
import edu.fgcu.stesting.uiesg.data.MouseGraphEdge;
import edu.fgcu.stesting.uiesg.data.MouseGraphNode;

import static java.awt.event.MouseEvent.*;

/**
 * GraphOutputData (GOD) is a container for the graph data. Instances will store
 * nodes and edges representing the various events that occur in the browser
 * window.
 * 
 * @author oalpha
 *
 */
public class GraphOutputDataImp implements GraphOutputData {

	/**
	 * Whether this instance has been locked.
	 */
	private boolean locked;

	/**
	 * The collection of actions in this graph.
	 */
	private final List<MouseGraphAction> actions;

	/**
	 * The collection of node indices in this graph.
	 */
	private final List<Integer> nodes;

	/**
	 * The collection of edge indices in this graph.
	 */
	private final List<Integer> edges;

	{
		actions = new LinkedList<>();
		nodes = new LinkedList<>();
		edges = new LinkedList<>();
	}

	/**
	 * Creates an empty instance. Used by SED.loadData().
	 */
	public GraphOutputDataImp() {
		locked = false;
	}

	/**
	 * Constructs a GOD instance from raw mouse data. The constructor will join
	 * multiple move events into fewer, longer MouseMoveEdges and
	 * MouseDragEdges, filter small movements into MouseHoverNodes, create
	 * MouseClickNodes for click events, etc.. This constructor shall not throw
	 * any exceptions except NullPointerException for null input.
	 * 
	 * @param mouseData
	 *            an iterator for the raw data
	 * @throws NullPointerException
	 *             if mouseData is null
	 */
	public GraphOutputDataImp( Iterator<MouseActionInputData.Point> mouseData )
			throws NullPointerException {
		int lastType = MOUSE_FIRST - 1;
		Point2D a = null, b = null;
		for (; mouseData.hasNext();) {
			Point c = mouseData.next();
			int t = c.type;
			Point2D p = c.browserLocation;
			switch (t) {
			case MOUSE_ENTERED:
				if (lastType == MOUSE_MOVED)
					this.addAction(GODFactory.newGraphAction(GODFactory.EDGE,
							GODFactory.MOVE, a.getX(), a.getY(), b.getX(),
							b.getY()));
				else if (lastType == MOUSE_DRAGGED)
					this.addAction(GODFactory.newGraphAction(GODFactory.EDGE,
							GODFactory.DRAG, a.getX(), a.getY(), b.getX(),
							b.getY()));
				this.addAction(GODFactory.newGraphAction(GODFactory.NODE,
						GODFactory.ENTER, p.getX(), p.getY()));
				a = p;
				break;
			case MOUSE_MOVED:
				b = p;
				break;
			case MOUSE_PRESSED:
				if (lastType == MOUSE_MOVED)
					this.addAction(GODFactory.newGraphAction(GODFactory.EDGE,
							GODFactory.MOVE, a.getX(), a.getY(), b.getX(),
							b.getY()));
				a = p;
				break;
			case MOUSE_DRAGGED:
				b = p;
				break;
			case MOUSE_RELEASED:
				if (lastType == MOUSE_DRAGGED)
					this.addAction(GODFactory.newGraphAction(GODFactory.EDGE,
							GODFactory.DRAG, a.getX(), a.getY(), b.getX(),
							b.getY()));
				break;
			case MOUSE_CLICKED:
				if (lastType == MOUSE_MOVED)
					this.addAction(GODFactory.newGraphAction(GODFactory.EDGE,
							GODFactory.MOVE, a.getX(), a.getY(), b.getX(),
							b.getY()));
				else if (lastType == MOUSE_DRAGGED)
					this.addAction(GODFactory.newGraphAction(GODFactory.EDGE,
							GODFactory.DRAG, a.getX(), a.getY(), b.getX(),
							b.getY()));
				this.addAction(GODFactory.newGraphAction(GODFactory.NODE,
						GODFactory.CLICK, p.getX(), p.getY()));
				a = p;
			case MOUSE_EXITED:
				if (lastType == MOUSE_MOVED)
					this.addAction(GODFactory.newGraphAction(GODFactory.EDGE,
							GODFactory.MOVE, a.getX(), a.getY(), b.getX(),
							b.getY()));
				else if (lastType == MOUSE_DRAGGED)
					this.addAction(GODFactory.newGraphAction(GODFactory.EDGE,
							GODFactory.DRAG, a.getX(), a.getY(), b.getX(),
							b.getY()));
				this.addAction(GODFactory.newGraphAction(GODFactory.NODE,
						GODFactory.EXIT, p.getX(), p.getY()));
				a = p;
			}
			lastType = t;
		}
		locked = true;
	}

	/**
	 * The number of nodes in the graph.
	 * 
	 * @return the number of nodes
	 */
	public int order() {
		return nodes.size();
	}

	/**
	 * The number of edges in the graph.
	 * 
	 * @return the number of edges
	 */
	public int size() {
		return edges.size();
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public void addAction( MouseGraphAction action ) {
		if (locked)
			throw new RuntimeException("GOD has been locked");
		int i = actions.size();
		if (action instanceof MouseGraphNode)
			nodes.add(i);
		else if (action instanceof MouseGraphEdge)
			edges.add(i);
		else
			return;
		actions.add(action);
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public MouseGraphAction getAction( int index ) {
		return actions.get(index);
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public MouseGraphNode getNode( int index ) {
		MouseGraphAction a = actions.get(index);
		if (a instanceof MouseGraphNode)
			return (MouseGraphNode) a;
		throw new IllegalArgumentException("Action is an edge");
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public MouseGraphEdge getEdge( int index ) {
		MouseGraphAction a = actions.get(index);
		if (a instanceof MouseGraphEdge)
			return (MouseGraphEdge) a;
		throw new IllegalArgumentException("Action is a node");
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public int indexOf( MouseGraphAction action ) {
		return actions.indexOf(action);
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public void lock() {
		locked = true;
	}

}