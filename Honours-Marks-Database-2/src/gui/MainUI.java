package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;


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
	
	public static void main2() {
		
		//Set up Shell
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(2, false));

	    //Left Display
		final Composite menuComposite = new Composite(shell, SWT.BORDER);
		GridData gd_menuComposite = new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1);
		gd_menuComposite.heightHint = shell.getDisplay().getBounds().height;
		menuComposite.setLayoutData(gd_menuComposite);
		menuComposite.setLayout(new GridLayout(1, false));
		
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
		
		//Padding
		new Label(menuComposite, SWT.HORIZONTAL);
		
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
		
		//Padding
		new Label(menuComposite, SWT.HORIZONTAL);
		
		Group grpTools = new Group(menuComposite, SWT.NONE);
		grpTools.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		grpTools.setText("Tools");
		grpTools.setLayout(new RowLayout(SWT.VERTICAL));
		
		Button btnBackup = new Button(grpTools, SWT.NONE);
		btnBackup.setAlignment(SWT.LEFT);
		btnBackup.setText("Backup");		
		
		//Generate the Reports Screen
		final CTabFolder reportTabFolder = DisplayReport.display(shell);
		
		//Generate the Tools Screen
		final CTabFolder toolsTabFolder = DisplayTools.display(shell);
		
		//Generate the Creation/Editing screen
		final CTabFolder CETabFolder = DisplayCE.display(shell);
		
		
		//Button Listeners
		Listener btnStudentReportListener = new Listener() {
			public void handleEvent(Event event) {
				CTabFolder hideTabFolder[] = {toolsTabFolder, CETabFolder};
				showScreen(shell, "Reports", reportTabFolder, hideTabFolder, 0);
				
			}
		};
		btnStudentReport.addListener(SWT.Selection, btnStudentReportListener);
		
		Listener btnMarkerReportListener = new Listener() {
			public void handleEvent(Event event) {
				CTabFolder hideTabFolder[] = {toolsTabFolder, CETabFolder};
				showScreen(shell, "Reports", reportTabFolder, hideTabFolder, 1);
			}
		};
		btnMarkerReport.addListener(SWT.Selection, btnMarkerReportListener);
		
		Listener btnEditStudentListener = new Listener() {
			public void handleEvent(Event event) {
				CTabFolder hideTabFolder[] = {reportTabFolder, toolsTabFolder};
				showScreen(shell, "Creation/Editing", CETabFolder, hideTabFolder, 0);
			}
		};
		btnEditStudent.addListener(SWT.Selection, btnEditStudentListener);
		
		Listener btnEditStaffListener = new Listener() {
			public void handleEvent(Event event) {
				CTabFolder hideTabFolder[] = {reportTabFolder, toolsTabFolder};
				showScreen(shell, "Creation/Editing", CETabFolder, hideTabFolder, 1);
			}
		};
		btnEditStaff.addListener(SWT.Selection, btnEditStaffListener);
		
		Listener btnBackupListener = new Listener() {
			public void handleEvent(Event event) {
				CTabFolder hideTabFolder[] = {reportTabFolder, CETabFolder};
				showScreen(shell, "Tools", toolsTabFolder, hideTabFolder, 0);
			}
		};
		btnBackup.addListener(SWT.Selection, btnBackupListener);

		//Listener to Constrain the Resizing of the Right Composites.
		//TODO: There is likely a better way to do this
		reportTabFolder.getParent().addListener (SWT.Resize,  new Listener () {
			public void handleEvent (Event event) {
				Rectangle idealDimensions = new Rectangle(
						2*menuComposite.getBounds().x + menuComposite.getBounds().width, menuComposite.getBounds().y, 
						reportTabFolder.getParent().getParent().getBounds().width - 6*menuComposite.getBounds().x - menuComposite.getBounds().width, menuComposite.getBounds().height);
				reportTabFolder.getParent().setBounds(idealDimensions);
				toolsTabFolder.getParent().setBounds(idealDimensions);
				CETabFolder.getParent().setBounds(idealDimensions);
				menuComposite.setSize(menuComposite.getSize().x, idealDimensions.height);
				
			}
		});

		//Set Initial Screen
		CTabFolder hideTabFolder[] = {reportTabFolder, toolsTabFolder,CETabFolder};
		showScreen(shell, "CITS3200 Program", toolsTabFolder, hideTabFolder, 0);
		
		
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
	
	private static void showScreen(Shell shell, String heading, CTabFolder showTabFolder, CTabFolder[] hideTabFolder, int index) {
		showTabFolder.getParent().setVisible(true);
		showTabFolder.setSelection(index);
		for (int i=0; i<hideTabFolder.length; i++) {
			hideTabFolder[i].getParent().setVisible(false);
		}
		shell.setText(heading);
	}
	
}