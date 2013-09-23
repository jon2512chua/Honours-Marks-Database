package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Tree;

/**
 * Cohort Report
 * @author Tim Lander
 */
public class DisplayReport_PopulateCohort {
	
	/**
	 * Populates the Cohort Report Tab
	 * @param reportTabFolder the folder to put the tab in
	 * @param tabName the name of the tab
	 * @wbp.parser.entryPoint
	 */
	public static void populate(final CTabFolder reportTabFolder, String tabName) {
		CTabItem tbtmReport = new CTabItem(reportTabFolder, SWT.NONE);
		tbtmReport.setText(tabName);

		Tree cohortTree = DisplayReport.createReportTree(reportTabFolder, "Selection", "Data");
		tbtmReport.setControl(cohortTree);
		
		
		
		
		
		
		
		DisplayReport.autoResizeColumn(cohortTree);
	}
}
