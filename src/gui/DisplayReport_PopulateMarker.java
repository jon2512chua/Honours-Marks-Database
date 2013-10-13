package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FillLayout;

/**
 * Marker Report
 * @author Tim Lander
 */
public class DisplayReport_PopulateMarker {
	
	/**
	 * Populates the Marker Report Tab
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

		for (int sn=0; sn<5; sn++) {
			TreeItem marker = new TreeItem(markerTree, SWT.NONE);

			marker.setText(new String[] {Data.StaffNumber[sn]});
			//marker.setExpanded(true);
			TreeItem studentNameTitle = new TreeItem(marker, SWT.NONE);		//TODO: in theory these could be combined into one row
			studentNameTitle.setText(new String[] {"Title", Data.StaffNameTitle[sn]});
			TreeItem studentNameLast = new TreeItem(marker, SWT.NONE);
			studentNameLast.setText(new String[] {"Last Name", Data.StaffNameLast[sn]});
			TreeItem studentNameFirst = new TreeItem(marker, SWT.NONE);
			studentNameFirst.setText(new String[] {"First Name", Data.StaffNameFirst[sn]});
			//TreeItem studentDissTitle = new TreeItem(marker, SWT.NONE);
			//studentDissTitle.setText(new String[] {"Dissertation Title", StudentDissTitle[sn]});
			//TreeItem studentSuper = new TreeItem(marker, SWT.NONE);
			//studentSuper.setText(new String[] { "Supervisor", StaffNameTitle[sn] + " " + StaffNameFirst[sn].charAt(0) + ". " + StaffNameLast[sn]});
			//TreeItem studentFinalMark = new TreeItem(student, SWT.NONE);
			//studentFinalMark.setText(new String[] {"Dissertation Title", SDissTitle[sn]});

			/*for (int units=sn/2; units<=sn/2+3; units++) {
				TreeItem unit = new TreeItem(marker, SWT.NONE);
				unit.setText(new String[] {Data.Unit[units]});
				TreeItem unitName = new TreeItem(unit, SWT.NONE);
				unitName.setText(new String[] {"Unit Name", Data.UnitName[units]});
				TreeItem unitMark = new TreeItem(unit, SWT.NONE);
				unitMark.setText(new String[] {"Unit Mark", "100%"});
			}*/

		}
		
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
		treeTop[1].addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				//TODO: export here
			}
		});
	}
}
