package gui;

import logic.CohortData;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Tree;

import orm.Student;
import orm.Unit;

/**
 * Student Oriented Entering Marks View
 * @author Tim Lander
 */
public class DisplayEnterMarks_PopulateStudentView {
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

		Combo studentCombo = new Combo(studentUnitSelectionComposite, SWT.NONE);
		try {
			for (Student s : CohortData.students) {
				studentCombo.add("[" + s.studentID + "] " + s.getFullName());
			}
		} catch (java.lang.NullPointerException e) {}

		try {
			Combo unitCombo = new Combo(studentUnitSelectionComposite, SWT.NONE);
			for (Unit u : CohortData.units) {
				unitCombo.add("[" + u.unitCode + "] " + u.name);
			}
		} catch (java.lang.NullPointerException e) {}

		Tree tree = new Tree(mainComposite, SWT.BORDER);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tbtmReport.setControl(mainComposite);



	}
}
