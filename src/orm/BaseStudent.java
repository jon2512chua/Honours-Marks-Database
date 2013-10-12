package orm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BaseStudent {
	/**
	 * These are keys for disciplines 
	 */
	public final static Map<String, String> discKeys;
	static
	{
		discKeys = new HashMap<String, String>();
		discKeys.put("a", "ANHB");
		discKeys.put("p", "PHYL");
		discKeys.put("n", "NEURO");
		discKeys.put("b", "BIOMS");
		discKeys.put("ANHB", "ANHB");
		discKeys.put("PHYL", "PHYL");
		discKeys.put("NEURO", "NEURO");
		discKeys.put("BIOMS", "BIOMS"); 
	}
	
	
	private int studentID;
    private int cohort;
    private String firstName;
    private String lastName;
    private String dissTitle;
    private List<Staff> supervisors;
    private int courseMark;
    private String grade;
    private List<Unit> discipline;
    
    public BaseStudent() {
        //Initialise database connection and fill up the variables.
        //What if we're creating a new one?
    }
    
    /**
     * Constructor for initialising a new student from import
     */
    public BaseStudent(int sID, String disc, String ln, String fn, String dissTit, List<String> supers) {
        //Initialise database connection and fill up the variables.
        //What if we're creating a new one?
    	studentID = sID;
		discipline = getUnitsByDisc(discKeys.get(disc));
		lastName = ln;
		firstName = fn;
		dissTitle = dissTit;
		supervisors = getSupervisorsByID(supers);
    	
    }
    
    public int getStudentID() {
        return studentID;
    }
    
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
    
    public int getCohortYear() {
        return cohort;
    }
    
    public void setCohortYear(int cohort) {
        this.cohort = cohort;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getDissTitle() {
        return dissTitle;
    }
    
    public void setDissTitle(String dissTitle) {
        this.dissTitle = dissTitle;
    }
    
    public List<Staff> getSupervisors() {
        return supervisors;
    }
    
    public void setSupervisors(List<Staff> supervisors) {
        this.supervisors = supervisors;
    }
    
    public int getCourseMark() {
        return courseMark;
    }
    
    public void setCourseMarks(int courseMark) {
        this.courseMark = courseMark;
    }
    
    public String getGrade() {
        return grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    public List<Unit> getDiscipline() {
        return discipline;
    }
    
    public void setDiscipline(List<Unit> discipline) {
        this.discipline = discipline;
    }
    
    /**
     * TODO implement findUnitsFromDisc
     * @param disc
     * @return
     */
    private List<Unit> getUnitsByDisc(String disc) {
    	return new LinkedList<Unit>();
    }
    
    private List<Staff> getSupervisorsByID(List<String> supers) {
    	return new LinkedList<Staff>();
    }
    
}
