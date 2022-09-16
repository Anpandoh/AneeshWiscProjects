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
 * Room which exams are going to be schedule for certain courses
 * @author Aneesh Pandoh
 */
public class Room {
  private String location;
  private int capacity;

  /**
   * Initializes the room with a certain location and capacity of students
   * @param location of room (the name of the room)
   * @param capacity the amount of students the room can fit
   * @throws IllegalArgumentException if the capacity is less than 0
   */
  public Room(String location, int capacity) throws IllegalArgumentException {
    if (capacity < 0) {
      throw new IllegalArgumentException("The capacity cannot be less than 0");
    }
    this.location = location;
    this.capacity = capacity;
  }

  /**
   * Getter for the location of the room
   * @return location of the room
   */
  public String getLocation() {
    return location;
  }

  /**
   * Getter for the capacity of the room
   * @return capacity of the room
   */
  public int getCapacity() {
    return capacity;
  }

  /**
   * Reduces the amount of students a room can hold by a given number
   * @param capacityRemoved the amount the capacity is being reduced by
   * @return the capacity after the size reduction
   * @throws IllegalArgumentException if the capacity is less than the capacity asked to be removed
   */
  public Room reduceCapacity(int capacityRemoved) throws IllegalArgumentException {
    if (capacityRemoved > capacity) {
      throw new IllegalArgumentException(
        "Cannot not lower capacity more than current capacity. Capacity:" + capacity
          + " Capacity removed: " + capacityRemoved);
    }
    return new Room(location, capacity - capacityRemoved);
  }

}
