package edu.fgcu.stesting.uiesg.data;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MouseActionInputData {
	
	public static class Point3D {
		
		public Point2D location;
		
		long timestamp;
		
	}
    
    private List<Point3D> rawData;
    
    protected MouseActionInputData( ArrayList<Point3D> rawData ) {
        this.rawData = rawData;
    }
    
    public MouseActionInputData() {
        rawData = new ArrayList<Point3D>();
    }
    
    public int size() {
        return rawData.size();
    }
    
    public Rectangle2D getRange() {
        throw new RuntimeException("method not implemented");
    }
    
    public void addPoint( Point2D point, long timestamp ) {
        throw new RuntimeException("method not implemented");
    }
    
    public Iterator<Point3D> iterate() {
        throw new RuntimeException("method not implemented");
    }
    
}