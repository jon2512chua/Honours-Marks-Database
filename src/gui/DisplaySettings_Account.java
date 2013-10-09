package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Account Settings Section
 * @author Tim Lander
 */
public class DisplaySettings_Account {
	private static Text usernameText;
	private static Text newPasswordText;
	private static Text retypeNewPasswordtext;
	private static Text secretQText;
	private static Text secretAText;
	private static Text currentPasswordText;

	/**
	 * Populates the Account Settings Tab Tab
	 * @param settingsTabFolder the folder to put the tab in
	 * @param tabName the name of the tab
	 * @wbp.parser.entryPoint
	 */
	public static void populate(final CTabFolder settingsTabFolder, String tabName) {
		CTabItem tbtmAccountSettings = new CTabItem(settingsTabFolder, SWT.NONE);
		tbtmAccountSettings.setText(tabName);

		final Composite accountSettingsComposite = new Composite(settingsTabFolder, SWT.NONE);
		accountSettingsComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		accountSettingsComposite.setLayout(new GridLayout(2, false));
		tbtmAccountSettings.setControl(accountSettingsComposite);

		Label lblUsername = new Label(accountSettingsComposite, SWT.NONE);
		lblUsername.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUsername.setText("Username:");

		usernameText = new Text(accountSettingsComposite, SWT.BORDER);
		GridData gd_usernameText = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_usernameText.widthHint = 350;
		usernameText.setLayoutData(gd_usernameText);

		//Padding - TODO: make this better
		new Label(accountSettingsComposite, SWT.NONE);
		new Label(accountSettingsComposite, SWT.NONE);

		Label lblSecretQ = new Label(accountSettingsComposite, SWT.NONE);
		lblSecretQ.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSecretQ.setText("Secret Question:");

		secretQText = new Text(accountSettingsComposite, SWT.BORDER);
		GridData gd_secretQText = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_secretQText.widthHint = 350;
		secretQText.setLayoutData(gd_secretQText);

		Label lblSecretA = new Label(accountSettingsComposite, SWT.NONE);
		lblSecretA.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSecretA.setText("Secret Answer:");

		secretAText = new Text(accountSettingsComposite, SWT.BORDER);
		GridData gd_secretAText = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_secretAText.widthHint = 350;
		secretAText.setLayoutData(gd_secretAText);

		//Padding - TODO: make this better
		new Label(accountSettingsComposite, SWT.NONE);
		new Label(accountSettingsComposite, SWT.NONE);

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
		
		Composite spacerComposite = new Composite(accountSettingsComposite, SWT.NONE);
		spacerComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, true, 2, 1));

		@SuppressWarnings("unused")	//TODO: remove later
		Button[] btnSaveDiscard = CommonButtons.addSaveDiscardChangesButton(accountSettingsComposite);

	}

}
