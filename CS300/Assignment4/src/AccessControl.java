//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Access Control Terminal Class
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


import java.util.ArrayList;
import java.util.NoSuchElementException;


/**
 * Access Control object with the necessary methods where admins can change users
 * and their information
 */
public class AccessControl {

  private static ArrayList<User> users;
  private User currentUser;
  private static final String DEFAULT_PASSWORD = "changeme";

  /**
   * A no-argument constructor that creates a default admin user
   */
  public AccessControl() {
    if (users == null) {
      users = new ArrayList<>();
      User user1 = new User("admin", "root", true);
      users.add(user1);
    }
    currentUser = null;
  }

  /**
   * Checks if a username password combination is a valid login
   *
   * @param username the username being checked
   * @param password the password being checked
   * @return true if the username and password are correct and login in a user,
   * false otherwise
   */
  public static boolean isValidLogin(String username, String password) {
    for (User user : users) {
      if (username.equals(user.getUsername()) && user.isValidLogin(password)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Changes the password of the current user
   *
   * @param newPassword that is replacing the current password
   */
  public void changePassword(String newPassword) {
    currentUser.setPassword(newPassword);
  }

  /**
   * Log out the current user
   */
  public void logout() {
    currentUser = null;
  }

  /**
   * It sets the current user to the user from the users list whose username matches the
   * string provided as input to the method (exact match case sensitive).
   * @param username which is going to be set to the current user
   */
  public void setCurrentUser(String username) {
    for (User user : users) {
      if (user.getUsername().equals(username)) {
        currentUser = user;
      }
    }
  }

  /**
   * Create a new user
   * With the default password and isAdmin == false
   * and add it to the users ArrayList
   *
   * @param username being added must be 5 or more characters
   * @return true if the user was sucessfully added, false if not
   * @throws IllegalArgumentException if
   * username is null or if its length is less than 5 ( < 5), or if
   * a user with the same username is already in the list of users
   */
  public boolean addUser(String username) throws IllegalArgumentException {
    if (currentUser != null && currentUser.getIsAdmin()) {
      if (username != null && username.length() >= 5) {
        for (User user : users) {
          if (user.getUsername().equals(username)) {
            throw new IllegalArgumentException("Username already exists");
          }
        }
        User newUser = new User(username, DEFAULT_PASSWORD, false);
        users.add(newUser);
        return true;
      } else {
        throw new IllegalArgumentException("Not a valid username, must be at least 5 characters");
      }
    }
    return false;
  }

  /**
   * Create a new user
   * With the default password
   * and add it to the users ArrayList
   *
   * @param username being added must be 5 or more characters
   * @param isAdmin  the state of the user's admin powers
   * @return true if the user was sucessfully added, false if not
   * @throws IllegalArgumentException if
   * username is null or if its length is less than 5 ( < 5), or if
   * a user with the same username is already in the list of users
   */
  public boolean addUser(String username, boolean isAdmin) {
    if (currentUser != null && currentUser.getIsAdmin()) {
      if (username != null && username.length() >= 5) {
        for (User user : users) {
          if (user.getUsername().equals(username)) {
            throw new IllegalArgumentException("Username already exists");
          }
        }
        User newUser = new User(username, DEFAULT_PASSWORD, isAdmin);
        users.add(newUser);
        return true;
      } else {
        throw new IllegalArgumentException("Not a valid username, must be at least 5 characters");
      }
    }
    return false;
  }

  /**
   * Remove a user given their unique username
   *
   * @param username of the user being removed
   * @return if the current user has Admin powers and
   * the user whose username is passed as input was successfully removed.
   * False if the the current user is null or does not have Admin power
   * @throws NoSuchElementException if no match with username is found in the list of users
   */
  public boolean removeUser(String username) throws NoSuchElementException {
    if (currentUser != null && currentUser.getIsAdmin()) {
      for (User user : users) {
        if (user.getUsername().equals(username)) {
          users.remove(user);
          return true;
        }
      }
      throw new NoSuchElementException("User with given username does not exist");
    }
    return false;
  }

  /**
   * Give a user admin power
   *
   * @param username of the user getting admin powers
   * @return true if this operation terminates successfully.
   * False if the current user is null or does not have admin powers
   * @throws NoSuchElementException if no match with username is found in the list of users
   */
  public boolean giveAdmin(String username) throws NoSuchElementException {
    if (currentUser != null && currentUser.getIsAdmin()) {
      for (User user : users) {
        if (user.getUsername().equals(username)) {
          user.setIsAdmin(true);
          return true;
        }
      }
      throw new NoSuchElementException("User with given username does not exist");
    }
    return false;
  }

  /**
   * Remove the admin power of a user given their username
   *
   * @param username of the user losing admin powers
   * @return true if this operation terminates successfully.
   * False if the current user is null or does not have admin powers
   * @throws NoSuchElementException if no match with username is found in the list of users
   */
  public boolean takeAdmin(String username) throws NoSuchElementException {
    if (currentUser != null && currentUser.getIsAdmin()) {
      for (User user : users) {
        if (user.getUsername().equals(username)) {
          user.setIsAdmin(false);
          return true;
        }
      }
      throw new NoSuchElementException("User with given username does not exist");
    }
    return false;
  }


  /**
   * Reset the password of a user given their username
   *
   * @param username of the user whose password is getting reset
   * @return true if this operation terminates successfully.
   * False if the current user is null or does not have admin powers
   * @throws NoSuchElementException if no match with username is found in the list of users
   */
  public boolean resetPassword(String username) throws NoSuchElementException {
    if (currentUser != null && currentUser.getIsAdmin()) {
      for (User user : users) {
        if (user.getUsername().equals(username)) {
          user.setPassword(DEFAULT_PASSWORD);
          return true;
        }
      }
      throw new NoSuchElementException("User with given username does not exist");
    }
    return false;
  }
}
