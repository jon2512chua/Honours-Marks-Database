package gui;

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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Point;

/**
 * Displays the Tools section
 * @author Tim Lander
 */
public class DisplayTools {
	private static Text text;
	private static int radioSelection = 4;
	private static Point comboSelection = new Point(0, 0);

	/**
	 * @wbp.parser.entryPoint
	 * @param shell the shell the data is displayed on.
	 * @return the CTabFolder that contains the data
	 */
	public static final CTabFolder display(Shell shell) {
		restoreSettings();

		final Composite displayComposite = new Composite(shell, SWT.BORDER);
		displayComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1));
		displayComposite.setLayout(new FillLayout());

		//Set up tabs
		final CTabFolder toolsTabFolder = new CTabFolder(displayComposite, SWT.NONE);
		toolsTabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		//Create Backup Data
		CTabItem tbtmTools = new CTabItem(toolsTabFolder, SWT.NONE);
		tbtmTools.setText("Schedule Backup");

		final Composite radioButtonComposite = new Composite(toolsTabFolder, SWT.NONE);
		tbtmTools.setControl(radioButtonComposite);
		radioButtonComposite.setLayout(new RowLayout(SWT.VERTICAL));

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
		text.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {		//Check if the value entered is an integer
				try {
					Integer.parseInt(e.text);
				} catch (final NumberFormatException numberFormatException) {
					e.doit = false;
				}
			}
		});
		text.setTextLimit(3);
		text.setLayoutData(new RowData(25, SWT.DEFAULT));
		//text.setText("");		//TODO: not needed?

		final Combo combo = new Combo(custombackupComposite, SWT.READ_ONLY);
		combo.add("hours");
		combo.add("days");
		combo.add("weeks");

		//Sets some controls initially disabled
		//TODO: may be able to be removed once we load settings
		for ( Control ctrl : custombackupComposite.getChildren() ) ctrl.setEnabled(!ctrl.getEnabled());

		Button btnSaveChanges = new Button(radioButtonComposite, SWT.NONE);
		btnSaveChanges.setText("Save Changes");

		//Displays previously saved settings
		((Button) radioButtonComposite.getChildren()[radioSelection]).setSelection(true);
		combo.setSelection(comboSelection);

		//Custom Button radio listener. Disables some controls if not selected.
		btnBackupCustom.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (btnBackupCustom.getSelection()) {
					for ( Control ctrl : custombackupComposite.getChildren() ) ctrl.setEnabled(true);
				} else {
					for ( Control ctrl : custombackupComposite.getChildren() ) ctrl.setEnabled(false);
				}
			}
		});

		//Save button Listener
		Listener btnSaveChangesListener = new Listener() {
			public void handleEvent(Event event) {
				saveSettings();
			}
		};
		btnSaveChanges.addListener(SWT.Selection, btnSaveChangesListener);

		return toolsTabFolder;
	}

	//Save settings
	private static final void saveSettings() {
		System.out.println("Backup settings saved");	//TODO: remove
		PopupWindow.display(text.getDisplay(), "Backup settings saved.", "Saved");
		//TODO: save somewhere
	}

	//Restore settings
	private static final void restoreSettings() {
		System.out.println("Backup settings loaded");	//TODO: remove
		//TODO: load from somewhere		
	}
}
