package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

/**
 * Displays the Reports section
 * @author Tim Lander
 */
public class DisplayReport {

	/**
	 * @wbp.parser.entryPoint
	 * @param parent the composite the data is displayed on.
	 * @return the CTabFolder that contains the data
	 */
	public static CTabFolder display(Composite parent) {
		Composite displayComposite = new Composite(parent, SWT.BORDER);
		displayComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1));
		displayComposite.setLayout(new FillLayout());

		//Set up tabs
		final CTabFolder reportTabFolder = new CTabFolder(displayComposite, SWT.NONE);
		reportTabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		//Create Student Data
		DisplayReport_PopulateStudent.populate(reportTabFolder, "Student Report");

		//Create Marker Data
		DisplayReport_PopulateMarker.populate(reportTabFolder, "Marker Report");

		//Create Unit Data
		DisplayReport_PopulateUnit.populate(reportTabFolder, "Unit Report");

		//Create Cohort Data
		//DisplayReport_PopulateCohort.populate(reportTabFolder, "Cohort Report");

		return reportTabFolder;
	}


	/**
	 * Generates a report tree in the following tabFolder
	 * @param composite the tabFolder which contains the tree
	 * @param column1Name the title of the first column
	 * @param column2Name the title of the second column
	 * @return the generated tree
	 */
	public static Tree createReportTree(Composite composite, String column1Name, String column2Name) {
		final Tree tree = new Tree(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL);
		TreeColumn lColumn = new TreeColumn(tree, SWT.LEFT);
		lColumn.setText(column1Name);
		lColumn.setWidth(MainUI.LColumnWidth);
		TreeColumn rColumn = new TreeColumn(tree, SWT.LEFT);
		rColumn.setText(column2Name);
		rColumn.setWidth(MainUI.RColumnWidth);

		return tree;
	}

	/**
	 * Resizes the columns to fit all the currently displayed data
	 * @param tree the tree which is to have its column widths adjusted
	 */
	public static void autoResizeColumn(final Tree tree) {
		//Listener to automatically resize Student Report column widths.
		Listener autoExpandStudentColumn = new Listener() {
			public void handleEvent(Event event) {
				tree.getDisplay().asyncExec(new Runnable() {
					public void run() {
						for ( TreeColumn tc : tree.getColumns() ) tc.pack();
					}
				});
			}
		};
		tree.addListener(SWT.Collapse, autoExpandStudentColumn);
		tree.addListener(SWT.Expand, autoExpandStudentColumn);
	}
}
