package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import sessionControl.Session;

/**
 * Base class representing a Staff.
 * 
 * @author Jonathan Chua
 * @author Samuel Widenbar
 * @version 16/10/2013
 */
public class BaseStaff {
    /**
     * Primary key identifying the Staff, exists as an Integer in the database.
     */
    public StringBuffer staffID = new StringBuffer (12);
    
    /**
     * First name of the staff, exists as a String in the database.
     */
    public StringBuffer firstName = new StringBuffer (24);
    
    /**
     * Last name of the staff, exists as a String in the database.
     */
    public StringBuffer lastName = new StringBuffer (24);
    
    /**
     * A sorted queue of Marks assigned by this staff.
     */
    public PriorityQueue<Mark> marks = new PriorityQueue<Mark>();
    
    /**
     * Constructor to create a new java object to represent a staff found in the database.
     * 
     * @param staffID the ID of the staff
     */
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
    
    /**
     * Constructor to create a java object to represent a staff that does not exist yet in the database.
     * 
     * @param staffID the ID of the staff
     * @param firstName the first name of the staff
     * @param lastName the last name of the staff
     * @throws SQLException when there is an error with the SQL statement
     */
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
    
    /**
     * Method to get the ID of the staff.
     * 
     * @return ID of the staff
     */
    public int getStaffID() {
        return Integer.parseInt(staffID+"");
    }
    
    /**
     * Method to set the ID of the staff.
     * 
     * @param staffID ID of the staff
     */
    public void setStaffID(int staffID) {
        this.staffID.replace(0, this.staffID.capacity(),  Integer.toString(staffID));
    }
    
    /**
     * Method to get the first name of the staff.
     * 
     * @return the first name of the staff
     */
    public StringBuffer getFirstName() {
        return firstName;
    }
    
    /**
     * Method to set the first name of the staff.
     * 
     * @param firstName first name of the staff
     */
    public void setFirstName(String firstName) {
    	this.firstName.replace(0, this.firstName.capacity(), firstName);
    }
    
    /**
     * Method to get the last name of the staff.
     * 
     * @return the last name of the staff
     */
    public StringBuffer getLastName() {
        return lastName;
    }
    
    /**
     * Method to set the last name of the staff.
     * 
     * @param lastName the last name of the staff
     */
    public void setLastName(String lastName) {
    	this.lastName.replace(0, this.lastName.capacity(), lastName);
    }
    
    /**
     * Method to get all the marks this staff had given out.
     * 
     * @return all the marks this staff had given out stored in a sorted queue
     */
    public PriorityQueue<Mark> getMarks() {
        return marks;
    }
    
    /**
     * Method to set all the marks this staff had given out.
     * 
     * @param marks all the marks this staff had given out stored in a sorted queue
     */
    public void setMarks(PriorityQueue<Mark> marks) {
        this.marks = marks;
    }
    
    /**
     * Private helper method to get all the marks from the database and put them in a sorted queue.
     * 
     * @param staffID
     * @return a sorted queue of Mark objects
     */
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
