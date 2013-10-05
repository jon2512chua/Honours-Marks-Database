package nicksTests;

import cohortSetupSubsystem.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import newCohort.CohortImporter;

public class ImportTest {

	public final static String EXCEL_PATH = "excelfile.xls";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        CohortImporter.importErrors = new StringBuffer();
		try {
            String host = "jdbc:derby://localhost:1527/HMDBS";
            String username = "nickos";
            String password = "123jazz";
            Connection con = DriverManager.getConnection( host, username, password );
            
            List<Student> cohort = CohortImporter.importFromFile(EXCEL_PATH);

            Iterator<Student> cohortIterator = cohort.iterator();

            while (cohortIterator.hasNext()) {
                Student aStudent = (Student) cohortIterator.next();
                aStudent.toStdOut();
                
                
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                String SQL = "SELECT * FROM Nickos.Students";
                ResultSet rs = stmt.executeQuery( SQL );
                
                rs.moveToInsertRow( );
                rs.updateInt("studentId", aStudent.studentId);
                rs.updateString("discipline", aStudent.discipline); 
                rs.updateString("lastName", aStudent.lastName);
                rs.updateString("firstName", aStudent.firstName);
                rs.updateString("dissTitle", aStudent.dissTitle);
                rs.insertRow( );
                stmt.close( );
                rs.close( );
            }   
            
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String SQL = "SELECT * FROM Nickos.Students";
            ResultSet rs = stmt.executeQuery( SQL );
            rs.first();
            String firstname = rs.getString("firstName");
            System.out.println(firstname);
        }

        catch ( SQLException err ) {
            System.out.println( err.getMessage( ) );
        }
        System.out.print(CohortImporter.importErrors);
	}

}
