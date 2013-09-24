package gui;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;

/**
 * Popup Windows
 * @author Tim Lander
 */
public class PopupWindow {
	private static Text userNameText;
	private static Text passwordText;
	/**
	 * Popups a message. No line wrapping currently implemented.
	 * @param parentShell the display currently in use
	 * @param text the text the popup displays 
	 * @param title the title to display
	 */
	public static void popupMessage(Shell parentShell, String text, String title) {
		final Shell shell = new Shell(parentShell, SWT.CLOSE | SWT.TITLE);

		// Set the Window Title
		shell.setText(title);
		RowLayout rl_shell = new RowLayout(SWT.VERTICAL);
		rl_shell.marginTop = 8;
		rl_shell.marginRight = 10;
		rl_shell.marginLeft = 10;
		rl_shell.marginBottom = 10;
		rl_shell.spacing = 25;
		rl_shell.center = true;
		shell.setLayout(rl_shell);

		// Create a Label in the Shell
		Label label = new Label(shell, SWT.WRAP);
		label.setText(text);

		Button btnOk = new Button(shell, SWT.CENTER);
		btnOk.setLayoutData(new RowData(75, SWT.DEFAULT));
		btnOk.setText("OK");

		shell.pack();
		shell.open();
		shell.setLocation((shell.getDisplay().getBounds().width-(shell.getSize().x))/2, 200);	//Centres popup

		//Button listener to deal with the button being pressed
		Listener btnBackupListener = new Listener() {
			public void handleEvent(Event event) {
				shell.dispose();
			}
		};
		btnOk.addListener(SWT.Selection, btnBackupListener);



		//TODO: check if needed. I'm  pretty sure it's not
		/*shell.addListener(SWT.Close, new Listener() {
			@Override
			public void handleEvent(Event event) {
				shell.dispose();
			}
		});*/
	}


	/**
	 * Popups a log on screen. Controls are disabled until a account is accepted.
	 * @param parentShell
	 * @wbp.parser.entryPoint
	 */
	public static void popupLogon(Shell parentShell) {		//TODO: perhaps add a forgotten password button?
		final String imageFileName = "splash.png";
		final Shell shell = new Shell(parentShell, SWT.TITLE);
		//Disables controls while the logon screen is displayed
		for ( Control ctrl : parentShell.getChildren() ) ctrl.setEnabled(false);

		// Set the Window Title
		shell.setText("Log On to the HMD System");
		GridLayout gl_shell = new GridLayout(3, false);
		gl_shell.marginWidth = 0;
		gl_shell.marginHeight = 0;
		gl_shell.marginBottom = 10;
		shell.setLayout(gl_shell);

		Composite imageComposite = new Composite(shell, SWT.NONE);
		RowLayout rl_imageComposite = new RowLayout(SWT.HORIZONTAL);
		rl_imageComposite.marginLeft = 0;
		rl_imageComposite.marginTop = 0;
		rl_imageComposite.marginRight = 0;
		rl_imageComposite.marginBottom = 0;
		rl_imageComposite.center = true;
		imageComposite.setLayout(rl_imageComposite);
		GridData gd_imageComposite = new GridData(SWT.CENTER, SWT.CENTER, true, true, 3, 1);
		gd_imageComposite.widthHint = 550;
		gd_imageComposite.heightHint = 200;
		imageComposite.setLayoutData(gd_imageComposite);
		Canvas canvas = new Canvas(imageComposite, SWT.NONE);
		canvas.setLayoutData(new RowData(550, 200));
		try {
			Image splashImage = new Image(parentShell.getDisplay(), imageFileName);
			canvas.setBackgroundImage(splashImage);
		} catch (SWTException e) {
			System.err.println("Warning: The file " + (new File(imageFileName)).toURI() + " was unable to be located.");
		}

		Label seperator = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData gd_seperator = new GridData(SWT.CENTER, SWT.TOP, true, false, 3, 1);
		gd_seperator.widthHint = 550;
		seperator.setLayoutData(gd_seperator);

		Label userNameLabel = new Label(shell, SWT.NONE);
		GridData gd_userNameLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_userNameLabel.verticalIndent = 10;
		gd_userNameLabel.horizontalIndent = 10;
		userNameLabel.setLayoutData(gd_userNameLabel);
		userNameLabel.setText("User Name:");
		userNameText = new Text(shell, SWT.BORDER);
		GridData gd_userNameText = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_userNameText.horizontalIndent = 20;
		gd_userNameText.verticalIndent = 10;
		gd_userNameText.widthHint = 350;
		userNameText.setLayoutData(gd_userNameText);
		new Label(shell, SWT.NONE);

		Label passwordLabel = new Label(shell, SWT.NONE);
		GridData gd_passwordLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_passwordLabel.horizontalIndent = 10;
		passwordLabel.setLayoutData(gd_passwordLabel);
		passwordLabel.setText("Password:");
		passwordText = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		GridData gd_passwordText = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_passwordText.horizontalIndent = 20;
		gd_passwordText.widthHint = 350;
		passwordText.setLayoutData(gd_passwordText);
		new Label(shell, SWT.NONE);
		
		Label lblCohortToLoad = new Label(shell, SWT.NONE);
		GridData gd_lblCohortToLoad = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblCohortToLoad.horizontalIndent = 10;
		lblCohortToLoad.setLayoutData(gd_lblCohortToLoad);
		lblCohortToLoad.setText("Cohort to Load:");
		final Combo combo = new Combo(shell, SWT.READ_ONLY);
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_combo.widthHint = 337;
		gd_combo.horizontalIndent = 20;
		combo.setLayoutData(gd_combo);
		combo.add("2013 - Semester 2");
		combo.add("2013 - Semester 1");
		combo.add("2012 - Semester 2");
		combo.add("2012 - Semester 1");
		combo.select(0);

		Composite buttonsComposite = new Composite(shell, SWT.NONE);
		GridData gd_buttonsComposite = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 3, 1);
		gd_buttonsComposite.verticalIndent = 20;
		buttonsComposite.setLayoutData(gd_buttonsComposite);
		RowLayout rl_buttonsComposite = new RowLayout(SWT.HORIZONTAL);
		rl_buttonsComposite.center = true;
		buttonsComposite.setLayout(rl_buttonsComposite);

		Button btnOK = new Button(buttonsComposite, SWT.CENTER);
		btnOK.setLayoutData(new RowData(75, SWT.DEFAULT));
		btnOK.setText("OK");

		Button btnClear = new Button(buttonsComposite, SWT.CENTER);
		btnClear.setLayoutData(new RowData(75, SWT.DEFAULT));
		btnClear.setText("Clear");

		Button btnQuit = new Button(buttonsComposite, SWT.CENTER);
		btnQuit.setLayoutData(new RowData(75, SWT.DEFAULT));
		btnQuit.setText("Quit");

		//Button listener to deal with the OK button being pressed
		Listener btnOKListener = new Listener() {
			@SuppressWarnings("unused")			///TODO: delete after validation is added in
			public void handleEvent(Event event) {
				//TODO: username/password validation
				if (true) {
					//Enables controls
					for ( Control ctrl : shell.getParent().getChildren() ) ctrl.setEnabled(true);
					shell.dispose();
				} else popupMessage(shell, "Invalid username or password."+"\n\r"+"Please try again.", "Invalid Account");
			}
		};
		btnOK.addListener(SWT.Selection, btnOKListener);

		//Button listener to deal with the button clear being pressed
		Listener btnClearListener = new Listener() {
			public void handleEvent(Event event) {
				userNameText.setText("");
				passwordText.setText("");
			}
		};
		btnClear.addListener(SWT.Selection, btnClearListener);

		//Button listener to deal with the Quit button being pressed
		Listener btnQuitListener = new Listener() {
			public void handleEvent(Event event) {
				shell.getParent().dispose();
			}
		};
		btnQuit.addListener(SWT.Selection, btnQuitListener);

		shell.pack();
		shell.open();
		shell.setLocation((shell.getDisplay().getBounds().width-(shell.getSize().x))/2, 150);

	}
}
