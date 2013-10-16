package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import logic.MarkCalculator;
import sessionControl.*;

/**
 * Base class representing a Student.
 * 
 * @author Jonathan Chua
 * @author Samuel Widenbar
 * @version 16/10/2013
 */
public class BaseStudent {
	/**
     * Primary key identifying the student, exists as an Integer in the database.
     */
    public StringBuffer studentID = new StringBuffer (10);
    
    /**
     * First name of the student, exists as a String in the database.
     */
    public StringBuffer firstName = new StringBuffer (30);
    
    /**
     * Last name of the student, exists as a String in the database.
     */
    public StringBuffer lastName = new StringBuffer (30);
    
    /**
     * Title of the student, exists as a String in the database.
     */
    public StringBuffer title = new StringBuffer (6);
    
    /**
     * Dissertation title the student is working on, exists as a String in the database.
     */
    public StringBuffer dissTitle = new StringBuffer (300);
    
    /**
     * The staff supervising the student.
     */
    public List<Staff> supervisors;
    
    /**
     * The mark the student got for the course he/she is enrolled for.
     */
    public StringBuffer courseMark = new StringBuffer (6);
    
    /**
     * The grade the student got for the course.
     */
    public StringBuffer grade = new StringBuffer (3);
    
    /**
     * The name of the discipline the student is enrolled in.
     */
    public StringBuffer disciplineName = new StringBuffer (20);
    
    /**
     * The unit combination that makes up the discipline the student is enrolled in.
     */
    public List<Unit> discipline = Collections.<Unit>emptyList();
    
    /**
     * Constructor to create a java object to represent a student found in the database.
     * 
     * @param studentID is the studentID of the student being searched for
     */
    public BaseStudent(int studentID) {
        try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet studentRS = s.executeQuery("SELECT * FROM Student WHERE StudentID=" + studentID)) {
            
            // There will only be one student returned as studentID is unique.
        	while (studentRS.next()) {
            
	            setStudentID(studentID);
	            setFirstName(studentRS.getString("FirstName"));
	            setLastName(studentRS.getString("LastName"));
	            setTitle(studentRS.getString("Title"));
	            setDissTitle(studentRS.getString("DissTitle"));
	            setSupervisors(getStaffList(studentID));
	            setCourseMarks(studentRS.getDouble("Mark"));
	            setGrade(studentRS.getString("Grade"));
	            setDiscipline(getUnitListByStudentID(studentID));
	            
	            MarkCalculator.calculateStudentMarks((Student)this); /// TAKE ME OUT
        	}
        	
        } catch (SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
	/**
	 * Constructor to create java object for a student that doesn't exist yet, and create
	 * a row for it in the database.
	 * 
	 * @param studentID student's ID number
	 * @param firstName student's first name
	 * @param lastName student's last name
	 * @param title student's title (ie Mr, Ms, etc)
	 * @param dissTitle student's dissertation title
	 * @param disciplineName name of student's discipline
	 * @param courseMark mark student has for his course
	 * @param grade grade student has for his course
	 * @param supervisors list of student's supervisors
	 * @throws SQLException if insert into table fails (ie. invalid student number given)
	 */
    public BaseStudent(int studentID, String firstName, String lastName, String title, String dissTitle, String disciplineName, double courseMark, String grade, List<Staff> supervisors) throws SQLException {
        try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            s.execute("INSERT INTO Student VALUES (" + studentID + ", '" + firstName + "', '" + lastName + "', '" + title + "', '" + dissTitle + "', '" + disciplineName + "', " + courseMark + ", '" + grade + "')");
            
            ListIterator<Staff> iterator = supervisors.listIterator();
            while (iterator.hasNext()) {
                int staffID = iterator.next().getStaffID();
                
                s.execute("INSERT INTO Supervises VALUES (" + studentID + ", " + staffID + ")");
            }
            
            setStudentID(studentID);
            setFirstName(firstName);
            setLastName(lastName);
            setTitle(title);
            setDissTitle(dissTitle);
            setSupervisors(supervisors);
            setCourseMarks(courseMark);
            setGrade(grade);
            setDisciplineName(disciplineName);
            setDiscipline(getUnitListByDisciplineName(disciplineName));
        } catch (SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException(ex.getMessage());
        }
    }
    
    /**
     * Method to get the student ID.
     * 
     * @return the ID identifying the student
     */
    public int getStudentID() {
        return Integer.parseInt(studentID+"");
    }
    
    /**
     * Method to set the student ID.
     * 
     * @param studentID the ID identifying the student
     */
    public void setStudentID(int studentID) {
    	this.studentID.replace(0, this.studentID.capacity(),  Integer.toString(studentID));
    }
    
    /**
     * Method to get the first name.
     * 
     * @return the first name of the student
     */
    public StringBuffer getFirstName() {
        return firstName;
    }
    
    /**
     * Method to set the first name.
     * 
     * @param firstName the first name of the student
     */
    public void setFirstName(String firstName) {
    	this.firstName.replace(0, this.firstName.capacity(), firstName);
    }
    
    /**
     * Method to get the last name of the student.
     * 
     * @return the last name of the student
     */
    public StringBuffer getLastName() {
        return lastName;
    }
    
    /**
     * Method to set the last name of the student.
     * 
     * @param lastName the last name of the student
     */
    public void setLastName(String lastName) {
    	this.lastName.replace(0, this.lastName.capacity(), lastName);
    }
    
    /**
     * Method to get the title of the student.
     * 
     * @return the title of the student (e.g. Mr., Mrs., etc.)
     */
    public StringBuffer getTitle() {
        return title;
    }
    
    /**
     * Method to set the title of the student.
     * 
     * @param title the title of the student (e.g. Mr., Mrs., etc.)
     */
    public void setTitle(String title) {
    	this.title.replace(0, this.title.capacity(), title);
    }
    
    /**
     * Method to get the dissertation title of the student.
     * 
     * @return the title of the dissertation the student is working on
     */
    public StringBuffer getDissTitle() {
        return dissTitle;
    }
    
    /**
     * Method to set the dissertation title of the student.
     * 
     * @param dissTitle the title of the dissertation the student is working on
     */
    public void setDissTitle(String dissTitle) {
    	this.dissTitle.replace(0, this.dissTitle.capacity(), dissTitle);
    }
    
    /**
     * Method to get the supervisors.
     * 
     * @return the list of staff supervising the student
     */
    public List<Staff> getSupervisors() {
        return supervisors;
    }
    
    /**
     * Method to set the supervisors.
     * 
     * @param supervisors the list of staff supervising the student
     */
    public void setSupervisors(List<Staff> supervisors) {
        this.supervisors = supervisors;
    }
    
    /**
     * Method to get the course mark.
     * 
     * @return the mark the student got for the course he/she enrolled for
     */
    public double getCourseMark() {
    	return Double.parseDouble(courseMark+"");
    }
    
    /**
     * Method to set the course mark.
     * 
     * @param courseMark the mark the student got for the course he/she enrolled for
     */
    public void setCourseMarks(double courseMark) {
    	this.courseMark.replace(0, this.courseMark.capacity(),  Double.toString(courseMark));
    	this.courseMark.setLength(5);
    }
    
    /**
     * Method to get the grade.
     * 
     * @return the grade the student got for the course
     */
    public StringBuffer getGrade() {
        return grade;
    }
    
    /**
     * Method to set the grade.
     * 
     * @param grade the grade the student got for the course
     */
    public void setGrade(String grade) {
    	try {
    		this.grade.replace(0, this.grade.capacity(), grade);
    	} catch (java.lang.NullPointerException e) {
    		this.grade.replace(0, this.grade.capacity(), "0");
    	}
    }
    
    /**
     * Method to get the discipline name.
     * 
     * @return the name of the discipline the student is enrolled in
     */
    public StringBuffer getDisciplineName() {
        return disciplineName;
    }
    
    /**
     * Method to set the discipline name.
     * 
     * @param disciplineName the name of the discipline the student is enrolled in
     */
    public void setDisciplineName(String disciplineName) {
    	this.disciplineName.replace(0, this.disciplineName.capacity(), disciplineName);
    }
    
    /**
     * Method to get the discipline.
     * 
     * @return a list of units making up the discipline the student is enrolled for
     */
    public List<Unit> getDiscipline() {
        return discipline;
    }
    
    /**
     * Method to set the discipline.
     * 
     * @param discipline a list of units making up the discipline the student is enrolled for
     */
    public void setDiscipline(List<Unit> discipline) {
        this.discipline = discipline;
    }
    
    /**
     * Private helper method to get a list of staff supervising a student.
     * 
     * @param studentID the relevant student
     * @return the list of staff
     */
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
    
    /**
     * Private helper method to get a list of units comprising the discipline the student is enrolled in.
     * 
     * @param studentID the relevant student
     * @return a list of units comprising the discipline
     */
    private List<Unit> getUnitListByStudentID(int studentID) {
        List<Unit> unitsList = new ArrayList<>();
        
        try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet unitsRS = s.executeQuery("SELECT * FROM Student AS s JOIN Discipline AS d ON s.Discipline=d.DisciplineName WHERE s.StudentID=" + studentID)) {
            
            while (unitsRS.next()) {
                unitsList.add(new Unit(unitsRS.getString("UnitCode"), studentID));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return unitsList;
    }
    
    /**
     * Private helper method to get a list of units given the discipline name.
     * 
     * @param disciplineName the name of the discipline
     * @return the list of units for said discipline
     */
    private List<Unit> getUnitListByDisciplineName(String disciplineName) {
        List<Unit> unitsList = new ArrayList<>();
        
        try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet unitsRS = s.executeQuery("SELECT * FROM Discipline WHERE DisciplineName='" + disciplineName + "'")) {
            
            while (unitsRS.next()) {
                unitsList.add(new Unit(unitsRS.getString("UnitCode")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return unitsList;
    }
}
