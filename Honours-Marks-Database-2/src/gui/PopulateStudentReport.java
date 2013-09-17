package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Student Report
 * @author Tim Lander
 */
public class PopulateStudentReport {
	static void populate(final Tree studentTree) {
		for (int sn=0; sn<5; sn++) {
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
					studentAssessmentMark.setText(new String[] {"Student Assessment Mark", "100%"});
					TreeItem AssessmentPercentUnit = new TreeItem(assessment, SWT.NONE);
					AssessmentPercentUnit.setText(new String[] {"Percent of Unit", "22%"});
					TreeItem AssessmentPercentFinalGrade = new TreeItem(assessment, SWT.NONE);
					AssessmentPercentFinalGrade.setText(new String[] {"Percent of Final Grade", "5%"});
					//TODO: add in markers. May be a bit tricky, as the number of markers are variable.
				}

			}

			//student.setExpanded(true);		//TODO:Perhaps add an expand all button?
		}

	}
}