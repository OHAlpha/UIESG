package edu.fgcu.stesting.uiesg.data.graph;

import java.awt.Shape;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.io.OutputStream;

import edu.fgcu.stesting.uiesg.data.MouseGraphAction;
import edu.fgcu.stesting.uiesg.data.MouseGraphEdge;
import edu.fgcu.stesting.uiesg.data.MouseGraphNode;

/**
 * Represents dragging of the mouse in the browser window. THe previous and next
 * actions will be either other MouseDragEdges or MouseClickNodes.
 * 
 * @author oalpha
 *
 */
public class MouseDragEdge extends AbstractMouseGraphAction implements MouseGraphEdge {

	@SuppressWarnings( "javadoc" )
	@Override
	public long getTimestamp() {
		throw new RuntimeException("method not implemented");
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public MouseGraphAction getPrevious() {
		throw new RuntimeException("method not implemented");
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public MouseGraphNode getNext() {
		throw new RuntimeException("method not implemented");
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public double getError() {
		throw new RuntimeException("method not implemented");
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public Dimension2D getVariance() {
		throw new RuntimeException("method not implemented");
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public Rectangle2D getRange() {
		throw new RuntimeException("method not implemented");
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public Shape getArea() {
		throw new RuntimeException("method not implemented");
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public int getPathType() {
		throw new RuntimeException("method not implemented");
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public Object getPathParameters() {
		throw new RuntimeException("method not implemented");
	}

	@SuppressWarnings( "javadoc" )
	@Override
	public void write( OutputStream out ) {
		// TODO Auto-generated method stub
		
	}

}