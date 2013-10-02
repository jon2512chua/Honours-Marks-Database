package newCohort;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import sessionControl.DerbyUtils;
import sessionControl.Directories;

/**
 * Object for facilitating the instantiation and population of a new cohort DB
 * 
 * @author Nicholas Abbey 20522805
 * @version 2/10/13
 * 
 */
public class CohortCreator {

	/**
	 * Stores a Cohort object while it is being worked on
	 */
	public static Cohort newCohort;

	/**
	 * Create a new Cohort Database
	 * 
	 * @param cohort
	 *            - eg for 2014 semester 1 -> use 20141
	 * @return true if successful
	 */
	public static boolean create(String cohort) {
		boolean unique = true;
		for (String s : sessionControl.Session.getCohorts()) {
			System.out.println(s + "  " + cohort); // NLA
			unique = !(s.equals(cohort));
		}
		if (unique) {
			newCohort = new Cohort(cohort);
			installSchema();
			return true;
		} else
			return false;
	}

	/**
	 * This method is called once the setup of the new cohort is finalised
	 * 
	 * @param useNow
	 *            - true if the user wants to swap to this database immediately
	 */
	public static void finaliseSetup(boolean useNow) {
		if (useNow) {
			// TODO swap to this cohort
		} else {
			newCohort = null; // clear the newCohort, as it has already been
								// saved.
		}
	}

	// Helper method to install schema from an SQL file
	private static boolean installSchema() {
		List<String> sql = DerbyUtils.getSqlFromFile(Directories.newCohortSql);
		try {
			Statement s = CohortCreator.newCohort.newConn.getConnection()
					.createStatement();
			for (String c : sql) {
				// System.out.println(c);
				s.execute(c);
			}
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error");
			return false;
		}
	}

}
