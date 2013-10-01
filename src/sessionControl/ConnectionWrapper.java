package sessionControl;

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
		disconnected = false;
	}

	// public void setConnection(Connection c) {dock = c;}

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

		String connectionURL = "jdbc:derby:" + db + ";";

		try {
			dock = DriverManager.getConnection(connectionURL);
		} catch (Throwable e) {
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
			return false;
		}
	}
}
