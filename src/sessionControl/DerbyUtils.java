package sessionControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Derby and Database utilities
 * 
 * @author Nicholas Abbey 20522805
 * @version 28/9/13
 */
public class DerbyUtils {

	/**
	 * Derby embedded driver
	 */
	public static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";

	/**
	 * Load the driver for Derby - called from MainLoader
	 */
	public static boolean loadDriver() {
		try {
			Class.forName(driver);
			return true;
		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("ERROR: Could not load the database driver, please contact System Administration.");
			return false;
		}
	}

	/**
	 * Shutdown the Derby driver. TODO This should be called for all exit
	 * conditions!
	 * 
	 * (In embedded mode, an application should shut down Derby. Shutdown throws
	 * the XJ015 exception to confirm success)
	 **/
	public static boolean shutdownDriver() {
		boolean gotSQLExc = false;
		try {
			DriverManager.getConnection("jdbc:derby:;shutdown=true");
		} catch (SQLException se) {
			if (se.getSQLState().equals("XJ015")) {
				gotSQLExc = true;
			}
		}
		if (!gotSQLExc) {
			System.err.println("ERROR: Derby Driver did non shutdown correctly.");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Connect to a database
	 * 
	 * @param db
	 *            - the directory of the database to be accessed
	 * @param conn
	 *            - the connection wrapper for that database (sysConn or dbConn)
	 */
	public static boolean dbConnect(String db) {
		if (db.equals(Directories.systemDb)) {
			return Session.sysConn.openConnection(db);
		} else
			return Session.dbConn.openConnection(Directories.dbDir + db);
	}

	/**
	 * Disconnect from an active database
	 * 
	 * @param conn
	 *            - connection to sever
	 */
	public static boolean dbDisconnect(ConnectionWrapper conn) {
		return conn.closeConnection();
	}

	/**
	 * Extract a list of SQL commands from an SQL file for execution by JDBC
	 * NOTE: SQL file must have certain format 1. remove all ; from end of line
	 * 2. no empty lines 3. each statement should occur on one line only (no \n
	 * for formating)
	 * 
	 * @param filepath
	 *            of the SQL
	 * @return a list of the commands - these will be executed one by one.
	 */
	public static List<String> getSqlFromFile(String filepath) {
		List<String> commands = new LinkedList<String>();
		Charset charset = Charset.forName("US-ASCII");
		Path file = FileSystems.getDefault().getPath(filepath);
		try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
			String line = reader.readLine();
			while (line != null) {
				commands.add(line);
				line = reader.readLine();
			}
		} catch (IOException x) {
			System.err.println("ERROR: Database schema could not be found at " + Directories.newCohortSql); //TODO fix exception
		}

		return commands;
	}

}
