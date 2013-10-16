package gui;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FillLayout;

import orm.Staff;
import orm.Mark;
import logic.CohortData;

/**
 * Marker Report
 * @author Tim Lander
 */
public class DisplayReport_PopulateMarker {
	private static Map<TreeItem, StringBuffer> TreeItemMap = new HashMap<TreeItem, StringBuffer>();
	public static Boolean hardRefreshNeeded = true;

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

		final Tree markerTree = DisplayReport.createReportTree(treeComposite, "Selection", "Data");
		tbtmReport.setControl(mainComposite);

		//Listener to automatically resize Student Report column widths.
		DisplayReport.autoResizeColumn(markerTree);

		//Listener for + button
		treeTop[0].addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				for ( TreeItem ti : markerTree.getItems() ) ti.setExpanded(true);
			}
		});

		//Listener for - button
		treeTop[1].addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				for ( TreeItem ti : markerTree.getItems() ) ti.setExpanded(false);
			}
		});

		//Listener for Export button
		treeTop[2].addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				FileDialog fd = new FileDialog(markerTree.getShell(), SWT.SAVE);
				fd.setText("Save Marker Summary...");
				fd.setFilterPath("exports/");
				fd.setFilterExtensions(new String[]{ "*.xls", "*.*" }); 
				String selected = fd.open();
				if(selected != null) { 							
					try {
						export.ToExcel.markerSummaries(selected);
						PopupWindow.popupMessage(markerTree.getShell(), "Export Successful!", "SUCCESS!");
					} catch (Exception e) {
						PopupWindow.popupMessage(markerTree.getShell(), e.toString(), "ERROR");
					}
				}
			}
		});

		//Listener to auto-update displayed data (currently untested)
		refreshAll(markerTree);
		reportTabFolder.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				refreshAll(markerTree);
			}
		});

	}

	/**
	 * Refreshes all data displayed in the tree
	 * @param tree the tree which is to be refreshed
	 */
	//TODO: currently does not add new treeItems
	public static void refreshAll(Tree tree) {
		if (hardRefreshNeeded) {
			for (TreeItem ti : tree.getItems()) ti.dispose();
			hardRefresh(tree);
			hardRefreshNeeded = !hardRefreshNeeded;
		}
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
		
	private static void hardRefresh(Tree tree) {
		for (Staff s : CohortData.staff) {
			TreeItem marker = new TreeItem(tree, SWT.NONE);
			TreeItemMap.put(marker, s.staffID);


			TreeItem staffNameLast = new TreeItem(marker, SWT.NONE);
			TreeItemMap.put(staffNameLast, s.lastName);
			staffNameLast.setText(0, "Last Name");

			TreeItem staffNameFirst = new TreeItem(marker, SWT.NONE);
			TreeItemMap.put(staffNameFirst, s.firstName);
			staffNameFirst.setText(0, "First Name");
			
			PriorityQueue<Mark> marks = s.getMarks();
			for (Mark m : marks) {
				TreeItem mark = new TreeItem(marker, SWT.NONE);
				TreeItemMap.put(mark, m.value);
				mark.setText(0, m.studentID.toString());
			}


		}
	}
		
}
