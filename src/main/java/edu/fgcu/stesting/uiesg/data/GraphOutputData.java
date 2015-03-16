package edu.fgcu.stesting.uiesg.data;

import java.util.Collection;

public class GraphOutputData {

	private Collection<MouseGraphNode> nodes;
	
	private Collection<MouseGraphEdge> edges;

	/**
	 * @param e
	 * @return
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	public boolean add(MouseGraphNode e) {
		return nodes.add(e);
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	public boolean add(MouseGraphEdge e) {
		return edges.add(e);
	}
    
}