package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
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
		
		
		final Composite editStudentComposite = new Composite(CETabFolder, SWT.NONE);
		editStudentComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		GridLayout gl_editStudentComposite = new GridLayout(3, false);
		gl_editStudentComposite.horizontalSpacing = -1;
		gl_editStudentComposite.verticalSpacing = 0;
		gl_editStudentComposite.marginWidth = 0;
		gl_editStudentComposite.marginHeight = 0;
		editStudentComposite.setLayout(gl_editStudentComposite);
		
		final Tree sturentTree = new Tree(editStudentComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL);
		sturentTree.setHeaderVisible(true);
		sturentTree.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1));
		
		//TODO: autoresize widths
		TreeColumn studentTree_studentNumber= new TreeColumn(sturentTree, SWT.LEFT);
		studentTree_studentNumber.setWidth(100);
		studentTree_studentNumber.setText("Student Number");
		TreeColumn studentTree_studentName = new TreeColumn(sturentTree, SWT.LEFT);
		studentTree_studentName.setWidth(100);
		studentTree_studentName.setText("Student Name");
		
		TreeItem newStudent = new TreeItem(sturentTree, SWT.NONE);
		newStudent.setText(new String[] {"+", "Add New Student"});
		for (int sn=0; sn<5; sn++) {
			TreeItem student = new TreeItem(sturentTree, SWT.NONE);
			student.setText(new String[] {Data.StudentNumber[sn], Data.StudentNameTitle[sn] + " " + Data.StudentNameFirst[sn].charAt(0) + ". " + Data.StudentNameLast[sn]});
		}

		
		
		Composite rComposite = new Composite(editStudentComposite, SWT.BORDER);
		rComposite.setLayout(new GridLayout(2, false));
		rComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		Label lblStudentNumber = new Label(rComposite, SWT.NONE);
		lblStudentNumber.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStudentNumber.setBounds(0, 0, 55, 15);
		lblStudentNumber.setText("Student Number:");
		
		studentNumber = new Text(rComposite, SWT.BORDER);
		studentNumber.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
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
		
		//TODO: autofit column widths
		//TODO: add scrollbar
		Tree supervisorTree = new Tree(rComposite, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		supervisorTree.setHeaderVisible(true);
		supervisorTree.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		TreeColumn supervisorTree_staffNumber= new TreeColumn(supervisorTree, SWT.LEFT);
		supervisorTree_staffNumber.setWidth(100);
		supervisorTree_staffNumber.setText("Staff Number");
		TreeColumn supervisorTree_staffName = new TreeColumn(supervisorTree, SWT.LEFT);
		supervisorTree_staffName.setWidth(100);
		supervisorTree_staffName.setText("Staff Name");
		
		Composite buttonsComposite = new Composite(rComposite, SWT.NONE);
		buttonsComposite.setLayout(new RowLayout(SWT.HORIZONTAL));
		buttonsComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, true, 2, 1));
		
		Button btnSaveChanges = new Button(buttonsComposite, SWT.NONE);
		btnSaveChanges.setText("Save Changes");
		
		Button btnDiscardChanges = new Button(buttonsComposite, SWT.NONE);
		btnDiscardChanges.setText("Discard Changes");
		for (int sn=0; sn<5; sn++) {
			TreeItem supervisor = new TreeItem(supervisorTree, SWT.NONE);
			supervisor.setText(new String[] {Data.StaffNumber[sn], Data.StaffNameTitle[sn] + " " + Data.StaffNameFirst[sn].charAt(0) + ". " + Data.StaffNameLast[sn]});
		}
		
		return editStudentComposite;
		
	}

}
