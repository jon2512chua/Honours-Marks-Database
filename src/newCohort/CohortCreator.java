package newCohort;

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
	public static Cohort newCohort = null;

	/**
	 * Create a new Cohort Database
	 * 
	 * @param cohort
	 *            - eg for 2014 semester 1 -> use 20141
	 * @return true if successful
	 */
	public static boolean create(String cohort) {
		if(newCohort == null) {
			//Check that cohort doesn't already exist
			boolean unique = true;
			for (String s : sessionControl.Session.getCohorts()) {
				unique = !(s.equals(cohort));
			}
			if (unique) {
				newCohort = new Cohort(cohort);
				return DerbyUtils.runSqlFromFile(Directories.newCohortSql, newCohort.newConn);
			} else
				System.err.println("WARNING: Cannot create cohort " + cohort.substring(0, 4) + " Sem " + cohort.substring(4) + " because it already exists.");
				return false;
		}
		else {
			System.err.println("WARNING: a new cohort is already open - could not instantiate another.");
			return false;
		}
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
	
	/**
	 * TODO
	 * @return
	 */
	public static boolean installData() {
		if (!(newCohort == null)) return DerbyUtils.runSqlFromFile(Directories.dummyData, newCohort.newConn);
		else {
			System.err.println("WARNING: could not add data as new cohort not created.");
			return false;
		}
	}
	
}
