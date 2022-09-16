//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    (descriptive title of the program making use of this file)
// Course:   CS 300 Spring 2022
//
// Author:   Aneesh Pandoh
// Email:    Pandoh@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////


import java.util.NoSuchElementException;

/**
 * This class implements unit test methods to check the correctness of Application,
 * ApplicationIterator, ApplicationQueue and OpenPosition classes in the assignment.
 *
 */
public class OpenPositionTester {

  /**
   * This method tests and makes use of the Application constructor, getter methods,
   * toString() and compareTo() methods.
   *
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testApplication() {
    // create an Application with valid input

    // create an Application with invalid input:
    // blank name
    // null email
    // no @ email
    // too many @ email
    // invalid score

    // verify getters

    // verify compareTo

    // verify toString

    return false;  // TODO change this
  }

  /**
   * This method tests and makes use of the ApplicationIterator class.
   *
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testApplicationIterator() {
    // create an ApplicationQueue with capacity at least 3
    // and at least 3 Applications with different scores

    // add those Applications to the queue

    // verify that iterating through the queue gives you the applications in order of
    // INCREASING score

    return false;  // TODO change this
  }

  /**
   * This method tests and makes use of the enqueue() and dequeue() methods
   * in the ApplicationQueue class.
   *
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testEnqueueDequeue() {
    // create an ApplicationQueue with capacity 3
    // and at least 4 Applications with different scores

    // enqueue an invalid value (null)

    // enqueue one valid application

    // enqueue two more valid applications

    // enqueue one more application (exceeds capacity)

    // dequeue one application (should be lowest score)

    // dequeue all applications

    // dequeue from an empty queue

    return false;  // TODO change this
  }

  /**
   * This method tests and makes use of the common methods (isEmpty(), size(), peek())
   * in the ApplicationQueue class.
   *
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testCommonMethods() {
    // create an ApplicationQueue with 0 capacity (should fail)

    // create an ApplicationQueue with capacity 3
    // and at least 3 Applications with different scores

    // verify the methods' behaviors on an empty queue

    // add one Application and verify the methods' behaviors

    // add the rest of the Applications and verify the methods' behaviors

    return false;  // TODO change this
  }

  /**
   * This method tests and makes use of OpenPosition class.
   *
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testOpenPosition() {
    // create an OpenPosition with 0 capacity (should fail)

    // create an OpenPosition with capacity 3
    // and at least 5 Applications with different scores

    // verify that the 3 MIDDLE-scoring Applications can be added
    // don't use the highest and lowest scoring applications YET

    // verify that getApplications returns the correct value for your input

    // verify that the result of getTotalScore is the sum of all 3 Application scores

    // verify that the lowest-scoring application is NOT added to the OpenPosition

    // verify that the highest-scoring application IS added to the OpenPosition

    // verify that getApplications has changed correctly

    // verify that the result of getTotalScore has changed correctly

    return false;  // TODO change this
  }

  /**
   * This method calls all the test methods defined and implemented in your OpenPositionTester class.
   *
   * @return true if all the test methods defined in this class pass, and false otherwise.
   */
  public static boolean runAllTests() {
    return testApplication() && testApplicationIterator()
      && testEnqueueDequeue() && testCommonMethods()
      && testOpenPosition();
  }

  /**
   * Driver method defined in this OpenPositionTester class
   *
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    System.out.print(runAllTests());
  }
}
