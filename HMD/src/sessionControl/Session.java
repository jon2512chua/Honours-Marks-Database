package sessionControl;

import java.sql.*;

/**
 * This static class holds information about the current session
 * @author Nicholas Abbey 20522805
 * @version 22/09/13
 */
public class Session {
    
    //@todo remove these lines
    private static String un = "";       
    private static String pwHash = "";
    
    /**
     * System database information
     */
    public static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    public static final String systemDB = "DatabaseFiles/System";
    //public static final String dbUser = "Admin";
    //public static final String dbPassword = "teamA2013";
    
    /**
     * Cohort under consideration 
     */
    public static String currentFocus = "";
    public static String cohortDB = "DatabaseFiles/" + currentFocus;
    //public static final String dbUser = "Admin";
    //public static final String dbPassword = "teamA2013";
    
    
    // Relative paths for windows instance
    private static final String winDbDir = "DatabaseFiles\\";
    private static final String winArchDir = "DatabaseFiles\\Archive\\"; 
    // Relative paths for mac instance
    private static final String macDbDir = "DatabaseFiles/";
    private static final String macArchDir = "DatabaseFiles/Archive/";
    // Get the current OS
    private final static String OS = System.getProperty("os.name").toLowerCase();
    /**
     * Path for database files
     */
    public static String dbDir = "";
    /**
     * Path for archive files
     */
    public static String archDir = "";
    // Sets paths based on OS
    static {
        if (OS.indexOf("win") >= 0) {
			dbDir = winDbDir;
            archDir = winArchDir;
		} else if (OS.indexOf("mac") >= 0) {
            dbDir = macDbDir;
            archDir = macArchDir;
        }
    }
    
    // Database file extension
    public static final String extension = ".xls";
    
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
        
        if(username.equals(un) && ((pwHash.equals("initial") && password.equals(pwHash)) || BCrypt.checkpw(password, pwHash))) {
            loggedIn = true;
            currentFocus = cohort; 
            pwHash = "";
            user = un;
            System.out.println("logged in");
            return true; 
        }
        else 
        {
            un = "";
            pwHash = "";
            System.out.println("not logged in...");
            return false;
        }
    }
    
    /**
     * Change password 
     * @todo the validation of newp should occur in UI
     */
    public static boolean changePassword(String oldp, String newp) {
        if(BCrypt.checkpw(oldp, pwHash)) {
            pwHash = BCrypt.hashpw(newp, BCrypt.gensalt()); 
            System.out.println("success");
            return true; 
        }
        else {
            System.out.println("failure");
            return false;
        } 
            
    }
    
    //@todo implement this method
    public void changeFocus(){}
    
    public static void dbConnect(String username, String pw)
   {
      
   // define the Derby connection URL to use 
      String connectionURL = "jdbc:derby:" + systemDB + ";";

      Connection conn = null;
      Statement s;
      ResultSet users;
      
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
            System.out.println("Connected to database " + systemDB);
            
            //   ## INITIAL SQL SECTION ## 
            //   Create a statement to issue simple commands.  
            s = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String SQL = "SELECT * FROM System";
            ResultSet rs = s.executeQuery( SQL );
            rs.first();
            
            String u = rs.getString("username");
            String p = rs.getString("password");
            
            un = u;
            pwHash = p;
            login(username, pw, "20132");
            
            s.close();
            conn.close();						
            System.out.println("Closed connection");

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
            
         //  Beginning of the primary catch block: uses errorPrint method
         }  catch (Throwable e)  {   
            /*       Catch all exceptions and pass them to 
            **       the exception reporting method             */
            System.out.println(" . . . exception thrown:");
            
         }
         System.out.println("Getting Started With Derby JDBC program ending.");
    }
    
}
    

    