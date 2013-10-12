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
		
		Composite rComposite = new Composite(editAssessmentComposite, SWT.NONE);
		rComposite.setLayout(new GridLayout(2, false));
		rComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblUnitName = new Label(rComposite, SWT.NONE);
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
		
		TreeColumn trclmnUnits = new TreeColumn(unitTree, SWT.NONE);
		trclmnUnits.setText("Units");
		
		for (int i = 0; i < 5; i++) {
			TreeItem unit = new TreeItem(unitTree, SWT.NONE | SWT.NO_FOCUS);
			unit.setText(new String[] {Data.Unit[i]});
			unit.setGrayed(true);
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
		
		Composite composite = new Composite(rComposite, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, false, false, 1, 1));
		composite.setLayout(new GridLayout(2, false));
		
		Button btnAddSubAssessment = new Button(composite, SWT.NONE);
		btnAddSubAssessment.setText("Add Sub Assessment");
		
		Button btnRemoveSubAssessment = new Button(composite, SWT.NONE);
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

		
		Listener LisAddSubAssessment = new Listener() {
			public void handleEvent(Event event) {
				PopupWindow.popupAddSubAssessment(editAssessmentComposite.getShell(), 
						"Please fill in the Sub Assessment Details", "Adding Sub Assessment", subAssessmentTree);
			}
		};

		btnAddSubAssessment.addListener(SWT.Selection, LisAddSubAssessment);
		
		Listener LisRemoveSubAssessment = new Listener() {
			public void handleEvent(Event event) {
				subAssessmentTree.getSelection()[0].dispose();
				/*
				PopupWindow.popupAddSubAssessment(editAssessmentComposite.getShell(), 
						"Please fill in the Sub Assessment Details", "Adding Sub Assessment", subAssessmentTree);
						*/
			}
		};
		btnRemoveSubAssessment.addListener(SWT.Selection, LisRemoveSubAssessment);
		
		@SuppressWarnings("unused")	//TODO: remove later
		Button[] btnSaveDiscard = CommonButtons.addSaveDiscardChangesButton(rComposite);
		tbtmEditAssessment.setControl(editAssessmentComposite);
		
	}
}
