package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import sessionControl.DerbyUtils;
import sessionControl.Directories;
import gui.MainUI;

/**
 * Main class. Acts as a preloader for MainUI.
 * @author Tim Lander
 */
public class MainLoader {
	private static final String errorFile = "LogFile.log"; 	//This is for when we want the errors to be printed to a log file.

	public static void main(String[] args) {
		//Prints Errors to a log file stored in the local directory
		//TODO: maybe standardise errors, and timestamp errors
		try {
			FileOutputStream fos = new FileOutputStream(new File(errorFile));
			System.setErr(new PrintStream(fos));
		} catch (FileNotFoundException e) {
			System.err.println("Warning: Unable to print to log file. Future errors/warning will print to stdout");
			e.printStackTrace();
		}
		
		//Load correct SWT.jar
		SWTLoader.loadSwtJar();
		
		//Load database drivers
		if(!DerbyUtils.loadDriver()) {
			PopupWindow.failedLoad(new Shell(new Display()), "Sorry, the database driver could not be loaded.\nPlease contact System Admin.");
			System.exit(1);	//Exit status of 0 indicates success.
		}
		//Connect to System Database
		else if(!DerbyUtils.dbConnect(Directories.systemDb)) {
			PopupWindow.failedLoad(new Shell(new Display()), "Sorry, we couldn't connect to the system database.\nPlease try again later, as there is most likely another user accessing the program.");
			System.exit(1);
		}
		else {
			//Begin program execution
			MainUI.main2();
		}
	}
	
	
}
