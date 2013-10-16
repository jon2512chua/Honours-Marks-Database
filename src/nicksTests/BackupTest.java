package nicksTests;

import backupSubsystem.BackupOperations;
import sessionControl.Session;

/**
 * A class for testing the system's backup and restore functions work.
 * <This testing class is dynamically edited/used - it may have been used differently in the past>
 * 
 * @author Nicholas Abbey 20522805
 */
public class BackupTest {
    public static void main(String[] args) {
        Session.currentFocus = "System";
        //boolean check = BackupOperations.backup(Session.currentFocus, false);
        
        boolean check = BackupOperations.restore("System 20130924 125651.zip");
        System.out.println(check);
    }
    
}
