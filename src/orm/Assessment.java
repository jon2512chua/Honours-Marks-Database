package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import logic.CohortData;
import sessionControl.Session;

/**
 * Class representing an Assessment.
 * 
 * @author Jonathan Chua
 * @author Samuel Widenbar
 * @version 16/10/2013
 */
public class Assessment extends BaseAssessment {
    /**
     * Constructor to create a java object to represent an assessment found in the database.
     * 
     * @param assessmentID to identify the assessment
     */
    public Assessment(int assessmentID) {
        super(assessmentID);
    }
	
    /**
     * Constructor to create a java object to represent an assessment found in the database, belonging to the specified unit.
     * 
     * @param assessmentID to identify the assessment
     * @param unit the unit that this assessment belongs to
     */
    public Assessment(int assessmentID, Unit unit) {
        super(assessmentID, unit);
    }
	
    /**
     * Constructor to create a java object to represent an assessment found in the database, belonging to the specified unit and student.
     * 
     * @param assessmentID to identify the assessment
     * @param studentID to identify the student
     * @param unit the unit that the assessment belongs to
     */
    public Assessment(int assessmentID, int studentID, Unit unit) {
    	super(assessmentID, studentID,  unit);
    }
    
    /**
     * Constructor to create a java object to represent an assessment that doesn't exist yet in the database.
     * 
     * @param assessmentID to identify this assessment
     * @param name the name of this assessment
     * @param parentUnit the unit this assessment belongs to
     * @param unitPercent the percentage this assessment takes up in the unit
     * @throws SQLException when there is an error with the SQL statement
     */
    public Assessment(int assessmentID, String name, Unit parentUnit, int unitPercent) throws SQLException {
        super(assessmentID, name, parentUnit, unitPercent);
    }
    
    /**
     * Method to get a list of all the assessments in the database.
     * 
     * @return a list of all the assessments
     */
    public static List<Assessment> getAllAssessments() {
        List<Assessment> allAssessments = new ArrayList<>();
        
        try {
            Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet assessmentRS = s.executeQuery("SELECT AssessmentID FROM Assessment");
            
            while (assessmentRS.next()) {
                allAssessments.add(new Assessment(assessmentRS.getInt("AssessmentID")));
            }
        } catch (java.lang.NullPointerException | SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return allAssessments;
    }
	
    /**
     * return assessment detail of a specific unit code and assessment name
     * @param unitCode the specific unit code
     * @return the Assessment class of the specific assessment
     */
    public static Assessment getAssessByCodeAndName (String unitCode, String assessmentName) {
    	for (Assessment a : Assessment.getAllAssessments()) {
    		try {if (unitCode.equals(a.getParentUnit().getUnitCode()+"") && assessmentName.equals(a.getName()+"")) return a;
    		} catch (java.lang.NumberFormatException e) {}
    	}
    	return null;
    }

    /**
     * return current assessmentID
     * @return the current assessmentID
     */
    public static int getMaxAssessID () {
    	try {
    		String sql = "Select max(assessmentID) from Assessment";
    		Statement s = Session.dbConn.getConnection().createStatement();
    		ResultSet r = s.executeQuery(sql);
    		r.next();
    		int assessID = r.getInt("assessmentID");
    		r.close();
    		s.close();
    		return assessID;
    	} catch (SQLException ex) {}
    	return 0;
    }

    /**
     * Update a single row of the Assessment table 
     * 	- called when the save changes button is hit.
     *  - AssessmentID is omitted so that it can never be changed. 
     * @throws SQLException 
     */
    public void updateRow() throws SQLException {
    	String sql = "UPDATE Student SET AssessmentName = '"+this.getName().toString()+"', UnitCode = '"+this.getParentUnit().toString()+"', UnitPercent = '"+this.getUnitPercent()+"' WHERE ssessmentID = " + this.getAssessmentID();
    	Statement stmt = Session.dbConn.getConnection().createStatement();
    	stmt.execute(sql);
    	stmt.close();
    }

    /**
     * Delete this unit
     * @throws SQLException
     */
    public void deleteRow() throws SQLException {
    	CohortData.units.remove(this);
    	String sql = "DELETE from Assessment WHERE AssessmentID = " + this.getAssessmentID();
    	Statement stmt = Session.dbConn.getConnection().createStatement();
    	stmt.execute(sql);
    	stmt.close();
    }
}