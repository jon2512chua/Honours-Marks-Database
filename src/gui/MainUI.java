package gui;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;

import sessionControl.DerbyUtils;

import org.eclipse.swt.widgets.Label;


/**
 * Main UI class
 * @author Tim Lander
 */
public class MainUI {

	//Constants
	private final static int DefaultHeight = 600;
	private final static int DefaultWidth = 900;
	public final static int LColumnWidth = 200;	//TODO: calculate dynamically
	public final static int RColumnWidth = 500;
	private static final String iconFileName = "icon.png";

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main2() {

		//Set up Shell
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(2, false));
		try {
			Image icon = new Image(display,iconFileName);
			shell.setImage(icon); 
		} catch (SWTException e) {
			System.err.println("Warning: The file " + (new File(iconFileName)).toURI().getPath() + " was unable to be located.");
		}

		//Displays Log On screen
		Shell logon = PopupWindow.popupLogon(shell);
		while (!logon.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}


		Composite nowViewingComposite = new Composite(shell, SWT.BORDER);
		nowViewingComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		nowViewingComposite.setLayout(new GridLayout(1, false));

		Label lblNowViewing = new Label(nowViewingComposite, SWT.NONE);
		lblNowViewing.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		lblNowViewing.setText("Now Viewing:");

		Label lblSemester = new Label(nowViewingComposite, SWT.NONE);
		lblSemester.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		lblSemester.setText(sessionControl.Session.currentFocus.substring(0, 4) + " - Semester " + sessionControl.Session.currentFocus.substring(4));


		final Composite displayComposite = new Composite(shell, SWT.NONE);
		GridData gd_displayComposite = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_displayComposite.verticalSpan = 3;
		displayComposite.setLayoutData(gd_displayComposite);
		final StackLayout sl_displayComposite = new StackLayout();
		displayComposite.setLayout(sl_displayComposite);


		//Left Display
		//TODO: fix button widths
		ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		final Composite menuComposite = new Composite(scrolledComposite, SWT.NONE);
		GridLayout gl_menuComposite = new GridLayout(1, false);
		gl_menuComposite.marginHeight = 1;
		gl_menuComposite.marginWidth = 1;
		gl_menuComposite.verticalSpacing = 20;
		menuComposite.setLayout(gl_menuComposite);

		Button btnReports = new Button(menuComposite, SWT.NONE);
		GridData gd_btnReports = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_btnReports.heightHint = 30;
		btnReports.setLayoutData(gd_btnReports);
		btnReports.setAlignment(SWT.LEFT);
		btnReports.setText("Reports");

		Button btnEnterMarks = new Button(menuComposite, SWT.NONE);
		GridData gd_btnEnterMarks = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_btnEnterMarks.heightHint = 30;
		btnEnterMarks.setLayoutData(gd_btnEnterMarks);
		btnEnterMarks.setAlignment(SWT.LEFT);
		btnEnterMarks.setText("Enter Marks");

		Button btnManageCohort = new Button(menuComposite, SWT.NONE);
		GridData gd_btnManageCohort = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_btnManageCohort.heightHint = 30;
		btnManageCohort.setLayoutData(gd_btnManageCohort);
		btnManageCohort.setAlignment(SWT.LEFT);
		btnManageCohort.setText("Manage Cohort");

		Button btnSettings = new Button(menuComposite, SWT.NONE);
		GridData gd_btnSettings = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_btnSettings.heightHint = 30;
		btnSettings.setLayoutData(gd_btnSettings);
		btnSettings.setAlignment(SWT.LEFT);
		btnSettings.setText("Settings");

		Composite exitComposite = new Composite(shell, SWT.BORDER);
		GridLayout gl_exitComposite = new GridLayout(1, false);
		gl_exitComposite.marginWidth = 1;
		gl_exitComposite.marginHeight = 1;
		exitComposite.setLayout(gl_exitComposite);
		exitComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));

		Button btnExit = new Button(exitComposite, SWT.NONE);
		GridData gd_btnExit = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_btnExit.heightHint = 30;
		btnExit.setLayoutData(gd_btnExit);
		btnExit.setAlignment(SWT.LEFT);
		btnExit.setText("Exit");

		scrolledComposite.setContent(menuComposite);
		scrolledComposite.setMinSize(menuComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		//Generate the Reports Screen
		final CTabFolder reportTabFolder = DisplayReport.display(displayComposite);

		//Generate the Tools Screen
		/*final CTabFolder toolsTabFolder = DisplayTools.display(displayComposite);*/

		//Generate the Creation/Editing screen
		final CTabFolder manageCohortTabFolder = DisplayCE.display(displayComposite);

		//Generate the Settings Screen
		final CTabFolder settingsTabFolder = DisplaySettings.display(displayComposite);


		
		//Button Listeners
		Listener btnReportsListener = new Listener() {
			public void handleEvent(Event event) {
				showScreen(reportTabFolder, sl_displayComposite, 0, "Reports");
			}
		};
		btnReports.addListener(SWT.Selection, btnReportsListener);

		Listener btnManageCohortListener = new Listener() {
			public void handleEvent(Event event) {
				showScreen(manageCohortTabFolder, sl_displayComposite, 0, "Manage Cohort");
			}
		};
		btnManageCohort.addListener(SWT.Selection, btnManageCohortListener);

		//TODO: link to btnEnterMarks
		/*Listener btnSettingsListener = new Listener() {
			public void handleEvent(Event event) {
				showScreen(toolsTabFolder, sl_displayComposite, 0, "Tools");
			}
		};
		btnSettings.addListener(SWT.Selection, btnSettingsListener);*/

		Listener btnSettingsListener = new Listener() {
			public void handleEvent(Event event) {
				showScreen(settingsTabFolder, sl_displayComposite, 0, "Settings");
			}
		};
		btnSettings.addListener(SWT.Selection, btnSettingsListener);

		Listener btnExitListener = new Listener() {
			public void handleEvent(Event event) {
				shell.close();
			}
		};
		btnExit.addListener(SWT.Selection, btnExitListener);

		//Set Initial Screen
		shell.setText("CITS3200 Program");

		//Sets window initial size, and window position to middle of screen
		shell.pack();
		shell.setSize(DefaultWidth, DefaultHeight);
		shell.setLocation((shell.getDisplay().getPrimaryMonitor().getBounds().width-(shell.getSize().x))/2, 80);
		shell.setMaximized(Boolean.valueOf(Settings.loadSettings("maximized", "false")));					//restores the maximized state
		shell.open();


		//Actions to perform when program is closed.
		shell.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent event) {
				Settings.saveSettings(new String[]{"maximized"}, new String[] {shell.getMaximized()+""});		//saves the maximized state
				if(sessionControl.Session.sysConn.isConnected()) DerbyUtils.dbDisconnect(sessionControl.Session.sysConn);
				if(sessionControl.Session.dbConn.isConnected()) DerbyUtils.dbDisconnect(sessionControl.Session.dbConn);
				DerbyUtils.shutdownDriver();
			}
		});



		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}


	/**
	 * Controls the selection of different screens
	 * @param showTabFolder the CTabFolder to display
	 * @param parent the StackLayout the CTabFolder's parent is laid out with
	 * @param tabIndex the tab to display
	 * @param title	the title to display at the top of the screen
	 */
	private static void showScreen(CTabFolder showTabFolder, StackLayout parent, int tabIndex, String title) {
		parent.topControl = showTabFolder.getParent();
		showTabFolder.setSelection(tabIndex);
		showTabFolder.getParent().getParent().layout();
		showTabFolder.getShell().setText(title);
	}

	//TODO: Tree *should* update automatically. If not, try this:
	/*public static void refresh(TreeItem ctrl, Tree tree) {
		//tree.getItems()	//list of all children
		//TreeItemsetSelection() //might work
	}*/

}