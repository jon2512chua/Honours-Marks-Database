package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Button;

/**
 * Import Data Section
 * @author Tim Lander
 */
public class DisplayTools_PopulateImportData {

	/**
	 * Populates the Schedule backup Tab
	 * @param toolsTabFolder the folder to put the tab in
	 * @param tabName the name of the tab
	 * @wbp.parser.entryPoint
	 */
	public static void populate(final CTabFolder toolsTabFolder, String tabName) {
		CTabItem tbtmImport = new CTabItem(toolsTabFolder, SWT.NONE);
		tbtmImport.setText(tabName);
		
	//TODO: replace with staff stuff
		Button btnNewButton2 = new Button(toolsTabFolder, SWT.NONE);
		btnNewButton2.setText("TODO:delete button, replace with stuff");

		tbtmImport.setControl(btnNewButton2);
	//End replace
		
	}
}