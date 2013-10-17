package gui;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import logic.CohortData;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.GridLayout;

import orm.Assessment;
import orm.SubAssessment;
import orm.Unit;

/**
 * Edit Assessment Section
 * @author Johnathan Lim
 */
public class DisplayCE_PopulateEditAssessment {
	
	private static Map<TreeItem, StringBuffer> TreeItemMap = new HashMap<TreeItem, StringBuffer>();
	public static Boolean hardRefreshNeeded = true;
	
	private static Text assessmentName;
	private static Text percentageUnit;
	
	private static Tree assessmentTree;
	private static Tree subAssessmentTree;
	private static boolean firstRun = true;

	/**
	 * helper class to disable and grayed out anything in the composite
	 * that is not subclass of composite, therefore doesn't work with tree etc.
	 * @param ctrl the composite that needs to be disabled
	 * @param enabled true as to enable false otherwise
	 */
	public static void recursiveSetEnabled(Control ctrl, boolean enabled) {
		if (ctrl instanceof Composite && !(ctrl instanceof Tree) && !(ctrl instanceof CTabFolder)) {
				Composite comp = (Composite) ctrl;
				for (Control c : comp.getChildren()) {
					recursiveSetEnabled(c, enabled);
				}
		} else {
			if ((ctrl instanceof CTabFolder)){
				CTabFolder cTabFold = (CTabFolder) ctrl;
				Composite comp = (Composite) ctrl;
				for (Control c : comp.getChildren()) {
					recursiveSetEnabled(c, enabled);
				}
				cTabFold.setEnabled(enabled);
			} else {
				ctrl.setEnabled(enabled);
			}
		}
	}
	
	/**
	 * Populates the Edit Assessment Tab
	 * @param CETabFolder the folder to put the tab in
	 * @param tabName the name of the tab
	 * @wbp.parser.entryPoint
	 */
	public static void populate(final CTabFolder CETabFolder, String tabName) {
		CTabItem tbtmEditAssessment = new CTabItem(CETabFolder, SWT.NONE);
		tbtmEditAssessment.setText(tabName);

		final Composite editAssessmentComposite = new Composite(CETabFolder, SWT.NONE);
		editAssessmentComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		GridLayout gl_editAssessmentComposite = new GridLayout(2, false);
		gl_editAssessmentComposite.horizontalSpacing = -1;
		gl_editAssessmentComposite.verticalSpacing = 0;
		gl_editAssessmentComposite.marginWidth = 0;
		gl_editAssessmentComposite.marginHeight = 0;
		editAssessmentComposite.setLayout(gl_editAssessmentComposite);
		
		assessmentTree = new Tree(editAssessmentComposite, SWT.BORDER | SWT.FULL_SELECTION);
		assessmentTree.setHeaderVisible(true);
		assessmentTree.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1));
		
		final Composite rComposite = new Composite(editAssessmentComposite, SWT.NONE);
		rComposite.setLayout(new GridLayout(2, false));
		rComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		final Label lblUnitName = new Label(rComposite, SWT.NONE);
		lblUnitName.setText("Add New Assessment");
		lblUnitName.pack();
		new Label(rComposite, SWT.NONE);
		
		Label lblName = new Label(rComposite, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblName.setText("Assessment Name:");
		
		assessmentName = new Text(rComposite, SWT.BORDER);
		assessmentName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label lblPercentageUnit = new Label(rComposite, SWT.NONE);
		lblPercentageUnit.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPercentageUnit.setText("Percentage Unit:");
		
		percentageUnit = new Text(rComposite, SWT.BORDER);
		percentageUnit.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		percentageUnit.setTextLimit(4);
		Validation.validateDouble(percentageUnit);
		
		TreeColumn trclmnUnits = new TreeColumn(assessmentTree, SWT.NONE);
		trclmnUnits.setText("Units/Assessments");
		
		
//		for (Unit u : CohortData.units) {
//			TreeItem unit = new TreeItem(assessmentTree, SWT.NONE);
//			TreeItemMap.put(unit, new StringBuffer[]{u.unitCode});
//			for (Assessment a : u.getAssessments()) {
//				TreeItem assessment = new TreeItem(unit, SWT.NONE);
//				System.out.println(a.name);
//				TreeItemMap.put(assessment, new StringBuffer[]{a.name});
//				//assessment.setText(a.getName()+"");
//			}
//		}

		
//		for (Unit u : CohortData.units) {
//			TreeItem unit = new TreeItem(assessmentTree, SWT.NONE);
//			unit.setText(u.getUnitCode().toString());
//			for (Assessment a : u.getAssessments()) {
//				TreeItem assessment = new TreeItem(unit, SWT.NONE);
//				assessment.setText(a.getName().toString());
//			}
//		}
		
		
		
		DisplayReport.autoResizeColumn(assessmentTree);
		
		Label label = new Label(rComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		
		Label lblSubAssessment = new Label(rComposite, SWT.NONE);
		lblSubAssessment.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1));
		lblSubAssessment.setText("Sub Assessment:");
		
		Composite addReSubACompposite = new Composite(rComposite, SWT.NONE);
		addReSubACompposite.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, false, false, 1, 1));
		addReSubACompposite.setLayout(new GridLayout(2, false));
		
		Button btnAddSubAssessment = new Button(addReSubACompposite, SWT.NONE);
		btnAddSubAssessment.setText("Add Sub Assessment");
		
		Button btnRemoveSubAssessment = new Button(addReSubACompposite, SWT.NONE);
		btnRemoveSubAssessment.setText("Remove Sub Assessment");
		
		
		subAssessmentTree = new Tree(rComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		subAssessmentTree.setHeaderVisible(true);
		subAssessmentTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		TreeColumn trclmnSubAssessmentName = new TreeColumn(subAssessmentTree, SWT.NONE);
		trclmnSubAssessmentName.setWidth(100);
		trclmnSubAssessmentName.setText("Assessment Name");
		
		TreeColumn trclmnMaximumMark = new TreeColumn(subAssessmentTree, SWT.NONE);
		trclmnMaximumMark.setWidth(100);
		trclmnMaximumMark.setText("Maximum Mark");
		
		TreeColumn trclmnAssessmentPercentage = new TreeColumn(subAssessmentTree, SWT.NONE);
		trclmnAssessmentPercentage.setWidth(100);
		trclmnAssessmentPercentage.setText("Assessment Percentage");
		
		for (TreeColumn tc : subAssessmentTree.getColumns()) tc.pack();
		assessmentTree.pack();
		
		//Accommodating Data into assessmentTree
		refreshTree();
		for (TreeItem ti : assessmentTree.getItems()) ti.setExpanded(true);
		for (TreeColumn tc : assessmentTree.getColumns()) tc.pack();
		assessmentTree.pack();
		//for (TreeItem ti : assessmentTree.getItems()) ti.setExpanded(false);

		//assessmentTree Listener
		assessmentTree.addListener(SWT.Selection,new Listener() {
			public void handleEvent(Event event) {
				TreeItem[] selected = assessmentTree.getSelection();
				if (selected.length != 0) {
					if (assessmentTree.indexOf(assessmentTree.getSelection()[0]) == 0) {
						recursiveSetEnabled(rComposite, true);
						assessmentName.setText("");
						percentageUnit.setText("");
						lblUnitName.setText("Add New Assessment");
						lblUnitName.pack();
					} else {
						if (selected[0].getParentItem() == null) {
							recursiveSetEnabled(rComposite, false);
						} else {
							recursiveSetEnabled(rComposite, true);
							Unit u = Unit.getUnitByCode(selected[0].getParentItem().getText());
							lblUnitName.setText(u.getUnitCode().toString());
							outer:
							for(Assessment a : u.getAssessments()) {
								if(selected[0].getText().equals(a.name+"")){
									for (TreeItem ti : subAssessmentTree.getItems()) ti.dispose();
									populateSelectedData(a);
									break outer;
								}
							}
						}
					}
				}
			}
		});
		
		Listener LisAddAssessment = new Listener() {
			public void handleEvent(Event event) {
				if (assessmentTree.getSelection().length == 0 || assessmentTree.indexOf(assessmentTree.getSelection()[0]) == 0) {
					if (!assessmentName.getText().isEmpty() && !percentageUnit.getText().isEmpty()) {
						PopupWindow.popupAddAssessment(editAssessmentComposite.getShell(), 
								"Please choose the unit that the Assessment will be added to", 
								"Adding Assessment", assessmentTree, assessmentName, percentageUnit);
					} else PopupWindow.popupMessage(editAssessmentComposite.getShell(), "Null Value is not allowed.", "ERROR!");
				} else {
					Assessment assess = Assessment.getAssessByCodeAndName(assessmentTree.getSelection()[0].getParentItem().getText(),
							assessmentTree.getSelection()[0].getText());
					assess.setName(assessmentName.getText());
					assess.setUnitPercent(Integer.parseInt(percentageUnit.getText()));
					//TODO save into sub assessment
					try {
						assess.updateRow();
					} catch (SQLException e) {
						PopupWindow.popupMessage(rComposite.getShell(), "Unable to save change. \nPossible corrupt data.", "ERROR! Save Unsuccessful");
					}
				}
			}
		};
		
		Listener LisRemoveAssessment = new Listener() {
			public void handleEvent(Event event) {
				if (assessmentTree.getSelection().length != 0) {
					if (assessmentTree.getSelection()[0] != null)
						if (PopupWindow.popupYessNo(editAssessmentComposite.getShell(),
								"Are you sure you want to REMOVE \"" + assessmentTree.getSelection()[0].getText()
								+ "\" from the database", "WARNING!"))
							try {
								Assessment.getAssessByCodeAndName(assessmentTree.getSelection()[0].getParentItem().getText(),
										assessmentTree.getSelection()[0].getText()).deleteRow();
								PopupWindow.popupMessage(editAssessmentComposite.getShell(), "Assessment removed.", "SUCCESS!");
								hardRefreshNeeded = true;
								refreshTree();
								assessmentName.setText("");
								percentageUnit.setText("");
							} catch (SQLException e) {
								PopupWindow.popupMessage(editAssessmentComposite.getShell(), "Assessment not removed.", "ERROR!");
							}
				} else PopupWindow.popupMessage(editAssessmentComposite.getShell(), "Please select an Assessment to be Removed.", "ERROR!");
			}
		};
		
		Listener LisAddSubAssessment = new Listener() {
			public void handleEvent(Event event) {
				PopupWindow.popupAddSubAssessment(editAssessmentComposite.getShell(), 
						"Please fill in the Sub Assessment Details", "Adding Sub Assessment", 
						subAssessmentTree, Assessment.getAssessByCodeAndName(lblUnitName.getText(), assessmentName.getText()));
				hardRefreshNeeded = true;
				refreshTree();
			}
		};

		btnAddSubAssessment.addListener(SWT.Selection, LisAddSubAssessment);
		
		Listener LisRemoveSubAssessment = new Listener() {
			public void handleEvent(Event event) {
				if (subAssessmentTree.isFocusControl() && PopupWindow.popupYessNo(editAssessmentComposite.getShell(),
						"Are you sure you want to REMOVE \"" + subAssessmentTree.getSelection()[0].getText()
						+ "\" from the database", "WARNING!")) {
					
					// Enable the previous window
					recursiveSetEnabled(editAssessmentComposite.getShell(), true);
					subAssessmentTree.getSelection()[0].dispose();
				}
				else PopupWindow.popupMessage(editAssessmentComposite.getShell(), "Please select a Sub Assessment to be Removed.", "ERROR!");
			}
		};
		btnRemoveSubAssessment.addListener(SWT.Selection, LisRemoveSubAssessment);
		Button[] btnSaveDiscard = CommonButtons.addSaveChangesDeleteButton(rComposite, "Assessment");
		btnSaveDiscard[0].addListener(SWT.Selection, LisAddAssessment);
		btnSaveDiscard[1].addListener(SWT.Selection, LisRemoveAssessment);
		
		tbtmEditAssessment.setControl(editAssessmentComposite);
		
	}
	
	/**
	 * Displays data relevant to which assessment was clicked on.
	 * @param student the student that was clicked on
	 */
	private static void populateSelectedData(Assessment assessment) {
		try {														//Found values
			for (TreeItem ti : subAssessmentTree.getItems()) ti.dispose();
			assessmentName.setText(assessment.getName()+"");
			percentageUnit.setText(assessment.getUnitPercent()+"");
			for (SubAssessment s : assessment.getSubAssessments()) {
				TreeItem subAssessment = new TreeItem(subAssessmentTree, SWT.NONE);
				subAssessment.setText(new String[] {s.getName()+"", s.getMaxMark()+"", s.getAssessmentPercent()+""});
				
			}

		} catch (java.lang.NullPointerException e) {				//Default values
			for (TreeItem ti : subAssessmentTree.getItems()) ti.dispose();
			assessmentName.setText("");
			percentageUnit.setText("");
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
		for ( TreeItem ti : assessmentTree.getItems() ) {
			try {
				ti.setText(TreeItemMap.get(ti).toString());

				refreshLevel(ti);
			} catch (java.lang.NullPointerException e) {}
		}
	}

	/**
	 * Called recursively by refreshAll(), to refresh groups of data
	 * @param parent the TreeItem whose children are to be refreshed.
	 */
	private static void refreshLevel(TreeItem parent) {
		for ( TreeItem ti : parent.getItems() ) {
			try {
				if (ti.getText(0).length() > 2)
					ti.setText(1, TreeItemMap.get(ti).toString());
				else ti.setText(0, TreeItemMap.get(ti).toString());
			} catch (java.lang.NullPointerException e) {
				ti.setText(1, "");
			}

			if (ti.getItemCount() > 0)
				refreshLevel (ti);
		}
	}
	
	/**
	 * Forced refresh on the tree
	 */
	private static void hardRefresh() {
		for (TreeItem ti : assessmentTree.getItems()) ti.dispose();
		for (TreeItem ti : subAssessmentTree.getItems()) ti.dispose();

		if (!firstRun) CohortData.loadData();
		firstRun = false;

		TreeItem newUnit = new TreeItem(assessmentTree, SWT.NONE);
		newUnit.setText("+  Add New Assessment");
		for (Unit u : CohortData.units) {
			TreeItem unit = new TreeItem(assessmentTree, SWT.NONE);
			TreeItemMap.put(unit, u.unitCode);
			for (Assessment a : u.getAssessments()) {
				TreeItem assessment = new TreeItem(unit, SWT.NONE);
				TreeItemMap.put(assessment, a.name);
			}
		}
		for (TreeItem ti : assessmentTree.getItems()) ti.setExpanded(true);
	}
}
