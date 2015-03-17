package edu.fgcu.stesting.uiesg.data;

import java.util.List;

public class GraphOutputData {

	@SuppressWarnings("unused")
	private List<MouseGraphNode> nodes;

	@SuppressWarnings("unused")
	private List<MouseGraphEdge> edges;
	
	public GraphOutputData( MouseActionInputData mouseData ) {
		throw new RuntimeException("constructor not implemented");
	}
	
	public int order() {
		throw new RuntimeException("method not implemented");
	}
	
	public int size() {
		throw new RuntimeException("method not implemented");
	}
    
}