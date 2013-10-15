package sessionControl;

import sessionControl.BCrypt;

public class PasswordCreator {

	/**
	 * A helper class for Team A to save Heather and admin accounts' initial passwords as BCrypt hashes.
	 * Enter password, run the file, copy the output into the System Database 
	 * NOTE!!! Please set password back to "" before saving/closing.
	 * @author Nicholas Abbey 20522805 
	 */
	public static void main(String[] args) {
		String password = "";
		System.out.println(BCrypt.hashpw(password, BCrypt.gensalt()));
	}

}
