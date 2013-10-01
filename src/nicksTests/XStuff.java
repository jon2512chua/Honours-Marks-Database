/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicksTests;

import backupSubsystem.BackupUtils;

/**
 *
 * @author nickos
 */
public class XStuff {
    public static void main(String[] args) {
//        String str = "20132.xls";
//        String[] strs = str.split("\\.");
//        System.out.println("Hello!");
//        for (String s : strs) {
//            System.out.println(s);
    	//Session.getCohorts();
    	
    	//String s = "20132 20130923 224723.zip";
    	//System.out.println(s.matches("\\d{5} \\d{8} \\d{6}.zip"));
    	
    	String[] b = BackupUtils.getBackupsList();
    	if(b.length > 0) {
    		for (String a : b) {
        		System.out.println(a);
        	}
    	}
    	else System.out.println("no backups");
    	
    	
        }
}

