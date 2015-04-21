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
		list.add(GODFactory.newGraphAction(0, GODFactory.NODE,
				GODFactory.ENTER, 0, 0));
		list.add(GODFactory.newGraphAction(1, GODFactory.EDGE, GODFactory.MOVE,
				new double[] {0, 0, 0, 0}));
		list.add(GODFactory.newGraphAction(2, GODFactory.NODE,
				GODFactory.CLICK, 0, 0));
		list.add(GODFactory.newGraphAction(3, GODFactory.EDGE, GODFactory.MOVE,
				new double[] {0, 0, 0, 0}));
		list.add(GODFactory.newGraphAction(4, GODFactory.NODE,
				GODFactory.HOVER, new double[] {0, 0}));
		list.add(GODFactory.newGraphAction(5, GODFactory.EDGE, GODFactory.MOVE,
				new double[] {0, 0, 0, 0}));
		list.add(GODFactory.newGraphAction(6, GODFactory.NODE, GODFactory.EXIT,
				0, 0));
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
	public int numActions() {
		return order + size;
	}

	@Override
	public void addAction( MouseGraphAction action ) {
		if (locked)
			throw new RuntimeException("GOD has been locked");
		if (action instanceof MouseGraphNode)
			order++;
		else if (action instanceof MouseGraphEdge)
			size++;
		else
			return;
		list.add(action);
	}

	@Override
	public MouseGraphAction getAction( int index ) {
		try {
			return list.get(index);
		} catch (Exception e) {
			throw new RuntimeException("index: " + index + ";", e);
		}
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

	@Override
	public boolean equals( Object o ) {
		if (o instanceof GraphOutputDataMock) {
			GraphOutputDataMock god = (GraphOutputDataMock) o;
			if (god.list.size() != list.size())
				return false;
			for (int i = 0; i < list.size(); i++)
				if (!god.list.get(i).equals(list.get(i)))
					return false;
			return true;
		} else
			return false;
	}

}