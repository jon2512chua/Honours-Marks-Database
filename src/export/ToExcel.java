package export;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import logic.CohortData;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
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
 */
public class ToExcel {
	
	//TODO set some cell styles for export http://poi.apache.org/spreadsheet/quick-guide.html
	//TODO add std devs, etc
	
	public static void markerSummaries(String filepath) throws Exception {
		//try {
			FileOutputStream fileOut = new FileOutputStream(filepath);
			
			Workbook wb = new HSSFWorkbook();
			Sheet s = wb.createSheet("Marker Summaries");
			
			int colOffset = 0; 
			int maxDepth = 0;
			Iterator<Staff> markers = CohortData.staff.iterator();
			
			if(!markers.hasNext()) {
				fileOut.close();
				throw new Exception("No markers");
			}
			
			while(markers.hasNext()) {
				Staff m = markers.next();
				Row row;
				if(colOffset == 0) row = s.createRow(0);
				else row = s.getRow(0);
				row.createCell(0+colOffset).setCellValue(m.getStaffID());
			    row.createCell(1+colOffset).setCellValue(m.getLastName().toString());
			    row.createCell(2+colOffset).setCellValue(m.getFirstName().toString());
			    if(colOffset == 0) row = s.createRow(1);
				else row = s.getRow(2);
			    
			    //HashMap<Unit, List<Assessment>>
			    
			    row.createCell(0+colOffset).setCellValue("Unit:");
			    //row.createCell(1+colOffset).setCellValue(u.mark.toString());
			    row.createCell(2+colOffset);
			    row.createCell(3+colOffset);
			    //if(colOffset == 0) row = s.createRow(2);
			}
//			    int rowNum = 3;
//			    //List<Assessment> as = u.getAssessments(); 
//			    if(as != null) {
//				    for (Assessment a : as) {
//				    	if (rowNum > maxDepth) row = s.createRow(rowNum++);
//				    	else row = s.getRow(rowNum++);
//				    	row.createCell(0+colOffset).setCellValue("Assessment");
//				    	row.createCell(1+colOffset).setCellValue("Average Mark");
//				    	row.createCell(2+colOffset).setCellValue("Unit Proportion");
//				    	if (rowNum > maxDepth) row = s.createRow(rowNum++);
//				    	else row = s.getRow(rowNum++);
//				    	row.createCell(0+colOffset).setCellValue(a.name.toString());
//				    	row.createCell(1+colOffset).setCellValue(a.mark.toString() + "%");
//				    	row.createCell(2+colOffset).setCellValue(a.unitPercent + "%");
//				    	if (rowNum > maxDepth) row = s.createRow(rowNum++);
//				    	else row = s.getRow(rowNum++);
//				    	row.createCell(0+colOffset).setCellValue("SubAssessment");
//				    	row.createCell(1+colOffset).setCellValue("Average Mark");
//				    	row.createCell(2+colOffset).setCellValue("Assessment Proportion");
//				    	List<SubAssessment> ss = a.getSubAssessments();
//				    	if(ss != null) {
//					    	for (SubAssessment sub : ss) {
//					    		if (rowNum > maxDepth) row = s.createRow(rowNum++);
//						    	else row = s.getRow(rowNum++);
//						    	row.createCell(0+colOffset).setCellValue(sub.name.toString());
//						    	row.createCell(1+colOffset).setCellValue(sub.getAveMark() + " (/" + sub.maxMark + ")");
//						    	row.createCell(2+colOffset).setCellValue(sub.getAssessmentPercent() + "%");
//					    	}
//				    	}
//				    	if (rowNum > maxDepth) row = s.createRow(rowNum++);
//				    	else row = s.getRow(rowNum++);
//				    }
//			    }
//			    maxDepth = rowNum;
//			    colOffset += 4;
//			}
//			for(int i = 0; i < colOffset + 3; i++) {s.autoSizeColumn(i);}
//			
//			wb.write(fileOut);
//			fileOut.close();
//		} catch (FileNotFoundException e) {
//			System.err.println("ERROR: couldn't create export file.\nPROGRAM REPORT: " + e);
//			throw new Exception("There has been an error exporting.");
//		} catch (IOException e) {
//			System.err.println("ERROR: couldn't write to export file.\nPROGRAM REPORT: " + e);
//			throw new Exception("There has been an error exporting.");
//		} catch (Exception e) {
//			throw new Exception("There are no units to export");
//		}
	}
	
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
			
			//Heading formating
			CellStyle heading = wb.createCellStyle();
			heading.setBorderBottom(CellStyle.BORDER_THIN);
			heading.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			Font font = wb.createFont();
		    font.setFontHeightInPoints((short)10);
		    font.setFontName("Arial");
		    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		    heading.setFont(font);
			
			Cell cell = row.createCell(0);
			cell.setCellValue("Student ID");
			cell.setCellStyle(heading);
		    cell = row.createCell(1);
		    cell.setCellValue("Last Name");
		    cell.setCellStyle(heading);
		    cell = row.createCell(2);
		    cell.setCellValue("First Name");
		    cell.setCellStyle(heading);
		    cell = row.createCell(3);
		    cell.setCellValue("Discipline");
		    cell.setCellStyle(heading);
		    cell = row.createCell(4);
		    cell.setCellValue("Mark");
		    cell.setCellStyle(heading);
		    cell = row.createCell(5);
		    cell.setCellValue("Grade");
		    cell.setCellStyle(heading);
		    cell = row.createCell(6);
		    cell.setCellValue("Supervisors:");
		    cell.setCellStyle(heading);
		    
		    int rowIndex = 1;
		    
		    if(CohortData.students == null) {
		    	fileOut.close();
		    	throw new Exception("No students found in CohortData");
		    }
		    int maxCol = 0;
		    for(Student student : CohortData.students) {
		    	row = s.createRow(rowIndex);
		    	row.createCell(0).setCellValue(student.getStudentID());
			    row.createCell(1).setCellValue(student.getLastName().toString());
			    row.createCell(2).setCellValue(student.getFirstName().toString());
			    row.createCell(3).setCellValue(student.getDisciplineName().toString());
			    row.createCell(4).setCellValue(student.getCourseMark());
			    row.createCell(5).setCellValue(student.getGrade().toString());
			    int colIndex = 6;
			    if(student.getSupervisors() != null) {
				    for(Staff sup : student.getSupervisors()){
				    	row.createCell(colIndex).setCellValue(sup.getFullName());
				    	colIndex++;
				    	if (colIndex > maxCol) maxCol = colIndex;
				    }
			    }
			    rowIndex++;
		    }
		    
		    for(int i = 0; i < maxCol; i++) {s.autoSizeColumn(i);}
			
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
	 * Write a summary of units to .xls
	 * 		NOTE: don't try to use a .xlsx filename
	 * @param filepath to export to
	 */
	public static void unitSummaries(String filepath) throws Exception {
		try {
			FileOutputStream fileOut = new FileOutputStream(filepath);
			
			Workbook wb = new HSSFWorkbook();
			Sheet s = wb.createSheet("Unit Summaries");
			
			int colOffset = 0; 
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
			    List<Assessment> as = u.getAssessments(); 
			    if(as != null) {
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
				    	if(ss != null) {
					    	for (SubAssessment sub : ss) {
					    		if (rowNum > maxDepth) row = s.createRow(rowNum++);
						    	else row = s.getRow(rowNum++);
						    	row.createCell(0+colOffset).setCellValue(sub.name.toString());
						    	row.createCell(1+colOffset).setCellValue(sub.getAveMark() + " (/" + sub.maxMark + ")");
						    	row.createCell(2+colOffset).setCellValue(sub.getAssessmentPercent() + "%");
					    	}
				    	}
				    	if (rowNum > maxDepth) row = s.createRow(rowNum++);
				    	else row = s.getRow(rowNum++);
				    }
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
