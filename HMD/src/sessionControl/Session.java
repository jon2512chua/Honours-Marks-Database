package sessionControl;

import java.io.File;
import java.io.FilenameFilter;
import java.sql.*;

/**
 * This static class holds information about the current session
 * @author Nicholas Abbey 20522805
 * @version 22/09/13
 */
public class Session {

	//@todo remove these lines
	private static String un = "";       
	private static String pwHash = "";

	/**
	 * System database information
	 */
	public static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String systemDBRel = "config/System";
	public static String systemAbs = (new File(systemDBRel)).toURI().getPath().toString();	//Convert relative path to absolute path
	public static final String systemDB = systemAbs;
	//public static final String systemDB = "/Users/nickos/SkyDrive/UWA2/CITS3200/Honours-Marks-Database/config/System";
	//public static final String dbUser = "Admin";
	//public static final String dbPassword = "teamA2013";

	public static final String dbDir = "/Users/nickos/SkyDrive/UWA2/CITS3200/Honours-Marks-Database/db/";
	//public static final String dbDir = "db/";
	public static final String backupDir = "db/backups/";
	/**
	 * Cohort under consideration 
	 */
	public static String currentFocus = "";
	public static String cohortDB = dbDir + currentFocus;
	//public static final String dbUser = "Admin";
	//public static final String dbPassword = "teamA2013";

	@SuppressWarnings("unused")
	private static boolean loggedIn = false;
	@SuppressWarnings("unused")
	private static String user = "";

	/**
	 * Sets loggedIn to false.
	 * In conjunction with GUI structure
	 * @todo finish this method - will need to tie to GUI methods and DB connection 
	 */
	public static void logout() {
		loggedIn = false;
		user = "";
		currentFocus = "";
	}

	/**
	 * Set loggedIn to true if credentials validated
	 * @todo finish - will need to tie to GUI methods and DB connection 
	 */
	public static boolean login(String username, String password, String cohort) {
		dbConnect();
		if(username.equals(un) && ((pwHash.equals("initial") && password.equals(pwHash)) || BCrypt.checkpw(password, pwHash))) {
			loggedIn = true;
			currentFocus = cohort; 
			pwHash = "";
			user = un;
			System.out.println("logged in to view " + cohort);
			//@todo getchohortdata operation
			return true; 
		} else {
			un = "";
			pwHash = "";
			System.out.println("not logged in...");
			return false;
		}
	}

	/**
	 * Change password 
	 * @todo the validation of newp should occur in UI
	 */
	public static boolean changePassword(String oldp, String newp) {
		if(BCrypt.checkpw(oldp, pwHash)) {
			pwHash = BCrypt.hashpw(newp, BCrypt.gensalt()); 
			System.out.println("success");
			return true; 
		} else {
			System.out.println("failure");
			return false;
		} 

	}

	//@todo implement this method
	public void changeFocus(){}

	public static void dbConnect() {

		// define the Derby connection URL to use
		String connectionURL = "jdbc:derby:" + systemAbs.substring(1);

		Connection conn = null;
		Statement s;
		@SuppressWarnings("unused")		//TODO: remove later
		ResultSet users;

		//   Beginning of JDBC code sections   
		//   ## LOAD DRIVER SECTION ##
		try	{
			/*
			 **  Load the Derby driver. 
			 **     When the embedded Driver is used this action start the Derby engine.
			 **  Catch an error and suggest a CLASSPATH problem
			 */
			Class.forName(driver); 
			System.out.println(driver + " loaded. ");
		} catch(java.lang.ClassNotFoundException e) {	//TODO: more professional error handling
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
			System.out.println("\n    >>> Please check your CLASSPATH variable   <<<\n");	//TODO: change to a more professional message
		}
		//  Beginning of Primary DB access section
		//   ## BOOT DATABASE SECTION ##
		try {
			// Create (if needed) and connect to the database
			conn = DriverManager.getConnection(connectionURL);	// @todo add username, password	 
			System.out.println("Connected to database " + systemDBRel);

			//   ## INITIAL SQL SECTION ## 
			//   Create a statement to issue simple commands.  
			s = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM System";
			ResultSet rs = s.executeQuery( SQL );
			rs.first();

			String u = rs.getString("username");
			String p = rs.getString("password");

			un = u;
			pwHash = p;

			s.close();
			conn.close();						
			System.out.println("Closed connection");

			//   ## DATABASE SHUTDOWN SECTION ## 
			/*** In embedded mode, an application should shut down Derby.
               Shutdown throws the XJ015 exception to confirm success. ***/			
			if (driver.equals("org.apache.derby.jdbc.EmbeddedDriver")) {
				boolean gotSQLExc = false;
				try {
					DriverManager.getConnection("jdbc:derby:;shutdown=true");
				} catch (SQLException se)  {	
					if ( se.getSQLState().equals("XJ015") ) {		
						gotSQLExc = true;
					}
				}
				if (!gotSQLExc) {
					System.out.println("Database did not shut down normally");
				}  else  {
					System.out.println("Database shut down normally");	
				}  
			}

			//  Beginning of the primary catch block: uses errorPrint method
		} catch (java.sql.SQLException e)  {   
			/*       Catch all exceptions and pass them to 
			 **       the exception reporting method             */
			System.err.println("Error: The database " + connectionURL.substring(11) + " was unable to be located");	//TODO: fix when using relative paths
			e.printStackTrace();
		}
		System.out.println("Getting Started With Derby JDBC program ending.");
	}

	// @todo http://stackoverflow.com/questions/5125242/list-only-subdirectory-from-directory-not-files
	public static String[] getCohorts() {
		File file = new File(dbDir);
		String[] directories = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return (new File(dir, name).isDirectory() && name.matches("\\d{5}"));
			}
		});
		if(directories == null) return new String[]{"No cohorts could be found"};
		return directories;
	}
}

