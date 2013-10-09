package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;

import backupSubsystem.BackupOperations;

/**
 * Schedule Backup Section
 * @author Tim Lander
 */
public class DisplaySettings_PopulateBackup {
	private static Text text;
	private static String setText;
	private static int radioSelection;
	private static int comboSelection;

	/**
	 * Populates the Schedule Backup Tab
	 * @param settingsTabFolder the folder to put the tab in
	 * @param tabName the name of the tab
	 * @wbp.parser.entryPoint
	 */
	public static void populate(final CTabFolder settingsTabFolder, String tabName) {
		restoreBackupSettings();

		CTabItem tbtmbackupSchedule = new CTabItem(settingsTabFolder, SWT.NONE);
		tbtmbackupSchedule.setText(tabName);

		//Create Backup Data
		final Composite backupComposite = new Composite(settingsTabFolder, SWT.NONE);
		tbtmbackupSchedule.setControl(backupComposite);
		backupComposite.setLayout(new GridLayout(2, false));

		final Composite radioButtoncomposite = new Composite(backupComposite, SWT.NONE);
		radioButtoncomposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		radioButtoncomposite.setLayout(new GridLayout(1, false));

		final Button btnBackupNever = new Button(radioButtoncomposite, SWT.RADIO);
		btnBackupNever.setText("Never");

		final Button btnBackupStartup = new Button(radioButtoncomposite, SWT.RADIO);
		btnBackupStartup.setText("On Startup");

		final Button btnBackupDaily = new Button(radioButtoncomposite, SWT.RADIO);
		btnBackupDaily.setText("Daily Startup");

		final Button btnBackupWeekly = new Button(radioButtoncomposite, SWT.RADIO);
		btnBackupWeekly.setText("Weekly Startup");

		final Button btnBackupMonthly = new Button(radioButtoncomposite, SWT.RADIO);
		btnBackupMonthly.setText("Monthly Startup");

		final Button btnBackupCustom = new Button(radioButtoncomposite, SWT.RADIO);
		btnBackupCustom.setText("Custom");

		final Composite custombackupComposite = new Composite(radioButtoncomposite, SWT.NONE);
		RowLayout rl_custombackupComposite = new RowLayout(SWT.HORIZONTAL);
		rl_custombackupComposite.marginTop = 0;
		rl_custombackupComposite.marginLeft = 10;
		custombackupComposite.setLayout(rl_custombackupComposite);

		CLabel lblNewLabel = new CLabel(custombackupComposite, SWT.NONE);
		lblNewLabel.setText("Backup Every:");

		text = new Text(custombackupComposite, SWT.BORDER | SWT.RIGHT);
		Validation.validateInt(text);
		text.setTextLimit(3);
		text.setLayoutData(new RowData(25, SWT.DEFAULT));
		text.setText(setText);

		final Combo combo = new Combo(custombackupComposite, SWT.READ_ONLY);
		combo.add("hours");
		combo.add("days");
		combo.add("weeks");
		combo.select(comboSelection);

		Composite backupNowComposite = new Composite(backupComposite, SWT.NONE);
		GridData gd_backupNowComposite = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_backupNowComposite.horizontalIndent = 10;
		backupNowComposite.setLayoutData(gd_backupNowComposite);

		final Button btnBackupNow = new Button(backupNowComposite, SWT.NONE);
		btnBackupNow.setBounds(0, 0, 75, 30);
		btnBackupNow.setText("Backup Now");

		Button[] btnSaveDiscard = CommonButtons.addSaveDiscardChangesButton(backupComposite);

		//Displays previously saved settings
		try {
			((Button) radioButtoncomposite.getChildren()[radioSelection]).setSelection(true);
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			System.err.println("Warning: Invalid settings file. Default values have been loaded.");
			radioSelection = 5;
			comboSelection = 1;
			((Button) radioButtoncomposite.getChildren()[radioSelection]).setSelection(true);	//TODO: ensure this is correct, even if UI changes
			combo.select(comboSelection);
		}

		//Disables some controls when the custom button is not selected
		SelectionListener btnBackupCustomListener = new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {}

			public void widgetSelected(SelectionEvent e) {
				if (btnBackupCustom.getSelection()) {
					for ( Control ctrl : custombackupComposite.getChildren() ) ctrl.setEnabled(true);
				} else {
					for ( Control ctrl : custombackupComposite.getChildren() ) ctrl.setEnabled(false);
				}
			}
		};
		btnBackupCustom.addSelectionListener(btnBackupCustomListener);
		btnBackupCustom.notifyListeners(SWT.Selection, new Event());	//Sets listener to check once on startup

		//Backup Now button listener
		Listener btnBackupNowListener = new Listener() {
			public void handleEvent(Event event) {
				btnBackupNow.setEnabled(false);
				if (BackupOperations.backup()) {
					PopupWindow.popupMessage(settingsTabFolder.getShell(), "Backup Successful.", "Backup");
				} else {
					PopupWindow.popupMessage(settingsTabFolder.getShell(), "Backup Failed.", "Backup");
				}
				btnBackupNow.setEnabled(true);
			}
		};
		btnBackupNow.addListener(SWT.Selection, btnBackupNowListener);


		//Save button Listener
		Listener btnSaveChangesListener = new Listener() {
			public void handleEvent(Event event) {
				comboSelection = combo.getSelectionIndex();
				radioSelection = 0;
				//TODO: ensure this is correct, even if UI changes
				while (((Button) radioButtoncomposite.getChildren()[radioSelection]).getSelection() == false /*&& radioSelection<100*/) radioSelection++;
				saveBackupSettings();
			}
		};
		btnSaveDiscard[0].addListener(SWT.Selection, btnSaveChangesListener);

	}

	/**
	 * Save settings
	 */
	private static void saveBackupSettings() {
		String keys[] = {"text", "radioSelection", "comboSelection"};
		String values[] = new String[] {text.getText(), radioSelection+"", comboSelection+""};
		if (Settings.saveSettings(keys, values)) {
			PopupWindow.popupMessage(text.getShell(), "Backup settings have been saved.", "Saved");
		} else {
			PopupWindow.popupMessage(text.getShell(), "Backup settings were unable to be saved.", "Error");
		}
	}

	/**
	 * Restore settings
	 */
	private static void restoreBackupSettings() {
		setText = Settings.loadSettings("text", "");
		radioSelection = new Integer(Settings.loadSettings("radioSelection", "4"));
		comboSelection = new Integer(Settings.loadSettings("comboSelection", "0"));
	}
}
