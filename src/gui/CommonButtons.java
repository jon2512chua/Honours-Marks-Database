package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * Class for dealing with common arrangements of buttons.</br>
 * Designed to help give the UI a consistent feel.
 * @author Tim Lander
 */
public class CommonButtons {
	
	/**
	 * Creates two buttons, and places them in the bottom right corner.</br>
	 * The buttons are 'Save Changes' and 'Discard Changes'
	 * @param parent the composite to put the buttons in
	 * @return the created buttons, in the order {Save Changes, Discard Changes}
	 */
	public static Button[] addSaveDiscardChangesButton(Composite parent) {
		Composite buttonsComposite = new Composite(parent, SWT.NONE);
		buttonsComposite.setLayout(new RowLayout(SWT.HORIZONTAL));
		buttonsComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, false, 2, 1));

		Button btnSaveChanges = new Button(buttonsComposite, SWT.NONE);
		btnSaveChanges.setText("Save Changes");

		Button btnDiscardChanges = new Button(buttonsComposite, SWT.NONE);
		btnDiscardChanges.setText("Discard Changes");
		
		Button[] returnButtons = new Button[2];
		returnButtons[0] = btnSaveChanges;
		returnButtons[1] = btnDiscardChanges;
		return returnButtons;
	}
	
	/**
	 * Creates three buttons, and places them in the bottom right corner.</br>
	 * The buttons are 'Change Username', 'Change Password' and 'Change Secret Question/Answer'
	 * @param parent the composite to put the buttons in
	 * @return the created buttons, in the order {Change Username, Change Password, Change Secret Question/Answer}
	 */
	public static Button[] addUpdateSettingsButtons(Composite parent) {
		Composite buttonsComposite = new Composite(parent, SWT.NONE);
		buttonsComposite.setLayout(new RowLayout(SWT.HORIZONTAL));
		buttonsComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, false, 2, 1));

		Button btnChangeUsername = new Button(buttonsComposite, SWT.NONE);
		btnChangeUsername.setText("Change Username");

		Button btnChangePassword = new Button(buttonsComposite, SWT.NONE);
		btnChangePassword.setText("Change Password");
		
		Button btnChangeQuestion = new Button(buttonsComposite, SWT.NONE);
		btnChangeQuestion.setText("Change Secret Question/Answer");
		
		Button[] returnButtons = new Button[3];
		returnButtons[0] = btnChangeUsername;
		returnButtons[1] = btnChangePassword;
		returnButtons[2] = btnChangeQuestion;
		return returnButtons;
	}
	
	/**
	 * Creates two buttons, and places them in the bottom right corner.</br>
	 * The buttons are 'Save Changes' and 'Delete "whatToDelete"'
	 * @param parent the composite to put the buttons in
	 * @param whatToDelete the string describing what you are deleting
	 * @return the created buttons, in the order {Save Changes, Discard Changes}
	 */
	public static Button[] addSaveChangesDeleteButton(Composite parent, String whatToDelete) {
		Composite buttonsComposite = new Composite(parent, SWT.NONE);
		buttonsComposite.setLayout(new RowLayout(SWT.HORIZONTAL));
		buttonsComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, false, 2, 1));

		Button btnSaveChanges = new Button(buttonsComposite, SWT.NONE);
		btnSaveChanges.setText("Save Changes");

		Button btnDelete = new Button(buttonsComposite, SWT.NONE);
		btnDelete.setText("Delete " + whatToDelete);
		
		Button[] returnButtons = new Button[2];
		returnButtons[0] = btnSaveChanges;
		returnButtons[1] = btnDelete;
		return returnButtons;
	}
	
	/**
	 * Creates two buttons, and places them in the bottom right corner.</br>
	 * The buttons are 'Create Now' and 'Discard Changes'
	 * @param parent the composite to put the buttons in
	 * @return the created buttons, in the order {Create Now, Discard Changes}
	 */
	public static Button[] addCreateDiscardChangesButton(Composite parent) {
		Composite buttonsComposite = new Composite(parent, SWT.NONE);
		buttonsComposite.setLayout(new RowLayout(SWT.HORIZONTAL));
		buttonsComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, false, 2, 1));

		Button btnCreateNow = new Button(buttonsComposite, SWT.NONE);
		btnCreateNow.setText("Create Now");

		Button btnDiscardChanges = new Button(buttonsComposite, SWT.NONE);
		btnDiscardChanges.setText("Discard Changes");
		
		Button[] returnButtons = new Button[2];
		returnButtons[0] = btnCreateNow;
		returnButtons[1] = btnDiscardChanges;
		return returnButtons;
	}
	
	/**
	 * Creates three buttons and places them in the top left corner </br>
	 * The buttons are '+', '-', 'Export Report to Excel'
	 * @param parent the composite to put the buttons in
	 * @return the created buttons, in the order {+, -, Export}
	 * @wbp.parser.entryPoint
	 */
	public static Button[] addReportTreeTop(Composite parent) {
		Composite buttonsComposite = new Composite(parent, SWT.NONE);
		RowLayout rl_buttonsComposite = new RowLayout(SWT.HORIZONTAL);
		rl_buttonsComposite.spacing = 0;
		rl_buttonsComposite.marginLeft = 0;
		rl_buttonsComposite.marginRight = 0;
		rl_buttonsComposite.marginTop = 0;
		rl_buttonsComposite.marginBottom = 0;
		buttonsComposite.setLayout(rl_buttonsComposite);
		buttonsComposite.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, true, false, 2, 1));
		
		RowData rd_ButtonsPM = new RowData(33,33);

		Button btnExpand = new Button(buttonsComposite, SWT.NONE);
		btnExpand.setLayoutData(rd_ButtonsPM);
		btnExpand.setText("+");

		Button btnCollapse = new Button(buttonsComposite, SWT.NONE);
		btnCollapse.setLayoutData(rd_ButtonsPM);
		btnCollapse.setText("-");
		
		Button btnExport = new Button(buttonsComposite, SWT.NONE);
		RowData rd_btnExport = new RowData();
		rd_btnExport.height = 33;
		btnExport.setLayoutData(rd_btnExport);
		btnExport.setText("Export Report to Excel");
		
		Button[] returnButtons = new Button[3];
		returnButtons[0] = btnExpand;
		returnButtons[1] = btnCollapse;
		returnButtons[2] = btnExport;
		return returnButtons;
	}

}
