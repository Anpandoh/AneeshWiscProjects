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
 * The button to restart the game when pressed
 */
public class RestartGameButton extends Button {

  /**
   * Creates the restart game button at a given position within a pApplet object
   *
   * @param x x-position where this button will be added to the display window
   * @param y y-position where this button will be added to the display window
   */
  public RestartGameButton(int x, int y) {
    super("Restart Game", x, y);
  }

  /**
   * When button is clicked, the game is restarted.
   */
  @Override public void mousePressed() {
    if (isMouseOver()) {
      ((TreasureHunt) processing).initGame();
    }
  }
}
