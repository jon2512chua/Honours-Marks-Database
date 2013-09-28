package sessionControl;

import java.sql.DriverManager;
import java.sql.SQLException;

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
		if(db.equals(Directories.systemDb)) {
			return Session.sysConn.openConnection(db);	
		}
		else
			return Session.dbConn.openConnection(db);
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

}
