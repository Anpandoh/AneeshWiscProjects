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


import java.util.Arrays;

/**
 * A exam schedule where courses can be assigned to certain rooms
 * @author Aneesh Pandoh
 */
public class Schedule {

  private Room[] rooms;
  private Course[] courses;
  private int[] assignments;

  /**
   * Initializes the schedule class given the rooms array and courses array
   * @param rooms
   * @param courses
   */
  public Schedule(Room[] rooms, Course[] courses) {
    this.rooms = rooms;
    this.courses = courses;

    assignments = new int[courses.length];
    Arrays.fill(assignments, -1);
  }

  /**
   * Initializes the schedule class given the rooms array and courses array and assignments array
   * @param rooms array of rooms
   * @param courses array of courses
   * @param assignments array with the assignements of the rooms and courses
   */
  private Schedule(Room[] rooms, Course[] courses, int[] assignments) {
    this.rooms = rooms;
    this.courses = courses;
    this.assignments = assignments;
  }

  /**
   * Getter which gets the number of rooms available in the schedule
   * @return the amount of rooms in the rooms array
   */
  public int getNumRooms() {
    return rooms.length;
  }

  /**
   * Gets a certain room given an index
   * @param roomIndex for the rooms array, to select the room being returned
   * @return room at the index of the array
   */
  public Room getRoom(int roomIndex) {
    if (roomIndex >= rooms.length || roomIndex < 0) {
      throw new IndexOutOfBoundsException("Index is Invalid");
    }
    return rooms[roomIndex];
  }

  /**
   * Getter which gets the number of courses in the schedule
   * @return the amount of courses in the courses array
   */
  public int getNumCourses() {
    return courses.length;
  }

  /**
   * Gets a certain course given an index
   * @param courseIndex for the course array, to select the course being returned
   * @return course at the index of the array
   */
  public Course getCourse(int courseIndex) {
    if (courseIndex >= courses.length || courseIndex < 0) {
      throw new IndexOutOfBoundsException("Index is Invalid");
    }
    return courses[courseIndex];
  }

  /**
   * Checks if a specific course has been assigned a room
   * @param courseIndex the course being check
   * @return true if assigned, false if not
   */
  public boolean isAssigned(int courseIndex) {
    return assignments[courseIndex] != -1;
  }

  /**
   * Gets the assignment of a specific course
   * @param courseIndex the index of the course being looked up
   * @return the room that the course is being assigned
   */
  public Room getAssignment(int courseIndex) {
    if (courseIndex >= courses.length || courseIndex < 0) {
      throw new IndexOutOfBoundsException("Index is Invalid");
    }
    if (isAssigned(courseIndex) == false) {
      throw new IllegalArgumentException("The course at the given index is not assigned a room");
    }
    return getRoom(assignments[courseIndex]);
  }

  /**
   * Checks if all the courses are assigned
   * @return true if all the courses are assigned, false otherwise
   */
  public boolean isComplete() {
    for (int i = 0; i < assignments.length; i++) {
      if (!isAssigned(i)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Assigns the course to a given room
   * @param courseIndex the index of the course being assigned from the course array
   * @param roomIndex the index of the room being assigned from the room array
   * @return a new schedule with assignments of the courses and rooms
   * @throws IllegalArgumentException if the course has already been assigned
   * @throws IndexOutOfBoundsException if either of the inddices are out of the range of
   * their respective arrays
   */
  public Schedule assignCourse(int courseIndex, int roomIndex)
    throws IllegalArgumentException, IndexOutOfBoundsException {

    if (isAssigned(courseIndex)) {
      throw new IllegalArgumentException("Course has already been assigned a room");
    }
    int[] tempAssignments = Arrays.copyOf(assignments, assignments.length);
    tempAssignments[courseIndex] = roomIndex;

    Room[] tempRoom = Arrays.copyOf(rooms, rooms.length);
    tempRoom[roomIndex] =
      getRoom(roomIndex).reduceCapacity(getCourse(courseIndex).getNumStudents());



    Schedule temp = new Schedule(tempRoom, courses, tempAssignments);

    return temp;

  }

  /**
   * Overrides how the array is presented, to show the schedule with the
   * corresponding assignments for rooms and courses
   * @return string representation of schedule
   */
  @Override public String toString() {
    String stringRepresentation = "{";

    for (int i = 0; i < courses.length; ++i) {
      stringRepresentation += courses[i].getName() + ": ";
      if (isAssigned(i)) {
        stringRepresentation += getAssignment(i).getLocation() + ", ";
      } else {
        stringRepresentation += "Unassigned, ";
      }
    }

    stringRepresentation = stringRepresentation.substring(0, stringRepresentation.length() - 2);
    stringRepresentation += "}";

    return stringRepresentation;
  }



}
