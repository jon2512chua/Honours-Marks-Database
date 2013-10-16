package orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.CohortData;
import sessionControl.Session;

/**
 * Class representing a Staff.
 * 
 * @author Jonathan Chua
 * @author Samuel Widenbar
 * @version 16/10/2013
 */public class Staff extends BaseStaff {
     
    /**
     * Constructor to create a new java object to represent a staff found in the database.
     * 
     * @param staffID the ID of the staff
     */
    public Staff(int staffID) {
        super(staffID);
    }

    /**
     * Constructor to create a java object to represent a staff that does not exist yet in the database.
     * 
     * @param staffID the ID of the staff
     * @param firstName the first name of the staff
     * @param lastName the last name of the staff
     * @throws SQLException when there is an error with the SQL statement
     */
    public Staff(int staffID, String firstName, String lastName) throws SQLException {
        super(staffID, firstName, lastName);
    }

    /**
     * Method that combines the first and last name of the staff together.
     * 
     * @return full name of the staff
     */
    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    /**
     * Method that calculates how many marks this staff had given out.
     * 
     * @return count of marks given by this staff
     */
    public int getNumMarks() {
        return getMarks().size();
    }

    /**
     * Method that retrieves a list of every single staff found in the database.
     * 
     * @return a list of all the staff from the database
     */
    public static List<Staff> getAllStaff() {
        List<Staff> allStaff = new ArrayList<>();

        try {
            Statement s = Session.dbConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet staffsRS = s.executeQuery("SELECT * FROM Staff");

            while (staffsRS.next()) {
                allStaff.add(new Staff(staffsRS.getInt("StaffID")));
            }
        } catch (java.lang.NullPointerException | SQLException ex) {
            Logger.getLogger(BaseStaff.class.getName()).log(Level.SEVERE, null, ex);
        }

        return allStaff;
    }
    
    /**
     * Method that retrieves a staff object identified by staffID.
     * 
     * TODO: Do we really need this? Does the constructor not do the exact same thing?
     * 
     * @param ID ID of the staff
     * @return a Staff object identified by the staffID
     */
    public static Staff getStaffByID (String ID) {
        for (Staff s : Staff.getAllStaff()) {
            try {
                if (Integer.parseInt(ID) == Integer.parseInt(s.getStaffID()+"")) {
                    return s;
                }
            } catch (java.lang.NumberFormatException e) {
            }
        }
        
        return null;
    }
    
    /**
     * Update a single row of the Staff table 
     * 	- called when the save changes button is hit.
     *  - staff number is omitted so that it can never be changed. 
     * @throws SQLException 
     */
    public void updateRow() throws SQLException {
    	String sql = "UPDATE Staff SET FirstName = '"+this.getFirstName().toString()+"', LastName = '"+this.getLastName().toString()+"' WHERE StaffID = " + this.getStaffID();
    	Statement stmt = Session.dbConn.getConnection().createStatement();
    	stmt.execute(sql);
    	stmt.close();
    }
    /**
     * Delete this staff member //TODO test
     * TODO delete ops are problematic - may need to refresh data??
     * @throws SQLException
     */
    public void deleteRow() throws SQLException {
    	CohortData.staff.remove(this);
    	String sql = "DELETE from Staff WHERE StaffID = " + this.getStaffID();
    	Statement stmt = Session.dbConn.getConnection().createStatement();
    	stmt.execute(sql);
    	stmt.close();
    }
    
    /**
     * Returns a list of the assessments a marker has marked.
     * 
     * @return the list, or null if none
     */
    public List<Assessment> getAssessmentsMarked(){
    	List<Assessment> marked = new LinkedList<Assessment>();
    	if (marks != null) {
    		for (Mark m : marks) {
    			Assessment a = m.getParentSubAssessment().getParentAssessment();
    			if(!marked.contains(a)) marked.add(a);
    		}
    		return marked;
    	}
    	else return null;
    }
    
    /**
     * Returns a list of the units a marker has marked.
     * 
     * @return the list, or null if none
     */
    public List<Unit> getUnitsMarked() {
    	List<Assessment> m = getAssessmentsMarked();
    	List<Unit> marked = new LinkedList<Unit>();
    	if(m != null) {
    		for (Assessment a  : m) {
    			Unit u = a.getParentUnit();
    			if(!marked.contains(u)) marked.add(u);
    		}
    		return marked;
    	}
    	else return null;
    }
}
