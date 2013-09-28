package sessionControl;

import java.io.File;
import java.io.FilenameFilter;
import java.sql.*;

/**
 * This static class holds information about the current session
 * 
 * @author Nicholas Abbey 20522805
 * @version 22/09/13
 */
public class Session {

	/**
	 * Object for storing connections to the System database
	 */
	public static ConnectionWrapper sysConn = null;
	/**
	 * Object for storing connections to the cohort database
	 */
	public static ConnectionWrapper dbConn = null;

	// do we need systemDB un/pw installed?
	/**
	 * Derby embedded driver
	 */
	public static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
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
	 * Cohort currently under consideration
	 */
	public static String currentFocus = "";
	/**
	 * Relative path to directory of currentFocus db
	 */
	public static String cohortDB = dbDir + currentFocus;

	/**
	 * true when login successful
	 */
	private static boolean loggedIn = false;
	/**
	 * Stores name of current user
	 */
	private static String user = "";

	/**
	 * Sets loggedIn to false. In conjunction with GUI structure TODO finish
	 * this method - will need to tie to GUI methods and DB connection
	 */
	public static void logout() {
		loggedIn = false;
		user = "";
		currentFocus = "";
	}

	/**
	 * Set loggedIn to true if credentials validated TODO finish - will need to
	 * tie to GUI methods and DB connection
	 */
	public static boolean login(String username, String password, String cohort) {

		boolean check = dbConnect(systemDb, sysConn);
		if(!check) return false;
		
		Statement query;

		try {
			query = sysConn.dock
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM System";

			ResultSet rs = query.executeQuery(SQL);

			rs.first();

			String u = rs.getString("username");
			String p = rs.getString("password");
			if (username.equals(u)
					&& ((p.equals("initial") && password.equals(p)) || BCrypt
							.checkpw(password, u))) {
				loggedIn = true;
				currentFocus = cohort;

				System.out.println("logged in to view " + cohort);
				// TODO get data operation
				dbDisconnect(sysConn);
				query.close();
				return true;
			} else {
				System.out.println("not logged in...");
				dbDisconnect(sysConn);
				query.close();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Change password
	 * 
	 * TODO the validation of newp should occur in UI
	 */
	// public static boolean changePassword(String oldp, String newp) {
	// if(BCrypt.checkpw(oldp, pwHash)) {
	// pwHash = BCrypt.hashpw(newp, BCrypt.gensalt());
	// System.out.println("success");
	// return true;
	// }
	// else {
	// System.out.println("failure");
	// return false;
	// }
	//
	// }

	/**
	 * change the GUI's focus to a different cohort TODO implement changeFocus
	 */
	public void changeFocus() {
	}

	/**
	 * Connect to a database
	 * 
	 * @param db
	 *            - the directory of the database to be accessed
	 * @param conn
	 *            - the connection wrapper for that database (sysConn or
	 *            dbConn)
	 */
	public static boolean dbConnect(String db, ConnectionWrapper conn) {
		if(sysConn == null) System.out.println("null");
		if (sysConn == null || sysConn.disconnected) {
			sysConn = new ConnectionWrapper();
		}
		if(sysConn == null) System.out.println("null");

		String connectionURL = "jdbc:derby:" + db + ";";

		try { // ## LOAD DRIVER SECTION ##
			Class.forName(driver);
			System.out.println(driver + " loaded. ");
		} catch (java.lang.ClassNotFoundException e) {
			return false;
		}

		try { // ## BOOT DATABASE SECTION ##
			sysConn.dock = DriverManager.getConnection(connectionURL);
			System.out.println("Connected to database " + systemDb);
		} catch (Throwable e) {
			System.out.println(" . . . exception thrown:");
			return false;
		}

		return true;
	}

	/**
	 * Disconnect from an active database
	 * 
	 * @param conn
	 *            - connection to sever
	 */
	public static boolean dbDisconnect(ConnectionWrapper conn) {
		try {
			sysConn.dock.close();
			sysConn.disconnected = true;
		} catch (SQLException e) {
			return false;
		}
		// ## DATABASE SHUTDOWN SECTION ##
		/***
		 * In embedded mode, an application should shut down Derby. Shutdown
		 * throws the XJ015 exception to confirm success.
		 ***/

		boolean gotSQLExc = false;
		try {
			DriverManager.getConnection("jdbc:derby:;shutdown=true");
		} catch (SQLException se) {
			if (se.getSQLState().equals("XJ015")) {
				gotSQLExc = true;
			}
		}
		if (!gotSQLExc) {
			// System.out.println("Database did not shut down normally");
			return false;
		} else {
			// System.out.println("Database shut down normally");
			return true;
		}
	}

	/**
	 * Helper method for populating the list of databases on the login screen
	 * This method was adapted from code from http://stackoverflow.com/questions
	 *         /5125242/list-only-subdirectory-from -directory-not-files, error
	 *         handling TODO fix reference
	 * 
	 * @return a list of the available databases         
	 */
	public static String[] getCohorts() {
		File file = new File(dbDir);
		String[] directories = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return (new File(dir, name).isDirectory() && name
						.matches("\\d{5}"));
			}
		});
		if (directories.length == 0)
			return (new String[] { "-1" });
		else
			return directories;
	}
}
