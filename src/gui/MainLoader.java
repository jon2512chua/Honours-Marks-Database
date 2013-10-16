package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import sessionControl.DerbyUtils;
import sessionControl.Directories;
import gui.MainUI;

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
			failedLoad("Sorry, the database driver could not be loaded.\nPlease contact System Admin.");
			System.exit(0);
		}
		//Connect to System Database
		else if(!DerbyUtils.dbConnect(Directories.systemDb)) {
			failedLoad("Sorry, we couldn't connect to the system database.\nPlease try again later, as there is most likely another user accessing the program.");
			System.exit(0);
		}
		else {
			//Begin program execution
			MainUI.main2();
		}
	}
	
	/**
	 * Method to display a popup if something goes wrong while starting up the system
	 * TODO needs fixing
	 * @param s
	 */
	public static void failedLoad(String s) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(2, false));
		try {
			Image icon = new Image(display,MainUI.getIconPath());
			shell.setImage(icon); 
		} catch (SWTException e) {
			System.err.println("Warning: The file " + (new File(MainUI.getIconPath())).toURI().getPath() + " was unable to be located.");
		}

		Shell errorMsg = PopupWindow.popupMsg(shell.getShell(), s, "HONOURS MARKS DATABASE");
		while (!errorMsg.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
