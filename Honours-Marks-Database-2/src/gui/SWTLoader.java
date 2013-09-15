package gui;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;


/**
 * @author Tim Lander
 *
 * Class which determines and loads correct SWT library, depending on OS.
 */
public class SWTLoader {
	public static void loadSwtJar() {		
		String swtFileName = null;
	    try {
	        Class<?> currentClass = new Object() { }.getClass().getEnclosingClass();
	        URLClassLoader classLoader = (URLClassLoader) currentClass.getClassLoader();
	        Method addUrlMethod = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
	        addUrlMethod.setAccessible(true);

	        //Determine OS
	        String osName = System.getProperty("os.name").toLowerCase();
	        String swtFileNameOsPart = 
	            osName.contains("win") ? "win32" :
	            osName.contains("mac") ? "macosx" :
	            osName.contains("linux") || osName.contains("nix") ? "linux_gtk" :
	            "error"; // throw new RuntimeException("Unknown OS name: "+osName)
	        if (swtFileNameOsPart == "error") {
	        	System.err.println("Error: Unsuported opperating system detected.");
	        }

	        //Determine OS architecture
	        String osArch = System.getProperty("os.arch").toLowerCase();
	        String swtFileNameArchPart = osArch.contains("64") ? "x64" : "x86";
	        String swtFileNameDirectory = "swt_lib";
	        
	        swtFileName = swtFileNameDirectory+"/swt"+"_"+swtFileNameOsPart+"_"+swtFileNameArchPart+".jar";
	        File swtFile = new File(swtFileName);
        	URL swtFileUrl = swtFile.toURI().toURL();
	        
        	//Check if the SWT library is in the expected directory, and load it if it is.
	        if(swtFile.exists()) {
	        	addUrlMethod.invoke(classLoader, new Object[]{swtFileUrl});
	        } else {
	        	System.err.println("Error: SWT libraries not found. "+swtFileUrl+" not found.");
	        }
	        
	    }
	    catch(Exception e) {
	        System.out.println("Unable to add the swt jar to the class path: "+swtFileName);
	        e.printStackTrace();
	    }
	}
}
