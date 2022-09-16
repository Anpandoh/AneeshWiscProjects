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

import processing.core.PImage;

import java.util.NoSuchElementException;
import java.io.File;


/**
 * Creates an object which will perform some sort of function when clicked
 */
public class InteractiveObject implements Clickable {
  // reference to the PApplet where this object will be drawn
  protected static TreasureHunt processing;
  private final String NAME; // name of this interactive object
  protected PImage image; // reference to the image of this object
  private int x; // x-position of this interactive object in the screen
  private int y; // y-position of this interactive object in the screen
  // Note that the position (x,y) of the interactive object is the position
  // of the upper-left corner of its image (and NOT the center of the image).
  private boolean isActive; // tells whether or not this object is active
  private boolean wasClicked; // tells whether this object was already clicked
  private String message; // message to be displayed when this object is clicked
  private InteractiveObject nextClue; // Object to be activated when this object is
  // clicked the first time, if any

  /**
   * Creates a default interactive object at location x,y with specified name and message
   *
   * @param name    of object
   * @param x       the x location of the object where (0,0) is the top left corner
   * @param y       the y location of the object where (0,0) is the top left corner
   * @param message the message that is displayed when the object is clicked
   */
  public InteractiveObject(String name, int x, int y, String message) {
    this.NAME = name;
    this.x = x;
    this.y = y;
    this.message = message;
    isActive = true;
    wasClicked = false;
    image = processing.loadImage("images" + File.separator + name + ".png");
  }

  /**
   * Creates a default interactive object at location x,y with specified name and message
   * as well as initializes the next clue
   *
   * @param name     of object
   * @param x        the x location of the object where (0,0) is the top left corner
   * @param y        the y location of the object where (0,0) is the top left corner
   * @param message  the message that is displayed when the object is clicked
   * @param nextClue which will be made once the object is interacted with correctly
   */
  public InteractiveObject(String name, int x, int y, String message, InteractiveObject nextClue) {
    this(name, x, y, message);
    this.nextClue = nextClue;
    nextClue.isActive = false;
  }

  /**
   *
   */
  public void activate() {
    isActive = true;
  }

  /**
   * Activates the next clue of the object and adds it to the TreasureHunt class
   * @throws NoSuchElementException if no next clue exists
   */
  protected void activateNextClue() throws NoSuchElementException {
    if (nextClue == null) {
      throw new NoSuchElementException("No next clue exists");
    }
    nextClue.activate();
    processing.add(nextClue);
  }

  /**
   * Deactivates the object
   */
  public void deactivate() {
    isActive = false;
  }

  /**
   * Getter for x value
   * @return the x position of object
   */
  public int getX() {
    return x;
  }

  /**
   * Getter for y value
   * @return the y position of object
   */
  public int getY() {
    return y;
  }

  /**
   * Check whether a given name is equal to the name of the object
   * @param name which is being determined if its the same
   * @return true if the object has the specified name, false otherwise
   */
  public boolean hasName(String name) {
    return name.equals(this.NAME);
  }

  /**
   * Getter for active value
   * @return true if object is active, false if not active
   */
  public boolean isActive() {
    return isActive;
  }

  /**
   * Getter for the message string
   * @return the message that the object should display when interacted with
   */
  public String message() {
    return message;
  }

  /**
   * Moves the object's location by a certain amount indicated by dx, dy
   * @param dx the amount of change the x value will increase by
   * @param dy the amount of change the y value will increase by
   */
  public void move(int dx, int dy) {
    x += dx;
    y += dy;
  }

  /**
   * Sets the next clue for the object
   * @param nextClue the next object which will be created from the current object
   */
  public void setNextClue(InteractiveObject nextClue) {
    this.nextClue = nextClue;
  }

  /**
   * Sets the PApplet for interactive object
   * @param processing the processing class which is being set
   */
  public static void setProcessing(TreasureHunt processing) {
    InteractiveObject.processing = processing;
  }


  /**
   * Draws the image of the interactive object
   */
  @Override public void draw() {
    processing.image(image, x, y);
  }

  /**
   * Displays message if mouse pressed, and activates the next clue if this is the first
   * time clicked and the next clue exists
   */
  @Override public void mousePressed() {
    if (isMouseOver()) {
      System.out.println(message);
      if (nextClue != null && !wasClicked) {
        activateNextClue();
        wasClicked = true;
      }
    }

  }

  /**
   * Empty method for if the mouse is released
   */
  @Override public void mouseReleased() {

  }

  /**
   * Checks if the mouse is over the object
   * @return true if the moust is over the object, false otherwise
   */
  @Override public boolean isMouseOver() {
    return processing.mouseX >= x && processing.mouseX <= x + image.width && processing.mouseY >= y
      && processing.mouseY <= y + image.height;
  }
}
