package gui;

import sessionControl.DerbyUtils;
import sessionControl.Directories;

/**
 * Main class. Acts as a preloader for MainUI.
 * @author Tim Lander
 */
public class MainLoader {

	public static void main(String[] args) {
		// TODO add filestream for error (Right at end) ADD a logfile
		
		//Load correct SWT.jar
		SWTLoader.loadSwtJar();
		
		//Load database drivers
		if(!DerbyUtils.loadDriver()) {
			//TODO gui.PopupWindow.popupMessage(parentShell, text, title)
			//TODO exit
		}
		//Connect to System Database
		else if(!DerbyUtils.dbConnect(Directories.systemDb)) {
			//TODO if can't connect to system then show a pop-up with error message and don't open.;
		}
		else {
			//Begin program execution
			MainUI.main2();
		}	
	}
}
