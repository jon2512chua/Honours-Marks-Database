package orm;


import logic.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.List;
//import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import sessionControl.Session;

public class BaseMark implements Comparable<Object> {
    public StringBuffer value = new StringBuffer (6);    
    public StringBuffer report = new StringBuffer (500);
    public StringBuffer markerID = new StringBuffer (12);
    public StringBuffer studentID = new StringBuffer (10);
    private int subAssessmentID;
    public SubAssessment parentSubAssessment;
    
    /**
     * An indicator to check whether the mark is within the two standard deviation
     * range from the average.
     * 
     * Should be true by default.
     */
    private boolean insideRange;

    
    public BaseMark(int subAssessmentID, int studentID, int markerID, SubAssessment subAssessment) {
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet markRS = s.executeQuery("SELECT * FROM SubAssessmentMark WHERE SubAssessmentID=" + subAssessmentID +
                									"AND StudentID ="+ studentID + "AND MarkerID =" + markerID)) {
            
            // There will only be one mark returned as each (subAssessmentID, studentID, markerID) tuple is unique
    		while (markRS.next()) {
            
	            setMarkerID(markerID);
	            setStudentID(studentID);
	            setParentSubAssessment(subAssessment);
	            subAssessmentID = subAssessmentID;
	            
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
    
    public BaseMark(int subAssessmentID, int studentID, int markerID) {
    	try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet markRS = s.executeQuery("SELECT * FROM SubAssessmentMark WHERE SubAssessmentID=" + subAssessmentID +
                									"AND StudentID ="+ studentID + "AND MarkerID =" + markerID)) {
            
            // There will only be one mark returned as each (subAssessmentID, studentID, markerID) tuple is unique
    		while (markRS.next()) {
            
	            setMarkerID(markerID);
	            setStudentID(studentID);
	            subAssessmentID = subAssessmentID;
	            
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
    
    public double getValue() {
    	try {
    		return Double.parseDouble(value+"");
    	} catch (java.lang.NumberFormatException e) {
    		return 0;
    	}
    }
    
    public void setValue(double value) {
    	this.value.replace(0, this.value.capacity(),  Double.toString(value));
    	this.value.setLength(5);
    }
    
    public boolean getInsideRange() {
        return insideRange;
    }
    
    public void setInsideRange(boolean insideRange) {
        this.insideRange = insideRange;
    }
    
    public int getMarkerID() {
    	return Integer.parseInt(markerID+"");
    }
    
    public void setMarkerID(int markerID) {
    	this.markerID.replace(0, this.markerID.capacity(),  Integer.toString(markerID));
    }
    
    public int getStudentID() {
    	return Integer.parseInt(studentID+"");
    }
    
    public void setStudentID(int studentID) {
    	this.studentID.replace(0, this.studentID.capacity(),  Integer.toString(studentID));
    }
    
    public SubAssessment getParentSubAssessment() {
        return parentSubAssessment;
    }
    
    public void setParentSubAssessment(SubAssessment parentSubAssessment) {
        this.parentSubAssessment = parentSubAssessment;
    }
    
    public StringBuffer getReport() {
        return report;
    }
    
    public void setReport(String report) {
    	this.report.replace(0, this.report.capacity(), report);
    }
    
    public int compareTo(Object otherMark) throws ClassCastException {
        if (!(otherMark instanceof Mark))
          throw new ClassCastException("A Mark object expected.");
        double otherValue = ((Mark) otherMark).getValue();  
        return (int) ((Double.parseDouble(this.value+"")) - otherValue);    
      }
    
    public int getSubAssessmentID() {
    	return subAssessmentID;
    }
    
    public void setSubAssessmentID(int subAssessmentID) {
        this.subAssessmentID = subAssessmentID;
    }
}
