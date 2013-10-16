package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import sessionControl.Session;

public class Assessment extends BaseAssessment {

	 public Assessment(int assessmentID) {
	        super(assessmentID);
	    }
	 
    public Assessment(int assessmentID, Unit unit) {
        super(assessmentID, unit);
    }
	
    public Assessment(int assessmentID, int studentID, Unit unit) {
    	super(assessmentID, studentID,  unit);
    }
    
    public Assessment(int assessmentID, String name, Unit parentUnit, int unitPercent) throws SQLException {
        super(assessmentID, name, parentUnit, unitPercent);
    }
    
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
}
