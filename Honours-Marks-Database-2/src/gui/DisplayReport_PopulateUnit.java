package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Unit Report
 * @author Tim Lander
 *
 */
public class DisplayReport_PopulateUnit {
	static void populate(Tree unitTree) {
		for (int unitNumber=0; unitNumber<5; unitNumber++) {
			TreeItem unit = new TreeItem(unitTree, SWT.NONE);
			unit.setText(new String[] {Data.Unit[unitNumber]});
		}
	}
}
