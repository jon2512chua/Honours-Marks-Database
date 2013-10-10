package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import sessionControl.*;

public class Student extends BaseStudent { 
    public Student(int studentID) {
        super(studentID);
    }
    
    public static List<Student> getAllStudents() {
        List<Student> allStudents = new ArrayList<>();
        
        try {
            Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet studentsRS = s.executeQuery("SELECT * FROM Student");
            
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
			if (Integer.parseInt(ID) == s.getStudentID()) return s;
    		} catch (java.lang.NumberFormatException e) {}
		}
    	return null;
    }
}
