package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
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
	 * Creates two buttons , and places them in the bottom right corner.</br>
	 * The buttons are 'Save Changes' and 'Discard Changes'
	 * @param parent the composite to put the buttons in
	 * @return the created buttons, in the order {Save Changes, Discard Changes}
	 */
	public static Button[] addSaveDiscardChangesButton(Composite parent) {
		Composite buttonsComposite = new Composite(parent, SWT.NONE);
		buttonsComposite.setLayout(new RowLayout(SWT.HORIZONTAL));
		buttonsComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, true, 2, 1));

		Button btnSaveChanges = new Button(buttonsComposite, SWT.NONE);
		btnSaveChanges.setText("Save Changes");

		Button btnDiscardChanges = new Button(buttonsComposite, SWT.NONE);
		btnDiscardChanges.setText("Discard Changes");
		
		Button[] returnButtons = new Button[2];
		returnButtons[0] = btnSaveChanges;
		returnButtons[1] = btnDiscardChanges;
		return returnButtons;
	}

}
