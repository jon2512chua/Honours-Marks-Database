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
	 * Relative path of System database
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
	public static final String newCohortSql = "sql/Honours_schema.sql";
	/**
	 * Relative path of Dummy Data SQL file TODO remove
	 */
	public static final String dummyData = "sql/dummy_data.sql";
	
	

}
