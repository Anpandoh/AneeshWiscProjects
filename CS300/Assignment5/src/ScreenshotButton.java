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


/**
 * The button that saves a screenshot when clicked
 */
public class ScreenshotButton extends Button {

  /**
   * Creates a screenshot button at a given position within a pApplet object
   *
   * @param x x-position where this button will be added to the display window
   * @param y y-position where this button will be added to the display window
   */
  public ScreenshotButton(int x, int y) {
    super("Take a screenshot", x, y);
  }

  /**
   * When button is clicked, a screenshot will be saved
   */
  @Override public void mousePressed() {
    if (isMouseOver()) {
      processing.save("screenshot.png");
    }
  }


}
