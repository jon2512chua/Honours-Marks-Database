package gui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;

import orm.Student;

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
	
	public static StringBuffer[] userInfo = new StringBuffer[3];
	
	/**
	 * Populates the Account Settings Tab Tab
	 * @param settingsTabFolder the folder to put the tab in
	 * @param tabName the name of the tab
	 * @wbp.parser.entryPoint
	 */
	public static void populate(final CTabFolder settingsTabFolder, String tabName) {
		CTabItem tbtmAccountSettings = new CTabItem(settingsTabFolder, SWT.NONE);
		tbtmAccountSettings.setText(tabName);
		
		
		userInfo[0] = (new StringBuffer()).append(sessionControl.Session.getUser());
		
		try {
			String sql = "SELECT SecretQn, SecretAns from System WHERE Username = '" + userInfo[0] + "'";
		
			Statement stmt = sessionControl.Session.sysConn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet r = stmt.executeQuery(sql);
			
			r.next();
			
			String q = "";
			try {q = r.getString("SecretQn");}
			catch (Exception e) {q = "woo";}

			String a = "";
			try {a = r.getString("SecretAns");}
			catch (Exception e) {a = "yeah";}
			
			userInfo[1] = (new StringBuffer()).append(q);
			userInfo[2] = (new StringBuffer()).append(a);
		
		} catch (SQLException e) {
			System.err.print("ERROR: " + e);
		}
		
		

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
		
		setFields();

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
		
		//Action to perform when the save button is pressed
		btnSaveDiscard[0].addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (!newPasswordText.getText().equals(retypeNewPasswordtext.getText())) {
					PopupWindow.popupMessage(settingsTabFolder.getShell(), "New passwords do not match.\nChanges have not been saved", "WARNING");
				} else if (newPasswordText.getText().length() == 0) {
					PopupWindow.popupMessage(settingsTabFolder.getShell(), "Password cannot be null.\nChanges have not been saved", "WARNING");
				} else {
					if(sessionControl.Session.changePassword(currentPasswordText.getText(), newPasswordText.getText())) {
						PopupWindow.popupMessage(settingsTabFolder.getShell(), "Password changed!\n", "SUCCESS!");
					}
					else {
						PopupWindow.popupMessage(settingsTabFolder.getShell(), "Password not correct.\n", "WARNING!");
					}
					//TODO save other data: username, secret question/answer.
				}
			}
		});
	}
	
	/**
	 * Helper method to reset view's fields if data is saved/.
	 */
	public static void setFields() {
		usernameText.setText(userInfo[0]+"");
		secretQText.setText(userInfo[1]+"");
		secretAText.setText(userInfo[2]+"");
	}

}
