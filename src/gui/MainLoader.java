package gui;

import sessionControl.DerbyUtils;
import sessionControl.Directories;

/**
 * Main class. Acts as a preloader for MainUI.
 * @author Tim Lander
 */
public class MainLoader {

	public static void main(String[] args) {
		
		//Load correct SWT.jar
		SWTLoader.loadSwtJar();
		
		//Load database drivers
		DerbyUtils.loadDriver();
		
		//Connect to System Database
		boolean check = DerbyUtils.dbConnect(Directories.systemDb);
		
		if (!check) ;//TODO if can't connect to system then show a pop-up with error message and don't open.
		else {
			//Begin program execution
			MainUI.main2();
		}	
	}
}
