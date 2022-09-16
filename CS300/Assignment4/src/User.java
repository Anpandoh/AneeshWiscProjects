//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Create A User
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


/**
 * Creates a user object with various methods relating to username, password and admin powers
 */
public class User {

  private final String USERNAME;
  private String password;
  private Boolean isAdmin;

  /**
   * Constructs a user
   *
   * @param username of user being created
   * @param password of user being created
   * @param isAdmin  is the admin powers of the user being created
   */
  public User(String username, String password, boolean isAdmin) {
    USERNAME = username;
    this.password = password;
    this.isAdmin = isAdmin;
  }

  /**
   * Report whether the password is correct
   *
   * @param password the password being check if it matches the password of the suer
   * @return true if the password is correct
   * false if the password is not equal to the password of the user
   */
  public boolean isValidLogin(String password) {
    return this.password.equals(password);
  }

  /**
   * Return the name of the user
   *
   * @return the USERNAME constant
   */
  public String getUsername() {
    return USERNAME;
  }

  /**
   * Report whether the user is an admin
   *
   * @return true if user is admin, false if user is not admin
   */
  public boolean getIsAdmin() {
    return isAdmin;
  }

  /**
   * Set the new password
   *
   * @param password the password which is going to be set for the user
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Set the new admin status
   *
   * @param isAdmin the state that the user's admin powers is going to be set to
   */
  public void setIsAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }
}
