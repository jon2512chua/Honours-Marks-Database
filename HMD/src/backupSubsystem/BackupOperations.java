package backupSubsystem;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import sessionControl.Session;

/**
 * This class performs file backup operations for the HMDBS
 * @author Nicholas Abbey 20522805
 * @version 22/09/13
 */
public class BackupOperations {
    /**
     * Method saves a copy of the currently open cohort data to the Archive 
     * directory
     * @param sourceName the filename to be copied (without directory)
     * @param restore true only if called from a restore call
     * @return true if operation successful
     * @todo check folder exists when backing up
     * @todo Heaps of error checking, DISCONNECT FROM DATABASE BEFORE THIS!
     */
    public static boolean backup(String sourceName, boolean restore) {
        String sourcePath = Session.dbDir + sourceName;
        
        DateFormat df = new SimpleDateFormat("yyyyMMdd HHmmss");
        Date date = new java.util.Date();
        String reportDate = df.format(date);
        
        String destZip = Session.backupDir + sourceName + " " + reportDate; 
        if(restore) {destZip += " (depr)";}
        destZip += ".zip";
        
        File sourceDir = new File(sourcePath);
        ZipUtility.zipDirectory(sourceDir, destZip);
        
        return true;
    }
    /**
     * Method restores database from an archive copy
     * @param archiveName the record to be instantiated
     * @return true if operation succeeds
     */
    public static boolean restore(String archiveName) {
        if(!archiveName.startsWith(Session.currentFocus)) {
            return false;
        }
        
        // Step 1: backup current state
        boolean check = backup(Session.currentFocus, true);
        if(!check) {return false;}
        else {
            File directory = new File(Session.dbDir + Session.currentFocus);
            
            // Step 2: delete current state
            if(!directory.exists()){
               return false;
            } 
            else {
                try {
                   DeleteUtility.delete(directory);
                }
                catch(IOException e){
                   return false;
                }
            }
            // Step 3: restore archive           
            ZipUtility.unZipIt(Session.backupDir + archiveName, Session.dbDir + Session.currentFocus);
            return true;
        }
    }
    
}
