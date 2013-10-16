package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import sessionControl.Session;

/**
 * Class representing a Mark.
 * 
 * @author Jonathan Chua
 * @author Samuel Widenbar
 * @version 16/10/2013
 */
public class Mark extends BaseMark {
    /**
     * Constructor to create a java object to represent a mark found in the database belonging to a sub assessment.
     * 
     * The primary key is a combination of (subAssessmentID, studentID, markerID).
     * 
     * @param subAssessmentID ID for the sub assessment this mark belongs to
     * @param studentID ID for the student this mark belongs to
     * @param markerID ID for the marker this mark belongs to
     * @param subAssessment sub assessment this mark belongs to
     */
    public Mark(int subAssessmentID, int studentID, int markerID, SubAssessment subAssessment) {
        super(subAssessmentID, studentID,  markerID, subAssessment);
    }
    
    /**
     * Constructor to create a java object to represent a mark found in the database.
     * 
     * The primary key is a combination of (subAssessmentID, studentID, markerID).
     * 
     * @param subAssessmentID ID for the sub assessment this mark belongs to
     * @param studentID ID for the student this mark belongs to
     * @param markerID ID for the marker this mark belongs to
     */
    public Mark(int subAssessmentID, int studentID, int markerID) {
        super(subAssessmentID, studentID,  markerID);
    }
    
    /**
     * Constructor to create a java object to represent a mark that does not exist yet in the database.
     * 
     * @param value the amount of marks
     * @param report comments on the mark
     * @param insideRange whether the mark is within the accepted range
     * @param markerID the ID of the marker who gave the mark
     * @param studentID the ID of the student who got the mark
     * @param parentSubAssessment the sub assessment this mark is given to
     * @throws SQLException when there is an error with the SQL statement
     */
    public Mark(double value, String report, boolean insideRange, int markerID, int studentID, SubAssessment parentSubAssessment) throws SQLException {
        super(value, report, insideRange, markerID, studentID, parentSubAssessment);
    }
    
    /**
     * Method to get all the marks in the database.
     * 
     * @return all the marks in the database placed in a list
     */
    public static List<Mark> getAllMarks() {
        List<Mark> allMarks = new ArrayList<>();
        
        try {
            Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet markRS = s.executeQuery("SELECT SubAssessmentID, StudentID, MarkerID FROM SubAssessmentMark");
            
            while (markRS.next()) {
                int subAssessmentID = markRS.getInt("SubAssessmentID");
                int studentID = markRS.getInt("StudentID");
                int markerID = markRS.getInt("MarkerID");
                allMarks.add(new Mark(subAssessmentID, studentID, markerID));
            }
        } catch (java.lang.NullPointerException | SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return allMarks;
    }
    
    /**
     * Update a single row of the mark table 
     * 	- called when the save changes button is hit.
     *  - student number is omitted so that it can never be changed. 
     * @throws SQLException 
     */
    public void updateRow() throws SQLException {
    	String sql = "UPDATE SubAssessmentMark SET Mark = "+Integer.parseInt(this.value.toString())+", InsideRange = "+this.getInsideRange()+", Report = '"+this.getReport().toString()+"' WHERE MarkerID = "+Integer.parseInt(this.markerID.toString())+" and StudentID = "+Integer.parseInt(this.studentID.toString())+" and SubAssessmentID = " + this.getSubAssessmentID();
    	Statement stmt = Session.dbConn.getConnection().createStatement();
    	stmt.execute(sql);
    	stmt.close();
    }
}
