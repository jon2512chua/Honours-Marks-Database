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
 * Displays the Creation/Editing section
 * @author Tim Lander
 */
public class DisplayCE {
	
	/**
	 * @param shell the shell the data is displayed on.
	 * @return the CTabFolder that contains the data
	 */
	public static CTabFolder display(Shell shell) {
		final Composite displayComposite = new Composite(shell, SWT.BORDER);
		displayComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1));
		displayComposite.setLayout(new FillLayout());
		
		//Set up tabs
		final CTabFolder CETabFolder = new CTabFolder(displayComposite, SWT.NONE);
		CETabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		//Create Backup Data
		CTabItem tbtmEditStudent = new CTabItem(CETabFolder, SWT.NONE);
		tbtmEditStudent.setText("Edit Student");
		
		//TODO: replace
		Button btnNewButton1 = new Button(CETabFolder, SWT.NONE);
		btnNewButton1.setText("TODO:delete button, replace with student stuff");

		tbtmEditStudent.setControl(btnNewButton1);
		//End replace
		
		CTabItem tbtmEditStaff = new CTabItem(CETabFolder, SWT.NONE);
		tbtmEditStaff.setText("Edit Staff");
		
		//TODO: replace
		Button btnNewButton2 = new Button(CETabFolder, SWT.NONE);
		btnNewButton2.setText("TODO:delete button, replace with staff stuff");
		
		tbtmEditStaff.setControl(btnNewButton2);
		//End replace

		return CETabFolder;
	}
}
