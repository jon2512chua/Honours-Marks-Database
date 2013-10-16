package backupSubsystem;
import java.io.File;
import java.io.IOException;
 
/**
 * @author Nicholas Abbey 20522805
 * @version 24/09/13
 */

public class DeleteUtility
{
	/**
	 * Delete a file
	 * 
	 * Adapted from solution at http://www.mkyong.com/java/how-to-delete-directory-in-java/
	 * 
	 * @param file - to delete
	 * @throws IOException - if error with operation
	 */
    public static void delete(File file) throws IOException {
        if(file.isDirectory()) {
    		
            //directory is empty, then delete it
    		if(file.list().length == 0) {file.delete();}
            else {
 
                //list all the directory contents
        	    String files[] = file.list();
 
           	    for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);
 
        	        //recursive delete
                    delete(fileDelete);
        	    }
 
        	    //check the directory again, if empty then delete it
        	    if(file.list().length==0){file.delete();}
    		}
    	}
        //if file, then delete it
        else {file.delete();}
    }
}