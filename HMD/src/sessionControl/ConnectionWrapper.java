package sessionControl;

import java.sql.Connection;

public class ConnectionWrapper {
	
	public Connection dock;
	public boolean disconnected;
	public String stuff = "blahblah";
	
	public ConnectionWrapper() {
		dock = null;
		disconnected = false;
	}

}
