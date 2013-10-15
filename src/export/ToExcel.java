package export;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import logic.CohortData;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import orm.Staff;
import orm.Student;
/**
 * This is a class for exporting particular statistics from the database into excel format.
 * @author Nicholas Abbey
 * @version 16/10/13
 *
 */
public class ToExcel {
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
			Cell cell = row.createCell(0);
		    cell.setCellValue("Student ID");
		    cell = row.createCell(1);
		    cell.setCellValue("Last Name");
		    cell = row.createCell(2);
		    cell.setCellValue("First Name");
		    cell = row.createCell(3);
		    cell.setCellValue("Discipline");
		    cell = row.createCell(4);
		    cell.setCellValue("Mark");
		    cell = row.createCell(5);
		    cell.setCellValue("Grade");
		    cell = row.createCell(6);
		    cell.setCellValue("Supervisors:");
		    
		    int rowIndex = 1;
		    
		    if(CohortData.students == null) {
		    	fileOut.close();
		    	throw new Exception("No students found in CohortData");
		    }
		    
		    for(Student student : CohortData.students) {
		    	row = s.createRow(rowIndex);
		    	cell = row.createCell(0);
			    cell.setCellValue(student.getStudentID());
			    cell = row.createCell(1);
			    cell.setCellValue(student.getLastName().toString());
			    cell = row.createCell(2);
			    cell.setCellValue(student.getFirstName().toString());
			    cell = row.createCell(3);
			    cell.setCellValue(student.getCourseMark());
			    cell = row.createCell(4);
			    cell.setCellValue(student.getGrade().toString());
			    cell = row.createCell(5);
			    cell.setCellValue(student.getDisciplineName().toString());
			    int colIndex = 6;
			    if(student.getSupervisors() != null) {
				    for(Staff sup : student.getSupervisors()){
				    	cell = row.createCell(colIndex);
				    	cell.setCellValue(sup.getFullName());
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
	
}
