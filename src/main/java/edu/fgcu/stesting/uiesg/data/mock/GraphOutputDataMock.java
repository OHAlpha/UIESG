package edu.fgcu.stesting.uiesg.data.mock;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.fgcu.stesting.uiesg.data.GODFactory;
import edu.fgcu.stesting.uiesg.data.GraphOutputData;
import edu.fgcu.stesting.uiesg.data.MouseGraphAction;
import edu.fgcu.stesting.uiesg.data.MouseGraphEdge;
import edu.fgcu.stesting.uiesg.data.MouseGraphNode;
import edu.fgcu.stesting.uiesg.data.MouseActionInputData.Point;

// TODO: javadoc
@SuppressWarnings( "javadoc" )
public class GraphOutputDataMock implements GraphOutputData {

	private boolean locked;

	private int order, size;

	private List<MouseGraphAction> list;

	public GraphOutputDataMock( Iterator<Point> mouseData ) {
		locked = true;
		order = 4;
		size = 3;
		list = new LinkedList<>();
		list.add(GODFactory.newGraphAction(GODFactory.NODE, GODFactory.ENTER,0,0));
		list.add(GODFactory.newGraphAction(GODFactory.EDGE, GODFactory.MOVE,0,0,0,0));
		list.add(GODFactory.newGraphAction(GODFactory.NODE, GODFactory.CLICK,0,0));
		list.add(GODFactory.newGraphAction(GODFactory.EDGE, GODFactory.MOVE,0,0,0,0));
		list.add(GODFactory.newGraphAction(GODFactory.NODE, GODFactory.HOVER,0,0));
		list.add(GODFactory.newGraphAction(GODFactory.EDGE, GODFactory.MOVE,0,0,0,0));
		list.add(GODFactory.newGraphAction(GODFactory.NODE, GODFactory.EXIT,0,0));
	}

	public GraphOutputDataMock() {
		locked = false;
		order = size = 0;
		list = new LinkedList<>();
	}

	@Override
	public int order() {
		return order;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void addAction( MouseGraphAction action ) {
		if (locked)
			throw new RuntimeException("GOD has been locked");
		if (action instanceof MouseGraphNode)
			order++;
		if (action instanceof MouseGraphEdge)
			size++;
		list.add(action);
	}

	@Override
	public MouseGraphAction getAction( int index ) {
		return list.get(index);
	}

	@Override
	public MouseGraphNode getNode( int index ) {
		MouseGraphAction a = list.get(index);
		if (a instanceof MouseGraphNode)
			return (MouseGraphNode) a;
		throw new IllegalArgumentException("Action is an edge");
	}

	@Override
	public MouseGraphEdge getEdge( int index ) {
		MouseGraphAction a = list.get(index);
		if (a instanceof MouseGraphEdge)
			return (MouseGraphEdge) a;
		throw new IllegalArgumentException("Action is a node");
	}

	@Override
	public int indexOf( MouseGraphAction action ) {
		return list.indexOf(action);
	}

	@Override
	public void lock() {
		locked = true;
	}

}