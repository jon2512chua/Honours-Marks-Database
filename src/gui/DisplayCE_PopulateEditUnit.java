package gui;

import java.util.HashMap;
import java.util.Map;

import logic.CohortData;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import orm.Unit;

/**
 * Edit Units Section
 * @author Johnathan Lim
 */
public class DisplayCE_PopulateEditUnit {
	@SuppressWarnings("unused")
	private static Text points;
	private static Text unitName;
	private static Text unitCode;
	private static Text creditPoints;
	private static Map<TreeItem, StringBuffer[]> TreeItemMap = new HashMap<TreeItem, StringBuffer[]>();

	/**
	 * Populates the Edit Unit Tab
	 * @param CETabFolder the folder to put the tab in
	 * @param tabName the name of the tab
	 * @wbp.parser.entryPoint
	 */	
	public static void populate(final CTabFolder CETabFolder, String tabName) {
		CTabItem tbtmEditUnit = new CTabItem(CETabFolder, SWT.NONE);
		tbtmEditUnit.setText(tabName);

		//TODO: replace with staff stuff
		final Composite editUnitComposite = new Composite(CETabFolder, SWT.NONE);
		editUnitComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		GridLayout gl_editUnitComposite = new GridLayout(2, false);
		gl_editUnitComposite.horizontalSpacing = -1;
		gl_editUnitComposite.verticalSpacing = 0;
		gl_editUnitComposite.marginWidth = 0;
		gl_editUnitComposite.marginHeight = 0;
		editUnitComposite.setLayout(gl_editUnitComposite);

		final Tree unitTree = new Tree(editUnitComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		unitTree.setHeaderVisible(true);
		unitTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		
		TreeColumn trclmnUnit = new TreeColumn(unitTree, SWT.NONE);
		trclmnUnit.setWidth(100);
		trclmnUnit.setText("Unit");
		
		TreeItem newUnit = new TreeItem(unitTree, SWT.NONE);
		newUnit.setText(new String[] {"+  Add New Unit"});

		Composite rComposite = new Composite(editUnitComposite, SWT.NONE);
		rComposite.setLayout(new GridLayout(2, false));
		rComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblUnitCode = new Label(rComposite, SWT.NONE);
		lblUnitCode.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUnitCode.setText("Unit Code:");

		unitCode = new Text(rComposite, SWT.BORDER);
		unitCode.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Label lblUnitName = new Label(rComposite, SWT.NONE);
		lblUnitName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUnitName.setText("Unit Name:");
		
		unitName = new Text(rComposite, SWT.BORDER);
		GridData gd_unitName = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_unitName.widthHint = 180;
		unitName.setLayoutData(gd_unitName);
		
		Label lblCreditPoints = new Label(rComposite, SWT.NONE);
		lblCreditPoints.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblCreditPoints.setText("Credit Points");
		
		creditPoints = new Text(rComposite, SWT.BORDER);
		creditPoints.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1));
		
		@SuppressWarnings("unused")
		Button[] btnSaveDiscard = CommonButtons.addSaveChangesDeleteButton(rComposite, "Unit");

		tbtmEditUnit.setControl(editUnitComposite);
		
		//for (Unit s : CohortData.units) {
			//TreeItem unit = new TreeItem(unitTree, SWT.NONE);
			//TreeItemMap.put(unit, new StringBuffer[]{new StringBuffer("CITS3200"), new StringBuffer("Prof Comp")});
		//}
		
		//refreshTree(unitTree);
		
		for (TreeColumn tc : unitTree.getColumns()) tc.pack();
		unitTree.pack();

	}
	
	/**
	 * Refreshes all data displayed in the tree
	 * @param tree the tree which is to be refreshed
	 */
	public static void refreshTree(Tree tree) {
		for ( TreeItem ti : tree.getItems() ) {
			try {
				ti.setText(new String[] {TreeItemMap.get(ti)[0].toString() + " " + TreeItemMap.get(ti)[1].toString()});
			} catch (java.lang.NullPointerException e) {}
		}
	}

}