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
    
    /**
     * A method for updating a single row of the Student table - this is called when the save changes button is hit.
     * Note that student number is omitted so that it can never be changed 
     * @throws SQLException 
     */
    public void updateRow() throws SQLException {
    	Session.dbConn.getConnection().createStatement().execute("" +
    			"UPDATE Student SET FirstName = '"+this.getFirstName().toString()+"', LastName = '"+this.getLastName().toString()+"', Title = '"+this.getTitle().toString()+"', DissTitle = '"+this.getDissTitle().toString()+"', Discipline = '"+this.getDisciplineName().toString()+"', Mark = "+this.getCourseMark()+", Grade = '"+this.getGrade().toString()+"' WHERE StudentID = " + this.getStudentID());	   
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
}
