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
	        String osName = System.getProperty("os.name").toLowerCase();
	        String osArch = System.getProperty("os.arch").toLowerCase();
	        //URLClassLoader classLoader = (URLClassLoader) getClass().getClassLoader();
	        Class<?> currentClass = new Object() { }.getClass().getEnclosingClass();
	        URLClassLoader classLoader = (URLClassLoader) currentClass.getClassLoader();
	        Method addUrlMethod = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
	        addUrlMethod.setAccessible(true);

	        String swtFileNameOsPart = 
	            osName.contains("win") ? "win32" :
	            osName.contains("mac") ? "macosx" :
	            osName.contains("linux") || osName.contains("nix") ? "linux_gtk" :
	            "error"; // throw new RuntimeException("Unknown OS name: "+osName)
	        if (swtFileNameOsPart == "error") {
	        	System.err.println("Error: Unsuported opperating system detected.");
	        }

	        String swtFileNameArchPart = osArch.contains("64") ? "x64" : "x86";
	        String swtFileNameDirectory = "swt_lib";
	        
	        swtFileName = swtFileNameDirectory+"/swt"+"_"+swtFileNameOsPart+"_"+swtFileNameArchPart+".jar";
	        //URL swtFileUrl = new URL("rsrc:"+swtFileName); // I am using Jar-in-Jar class loader which understands this URL; adjust accordingly if you don't
	        //addUrlMethod.invoke(classLoader, swtFileUrl);
	        
	        
	        //swtFileName = "swt_lib/swt.jar";
	        
	        
	        File swtFile = new File(swtFileName);
        	URL swtFileUrl = swtFile.toURI().toURL();
	        
	        if(swtFile.exists()) {
	        	addUrlMethod.invoke(classLoader, new Object[]{swtFileUrl});
	        } else {
	        	System.err.println("Error: SWT libraries not found. "+swtFileUrl+" not found.");
	        }
	        
	        //System.out.println(swtFileUrl);
	        
	        
	        
	    }
	    catch(Exception e) {
	        System.out.println("Unable to add the swt jar to the class path: "+swtFileName);
	        e.printStackTrace();
	    }
	}
}
