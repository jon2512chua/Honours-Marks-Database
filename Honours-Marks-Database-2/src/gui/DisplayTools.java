package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;

/**
 * Displays the Tools section
 * @author Tim Lander
 */
public class DisplayTools {
	private static Text text;
	private static String setText;
	private static int radioSelection;
	private static int comboSelection;

	public static final String settingsFileName = "settings";

	/**
	 * @wbp.parser.entryPoint
	 * @param parent the parent composite the data is displayed on.
	 * @return the CTabFolder that contains the data
	 */
	public static final CTabFolder display(Composite parent) {
		restoreSettings();

		final Composite displayComposite = new Composite(parent, SWT.BORDER);
		displayComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1));
		displayComposite.setLayout(new FillLayout());

		//Set up tabs
		final CTabFolder toolsTabFolder = new CTabFolder(displayComposite, SWT.NONE);
		toolsTabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		CTabItem tbtmTools = new CTabItem(toolsTabFolder, SWT.NONE);
		tbtmTools.setText("Schedule Backup");

		CTabItem tbtmImport = new CTabItem(toolsTabFolder, SWT.NONE);
		tbtmImport.setText("Import Data");

		CTabItem tbtmExport = new CTabItem(toolsTabFolder, SWT.NONE);
		tbtmExport.setText("Export Data");

		//Create Backup Data
		final Composite radioButtonComposite = new Composite(toolsTabFolder, SWT.NONE);
		tbtmTools.setControl(radioButtonComposite);
		radioButtonComposite.setLayout(new GridLayout(1, false));

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
		
		Composite buttonsComposite = new Composite(radioButtonComposite, SWT.NONE);
		buttonsComposite.setLayout(new RowLayout(SWT.HORIZONTAL));
		buttonsComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, true, 2, 1));

		Button btnSaveChanges = new Button(buttonsComposite, SWT.NONE);
		btnSaveChanges.setText("Save Changes");

		Button btnDiscardChanges = new Button(buttonsComposite, SWT.NONE);
		btnDiscardChanges.setText("Discard Changes");

		//Displays previously saved settings
		try {
			((Button) radioButtonComposite.getChildren()[radioSelection]).setSelection(true);
			combo.select(comboSelection);
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			System.err.println("Warning: Invalid settings file. Default values have been loaded.");
			radioSelection = 5;
			comboSelection = 0;
			((Button) radioButtonComposite.getChildren()[radioSelection]).setSelection(true);
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


		//Save button Listener
		Listener btnSaveChangesListener = new Listener() {
			public void handleEvent(Event event) {
				comboSelection = combo.getSelectionIndex();
				radioSelection = 0;
				while (((Button) radioButtonComposite.getChildren()[radioSelection]).getSelection() == false && radioSelection<100) radioSelection++;
				saveSettings();
			}
		};
		btnSaveChanges.addListener(SWT.Selection, btnSaveChangesListener);

		return toolsTabFolder;
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
