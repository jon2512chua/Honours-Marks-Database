package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Settings {
	private static final String settingsFileName = "settings";
	private static Properties props = new Properties();
	
	/**
	 * Save settings
	 */
	public static Boolean saveSettings(String[] keys, String[] values) {
		try {
			int value = 0;
			for (String key : keys) {
				props.setProperty(key, values[value++]);
			}

			OutputStream out = new FileOutputStream(new File(settingsFileName));
			props.store(out, "HMD Settings File");
			out.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	/**
	 * Restore settings
	 */
	public static String loadSettings(String key, String defaultValue) {
		//Properties props = new Properties();
		InputStream is = null;

		// Try loading from the current directory
		try {
			is = new FileInputStream( new File(settingsFileName) );
			// Try loading properties from the file (if found)
			props.load(is);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new String(props.getProperty(key, defaultValue));
	}

}
