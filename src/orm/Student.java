package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import sessionControl.*;

public class Student extends BaseStudent { 
    public Student(int studentID) {
        super(studentID);
    }
    
    public Student(int studentID, String firstName, String lastName, String title, String dissTitle, String disciplineName, double courseMark, String grade, List<Staff> supervisors) {
        super(studentID, firstName, lastName, title, dissTitle, disciplineName, courseMark, grade, supervisors);
    }
        
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
    
    public String getFullName() {
        String fullName = getFirstName() + " " + getLastName();
        
        return fullName;
    }
    
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
     * @author Nicholas Abbey
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
     * @param s
     * @throws SQLException 
     */
    public void addSupervisor(Staff s) throws SQLException {
    	this.supervisors.add(s);
    	String sql = "Insert into Supervises values (" + this.getStudentID() + " , " + s.getStaffID() + ")";
    	System.out.println("ROW INSERT: " + sql); //TODO delete
    	Session.dbConn.getConnection().createStatement().execute(sql);
    }
    /**
     * Delete a supervisor to this student
     * @param s
     * @throws SQLException 
     */
    public void deleteSupervisor(Staff s) throws SQLException {
    	this.supervisors.remove(s);
    	String sql = "Delete from Supervises WHERE StudentID = " + this.getStudentID() + " and StaffID = " + s.getStaffID();
    	System.out.println("ROW DELETE: " + sql); //TODO delete
    	Session.dbConn.getConnection().createStatement().execute(sql);
    }
    
    /**
     * A method for updating a single row of the Student table - this is called when the save changes button is hit.
     * Note that student number is omitted so that it can never be changed 
     * @throws SQLException 
     */
    public void updateRow() throws SQLException {
    	String sql = "UPDATE Student SET FirstName = '"+this.getFirstName().toString()+"', LastName = '"+this.getLastName().toString()+"', Title = '"+this.getTitle().toString()+"', DissTitle = '"+this.getDissTitle().toString()+"', Discipline = '"+this.getDisciplineName().toString()+"', Mark = "+this.getCourseMark()+", Grade = '"+this.getGrade().toString()+"' WHERE StudentID = " + this.getStudentID();
    	System.out.println("ROW UPDATE: " + sql); //TODO delete
    	Session.dbConn.getConnection().createStatement().execute(sql);	   
    }
    
}
