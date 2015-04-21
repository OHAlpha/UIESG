package edu.fgcu.stesting.uiesg.data.graph;

import edu.fgcu.stesting.uiesg.data.GODFactory;
import edu.fgcu.stesting.uiesg.data.MouseGraphAction;
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

	/**
	 * @param timestamp
	 * @param previous
	 * @param next
	 */
	protected AbstractMouseGraphNode( long timestamp,
			MouseGraphAction previous, MouseGraphAction next ) {
		super(timestamp, previous, next);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param timestamp
	 */
	protected AbstractMouseGraphNode( long timestamp ) {
		super(timestamp);
		// TODO Auto-generated constructor stub
	}

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
