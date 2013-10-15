package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import logic.CohortData;
import sessionControl.Session;

public class SubAssessment extends BaseSubAssessment {
    public SubAssessment(int subAssessmentID, Assessment assessment) {
        super(subAssessmentID, assessment);
    }

    public SubAssessment(int subAssessmentID, int studentID, Assessment assessment) {
        super(subAssessmentID, studentID, assessment);
    }
    
    public SubAssessment(int subAssessmentID){
    	super(subAssessmentID);
    }
    
 
    public static List<SubAssessment> getAllSubAssessments() {
        List<SubAssessment> allSubAssessments = new ArrayList<>();
        
        try {
            Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet subAssessmentRS = s.executeQuery("SELECT SubAssessmentID FROM SubAssessment");
            
            while (subAssessmentRS.next()) {
                allSubAssessments.add(new SubAssessment(subAssessmentRS.getInt("UnitCode")));
            }
        } catch (java.lang.NullPointerException | SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return allSubAssessments;
    }
    
    /**
     * Update a single row of the SubAssessment table 
     * 	- called when the save changes button is hit.
     *  - SubAssessmentID number is omitted so that it can never be changed. 
     * @throws SQLException 
     */
    public void updateRow() throws SQLException {
    	String sql = "UPDATE SubAssessment SET SubAssessmentName = '"+this.getName().toString()+"', AssessmentID = "+this.getParentAssessment().getAssessmentID()+", AssessmentPercent = "+this.getAssessmentPercent()+", MaxMarks = "+this.getMaxMark()+" WHERE SubAssessmentID = " + this.getSubAssessmentID();
    	Statement stmt = Session.dbConn.getConnection().createStatement();
    	stmt.execute(sql);
    	stmt.close();
    }
    
    /**
     * Delete this student //TODO test
     * @throws SQLException
     */
    public void deleteRow() throws SQLException {
    	CohortData.subassessments.remove(this);
    	String sql = "DELETE from SubAssessment WHERE SubAssessmentID = " + this.getSubAssessmentID();
    	Statement stmt = Session.dbConn.getConnection().createStatement();
    	stmt.execute(sql);
    	stmt.close();
    }
    
}
