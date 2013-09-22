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
	private static Text usernameText;
	private static Text newPasswordText;
	private static Text retypeNewPasswordtext;
	private static Text emailText;
	private static Text currentPasswordText;
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
		
		usernameText = new Text(accountSettingsComposite, SWT.BORDER);
		GridData gd_usernameText = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_usernameText.widthHint = 350;
		usernameText.setLayoutData(gd_usernameText);
		
		Label lblEmailAddress = new Label(accountSettingsComposite, SWT.NONE);
		lblEmailAddress.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEmailAddress.setText("Email Address:");
		
		emailText = new Text(accountSettingsComposite, SWT.BORDER);
		GridData gd_emailText = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_emailText.widthHint = 350;
		emailText.setLayoutData(gd_emailText);
		
		Label lblNewPassword = new Label(accountSettingsComposite, SWT.NONE);
		lblNewPassword.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewPassword.setText("New Password:");
		
		newPasswordText = new Text(accountSettingsComposite, SWT.BORDER | SWT.PASSWORD);
		GridData gd_newPasswordText = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_newPasswordText.widthHint = 350;
		newPasswordText.setLayoutData(gd_newPasswordText);
		
		Label lblRetypeNewPassword = new Label(accountSettingsComposite, SWT.NONE);
		lblRetypeNewPassword.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRetypeNewPassword.setText("Retype New Password:");
		
		retypeNewPasswordtext = new Text(accountSettingsComposite, SWT.BORDER | SWT.PASSWORD);
		GridData gd_retypeNewPasswordtext = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_retypeNewPasswordtext.widthHint = 350;
		retypeNewPasswordtext.setLayoutData(gd_retypeNewPasswordtext);
		
		Label label = new Label(accountSettingsComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label lblCurrentPassword = new Label(accountSettingsComposite, SWT.NONE);
		lblCurrentPassword.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCurrentPassword.setText("Current Password:");
		
		currentPasswordText = new Text(accountSettingsComposite, SWT.BORDER | SWT.PASSWORD);
		GridData gd_currentPasswordText = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_currentPasswordText.widthHint = 350;
		currentPasswordText.setLayoutData(gd_currentPasswordText);
		
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
