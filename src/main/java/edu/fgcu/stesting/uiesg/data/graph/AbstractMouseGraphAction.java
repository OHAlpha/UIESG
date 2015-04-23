package edu.fgcu.stesting.uiesg.data.graph;

import edu.fgcu.stesting.uiesg.data.MouseGraphAction;

// TODO: javadoc
@SuppressWarnings( "javadoc" )
public abstract class AbstractMouseGraphAction implements MouseGraphAction {

	private MouseGraphAction previous;

	private MouseGraphAction next;

	private long timestamp;

	protected AbstractMouseGraphAction( long timestamp ) {
		this.timestamp = timestamp;
	}

	protected AbstractMouseGraphAction( long timestamp,
			MouseGraphAction previous, MouseGraphAction next ) {
		this.timestamp = timestamp;
		this.previous = previous;
		this.next = next;
	}

	@Override
	public MouseGraphAction getPrevious() {
		return previous;
	}

	@Override
	public MouseGraphAction getNext() {
		return next;
	}

	@Override
	public void setPrevious( MouseGraphAction previous ) {
		this.previous = previous;
	}

	@Override
	public void setNext( MouseGraphAction next ) {
		this.next = next;
	}

	@Override
	public long getTimestamp() {
		return timestamp;
	}

}