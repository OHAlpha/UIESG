package edu.fgcu.stesting.uiesg.data;

class MouseActionInputData {
    
    private List<Point2D> rawData;
    
    public MouseActionInputData() {
        rawData = new ArrayList<Point2D>();
    }
    
    public int size() {
        return rawData.size();
    }
    
    public Rectangle2D getRange() {
        throw new RuntimeException("method not implemented");
    }
    
}