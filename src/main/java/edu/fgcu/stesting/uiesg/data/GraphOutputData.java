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

}