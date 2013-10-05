package sessionControl;

/**
 * This class holds the relevant relative paths for the system's dependencies
 * 
 * @author Nicholas Abbey 20522805
 * @version 28/9/13
 * 
 */
public class Directories {

	/**
	 * Relative directory of System database
	 */
	public static final String systemDb = "config/System";
	/**
	 * Relative path of directory holding cohort databases
	 */
	public static final String dbDir = "db/";
	/**
	 * Relative path of directory holding backup files
	 */
	public static final String backupDir = "db/backups/";
	/**
	 * Relative path of Cohort Creation SQL file
	 */
	public static final String newCohortSql = "sql/schema.sql";

}
