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
			int i = 0;
			for (String s : backups) {
				backups [i] = s.substring(0, 4) + "/" + s.substring(4, 6) + "(" + s.substring(12, 14) + "/" + s.substring(10, 12) + "/" + s.substring(8, 10) + ", " + s.substring(15, 17) + ":" + s.substring(17, 19) + ":" + s.substring(19, 21) + ")";
				i++;
			}
			return backups;
		}
	}

}
