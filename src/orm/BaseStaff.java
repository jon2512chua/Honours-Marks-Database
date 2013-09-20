package orm;

import java.util.PriorityQueue;

public class BaseStaff {
    private String staffId;
    private String name;
    private int numberMarks;
    private PriorityQueue<Mark> marks;
    
    public BaseStaff() {
        // Get DB connection.
    }
    
    public String getStaffId() {
        return staffId;
    }
    
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getNumberMarks() {
        return numberMarks;
    }
    
    public void setNumberMarks(int numberMarks) {
        this.numberMarks = numberMarks;
    }
    
    public PriorityQueue<Mark> getMarks() {
        return marks;
    }
    
    public void setMarks(PriorityQueue<Mark> marks) {
        this.marks = marks;
    }
}
