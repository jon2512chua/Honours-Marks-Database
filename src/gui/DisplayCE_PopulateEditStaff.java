package gui;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import logic.CohortData;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolTip;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import orm.Staff;

/**
 * Edit Staff Section
 * @author Tim Lander
 */
public class DisplayCE_PopulateEditStaff {
	private static Map<TreeItem, StringBuffer[]> TreeItemMap = new HashMap<TreeItem, StringBuffer[]>();
	public static Boolean hardRefreshNeeded = true;

	private static Text staffNumber;
	//private static Text title;
	private static Text lastName;
	private static Text firstName;
	private static Tree staffTree;
	private static String selectedStaff;
	private static Boolean firstRun = true; 
	/**
	 * Populates the Edit Staff Tab
	 * @param CETabFolder the folder to put the tab in
	 * @param tabName the name of the tab
	 * @wbp.parser.entryPoint
	 */
	public static void populate(final CTabFolder CETabFolder, String tabName) {
		CTabItem tbtmEditStaff = new CTabItem(CETabFolder, SWT.NONE);
		tbtmEditStaff.setText(tabName);

		//TODO: sort tree's be clicking on column title
		//TODO: save data
		final Composite editStaffComposite = new Composite(CETabFolder, SWT.NONE);
		editStaffComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		GridLayout gl_editStaffComposite = new GridLayout(3, false);
		gl_editStaffComposite.verticalSpacing = 0;
		gl_editStaffComposite.marginWidth = 0;
		gl_editStaffComposite.marginHeight = 0;
		editStaffComposite.setLayout(gl_editStaffComposite);

		tbtmEditStaff.setControl(editStaffComposite);


		staffTree = new Tree(editStaffComposite, SWT.BORDER | SWT.FULL_SELECTION);
		staffTree.setHeaderVisible(true);
		staffTree.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 2));

		TreeColumn staffTree_staffNumber= new TreeColumn(staffTree, SWT.LEFT);
		staffTree_staffNumber.setText("Staff Number");
		TreeColumn staffTree_staffName = new TreeColumn(staffTree, SWT.LEFT);
		staffTree_staffName.setText("Staff Name");
		staffTree.pack();

		staffTree.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Selection made: " + staffTree.getSelection()[0].getText());
				if (staffTree.getSelectionCount() == 1) {
					if (!staffTree.getSelection()[0].getText().equals("+"))  {
						TreeItem item = staffTree.getSelection()[0];
						Staff s = Staff.getStaffByID(item.getText());
						selectedStaff = s.getStaffID() +"";
						populateSelectedData(s);
						staffNumber.setEnabled(true);
//						staffNumber.setText(s.getStaffID()+"");
//						lastName.setText(s.getLastName().toString());
//						firstName.setText);
					} else {
						staffNumber.setEnabled(true);
						staffNumber.setText("");
						lastName.setText("");
						firstName.setText("");
					}
				}
			}
		});
		/*final Button btnImportStaff = new Button(editStaffComposite, SWT.NONE);
		btnImportStaff.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnImportStaff.setText("Import Staff from Excel");
		btnImportStaff.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				FileDialog fd = new FileDialog(btnImportStaff.getShell(), SWT.OPEN);
				fd.setText("Import From Excel");
				fd.setFilterPath(System.getProperty("user.home"));
				fd.setFilterExtensions(new String[]{ "*.xlsx", "*.xls", "*.*" });
				String selected = fd.open();

				if(selected != null) {
					//TODO: importing staff stuff
					//String importReport = CohortImporter.importFromFile(selected).toString();
					//PopupWindow.popupMessage(CETabFolder.getShell(), importReport, "RESULTS");
				}
			}
		});*/

		Composite rComposite = new Composite(editStaffComposite, SWT.NONE);
		rComposite.setLayout(new GridLayout(2, false));
		rComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 2));

		Label lblStaffNumber = new Label(rComposite, SWT.NONE);
		lblStaffNumber.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStaffNumber.setText("Staff Number:");
		staffNumber = new Text(rComposite, SWT.BORDER);
		staffNumber.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		staffNumber.setTextLimit(9); // Changed - don't have time to fix all the staff stuff
		Validation.validateInt(staffNumber);

		//Commented out checkbox
		/*new Label(rComposite, SWT.NONE);
		Button isUWAStaff = new Button(rComposite, SWT.CHECK);
		isUWAStaff.setText("UWA Staff?");*/


		/*//Staff do not have a title
		Label lblTitle = new Label(rComposite, SWT.NONE);
		lblTitle.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTitle.setText("Title:");
		title = new Text(rComposite, SWT.BORDER);
		title.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		 */


		Label lblLastName = new Label(rComposite, SWT.NONE);
		lblLastName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLastName.setText("Last Name:");
		lastName = new Text(rComposite, SWT.BORDER);
		lastName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblFirstName = new Label(rComposite, SWT.NONE);
		lblFirstName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFirstName.setText("First Name:");
		firstName = new Text(rComposite, SWT.BORDER);
		firstName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite paddingComposite = new Composite(rComposite, SWT.NONE);
		paddingComposite.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 2, 1));

		Button[] btnSaveDiscard = CommonButtons.addSaveChangesDeleteButton(rComposite, "Staff Member");

		refreshTree();

		//Action to perform when the save button is pressed
		btnSaveDiscard[0].addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (staffNumber.getText().length() != 8) {
					PopupWindow.popupMessage(CETabFolder.getShell(), "Staff number must be 8 digits long. \nStaff member has not been saved", "Error");
				} else {
					try {
						TreeItem item = staffTree.getSelection()[0];
						saveData(Staff.getStaffByID(item.getText()));
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {
						saveData(null);
					}
				}
			}
		});
		
		//Action to perform when the delete button is pressed
		btnSaveDiscard[1].addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				try {
					Staff.getStaffByID(selectedStaff).deleteRow();
					
					PopupWindow.popupMessage(CETabFolder.getShell(), "Staff deleted.", "Complete");
				}
				catch (Exception e) {
					PopupWindow.popupMessage(CETabFolder.getShell(), "Staff could not be deleted.", "WARNING");
				}
				hardRefreshNeeded = true;
				refreshTree();
			}	
		});

		//Tool tip.
		//TODO: display when input is invalid
		final ToolTip tip = new ToolTip(CETabFolder.getShell(), SWT.BALLOON);
		tip.setMessage("Staff number must be 8 digits long.");
		staffNumber.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				tip.setVisible(false);
			}

			public void focusGained(FocusEvent e) {
				tip.setLocation(staffNumber.toDisplay(0, staffNumber.getSize().y));
				tip.setVisible(true);
			}
		});


		//Auto Fit Columns
		for (TreeColumn tc : staffTree.getColumns()) tc.pack();

		//Listener to auto-update displayed data (currently untested)
		CETabFolder.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				refreshTree();
			}
		});

	}


	/**
	 * Displays data relevant to which staff member was clicked on.
	 * @param s the staff member that was clicked on
	 */
	private static void populateSelectedData(Staff s) {
		try {														//Found values
			staffNumber.setText(s.getStaffID()+"");
			//title.setText(s.()+"");
			lastName.setText(s.getLastName()+"");
			firstName.setText(s.getFirstName()+"");

			staffNumber.setEnabled(false);

		} catch (java.lang.NullPointerException e) {				//Default values
			staffNumber.setText("");
			//title.setText("");
			lastName.setText("");
			firstName.setText("");

			staffNumber.setEnabled(true);
		}
	}

	/**
	 * Action to perform when the save button is pressed
	 * @param staff the staff member whose data is to be saved
	 */
	private static void saveData(Staff staff) {
		try {									
			//staff.setTitle(title.getText());
			staff.setLastName(lastName.getText());
			staff.setFirstName(firstName.getText());

			staff.updateRow();
			refreshTree();

			PopupWindow.popupMessage(staffTree.getShell(), "Staff member saved successfully", "Save Successful");
		} catch (java.lang.NullPointerException | SQLException e) {
			try {
				new Staff(Integer.parseInt(staffNumber.getText()), firstName.getText(), lastName.getText());
				PopupWindow.popupMessage(staffTree.getShell(), "New staff member created successfully", "Save Successful");
				hardRefreshNeeded = true;
				refreshTree();
			} catch (SQLException ex) {
				PopupWindow.popupMessage(staffTree.getShell(), "New satff member was unable to be created. \nPossible duplicate staff number", "Save Unsuccessful");
			}

		} 
	}

	/**
	 * Refreshes all data displayed in the tree
	 * @param tree the tree which is to be refreshed
	 */
	public static void refreshTree() {
		if (hardRefreshNeeded) {
			hardRefresh();
			hardRefreshNeeded = false;
		}
		for ( TreeItem ti : staffTree.getItems() ) {
			try {
				ti.setText(new String[] {TreeItemMap.get(ti)[0].toString(), TreeItemMap.get(ti)[1] + " " + TreeItemMap.get(ti)[2]});
			} catch (java.lang.NullPointerException e) {}
		}
	}

	private static void hardRefresh() {
		for (TreeItem ti : staffTree.getItems()) ti.dispose();
		
		if (!firstRun) CohortData.loadData();
		firstRun = false;
		
		TreeItem newStaff = new TreeItem(staffTree, SWT.NONE);
		newStaff.setText(new String[] {"+", "Add New Staff member"});

		for (Staff s : CohortData.staff) {
			TreeItem staff = new TreeItem(staffTree, SWT.NONE);
			TreeItemMap.put(staff, new StringBuffer[]{s.staffID, s.firstName, s.lastName});
		}
	}

}