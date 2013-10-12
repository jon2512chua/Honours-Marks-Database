package orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import sessionControl.Session;

public class BaseMark {
    public StringBuffer value = new StringBuffer (30);    
    public StringBuffer report = new StringBuffer (30);
    public StringBuffer markerID = new StringBuffer (30);
    public StringBuffer studentID = new StringBuffer (30);
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
                ResultSet markRS = s.executeQuery("SELECT * FROM SubAssessmentMark WHERE SubAssessment=" + subAssessmentID +
                									"&& StudentID ="+ studentID + "&& MarkerID =" + markerID)) {
            
            // There will only be one mark returned as each (subAssessmentID, studentID, markerID) tuple is unique
    		markRS.first();
            
            setMarkerID(markerID);
            setStudentID(studentID);
            setParentSubAssessment(subAssessment);
            
            
            setValue(markRS.getInt("Mark"));
            setReport(markRS.getString("Report"));
            
        } catch (SQLException ex) {
            Logger.getLogger(BaseUnit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public double getValue() {
    	return Double.parseDouble(value+"");
    }
    
    public void setValue(double value) {
    	this.value.replace(0, this.value.length(),  Double.toString(value));
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
    	this.markerID.replace(0, this.markerID.length(),  Integer.toString(markerID));
    }
    
    public int getStudentID() {
    	return Integer.parseInt(studentID+"");
    }
    
    public void setStudentID(int studentID) {
    	this.studentID.replace(0, this.studentID.length(),  Integer.toString(studentID));
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
    	this.report.replace(0, this.report.length(), report);
    }
}
