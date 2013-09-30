package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;

public class DisplaySettings_PopulateBackupRestore {

	/**
	 * Populates the Restore Backup Tab
	 * @param toolsTabFolder the folder to put the tab in
	 * @param tabName the name of the tab
	 * @wbp.parser.entryPoint
	 */
	public static void populate(final CTabFolder toolsTabFolder, String tabName) {
		CTabItem tbtmbackupSchedule = new CTabItem(toolsTabFolder, SWT.NONE);
		tbtmbackupSchedule.setText(tabName);
		
		
	}

}
