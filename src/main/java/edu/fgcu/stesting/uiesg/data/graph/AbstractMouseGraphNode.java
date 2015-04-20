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
public abstract class AbstractMouseGraphNode extends AbstractMouseGraphAction
		implements MouseGraphNode {

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.fgcu.stesting.uiesg.data.MouseGraphAction#asNode()
	 */
	@Override
	@SuppressWarnings( "javadoc" )
	public MouseGraphNode asNode() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.fgcu.stesting.uiesg.data.MouseGraphAction#asEdge()
	 */
	@Override
	@SuppressWarnings( "javadoc" )
	public MouseGraphEdge asEdge() {
		throw new IllegalArgumentException("This action is a node");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.fgcu.stesting.uiesg.data.MouseGraphAction#getType()
	 */
	@Override
	@SuppressWarnings( "javadoc" )
	public int getType() {
		return GODFactory.NODE;
	}

}
