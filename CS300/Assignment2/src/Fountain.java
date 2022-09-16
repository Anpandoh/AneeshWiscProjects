//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Fountain GUI That Releases Rains Droplets
// Course:   CS 300 Spring 2022
//
// Author:   Aneesh Pandoh
// Email:    Pandoh@wisc.edu
// Lecturer: Mouna Kacem
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Random;
import java.io.File;

import processing.core.PImage;

/**
 * Creates a fountain which drops infinite water droplets
 *
 * @author Aneesh Pandoh
 */
public class Fountain {
  private static final int SEED = 1;
  private static Random randGen;
  private static PImage fountainImage;
  private static int positionX;
  private static int positionY;
  private static Droplet[] droplets;
  private static int startColor; // blue: Utility.color(23,141,235)
  private static int endColor; // lighter blue: Utility.color(23,200,255)


  /**
   * Main method
   *
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    Utility.runApplication();
  }

  /**
   * Updates the location of a droplet
   *
   * @param index of which droplet in the array location needs to be updated
   */
  private static void updateDroplet(int index) {

    Droplet dropX = droplets[index];
    Utility.fill(dropX.getColor(), dropX.getTransparency());
    Utility.circle(dropX.getPositionX(), dropX.getPositionY(), dropX.getSize());

    dropX.setVelocityY(dropX.getVelocityY() + 0.3f);
    dropX.setPositionX(dropX.getPositionX() + dropX.getVelocityX());
    dropX.setPositionY(dropX.getPositionY() + dropX.getVelocityY());
    dropX.setAge(dropX.getAge() + 1);
  }

  /**
   * Adds randomly generated droplets to the droplet array
   *
   * @param numNewDroplets the number of new droplets that will be added to the droplets array
   */
  private static void createNewDroplets(int numNewDroplets) {

    int madeDroplets = 0;
    for (int i = 0; i < droplets.length; ++i) {
      if (droplets[i] == null && madeDroplets < numNewDroplets) {
        droplets[i] = new Droplet();
        droplets[i].setVelocityX(randGen.nextFloat() * 2 - 1);
        droplets[i].setVelocityY(randGen.nextFloat() * 5 - 10);
        droplets[i].setPositionX(positionX + randGen.nextFloat() * 6 - 3);
        droplets[i].setPositionY(positionY + randGen.nextFloat() * 6 - 3);
        droplets[i].setSize(randGen.nextFloat() * 7 + 4);
        droplets[i].setColor(Utility.lerpColor(startColor, endColor, randGen.nextFloat()));
        droplets[i].setAge(randGen.nextInt(41));
        droplets[i].setTransparency(randGen.nextInt(96) + 32);

        ++madeDroplets;
      }
    }
  }

  /**
   * Removes droplets that are too old
   *
   * @param maxAge the largest age number that a droplet can be before it gets removed
   */
  private static void removeOldDroplets(int maxAge) {
    for (int i = 0; i < droplets.length; ++i) {
      if (droplets[i] != null && droplets[i].getAge() > maxAge) {
        droplets[i] = null;
      }
    }
  }

  /**
   * Allows the fountain (and its droplets) to be moved by the click or drag of a cursor
   */
  public static void mousePressed() {
    positionX = Utility.mouseX();
    positionY = Utility.mouseY();
  }

  /**
   * Saves a screenshot if the user presses the 's' key
   *
   * @param key the user input via the keyboard
   */
  public static void keyPressed(char key) {
    if (key == 's' || key == 'S') {
      Utility.save("screenshot.png");
    }
  }

  /**
   * This tester initializes the droplets array to hold at least three droplets.
   * Creates a single droplet at position (3,3) with velocity (-1,-2). Then checks
   * whether calling updateDroplet() on this droplet’s index correctly results in
   * changing its position to (2.0, 1.3).
   *
   * @return true when no defect is found, and false otherwise
   */
  private static boolean testUpdateDroplet() {

    Droplet drop1 = new Droplet();
    Droplet drop2 = new Droplet();
    Droplet drop3 = new Droplet();
    drop1.setPositionX(3);
    drop1.setPositionY(3);
    drop1.setVelocityX(-1);
    drop1.setVelocityY(-2);
    droplets = new Droplet[] {drop1, drop2, drop3};

    updateDroplet(0);
    float expectedOutput1 = 2.0f;
    float expectedOutput2 = 1.3f;

    if (droplets[0].getPositionX() != expectedOutput1
      || droplets[0].getPositionY() != expectedOutput2) {
      System.out.println("Problem detected: Your updateDroplet() method"
        + "failed to return the expected output when a droplet with position(3,3) and "
        + "velocity(-1,-2) is passed as input");
      return false;
    }
    return true;
  }

  /**
   * This tester initializes the droplets array to hold at least three droplets.
   * Calls removeOldDroplets(6) on an array with three droplets (two of which have
   * ages over six and another that does not). Then checks whether the old droplets
   * were removed and the young droplet was left alone.
   *
   * @return true when no defect is found, and false otherwise
   */
  private static boolean testRemoveOldDroplets() {
    Droplet drop1 = new Droplet();
    Droplet drop2 = new Droplet();
    Droplet drop3 = new Droplet();
    drop1.setAge(7);
    drop2.setAge(2);
    drop3.setAge(10);
    droplets = new Droplet[] {drop1, drop2, drop3};

    Droplet expectedOutput1 = null;
    Droplet expectedOutput2 = drop2;
    Droplet expectedOutput3 = null;

    removeOldDroplets(6);

    if (droplets[0] != expectedOutput1 || droplets[1] != expectedOutput2
      || droplets[2] != expectedOutput3) {
      System.out.println("Problem detected: Your removeOldDroplets() method"
        + "failed to return the expected output a maxAge of 6 is passed as input");
      return false;
    }
    return true;
  }

  /**
   * Gets called once which loads the background as well as the fountain image
   * It also sets the static variables inclduing the droplets array
   * Calls the tester methods
   */
  public static void setup() {
    System.out.println(testUpdateDroplet());
    System.out.println(testRemoveOldDroplets());

    fountainImage = Utility.loadImage(
      "images" + File.separator + "fountain.png");     // load the image of the fountain
    randGen = new Random(SEED);
    positionX = Utility.width() / 2;
    positionY = Utility.height() / 2;

    startColor = Utility.color(23, 141, 235);
    endColor = Utility.color(23, 200, 255);

    droplets = new Droplet[800];
  }

  /**
   * Continuously to displays the background, images, and droplets
   */
  public static void draw() {
    // Draw the fountain image to the screen at position (positionX, positionY)
    Utility.background(Utility.color(253, 245, 230));
    Utility.image(fountainImage, positionX, positionY);

    createNewDroplets(10);

    for (int i = 0; i < droplets.length; ++i) {
      if (droplets[i] != null) {
        updateDroplet(i);
      }
    }
    removeOldDroplets(80);
  }
}
