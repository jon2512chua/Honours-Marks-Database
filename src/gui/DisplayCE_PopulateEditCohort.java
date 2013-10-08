package gui;

import java.util.Calendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Edit Cohort Section
 * @author Tim Lander
 */
public class DisplayCE_PopulateEditCohort {
	/**
	 * Populates the Edit Assessment Tab
	 * @param CETabFolder the folder to put the tab in
	 * @param tabName the name of the tab
	 * @wbp.parser.entryPoint
	 */
	public static void populate(final CTabFolder CETabFolder, String tabName) {
		CTabItem tbtmEditCohort = new CTabItem(CETabFolder, SWT.NONE);
		tbtmEditCohort.setText(tabName);
		final Composite editCohortComposite = new Composite(CETabFolder, SWT.NONE);
		editCohortComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		GridLayout gl_editCohortComposite = new GridLayout(1, false);
		editCohortComposite.setLayout(gl_editCohortComposite);
		tbtmEditCohort.setControl(editCohortComposite);

		Composite compositeChooseSemester = new Composite(editCohortComposite, SWT.BORDER);
		GridLayout gl_compositeChooseSemester = new GridLayout(3, false);
		compositeChooseSemester.setLayout(gl_compositeChooseSemester);
		compositeChooseSemester.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));

		Label lblNewCohort = new Label(compositeChooseSemester, SWT.NONE);
		lblNewCohort.setText("New Cohort:");

		Spinner spinnerYear = new Spinner(compositeChooseSemester, SWT.BORDER);
		spinnerYear.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
		spinnerYear.setMaximum(2100);
		spinnerYear.setMinimum(2000);
		spinnerYear.setTextLimit(4);
		spinnerYear.setSelection(Calendar.getInstance().get(Calendar.YEAR));	//Defaults to current year

		Combo comboSemester = new Combo(compositeChooseSemester, SWT.READ_ONLY);
		comboSemester.add("Semester 1");
		comboSemester.add("Semester 2");
		comboSemester.select(1);

		Composite compositeChooseImportData = new Composite(editCohortComposite, SWT.BORDER);
		compositeChooseImportData.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		compositeChooseImportData.setLayout(new GridLayout(3, false));

		Label lblImportFromPrevious = new Label(compositeChooseImportData, SWT.NONE);
		lblImportFromPrevious.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblImportFromPrevious.setText("Import the following from the previous semester:");

		final Composite compositeChkList = new Composite(compositeChooseImportData, SWT.BORDER);
		compositeChkList.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 2));
		GridLayout gl_compositeChkList = new GridLayout(1, false);
		gl_compositeChkList.verticalSpacing = 0;
		compositeChkList.setLayout(gl_compositeChkList);

		Button btnUnits = new Button(compositeChkList, SWT.CHECK);
		btnUnits.setText("Units");

		Button btnAssessments = new Button(compositeChkList, SWT.CHECK);
		btnAssessments.setText("Assessments");

		Button btnSubassessments = new Button(compositeChkList, SWT.CHECK);
		btnSubassessments.setText("SubAssessments");

		Button btnDataSelectAll = new Button(compositeChooseImportData, SWT.NONE);
		btnDataSelectAll.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnDataSelectAll.setText("Select All");
		new Label(compositeChooseImportData, SWT.NONE);
		btnDataSelectAll.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				for (Control button : compositeChkList.getChildren()) ((Button) button).setSelection(true);
			}
		});

		Button btnDataSelectNone = new Button(compositeChooseImportData, SWT.NONE);
		btnDataSelectNone.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnDataSelectNone.setText("Select None");
		new Label(compositeChooseImportData, SWT.NONE);
		btnDataSelectNone.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				for (Control button : compositeChkList.getChildren()) ((Button) button).setSelection(false);
			}
		});	

		Composite compositeChooseImportStaff = new Composite(editCohortComposite, SWT.BORDER);
		compositeChooseImportStaff.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		compositeChooseImportStaff.setLayout(new GridLayout(3, false));

		Label lblImportStaffFrom = new Label(compositeChooseImportStaff, SWT.NONE);
		lblImportStaffFrom.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 3, 1));
		lblImportStaffFrom.setText("Import the following staff from the previous semester:");



		final Tree staffTree = new Tree(compositeChooseImportStaff, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		staffTree.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 2));
		staffTree.setHeaderVisible(true);
		TreeColumn staffTree_staffNumber= new TreeColumn(staffTree, SWT.LEFT);
		staffTree_staffNumber.setText("Staff Number");
		TreeColumn staffTree_staffName = new TreeColumn(staffTree, SWT.LEFT);
		staffTree_staffName.setText("Staff Name");
		for (int sn=0; sn<5; sn++) {
			TreeItem supervisor = new TreeItem(staffTree, SWT.NONE);
			supervisor.setText(new String[] {Data.StaffNumber[sn], Data.StaffNameTitle[sn] + " " + Data.StaffNameFirst[sn].charAt(0) + ". " + Data.StaffNameLast[sn]});
		}

		for (TreeColumn tc : staffTree.getColumns()) tc.pack();
		staffTree.pack();

		Button btnStaffSelectAll = new Button(compositeChooseImportStaff, SWT.NONE);
		btnStaffSelectAll.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnStaffSelectAll.setText("Select All");
		new Label(compositeChooseImportStaff, SWT.NONE);
		btnStaffSelectAll.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				for (TreeItem ti: staffTree.getItems()) ti.setChecked(true);
			}
		});

		Button btnStaffSelectNone = new Button(compositeChooseImportStaff, SWT.NONE);
		btnStaffSelectNone.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		btnStaffSelectNone.setText("Select None");
		new Label(compositeChooseImportStaff, SWT.NONE);
		btnStaffSelectNone.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				for (TreeItem ti: staffTree.getItems()) ti.setChecked(false);
			}
		});


		Composite compositeImportFromExecel = new Composite(editCohortComposite, SWT.BORDER);
		compositeImportFromExecel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		compositeImportFromExecel.setLayout(new GridLayout(1, false));

		final Button btnImportStudentsFrom = new Button(compositeImportFromExecel, SWT.NONE);
		btnImportStudentsFrom.setText("Import Students from Excel");
		btnImportStudentsFrom.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				FileDialog fd = new FileDialog(btnImportStudentsFrom.getShell(), SWT.OPEN);
				fd.setText("Import From Excel");
				fd.setFilterPath(System.getProperty("user.home"));	//TODO: test on mac
				fd.setFilterExtensions(new String[]{ "*.xlsx", "*.xls", "*.*" });
				String selected = fd.open();

				//TODO: importing. Returns null if cancel is pressed
				System.out.println(selected);	//TODO: delete me
			}
		});

		Composite compositeSpacer = new Composite(editCohortComposite, SWT.NONE);
		compositeSpacer.setLayout(new GridLayout(1, false));
		compositeSpacer.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1));
		
		CommonButtons.addCreateDiscardChangesButton(editCohortComposite);
		

	}
}
