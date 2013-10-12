package orm;

public class SubAssessment extends BaseSubAssessment {
	public SubAssessment(int subAssessmentID) {
		super(subAssessmentID);
	}
	
	public SubAssessment(int subAssessmentID, int studentID, Assessment assessment) {
		super(subAssessmentID, studentID, assessment);
	}
}
