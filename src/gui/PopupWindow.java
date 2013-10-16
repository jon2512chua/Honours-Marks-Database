package gui;

import java.io.File;

import logic.CohortData;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;

import sessionControl.Session;
import sessionControl.Errors;
import sessionControl.DerbyUtils;


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
	public static void popupMessage(final Shell parentShell, String text, String title) {
		// Disable the previous window
		DisplayCE_PopulateEditAssessment.recursiveSetEnabled(parentShell, false);
		
		final Shell shell = new Shell(parentShell, SWT.CLOSE | SWT.TITLE);
		shell.setImage(parentShell.getImage());

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
		shell.setLocation((shell.getDisplay().getPrimaryMonitor().getBounds().width-(shell.getSize().x))/2, 200);	//Centres popup
		shell.open();

		//Button listener to deal with the button being pressed
		Listener btnOKListener = new Listener() {
			public void handleEvent(Event event) {
				DisplayCE_PopulateEditAssessment.recursiveSetEnabled(parentShell, true);
				shell.close();
			}
		};
		btnOk.addListener(SWT.Selection, btnOKListener);

		shell.addListener(SWT.Close, new Listener() {
			public void handleEvent(Event event) {
				DisplayCE_PopulateEditAssessment.recursiveSetEnabled(parentShell, true);
				shell.dispose();
			}
		});
	}
	
	/**
	 * RETURNS a shell object for displaying error messages before MainUI is displayed
	 * Popups a message. (duplicate implementation to above) 
	 * @param parentShell the display currently in use
	 * @param text the text the popup displays 
	 * @param title the title to display
	 */
	public static Shell popupMsg(final Shell parentShell, String text, String title) {
		// Disable the previous window
		DisplayCE_PopulateEditAssessment.recursiveSetEnabled(parentShell, false);
		
		final Shell shell = new Shell(parentShell, SWT.CLOSE | SWT.TITLE);
		shell.setImage(parentShell.getImage());

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
		shell.setLocation((shell.getDisplay().getPrimaryMonitor().getBounds().width-(shell.getSize().x))/2, 200);	//Centres popup
		shell.open();

		//Button listener to deal with the button being pressed
		Listener btnQuitListener = new Listener() {
			public void handleEvent(Event event) {
				shell.getShell().dispose();
				System.exit(0);	//Gracefully exit system.
			}
		};
		btnOk.addListener(SWT.Close, btnQuitListener);
		
		return shell;
	}
	
	/**
	 * Popups a message, with yes/no buttons. No line wrapping currently implemented.
	 * @param parentShell the display currently in use
	 * @param text the text the popup displays 
	 * @param title the title to display

	 */
	private static boolean returnVal = false;
	static boolean popupYessNo(final Shell parentShell, String text, String title) {	//TODO: test

		// Disable the previous window
		DisplayCE_PopulateEditAssessment.recursiveSetEnabled(parentShell, false);
		
		final Shell shell = new Shell(parentShell, SWT.TITLE);
		shell.setImage(parentShell.getImage());

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
		
		Composite composite = new Composite(shell, SWT.NONE);
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.horizontalSpacing = 10;
		composite.setLayout(gl_composite);

		Button btnYes = new Button(composite, SWT.CENTER);
		GridData gd_btnYes = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnYes.widthHint = 75;
		btnYes.setLayoutData(gd_btnYes);
		btnYes.setText("Yes");
		
		Button btnNo = new Button(composite, SWT.NONE);
		GridData gd_btnNo = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnNo.widthHint = 75;
		btnNo.setLayoutData(gd_btnNo);
		btnNo.setText("No");

	    shell.setDefaultButton(btnYes);

		shell.pack();
		shell.setLocation((shell.getDisplay().getPrimaryMonitor().getBounds().width-(shell.getSize().x))/2, 200);	//Centres popup
		shell.open();

		//Button listener to deal with the YES button being pressed
		Listener btnYesListener = new Listener() {
			public void handleEvent(Event event) {
				returnVal = true;
				DisplayCE_PopulateEditAssessment.recursiveSetEnabled(parentShell, true);
				shell.close();
			}
		};
		btnYes.addListener(SWT.Selection, btnYesListener);
		
		//Button listener to deal with the NO button being pressed
		Listener btnNoListener = new Listener() {
			public void handleEvent(Event event) {
				returnVal = false;
				DisplayCE_PopulateEditAssessment.recursiveSetEnabled(parentShell, true);
				shell.close();
			}
		};
		btnNo.addListener(SWT.Selection, btnNoListener);
		
		//Actions to perform when program is closed.
		shell.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent event) {
				DisplayCE_PopulateEditAssessment.recursiveSetEnabled(parentShell, true);
				//return returnVal;
			}
		});

		
		shell.addListener(SWT.Close, new Listener() {
			public void handleEvent(Event event) {
				DisplayCE_PopulateEditAssessment.recursiveSetEnabled(parentShell, true);
				shell.dispose();
			}
		});
		
		while (!shell.isDisposed()) {
			if (!shell.getDisplay().readAndDispatch()) {
				shell.getDisplay().sleep();
			}
		}
		
		return returnVal;
	}
	
	/**
	 * Popups a log on screen. Controls are disabled until a account is accepted.
	 * @param parentShell
	 * @return 
	 */
	public static Shell popupLogon(Shell parentShell) {		//TODO: perhaps add a forgotten password button?
		final String imageFileName = "splash.png";
		final Shell shell = new Shell(parentShell, SWT.TITLE);

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
			System.err.println("Warning: The file " + (new File(imageFileName)).toURI().getPath() + " was unable to be located.");
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
		userNameText.setText("Heather"); //TODO remove this - just for ease at the moment

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
		passwordText.setText("default"); //TODO remove this - just for ease at the moment

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

		String[] cohorts = Session.getCohorts();
		if (cohorts[0].equals(Errors.noDatabaseFolder)) {
			System.err.println("Error: Database folder was not found.");
			combo.add("Error: Database folder was not found.");
		}
		else if (cohorts.length == 0) {
			combo.add(Errors.noDatabaseFound);
		}
		else {
			for (String c : cohorts) {
				combo.add(c.substring(0, 4) + " - Semester " + c.substring(4));
			}
		}
		combo.select(0); //@todo find the last used one from the system DB

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
			public void handleEvent(Event event) {
				String selectedCohort = combo.getItems()[combo.getSelectionIndex()];
				int sem = selectedCohort.length();
				try {
					if(selectedCohort.equals(Errors.noDatabaseFound)) {selectedCohort = "";}
					else {selectedCohort = selectedCohort.substring(0, 4) + selectedCohort.substring(sem-1, sem);} 
					if (Session.login(userNameText.getText(), passwordText.getText(), selectedCohort)) {
                        DerbyUtils.dbConnect(selectedCohort);
						CohortData.loadData();
//						System.out.print("(CHECK) Number: " + CohortData.numStudents); //TODO delete
//						System.exit(0);//delete
						//Enables controls	
						for ( Control ctrl : shell.getParent().getChildren() ) ctrl.setEnabled(true);
						shell.close();
					} else popupMessage(shell, "Invalid username or password."+"\n\r"+"Please try again.", "Invalid Account");
				} catch (java.lang.StringIndexOutOfBoundsException e) {
					popupMessage(shell, "Invalid username or password."+"\n\r"+"Please try again.", "Invalid Account");
				}
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
				shell.getShell().close();
				System.exit(0);	//Gracefully exit system.
			}
		};
		btnQuit.addListener(SWT.Selection, btnQuitListener);

		shell.pack();
		shell.setLocation((shell.getDisplay().getPrimaryMonitor().getBounds().width-(shell.getSize().x))/2, 150);
		shell.open();
		
		return shell;

	}
	
	private static Text subAssessmentName;
	private static Text maximumMark;
	private static Text assessmentPercentage;
	/**
	 * Popups a message, to add sub assessment
	 * @param parentShell the display currently in use
	 * @param text the text the popup displays 
	 * @param title the title to display

	 */
	public static void popupAddSubAssessment(final Shell parentShell, String text, String title, final Tree tree) {
		// Disable the previous window
		DisplayCE_PopulateEditAssessment.recursiveSetEnabled(parentShell, false);
		
		final Shell shell = new Shell(parentShell, SWT.CLOSE | SWT.TITLE);
		shell.setImage(parentShell.getImage());

		// Set the Window Title
		shell.setText(title);
		shell.setLayout(new GridLayout(2, false));

		// Create a Label in the Shell
		Label label = new Label(shell, SWT.WRAP);
		label.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		label.setText(text);
		
		Label lblAssessmentName = new Label(shell, SWT.NONE);
		lblAssessmentName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAssessmentName.setText("Assessment Name:");
		
		subAssessmentName = new Text(shell, SWT.BORDER);
		GridData gd_assessmentName = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_assessmentName.widthHint = 180;
		subAssessmentName.setLayoutData(gd_assessmentName);
		
		Label lblMaximumMark = new Label(shell, SWT.NONE);
		lblMaximumMark.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMaximumMark.setText("Maximum Mark:");
		
		maximumMark = new Text(shell, SWT.BORDER);
		maximumMark.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		maximumMark.setTextLimit(3);
		Validation.validateInt(maximumMark);
		
		Label lblAssessmentPercentage = new Label(shell, SWT.NONE);
		lblAssessmentPercentage.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAssessmentPercentage.setText("Assessment Percentage:");
		
		assessmentPercentage = new Text(shell, SWT.BORDER);
		assessmentPercentage.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		assessmentPercentage.setTextLimit(5);
		Validation.validateDouble(assessmentPercentage);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, false, 2, 1));
		composite.setLayout(new GridLayout(2, false));
		
				//Button listener to deal with the button being pressed
				Listener btnOKListener = new Listener() {
					public void handleEvent(Event event) {
						if (!subAssessmentName.getText().isEmpty() && !maximumMark.getText().isEmpty() && !assessmentPercentage.getText().isEmpty()) {
							TreeItem data = new TreeItem(tree, SWT.NONE);
							data.setText(new String[] {subAssessmentName.getText(), maximumMark.getText(), assessmentPercentage.getText()});
							for (TreeColumn tc : tree.getColumns()) tc.pack();
							DisplayCE_PopulateEditAssessment.recursiveSetEnabled(parentShell, true);
							shell.close();
						} else {
							popupMessage(parentShell, "Null Value is not allowed.", "ERROR!");
						}
					}
				};
				
				Listener btnCancelListener = new Listener() {
					public void handleEvent(Event event) {
						DisplayCE_PopulateEditAssessment.recursiveSetEnabled(parentShell, true);
						shell.close();
					}
				};
		
				Button btnOk = new Button(composite, SWT.CENTER);
				btnOk.setText("OK");
				btnOk.addListener(SWT.Selection, btnOKListener);
				
				Button btnCancel = new Button(composite, SWT.NONE);
				btnCancel.setText("Cancel");
				btnCancel.addListener(SWT.Selection, btnCancelListener);
				
				shell.setDefaultButton(btnOk);

		shell.pack();
		shell.setLocation((shell.getDisplay().getPrimaryMonitor().getBounds().width-(shell.getSize().x))/2, 200);
		shell.open();
		
		shell.addListener(SWT.Close, new Listener() {
			public void handleEvent(Event event) {
				DisplayCE_PopulateEditAssessment.recursiveSetEnabled(parentShell, true);
				shell.dispose();
			}
		});
		
	}
	
	/**
	 * Popups a message, to add sub assessment
	 * @param parentShell the display currently in use
	 * @param text the text the popup displays 
	 * @param title the title to display
	 * @param assessmentName the assessmentName
	 * @param percentageUnit the percentage unit
	 * @wbp.parser.entryPoint
	 */
	public static void popupAddAssessment(final Shell parentShell, String text, String title, final Tree tree, final Text assessmentName, final Text percentageUnit) {
		// Disable the previous window
		DisplayCE_PopulateEditAssessment.recursiveSetEnabled(parentShell, false);
		
		final Shell shell = new Shell(parentShell, SWT.CLOSE | SWT.TITLE);
		shell.setImage(parentShell.getImage());

		// Set the Window Title
		shell.setText(title);
		shell.setLayout(new GridLayout(2, false));

		// Create a Label in the Shell
		Label label = new Label(shell, SWT.WRAP);
		label.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		label.setText(text);
		
		Label lblUnitName = new Label(shell, SWT.NONE);
		lblUnitName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUnitName.setText("Unit Name:");
;
		
		final Combo unitName = new Combo(shell, SWT.READ_ONLY);
		String[] unitNameString = new String[Data.Unit.length];
		for (int i = 0; i < Data.UnitName.length; i++) {
			unitNameString[i] = Data.Unit[i] + " " + Data.UnitName[i];
		}
		unitName.setItems(unitNameString);
		unitName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, false, 2, 1));
		composite.setLayout(new GridLayout(2, false));
		
				//Button listener to deal with the button being pressed
				Listener btnOKListener = new Listener() {
					public void handleEvent(Event event) {
						if (!unitName.getText().isEmpty()) {
							int i = 0;
							boolean added = false;
							while (i < tree.getItemCount() && !added){
								String[] selectedUnit = unitName.getText().split(" ");
								if (tree.getItem(i).getText().equals(selectedUnit[0])) {
									TreeItem data = new TreeItem(tree.getItem(i), SWT.NONE);
									data.setText(assessmentName.getText());
									for (TreeColumn tc : tree.getColumns()) tc.pack();
									added = true;
								}
								i++;
							}
							assessmentName.setText("");
							percentageUnit.setText("");
							DisplayCE_PopulateEditAssessment.recursiveSetEnabled(parentShell, true);
							shell.close();
						} else {
							popupMessage(parentShell, "Null Value is not allowed.", "ERROR!");
						}
					}
				};
				
				Listener btnCancelListener = new Listener() {
					public void handleEvent(Event event) {
						DisplayCE_PopulateEditAssessment.recursiveSetEnabled(parentShell, true);
						shell.close();
					}
				};
		
				Button btnOk = new Button(composite, SWT.CENTER);
				btnOk.setText("OK");
				btnOk.addListener(SWT.Selection, btnOKListener);
				
				Button btnCancel = new Button(composite, SWT.NONE);
				btnCancel.setText("Cancel");
				btnCancel.addListener(SWT.Selection, btnCancelListener);
				
				shell.setDefaultButton(btnOk);
		

				shell.pack();
				shell.setLocation((shell.getDisplay().getPrimaryMonitor().getBounds().width-(shell.getSize().x))/2, 200);
				shell.open();
				
		shell.addListener(SWT.Close, new Listener() {
			public void handleEvent(Event event) {
				DisplayCE_PopulateEditAssessment.recursiveSetEnabled(parentShell, true);
				shell.dispose();
			}
		});
		
	}

}
