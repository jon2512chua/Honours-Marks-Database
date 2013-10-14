package newCohort;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import orm.BaseStudent;
import orm.Staff;
import orm.Student;

/**
 * This class imports students in bulk from a prefilled template spreadsheet
 * @author Nicholas Abbey 20522805
 * @version 28/9/13
 * @todo needs to have write to DB method!
 */
public class CohortImporter {
	
	/**
	 * holds log for reporting errors encountered when bulk-importing
	 */
	private static StringBuffer importErrors; 

	/**
	 * Import student records from excel template
	 * @param filename
	 * @param cohort
	 * @return a List of BaseStudents or null if error
	 */
	public static StringBuffer importFromFile(String filename) {	
		List<BaseStudent> students = new LinkedList<BaseStudent>();
		// fresh stringbuffer for each attempt
		importErrors = new StringBuffer();
		
		try {

			FileInputStream file = new FileInputStream(new File(filename));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();

			//Dodge the first row of the Template, which has headings in it
			if (rowIterator.hasNext()) rowIterator.next(); 
			else {return null;}

			int index = 1;

			while (rowIterator.hasNext()) {
				HSSFRow row = (HSSFRow) rowIterator.next();
				index++;

				HSSFCell cell = row.getCell(0, org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK);
				int sID;
				if(cell.getCellType() == 0) {
					sID = (int) cell.getNumericCellValue();
					if (Student.getStudentByID(sID+"") != null) {
						String error = "Row: " + index + ": student already exits in database - Student not added.\n";
						importErrors.append(error);
						continue;
					}
				}
				else {
					String error = "Row: " + index + ": no Student ID - Student not added.\n";
					importErrors.append(error);
					continue;
				}

				cell = row.getCell(1, org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK);
				String disc;
				if(cell.getCellType() == 1) {
					disc = cell.getStringCellValue().toLowerCase();
					if(!(disc.equals("a") || disc.equals("n") || disc.equals("p") || disc.equals("b"))) {
						String error = "Row: " + index + ": invalid Discipline in row - Student not added.\n";
						importErrors.append(error);
						continue;
					}
				} else {
					String error = "Row: " + index + ": invalid Discipline in row - Student not added.\n";
					importErrors.append(error);
					continue;
				}

				String title = row.getCell(2,org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK).getStringCellValue();
				String ln = row.getCell(3,org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK).getStringCellValue();
				String fn = row.getCell(4, org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK).getStringCellValue();
				String dissTit = row.getCell(5, org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK).getStringCellValue();

				List<Staff> supers = new LinkedList<Staff>();

				int supID = -1;
				int i = 6;
				do {
					cell = row.getCell(i, org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK);
					if(cell.getCellType() == 0) {
						supID = (int) cell.getNumericCellValue();
						Staff sup = orm.Staff.getStaffByID(supID + ""); 
						if (sup != null) {
							supers.add(sup);
						}
						else {
							String error = "Row " + index + " : invalid supervisor number in column " + i + ". Student added, but supervisor not.\n";
							importErrors.append(error);
						}
					} else if (cell.getCellType() == 3) { //NO MORE SUPERVISORS
						supID = -1;
					} else {
						String error = "Row " + index + " : invalid supervisor number in column " + i + ". Student added, but supervisor not.\n";
						importErrors.append(error);
					}
					i++;
				} while (supID != -1);

				new BaseStudent(sID, fn, ln, title, dissTit, disc, 0, "N/A", supers); // TODO create wrapper
				//BaseStudent(int studentID, String firstName, String lastName, String title, String dissTitle, String disciplineName, double courseMark, String grade, List<Staff> supervisors) {
			}
			file.close();
			return importErrors;
		} catch (FileNotFoundException e) {
			importErrors.append("ERROR: Selected Excel import file could not be found.");
			return importErrors;
		} catch (IOException e) {
			importErrors.append("ERROR: An error occurred while importing from Excel. Please check your data in case of partial effects.");
			return importErrors;
		}	
	}

}
