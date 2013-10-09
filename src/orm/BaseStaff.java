package orm;

import java.util.PriorityQueue;

public class BaseStaff {
    private int staffID;
    private String firstName;
    private String lastName;
    private PriorityQueue<Mark> marks;
    
    public BaseStaff(int staffID) {
        // Get DB connection.
    }
    
    public int getStaffID() {
        return staffID;
    }
    
    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }
    
    public String getFistName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public PriorityQueue<Mark> getMarks() {
        return marks;
    }
    
    public void setMarks(PriorityQueue<Mark> marks) {
        this.marks = marks;
    }
}
