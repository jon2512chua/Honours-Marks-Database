package backupSubsystem;
import java.io.File;
import java.io.IOException;
 
/**
 * http://www.mkyong.com/java/how-to-delete-directory-in-java/
 * @author nickos
 * @todo exceptions, cleanup, doc
 */

public class DeleteUtility
{
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