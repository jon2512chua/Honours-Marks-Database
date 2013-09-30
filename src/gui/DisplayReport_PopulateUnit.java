package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Unit Report
 * @author Tim Lander
 */
public class DisplayReport_PopulateUnit {
	
	/**
	 * Populates the Unit Report Tab
	 * @param reportTabFolder the folder to put the tab in
	 * @param tabName the name of the tab
	 * @wbp.parser.entryPoint
	 */
	public static void populate(final CTabFolder reportTabFolder, String tabName) {
		CTabItem tbtmReport = new CTabItem(reportTabFolder, SWT.NONE);
		tbtmReport.setText(tabName);

		Tree unitTree = DisplayReport.createReportTree(reportTabFolder, "Selection", "Data");
		tbtmReport.setControl(unitTree);
		
		for (int unitNumber=0; unitNumber<5; unitNumber++) {
			TreeItem unit = new TreeItem(unitTree, SWT.NONE);
			unit.setText(new String[] {Data.Unit[unitNumber]});
		}
		
		
		
		
		
		
		
		DisplayReport.autoResizeColumn(unitTree);
	}
}
