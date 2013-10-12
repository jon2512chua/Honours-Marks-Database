package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Edit Units Section
 * @author Johnathan Lim
 */
public class DisplayCE_PopulateEditCourse {
	@SuppressWarnings("unused")
	private static Text points;
	private static Text courseName;
	private static Text courseCode;

	/**
	 * Populates the Edit Unit Tab
	 * @param CETabFolder the folder to put the tab in
	 * @param tabName the name of the tab
	 * @wbp.parser.entryPoint
	 */
	public static void populate(final CTabFolder CETabFolder, String tabName) {
		CTabItem tbtmEditCourse = new CTabItem(CETabFolder, SWT.NONE);
		tbtmEditCourse.setText(tabName);

		//TODO: replace with staff stuff
		final Composite editCourseComposite = new Composite(CETabFolder, SWT.NONE);
		editCourseComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		GridLayout gl_editCourseComposite = new GridLayout(2, false);
		gl_editCourseComposite.horizontalSpacing = -1;
		gl_editCourseComposite.verticalSpacing = 0;
		gl_editCourseComposite.marginWidth = 0;
		gl_editCourseComposite.marginHeight = 0;
		editCourseComposite.setLayout(gl_editCourseComposite);

		final Tree courseTree = new Tree(editCourseComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		courseTree.setHeaderVisible(true);
		courseTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		
		TreeColumn trclmnCourse = new TreeColumn(courseTree, SWT.NONE);
		trclmnCourse.setWidth(100);
		trclmnCourse.setText("Course");

		Composite rComposite = new Composite(editCourseComposite, SWT.NONE);
		rComposite.setLayout(new GridLayout(2, false));
		rComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblCourseCode = new Label(rComposite, SWT.NONE);
		lblCourseCode.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCourseCode.setText("Course Code:");
		
		courseCode = new Text(rComposite, SWT.BORDER);
		courseCode.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblCourseName = new Label(rComposite, SWT.NONE);
		lblCourseName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCourseName.setText("Course Name:");
		
		courseName = new Text(rComposite, SWT.BORDER);
		courseName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite unitComposite = new Composite(rComposite, SWT.NONE);
		unitComposite.setLayout(new GridLayout(1, false));
		unitComposite.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 2, 1));
		
		Label lblIncludedUnits = new Label(unitComposite, SWT.NONE);
		lblIncludedUnits.setText("Included Units:");
		
		Tree includedUnitTree = new Tree(unitComposite, SWT.BORDER);
		includedUnitTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		
		TreeColumn trclmnIncludedUnit = new TreeColumn(includedUnitTree, SWT.NONE);
		trclmnIncludedUnit.setWidth(100);
		trclmnIncludedUnit.setText("Included Unit");
		
		@SuppressWarnings("unused")
		Button[] btnSaveDiscard = CommonButtons.addSaveDiscardChangesButton(rComposite);

		tbtmEditCourse.setControl(editCourseComposite);

		//End replace

	}

}