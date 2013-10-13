package gui;

import java.util.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import orm.*;

/**
 * Student Report
 * @author Tim Lander
 */
public class DisplayReport_PopulateStudent {
	private static Map<TreeItem, StringBuffer> TreeItemMap = new HashMap<TreeItem, StringBuffer>();

	/**
	 * Populates the Student Report
	 * @param reportTabFolder the folder to put the tab in
	 * @param tabName the name of the tab
	 * @wbp.parser.entryPoint
	 */
	public static void populate(final CTabFolder reportTabFolder, String tabName) {
		CTabItem tbtmReport = new CTabItem(reportTabFolder, SWT.NONE);
		tbtmReport.setText(tabName);

		Composite mainComposite = new Composite(reportTabFolder, SWT.NONE);
		GridLayout gl_mainComposite = new GridLayout(1, false);
		gl_mainComposite.horizontalSpacing = 0;
		gl_mainComposite.verticalSpacing = 0;
		gl_mainComposite.marginWidth = 0;
		gl_mainComposite.marginHeight = 0;
		mainComposite.setLayout(gl_mainComposite);

		Button[] treeTop = CommonButtons.addReportTreeTop(mainComposite);

		Composite treeComposite = new Composite(mainComposite, SWT.NONE);
		treeComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
		treeComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		final Tree studentTree = DisplayReport.createReportTree(treeComposite, "Selection", "Data");
		tbtmReport.setControl(mainComposite);

		try {	//TODO: better error handling

			List<Student> allStudents = Student.getAllStudents();
			for (Student s : allStudents) {
				TreeItem student = new TreeItem(studentTree, SWT.NONE);
				TreeItemMap.put(student, s.studentID);

				TreeItem studentNameTitle = new TreeItem(student, SWT.NONE);
				TreeItemMap.put(studentNameTitle, s.title);
				studentNameTitle.setText(0, "Title");

				TreeItem studentNameLast = new TreeItem(student, SWT.NONE);
				TreeItemMap.put(studentNameLast, s.lastName);
				studentNameLast.setText(0, "Last Name");

				TreeItem studentNameFirst = new TreeItem(student, SWT.NONE);
				TreeItemMap.put(studentNameFirst, s.firstName);
				studentNameFirst.setText(0, "First Name");

				TreeItem studentDissTitle = new TreeItem(student, SWT.NONE);
				TreeItemMap.put(studentDissTitle, s.dissTitle);
				studentDissTitle.setText(0, "Dissertation Title");

				for (Staff supervisor : s.supervisors) {
					TreeItem studentSuper = new TreeItem(student, SWT.NONE);
					TreeItemMap.put(studentSuper, new StringBuffer (supervisor.getFullName()));	//TODO: fix for stringbuffer
					studentSuper.setText(0, "Supervisor");
				}

				List<Unit> units = s.getDiscipline();
				for (Unit u : units) {
					TreeItem unit = new TreeItem(student, SWT.NONE);
					TreeItemMap.put(unit, u.unitCode);

					TreeItem unitName = new TreeItem(unit, SWT.NONE);
					TreeItemMap.put(unitName, u.name);
					unitName.setText(0, "Unit Name");

					TreeItem unitPoints = new TreeItem(unit, SWT.NONE);
					TreeItemMap.put(unitPoints, u.points);
					unitPoints.setText(0, "Unit Points");

					TreeItem unitMarks = new TreeItem(unit, SWT.NONE);
					TreeItemMap.put(unitMarks, u.mark);
					unitMarks.setText(0, "Unit Mark");

					List<Assessment> assessments = u.getAssessments();
					for (Assessment a : assessments) {
						TreeItem assessment = new TreeItem(unit, SWT.NONE);
						TreeItemMap.put(assessment, a.name);
						
						TreeItem assessmentPercentUnit = new TreeItem(assessment, SWT.NONE);
						TreeItemMap.put(assessmentPercentUnit, a.unitPercent);
						assessmentPercentUnit.setText(0, "Precent of Unit");
						
						TreeItem assessmentMark = new TreeItem(assessment, SWT.NONE);
						TreeItemMap.put(assessmentMark, a.mark);
						assessmentMark.setText(0, "Mark");
						
						List<SubAssessment> subAssessments = a.getSubAssessments();
						for (SubAssessment sa : subAssessments) {
							TreeItem subAssessment = new TreeItem(assessment, SWT.NONE);
							TreeItemMap.put(subAssessment, sa.name);
							
							TreeItem subAssessmentsPercentAssessment = new TreeItem(subAssessment, SWT.NONE);
							TreeItemMap.put(subAssessmentsPercentAssessment, sa.assessmentPercent);
							subAssessmentsPercentAssessment.setText(0, "Precent of Assessment");
							
							TreeItem subAssessmentMaxMark = new TreeItem(subAssessment, SWT.NONE);
							TreeItemMap.put(subAssessmentMaxMark, sa.maxMark);
							subAssessmentMaxMark.setText(0, "Maximum Mark");
						}
					}
				}

			}


		} catch (java.lang.NullPointerException e) {
			System.out.println("Whoops, null pointer exception");
			e.printStackTrace();
		}

		//Listener to automatically resize Student Report column widths.
		DisplayReport.autoResizeColumn(studentTree);

		//Listener for + button
		treeTop[0].addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				for ( TreeItem ti : studentTree.getItems() ) ti.setExpanded(true);
			}
		});

		//Listener for - button
		treeTop[1].addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				for ( TreeItem ti : studentTree.getItems() ) ti.setExpanded(false);
			}
		});

		//Listener for Export button
		treeTop[1].addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				//TODO: export here
			}
		});
		
		//Listener to auto-update displayed data (currently untested)
		refreshAll(studentTree);
		reportTabFolder.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				refreshAll(studentTree);
			}
		});

	}

	/**
	 * Refreshes all data displayed in the tree
	 * @param tree the tree which is to be refreshed
	 */
	public static void refreshAll(Tree tree) {
		for ( TreeItem ti : tree.getItems() ) {
			ti.setText(TreeItemMap.get(ti).toString());
			
			refreshLevel(ti);
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

}