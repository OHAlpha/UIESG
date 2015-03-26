package edu.fgcu.stesting.uiesg.data.imp;

import java.util.Iterator;
import java.util.List;

import edu.fgcu.stesting.uiesg.data.GraphOutputData;
import edu.fgcu.stesting.uiesg.data.graph.MouseGraphAction;
import edu.fgcu.stesting.uiesg.data.graph.MouseGraphEdge;
import edu.fgcu.stesting.uiesg.data.graph.MouseGraphNode;

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
	 * The collection of nodes in this graph.
	 */
	@SuppressWarnings( "unused" )
	private final List<MouseGraphNode> nodes;

	/**
	 * The collection of edges in this graph.
	 */
	@SuppressWarnings( "unused" )
	private final List<MouseGraphEdge> edges;
	
	public GraphOutputDataImp()
			throws NullPointerException {
		throw new RuntimeException("constructor not implemented");
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
	public GraphOutputDataImp( Iterator<MouseActionInputDataImp.Point> mouseData )
			throws NullPointerException {
		throw new RuntimeException("constructor not implemented");
	}

	/**
	 * The number of nodes in the graph.
	 * 
	 * @return the number of nodes
	 */
	public int order() {
		throw new RuntimeException("method not implemented");
	}

	/**
	 * The number of edges in the graph.
	 * 
	 * @return the number of edges
	 */
	public int size() {
		throw new RuntimeException("method not implemented");
	}

	@Override
	public void addAction( MouseGraphAction action ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MouseGraphAction getAction( int index ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MouseGraphNode getNode( int index ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MouseGraphEdge getEdge( int index ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf( MouseGraphAction action ) {
		// TODO Auto-generated method stub
		return 0;
	}

}