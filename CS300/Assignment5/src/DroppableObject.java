//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    TreasureHuntGame
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
 * Creates an object that is droppable on a target location to activate a next clue
 */
public class DroppableObject extends DraggableObject {

  private InteractiveObject target;

  /**
   * Creates a droppable interactive object at location x,y with specified name, message, target
   * and nextClue
   * @param name    of object
   * @param x       the x location of the object where (0,0) is the top left corner
   * @param y       the y location of the object where (0,0) is the top left corner
   * @param message the message that is displayed when the object is clicked
   * @param target the location where the object is dropped revealing the next clue
   * @param nextClue the next object that will be revealed if the droppable object is
   *                 correctly interacted with
   */
  DroppableObject(String name, int x, int y, String message, InteractiveObject target,
    InteractiveObject nextClue) {
    super(name,x, y, message);
    this.target = target;
    setNextClue(nextClue);
  }

  /**
   * Checks if the object is over the target area
   * @param other the target area which is another interactive object
   * @return true if object is over target, false otherwise
   */
  public boolean isOver(InteractiveObject other) {
    int x1 = getX();
    int y1 = getY();
    int x2 = x1 + image.width;
    int y2 = y1 + image.height;

    int targetx1 = other.getX();
    int targety1 = other.getY();
    int targetx2 = targetx1 + other.image.width;
    int targety2 = targety1 + other.image.height;

    if ((x1 < targetx2) && (targetx1 < x2) && (y1 < targety2) && (targety1 < y2)){
      return true;
    }
    return false;
  }

  /**
   * Check if the object is over the droppable area and activates next clue if so and
   * deactivates current object
   */
  @Override
  public void mouseReleased(){
    stopDragging();
    if (isOver(target) && target.isActive()) {
      deactivate();
      target.deactivate();
      try {
        activateNextClue();
      } catch(NoSuchElementException NoNextClue) {
        System.out.println(NoNextClue.getMessage());
      }

      message();
    }
  }





}
