package nicksTests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class database {

	/**
	 * Nick's class for testing some database operations...
	 */
	public static void main(String[] args) {
		loadDriver();
		dbConnect("a", "b");
		dbConnect("Heather", "initial");
		shutdownDriver();

	}
	public static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	
	public static void loadDriver() {
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
	}
	
	public static void dbConnect(String un, String pw)
	   {
	      
	   // define the Derby connection URL to use 
	      String connectionURL = "jdbc:derby:config/System;";

	      Connection conn = null;
	      Statement s;	      
	      
	      //   Beginning of JDBC code sections   
	      //   ## LOAD DRIVER SECTION ##
	      
	      //  Beginning of Primary DB access section
	      //   ## BOOT DATABASE SECTION ##
	     try {
	            // Create (if needed) and connect to the database
	            conn = DriverManager.getConnection(connectionURL);	// @todo add username, password	 
	            System.out.println("Connected to database ");
	            
	            //   ## INITIAL SQL SECTION ## 
	            //   Create a statement to issue simple commands.  
	            s = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            String SQL = "SELECT * FROM System";
	            ResultSet rs = s.executeQuery( SQL );
	            rs.first();
	            
	            String u = rs.getString("username");
	            String p = rs.getString("password");
	            
	            if(u.equals(un) && p.equals(pw)) {
	            	System.out.println("LOGGED IN");
	            }
	            else System.out.println("NOT LOGGED IN");
	            s.close();
	            conn.close();						
	            System.out.println("Closed connection");
	            
	         //  Beginning of the primary catch block: uses errorPrint method
	         }  catch (Throwable e)  {   
	            /*       Catch all exceptions and pass them to 
	            **       the exception reporting method             */
	            System.out.println(" . . . exception thrown:");
	            
	         }
	         
	    }
	
	public static void shutdownDriver () {
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
	    
	}

	
