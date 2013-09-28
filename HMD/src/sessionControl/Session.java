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
	public static ConnectionWrapper sysConn = new ConnectionWrapper();
	/**
	 * Object for storing connections to the cohort database
	 */
	public static ConnectionWrapper dbConn = new ConnectionWrapper();

	// do we need systemDB un/pw installed?

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
	private static boolean loggedIn = false;
	/**
	 * Stores name of current user
	 */
	private static String user = "";

	// --------------------------------------------------------------------------------

	/**
	 * Set loggedIn to true if credentials validated TODO finish - will need to
	 * tie to GUI methods and DB connection
	 */
	public static boolean login(String username, String password, String cohort) {

		boolean check = DerbyUtils.dbConnect(Directories.systemDb);
		if (!check)
			return false;

		Statement query;

		try {
			query = sysConn.getConnection()
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
				DerbyUtils.dbDisconnect(sysConn);
				query.close();
				return true;
			} else {
				System.out.println("not logged in...");
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
	 * Sets loggedIn to false. In conjunction with GUI structure TODO finish
	 * this method - will need to tie to GUI methods and DB connection
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
	 * Helper method for populating the list of databases on the login screen
	 * This method was adapted from code from http://stackoverflow.com/questions
	 * /5125242/list-only-subdirectory-from -directory-not-files, error handling
	 * TODO fix reference
	 * 
	 * @return a list of the available databases
	 */
	public static String[] getCohorts() {
		File file = new File(Directories.dbDir);
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
