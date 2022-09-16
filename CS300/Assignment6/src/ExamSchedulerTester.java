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
 * Tests the various new methods in the ShoppingCart class and checks for errors
 *
 * @author Aneesh Pandoh
 */
public class ExamSchedulerTester {

  /**
   * Main method
   *
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("runAllTests(): " + runAllTests());


    
  }

  /**
   * Checks if all the test cases pass
   *
   * @return true when this test verifies all tests are passed,
   * and false otherwise
   */
  public static boolean runAllTests() {
    if (testCourse() && testRoom() && testScheduleAccessors() && testAssignCourse()
      && testFindSchedule() && testFindAllSchedules()) {
      return true;
    } else {
      System.out.println(
        "Problem detected: Your runAllTests() method " + "found an error in ExamScheduler Program");
      return false;
    }
  }

  /**
   * Checks if the Course object is properly created and implemented
   * @return true when this test verifies a correct functionality,
   * and false otherwise
   */
  public static boolean testCourse() {
    //Tests if test object gets created properly and methods work
    try {
      Course test = new Course("testName", 5);
      if (test.getNumStudents() != 5 || !test.getName().equals("testName")) {
        return false;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }

    //Testing if proper exception is thrown for invalid input
    try {
      Course test = new Course("testName", -1);
      return false;
    } catch (IllegalArgumentException testName) {
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
    return true;
  }

  /**
   * Checks if the Room object is properly created and implemented
   * @return true when this test verifies a correct functionality,
   * and false otherwise
   */
  public static boolean testRoom() {
    //Tests if test object gets created properly and methods work
    try {
      Room test = new Room("testLocation", 5);
      if (test.getCapacity() != 5 || !test.getLocation().equals("testLocation")) {
        System.out.println("1");
        return false;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }

    //Tests the reduceCapacity method
    try {
      Room test = new Room("testLocation", 5);
      Room newTest = test.reduceCapacity(2);
      if (newTest.getCapacity() != 3) {
        return false;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }

    //Tests the exception for the reduceCapacity method
    try {
      Room test = new Room("testLocation", 5);
      test.reduceCapacity(7);
      return false;
    } catch (IllegalArgumentException e) {
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }

    //Testing if proper exception is thrown for invalid input
    try {
      Room test = new Room("testName", -1);
      return false;
    } catch (IllegalArgumentException testName) {
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }

    return true;
  }



  /**
   * Checks if the Schedule object and its accessors is properly created and implemented
   * @return true when this test verifies a correct functionality,
   * and false otherwise
   */
  public static boolean testScheduleAccessors() {

    Course course1 = new Course("A", 15);
    Course course2 = new Course("B", 70);
    Course course3 = new Course("C", 70);

    Room room1 = new Room("X", 75);
    Room room2 = new Room("Y", 100);
    Room room3 = new Room("Z", 15);

    Course[] Courses = {course1, course2, course3};
    Room[] Rooms = {room1, room2, room3};


    //Tests all the accessors for schedule
    try {
      Schedule test = new Schedule(Rooms, Courses);
      if (test.getNumRooms() != 3) {
        return false;
      }
      if (!(test.getRoom(0).equals(room1))) {
        return false;
      }
      if (test.getNumCourses() != 3) {
        return false;
      }
      if (!(test.getCourse(0).equals(course1))) {
        return false;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }

    //Tests if the schedule.getCourse produce the correct error
    try {
      Schedule test = new Schedule(Rooms, Courses);
      test.getCourse(5);
      return false;
    } catch (IndexOutOfBoundsException e) {
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }

    //Tests if the schedule.getRoom produces the correct error
    try {
      Schedule test = new Schedule(Rooms, Courses);
      test.getRoom(5);
      return false;
    } catch (IndexOutOfBoundsException e) {
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }

    //Tests if the schedule.getAssignment produces the correct error
    try {
      Schedule test = new Schedule(Rooms, Courses);
      test.getAssignment(-1);
      return false;
    } catch (IndexOutOfBoundsException e) {
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }

    try {
      Schedule test = new Schedule(Rooms, Courses);
      test.getAssignment(0);
      return false;
    } catch (IllegalArgumentException e) {
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }


    return true;
  }

  /**
   * Checks if the Schedule.assignCourse and other relevant methods are properly created
   * and implemented
   * @return true when this test verifies a correct functionality,
   * and false otherwise
   */
  public static boolean testAssignCourse() {

    Course course1 = new Course("A", 15);
    Course course2 = new Course("B", 70);
    Course course3 = new Course("C", 70);

    Room room1 = new Room("X", 75);
    Room room2 = new Room("Y", 100);
    Room room3 = new Room("Z", 15);

    Course[] Courses = {course1, course2, course3};
    Room[] Rooms = {room1, room2, room3};
    Schedule test = new Schedule(Rooms, Courses);

    //Tests assigning a course
    try {
      Schedule newTest = test.assignCourse(0, 0);
      if (!(newTest.isAssigned(0))) {
        return false;
      }
      if (!(newTest.getAssignment(0).getLocation().equals(room1.getLocation()))) {
        return false;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }

    //Tests assigning a course that has already been assigned
    try {
      Schedule newTest = test.assignCourse(0, 0);
      Schedule newTest1 = newTest.assignCourse(0, 0);
      return false;
    } catch (IllegalArgumentException e) {
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }

    //Tests schedule.isComplete
    try {
      Schedule newTest = test.assignCourse(0, 2);
      Schedule newTest1 = newTest.assignCourse(1, 0);
      Schedule newTest2 = newTest1.assignCourse(2, 1);
      if (!newTest2.isComplete()) {
        return false;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }

    return true;
  }

  /**
   * Checks if the ExamScheduler is properly created and implemented and can find the
   * proper schedule
   * @return true when this test verifies a correct functionality,
   * and false otherwise
   */
  public static boolean testFindSchedule() {
    //Test if it finds correct schedule
    try {
      Course course1 = new Course("A", 15);
      Course course2 = new Course("B", 70);
      Course course3 = new Course("C", 100);
      Room room1 = new Room("X", 50);
      Room room2 = new Room("Y", 100);
      Room room3 = new Room("Z", 70);
      Course[] Courses = {course1, course2, course3};
      Room[] Rooms = {room1, room2, room3};

      if (!(ExamScheduler.findSchedule(Rooms, Courses).toString().equals("{A: X, B: Z, C: Y}"))) {
        return false;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }

    //Tests if proper error is thrown when no valid schedules exist
    try {
      Course course1 = new Course("A", 20 );
      Course course2 = new Course("B", 110);
      Course course3 = new Course("C", 80);
      Room room1 = new Room("X", 100);
      Room room2 = new Room("Y", 50);
      Room room3 = new Room("Z", 25);
      Course[] Courses = {course1, course2, course3};
      Room[] Rooms = {room1, room2, room3};


      ExamScheduler.findSchedule(Rooms, Courses);
      return false;
    } catch (IllegalStateException e) {
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
    return true;
  }

  /**
   * Checks if the findAllSchedules method is properly created and implemented and can list
   * all the proper schedules into an array
   * @return true when this test verifies a correct functionality,
   * and false otherwise
   */
  public static boolean testFindAllSchedules() {

    //Tests to find all schedules
    try {
      Course course1 = new Course("A", 15);
      Course course2 = new Course("B", 70);
      Course course3 = new Course("C", 50);
      Room room1 = new Room("X", 100);
      Room room2 = new Room("Y", 25);
      Room room3 = new Room("Z", 80);
      Course[] Courses = {course1, course2, course3};
      Room[] Rooms = {room1, room2, room3};


      String expectedAnswer = "[{A: X, B: X, C: Z}, {A: X, B: Z, C: X}, {A: Y, B: X, C: Z},"
        + " {A: Y, B: Z, C: X}, {A: Z, B: X, C: Z}]";

      if (!(ExamScheduler.findAllSchedules(Rooms, Courses).toString().equals(expectedAnswer))) {
        return false;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }

    //Tests if findAllSchedules throws right error when no valid schedules exist
    try {
      Course course1 = new Course("A", 15);
      Course course2 = new Course("B", 70);
      Course course3 = new Course("C", 50);
      Room room1 = new Room("X", 5);
      Room room2 = new Room("Y", 5);
      Room room3 = new Room("Z", 5);
      Course[] Courses = {course1, course2, course3};
      Room[] Rooms = {room1, room2, room3};


      if (!(ExamScheduler.findAllSchedules(Rooms, Courses).toString().equals("[]"))) {
        return false;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }


    return true;
  }

}
