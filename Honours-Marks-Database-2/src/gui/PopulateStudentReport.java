package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Student Report
 * @author Tim Lander
 */
public class PopulateStudentReport {
	static void populate(Tree studentTree) {
		for (int sn=0; sn<5; sn++) {
			TreeItem student = new TreeItem(studentTree, SWT.NONE);

			student.setText(new String[] {Data.StudentNumber[sn]});
			//student.setExpanded(true);
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
			//TreeItem studentFinalMark = new TreeItem(student, SWT.NONE);
			//studentFinalMark.setText(new String[] {"Dissertation Title", SDissTitle[sn]});

			for (int units=sn/2; units<=sn/2+3; units++) {
				TreeItem unit = new TreeItem(student, SWT.NONE);
				unit.setText(new String[] {Data.Unit[units]});
				TreeItem unitName = new TreeItem(unit, SWT.NONE);
				unitName.setText(new String[] {"Unit Name", Data.UnitName[units]});
				TreeItem unitMark = new TreeItem(unit, SWT.NONE);
				unitMark.setText(new String[] {"Unit Mark", "100%"});
			}

			//student.setExpanded(true);
		}
	}
}
