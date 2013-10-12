package sessionControl;

import java.io.File;
import java.io.FilenameFilter;
import java.sql.*;

/**
 * This static class holds information about the current session
 * 
 * @author Nicholas Abbey 20522805
 * @version 22/09/13
 * 
 *          PASSWORD CURRENTLY SET TO DEFAULT!! users are either Heather or
 *          admin
 * 
 */
public class Session {

	/**
	 * Object for storing connections to the System database
	 */
	public static ConnectionWrapper sysConn = new ConnectionWrapper();
	/**
	 * Object for storing connections to the cohort database
	 */
	public static ConnectionWrapper dbConn = new ConnectionWrapper();

	// do we need systemDB username/password installed?

	/**
	 * Cohort currently under consideration
	 */
	public static String currentFocus = "";
	/**
	 * Relative path to directory of currentFocus db
	 */
	public static String cohortDB = Directories.dbDir + currentFocus;

	/**
	 * true when login successful
	 */
	@SuppressWarnings("unused")	//TODO remove
	private static boolean loggedIn = false; // is this necessary??? TODO
												// depends on logout protocol
	/**
	 * Stores name of current user
	 */
	private static String user = "";

	// --------------------------------------------------------------------------------

	/**
	 * Set loggedIn to true if credentials validated
	 */
	public static boolean login(String username, String password, String cohort) {

		Statement query;

		try {
			query = sysConn.getConnection()
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM System where username = '" + username
					+ "'";

			ResultSet rs = query.executeQuery(SQL);

			if (!rs.next()) {
				return false;
			}

			String u = rs.getString("username");
			String p = rs.getString("password");
			if (username.equals(u) && BCrypt.checkpw(password, p)) {
				loggedIn = true;
				currentFocus = cohort;
				user = username;
				rs.close();
				query.close();
				return true;
			} else {
				rs.close();
				query.close();
				return false;
			}
		} catch (SQLException e) {
			System.err.println("ERROR: There was an error connecting to the System database: could not attempt logon.");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Sets loggedIn to false. 
	 */
	public static void logout() {
		loggedIn = false;
		user = "";
		currentFocus = "";
	}

	/**
	 * Change password
	 * 
	 * TODO the validation of newp should occur in UI
	 */
	public static boolean changePassword(String oldp, String newp) {

		boolean check = DerbyUtils.dbConnect(Directories.systemDb);
		if (!check)
			return false;

		Statement query;

		try {
			query = sysConn.getConnection()
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM System WHERE username = '"
					+ Session.user + "'";
			ResultSet rs = query.executeQuery(SQL);

			rs.next();

			String p = rs.getString("password");

			if ((BCrypt.checkpw(oldp, p))) {
				String update = "UPDATE System SET password = '"
						+ BCrypt.hashpw(newp, BCrypt.gensalt())
						+ "' WHERE username = " + "'admin'";
				query.execute(update);
				query.close();
				rs.close();
				DerbyUtils.dbDisconnect(sysConn);
				return true;
			} else {
				DerbyUtils.dbDisconnect(sysConn);
				query.close();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Change the GUI's focus to a different cohort TODO implement changeFocus
	 */
	public boolean changeFocus(String newFocus) {
		dbConn.closeConnection();
		if (DerbyUtils.dbConnect(newFocus)) {
			currentFocus = newFocus;
			// TODO load data!
			return true;
		} else
			return false;
	}

	/**
	 * Helper method for populating the list of databases on the login screen
	 * This method was adapted from code from http://stackoverflow.com/questions
	 * /5125242/list-only-subdirectory-from -directory-not-files, error handling
	 * TODO fix reference
	 * 
	 * @return a list of the available databases
	 */
	public static String[] getCohorts() {
		File file = new File(Directories.dbDir);
		if(file.exists()) {
			String[] directories = file.list(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return (new File(dir, name).isDirectory() && name
							.matches("\\d{5}"));
				}
			});
			return directories;
		}
		else {
			System.err.println("ERROR: The database directory '" + Directories.dbDir + "' could not be found.");
			return new String[]{Errors.noDatabaseFolder};
		} 		
	}
}
