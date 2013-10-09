package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import java.util.*;
import sessionControl.Session;

public class BaseStaff {
    private int staffID;
    private String firstName;
    private String lastName;
    private PriorityQueue<Mark> marks;
    
    public BaseStaff(int staffID) {
        try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet staffRS = s.executeQuery("SELECT * FROM Staff WHERE StaffID=" + staffID)) {
            
            // There will only be one staff returned as staffID is unique.
            staffRS.first();
            
            this.staffID = staffID;
            this.firstName = staffRS.getString("FirstName");
            this.lastName = staffRS.getString("LastName");
            // TODO: Figure out what the marks represent.
        } catch (SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
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
