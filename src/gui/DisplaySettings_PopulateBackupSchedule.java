package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

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
import org.eclipse.swt.widgets.Label;

/**
 * Schedule Backup Section
 * @author Tim Lander
 */
public class DisplaySettings_PopulateBackupSchedule {
	private static Text text;
	private static String setText;
	private static int radioSelection;
	private static int comboSelection;

	private static final String settingsFileName = "settings";

	/**
	 * Populates the Schedule Backup Tab
	 * @param settingsTabFolder the folder to put the tab in
	 * @param tabName the name of the tab
	 * @wbp.parser.entryPoint
	 */
	public static void populate(final CTabFolder settingsTabFolder, String tabName) {
		restoreSettings();

		CTabItem tbtmbackupSchedule = new CTabItem(settingsTabFolder, SWT.NONE);
		tbtmbackupSchedule.setText(tabName);

		//Create Backup Data
		final Composite radioButtonComposite = new Composite(settingsTabFolder, SWT.NONE);
		tbtmbackupSchedule.setControl(radioButtonComposite);
		radioButtonComposite.setLayout(new GridLayout(2, false));
		new Label(radioButtonComposite, SWT.NONE);
		
		Composite backupNowComposite = new Composite(radioButtonComposite, SWT.NONE);
		GridData gd_backupNowComposite = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 8);
		gd_backupNowComposite.horizontalIndent = 10;
		backupNowComposite.setLayoutData(gd_backupNowComposite);
		
		Button btnBackupNow = new Button(backupNowComposite, SWT.NONE);
		btnBackupNow.setBounds(0, 0, 75, 25);
		btnBackupNow.setText("Backup Now");

		final Button btnBackupNever = new Button(radioButtonComposite, SWT.RADIO);
		btnBackupNever.setText("Never");

		final Button btnBackupStartup = new Button(radioButtonComposite, SWT.RADIO);
		btnBackupStartup.setText("On Startup");

		final Button btnBackupDaily = new Button(radioButtonComposite, SWT.RADIO);
		btnBackupDaily.setText("Daily Startup");

		final Button btnBackupWeekly = new Button(radioButtonComposite, SWT.RADIO);
		btnBackupWeekly.setText("Weekly Startup");

		final Button btnBackupMonthly = new Button(radioButtonComposite, SWT.RADIO);
		btnBackupMonthly.setText("Monthly Startup");

		final Button btnBackupCustom = new Button(radioButtonComposite, SWT.RADIO);
		btnBackupCustom.setText("Custom");

		final Composite custombackupComposite = new Composite(radioButtonComposite, SWT.NONE);
		RowLayout rl_custombackupComposite = new RowLayout(SWT.HORIZONTAL);
		rl_custombackupComposite.center = true;
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

		Button[] btnSaveDiscard = CommonButtons.addSaveDiscardChangesButton(radioButtonComposite);

		//Displays previously saved settings
		try {
			((Button) radioButtonComposite.getChildren()[radioSelection+2]).setSelection(true);
			combo.select(comboSelection);
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			System.err.println("Warning: Invalid settings file. Default values have been loaded.");
			radioSelection = 5;
			comboSelection = 1;
			((Button) radioButtonComposite.getChildren()[radioSelection+2]).setSelection(true);	//TODO: ensure this is correct, even if UI changes
			combo.select(comboSelection);
		}

		//Disables some controls when the custom button is not selected
		SelectionListener btnBackupCustomListene = new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {}

			public void widgetSelected(SelectionEvent e) {
				if (btnBackupCustom.getSelection()) {
					for ( Control ctrl : custombackupComposite.getChildren() ) ctrl.setEnabled(true);
				} else {
					for ( Control ctrl : custombackupComposite.getChildren() ) ctrl.setEnabled(false);
				}
			}
		};
		btnBackupCustom.addSelectionListener(btnBackupCustomListene);
		btnBackupCustom.notifyListeners(SWT.Selection, new Event());	//Sets listener to check once on startup
		
		//Backup Now button listner
		Listener btnBackupNowListener = new Listener() {	//TODO: test 
			@SuppressWarnings("unused")	//TODO: remove
			public void handleEvent(Event event) {
				if (BackupOperations.backup()) {//TODO: uncomment out
					PopupWindow.popupMessage(settingsTabFolder.getShell(), "Backup Successful.", "Backup");
				} else {
					PopupWindow.popupMessage(settingsTabFolder.getShell(), "Backup Failed.", "Backup");
				}
			}
		};
		btnBackupNow.addListener(SWT.Selection, btnBackupNowListener);


		//Save button Listener
		Listener btnSaveChangesListener = new Listener() {
			public void handleEvent(Event event) {
				comboSelection = combo.getSelectionIndex();
				radioSelection = 0;
				//TODO: ensure this is correct, even if UI changes
				while (((Button) radioButtonComposite.getChildren()[radioSelection+2]).getSelection() == false /*&& radioSelection<100*/) radioSelection++;
				saveSettings();
			}
		};
		btnSaveDiscard[0].addListener(SWT.Selection, btnSaveChangesListener);

	}

	/**
	 * Save settings
	 */
	private static final void saveSettings() {
		try {
			Properties props = new Properties();
			props.setProperty("text", text.getText());
			props.setProperty("radioSelection", ""+radioSelection);
			props.setProperty("comboSelection", ""+comboSelection);

			OutputStream out = new FileOutputStream(new File(settingsFileName));
			props.store(out, "HMD Settings File");
			PopupWindow.popupMessage(text.getShell(), "Backup settings saved.", "Saved");
		}
		catch (Exception e ) {
			e.printStackTrace();
		}

	}

	/**
	 * Restore settings
	 */
	private static final void restoreSettings() {
		Properties props = new Properties();
		InputStream is = null;

		// Try loading from the current directory
		try {
			is = new FileInputStream( new File(settingsFileName) );
			// Try loading properties from the file (if found)
			props.load( is );
		} catch (Exception e) {is = null;}

		setText = new String(props.getProperty("text", ""));
		radioSelection = new Integer(props.getProperty("radioSelection", "4"));
		comboSelection = new Integer(props.getProperty("comboSelection", "0"));
	}
}
