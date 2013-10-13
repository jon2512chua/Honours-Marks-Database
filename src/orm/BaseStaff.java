package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import sessionControl.Session;

public class BaseStaff {
    public StringBuffer staffID = new StringBuffer (30);
    public StringBuffer firstName = new StringBuffer (30);
    public StringBuffer lastName = new StringBuffer (30);
    public PriorityQueue<Mark> marks;
    
    public BaseStaff(int staffID) {
        try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet staffRS = s.executeQuery("SELECT * FROM Staff WHERE StaffID=" + staffID)) {
            
            // There will only be one staff returned as staffID is unique.
            staffRS.first();
            
            setStaffID(staffID);
            setFirstName(staffRS.getString("FirstName"));
            setLastName(staffRS.getString("LastName"));
            // TODO: Figure out what the marks represent.
        } catch (SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getStaffID() {
        return Integer.parseInt(staffID+"");
    }
    
    public void setStaffID(int staffID) {
        this.staffID.replace(0, this.staffID.length(),  Integer.toString(staffID));
    }
    
    public StringBuffer getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
    	this.firstName.replace(0, this.firstName.length(), firstName);
    }
    
    public StringBuffer getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
    	this.lastName.replace(0, this.lastName.length(), lastName);
    }
    
    public PriorityQueue<Mark> getMarks() {
        return marks;
    }
    
    public void setMarks(PriorityQueue<Mark> marks) {
        this.marks = marks;
    }
}
