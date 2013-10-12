package orm;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import sessionControl.*;

public class BaseStudent {
	
    public StringBuffer studentID;
    public StringBuffer firstName;
    public StringBuffer lastName;
    public StringBuffer title;
    public StringBuffer dissTitle;
    public List<Staff> supervisors;
    public StringBuffer courseMark;
    public StringBuffer grade;
    public StringBuffer disciplineName;
    public List<Unit> discipline;
    
    public BaseStudent(int studentID) {
        try (Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet studentRS = s.executeQuery("SELECT * FROM Student WHERE StudentID=" + studentID)) {
            
            // There will only be one student returned as studentID is unique.
            studentRS.first();
            
            setStudentID(studentID);
            setFirstName(studentRS.getString("FirstName"));
            setLastName(studentRS.getString("LastName"));
            setTitle(studentRS.getString("Title"));
            setDissTitle(studentRS.getString("DissTitle"));
            setSupervisors(getStaffList(studentID));
            setCourseMarks(studentRS.getDouble("Mark"));
            setGrade(studentRS.getString("Grade"));
            this.discipline = getUnitsList(studentID);
        } catch (SQLException ex) {
            Logger.getLogger(BaseStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public BaseStudent(int studentID, String firstName, String lastName, String title, String dissTitle, String disciplineName, double courseMark, String grade, List<Staff> supervisors) {
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
        }
    }
    
    public int getStudentID() {
        return Integer.parseInt(studentID+"");
    }
    
    public void setStudentID(int studentID) {
    	this.studentID.replace(0, this.studentID.length(),  Integer.toString(studentID));
    }
    
    public StringBuffer getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
    	this.firstName.replace(0, this.firstName.length(), firstName);
    }
    
    public StringBuffer getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
    	this.lastName.replace(0, this.lastName.length(), lastName);
    }
    
    public StringBuffer getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
    	this.title.replace(0, this.title.length(), title);
    }
    
    public StringBuffer getDissTitle() {
        return dissTitle;
    }
    
    public void setDissTitle(String dissTitle) {
    	this.dissTitle.replace(0, this.dissTitle.length(), dissTitle);
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
    	this.courseMark.replace(0, this.courseMark.length(),  Double.toString(courseMark));
    }
    
    public StringBuffer getGrade() {
        return grade;
    }
    
    public void setGrade(String grade) {
    	this.grade.replace(0, this.grade.length(), grade);
    }
    
    public StringBuffer getDisciplineName() {
        return disciplineName;
    }
    
    public void setDisciplineName(String disciplineName) {
    	this.disciplineName.replace(0, this.disciplineName.length(), disciplineName);
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
