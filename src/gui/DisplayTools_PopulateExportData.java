package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Button;

/**
 * Export Data Section
 * @author Tim Lander
 */
public class DisplayTools_PopulateExportData {

	/**
	 * Populates the Import Data Tab
	 * @param toolsTabFolder the folder to put the tab in
	 * @param tabName the name of the tab
	 * @wbp.parser.entryPoint
	 */
	public static void populate(final CTabFolder toolsTabFolder, String tabName) {
		CTabItem tbtmExport = new CTabItem(toolsTabFolder, SWT.NONE);
		tbtmExport.setText(tabName);
		
	//TODO: replace with staff stuff
		Button btnNewButton2 = new Button(toolsTabFolder, SWT.NONE);
		btnNewButton2.setText("TODO:delete button, replace with stuff");

		tbtmExport.setControl(btnNewButton2);
	//End replace
		
	}
}
