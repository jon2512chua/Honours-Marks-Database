package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import sessionControl.Session;

public class Mark extends BaseMark {
    public Mark(int subAssessmentID, int studentID, int markerID, SubAssessment subAssessment) {
        super(subAssessmentID, studentID,  markerID, subAssessment);
    }
    
    public Mark(double value, String report, boolean insideRange, int markerID, int studentID, SubAssessment parentSubAssessment) throws SQLException {
        super(value, report, insideRange, markerID, studentID, parentSubAssessment);
    }
    
    public static List<Mark> getAllMarks() {
        List<Mark> allMarks = new ArrayList<>();
        
        try {
            Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet markRS = s.executeQuery("SELECT SubAssessmentID, StudentID, MarkerID FROM SubAssessmentMark");
            
            while (markRS.next()) {
                int subAssessmentID = markRS.getInt("SubAssessmentID");
                int studentID = markRS.getInt("StudentID");
                int markerID = markRS.getInt("MarkerID");
                allMarks.add(new Mark(subAssessmentID, studentID, markerID, new SubAssessment(subAssessmentID)));
            }
        } catch (java.lang.NullPointerException | SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return allMarks;
    }
}
