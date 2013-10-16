package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import logic.CohortData;
import sessionControl.*;

/**
 * Class representing a Student.
 * 
 * @author Jonathan Chua
 * @author Samuel Widenbar
 * @author Nicholas Abbey
 * @version 16/10/2013
 */
public class Student extends BaseStudent { 
    /**
     * Constructor to create a java object to represent a student found in the database.
     * 
     * @param studentID is the studentID of the student being searched for
     */
    public Student(int studentID) {
        super(studentID);
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
    public Student(int studentID, String firstName, String lastName, String title, String dissTitle, String disciplineName, double courseMark, String grade, List<Staff> supervisors) throws SQLException {
        super(studentID, firstName, lastName, title, dissTitle, disciplineName, courseMark, grade, supervisors);
    }
    
    /**
     * Method to get a list of all the students in the database.
     * 
     * @return an ArrayList of all the students in the database
     */
    public static List<Student> getAllStudents() {
        List<Student> allStudents = new ArrayList<>();
        
        try {
            Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet studentsRS = s.executeQuery("SELECT StudentID FROM Student");
            
            while (studentsRS.next()) {
                allStudents.add(new Student(studentsRS.getInt("StudentID")));
            }
        } catch (java.lang.NullPointerException | SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return allStudents;
    }
    
    /**
     * Method to get a string containing all the supervisors.
     * 
     * @return string of supervisors, separated by a comma
     */
    public String getCommaSeparatedSupervisorString() {
        String csSupervisorStr = "";
        
        for (Staff supervisor : getSupervisors()) {
            csSupervisorStr += supervisor.getFullName() + ", ";
        }
        
        csSupervisorStr.trim();
        // TODO: Doing it this way temporarily to get things to show. Might want to revise.
        csSupervisorStr = csSupervisorStr.substring(0, csSupervisorStr.length() - 2);
        
        return csSupervisorStr;
    }
    
    /**
     * Method to get the full name of the student.
     * 
     * @return full name of the student
     */
    public String getFullName() {
        String fullName = getFirstName() + " " + getLastName();
        
        return fullName;
    }
    
    /**
     * Method to get a student identified by the ID.
     * 
     * TODO: Is this really needed? Does the constructor not serve the same purpose?
     * This way is also more inefficient as it does a brute force search instead of letting the database handle it.
     * 
     * @param ID the ID identifying the student
     * @return 
     */
    public static Student getStudentByID (String ID) {
    	for (Student s : Student.getAllStudents()) {
    		try {
			if (Integer.parseInt(ID) == Integer.parseInt(s.getStudentID()+"")) return s;
    		} catch (java.lang.NumberFormatException e) {}
		}
    	return null;
    }
    
    /**
     * Check if a Student is supervised by a certain staff member
     * 
     * @param supID the id of a supervisor to be found
     * @return
     */
    public boolean hasSupervisor (int supID) {
    	for (Staff s : this.supervisors) {
    		if(s.getStaffID() == supID) return true;
    	}
    	return false;
    }
    /**
     * Add a supervisor to this student
     * @param s - supervisor to add (Staff Object)
     * @throws SQLException 
     */
    public void addSupervisor(Staff s) throws SQLException {
    	this.supervisors.add(s);
    	String sql = "Insert into Supervises values (" + this.getStudentID() + " , " + s.getStaffID() + ")";
    	Statement stmt = Session.dbConn.getConnection().createStatement();
    	stmt.execute(sql);
    	stmt.close();
    }
    /**
     * Delete a supervisor to this student
     * @param s
     * @throws SQLException 
     */
    public void deleteSupervisor(Staff s) throws SQLException {
    	this.supervisors.remove(s);
    	String sql = "Delete from Supervises WHERE StudentID = " + this.getStudentID() + " and StaffID = " + s.getStaffID()	;
    	Statement stmt = Session.dbConn.getConnection().createStatement();
    	stmt.execute(sql);
    	stmt.close();
    }
    
    /**
     * Update a single row of the Student table 
     * 	- called when the save changes button is hit.
     *  - student number is omitted so that it can never be changed. 
     * @throws SQLException 
     */
    public void updateRow() throws SQLException {
    	String sql = "UPDATE Student SET FirstName = '"+this.getFirstName().toString()+"', LastName = '"+this.getLastName().toString()+"', Title = '"+this.getTitle().toString()+"', DissTitle = '"+this.getDissTitle().toString()+"', Discipline = '"+this.getDisciplineName().toString()+"', Mark = "+this.getCourseMark()+", Grade = '"+this.getGrade().toString()+"' WHERE StudentID = " + this.getStudentID();
    	Statement stmt = Session.dbConn.getConnection().createStatement();
    	stmt.execute(sql);
    	stmt.close();
    }
    
    /**
     * Delete this student //TODO test
     * @throws SQLException
     */
    public void deleteRow() throws SQLException {
    	CohortData.students.remove(this);
    	String sql = "DELETE from Student WHERE StudentID = " + this.getStudentID();
    	Statement stmt = Session.dbConn.getConnection().createStatement();
    	stmt.execute(sql);
    	stmt.close();
    }
    
}
