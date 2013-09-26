package gui;

/**
 * Main class. Acts as a preloader for MainUI.
 * @author Tim Lander
 */
public class MainLoader {

	public static void main(String[] args) {
		
		//Load correct SWT.jar
		SWTLoader.loadSwtJar();

		//Begin program execution
		MainUI.main2();
		
	}

}
