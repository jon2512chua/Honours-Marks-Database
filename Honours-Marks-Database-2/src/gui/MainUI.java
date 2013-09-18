package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.StackLayout;


/**
 * Main UI class
 * @author Tim Lander
 */
public class MainUI {

	//Constants
	final static int DefaultHeight = 600;
	final static int DefaultWidth = 900;
	final static int LColumnWidth = 200;	//TODO: calculate dynamically
	final static int RColumnWidth = 500;

	//static Display display = null;

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main2() {

		//Set up Shell
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(2, false));

		//Left Display
		final Composite menuComposite = new Composite(shell, SWT.BORDER);
		GridData gd_menuComposite = new GridData(SWT.LEFT, SWT.TOP, false, true);
		gd_menuComposite.heightHint = shell.getDisplay().getBounds().height;
		menuComposite.setLayoutData(gd_menuComposite);
		GridLayout gl_menuComposite = new GridLayout(1, false);
		gl_menuComposite.verticalSpacing = 20;
		menuComposite.setLayout(gl_menuComposite);

		Group grpReports = new Group(menuComposite, SWT.NONE);
		grpReports.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		grpReports.setText("Reports");
		grpReports.setLayout(new RowLayout(SWT.VERTICAL));

		Button btnStudentReport = new Button(grpReports, SWT.NONE);
		btnStudentReport.setAlignment(SWT.LEFT);
		btnStudentReport.setText("Student Report");

		Button btnMarkerReport = new Button(grpReports, SWT.NONE);
		btnMarkerReport.setAlignment(SWT.LEFT);
		btnMarkerReport.setText("Marker Report");

		Group grpEditing = new Group(menuComposite, SWT.NONE);
		grpEditing.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		grpEditing.setText("Creation/Editing");
		grpEditing.setLayout(new RowLayout(SWT.VERTICAL));

		Button btnEditStudent = new Button(grpEditing, SWT.NONE);
		btnEditStudent.setAlignment(SWT.LEFT);
		btnEditStudent.setText("Edit Student");

		Button btnEditStaff = new Button(grpEditing, SWT.NONE);
		btnEditStaff.setAlignment(SWT.LEFT);
		btnEditStaff.setText("Edit Staff");

		Group grpTools = new Group(menuComposite, SWT.NONE);
		grpTools.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		grpTools.setText("Tools");
		grpTools.setLayout(new RowLayout(SWT.VERTICAL));

		Button btnBackup = new Button(grpTools, SWT.NONE);
		btnBackup.setAlignment(SWT.LEFT);
		btnBackup.setText("Backup");		

		final Composite displayComposite = new Composite(shell, SWT.NONE);
		displayComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		final StackLayout sl_displayComposite = new StackLayout();
		displayComposite.setLayout(sl_displayComposite);
		
		//Generate the Reports Screen
		final CTabFolder reportTabFolder = DisplayReport.display(displayComposite);

		//Generate the Tools Screen
		final CTabFolder toolsTabFolder = DisplayTools.display(displayComposite);

		//Generate the Creation/Editing screen
		final CTabFolder CETabFolder = DisplayCE.display(displayComposite);


		//Button Listeners
		Listener btnStudentReportListener = new Listener() {
			public void handleEvent(Event event) {
				showScreen(reportTabFolder, sl_displayComposite, 0, "Reports");
			}
		};
		btnStudentReport.addListener(SWT.Selection, btnStudentReportListener);

		Listener btnMarkerReportListener = new Listener() {
			public void handleEvent(Event event) {
				showScreen(reportTabFolder, sl_displayComposite, 1, "Reports");
			}
		};
		btnMarkerReport.addListener(SWT.Selection, btnMarkerReportListener);

		Listener btnEditStudentListener = new Listener() {
			public void handleEvent(Event event) {
				showScreen(CETabFolder, sl_displayComposite, 0, "Creation/Editing");
			}
		};
		btnEditStudent.addListener(SWT.Selection, btnEditStudentListener);

		Listener btnEditStaffListener = new Listener() {
			public void handleEvent(Event event) {
				showScreen(CETabFolder, sl_displayComposite, 1, "Creation/Editing");
			}
		};
		btnEditStaff.addListener(SWT.Selection, btnEditStaffListener);

		Listener btnBackupListener = new Listener() {
			public void handleEvent(Event event) {
				showScreen(toolsTabFolder, sl_displayComposite, 0, "Tools");
			}
		};
		btnBackup.addListener(SWT.Selection, btnBackupListener);

		//Set Initial Screen
		shell.setText("CITS3200 Program");


		shell.pack();
		shell.open();
		//Sets window initial size, and window position to middle of screen
		shell.setSize(DefaultWidth, DefaultHeight);
		shell.setLocation((shell.getDisplay().getBounds().width-(shell.getSize().x))/2, 80);

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

}