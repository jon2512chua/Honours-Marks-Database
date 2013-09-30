package backupSubsystem;

import java.io.File;
import java.io.FilenameFilter;

import sessionControl.Directories;

/**
 * A class for minor utilities used in the backup subsystem
 * 
 * @author Nicholas Abbey 20522805
 * @version 28/9/13
 * 
 */
public class BackupUtils {

	/**
	 * A method to populate a list of available backups.
	 * 
	 * @return the list of backups
	 */
	public static String[] getBackupsList() {
		{
			File file = new File(Directories.backupDir);
			String[] backups = file.list(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return (new File(dir, name).exists() && name
							.matches("\\d{5} \\d{8} \\d{6}.zip"));
				}
			});
			return backups;
		}
	}

}
