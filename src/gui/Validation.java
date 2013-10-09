package gui;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Text;

/**
 * Class dealing with validation of text input.
 * @author Tim Lander
 *
 */
public class Validation {
	/**
	 * Attaches a VerifyListener to the input.<br/>
	 * Ensures only integers can be entered.
	 * @param text the text to validate
	 */
	public static void validateInt(Text text) {
		text.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {		//Check if the value entered is an integer
				if (e.character != '\u0008' && e.character != '\u007F' && e.character != '\u0000') {	//Allows backspace/delete
					try {
						Integer.parseInt(e.text);
					} catch (final NumberFormatException numberFormatException) {
						e.doit = false;
					}
				}
			}
		});
		
	}
	
	/**
	 * Attaches a VerifyListener to the input.<br/>
	 * Ensures only doubles can be entered.
	 * @param text the text to validate
	 */
	public static void validateDouble(Text text) { //TODO: test
		text.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {		//Check if the value entered is a double
				if (e.character != '\u0008' && e.character != '\u007F' && e.character != '\u0000') {	//Allows backspace/delete/.
					try {
						Integer.parseInt(e.text);
					} catch (final NumberFormatException numberFormatException) {
						e.doit = false;
					}
				//} else if (e.character == '\u002E' && text.toString().contains("\u002E")) {
				} else if (e.character == '\u002E' && e.getSource().toString().contains("\u002E")) {
					e.doit = false;
				}
			}
		});
		
	}

}
