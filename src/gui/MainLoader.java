package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import sessionControl.DerbyUtils;
import sessionControl.Directories;

/**
 * Main class. Acts as a preloader for MainUI.
 * @author Tim Lander
 */
@SuppressWarnings("unused")	//TODO: remove
public class MainLoader {
	private static final String errorFile = "LogFile.log";

	public static void main(String[] args) {
		// TODO: undisable
		//Prints Errors to a log file stored in the local directory
		//TODO: maybe standardise errors, and timestamp errors
		/*try {
			FileOutputStream fos = new FileOutputStream(new File(errorFile));
			System.setErr(new PrintStream(fos));
		} catch (FileNotFoundException e) {
			System.err.println("Warning: Unable to print to log file. Future errors/warning will print to stdout");
			e.printStackTrace();
		}*/
		
		//Load correct SWT.jar
		SWTLoader.loadSwtJar();
		
		//Load database drivers
		if(!DerbyUtils.loadDriver()) {
			//TODO gui.PopupWindow.popupMessage(parentShell, text, title)
			System.exit(0);
		}
		//Connect to System Database
		else if(!DerbyUtils.dbConnect(Directories.systemDb)) {
			//TODO show a pop-up with error message
			System.exit(0);
		}
		else {
			//Begin program execution
			MainUI.main2();
		}	
	}
}
