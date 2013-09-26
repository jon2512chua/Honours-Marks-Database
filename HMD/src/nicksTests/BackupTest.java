package nicksTests;

import backupSubsystem.BackupOperations;
import sessionControl.Session;

/**
 *
 * @author nickos
 * 
 */
public class BackupTest {
    public static void main(String[] args) {
        Session.currentFocus = "System";
        //boolean check = BackupOperations.backup(Session.currentFocus, false);
        
        boolean check = BackupOperations.restore("System 20130924 125651.zip");
        System.out.println(check);
    }
    
}
