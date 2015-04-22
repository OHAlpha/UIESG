package edu.fgcu.stesting.uiesg.data;

/**
 * GraphOutputData (GOD) is a container for the graph data. Instances will store
 * nodes and edges representing the various events that occur in the browser
 * window.
 * 
 * @author oalpha
 *
 */
public interface GraphOutputData {

	/**
	 * The number of nodes in the graph.
	 * 
	 * @return the number of nodes
	 */
	int order();

	/**
	 * The number of edges in the graph.
	 * 
	 * @return the number of edges
	 */
	int size();
	
	/**
	 * The number of actions in the graph.
	 * 
	 * @return the number of edges
	 */
	int numActions();

	/**
	 * Adds the action to the graph. Used by SED.loadData().
	 * 
	 * @param action
	 *            the action to add.
	 */
	void addAction( MouseGraphAction action );

	/**
	 * Returns the action at the specified index.
	 * 
	 * @param index
	 *            the index of the action
	 * @return the action
	 * @throws RuntimeException
	 *             if this instance has been locked
	 */
	MouseGraphAction getAction( int index ) throws RuntimeException;

	/**
	 * Returns the node at the specified index.
	 * 
	 * @param index
	 *            the index of the node
	 * @return the node
	 * @throws IllegalArgumentException
	 *             if the action at this index is an edge
	 */
	MouseGraphNode getNode( int index ) throws IllegalArgumentException;

	/**
	 * Returns the edge at the specified index.
	 * 
	 * @param index
	 *            the index of the edge
	 * @return the edge
	 * @throws IllegalArgumentException
	 *             if the action at this index is a node
	 */
	MouseGraphEdge getEdge( int index ) throws IllegalArgumentException;

	/**
	 * Returns the index of the specified action if it exists in the graph.
	 * 
	 * @param action
	 *            the action to search for
	 * @return the index
	 */
	int indexOf( MouseGraphAction action );

	/**
	 * Prevents subsequent calls to addAction to fail.
	 */
	void lock();

	/**
	 * Throws an error if this god is not equivalent to the specified god.
	 * 
	 * @param graphData
	 *            the god to test
	 * @throws AssertionError
	 *             if these gods are not equivalent
	 */
	void assertEquals( GraphOutputData graphData );

}