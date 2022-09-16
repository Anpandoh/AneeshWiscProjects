///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           ZylabLoginandSignup
// Course:          CS 200, Sem 1, and 2021
//
// Author:          Aneesh Pandoh
// Email:           Pandoh@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// Source or Recipient; Description
// Examples (REMOVE in your code - unless Jane Doe helped you and you helped John Doe accordingly):
// Jane Doe; helped me with for loop in reverse method
// https://docs.oracle.com/javase/tutorial/java/nutsandbolts/for.html; 
//         counting for loop
// John Doe; I helped with switch statement in main method.
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class LoginAndSignup {
	
	
	/**
	 * readFromFile method will read from the database file and put all usernames, 
	 * encrypted passwords, and keys in the corresponding ArrayLists. If the file cannot open,
     * output error message: <dbName> cannot open!
     *
	 * @param dbName The file name to read all users, passwords, and keys
	 * @param users
	 * @param pwds
	 * @param keys
	 */

	public static void readFromFile(String dbName, ArrayList<String> users, ArrayList<String> pwds, ArrayList<Integer> keys) {
		FileInputStream fileByteStream = null;
		Scanner fileContent = null;
		try {
			fileByteStream = new FileInputStream(dbName);
			fileContent = new Scanner(fileByteStream);


		while (fileContent.hasNextLine()) {
			users.add(fileContent.next());
			pwds.add(fileContent.next());
			keys.add(fileContent.nextInt());
		}

			fileByteStream.close();
		}
		catch(FileNotFoundException openException) {
			System.out.println(dbName + " cannot open!");
		}

		catch(IOException closeNullException) {
			System.out.println(dbName + " cannot close!");
		}



	}

	/**
	 * writeToFile method write the usernames, encrypted passwords and keys back to the file.
	 * The username and its corresponding encrypted password and key are written on the same line.
	 * Use tab to separate each of them. If the file cannot open,
    * output error message: <fileName> cannot open!
    * 
	 * @param fileName
	 * @param users
	 * @param encryptedpwds
	 * @param keys
	 */
	public static void writeToFile(String fileName, ArrayList<String> users, ArrayList<String> encryptedpwds, ArrayList<Integer> keys) {
		File fileStream = new File(fileName);
		PrintWriter outFS = null;
		try {
			outFS = new PrintWriter(fileStream);
		}
		catch(FileNotFoundException openException) {
			System.out.println(fileName + " cannot open!");
		}

		for (int i = 0; i<users.size(); ++i) {
			outFS.println(users.get(i) + "\t" + encryptedpwds.get(i) + "\t" + keys.get(i));
		}

		try {
			outFS.close();
		}
		catch (NullPointerException closeException){
			System.out.println(fileName + " cannot close!");

		}

	}
	
   /**
     * Encrypt the password - update each character in the password by adding key to the 
     * character value For example, if key is 3, and password is "abc", the encrypted password 
     * would be "def".
     * 
     * @param password
     * @param key
     * @return an encrypted password 
     */
	public static String pwdEncryption(String password, int key) {
		String encryptedPswd = "";
		for (int i = 0; i < password.length(); ++i){
			int character = password.charAt(i);
			character += key;
			encryptedPswd += (char)character;
		}
		
		return encryptedPswd;
	}
	
	/**
	 * Decrypt the password - update each character in the password by subtracting key to the 
	 * character value. For example, if key is 1, and encrypted password is "hi", the original 
	 * password would be "gh".
	 * 
	 * @param encryptedPassword
	 * @param key
	 * @return the original password
	 */
	public static String pwdDecryption(String encryptedPassword, int key){
		String decryptedPswd = "";
		for (int i = 0; i < encryptedPassword.length(); ++i){
			int character = encryptedPassword.charAt(i);
			character -= key;
			decryptedPswd += (char)character;
		}
		
		return decryptedPswd;
	}
	
	/**
	 * Check whether the username and password are correct
	 * if the username does not exist, return "Invalid username"
	 * if the username exists but the password is incorrect, return "Invalid password"
	 * if the username exists and password is correct, return "Successful login"
	 * 
	 * @param users ArrayList contains all usernames
	 * @param pwds ArrayList contains all passwords
	 * @param keys ArrayList contains all keys to encrypt and decrypt
	 * @param userName 
	 * @param password
	 * @return a message to indicate the login result
	 */
	public static String login (ArrayList<String> users, ArrayList<String> pwds, ArrayList<Integer> keys, String userName, String password) {
		
		return null;
	}
	
  /**
	 * This method is used for signing up a new user. 
	 * If the newUser already exists, then the signup action fails and returns "Invalid username".
	 * Else signup action succeeds with the username, encrypted password and the key are added to
	 * the corresponding ArrayLists and return "Successful signup".
	 * 
	 * @param rand Random instance to generate a random key. The key should in range of [1, 20]
	 * @param users ArrayList contains all usernames
	 * @param pwds ArrayList contains all passwords
	 * @param keys ArrayList contains all keys to encrypt and decrypt
	 * @param newUser new username
	 * @param newPwd new password
	 * @return a message to indicate the signup result
	 */
	public static String signup(Random rand, ArrayList<String> users, ArrayList<String> pwds, ArrayList<Integer> keys, String newUser, String newPwd) {
		if (!uniqueUser(newUser, users)){
			return "Invalid username";
		}
		else {
			int key = rand.nextInt(20) + 1;
			pwds.add(pwdEncryption(newPwd, key));
			keys.add(key);
			users.add(newUser);
			return "Successful signup";
		}

	}
	
	/**
	 * Determine whether the new username exists.
	 * 
	 * @param newUser
	 * @param users
	 * @return boolean true if the newUser doesn't exist
	 * 				   false if the newUser already exists
	 */
	public static boolean uniqueUser(String newUser, ArrayList<String> users) {
		return !users.contains(newUser);
	}
	
	public static void main(String[] args) {
		//userDB.t
		
	}

}
