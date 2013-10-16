package backupSubsystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author Nicholas Abbey 20522805
 * @version 24/09/13
 * @todo cleanup, annotation, passing errors 
 */

// This class adapted from solution at 
// http://www.journaldev.com/957/java-zip-example-to-zip-single-file-and-a-directory-recursively

public class ZipUtility {
	// list of all the files in a directory (part of recursive zip)
	private static List<String> filesListInDir = new ArrayList<String>();

	/**
	 * This method zips the directory
	 * @param dir
	 * @param zipDirName
	 */
	public static void zipDirectory(File dir, String zipDirName) {
		try {
			populateFilesList(dir);
			//now zip files one by one
			//create ZipOutputStream to write to the zip file
			FileOutputStream fos = new FileOutputStream(zipDirName);
			ZipOutputStream zos = new ZipOutputStream(fos);
			for(String filePath : filesListInDir){

				//for ZipEntry we need to keep only relative file path, so we used substring on absolute path
				ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length()+1, filePath.length()));
				zos.putNextEntry(ze);
				//read the file and write to ZipOutputStream
				FileInputStream fis = new FileInputStream(filePath);
				byte[] buffer = new byte[1024];
				int len;
				while ((len = fis.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				zos.closeEntry();
				fis.close();
			}
			zos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method populates all the files in a directory to a List
	 * @param dir
	 * @throws IOException
	 */
	private static void populateFilesList(File dir) throws IOException {
		File[] files = dir.listFiles();
		for(File file : files){
			if(file.isFile()) filesListInDir.add(file.getAbsolutePath());
			else populateFilesList(file);
		}
	}

	/**
	 * Unzip a zipped archive
	 * @param zipFile input zip file
	 * @param output zip file output folder
	 * @todo - alter, reference, annotate, error handling
	 */
	public static void unZipIt(String zipFile, String outputFolder) {
		byte[] buffer = new byte[1024];
		try {
			File folder = new File(outputFolder);
			if (!folder.exists()) {
				folder.mkdir();
			}
			ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
			ZipEntry ze = zis.getNextEntry();
			while (ze != null) {
				String fileName = ze.getName();
				File newFile = new File(outputFolder + File.separator + fileName);
				new File(newFile.getParent()).mkdirs();
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				ze = zis.getNextEntry();
			}
			zis.closeEntry();
			zis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}