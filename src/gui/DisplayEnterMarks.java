package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * Displays the marks entering screen
 * @author Tim Lander
 */
public class DisplayEnterMarks {
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
		final CTabFolder marksTabFolder = new CTabFolder(displayComposite, SWT.NONE);
		marksTabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		//Student View
		DisplayEnterMarks_PopulateStudentView.populate(marksTabFolder, "Student Orientation");
		
		//Marker View
		DisplayEnterMarks_PopulateMarkerView.populate(marksTabFolder, "Marker Orientation");
		
		
		return marksTabFolder;
	}
}
