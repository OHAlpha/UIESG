package edu.fgcu.stesting.uiesg.data;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class MouseActionInputData {
    
    private List<Point2D> rawData;
    
    private List<Long> times;
    
    protected MouseActionInputData( ArrayList<Point2D> rawData ) {
        this.rawData = rawData;
    }
    
    public MouseActionInputData() {
        rawData = new ArrayList<Point2D>();
        times = new ArrayList<Long>();
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
    
}