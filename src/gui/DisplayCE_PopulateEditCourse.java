package gui;

import java.util.HashMap;
import java.util.Map;

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
	private static Map<TreeItem, StringBuffer[]> TreeItemMap = new HashMap<TreeItem, StringBuffer[]>();

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

		TreeItem newCourse = new TreeItem(courseTree, SWT.NONE);
		newCourse.setText(new String[] {"+  Add New Course"});

		Composite rComposite = new Composite(editCourseComposite, SWT.NONE);
		rComposite.setLayout(new GridLayout(2, false));
		rComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblCourseCode = new Label(rComposite, SWT.NONE);
		lblCourseCode.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCourseCode.setText("Course Code:");
		
		courseCode = new Text(rComposite, SWT.BORDER);
		courseCode.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
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
		
		Tree includedUnitTree = new Tree(unitComposite, SWT.BORDER | SWT.CHECK );
		includedUnitTree.setHeaderVisible(true);
		includedUnitTree.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		
		TreeColumn trclmnUnitCode = new TreeColumn(includedUnitTree, SWT.NONE);
		trclmnUnitCode.setWidth(100);
		trclmnUnitCode.setText("Unit Code");
		
		TreeColumn trclmnUnitName = new TreeColumn(includedUnitTree, SWT.NONE);
		trclmnUnitName.setWidth(100);
		trclmnUnitName.setText("Unit Name");
		
		@SuppressWarnings("unused")
		Button[] btnSaveDiscard = CommonButtons.addSaveChangesDeleteButton(rComposite, "Course");

		tbtmEditCourse.setControl(editCourseComposite);
		/*TODO
		for (Course c : CohortData.units) {
			TreeItem unit = new TreeItem(courseTree, SWT.NONE);
			TreeItemMap.put(unit, new StringBuffer[]{c.unitCode, c.name});
		}*/
		
		refreshTree(courseTree);
		
		for (TreeColumn tc : courseTree.getColumns()) tc.pack();
		courseTree.pack();
		/*TODO
		courseTree.addListener(SWT.Selection,new Listener() {
			public void handleEvent(Event event) {
				TreeItem[] selected = courseTree.getSelection();
				if (selected.length != 0) {
					String[] selectedString = selected[0].getText().split(" ");
					if (courseTree.indexOf(courseTree.getSelection()[0]) == 0) {
						courseCode.setText("");
						courseName.setText("");
						points.setText("");
					} else {
						Course c = Course.getUnitByCode(selectedString[0]);
						populateSelectedData(c);
						//TODO populate unit data when clicked
					}
				}
			}
		});
		*/
	
	}
	
	/**
	 * Displays data relevant to which student was clicked on.
	 * @param student the student that was clicked on
	 */
	/*TODO
	private static void populateSelectedData(Course unit) {
		try {														//Found values
			courseCode.setText(unit.getUnitCode()+"");
			courseName.setText(unit.getName()+"");
			points.setText(unit.getPoints()+"");

		} catch (java.lang.NullPointerException e) {				//Default values
			courseCode.setText(unit.getUnitCode()+"");
			courseName.setText(unit.getName()+"");
			points.setText(unit.getPoints()+"");

		}
	}*/
	
	/**
	 * Refreshes all data displayed in the tree
	 * @param tree the tree which is to be refreshed
	 */
	public static void refreshTree(Tree tree) {
		for ( TreeItem ti : tree.getItems() ) {
			try {
				ti.setText(new String[] {TreeItemMap.get(ti)[0].toString() + " " + TreeItemMap.get(ti)[1].toString()});
			} catch (java.lang.NullPointerException e) {}
		}
	}

}