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
    
	/**
	 * load all data upon db opening
	 * Order of these method calls is important due to dependencies
	 */
	public static void loadData() {
		loadStaff();
		loadUnits();
		loadAssessments();
		loadSubassessments();
		loadStudents();
//		loadStaff();
//		loadMarks() etc....
	}
	
	/**
	 * Load all students from the database on opening
	 */
	private static void loadStudents(){
		students = Student.getAllStudents();
		numStudents = students.size();	
	}
	/**
	 * Load all staff from the database on opening
	 */
	private static void loadStaff(){
		staff = Staff.getAllStaff();
		numStaff = staff.size();	
	}
	/**
	 * Load all units from the database on opening
	 */
	private static void loadUnits(){
//		units = Unit.getAllUnits();
//		numUnits = units.size();	
	}
	/**
	 * Load all assessments from the database on opening
	 */
	private static void loadAssessments(){
//		assessments = Assessment.getAllAssessments();
//		numAssessments = assessments.size();	
	}
	/**
	 * Load all subassessments from the database on opening
	 */
	private static void loadSubassessments(){
//		subassessments = Subassessment.getAllSubassessments();
//		numSubassessments = Subassessments.size();	
	}
	
	/**
	 * Write an already existent unit to a new database
	 * @param u - the unit taken from CohortData.units
	 * @param c - the connection to the new database
	 * @throws SQLException
	 */
	public static void writeUnit(Unit u, Connection c) throws SQLException {
		try {
			String sql = "Insert into Unit values ('" + u.getUnitCode() + "', '" + u.getName() + "', " + u.getPoints() + ")";
			System.out.println(sql); //TODO
			c.createStatement().execute(sql);
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	/**
	 * Write an already existent assessment to a new database
	 * @param a - the assessment taken from CohortData.units
	 * @param c - the connection to the new database
	 * @throws SQLException
	 */
	public static void writeAssessment(Assessment a, Connection c) throws SQLException {
		try {
			String sql = "Insert into Assessment values (" + null + ", '" + a.getName() + "', '" + a.getParentUnit().getUnitCode() + "', " + a.getUnitPercent() + ")";
			System.out.println(sql); //TODO
			c.createStatement().execute(sql);
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	/**
	 * Write an already existent subassessment to a new database
	 * @param s - the subassessment taken from CohortData.units
	 * @param c - the connection to the new database
	 * @throws SQLException
	 */
	public static void writeSubassessment(SubAssessment s, Connection c) throws SQLException {
		try {
			String sql = "Insert into SubAssessment values (" + null + ", '" + s.getName() + "', '" + s.getParentAssessment().getName() + "', " + s.getAssessmentPercent() + ", " + s.getMaxMark() +  ")";
			System.out.println(sql); //TODO
			c.createStatement().execute(sql);
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	/**
	 * Write an already existent staff to a new database
	 * @param s - the staff taken from CohortData.units
	 * @param c - the connection to the new database
	 * @throws SQLException
	 */
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
