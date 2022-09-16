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


import java.util.ArrayList;


/**
 * Schedules the exams for courses to certain locations
 * @author Aneesh Pandoh
 */
public class ExamScheduler {

  /**
   * Creates a schedule given rooms and courses
   * @param rooms available to be scheduled an exam
   * @param courses that need to be assigned rooms
   * @return a working schedule, or an error if no schedule can be found
   */
  public static Schedule findSchedule(Room[] rooms, Course[] courses) {
    Schedule unassignedSchedule = new Schedule(rooms, courses);
    return findScheduleHelper(unassignedSchedule, 0);
  }

  /**
   * Assigns the courses to specific rooms recursively
   * @param schedule the schedule that is being created and assigned
   * @param index is the course that is being assigned at the specific function call
   * @return The first schedule that works
   * @throws IllegalStateException if no schedule can be found
   */
  private static Schedule findScheduleHelper(Schedule schedule, int index)
    throws IllegalStateException {
    if (schedule.getNumCourses() == index) {
      if (schedule.isComplete()) {
        return schedule;
      } else {
        throw new IllegalStateException("Schedule is Invalid");
      }
    }
    if (schedule.isAssigned(index)) {
      schedule = findScheduleHelper(schedule, index + 1);
      return schedule;
    } else {
      for (int roomIndex = 0; roomIndex < schedule.getNumRooms(); ++roomIndex) {
        try {
          Schedule testSchedule = schedule.assignCourse(index, roomIndex);
          return findScheduleHelper(testSchedule, index + 1);
        } catch (IllegalStateException invalid) {
        } catch (IllegalArgumentException notEnoughSpace) {
          if (roomIndex == schedule.getNumRooms() - 1)
            throw new IllegalStateException("Schedule is Invalid");
        }
      }
    }
    throw new IllegalStateException("Schedule is Invalid");
  }


  /**
   * Gets all possible schedules given rooms and courses needing to be assigned
   * @param rooms that are available to be assigned
   * @param courses that need to be assigned
   * @return arraylist of all possible schedule combinations, empty arraylist if none
   */
  public static ArrayList<Schedule> findAllSchedules(Room[] rooms, Course[] courses) {
    Schedule unassignedSchedule = new Schedule(rooms, courses);
    return findAllSchedulesHelper(unassignedSchedule, 0);
  }

  /**
   * Recursively assigns courses to rooms, making complete schedules and adds them to an arraylist
   * @param schedule the schedule that is being created and assigned
   * @param index is the course that is being assigned at the specific function call
   * @return arraylist of all possible schedule combinations, empty arraylist if none
   */
  private static ArrayList<Schedule> findAllSchedulesHelper(Schedule schedule, int index) {
    ArrayList<Schedule> allSchedulesList = new ArrayList<>();

    if (schedule.getNumCourses() == index) {
      if (schedule.isComplete()) {
        allSchedulesList.add(schedule);
        return allSchedulesList;
      } else {
        throw new IllegalStateException("Schedule is invalid");
      }
    }
    if (schedule.isAssigned(index)) {
      allSchedulesList = findAllSchedulesHelper(schedule, index + 1);
      return allSchedulesList;
    } else {
      for (int roomIndex = 0; roomIndex < schedule.getNumRooms(); ++roomIndex) {
        try {
          Schedule newSchedule = schedule.assignCourse(index, roomIndex);
          allSchedulesList.addAll((findAllSchedulesHelper(newSchedule, index + 1)));
        } catch (IllegalStateException | IllegalArgumentException ignored) {
        }
      }
    }

    return allSchedulesList;
  }

}

