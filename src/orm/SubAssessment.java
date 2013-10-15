package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import sessionControl.Session;

public class SubAssessment extends BaseSubAssessment {
    public SubAssessment(int subAssessmentID) {
        super(subAssessmentID);
    }

    public SubAssessment(int subAssessmentID, int studentID, Assessment assessment) {
        super(subAssessmentID, studentID, assessment);
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
}
