package edu.fgcu.stesting.uiesg.data.graph;

import edu.fgcu.stesting.uiesg.data.GODFactory;
import edu.fgcu.stesting.uiesg.data.MouseGraphEdge;
import edu.fgcu.stesting.uiesg.data.MouseGraphNode;

/**
 * Provides the casting functions' implementations for nodes.
 * 
 * @author oalpha
 *
 */
public abstract class AbstractMouseGraphEdge extends AbstractMouseGraphAction implements
		MouseGraphEdge {

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.fgcu.stesting.uiesg.data.MouseGraphAction#asNode()
	 */
	@SuppressWarnings( "javadoc" )
	public MouseGraphNode asNode() {
		throw new IllegalArgumentException("This action is an edge");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.fgcu.stesting.uiesg.data.MouseGraphAction#asEdge()
	 */
	@SuppressWarnings( "javadoc" )
	public MouseGraphEdge asEdge() {
		return this;
	}
	
	/* (non-Javadoc)
	 * @see edu.fgcu.stesting.uiesg.data.MouseGraphAction#getType()
	 */
	@SuppressWarnings( "javadoc" )
	public int getType() {
		return GODFactory.EDGE;
	}

}
