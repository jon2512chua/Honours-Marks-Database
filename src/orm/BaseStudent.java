package orm;

import java.util.List;

public class BaseStudent {
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
}
