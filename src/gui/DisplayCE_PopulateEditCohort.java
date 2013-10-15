package gui;

import java.util.Calendar;
import java.util.List;

import logic.CohortData;

import newCohort.CohortCreator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import orm.Assessment;
import orm.Staff;
import orm.SubAssessment;
import orm.Unit;

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

		final Spinner spinnerYear = new Spinner(compositeChooseSemester, SWT.BORDER);
		spinnerYear.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
		spinnerYear.setMaximum(2100);
		spinnerYear.setMinimum(2000);
		spinnerYear.setTextLimit(4);
		spinnerYear.setSelection(Calendar.getInstance().get(Calendar.YEAR));	//Defaults to current year

		final Combo comboSemester = new Combo(compositeChooseSemester, SWT.READ_ONLY);
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

		final Button btnUnits = new Button(compositeChkList, SWT.CHECK);
		btnUnits.setText("Units");
		
		// Have added listeners below: because of the schema of DB we can't add sub without assess, can't add assess without unit
		
		// When units unchecked, should uncheck other boxes
		btnUnits.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if(!btnUnits.getSelection()) {
					for (Control button : compositeChkList.getChildren()) {
						Button b = ((Button) button); 
						if(b.getText().equals("Assessments") || b.getText().equals("Subassessments")) b.setSelection(false);
					}
				}
			}
		});

		final Button btnAssessments = new Button(compositeChkList, SWT.CHECK);
		btnAssessments.setText("Assessments");

		// When assessment unchecked, should uncheck subassessment
		// When assessment checked, should check unit
		btnAssessments.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if(!btnAssessments.getSelection()) {
					for (Control button : compositeChkList.getChildren()) {
						Button b = ((Button) button); 
						if(b.getText().equals("Subassessments")) b.setSelection(false);
					}
				}
				else {
					for (Control button : compositeChkList.getChildren()) {
						Button b = ((Button) button); 
						if(b.getText().equals("Units")) b.setSelection(true);
					}
				}
			}
		});
		
		final Button btnSubassessments = new Button(compositeChkList, SWT.CHECK);
		btnSubassessments.setText("Subassessments");
		
		// When subassessment checked, should check unit, assessment
		btnSubassessments.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if(btnSubassessments.getSelection()) {
					for (Control button : compositeChkList.getChildren()) {
						Button b = ((Button) button); 
						if(b.getText().equals("Assessments") || b.getText().equals("Units")) b.setSelection(true);
					}
				}
			}
		});		

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
		List<Staff> allStaff = Staff.getAllStaff();
		for (Staff s : allStaff) {
			TreeItem supervisor = new TreeItem(staffTree, SWT.NONE);
			supervisor.setText(new String[] {String.valueOf(s.getStaffID()), String.valueOf(s.getFullName())});
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

		Composite compositeSpacer = new Composite(editCohortComposite, SWT.NONE);
		compositeSpacer.setLayout(new GridLayout(1, false));
		compositeSpacer.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1));
		

		Button[] btnCreateDiscard = CommonButtons.addCreateDiscardChangesButton(editCohortComposite);
		
		btnCreateDiscard[0].addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				String[] cohorts = sessionControl.Session.getCohorts();
				String newCohort = spinnerYear.getText() + (comboSemester.getSelectionIndex() + 1);
				// Check unique
				boolean existant = false;
				for (String c : cohorts) {if(c.equals(newCohort)) existant = true;}
				
				// If the proposed cohort is unique...
				if(!existant) {
					try {
						if(CohortCreator.create(newCohort))
						{
							try {
								Boolean pureSuccess = true;
								String feedback = "Congratulations, " + newCohort + " added successfully!\n" +
										"Please quit and log back in to open the new cohort.";
								if(!(CohortData.units == null) && !CohortData.units.isEmpty()) {
									if(btnUnits.getSelection()) {
										for (Unit u : CohortData.units) {
											CohortData.writeUnit(u, CohortCreator.newCohort.newConn.getConnection());
										}
									}
									if(!(CohortData.assessments == null) && !CohortData.assessments.isEmpty()) {
										if(btnAssessments.getSelection()) {
											for (Assessment a : CohortData.assessments) {
												CohortData.writeAssessment(a, CohortCreator.newCohort.newConn.getConnection());
											}
										}
										if(!(CohortData.subassessments == null) && !CohortData.subassessments.isEmpty()) {
											if(btnSubassessments.getSelection()) {
												for (SubAssessment s : CohortData.subassessments) {
													CohortData.writeSubassessment(s, CohortCreator.newCohort.newConn.getConnection());
												}
											}
										}
										else {
											feedback = "Cohort has been created, but no subassessments were available.\nPlease exit and log back in to set up the new cohort.";
											pureSuccess = false;
										}
									}
									else {
										feedback = "Cohort has been created, but no assessments were available.\nPlease exit and log back in to set up the new cohort.";
										pureSuccess = false;
									}
								}
								else {
									feedback = "Cohort has been created, but no units were available.\nPlease exit and log back in to set up the new cohort.";
									pureSuccess = false;
								}
								
								for (TreeItem i : staffTree.getItems()) {
									if (i.getChecked()) {
										Staff s = Staff.getStaffByID(i.getText());
										CohortData.writeStaff(s, CohortCreator.newCohort.newConn.getConnection());
									}
								}
								CohortCreator.finaliseSetup();
								String label; 
								if (pureSuccess) label = "COHORT CREATED";
								else label = "CAUTION";
								PopupWindow.popupMessage(staffTree.getShell(), feedback, label);
								
							} catch (Exception e) {
								PopupWindow.popupMessage(staffTree.getShell(), "There was an error creating the new cohort." +
										"\nPlease delete the " + newCohort + " directory (if it exists) and try again.\n" + e, "WARNING");
								System.err.print(e);
								CohortCreator.finaliseSetup();
							}
						}
						else {
							PopupWindow.popupMessage(staffTree.getShell(), "There was an error creating the new cohort." +
									"\nPlease delete the " + newCohort + " directory (if it exists) and try again.", "WARNING");
							CohortCreator.finaliseSetup();
						}
						
					} catch (Exception e) {
						PopupWindow.popupMessage(staffTree.getShell(), "There was an error creating the new cohort." +
								"\nPlease delete the " + newCohort + " directory (if it exists) and try again.\n" + e, "WARNING");
						System.err.print(e);
						CohortCreator.finaliseSetup();
					}
				}
				else {
					PopupWindow.popupMessage(staffTree.getShell(), "Selected cohort was not created because it already exists.", "WARNING");
				}
			}
		});
		
	}
}
