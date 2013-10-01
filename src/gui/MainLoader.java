package gui;

import sessionControl.DerbyUtils;

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
		
		//Begin program execution
		MainUI.main2();	
	}
}
