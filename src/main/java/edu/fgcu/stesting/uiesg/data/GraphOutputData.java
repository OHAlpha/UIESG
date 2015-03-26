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
	 */
	MouseGraphAction getAction( int index );

	/**
	 * Returns the node at the specified index.
	 * 
	 * @param index
	 *            the index of the node
	 * @return the node
	 */
	MouseGraphNode getNode( int index );

	/**
	 * Returns the edge at the specified index.
	 * 
	 * @param index
	 *            the index of the edge
	 * @return the edge
	 */
	MouseGraphEdge getEdge( int index );

	/**
	 * Returns the index of the specified action if it exists in the graph.
	 * 
	 * @param action
	 *            the action to search for
	 * @return the index
	 */
	int indexOf( MouseGraphAction action );

}