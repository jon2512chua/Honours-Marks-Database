package orm;

public class BaseMark {
    private String markId;
    private int value;
    private Staff marker;
    private String report;
    
    public BaseMark() {
        // Get DB Connection.
    }
    
    public String getMarkId() {
        return markId;
    }
    
    public void setMarkId(String markId) {
        this.markId = markId;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
        this.value = value;
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
