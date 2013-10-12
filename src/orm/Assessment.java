package orm;

public class Assessment extends BaseAssessment {

	public Assessment(int assessmentID) {
		super(assessmentID);
	}
	
    public Assessment(int assessmentID, int studentID, Unit unit) {
    	super(assessmentID, studentID,  unit);
    }
}
