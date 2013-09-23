package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Marker Report
 * @author Tim Lander
 */
public class DisplayReport_PopulateMarker {
	static void populate(Tree markerTree) {
		for (int sn=0; sn<5; sn++) {
			TreeItem marker = new TreeItem(markerTree, SWT.NONE);
			
			marker.setText(new String[] {Data.StaffNumber[sn]});
			//marker.setExpanded(true);
			TreeItem studentNameTitle = new TreeItem(marker, SWT.NONE);		//TODO: in theory these could be combined into one row
			studentNameTitle.setText(new String[] {"Title", Data.StaffNameTitle[sn]});
			TreeItem studentNameLast = new TreeItem(marker, SWT.NONE);
			studentNameLast.setText(new String[] {"Last Name", Data.StaffNameLast[sn]});
			TreeItem studentNameFirst = new TreeItem(marker, SWT.NONE);
			studentNameFirst.setText(new String[] {"First Name", Data.StaffNameFirst[sn]});
			//TreeItem studentDissTitle = new TreeItem(marker, SWT.NONE);
			//studentDissTitle.setText(new String[] {"Dissertation Title", StudentDissTitle[sn]});
			//TreeItem studentSuper = new TreeItem(marker, SWT.NONE);
			//studentSuper.setText(new String[] { "Supervisor", StaffNameTitle[sn] + " " + StaffNameFirst[sn].charAt(0) + ". " + StaffNameLast[sn]});
			//TreeItem studentFinalMark = new TreeItem(student, SWT.NONE);
			//studentFinalMark.setText(new String[] {"Dissertation Title", SDissTitle[sn]});

			/*for (int units=sn/2; units<=sn/2+3; units++) {
				TreeItem unit = new TreeItem(marker, SWT.NONE);
				unit.setText(new String[] {Data.Unit[units]});
				TreeItem unitName = new TreeItem(unit, SWT.NONE);
				unitName.setText(new String[] {"Unit Name", Data.UnitName[units]});
				TreeItem unitMark = new TreeItem(unit, SWT.NONE);
				unitMark.setText(new String[] {"Unit Mark", "100%"});
			}*/

		}
	}
}
