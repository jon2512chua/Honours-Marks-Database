package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Displays the Tools section
 * @author Tim Lander
 */
public class DisplayTools {
	
	/**
	 * @param shell the shell the data is displayed on.
	 * @return the CTabFolder that contains the data
	 */
	public static CTabFolder display(Shell shell) {
		final Composite displayComposite = new Composite(shell, SWT.BORDER);
		displayComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1));
		displayComposite.setLayout(new FillLayout());
		
		//Set up tabs
		final CTabFolder toolsTabFolder = new CTabFolder(displayComposite, SWT.NONE);
		toolsTabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		//Create Backup Data
		CTabItem tbtmTools = new CTabItem(toolsTabFolder, SWT.NONE);
		tbtmTools.setText("Backup");
		
		Button btnNewButton = new Button(toolsTabFolder, SWT.NONE);
		btnNewButton.setText("Backup Now!");
		
		tbtmTools.setControl(btnNewButton);

		return toolsTabFolder;
	}
}
