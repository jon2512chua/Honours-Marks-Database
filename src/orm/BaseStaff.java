package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import sessionControl.Session;

public class BaseStaff {
    public StringBuffer staffID = new StringBuffer (12);
    public StringBuffer firstName = new StringBuffer (24);
    public StringBuffer lastName = new StringBuffer (24);
    public PriorityQueue<Mark> marks;
    
    public BaseStaff(int staffID) {
        try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet staffRS = s.executeQuery("SELECT * FROM Staff WHERE StaffID=" + staffID)) {
            
            // There will only be one staff returned as staffID is unique.
        	while (staffRS.next()) {
            
	            setStaffID(staffID);
	            setFirstName(staffRS.getString("FirstName"));
	            setLastName(staffRS.getString("LastName"));
	            setMarks(getSortedMarks(staffID));
        	}
        } catch (SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public BaseStaff(int staffID, String firstName, String lastName) throws SQLException {
        try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            s.execute("INSERT INTO Staff VALUES ("
                    + staffID + ", '"
                    + firstName + "', '"
                    + lastName + "')");
            
            setStaffID(staffID);
            setFirstName(firstName);
            setLastName(lastName);
            setMarks(new PriorityQueue<Mark>());
        } catch (SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException(ex.getMessage());
        }
    }
    
    public int getStaffID() {
        return Integer.parseInt(staffID+"");
    }
    
    public void setStaffID(int staffID) {
        this.staffID.replace(0, this.staffID.capacity(),  Integer.toString(staffID));
    }
    
    public StringBuffer getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
    	this.firstName.replace(0, this.firstName.capacity(), firstName);
    }
    
    public StringBuffer getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
    	this.lastName.replace(0, this.lastName.capacity(), lastName);
    }
    
    public PriorityQueue<Mark> getMarks() {
        return marks;
    }
    
    public void setMarks(PriorityQueue<Mark> marks) {
        this.marks = marks;
    }
    
    private PriorityQueue<Mark> getSortedMarks(int staffID) {
        PriorityQueue<Mark> marksQueue = new PriorityQueue<>();
        
        try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet markRS = s.executeQuery("SELECT * FROM SubAssessmentMark WHERE MarkerID=" + staffID)) {
            while (markRS.next()) {
                marksQueue.add(new Mark(markRS.getInt("SubAssessmentID"), markRS.getInt("StudentID"), staffID, new SubAssessment(markRS.getInt("SubAssessmentID"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return marksQueue;
    }
}
