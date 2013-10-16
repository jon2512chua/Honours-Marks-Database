package gui;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import logic.CohortData;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TreeCursor;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import orm.Assessment;
import orm.Mark;
import orm.Student;
import orm.SubAssessment;
import orm.Unit;

import org.eclipse.swt.layout.RowData;

/**
 * Student Oriented Entering Marks View
 * @author Tim Lander
 */
public class DisplayEnterMarks_PopulateStudentView {
	private static Map<TreeItem, StringBuffer[]> TreeItemMap = new HashMap<TreeItem, StringBuffer[]>();
	public static Boolean hardRefreshNeeded = true;

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
		studentCombo.setLayoutData(new RowData(200, SWT.DEFAULT));
		try {
			for (Student s : CohortData.students)
				studentCombo.add("[" + s.studentID + "] " + s.getFullName());
		} catch (java.lang.NullPointerException e) {}

		final Combo unitCombo = new Combo(studentUnitSelectionComposite, SWT.READ_ONLY);
		unitCombo.setLayoutData(new RowData(200, SWT.DEFAULT));

		final Combo assessmentCombo = new Combo(studentUnitSelectionComposite, SWT.READ_ONLY);
		assessmentCombo.setLayoutData(new RowData(200, SWT.DEFAULT));

		final Tree marksTree = new Tree(mainComposite, SWT.BORDER);
		marksTree.setLinesVisible(true);
		marksTree.setHeaderVisible(true);
		marksTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tbtmReport.setControl(mainComposite);

		new TreeColumn(marksTree, SWT.NONE);
		TreeColumn trclmnMean = new TreeColumn(marksTree, SWT.NONE);
		trclmnMean.setText("Mean");
		TreeColumn trclmnRange = new TreeColumn(marksTree, SWT.NONE);
		trclmnRange.setText("Range");
		TreeColumn trclmnSD = new TreeColumn(marksTree, SWT.NONE);
		trclmnSD.setText("Standard Deviation");
		TreeColumn trclmnMaxMark = new TreeColumn(marksTree, SWT.NONE);
		trclmnMaxMark.setText("Max Mark");
		DisplayReport.autoResizeColumn(marksTree);

		//Student Combo Selection Listener
		studentCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				unitCombo.removeAll();
				assessmentCombo.removeAll();
				String studentNumber = studentCombo.getItem(studentCombo.getSelectionIndex()).substring(1, 9);
				try {
					for (Unit u : Student.getStudentByID(studentNumber).discipline)
						unitCombo.add("[" + u.unitCode + "] " + u.name);
				} catch (java.lang.NullPointerException NPE) {}
			}
		});

		//Unit Combo Selection Listener
		unitCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				assessmentCombo.removeAll();
				String studentNumber = studentCombo.getItem(studentCombo.getSelectionIndex()).substring(1, 9);
				try {
					for (Assessment a : Student.getStudentByID(studentNumber).discipline.toArray(new Unit[0])[unitCombo.getSelectionIndex()].getAssessments())
						assessmentCombo.add(a.name+"");
				} catch (java.lang.NullPointerException NPE) {}
			}
		});

		//Assessment Combo Selection Listener
		assessmentCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				hardRefreshNeeded = true;
				refreshTree(marksTree, studentCombo, unitCombo, assessmentCombo);
				for ( TreeColumn tc : marksTree.getColumns() ) tc.pack();
			}
		});

		//set up editing
		final TreeCursor cursor = new TreeCursor(marksTree, SWT.NONE);	//TODO: fix border
		cursor.setForeground(cursor.getDisplay().getSystemColor(SWT.COLOR_BLACK));

		final ControlEditor editor = new ControlEditor(cursor);
		editor.grabHorizontal = true;
		editor.grabVertical = true;

		//set up editing listeners
		cursor.addSelectionListener(new SelectionAdapter() {

			// when the TreeEditor is over a cell, select the corresponding row in the tree
			public void widgetSelected(SelectionEvent e) {
				if (cursor.getColumn() > 4 && cursor.getRow().getText(cursor.getColumn()) != "") {
					cursor.setBackground(cursor.getDisplay().getSystemColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
					marksTree.setSelection(new TreeItem[] { cursor.getRow() });
				} else {
					cursor.setBackground(cursor.getRow().getBackground(cursor.getColumn()));
					cursor.setForeground(cursor.getRow().getForeground(cursor.getColumn()));
				}
			}

			// when the user hits "ENTER" in the TreeCursor, pop up a text
			// editor so that they can change the text of the cell
			public void widgetDefaultSelected(SelectionEvent e) {
				if (cursor.getColumn() > 4 && cursor.getRow().getText(cursor.getColumn()) != "") {
					final Text text = new Text(cursor, SWT.NONE);
					Validation.validateDouble(text);

					TreeItem row = cursor.getRow();
					int column = cursor.getColumn();
					text.setText(row.getText(column));
					text.selectAll();
					text.setFocus();
					text.addKeyListener(new KeyAdapter() {
						public void keyPressed(KeyEvent e) {
							// close the text editor and copy the data over
							// when the user hits "ENTER"
							if (e.character == SWT.CR) {	//works for both enter keys
								TreeItem row = cursor.getRow();
								int column = cursor.getColumn();
								TreeItemMap.get(row)[column].replace(0, TreeItemMap.get(row)[column].capacity(), text.getText());

								//row.getText(column).

								//Quick and dirty way to save the students marks
								for (Student s : Student.getAllStudents()) {
									try {
										s.updateRow();
									} catch (SQLException e1) {}
								}

								text.dispose();
								//refresh(tree);
								//TODO: increment row
							}
							// close the text editor when the user hits "ESC"
							if (e.character == SWT.ESC) {
								text.dispose();
							}
							if (e.character == SWT.TAB) {
								//TODO: increment column
							}
						}
					});
					editor.setEditor(text);
					text.setFocus();


					text.addFocusListener(new FocusAdapter() {
						public void focusLost(FocusEvent event) {
							TreeItem row = cursor.getRow();
							int column = cursor.getColumn();

							TreeItemMap.get(row)[column].replace(0, TreeItemMap.get(row)[column].capacity(), text.getText());

							text.dispose();
						}
					});

				}
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
	 * @param studentCombo
	 * @param assessmentCombo 
	 */
	public static void refreshTree(Tree tree, Combo studentCombo, Combo unitCombo, Combo assessmentCombo) {
		if (hardRefreshNeeded) {
			for (TreeItem ti : tree.getItems()) ti.dispose();
			hardRefresh(tree, studentCombo, unitCombo, assessmentCombo);
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

	/**
	 * @param marksTree
	 * @param studentCombo
	 * @param assessmentCombo 
	 */
	private static void hardRefresh(Tree marksTree, Combo studentCombo, Combo unitCombo, Combo assessmentCombo) {
		Boolean populateMarkersNeeded = true;
		//remove old marker columns.
		int nStaticColumns = 5;
		for ( TreeColumn tc : marksTree.getColumns() ) {
			if (nStaticColumns-- <= 0)	//TODO: double check this is correct
				tc.dispose();
		}

		String studentNumber = studentCombo.getItem(studentCombo.getSelectionIndex()).substring(1, 9);
		for (Unit u : Student.getStudentByID(studentNumber).discipline) {
			if (u.unitCode.toString().equals( unitCombo.getItem(unitCombo.getSelectionIndex()).substring(1, 9) )) {

				TreeItem unit = new TreeItem(marksTree, SWT.NONE);
				TreeItemMap.put(unit, new StringBuffer[]{u.name, u.mark});

				for (Assessment a : u.getAssessments()) {
					if (a.name.toString().equals( assessmentCombo.getItem(assessmentCombo.getSelectionIndex()) )) {
						TreeItem assessment = new TreeItem(unit, SWT.NONE);
						TreeItemMap.put(assessment, new StringBuffer[]{a.name, a.mark});

						for (SubAssessment sa : a.getSubAssessments()) {
							TreeItem subAssessment = new TreeItem(assessment, SWT.NONE);					
							StringBuffer[] strBuf = new StringBuffer[]{sa.name, sa.aveMark, sa.getRange(), sa.standDev, sa.maxMark};
							for (Mark m : sa.getMarks()) {
								strBuf = concatenateStringBufferArray(strBuf, new StringBuffer[]{m.value});

								if (populateMarkersNeeded) {
									TreeColumn trclmnMarker = new TreeColumn(marksTree, SWT.NONE);
									trclmnMarker.setText(m.markerID.toString());
									trclmnMarker.setWidth(100);
								}
							}
							TreeItemMap.put(subAssessment, strBuf);
							populateMarkersNeeded = false;
							for (int i=0; i<5; i++)
								subAssessment.setBackground(i, marksTree.getDisplay().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
						}
						assessment.setExpanded(true);
						assessment.setBackground(marksTree.getDisplay().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
					}

				}
				unit.setExpanded(true);
				unit.setBackground(marksTree.getDisplay().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));

			}
		}
	}
}
