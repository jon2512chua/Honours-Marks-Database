package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;

import sessionControl.Errors;
// TODO Tim - the dropdown menu here doesn't refresh itself : update: i think it might once you swap the order of popup and main window...?
public class DisplaySettings_PopulateBackupRestore {

	/**
	 * Populates the Restore Backup Tab
	 * @param settingsTabFolder the folder to put the tab in
	 * @param tabName the name of the tab
	 * @wbp.parser.entryPoint
	 */
	public static void populate(final CTabFolder settingsTabFolder, String tabName) {
		final CTabItem tbtmbackupRestore = new CTabItem(settingsTabFolder, SWT.NONE);
		tbtmbackupRestore.setText(tabName);
		final Composite composite = new Composite(settingsTabFolder, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		tbtmbackupRestore.setControl(composite);

		final Combo combo = new Combo(composite, SWT.READ_ONLY);

		settingsTabFolder.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (event.item == tbtmbackupRestore) {
					combo.removeAll();
					String[] backupList = backupSubsystem.BackupUtils.getBackupsList();
					if (backupList.length == 0) combo.add("No Backups Found");
					else if (backupList[0].equals(Errors.noBackupsFolder)) combo.add("ERROR: " + Errors.noBackupsFolder);
					else for (String c : backupList) combo.add(c);
					combo.select(0);
					composite.pack();
				}
			}
		});
		settingsTabFolder.notifyListeners(SWT.FocusIn, new Event());

		Button btnRestoreBackup = new Button(composite, SWT.NONE);
		btnRestoreBackup.setText("Restore Backup");

		//Save button Listener
		Listener btnRestoreBackupListener = new Listener() {
			public void handleEvent(Event event) {
				String comboSelection = combo.getItem(combo.getSelectionIndex());
				if (!comboSelection.startsWith("No")) {	//TODO: test following
					if (PopupWindow.popupYessNo(settingsTabFolder.getShell(), "Are you sure you want to restore the database to the backup " + comboSelection + "?", "Restore backup")) {
						String selection = comboSelection;
						selection = selection.substring(0, 4) + selection.charAt(5) + " 20" + selection.substring(14, 16) + selection.substring(11, 13) + selection.substring(8, 10) + selection.substring(17, 20) + selection.substring(21, 23) + selection.substring(24, 26) + ".zip";
						if (backupSubsystem.BackupOperations.restore(selection)) {
							PopupWindow.popupMessage(settingsTabFolder.getShell(), "The database has been restored to: " + comboSelection + ".", "Restoration Successful");
						} else {
							PopupWindow.popupMessage(settingsTabFolder.getShell(), "The database " + comboSelection + " could not be restored.", "Restoration Unsuccessful");
						}
					}
				} else {
					System.err.println("Warning: Cannot restore backup if no backups are found.");
					PopupWindow.popupMessage(settingsTabFolder.getShell(), "No backups found to restore. \nBackup operation not completed.", "Cannot Restore Backup");
				}
			}
		};
		btnRestoreBackup.addListener(SWT.Selection, btnRestoreBackupListener);


	}

}
