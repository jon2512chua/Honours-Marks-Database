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

import cohortSetupSubsystem.Student;

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
	public static StringBuffer importErrors = new StringBuffer();

	/**
	 * Import student records from excel template
	 * @param filename
	 * @param cohort
	 * @return a List of Students or null if error
	 */
	public static List<Student> importFromFile(String filename) {	
		List<Student> students = new LinkedList<Student>();

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
				if(cell.getCellType() == 0) {sID = (int) cell.getNumericCellValue();}
				else {
					String error = "ERROR: no Student ID in row " + index + ". Student not added.\n";
					importErrors.append(error);
					//System.out.print(error);
					continue;
				}

				cell = row.getCell(1, org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK);
				String disc;
				if(cell.getCellType() == 1) {
					disc = cell.getStringCellValue().toLowerCase();
					if(!(disc.equals("a") || disc.equals("n") || disc.equals("p") || disc.equals("b"))) {
						String error = "ERROR: invalid discipline in row " + index + ". Student not added.\n";
						importErrors.append(error);
						//System.out.print(error);
						continue;
					}
				} else {
					String error = "ERROR: invalid discipline in row " + index + ". Student not added.\n";
					importErrors.append(error);
					//System.out.print(error);
					continue;
				}

				@SuppressWarnings("unused")	//TODO: remove
				String title = row.getCell(2,org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK).getStringCellValue();
				String ln = row.getCell(3,org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK).getStringCellValue();
				String fn = row.getCell(4, org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK).getStringCellValue();
				String dissTit = row.getCell(5, org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK).getStringCellValue();

				List<String> supers = new LinkedList<String>();

				int supId = -1;
				int i = 6;
				do {
					cell = row.getCell(i, org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK);
					if(cell.getCellType() == 0) {
						supId = (int) cell.getNumericCellValue();
						if((supId > 99999999 && supId < 199999999) || (supId > 199 && supId < 299)) {
							supers.add(supId + "");
						} else {
							String error = "ERROR: invalid supervisor number in row " + index + ", column " + i + ". Student added, but supervisor not.\n";
							importErrors.append(error);
							//System.out.print(error);
						}
					} else if (cell.getCellType() == 3) {
						supId = -1;
					} else {
						String error = "ERROR: invalid supervisor number in row " + index + ", column " + i + ". Student added, but supervisor not.\n";
						importErrors.append(error);
						//System.out.print(error);
					}
					i++;
				} while (supId != -1);

				students.add(new Student(sID, disc, ln, fn, dissTit, supers)); // make a wrapper method to ensure student successfully added??
			}
			file.close();
			return students;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}	
	}

}
