package orm;

import java.util.PriorityQueue;

public class BaseStaff {
    private int staffID;
    private String name;
    private PriorityQueue<Mark> marks;
    
    public BaseStaff() {
        // Get DB connection.
    }
    
    public int getStaffID() {
        return staffID;
    }
    
    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public PriorityQueue<Mark> getMarks() {
        return marks;
    }
    
    public void setMarks(PriorityQueue<Mark> marks) {
        this.marks = marks;
    }
}
