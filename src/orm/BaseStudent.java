package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import logic.MarkCalculator;
import sessionControl.*;


public class BaseStudent {
	
    public StringBuffer studentID = new StringBuffer (10);
    public StringBuffer firstName = new StringBuffer (30);
    public StringBuffer lastName = new StringBuffer (30);
    public StringBuffer title = new StringBuffer (6);
    public StringBuffer dissTitle = new StringBuffer (300);
    public List<Staff> supervisors;
    public StringBuffer courseMark = new StringBuffer (6);
    public StringBuffer grade = new StringBuffer (3);
    public StringBuffer disciplineName = new StringBuffer (20);
    public List<Unit> discipline = Collections.<Unit>emptyList();
    
    /**
     * Method to create a java object to represent a student found in the database
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
	 * Method to create java object for a student that doesn't exist yet, and create
	 * a row for it in the database
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
    
    public int getStudentID() {
        return Integer.parseInt(studentID+"");
    }
    
    public void setStudentID(int studentID) {
    	this.studentID.replace(0, this.studentID.capacity(),  Integer.toString(studentID));
    }
    
    public StringBuffer getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
    	this.firstName.replace(0, this.firstName.capacity(), firstName);
    }
    
    public StringBuffer getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
    	this.lastName.replace(0, this.lastName.capacity(), lastName);
    }
    
    public StringBuffer getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
    	this.title.replace(0, this.title.capacity(), title);
    }
    
    public StringBuffer getDissTitle() {
        return dissTitle;
    }
    
    public void setDissTitle(String dissTitle) {
    	this.dissTitle.replace(0, this.dissTitle.capacity(), dissTitle);
    }
    
    public List<Staff> getSupervisors() {
        return supervisors;
    }
    
    public void setSupervisors(List<Staff> supervisors) {
        this.supervisors = supervisors;
    }
    
    public double getCourseMark() {
    	return Double.parseDouble(courseMark+"");
    }
    
    public void setCourseMarks(double courseMark) {
    	this.courseMark.replace(0, this.courseMark.capacity(),  Double.toString(courseMark));
    	this.courseMark.setLength(5);
    }
    
    public StringBuffer getGrade() {
        return grade;
    }
    
    public void setGrade(String grade) {
    	try {
    		this.grade.replace(0, this.grade.capacity(), grade);
    	} catch (java.lang.NullPointerException e) {
    		this.grade.replace(0, this.grade.capacity(), "0");
    	}
    }
    
    public StringBuffer getDisciplineName() {
        return disciplineName;
    }
    
    public void setDisciplineName(String disciplineName) {
    	this.disciplineName.replace(0, this.disciplineName.capacity(), disciplineName);
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
