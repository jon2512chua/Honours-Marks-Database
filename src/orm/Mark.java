package orm;

import java.sql.SQLException;

public class Mark extends BaseMark {
    public Mark(int subAssessmentID, int studentID, int markerID, SubAssessment subAssessment) {
        super(subAssessmentID, studentID,  markerID, subAssessment);
    }
    
    public Mark(double value, String report, boolean insideRange, int markerID, int studentID, SubAssessment parentSubAssessment) throws SQLException {
        super(value, report, insideRange, markerID, studentID, parentSubAssessment);
    }
}
