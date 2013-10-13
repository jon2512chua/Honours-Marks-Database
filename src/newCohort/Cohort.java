package newCohort;

import sessionControl.ConnectionWrapper;
import sessionControl.Directories;

/**
 * Object for facilitating the instantiation and population of a new cohort DB
 * @author Nicholas Abbey 20522805
 * @version 2/10/13
 *
 */
public class Cohort {
	
	/**
	 * Connection to the new database TODO make private?
	 */
	public ConnectionWrapper newConn;

	/**
	 * Constructor
	 * @param cohort eg for 2014 semester 1 -> use 20141
	 */
	public Cohort(String cohort) {
		newConn = new ConnectionWrapper();
		newConn.openConnection(Directories.dbDir + cohort + ";create=true");
	}

}
