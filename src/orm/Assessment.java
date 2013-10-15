package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import sessionControl.Session;

public class Assessment extends BaseAssessment {

    public Assessment(int assessmentID) {
        super(assessmentID);
    }
	
    public Assessment(int assessmentID, int studentID, Unit unit) {
    	super(assessmentID, studentID,  unit);
    }
    
    public static List<Assessment> getAllAssessments() {
        List<Assessment> allAssessments = new ArrayList<>();
        
        try {
            Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet assessmentRS = s.executeQuery("SELECT UnitCode FROM Student");
            
            while (assessmentRS.next()) {
                allAssessments.add(new Assessment(assessmentRS.getInt("UnitCode")));
            }
        } catch (java.lang.NullPointerException | SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return allAssessments;
    }
}
