package export;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import logic.CohortData;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import orm.Assessment;
import orm.Staff;
import orm.Student;
import orm.SubAssessment;
import orm.Unit;
/**
 * This is a class for exporting particular statistics from the database into excel format.
 * @author Nicholas Abbey
 * @version 16/10/13
 *
 */
public class ToExcel {
	
	//TODO set some cell styles for export http://poi.apache.org/spreadsheet/quick-guide.html
	
	/**
	 * Write a summary of the students' final grades and key information to .xls
	 * 		NOTE: don't try to use a .xlsx filename
	 * @param filepath to export to
	 */
	public static void studentSummaries(String filepath) throws Exception {
		try {
			FileOutputStream fileOut = new FileOutputStream(filepath);
			
			Workbook wb = new HSSFWorkbook();
			Sheet s = wb.createSheet("Student Summaries");
		
			Row row = s.createRow(0);
			row.createCell(0).setCellValue("Student ID");
		    row.createCell(1).setCellValue("Last Name");
		    row.createCell(2).setCellValue("First Name");
		    row.createCell(3).setCellValue("Discipline");
		    row.createCell(4).setCellValue("Mark");
		    row.createCell(5).setCellValue("Grade");
		    row.createCell(6).setCellValue("Supervisors:");
		    
		    int rowIndex = 1;
		    
		    if(CohortData.students == null) {
		    	fileOut.close();
		    	throw new Exception("No students found in CohortData");
		    }
		    
		    for(Student student : CohortData.students) {
		    	row = s.createRow(rowIndex);
		    	row.createCell(0).setCellValue(student.getStudentID());
			    row.createCell(1).setCellValue(student.getLastName().toString());
			    row.createCell(2).setCellValue(student.getFirstName().toString());
			    row.createCell(3).setCellValue(student.getCourseMark());
			    row.createCell(4).setCellValue(student.getGrade().toString());
			    row.createCell(5).setCellValue(student.getDisciplineName().toString());
			    int colIndex = 6;
			    if(student.getSupervisors() != null) {
				    for(Staff sup : student.getSupervisors()){
				    	row.createCell(colIndex).setCellValue(sup.getFullName());
				    	colIndex++;
				    }
			    }
			    rowIndex++;
		    }
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: couldn't create export file.\nPROGRAM REPORT: " + e);
			throw new Exception("There has been an error exporting.");
		} catch (IOException e) {
			System.err.println("ERROR: couldn't write to export file.\nPROGRAM REPORT: " + e);
			throw new Exception("There has been an error exporting.");
		} catch (Exception e) {
			throw new Exception("There are no students to export");
		}
	}

	/**
	 * Write a summary of the students' final grades and key information to .xls
	 * 		NOTE: don't try to use a .xlsx filename
	 * @param filepath to export to
	 */
	public static void unitSummaries(String filepath) throws Exception {
		try {
			FileOutputStream fileOut = new FileOutputStream(filepath);
			
			Workbook wb = new HSSFWorkbook();
			Sheet s = wb.createSheet("Unit Summaries");
			
			int colOffset = 0; //TODO add 4 (horizontal offset)
			int maxDepth = 0;
			Iterator<Unit> us = CohortData.units.iterator();
			
			if(!us.hasNext()) {
				fileOut.close();
				throw new Exception("No units");
			}
			
			while(us.hasNext()) {
				Unit u = us.next();
				Row row;
				if(colOffset == 0) row = s.createRow(0);
				else row = s.getRow(0);
				row.createCell(0+colOffset).setCellValue(u.unitCode.toString());
			    row.createCell(1+colOffset).setCellValue(u.getName().toString());
			    if(colOffset == 0) row = s.createRow(1);
				else row = s.getRow(1);
			    row.createCell(0+colOffset).setCellValue("Average Mark");
			    row.createCell(1+colOffset).setCellValue(u.mark.toString());
			    row.createCell(2+colOffset);
			    row.createCell(3+colOffset);
			    if(colOffset == 0) row = s.createRow(2);
			    int rowNum = 3;
			    List<Assessment> as = u.getAssessments(); //TODO check null case...
			    for (Assessment a : as) {
			    	if (rowNum > maxDepth) row = s.createRow(rowNum++);
			    	else row = s.getRow(rowNum++);
			    	row.createCell(0+colOffset).setCellValue("Assessment");
			    	row.createCell(1+colOffset).setCellValue("Average Mark");
			    	row.createCell(2+colOffset).setCellValue("Unit Proportion");
			    	if (rowNum > maxDepth) row = s.createRow(rowNum++);
			    	else row = s.getRow(rowNum++);
			    	row.createCell(0+colOffset).setCellValue(a.name.toString());
			    	row.createCell(1+colOffset).setCellValue(a.mark.toString() + "%");
			    	row.createCell(2+colOffset).setCellValue(a.unitPercent + "%");
			    	if (rowNum > maxDepth) row = s.createRow(rowNum++);
			    	else row = s.getRow(rowNum++);
			    	row.createCell(0+colOffset).setCellValue("SubAssessment");
			    	row.createCell(1+colOffset).setCellValue("Average Mark");
			    	row.createCell(2+colOffset).setCellValue("Assessment Proportion");
			    	List<SubAssessment> ss = a.getSubAssessments();
			    	for (SubAssessment sub : ss) {
			    		if (rowNum > maxDepth) row = s.createRow(rowNum++);
				    	else row = s.getRow(rowNum++);
				    	row.createCell(0+colOffset).setCellValue(sub.name.toString());
				    	row.createCell(1+colOffset).setCellValue(sub.getAveMark() + "/" + sub.maxMark);
				    	row.createCell(2+colOffset).setCellValue(sub.getAssessmentPercent() + "%");
			    	}
			    	if (rowNum > maxDepth) row = s.createRow(rowNum++);
			    	else row = s.getRow(rowNum++);
			    }
			    maxDepth = rowNum;
			    colOffset += 4;
			}
			for(int i = 0; i < colOffset + 3; i++) {s.autoSizeColumn(i);}
			
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: couldn't create export file.\nPROGRAM REPORT: " + e);
			throw new Exception("There has been an error exporting.");
		} catch (IOException e) {
			System.err.println("ERROR: couldn't write to export file.\nPROGRAM REPORT: " + e);
			throw new Exception("There has been an error exporting.");
		} catch (Exception e) {
			throw new Exception("There are no units to export");
		}
	}
}
