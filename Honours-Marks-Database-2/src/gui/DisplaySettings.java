package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Displays the settings section
 * @author Tim Lander
 */
public class DisplaySettings {
	private static Text text;
	private static Text text_1;
	private static Text text_2;
	private static Text text_3;
	private static Text text_4;
	/**
	 * @param parent the composite the data is displayed on.
	 * @return the CTabFolder that contains the data
	 * @wbp.parser.entryPoint
	 */
	public static CTabFolder display(Composite parent) {
		final Composite displayComposite = new Composite(parent, SWT.BORDER);
		displayComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1));
		displayComposite.setLayout(new FillLayout());

		//Set up tabs
		final CTabFolder settingsTabFolder = new CTabFolder(displayComposite, SWT.NONE);
		settingsTabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		CTabItem tbtmAccountSettings = new CTabItem(settingsTabFolder, SWT.NONE);
		tbtmAccountSettings.setText("Account Settings");

		final Composite accountSettingsComposite = new Composite(settingsTabFolder, SWT.NONE);
		tbtmAccountSettings.setControl(accountSettingsComposite);
		accountSettingsComposite.setLayout(new GridLayout(2, false));
		
		Label lblUsername = new Label(accountSettingsComposite, SWT.NONE);
		lblUsername.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUsername.setText("Username:");
		
		text = new Text(accountSettingsComposite, SWT.BORDER);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text.widthHint = 350;
		text.setLayoutData(gd_text);
		
		Label lblEmailAddress = new Label(accountSettingsComposite, SWT.NONE);
		lblEmailAddress.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEmailAddress.setText("Email Address:");
		
		text_3 = new Text(accountSettingsComposite, SWT.BORDER);
		GridData gd_text_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_3.widthHint = 350;
		text_3.setLayoutData(gd_text_3);
		
		Label lblNewPassword = new Label(accountSettingsComposite, SWT.NONE);
		lblNewPassword.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewPassword.setText("New Password:");
		
		text_1 = new Text(accountSettingsComposite, SWT.BORDER | SWT.PASSWORD);
		GridData gd_text_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_1.widthHint = 350;
		text_1.setLayoutData(gd_text_1);
		
		Label lblRetypeNewPassword = new Label(accountSettingsComposite, SWT.NONE);
		lblRetypeNewPassword.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRetypeNewPassword.setText("Retype New Password:");
		
		text_2 = new Text(accountSettingsComposite, SWT.BORDER | SWT.PASSWORD);
		GridData gd_text_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_2.widthHint = 350;
		text_2.setLayoutData(gd_text_2);
		
		Label label = new Label(accountSettingsComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label lblCurrentPassword = new Label(accountSettingsComposite, SWT.NONE);
		lblCurrentPassword.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCurrentPassword.setText("Current Password:");
		
		text_4 = new Text(accountSettingsComposite, SWT.BORDER | SWT.PASSWORD);
		GridData gd_text_4 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_4.widthHint = 350;
		text_4.setLayoutData(gd_text_4);
		
		Composite buttonsComposite = new Composite(accountSettingsComposite, SWT.NONE);
		buttonsComposite.setLayout(new RowLayout(SWT.HORIZONTAL));
		buttonsComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, true, 2, 1));

		Button btnSaveChanges = new Button(buttonsComposite, SWT.NONE);
		btnSaveChanges.setText("Save Changes");

		Button btnDiscardChanges = new Button(buttonsComposite, SWT.NONE);
		btnDiscardChanges.setText("Discard Changes");

		return settingsTabFolder;
	}

}
