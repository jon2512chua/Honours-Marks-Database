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
        } catch (SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return allStudents;
    }
    
    public String getFullName() {
        String fullName = getFirstName() + getLastName();
        
        return fullName;
    }
}
