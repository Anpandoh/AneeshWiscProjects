//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Access Control Tester Class
// Course:   CS 300 Spring 2022
//
// Author:   Aneesh Pandoh
// Email:    Pandoh@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////


import java.util.NoSuchElementException;

/**
 * Tester class which makes sure the classes AccessControl and User work as intended
 */
public class AccessControlTester {

  /**
   * Main method, which calls all the tester methods and prints the result
   * @param args - Command Line Arguments (none
   */
  public static void main(String[] args) {
    //    System.out.println(testAddUserWithNoAdminPowers());
    //    System.out.println(testAddRemoveUserWithAdminPowers());
    //    System.out.println(testAccessControlIsValidLoginNotValidUser());
    //    System.out.println(testUserConstructorAndMethods());
    System.out.println("runAllTests(): " + runAllTests());
  }

  /**
   * Runs all the tester methods and determines if there is an error or not
   * @return true if no error was found, false if there is a error found by a tester method
   */
  public static boolean runAllTests() {
    if (!(testAccessControlIsValidLoginNotValidUser() && testUserConstructorAndMethods()
      && testAddRemoveUserWithAdminPowers() && testAddUserWithNoAdminPowers())) {
      System.out.println("Error: There was an error found by one of the test methods");
      return false;
    }
    return true;
  }


  /**
   * Tests the user class and all of it's methods including, getUsername(), isValidLogin(),
   * getIsAdmin(), setIsAdmin(), setPassword() and the constructor
   * @return true if all the methods work as expected, return false if there is an incorrect return
   * value in a method
   */
  public static boolean testUserConstructorAndMethods() {

    try {
      //Constructs user, which will be tested
      User user1 = new User("userX", "abcd", true);

      //test getUsername
      String expectedOutput = "userX";
      if (!(user1.getUsername().equals(expectedOutput))) {
        System.out.println("Error: returns inccorect username");
        return false;
      }

      //test isValidLogin
      if (!(user1.isValidLogin("abcd"))) {
        System.out.println("Error: Password does not give a valid login");
        return false;
      }

      //test getIsAdmin
      if (!(user1.getIsAdmin())) {
        System.out.println("Error: getIsAdmin returns the wrong boolean");
        return false;
      }

      //test setIsAdmin
      user1.setIsAdmin(false);
      if (user1.getIsAdmin()) {
        System.out.println("Error: getIsAdmin returns the wrong boolean");
        return false;
      }

      //Test setPassword()
      user1.setPassword("xyz");
      if (!(user1.isValidLogin("xyz"))) {
        System.out.println("Error: Password does not give a valid login");
        return false;
      }
    } catch(Exception exception){
      System.out.println(exception.getMessage());
      return false;
    }

    return true;
  }

  /**
   * Tests the AccessControl class' method for determining whether a username and password
   * combination is valid
   * @return true if it logs in correctly and doesnt log in the wrong credentials,
   * false if it logs incorrectly
   */
  public static boolean testAccessControlIsValidLoginNotValidUser() {
    try {
      AccessControl terminal = new AccessControl();
      if (terminal.isValidLogin("", "")) {
        System.out.println("Error: Able to login with incorrect credentials");
        return false;
      }
      if (terminal.isValidLogin("admin", "root")) {
        return true;
      }
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
      return false;
    }

    return false;
  }

  /**
   * Tests that a user cannot be added without admin powers
   * @return true if adding a user without admin powers does not work, false if adding
   * a user without admin powers works
   */
  public static boolean testAddUserWithNoAdminPowers() {
    try {
      AccessControl terminal1 = new AccessControl();
      if (terminal1.addUser("testUser")
        || terminal1.isValidLogin("testUser", "changeme")) {
        System.out.println("Error");
        return false;
      }
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
      return false;
    }

    return true;
  }

  /**
   * Tests if adding and removing users works as functioned given that the current user
   * has the proper admin powers
   * Tests that the proper exceptions are thrown if necessary
   * @return true if the user is sucessfully added and removed and that the proper exceptions
   * are thrown, false otherwise.
   */
  public static boolean testAddRemoveUserWithAdminPowers() {
    AccessControl terminal2 = new AccessControl();
    terminal2.setCurrentUser("admin");

    //Test adding testUser
    try {
      if (!(terminal2.addUser("testUser")
        || terminal2.isValidLogin("testUser", "changeme"))) {
        System.out.println("Error adding a user with Admin Powers");
        return false;
      }
    } catch (IllegalArgumentException badUserName) {
      System.out.println(badUserName.getMessage());
      return false;
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
      return false;
    }


    //Test adding a username that already exists
    try {
      terminal2.addUser("testUser");
      return false;
    } catch (IllegalArgumentException expectedError) {
      System.out.println("Expected Error: " + expectedError.getMessage());
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
      return false;
    }

    //Test adding a username that is too short
    try {
      terminal2.addUser("abc");
      return false;
    } catch (IllegalArgumentException expectedError) {
      System.out.println("Expected Error: " + expectedError.getMessage());
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
      return false;
    }

    //Test removing the testUser
    try {
      if (!terminal2.removeUser("testUser")
        || terminal2.isValidLogin("testUser", "changeme")) {
        System.out.println("Error removing a user with Admin Powers");
        return false;
      }
    } catch (NoSuchElementException noUserName) {
      System.out.println(noUserName.getMessage());
      return false;
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
      return false;
    }

    //Test removing a user that does not exist
    try {
      terminal2.removeUser("abcdef");
      return false;
    } catch (NoSuchElementException expectedError) {
      System.out.println("Expected Error: " + expectedError.getMessage());
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
      return false;
    }
    return true;
  }
}
