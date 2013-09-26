package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Button;

/**
 * Edit Staff Section
 * @author Tim Lander
 */
public class DisplayCE_PopulateEditSfaff {
	
	/**
	 * Populates the Edit Staff Tab
	 * @param CETabFolder the folder to put the tab in
	 * @param tabName the name of the tab
	 * @wbp.parser.entryPoint
	 */
	public static void populate(final CTabFolder CETabFolder, String tabName) {
		CTabItem tbtmEditStaff = new CTabItem(CETabFolder, SWT.NONE);
		tbtmEditStaff.setText(tabName);

	//TODO: replace with staff stuff
		Button btnNewButton2 = new Button(CETabFolder, SWT.NONE);
		btnNewButton2.setText("TODO:delete button, replace with stuff");

		tbtmEditStaff.setControl(btnNewButton2);
	//End replace

	}

}
