package edu.fgcu.stesting.uiesg.data;

import java.util.Iterator;

import edu.fgcu.stesting.uiesg.data.MouseActionInputData.MAIDFactory;
import edu.fgcu.stesting.uiesg.data.graph.MouseGraphAction;
import edu.fgcu.stesting.uiesg.data.graph.MouseGraphEdge;
import edu.fgcu.stesting.uiesg.data.graph.MouseGraphNode;
import edu.fgcu.stesting.uiesg.data.imp.GraphOutputDataImp;
import edu.fgcu.stesting.uiesg.data.imp.MouseActionInputDataImp;

/**
 * GraphOutputData (GOD) is a container for the graph data. Instances will store
 * nodes and edges representing the various events that occur in the browser
 * window.
 * 
 * @author oalpha
 *
 */
public interface GraphOutputData {

	public static class GODFactory {

		public static final int MOCK = 0;

		public static final int IMPLEMENTATION = 1;

		protected static int mode;

		protected static GraphOutputData newInstance() {
			return mode == MOCK ? new GraphOutputDataMock()
					: new GraphOutputDataImp();
		}

		public static GraphOutputData newInstance( Iterator<MouseActionInputDataImp.Point> mouseData ) {
			return mode == MOCK ? new GraphOutputDataMock(mouseData)
					: new GraphOutputDataImp(mouseData);
		}

		public static void init( int mode ) {
			GODFactory.mode = mode;
		}

	}

	/**
	 * The number of nodes in the graph.
	 * 
	 * @return the number of nodes
	 */
	int order();

	/**
	 * The number of edges in the graph.
	 * 
	 * @return the number of edges
	 */
	int size();
	
	void addAction( MouseGraphAction action );
	
	MouseGraphAction getAction( int index );
	
	MouseGraphNode getNode( int index );
	
	MouseGraphEdge getEdge( int index );

	int indexOf( MouseGraphAction action );

}