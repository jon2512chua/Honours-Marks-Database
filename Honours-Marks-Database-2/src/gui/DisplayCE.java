package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * Displays the Creation/Editing section
 * @author Tim Lander
 */
public class DisplayCE {
	
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
		final CTabFolder CETabFolder = new CTabFolder(displayComposite, SWT.NONE);
		CETabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmEditStudent = new CTabItem(CETabFolder, SWT.NONE);
		tbtmEditStudent.setText("Edit Student");
		
		CTabItem tbtmEditStaff = new CTabItem(CETabFolder, SWT.NONE);
		tbtmEditStaff.setText("Edit Staff");
		
		CTabItem tbtmEditCourse = new CTabItem(CETabFolder, SWT.NONE);
		tbtmEditCourse.setText("Edit Course");
		
		CTabItem tbtmEditUnit = new CTabItem(CETabFolder, SWT.NONE);
		tbtmEditUnit.setText("Edit Unit");
		
		CTabItem tbtmEditAssessment = new CTabItem(CETabFolder, SWT.NONE);
		tbtmEditAssessment.setText("Edit Assessment");
		
		
	//TODO: replace with student stuff
		Composite editStudentComposite = PopulateEditStudent.populate(CETabFolder);
		tbtmEditStudent.setControl(editStudentComposite);
	//End replace
		
		
	//TODO: replace with staff stuff
		Button btnNewButton2 = new Button(CETabFolder, SWT.NONE);
		btnNewButton2.setText("TODO:delete button, replace with staff stuff");
		
		tbtmEditStaff.setControl(btnNewButton2);
	//End replace

		return CETabFolder;
	}
}
