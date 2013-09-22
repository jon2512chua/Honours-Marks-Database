package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;

/**
 * Edit Students Section
 * @author Tim Lander
 */
public class PopulateEditStudent {
	private static Text studentNumber;
	private static Text title;
	private static Text lastName;
	private static Text firstName;
	private static Text dissertationTitle;

	/**
	 * @wbp.parser.entryPoint
	 */
	static Composite populate(final CTabFolder CETabFolder) {

		//TODO: sort tree's be clicking on title
		//TODO: populate trees
		//TODO: save data
		final Composite editStudentComposite = new Composite(CETabFolder, SWT.NONE);
		editStudentComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		GridLayout gl_editStudentComposite = new GridLayout(3, false);
		gl_editStudentComposite.horizontalSpacing = -1;
		gl_editStudentComposite.verticalSpacing = 0;
		gl_editStudentComposite.marginWidth = 0;
		gl_editStudentComposite.marginHeight = 0;
		editStudentComposite.setLayout(gl_editStudentComposite);

		final Tree studentTree = new Tree(editStudentComposite, SWT.BORDER | SWT.FULL_SELECTION);
		studentTree.setHeaderVisible(true);
		studentTree.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1));

		TreeColumn studentTree_studentNumber= new TreeColumn(studentTree, SWT.LEFT);
		studentTree_studentNumber.setText("Student Number");
		TreeColumn studentTree_studentName = new TreeColumn(studentTree, SWT.LEFT);
		studentTree_studentName.setText("Student Name");

		TreeItem newStudent = new TreeItem(studentTree, SWT.NONE);
		newStudent.setText(new String[] {"+", "Add New Student"});
		for (int sn=0; sn<5; sn++) {
			TreeItem student = new TreeItem(studentTree, SWT.NONE);
			student.setText(new String[] {Data.StudentNumber[sn], Data.StudentNameTitle[sn] + " " + Data.StudentNameFirst[sn].charAt(0) + ". " + Data.StudentNameLast[sn]});
		}



		Composite rComposite = new Composite(editStudentComposite, SWT.NONE);
		rComposite.setLayout(new GridLayout(2, false));
		rComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		Label lblStudentNumber = new Label(rComposite, SWT.NONE);
		lblStudentNumber.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStudentNumber.setText("Student Number:");
		studentNumber = new Text(rComposite, SWT.BORDER);
		studentNumber.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		studentNumber.setTextLimit(8);
		studentNumber.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {		//Check if the value entered is an integer
				if (e.character != '\u0008' && e.character != '\u007F') {	//Allows backspace/delete
					try {
						Integer.parseInt(e.text);
					} catch (final NumberFormatException numberFormatException) {
						e.doit = false;
					}
				}
			}
		});

		Label lblTitle = new Label(rComposite, SWT.NONE);
		lblTitle.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTitle.setText("Title:");
		title = new Text(rComposite, SWT.BORDER);
		title.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblLastName = new Label(rComposite, SWT.NONE);
		lblLastName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLastName.setText("Last Name:");
		lastName = new Text(rComposite, SWT.BORDER);
		lastName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblFirstName = new Label(rComposite, SWT.NONE);
		lblFirstName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFirstName.setText("First Name:");
		firstName = new Text(rComposite, SWT.BORDER);
		firstName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label seperator = new Label(rComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
		seperator.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label lblDissertationTitle = new Label(rComposite, SWT.NONE);
		lblDissertationTitle.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDissertationTitle.setText("Dissertation Title:");
		dissertationTitle = new Text(rComposite, SWT.BORDER);
		dissertationTitle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblSupervisors = new Label(rComposite, SWT.NONE);
		lblSupervisors.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblSupervisors.setText("Supervisor(s):");
		Tree supervisorTree = new Tree(rComposite, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		supervisorTree.setHeaderVisible(true);
		supervisorTree.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1));
		TreeColumn supervisorTree_staffNumber= new TreeColumn(supervisorTree, SWT.LEFT);
		supervisorTree_staffNumber.setText("Staff Number");
		TreeColumn supervisorTree_staffName = new TreeColumn(supervisorTree, SWT.LEFT);
		supervisorTree_staffName.setText("Staff Name");
		for (int sn=0; sn<5; sn++) {
			TreeItem supervisor = new TreeItem(supervisorTree, SWT.NONE);
			supervisor.setText(new String[] {Data.StaffNumber[sn], Data.StaffNameTitle[sn] + " " + Data.StaffNameFirst[sn].charAt(0) + ". " + Data.StaffNameLast[sn]});
		}

		Composite buttonsComposite = new Composite(rComposite, SWT.NONE);
		buttonsComposite.setLayout(new RowLayout(SWT.HORIZONTAL));
		buttonsComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, true, 2, 1));

		Button btnSaveChanges = new Button(buttonsComposite, SWT.NONE);
		btnSaveChanges.setText("Save Changes");

		Button btnDiscardChanges = new Button(buttonsComposite, SWT.NONE);
		btnDiscardChanges.setText("Discard Changes");
		

		//Auto Fit Columns
		for (TreeColumn tc : supervisorTree.getColumns()) tc.pack();
		supervisorTree.pack();
		for (TreeColumn tc : studentTree.getColumns()) tc.pack();
		studentTree.pack();


		return editStudentComposite;

	}

}
