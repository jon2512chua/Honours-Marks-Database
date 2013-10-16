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

	/**
	 * Cohort currently under consideration
	 */
	public static String currentFocus = "";
	/**
	 * Relative path to directory of currentFocus db
	 */
	public static String cohortDB = Directories.dbDir + currentFocus;
	/**
	 * Stores name of current user
	 */
	private static String user = "";

	// --------------------------------------------------------------------------------

	/**
	 * Return the currently logged in user
	 * @return username
	 */
	public static String getUser() {
		return user;
	}
	
	/**
	 * Log a user in if their credentials are validated
	 * @param username
	 * @param password
	 * @param the cohort to load, selected from the login screen
	 * @return true if successful
	 */
	public static boolean login(String username, String password, String cohort) {
		if (checkPassword(username, password)) {
			currentFocus = cohort;
			user = username;
			return true;
		}
		else return false;
	}
	
//	/** Couldn't be used because setup needed retooling.
//	 *	
//	 * Log a user in if they have forgotton their details
//	 * Sets password to default.
//	 * @param username
//	 * @param secretAnswer
//	 * @return true if successful
//	 */
//	public static boolean recover(String username, String secretAnswer, String cohort)
//	{
//		if (checkRecovery(username, secretAnswer)) {
//			currentFocus = cohort;
//			user = username;
//			return true;
//		}
//		else return false;
//	}
	
	
	
	
	
	/**
	 * Helper method to check if a username/password combo match
	 * Called by recover
	 * @param username
	 * @param secretAnswer
	 * @return true if password correct 
	 */
	public static boolean checkRecovery(String username, String secretAnswer) {
		try {
			Statement query = sysConn.getConnection()
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT SecretAns FROM System where username = '" + username
					+ "'";

			ResultSet rs = query.executeQuery(SQL);

			if (!rs.next()) {
				return false;
			}

			String a = rs.getString("SecretAns");
			if (a.equals(secretAnswer)) {
				rs.close();
				query.close();
				user = username;
				return true;
			} else {
				rs.close();
				query.close();
				return false;
			}
		} catch (SQLException e) {
			System.err.println("ERROR: There was an error connecting to the System database: could check recovery details.");
			System.err.println("PROGRAM REPORT: " + e);
			return false;
		}
	}
	
	/**
	 * @param Username
	 * @return Secret Question for printing
	 */
	public static String getSecretQuestion(String username) {
		try {
			Statement query = sysConn.getConnection()
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT SecretQn FROM System where Username = '" + username
					+ "'";

			ResultSet rs = query.executeQuery(SQL);
			
			rs.next();

			return rs.getString("SecretQn");
		} catch (SQLException e) {
			System.err.println("ERROR: There was an error connecting to the System database: could check recovery details.");
			System.err.println("PROGRAM REPORT: " + e);
			return "Couldn't retrieve secret question.\n" +
					"It was not set for this user, or an error has occured.";
		}
	}
	
	/**
	 * Helper method to check if a username/password combo match
	 * Called by login, change password, and in DisplaySettings when updating info 
	 * @param username
	 * @param password
	 * @return true if password correct 
	 */
	private static boolean checkPassword(String username, String password) {
		try {
			Statement query = sysConn.getConnection()
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT Password FROM System where Username = '" + username
					+ "'";

			ResultSet rs = query.executeQuery(SQL);

			if (!rs.next()) {
				return false;
			}
			String p = rs.getString("Password");
			if (BCrypt.checkpw(password, p)) {			
				rs.close();
				query.close();
				return true;
			} else {
				rs.close();
				query.close();
				return false;
			}
		} catch (SQLException e) {
			System.err.println("ERROR: There was an error connecting to the System database: could check password.");
			System.err.println("PROGRAM REPORT: " + e);
			return false;
		}
	}

	/**
	 * Sets loggedIn to false. 
	 */
	public static void logout() {
		user = "";
		currentFocus = "";
	}

	/**
	 * Change password
	 * @return true if successful
	 */
	public static boolean changePassword(String oldp, String newp) {
		if (checkPassword(user, oldp)) {
			Statement stmt;
			try {
				stmt = sysConn.getConnection().createStatement();
				String update = "UPDATE System SET Password = '"
						+ BCrypt.hashpw(newp, BCrypt.gensalt())
						+ "' WHERE username = '" + user + "'";
				stmt.execute(update);
				stmt.close();
				return true;
			} catch (SQLException e) {
				System.err.println("ERROR: couldn't change password.\nPROGRAM REPORT: " + e);
				return false;
			}
		}
		else return false;
	}
	
	/**
	 * Change username
	 * @return true if successful
	 */
	public static boolean changeUsername(String oldp, String newUsername) {
		if (checkPassword(user, oldp)) {
			Statement stmt;
			try {
				stmt = sysConn.getConnection().createStatement();
				String update = "UPDATE System SET Username = '"
						+ newUsername
						+ "' WHERE username = '" + user + "'";
				stmt.execute(update);
				stmt.close();
				user = newUsername;
				return true;
			} catch (SQLException e) {
				System.err.println("ERROR: couldn't change username.\nPROGRAM REPORT: " + e);
				return false;
			}
		}
		else return false;
	}

	/**
	 * Change secret question and/or answer
	 * @return true if successful
	 */
	public static boolean changeRecovery(String oldp, String newQuestion, String newAnswer) {
		if (checkPassword(user, oldp)) {
			Statement stmt;
			try {
				stmt = sysConn.getConnection().createStatement();
				String update = "UPDATE System SET SecretQn = '"
						+ newQuestion + "', SecretAns = '" + newAnswer
						+ "' WHERE username = '" + user + "'";
				stmt.execute(update);
				stmt.close();
				return true;
			} catch (SQLException e) {
				System.err.println("ERROR: couldn't change username.\nPROGRAM REPORT: " + e);
				return false;
			}
		}
		else return false;
	}
	
	/**
	 * Change the GUI's focus to a different cohort 
	 * There is not currently a GUI option for doing this.
	 * @param the new cohort to focus on
	 * @param true if successful
	 */
	public boolean changeFocus(String newFocus) {
		dbConn.closeConnection();
		if (DerbyUtils.dbConnect(newFocus)) {
			currentFocus = newFocus;
			logic.CohortData.loadData();
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
