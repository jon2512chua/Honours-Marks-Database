package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
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

		//Student Tab
		DisplayCE_PopulateEditStudent.populate(CETabFolder, "Edit Student");
		
		//Staff Tab
		DisplayCE_PopulateEditStaff.populate(CETabFolder, "Edit Staff");

		//Course Tab
		DisplayCE_PopulateEditCourse.populate(CETabFolder, "Edit Course");
		
		//Unit Tab
		DisplayCE_PopulateEditUnit.populate(CETabFolder, "Edit Unit");
		
		//Assessment Tab
		DisplayCE_PopulateEditAssessment.populate(CETabFolder, "Edit Assessment");
		
		//Cohort Tab
		DisplayCE_PopulateEditCohort.populate(CETabFolder, "New Cohort");

		return CETabFolder;
	}
}
