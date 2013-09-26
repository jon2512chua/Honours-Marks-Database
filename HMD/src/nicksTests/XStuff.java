/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicksTests;

/**
 *
 * @author nickos
 */
public class XStuff {
    public static void main(String[] args) {
        String str = "20132.xls";
        String[] strs = str.split("\\.");
        System.out.println("Hello!");
        for (String s : strs) {
            System.out.println(s);
        }
    }
}
