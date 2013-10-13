package logic;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import orm.*;

/**
 * Class to hold (static) lists of all students, all units, and all markers involved in this cohort
 */
public class CohortData
{
    public static List<Student> students;
	public static int numStudents;
    public static List<Staff> staff;
	public static int numStaff;
    public static List<Unit> units;
	public static int numUnits;
    public static List<Assessment> assessments;
	public static int numAssessments;
	public static List<SubAssessment> subassessments;
	public static int numSubssessments;
    
	public static void findStudents(){
//		students = Student.getAllStudents();
//		numStudents = students.size();	
	}

	public static void findStaff(){
//		staff = Staff.getAllStaff();
//		numStaff =staff.size();	
	}

	public static void findUnits(){
//		units = Unit.getAllUnits();
//		numUnits = units.size();	
	}

	public static void findAssessments(){
//		assessments = Assessment.getAllAssessments();
//		numAssessments = assessments.size();	
	}
	
	// Usually use connection:
	public static void writeUnit(Unit u, Connection c) throws SQLException {
		try {
			String sql = "Insert into Unit values ('" + u.getUnitCode() + "', '" + u.getName() + "', " + u.getPoints() + ")";
			System.out.println(sql); //TODO
			c.createStatement().execute(sql);
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	public static void writeAssessment(Assessment a, Connection c) throws SQLException {
		try {
			String sql = "Insert into Assessment values (" + null + ", '" + a.getName() + "', '" + a.getParentUnit().getUnitCode() + "', " + a.getUnitPercent() + ")";
			System.out.println(sql); //TODO
			c.createStatement().execute(sql);
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	public static void writeSubassessment(SubAssessment s, Connection c) throws SQLException {
		try {
			String sql = "Insert into SubAssessment values (" + null + ", '" + s.getName() + "', '" + s.getParentAssessment().getName() + "', " + s.getAssessmentPercent() + ", " + s.getMaxMark() +  ")";
			System.out.println(sql); //TODO
			c.createStatement().execute(sql);
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	public static void writeStaff(Staff s, Connection c) throws Exception {
		try {
			String sql = "Insert into Staff values (" + s.getStaffID() + ", '" + s.getFirstName() + "', '" + s.getLastName() + "')";
			System.out.println(sql); //TODO
			c.createStatement().execute(sql);
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	
}
