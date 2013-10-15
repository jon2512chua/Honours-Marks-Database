package gui;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

import logic.CohortData;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import orm.Assessment;
import orm.Mark;
import orm.Student;
import orm.SubAssessment;
import orm.Unit;

/**
 * Student Oriented Entering Marks View
 * @author Tim Lander
 */
public class DisplayEnterMarks_PopulateStudentView {
	private static Map<TreeItem, StringBuffer[]> TreeItemMap = new HashMap<TreeItem, StringBuffer[]>();
	public static Boolean hardRefreshNeeded = true;
	public static Boolean populateMarkersNeeded = true;

	/**
	 * Populates the entering marks - student orientation Tab
	 * @param marksTabFolder the folder to put the tab in
	 * @param tabName the name of the tab
	 * @wbp.parser.entryPoint
	 */
	public static void populate(CTabFolder marksTabFolder, String tabName) {
		CTabItem tbtmReport = new CTabItem(marksTabFolder, SWT.NONE);
		tbtmReport.setText(tabName);

		Composite mainComposite = new Composite(marksTabFolder, SWT.NONE);
		GridLayout gl_mainComposite = new GridLayout(1, false);
		gl_mainComposite.horizontalSpacing = 0;
		gl_mainComposite.verticalSpacing = 0;
		gl_mainComposite.marginWidth = 0;
		gl_mainComposite.marginHeight = 0;
		mainComposite.setLayout(gl_mainComposite);

		Composite studentUnitSelectionComposite = new Composite(mainComposite, SWT.NONE);
		studentUnitSelectionComposite.setLayout(new RowLayout(SWT.HORIZONTAL));
		studentUnitSelectionComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));

		//TODO: make autoupdate
		final Combo studentCombo = new Combo(studentUnitSelectionComposite, SWT.READ_ONLY);
		try {
			for (Student s : CohortData.students)
				studentCombo.add("[" + s.studentID + "] " + s.getFullName());
		} catch (java.lang.NullPointerException e) {}

		final Combo unitCombo = new Combo(studentUnitSelectionComposite, SWT.READ_ONLY);

		final Tree marksTree = new Tree(mainComposite, SWT.BORDER);
		marksTree.setLinesVisible(true);
		marksTree.setHeaderVisible(true);
		marksTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tbtmReport.setControl(mainComposite);

		TreeColumn trclmnAssess = new TreeColumn(marksTree, SWT.NONE);
		trclmnAssess.setText("");	//TODO: may be unneeded
		TreeColumn trclmnMean = new TreeColumn(marksTree, SWT.NONE);
		trclmnMean.setText("Mean");
		TreeColumn trclmnRange = new TreeColumn(marksTree, SWT.NONE);
		trclmnRange.setText("Range");
		TreeColumn trclmnSD = new TreeColumn(marksTree, SWT.NONE);
		trclmnSD.setText("Standard Deviation");
		TreeColumn trclmnMaxMark = new TreeColumn(marksTree, SWT.NONE);
		trclmnMaxMark.setText("Max Mark");

		//Student Selection Listener
		studentCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				unitCombo.removeAll();
				String studentNumber = studentCombo.getItem(studentCombo.getSelectionIndex()).substring(1, 9);
				try {
					for (Unit u : Student.getStudentByID(studentNumber).discipline)
						unitCombo.add("[" + u.unitCode + "] " + u.name);
				} catch (java.lang.NullPointerException NPE) {}
			}
		});

		//Unit Selection Listener
		unitCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {


				refreshTree(marksTree, studentCombo);
				for ( TreeColumn tc : marksTree.getColumns() ) tc.pack();
			}
		});

	}

	//Messes up autoupdating, but at least it *should* work.
	private static StringBuffer[] concatenateStringBufferArray(StringBuffer[] A, StringBuffer[] B) {
		int aLen = A.length;
		int bLen = B.length;

		StringBuffer[] C = (StringBuffer[]) Array.newInstance(A.getClass().getComponentType(), aLen+bLen);
		System.arraycopy(A, 0, C, 0, aLen);
		System.arraycopy(B, 0, C, aLen, bLen);

		return C;
	}

	/**
	 * Refreshes all data displayed in the tree
	 * @param tree the tree which is to be refreshed
	 */
	public static void refreshTree(Tree tree, Combo studentCombo) {
		if (hardRefreshNeeded) {
			for (TreeItem ti : tree.getItems()) ti.dispose();
			hardRefresh(tree, studentCombo);
			hardRefreshNeeded = false;
		}
		for ( TreeItem ti : tree.getItems() ) {
			int i = 0;
			for (StringBuffer sb : TreeItemMap.get(ti)) {
				ti.setText(i++, sb.toString());
			}

			refreshLevel(ti);
		}
	}

	/**
	 * Called recursively by refreshAll(), to refresh groups of data
	 * @param parent the TreeItem whose children are to be refreshed.
	 */
	private static void refreshLevel(TreeItem parent) {
		for ( TreeItem ti : parent.getItems() ) {

			int i = 0;
			for (StringBuffer sb : TreeItemMap.get(ti)) {
				ti.setText(i++, sb.toString());
			}


			if (ti.getItemCount() > 0)
				refreshLevel (ti);
		}
	}

	private static void hardRefresh(Tree marksTree, Combo studentCombo) {

		String studentNumber = studentCombo.getItem(studentCombo.getSelectionIndex()).substring(1, 9);
		for (Unit u : Student.getStudentByID(studentNumber).discipline) {
			TreeItem unit = new TreeItem(marksTree, SWT.NONE);
			TreeItemMap.put(unit, new StringBuffer[]{u.name, u.mark});

			for (Assessment a : u.getAssessments()) {
				TreeItem assessment = new TreeItem(unit, SWT.NONE);
				TreeItemMap.put(assessment, new StringBuffer[]{a.name, a.mark});

				for (SubAssessment sa : a.getSubAssessments()) {
					TreeItem subAssessment = new TreeItem(assessment, SWT.NONE);					
					StringBuffer[] strBuf = new StringBuffer[]{sa.name, sa.aveMark, new StringBuffer("TODO"), new StringBuffer("TODO"), sa.maxMark};
					for (Mark m : sa.getMarks()) {
						strBuf = concatenateStringBufferArray(strBuf, new StringBuffer[]{m.value});

						if (populateMarkersNeeded) {
							TreeColumn trclmnMarker = new TreeColumn(marksTree, SWT.NONE);
							trclmnMarker.setText(m.markerID.toString());
						}
					}
					TreeItemMap.put(subAssessment, strBuf);
				}
				populateMarkersNeeded = false;
			}

		}
	}
}
