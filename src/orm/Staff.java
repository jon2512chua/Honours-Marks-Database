package orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.CohortData;

import sessionControl.Session;

public class Staff extends BaseStaff {
	public Staff(int staffID) {
		super(staffID);
	}

	public String getFullName() {
		return this.getFirstName() + " " + this.getLastName();
	}

	public int getNumMarks() {
		return getMarks().size();
	}

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
	
    public static Staff getStaffByID (String ID) {
	    for (Staff s : Staff.getAllStaff()) {
	    	try {
				if (Integer.parseInt(ID) == Integer.parseInt(s.getStaffID()+"")) return s;
			} catch (java.lang.NumberFormatException e) {}
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
    
}
