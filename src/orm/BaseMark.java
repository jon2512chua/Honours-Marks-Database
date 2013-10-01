package orm;

public class BaseMark {
    private String markID;
    private int value;
    
    /**
     * An indicator to check whether the mark is within the two standard deviation
     * range from the average.
     * 
     * Should be true by default.
     */
    private boolean insideRange;
    private Staff marker;
    private String report;
    
    public BaseMark() {
        // Get DB Connection.
        insideRange = true;
    }
    
    public String getMarkID() {
        return markID;
    }
    
    public void setMarkID(String markID) {
        this.markID = markID;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public boolean getInsideRange() {
        return insideRange;
    }
    
    public void setInsideRange(boolean insideRange) {
        this.insideRange = insideRange;
    }
    
    public Staff getMarker() {
        return marker;
    }
    
    public void setMarker(Staff marker) {
        this.marker = marker;
    }
    
    public String getReport() {
        return report;
    }
    
    public void setReport(String report) {
        this.report = report;
    }
}
