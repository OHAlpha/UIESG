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
@SuppressWarnings( "javadoc" )
public abstract class AbstractMouseGraphEdge extends AbstractMouseGraphAction
		implements MouseGraphEdge {

	/**
	 * @param timestamp
	 * @param previous
	 * @param next
	 */
	protected AbstractMouseGraphEdge( long timestamp,
			MouseGraphAction previous, MouseGraphAction next ) {
		super(timestamp, previous, next);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param timestamp
	 */
	protected AbstractMouseGraphEdge( long timestamp ) {
		super(timestamp);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.fgcu.stesting.uiesg.data.MouseGraphAction#asNode()
	 */
	@Override
	public MouseGraphNode asNode() {
		throw new IllegalArgumentException("This action is an edge");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.fgcu.stesting.uiesg.data.MouseGraphAction#asEdge()
	 */
	@Override
	public MouseGraphEdge asEdge() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.fgcu.stesting.uiesg.data.MouseGraphAction#getType()
	 */
	@Override
	public int getType() {
		return GODFactory.EDGE;
	}

}
