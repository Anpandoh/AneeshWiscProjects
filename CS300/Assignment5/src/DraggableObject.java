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
 * Class for a object which can be dragged when interacted with
 */
public class DraggableObject extends InteractiveObject {

  private boolean isDragging;
  private int oldMouseX;
  private int oldMouseY;

  /**
   * Creates a default draggable object at location x,y with specified name and the
   * default message of help me
   * @param name    of object
   * @param x       the x location of the object where (0,0) is the top left corner
   * @param y       the y location of the object where (0,0) is the top left corner
   *
   */
  public DraggableObject(String name, int x, int y) {
    super(name, x, y, "Drag Me!");
  }

  /**
   * Creates a draggable interactive object at location x,y with specified name and message
   *
   * @param name    of object
   * @param x       the x location of the object where (0,0) is the top left corner
   * @param y       the y location of the object where (0,0) is the top left corner
   * @param message the message that is displayed when the object is clicked
   */
  public DraggableObject(String name, int x, int y, String message) {
    super(name, x, y, message);
  }

  /**
   * Gets the value of isDragging
   * @return true if the object is being dragged, false otherwise
   */
  public boolean isDragging() {
    return isDragging;
  }

  /**
   * Starts dragging the object and sets the old mouse location to the current location
   */
  public void startDragging() {
    isDragging = true;
    oldMouseX = processing.mouseX;
    oldMouseY = processing.mouseY;

  }

  /**
   * Sets isDragging to false (stops dragging)
   */
  public void stopDragging() {
    isDragging = false;
  }


  /**
   * If the mouse is over the draggable object and held, it starts dragging
   */
  @Override public void mousePressed() {
    if (isMouseOver()) {
      startDragging();
    }
  }

  /**
   * Draws the object wgile dragging
   */
  @Override public void draw() {
    if (isDragging) {
      this.move(processing.mouseX - oldMouseX, processing.mouseY - oldMouseY);
      processing.image(image, processing.mouseX, processing.mouseY);
      startDragging();
    } else {
      processing.image(image, getX(), getY());
    }

  }

  /**
   * Stops dragging if mouse is released
   */
  @Override public void mouseReleased() {
    stopDragging();
  }


}
