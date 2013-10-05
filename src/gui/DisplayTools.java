package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * Displays the Tools section
 * @author Tim Lander
 */
public class DisplayTools {


	/**
	 * @wbp.parser.entryPoint
	 * @param parent the parent composite the data is displayed on.
	 * @return the CTabFolder that contains the data
	 */
	public static final CTabFolder display(Composite parent) {

		final Composite displayComposite = new Composite(parent, SWT.BORDER);
		displayComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1));
		displayComposite.setLayout(new FillLayout());

		//Set up tabs
		final CTabFolder toolsTabFolder = new CTabFolder(displayComposite, SWT.NONE);
		toolsTabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		//Import Data tab
		DisplayTools_PopulateImportData.populate(toolsTabFolder, "Import Data");
		
		//Export Data tab
		DisplayTools_PopulateExportData.populate(toolsTabFolder, "Export Data");		

		return toolsTabFolder;
	}


}
