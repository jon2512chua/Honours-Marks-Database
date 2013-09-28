package sessionControl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionWrapper {
	
	private Connection dock;
	private boolean disconnected;
	
	public ConnectionWrapper() {
		dock = null;
		disconnected = false;
	}
	
	//public void setConnection(Connection c) {dock = c;}
	
	public Connection getConnection() {return dock;}
	
	public boolean openConnection(String db) {
		if (disconnected) {dock = null;}

		String connectionURL = "jdbc:derby:" + db + ";";

		try {
			dock = DriverManager.getConnection(connectionURL);
		} catch (Throwable e) {
			return false;
		}
		return true;
	}
	
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


