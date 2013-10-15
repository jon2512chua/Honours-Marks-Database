package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.GridLayout;


/**
 * Edit Assessment Section
 * @author Johnathan Lim
 */
public class DisplayCE_PopulateEditAssessment {
	private static Text assessmentName;
	private static Text percentageUnit;

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

	//TODO: 
		final Composite editAssessmentComposite = new Composite(CETabFolder, SWT.NONE);
		editAssessmentComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		GridLayout gl_editAssessmentComposite = new GridLayout(2, false);
		gl_editAssessmentComposite.horizontalSpacing = -1;
		gl_editAssessmentComposite.verticalSpacing = 0;
		gl_editAssessmentComposite.marginWidth = 0;
		gl_editAssessmentComposite.marginHeight = 0;
		editAssessmentComposite.setLayout(gl_editAssessmentComposite);
		
		final Tree unitTree = new Tree(editAssessmentComposite, SWT.BORDER | SWT.FULL_SELECTION);
		unitTree.setHeaderVisible(true);
		unitTree.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1));
		
		final Composite rComposite = new Composite(editAssessmentComposite, SWT.NONE);
		rComposite.setLayout(new GridLayout(2, false));
		rComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		final Label lblUnitName = new Label(rComposite, SWT.NONE);
		lblUnitName.setText("UNIT NAME");
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
		
		TreeColumn trclmnUnits = new TreeColumn(unitTree, SWT.NONE);
		trclmnUnits.setText("Units");
		
		TreeItem newUnit = new TreeItem(unitTree, SWT.NONE | SWT.NO_FOCUS);
		newUnit.setText("+  Add New Assessment");
		for (int i = 0; i < Data.Unit.length; i++) {
			TreeItem unit = new TreeItem(unitTree, SWT.NONE | SWT.NO_FOCUS);
			unit.setText(new String[] {Data.Unit[i]});
			for (int j = 0; j < 3; j++) {
				TreeItem assessment = new TreeItem(unit, SWT.NONE);
				assessment.setText(new String[] {Data.Assessment[j]});
			}
		}
		
		for (TreeItem ti : unitTree.getItems()) ti.setExpanded(true);
		for (TreeColumn tc : unitTree.getColumns()) tc.pack();
		unitTree.pack();
		//for (TreeItem ti : unitTree.getItems()) ti.setExpanded(false);
		
		DisplayReport.autoResizeColumn(unitTree);
		
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
		
		
		final Tree subAssessmentTree = new Tree(rComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
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
		
		for (int i = 0; i < 3; i++) {
			TreeItem subAssessment = new TreeItem(subAssessmentTree, SWT.NONE);
			subAssessment.setText(new String[] {Data.SubAssessment[i]});
		}
		
		for (TreeColumn tc : subAssessmentTree.getColumns()) tc.pack();
		unitTree.pack();

		
		unitTree.addListener(SWT.Selection,new Listener() {
			public void handleEvent(Event event) {
				TreeItem[] selected = unitTree.getSelection();
				if (selected.length != 0) {
					if (unitTree.indexOf(unitTree.getSelection()[0]) == 0) {
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
							lblUnitName.setText(selected[0].getParentItem().getText());
							assessmentName.setText(selected[0].getText());
						}
					}
				}
			}
		});
		
		Listener LisAddAssessment = new Listener() {
			public void handleEvent(Event event) {
				if (unitTree.indexOf(unitTree.getSelection()[0]) == 0) {
					if (!assessmentName.getText().isEmpty() && !percentageUnit.getText().isEmpty()) {
						PopupWindow.popupAddAssessment(editAssessmentComposite.getShell(), 
								"Please choose the unit that the Assessment will be added to", 
								"Adding Assessment", unitTree, assessmentName, percentageUnit);
					} else PopupWindow.popupMessage(editAssessmentComposite.getShell(), "Null Value is not allowed.", "ERROR!");
				} else {
					//TODO Save
				}
			}
		};
		
		Listener LisRemoveAssessment = new Listener() {
			public void handleEvent(Event event) {
				if (unitTree.getSelection().length != 0) {
					if (unitTree.getSelection()[0] != null)
						if (PopupWindow.popupYessNo(editAssessmentComposite.getShell(),
							"Are you sure you want to REMOVE \"" + unitTree.getSelection()[0].getText()
							+ "\" from the database", "WARNING!"))
						unitTree.getSelection()[0].dispose(); //TODO proper delete from database
				} else PopupWindow.popupMessage(editAssessmentComposite.getShell(), "Please select an Assessment to be Removed.", "ERROR!");
			}
		};
		
		Listener LisAddSubAssessment = new Listener() {
			public void handleEvent(Event event) {
				PopupWindow.popupAddSubAssessment(editAssessmentComposite.getShell(), 
						"Please fill in the Sub Assessment Details", "Adding Sub Assessment", subAssessmentTree);
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
		Button[] btnSaveDiscard = CommonButtons.addSaveDiscardChangesButton(rComposite);
		btnSaveDiscard[0].addListener(SWT.Selection, LisAddAssessment);
		btnSaveDiscard[1].addListener(SWT.Selection, LisRemoveAssessment);
		
		tbtmEditAssessment.setControl(editAssessmentComposite);
		
	}
}
