package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import sessionControl.*;
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
    private String firstName;
    private String lastName;
    private String title;
    private String dissTitle;
    private List<Staff> supervisors;
    private double courseMark;
    private String grade;
    private List<Unit> discipline;
    
    public BaseStudent(int studentID) {
        try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet studentRS = s.executeQuery("SELECT * FROM Student WHERE StudentID=" + studentID)) {
            
            // There will only be one student returned as studentID is unique.
            studentRS.first();
            
            this.studentID = studentID;
            this.firstName = studentRS.getString("FirstName");
            this.lastName = studentRS.getString("LastName");
            this.title = studentRS.getString("Title");
            this.dissTitle = studentRS.getString("DissTitle");
            this.supervisors = getStaffList(studentID);
            this.courseMark = studentRS.getDouble("Mark");
            this.grade = studentRS.getString("Grade");
            this.discipline = getUnitsList(studentID);
        } catch (SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
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
    
    public double getCourseMark() {
        return courseMark;
    }
    
    public void setCourseMarks(double courseMark) {
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
    
    private List<Staff> getStaffList(int studentID) {
        List<Staff> staffList = new ArrayList<>();
        
        try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet staffRS = s.executeQuery("SELECT * FROM Supervises WHERE StudentID=" + studentID)) {
            
            while (staffRS.next()) {
                staffList.add(new Staff(staffRS.getInt("StaffID")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return staffList;
    }
    
    private List<Unit> getUnitsList(int studentID) {
        List<Unit> unitsList = new ArrayList<>();
        
        try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet unitsRS = s.executeQuery("SELECT * FROM Student AS s JOIN Discipline AS d ON s.Discipline=d.DisciplineName WHERE s.StudentID=" + studentID)) {
            
            while (unitsRS.next()) {
                unitsList.add(new Unit(unitsRS.getString("UnitCode")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return unitsList;
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
