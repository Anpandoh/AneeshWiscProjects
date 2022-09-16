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


public interface Clickable {
  /**
   * Empty draw function
   */
  public void draw(); // draws this Clickable object to the screen

  /**
   * Empty mousePressed function
   */
  public void mousePressed(); // defines the behavior of this Clickable object
  // each time the mouse is pressed

  /**
   * Empty mouseReleased function
   */
  public void mouseReleased(); // defines the behavior of this Clickable object
  // each time the mouse is released

  /**
   * Empty is mouse over function
   * @return none.
   */
  public boolean isMouseOver(); // returns true if the mouse is over this clickable object
}
