package cohortSetupSubsystem;

import java.util.Iterator;
import java.util.List;
/**
 * THIS CLASS IS NOT IN USE -> IT WAS FROM AN EARLIER PROTOTYPING VERSION
 * @author Nicholas Abbey
 */
public class Student {



	public int studentId;
	public String discipline;
	public String lastName;
	public String firstName;
	public String dissTitle;
	public List<String> supervisors;	// Will be Staff type
	public int cumulativeMark;			// 
	public int range;
	@SuppressWarnings("unused")			//TODO: remove later
	private String[] units;				// Will be Unit[]

	/**
	 * Constructor for initial instantiation of student object - either from form entry or mass entry
	 * @param sId
	 * @param disc
	 * @param lName
	 * @param fName
	 * @param dissT
	 */
	public Student(int sId, String disc, String lName, String fName, String dissT, List<String> sups) {
		studentId = sId;
		discipline = disc;
		lastName = lName;
		firstName = fName;
		dissTitle = dissT;
		supervisors = sups;
		cumulativeMark = 0;
		units = null; // have a method in discipline to initialise this properly. getUnits(cohort, discipline)
	}

	public void toStdOut() {
		if(dissTitle.equals("")) dissTitle = "<no topic>";
		String sup = "";
		Iterator<String> supIt = supervisors.iterator();
		while (supIt.hasNext()) sup += supIt.next() + " ";
		if(sup.equals("")) sup = "<no supervisors>";
		System.out.println(studentId + " " + " " + discipline + " " + lastName + " " + firstName + " " + dissTitle + " " + sup);
	}

	public String toSQL(){return "";}

}
