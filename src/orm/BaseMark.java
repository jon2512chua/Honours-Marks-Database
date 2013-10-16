package orm;


import logic.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import sessionControl.Session;

/**
 * Base class representing a Mark.
 * 
 * @author Jonathan Chua
 * @author Samuel Widenbar
 * @version 16/10/2013
 */
public class BaseMark implements Comparable<Object> {
    /**
     * The amount of marks, exists as a Double in the database.
     */
    public StringBuffer value = new StringBuffer (6);
    
    /**
     * The comments accompanying this mark, exists as a String in the database.
     */
    public StringBuffer report = new StringBuffer (500);
    
    /**
     * The ID of the marker who assigned the mark, exists as an Integer in the database.
     */
    public StringBuffer markerID = new StringBuffer (12);
    
    /**
     * The ID of the student this mark is assigned to, exists as an Integer in the database.
     */
    public StringBuffer studentID = new StringBuffer (10);
    
    /**
     * The ID of the sub assessment this mark belongs to.
     * 
     */
    private int subAssessmentID;
    
    /**
     * The sub assessment this mark belongs to.
     */
    public SubAssessment parentSubAssessment;
    
    /**
     * An indicator to check whether the mark is within the two standard deviation
     * range from the average.
     * 
     * Should be true by default.
     */
    private boolean insideRange;
    
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
    public BaseMark(int subAssessmentID, int studentID, int markerID, SubAssessment subAssessment) {
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet markRS = s.executeQuery("SELECT * FROM SubAssessmentMark WHERE SubAssessmentID=" + subAssessmentID +
                									"AND StudentID ="+ studentID + "AND MarkerID =" + markerID)) {
            
            // There will only be one mark returned as each (subAssessmentID, studentID, markerID) tuple is unique
            while (markRS.next()) {
                setMarkerID(markerID);
                setStudentID(studentID);
                setParentSubAssessment(subAssessment);
                //subAssessmentID = subAssessmentID;

                int inRange = markRS.getInt("InsideRange");
                if (inRange == 0){
                    setInsideRange(false);
                }
                else {
                    setInsideRange(true);
                }

                setValue(markRS.getDouble("Mark"));
                setReport(markRS.getString("Report"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseUnit.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public BaseMark(int subAssessmentID, int studentID, int markerID) {
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet markRS = s.executeQuery("SELECT * FROM SubAssessmentMark WHERE SubAssessmentID=" + subAssessmentID +
                									"AND StudentID ="+ studentID + "AND MarkerID =" + markerID)) {
            
            // There will only be one mark returned as each (subAssessmentID, studentID, markerID) tuple is unique
            while (markRS.next()) {
                setMarkerID(markerID);
                setStudentID(studentID);
                //subAssessmentID = subAssessmentID;

                int inRange = markRS.getInt("InsideRange");
                if (inRange == 0){
                    setInsideRange(false);
                }
                else {
                    setInsideRange(true);
                }

                setValue(markRS.getDouble("Mark"));
                setReport(markRS.getString("Report"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseUnit.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public BaseMark(double value, String report, boolean insideRange, int markerID, int studentID, SubAssessment parentSubAssessment) throws SQLException {
        try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            s.execute("INSERT INTO SubAssessmentMark VALUES ("
                    + value + ", "
                    + markerID + ", "
                    + studentID + ", "
                    + parentSubAssessment.getSubAssessmentID() + ", '"
                    + (getInsideRange() ? 1 : 0) +"', '"
                    + report + "')");
            
            setValue(value);
            setReport(report);
            setInsideRange(insideRange);
            setMarkerID(markerID);
            setStudentID(studentID);
            setParentSubAssessment(parentSubAssessment);
            subAssessmentID = parentSubAssessment.getSubAssessmentID();
            
            MarkCalculator.calculateStudentMarks(Student.getStudentByID(studentID+""));
            
        } catch (SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException(ex.getMessage());
        }
    }
    
    /**
     * Method that gets the amount of marks.
     * 
     * @return amount of marks
     */
    public double getValue() {
    	try {
            return Double.parseDouble(value+"");
    	} catch (java.lang.NumberFormatException e) {
            return 0;
    	}
    }
    
    /**
     * Method that sets the amount of marks.
     * 
     * @param value amount of marks
     */
    public void setValue(double value) {
    	this.value.replace(0, this.value.capacity(),  Double.toString(value));
    	this.value.setLength(5);
    }
    
    /**
     * Method that checks whether the mark is inside the accepted range.
     * 
     * @return true if the mark is inside the accepted range, false if otherwise
     */
    public boolean getInsideRange() {
        return insideRange;
    }
    
    /**
     * Method that sets whether the mark is inside the accepted range.
     * 
     * @param insideRange boolean value that indicates whether the mark is inside the accepted range
     */
    public void setInsideRange(boolean insideRange) {
        this.insideRange = insideRange;
    }
    
    /**
     * Method that gets the ID of the marker that assigned the mark.
     * 
     * @return the ID of the marker
     */
    public int getMarkerID() {
    	return Integer.parseInt(markerID+"");
    }
    
    /**
     * Method that sets the ID of the marker that assigned the mark.
     * 
     * @param markerID the ID of the marker
     */
    public void setMarkerID(int markerID) {
    	this.markerID.replace(0, this.markerID.capacity(),  Integer.toString(markerID));
    }
    
    /**
     * Method that gets the ID of the student the mark is assigned to.
     * 
     * @return the ID of the student
     */
    public int getStudentID() {
    	return Integer.parseInt(studentID+"");
    }
    
    /**
     * Method that sets the ID of the student the mark is assigned to.
     * 
     * @param studentID the ID of the student
     */
    public void setStudentID(int studentID) {
    	this.studentID.replace(0, this.studentID.capacity(),  Integer.toString(studentID));
    }
    
    /**
     * Method that gets the sub assessment this mark is assigned for.
     * 
     * @return a SubAssessment object
     */
    public SubAssessment getParentSubAssessment() {
        return parentSubAssessment;
    }
    
    /**
     * Method that sets the sub assessment this mark is assigned for.
     * 
     * @param parentSubAssessment a SubAssessment object
     */
    public void setParentSubAssessment(SubAssessment parentSubAssessment) {
        this.parentSubAssessment = parentSubAssessment;
    }
    
    /**
     * Method that gets the comments accompanying the mark.
     * 
     * @return the comment
     */
    public StringBuffer getReport() {
        return report;
    }
    
    /**
     * Method that sets the comments accompanying the mark.
     * 
     * @param report the comment
     */
    public void setReport(String report) {
    	this.report.replace(0, this.report.capacity(), report);
    }
    
    /**
     * Method that gets the ID of the sub assessment this mark is assigned for.
     * 
     * @return the ID of the sub assessment
     */
    public int getSubAssessmentID() {
    	return subAssessmentID;
    }
    
    /**
     * Method that sets the ID of the sub assessment this mark is assigned for.
     * 
     * @param subAssessmentID the ID of the sub assessment
     */
    public void setSubAssessmentID(int subAssessmentID) {
        this.subAssessmentID = subAssessmentID;
    }
    
    /**
     * Implementing the Comparable interface so that Mark objects can be compared.
     * 
     * @param otherMark the other Mark object to be compared
     * @return a negative integer, zero, or a positive integer as this Mark is less than, equal to, or greater than the specified Mark
     * @throws ClassCastException when the argument provided is not a Mark object
     */
    @Override
    public int compareTo(Object otherMark) throws ClassCastException {
        if (!(otherMark instanceof Mark)) {
            throw new ClassCastException("A Mark object expected.");
        }
        double otherValue = ((Mark) otherMark).getValue();  
        
        return (int) ((Double.parseDouble(this.value+"")) - otherValue);    
    }
}
