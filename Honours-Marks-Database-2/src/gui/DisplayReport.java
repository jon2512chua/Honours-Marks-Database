package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
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
		final Tree studentTree = createTab(reportTabFolder, "Student Report", "Selection", "Data");
		PopulateStudentReport.populate(studentTree);
		
		//Create Marker Data
		final Tree markerTree = createTab(reportTabFolder, "Marker Report", "Selection", "Data");
		PopulateMarkerReport.populate(markerTree);
		
		//Listener to automatically resize Student Report column widths.
		Listener autoExpandStudentColumn = new Listener() {
			public void handleEvent(Event event) {
				resizeColumns(studentTree);
			}
		};
		studentTree.addListener(SWT.Collapse, autoExpandStudentColumn);
		studentTree.addListener(SWT.Expand, autoExpandStudentColumn);	//TODO: is the really needed? Just doing on expand would possibly do fine
		
		//Listener to automatically resize Student Report column widths.
		Listener autoExpandMarkerColumn = new Listener() {
			public void handleEvent(Event event) {
				resizeColumns(markerTree);
			}
		};
		markerTree.addListener(SWT.Collapse, autoExpandMarkerColumn);
		markerTree.addListener(SWT.Expand, autoExpandMarkerColumn);		//TODO: is the really needed? Just doing on expand would possibly do fine
		
		return reportTabFolder;
	}
	
	/**
	 * Resizes the columns to fit all the currently displayed data
	 * @param tree the tree which is to have its column widths adjusted
	 */
	private static void resizeColumns(final Tree tree) {
		tree.getDisplay().asyncExec(new Runnable() {
			public void run() {
				for ( TreeColumn tc : tree.getColumns() )
					tc.pack();
			}
		});
		
	}
	
	private static Tree createTab(CTabFolder tabFolder, String tabName, String column1Name, String column2Name) {
		CTabItem tbtmReport = new CTabItem(tabFolder, SWT.NONE);
		tbtmReport.setText(tabName);
		final Tree tree = new Tree(tabFolder, SWT.BORDER | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL);
		tbtmReport.setControl(tree);
		//tree.setHeaderVisible(false);	//Stops column resizing though...
		TreeColumn lColumn = new TreeColumn(tree, SWT.LEFT);
		lColumn.setText(column1Name);
		lColumn.setWidth(MainUI.LColumnWidth);
		TreeColumn rColumn = new TreeColumn(tree, SWT.LEFT);
		rColumn.setText(column2Name);
		rColumn.setWidth(MainUI.RColumnWidth);
		
		return tree;
	}

}
