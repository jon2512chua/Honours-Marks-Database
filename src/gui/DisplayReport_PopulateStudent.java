package gui;

import java.util.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import orm.*;

/**
 * Student Report
 * @author Tim Lander
 */
public class DisplayReport_PopulateStudent {
	static Map<TreeItem, StringBuffer> TreeItemMap = new HashMap<TreeItem, StringBuffer>();

	/**
	 * Populates the Student Report
	 * @param reportTabFolder the folder to put the tab in
	 * @param tabName the name of the tab
	 * @wbp.parser.entryPoint
	 */
	public static void populate(final CTabFolder reportTabFolder, String tabName) {
		CTabItem tbtmReport = new CTabItem(reportTabFolder, SWT.NONE);
		tbtmReport.setText(tabName);

		Tree studentTree = DisplayReport.createReportTree(reportTabFolder, "Selection", "Data");
		tbtmReport.setControl(studentTree);

		try {	//TODO: better error handling

			List<Student> allStudents = Student.getAllStudents();
			for (Student s : allStudents) {
				TreeItem student = new TreeItem(studentTree, SWT.NONE);
				TreeItemMap.put(student, s.studentID);
				TreeItem studentNameTitle = new TreeItem(student, SWT.NONE);
				TreeItemMap.put(studentNameTitle, s.title);
				studentNameTitle.setText(0, "Title");
				TreeItem studentNameLast = new TreeItem(student, SWT.NONE);
				TreeItemMap.put(studentNameTitle, s.lastName);
				studentNameLast.setText(0, "Last Name");
				TreeItem studentNameFirst = new TreeItem(student, SWT.NONE);
				TreeItemMap.put(studentNameTitle, s.firstName);
				studentNameFirst.setText(0, "First Name");
				TreeItem studentDissTitle = new TreeItem(student, SWT.NONE);
				TreeItemMap.put(studentNameTitle, s.dissTitle);
				studentDissTitle.setText(0, "Dissertation Title");

				/*for (Staff supervisor : s.supervisors) {
					TreeItem studentSuper = new TreeItem(student, SWT.NONE);
					TreeItemMap.put(studentSuper, new StringBuffer ("TODO:stringbuffer staff"));
				}*/

				/*List<Unit> units = s.getDiscipline();
				for (Unit u : units) {
					TreeItem unit = new TreeItem(student, SWT.NONE);
					unit.setText(new String[] {u.getUnitCode()});
					TreeItem unitName = new TreeItem(unit, SWT.NONE);
					unitName.setText(new String[] {"Unit Name", u.getName()});
					TreeItem unitPoints = new TreeItem(unit, SWT.NONE);
					unitPoints.setText(new String[] {"Unit Points", u.getPoints()+""});
					TreeItem unitMarks = new TreeItem(unit, SWT.NONE);
					unitMarks.setText(new String[] {"Unit Mark", u.getMark()+""});


					List<Assessment> assessments = u.getAssessments();
					for (Assessment a : assessments) {
						TreeItem assessment = new TreeItem(unit, SWT.NONE);
						assessment.setText(new String[] {a.getAssessmentID()});
					}
				}*/

			}


		} catch (java.lang.NullPointerException e) {
			System.out.println("Whoops, null pointer exception");
			e.printStackTrace();
		}

		/*for (int sn=0; sn<5; sn++) {
			TreeItem student = new TreeItem(studentTree, SWT.NONE);
			student.setText(new String[] {Data.StudentNumber[sn]});
			TreeItem studentNameTitle = new TreeItem(student, SWT.NONE);		//TODO: in theory these could be combined into one row
			studentNameTitle.setText(new String[] {"Title", Data.StudentNameTitle[sn]});
			TreeItem studentNameLast = new TreeItem(student, SWT.NONE);
			studentNameLast.setText(new String[] {"Last Name", Data.StudentNameLast[sn]});
			TreeItem studentNameFirst = new TreeItem(student, SWT.NONE);
			studentNameFirst.setText(new String[] {"First Name", Data.StudentNameFirst[sn]});
			TreeItem studentDissTitle = new TreeItem(student, SWT.NONE);
			studentDissTitle.setText(new String[] {"Dissertation Title", Data.StudentDissTitle[sn]});
			TreeItem studentSuper = new TreeItem(student, SWT.NONE);
			studentSuper.setText(new String[] { "Supervisor", Data.StaffNameTitle[sn] + " " + Data.StaffNameFirst[sn].charAt(0) + ". " + Data.StaffNameLast[sn]});

			for (int units=sn/2; units<=sn/2+3; units++) {
				TreeItem unit = new TreeItem(student, SWT.NONE);
				unit.setText(new String[] {Data.Unit[units]});
				TreeItem unitName = new TreeItem(unit, SWT.NONE);
				unitName.setText(new String[] {"Unit Name", Data.UnitName[units]});
				TreeItem studentUnitMark = new TreeItem(unit, SWT.NONE);
				studentUnitMark.setText(new String[] {"Students Unit Mark", "100%"});
				TreeItem studentUnitGrade = new TreeItem(unit, SWT.NONE);
				studentUnitGrade.setText(new String[] {"Students Unit Grade", "HD"});
				TreeItem unitPoints = new TreeItem(unit, SWT.NONE);
				unitPoints.setText(new String[] {"Unit Points", "6"});

				for (int assessments=0; assessments<4; assessments++) {
					TreeItem assessment = new TreeItem(unit, SWT.NONE);
					assessment.setText(new String[] {Data.Assessment[assessments]});
					TreeItem studentAssessmentMark = new TreeItem(assessment, SWT.NONE);
					studentAssessmentMark.setText(new String[] {"Student Assessment Mark", (10*Data.marks[sn*5+units*4+assessments])+"%"});
					TreeItem AssessmentPercentUnit = new TreeItem(assessment, SWT.NONE);
					AssessmentPercentUnit.setText(new String[] {"Percent of Unit", "22%"});
					TreeItem AssessmentPercentFinalGrade = new TreeItem(assessment, SWT.NONE);
					AssessmentPercentFinalGrade.setText(new String[] {"Percent of Final Grade", "5%"});
					//TODO: add in markers. May be a bit tricky, as the number of markers are variable.
				}

			}

			//student.setExpanded(true);		//TODO:Perhaps add an expand all button?
		}*/

		refreshAll(studentTree);
		
		//Listener to automatically resize Student Report column widths.
		DisplayReport.autoResizeColumn(studentTree);

	}

	public static void refreshAll(Tree tree) {
		for ( TreeItem ti : tree.getItems() ) {
			ti.setText(TreeItemMap.get(ti).toString());
			
			for ( TreeItem ti2 : ti.getItems() ) {
				ti.setText(1, TreeItemMap.get(ti2).toString());
			}
		}
	}

}