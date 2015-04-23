package edu.fgcu.stesting.uiesg.data.imp;

import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.fgcu.stesting.uiesg.data.GODFactory;
import edu.fgcu.stesting.uiesg.data.GraphOutputData;
import edu.fgcu.stesting.uiesg.data.MouseActionInputData;
import edu.fgcu.stesting.uiesg.data.MouseActionInputData.Point;
import edu.fgcu.stesting.uiesg.data.mock.GraphOutputDataMock;
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
	 * The minimum speed ( in pixels per second ) that will be considered
	 * movement.
	 */
	private static final double SPEED_THRESHHOLD = 5;

	/**
	 * The maximum time ( in seconds ) between events that will be considered
	 * movement.
	 */
	private static final long TIME_THRESHHOLD = 200;

	/**
	 * Signifies the hovering of the mouse.
	 */
	private static final int MOUSE_HOVER = MOUSE_LAST + 1;

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
		long lastTime = -1;
		List<Point2D> h = new LinkedList<>();
		for (; mouseData.hasNext();) {
			Point c = mouseData.next();
			int t = c.type;
			switch (t) {
			case MOUSE_ENTERED:
				h.clear();
				this.addAction(GODFactory.newGraphAction(c.timestamp,
						GODFactory.NODE, GODFactory.ENTER,
						c.browserLocation.getX(), c.browserLocation.getY()));
				break;
			case MOUSE_MOVED:
				double d = h.isEmpty() ? 0 : c.browserLocation.distance(h.get(h
						.size() - 1));
				long r = c.timestamp - lastTime;
				if (d * 1000. / r < SPEED_THRESHHOLD || r > TIME_THRESHHOLD) {
					if (lastType == MOUSE_MOVED)
						this.addAction(GODFactory.newGraphAction(lastTime,
								GODFactory.EDGE, GODFactory.MOVE, toDouble(h)));
					t = MOUSE_HOVER;
				} else if (lastType == MOUSE_HOVER) {
					this.addAction(GODFactory.newGraphAction(lastTime,
							GODFactory.NODE, GODFactory.HOVER, toDouble(h)));
				}
				Point2D l = h.get(h.size() - 1);
				h.clear();
				h.add(l);
				break;
			case MOUSE_PRESSED:
				if (lastType == MOUSE_MOVED)
					this.addAction(GODFactory.newGraphAction(lastTime,
							GODFactory.EDGE, GODFactory.MOVE, toDouble(h)));
				else if (lastType == MOUSE_HOVER)
					this.addAction(GODFactory.newGraphAction(lastTime,
							GODFactory.NODE, GODFactory.HOVER, toDouble(h)));
				h.clear();
				break;
			case MOUSE_DRAGGED:
				break;
			case MOUSE_RELEASED:
				if (lastType == MOUSE_DRAGGED)
					this.addAction(GODFactory.newGraphAction(lastTime,
							GODFactory.EDGE, GODFactory.DRAG, toDouble(h)));
				h.clear();
				break;
			case MOUSE_CLICKED:
				if (lastType == MOUSE_MOVED)
					this.addAction(GODFactory.newGraphAction(lastTime,
							GODFactory.EDGE, GODFactory.MOVE, toDouble(h)));
				else if (lastType == MOUSE_DRAGGED)
					this.addAction(GODFactory.newGraphAction(lastTime,
							GODFactory.EDGE, GODFactory.DRAG, toDouble(h)));
				else if (lastType == MOUSE_HOVER)
					this.addAction(GODFactory.newGraphAction(lastTime,
							GODFactory.NODE, GODFactory.HOVER, toDouble(h)));
				this.addAction(GODFactory.newGraphAction(c.timestamp,
						GODFactory.NODE, GODFactory.CLICK,
						c.browserLocation.getX(), c.browserLocation.getY()));
				h.clear();
				break;
			case MOUSE_EXITED:
				if (lastType == MOUSE_MOVED)
					this.addAction(GODFactory.newGraphAction(lastTime,
							GODFactory.EDGE, GODFactory.MOVE, toDouble(h)));
				else if (lastType == MOUSE_DRAGGED)
					this.addAction(GODFactory.newGraphAction(lastTime,
							GODFactory.EDGE, GODFactory.DRAG, toDouble(h)));
				else if (lastType == MOUSE_HOVER)
					this.addAction(GODFactory.newGraphAction(lastTime,
							GODFactory.NODE, GODFactory.HOVER, toDouble(h)));
				this.addAction(GODFactory.newGraphAction(c.timestamp,
						GODFactory.NODE, GODFactory.EXIT,
						c.browserLocation.getX(), c.browserLocation.getY()));
				h.clear();
			}
			h.add(c.browserLocation);
			lastType = t;
			lastTime = c.timestamp;
		}
		locked = true;
	}

	@SuppressWarnings( "javadoc" )
	private double[] toDouble( List<Point2D> h ) {
		double[] out = new double[h.size() * 2];
		for (int i = 0; i < h.size(); i++) {
			Point2D p = h.get(i);
			out[i * 2] = p.getX();
			out[i * 2 + 1] = p.getY();
		}
		return out;
	}

	/**
	 * The number of nodes in the graph.
	 * 
	 * @return the number of nodes
	 */
	@Override
	public int order() {
		return nodes.size();
	}

	/**
	 * The number of edges in the graph.
	 * 
	 * @return the number of edges
	 */
	@Override
	public int size() {
		return edges.size();
	}

	/**
	 * The number of actions in the graph.
	 * 
	 * @return the number of edges
	 */
	@Override
	public int numActions() {
		return nodes.size() + edges.size();
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

	@Override
	@SuppressWarnings( "javadoc" )
	public boolean equals( Object o ) {
		if (o instanceof GraphOutputDataMock) {
			GraphOutputDataMock god = (GraphOutputDataMock) o;
			return assertEquals(god, false);
		} else
			return false;
	}

	@Override
	@SuppressWarnings( "javadoc" )
	public boolean assertEquals( GraphOutputData graphData, boolean error ) {
		if (graphData.numActions() != actions.size())
			if (error)
				throw new AssertionError("graphData.size() should be "
						+ numActions() + " but is " + graphData.numActions());
			else
				return false;
		for (int i = 0; i < actions.size(); i++) {
			if (!graphData.getAction(i).assertEquals(actions.get(i), error))
				return false;
		}
		return true;
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public String toString() {
		return "GODImp( #points: " + numActions() + ", order: " + order()
				+ ", size: " + size() + " )";
	}

}