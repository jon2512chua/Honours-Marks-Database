package orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import sessionControl.Session;

public class Staff extends BaseStaff {
	public Staff(int staffID) {
		super(staffID);
	}

	public String getFullName() {
		return this.getFistName() + " " + this.getLastName();
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
}
