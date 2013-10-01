package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Button;

/**
 * Edit Units Section
 * @author Tim Lander
 */
public class DisplayCE_PopulateEditUnit {

	/**
	 * Populates the Edit Unit Tab
	 * @param CETabFolder the folder to put the tab in
	 * @param tabName the name of the tab
	 * @wbp.parser.entryPoint
	 */
	public static void populate(final CTabFolder CETabFolder, String tabName) {
		CTabItem tbtmEditUnit = new CTabItem(CETabFolder, SWT.NONE);
		tbtmEditUnit.setText(tabName);

	//TODO: replace with staff stuff
		Button btnNewButton2 = new Button(CETabFolder, SWT.NONE);
		btnNewButton2.setText("TODO:delete button, replace with stuff");

		tbtmEditUnit.setControl(btnNewButton2);
	//End replace

	}

}