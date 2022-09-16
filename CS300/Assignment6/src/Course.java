//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Scheduling Exams
// Course:   CS 300 Spring 2022
//
// Author:   Aneesh Pandoh
// Email:    Pandoh@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Persons: NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////


/**
 * Course which is going to be assigned to a room.
 * @author Aneesh Pandoh
 */
public class Course {

  private String name;
  private int numStudents;

  /**
   * Initializes a course
   * @param name of the couse
   * @param numStudents is the amount of students in the course
   * @throws IllegalArgumentException if the amount of students provided is negative
   */
  public Course(String name, int numStudents) throws IllegalArgumentException {
    if (numStudents < 0) {
      throw new IllegalArgumentException("The amount of students cannot be negative");
    }
    this.name = name;
    this.numStudents = numStudents;
  }

  /**
   * Getter for the name of the course
   * @return the name of the course
   */
  public String getName() {
    return name;
  }

  /**
   * Getter for the amount of students in the course
   * @return the amount of students in the course
   */
  public int getNumStudents() {
    return numStudents;
  }

}
