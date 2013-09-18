package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.RowData;

/**
 * Popup Windows
 * @author Tim Lander
 */
public class PopupWindow {
	/**
	 * Popups a message. No line wrapping currently implemented.
	 * @wbp.parser.entryPoint
	 * @param display the display currently in use
	 * @param text the text the popup displays 
	 * @param title the title to display
	 */
	public static void display(Display display, String text, String title) {
		final Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE);

		// =====================
		// Set the Window Title
		// =====================
		shell.setText(title);
		RowLayout rl_shell = new RowLayout(SWT.VERTICAL);
		rl_shell.marginTop = 8;
		rl_shell.marginRight = 10;
		rl_shell.marginLeft = 10;
		rl_shell.marginBottom = 10;
		rl_shell.spacing = 25;
		rl_shell.center = true;
		shell.setLayout(rl_shell);

		// ============================
		// Create a Label in the Shell
		// ============================
		Label label = new Label(shell, SWT.NONE);
		label.setText(text);

		Button btnOk = new Button(shell, SWT.CENTER);
		btnOk.setLayoutData(new RowData(75, SWT.DEFAULT));
		btnOk.setText("OK");

		shell.pack();
		shell.open();
		shell.setLocation((shell.getDisplay().getBounds().width-(shell.getSize().x))/2, 200);	//Centers popup

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

}
