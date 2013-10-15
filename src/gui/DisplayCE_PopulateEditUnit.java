package gui;

import java.sql.SQLException;
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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
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
@SuppressWarnings("unused")
public class DisplayCE_PopulateEditUnit {
	
	private static Text points;
	private static Text unitName;
	private static Text unitCode;
	private static Text creditPoints;
	private static Map<TreeItem, StringBuffer[]> TreeItemMap = new HashMap<TreeItem, StringBuffer[]>();
	private static boolean hardRefreshNeeded;

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

		final Composite rComposite = new Composite(editUnitComposite, SWT.NONE);
		rComposite.setLayout(new GridLayout(2, false));
		rComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblUnitCode = new Label(rComposite, SWT.NONE);
		lblUnitCode.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUnitCode.setText("Unit Code:");

		unitCode = new Text(rComposite, SWT.BORDER);
		unitCode.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
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
		creditPoints.setTextLimit(2);
		Validation.validateInt(creditPoints);
		

		Button[] btnSaveDiscard = CommonButtons.addSaveDiscardChangesButton(rComposite);

		tbtmEditUnit.setControl(editUnitComposite);
		
		DisplayCE_PopulateEditAssessment.recursiveSetEnabled(rComposite, false);
		
		btnSaveDiscard[0].addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				TreeItem[] selected = unitTree.getSelection();
				if (selected.length != 0) {
					if (unitTree.indexOf(unitTree.getSelection()[0]) == 0) {
						if (!unitName.getText().isEmpty() && !unitCode.getText().isEmpty()) {
							try {
								new Unit(unitCode.getText().toUpperCase(), unitName.getText(), Integer.parseInt(creditPoints.getText()));
								PopupWindow.popupMessage(unitTree.getShell(), "New Unit created successfully.", "Save Successful");
								hardRefreshNeeded = true;
								refreshTree(unitTree);
							} catch (SQLException ex) {
								PopupWindow.popupMessage(unitTree.getShell(), "New unit was unable to be created. \nPossible duplicate unit code.", "ERROR! Save Unsuccessful");
							}
						} else PopupWindow.popupMessage(unitTree.getShell(), "Null Value is not allowed.", "ERROR!");
					} else {
						Unit unit = Unit.getUnitByCode(selected[0].getText());
						unit.setName(unitName.getText());
						unit.setUnitCode(unitCode.getText().toUpperCase());
						unit.setPoints(Integer.parseInt(creditPoints.getText()));
						try {
							unit.updateRow();
						} catch (SQLException e) {
							PopupWindow.popupMessage(unitTree.getShell(), "Unable to save change. \nPossible corrupt data.", "ERROR! Save Unsuccessful");
						}
					}
				}
			}
		});
		
		for (Unit u : CohortData.units) {
			TreeItem unit = new TreeItem(unitTree, SWT.NONE);
			TreeItemMap.put(unit, new StringBuffer[]{u.unitCode, u.name});
		}
		
		refreshTree(unitTree);
		
		for (TreeColumn tc : unitTree.getColumns()) tc.pack();
		unitTree.pack();
		
		unitTree.addListener(SWT.Selection,new Listener() {
			public void handleEvent(Event event) {
				DisplayCE_PopulateEditAssessment.recursiveSetEnabled(rComposite, true);
				TreeItem[] selected = unitTree.getSelection();
				if (selected.length != 0) {
					String[] selectedString = selected[0].getText().split(" ");
					if (unitTree.indexOf(unitTree.getSelection()[0]) == 0) {
						unitCode.setEnabled(true);
						unitCode.setText("");
						unitName.setText("");
						creditPoints.setText("");
					} else {
						Unit u = Unit.getUnitByCode(selectedString[0]);
						populateSelectedData(u);
					}
				}
			}
		});
	
	}
	
	/**
	 * Displays data relevant to which student was clicked on.
	 * @param student the student that was clicked on
	 */
	private static void populateSelectedData(Unit unit) {
		try {														//Found values
			unitCode.setEnabled(false);
			unitCode.setText(unit.getUnitCode()+"");
			unitName.setText(unit.getName()+"");
			creditPoints.setText(unit.getPoints()+"");

		} catch (java.lang.NullPointerException e) {				//Default values
			unitCode.setEnabled(true);
			unitCode.setText("");
			unitName.setText("");
			creditPoints.setText("");
		}
	}
	
	/**
	 * Refreshes all data displayed in the tree
	 * @param tree the tree which is to be refreshed
	 */
	public static void refreshTree(Tree tree) {
		if (hardRefreshNeeded) {
			hardRefresh(tree);
		}
		for ( TreeItem ti : tree.getItems() ) {
			try {
				ti.setText(new String[] {TreeItemMap.get(ti)[0].toString() + " " + TreeItemMap.get(ti)[1].toString()});
			} catch (java.lang.NullPointerException e) {}
		}
	}

	private static void hardRefresh(Tree tree) {
		hardRefreshNeeded = false;
		for (TreeItem ti : tree.getItems()) ti.dispose();
		
		TreeItem newStudent = new TreeItem(tree, SWT.NONE);
		newStudent.setText(new String[] {"+", "Add New Student"});
		for (Unit u : CohortData.units) {
			TreeItem unit = new TreeItem(tree, SWT.NONE);
			TreeItemMap.put(unit, new StringBuffer[]{u.unitCode, u.name});
			refreshTree(tree);
		}
	}
	

}