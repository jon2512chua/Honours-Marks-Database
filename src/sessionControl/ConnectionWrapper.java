package sessionControl;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class stores connection information and has connection utilities TODO needs
 * more testing
 * 
 * @author Nicholas Abbey 20522805
 * @version 28/9/13
 * 
 */
public class ConnectionWrapper {

	private Connection dock;
	private boolean disconnected;

	/**
	 * Constructor for new ConnectionWrapper
	 */
	public ConnectionWrapper() {
		dock = null;
		disconnected = true;
	}

	/**
	 * Check connection is active
	 * 
	 * @return true if connected
	 */
	public boolean isConnected() {
		return !disconnected;
	}

	/**
	 * Get connection for querying
	 * 
	 * @return the connection
	 */
	public Connection getConnection() {
		return dock;
	}

	/**
	 * Open a new connection
	 * 
	 * @param db
	 *            the database to connect to
	 * @return true if successful
	 */
	public boolean openConnection(String db) {
		if (disconnected) {
			dock = null;
		}
		
		if(!db.contains("true")) {
			File file = new File(db);
			if(!file.exists()) {
				System.err.println("ERROR: Database directory " + db + " does not exist.");
				return false;
			}
			db += ";";
		}
		
		String connectionURL = "jdbc:derby:" + db;

		try {
			dock = DriverManager.getConnection(connectionURL);
			disconnected = false;
		} catch (Throwable e) {
			System.err.println("ERROR: Could not connect to database in directory " + db.substring(0, db.length() - 1));
			return false;
		}
		return true;
	}

	/**
	 * Close a connection
	 * 
	 * @return true if successful
	 */
	public boolean closeConnection() {
		try {
			dock.close();
			disconnected = true;
			return true;
		} catch (SQLException e) {
			System.err.println("ERROR: Disconnect from database unsuccessful.");
			return false;
		}
	}
}
