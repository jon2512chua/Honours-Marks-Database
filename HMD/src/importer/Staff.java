/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package importer;

import java.util.PriorityQueue;

/**
 *
 * @author nickos
 */
@SuppressWarnings("unused")	//TODO: remove later
public class Staff {
	private int staffID;
	private String name;
	private String contact;
	private int numberMarks;
	private PriorityQueue<Mark> marks;

	public Staff(int sID, String name, String contact) {
		this.staffID = sID;
		this.name = name;
		this.contact = contact;
		this.marks = null;
		this.numberMarks = marks.size();                  
	}


}
