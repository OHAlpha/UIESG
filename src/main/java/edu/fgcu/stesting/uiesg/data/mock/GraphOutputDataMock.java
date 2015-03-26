package edu.fgcu.stesting.uiesg.data.mock;

import java.util.Iterator;

import edu.fgcu.stesting.uiesg.data.GraphOutputData;
import edu.fgcu.stesting.uiesg.data.MouseActionInputData.Point;
import edu.fgcu.stesting.uiesg.data.graph.MouseGraphAction;
import edu.fgcu.stesting.uiesg.data.graph.MouseGraphEdge;
import edu.fgcu.stesting.uiesg.data.graph.MouseGraphNode;

// TODO: javadoc
@SuppressWarnings( "javadoc" )
public class GraphOutputDataMock implements GraphOutputData {

	public GraphOutputDataMock( Iterator<Point> mouseData ) {
		// TODO Auto-generated constructor stub
	}

	public GraphOutputDataMock() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int order() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addAction( MouseGraphAction action ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MouseGraphAction getAction( int index ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MouseGraphNode getNode( int index ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MouseGraphEdge getEdge( int index ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf( MouseGraphAction action ) {
		// TODO Auto-generated method stub
		return 0;
	}

}