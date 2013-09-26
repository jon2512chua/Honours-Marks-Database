package sessionControl;

import java.io.File;
import java.io.FilenameFilter;
import java.sql.*;

/**
 * This static class holds information about the current session
 * @author Nicholas Abbey 20522805
 * @version 22/09/13
 */
public class Session {
    
    //@todo remove these lines
    
    public static Connection conn = null;
    public static Statement query;
    public static ResultSet users;
    
    /**
     * System database information
     */
    public static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    //public static final String systemDB = "config/System"; @todo fix relative paths
    public static final String systemDb = "/Users/nickos/SkyDrive/UWA2/CITS3200/Honours-Marks-Database/config/System";
    //public static final String dbUser = "Admin";
    //public static final String dbPassword = "teamA2013";    
    
    public static final String dbDir = "/Users/nickos/SkyDrive/UWA2/CITS3200/Honours-Marks-Database/db/";
    //public static final String dbDir = "db/";
    public static final String backupDir = "db/backups/";
    /**
     * Cohort under consideration 
     */
    public static String currentFocus = "";
    public static String cohortDB = dbDir + currentFocus;
    //public static final String dbUser = "Admin";
    //public static final String dbPassword = "teamA2013";
    
    private static boolean loggedIn = false;
    private static String user = "";
    
    /**
     * Sets loggedIn to false.
     * In conjunction with GUI structure
     * @todo finish this method - will need to tie to GUI methods and DB connection 
     */
    public static void logout() {
        loggedIn = false;
        user = "";
        currentFocus = "";
    }
    
    /**
     * Set loggedIn to true if credentials validated
     * @todo finish - will need to tie to GUI methods and DB connection 
     */
    public static boolean login(String username, String password, String cohort) {
        dbConnect(systemDb);
        
        try {
			query = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM System";
	        ResultSet rs = query.executeQuery( SQL );
	        rs.first();
	        
	        String u = rs.getString("username");
	        String p = rs.getString("password");
	        if(username.equals(u) && ((p.equals("initial") && password.equals(p)) || BCrypt.checkpw(password, u))) {
	            loggedIn = true;
	            currentFocus = cohort; 
	            
	            System.out.println("logged in to view " + cohort);
	            //@todo getchohortdata operation
	            dbDisconnect();
	            return true;	            
	            }
	            else 
	            {
	                System.out.println("not logged in...");
	                dbDisconnect();
	                return false;
	            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
    }
    
    /**
     * Change password 
     * @todo the validation of newp should occur in UI
     */
//    public static boolean changePassword(String oldp, String newp) {
//        if(BCrypt.checkpw(oldp, pwHash)) {
//            pwHash = BCrypt.hashpw(newp, BCrypt.gensalt()); 
//            System.out.println("success");
//            return true; 
//        }
//        else {
//            System.out.println("failure");
//            return false;
//        } 
//            
//    }
    
    //@todo implement this method
    public void changeFocus(){}
    /**
     * Connect to the system database
     */
    public static void dbConnect(String db)
   {
    	String connectionURL = "jdbc:derby:" + db + ";";
      //   Beginning of JDBC code sections   
      //   ## LOAD DRIVER SECTION ##
      try	        {
          /*
          **  Load the Derby driver. 
          **     When the embedded Driver is used this action start the Derby engine.
          **  Catch an error and suggest a CLASSPATH problem
           */
          Class.forName(driver); 
          System.out.println(driver + " loaded. ");
      } catch(java.lang.ClassNotFoundException e)     {
          System.err.print("ClassNotFoundException: ");
          System.err.println(e.getMessage());
          System.out.println("\n    >>> Please check your CLASSPATH variable   <<<\n");
      }
      //  Beginning of Primary DB access section
      //   ## BOOT DATABASE SECTION ##
     try {
            // Create (if needed) and connect to the database
            conn = DriverManager.getConnection(connectionURL);	// @todo add username, password	 
            System.out.println("Connected to database " + systemDb);

         //  Beginning of the primary catch block: uses errorPrint method
         }  catch (Throwable e)  {   
            /*       Catch all exceptions and pass them to 
            **       the exception reporting method             */
            System.out.println(" . . . exception thrown:");
            
         }
    }

    public static void dbDisconnect() {
    try {
		query.close();
		conn.close();
		System.out.println("Getting Started With Derby JDBC program ending.");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 					

    //   ## DATABASE SHUTDOWN SECTION ## 
    /*** In embedded mode, an application should shut down Derby.
       Shutdown throws the XJ015 exception to confirm success. ***/			
    if (driver.equals("org.apache.derby.jdbc.EmbeddedDriver")) {
       boolean gotSQLExc = false;
       try {
          DriverManager.getConnection("jdbc:derby:;shutdown=true");
       } catch (SQLException se)  {	
          if ( se.getSQLState().equals("XJ015") ) {		
             gotSQLExc = true;
          }
       }
       if (!gotSQLExc) {
       	  System.out.println("Database did not shut down normally");
       }  else  {
          System.out.println("Database shut down normally");	
       }  
    }
    }
/**
 * Helper method for populating the list of databases on the login screen
 * @return a list of the available databases
 * @todo http://stackoverflow.com/questions/5125242/list-only-subdirectory-from-directory-not-files, error handling
 */
	public static String[] getCohorts() {
		File file = new File(dbDir);
		String[] directories = file.list(new FilenameFilter() {
		  @Override
		  public boolean accept(File dir, String name) {
		    return (new File(dir, name).isDirectory() && name.matches("\\d{5}"));
		  }
		});
		if(directories.length == 0) return (new String[]{"-1"});
		else return directories;
	}
}

    