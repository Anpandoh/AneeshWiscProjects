//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Exception Shopping Cart Class Tester Makes Sure Everything Works As Expected
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


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Tests the various new methods in the ShoppingCart class and checks for errors
 *
 * @author Aneesh Pandoh
 */
public class ExceptionalShoppingCartTester {

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
    if (testSaveCartSummary() && testLoadCartSummary() && testLookupMethods()
      && testAddItemToMarketCatalog()) {
      return true;
    } else {
      System.out.println(
        "Problem detected: Your runAllTests() method " + "found an error in ShoppingCart.java");
      return false;
    }
  }

  /**
   * Checks if the lookUpProductByName and lookUpProductByID methods work and produce the right
   * error when necessary
   *
   * @return true when this test verifies a correct functionality,
   * and false otherwise
   */

  public static boolean testLookupMethods() {

    //lookupProductByID

    //Check if produces error for a key that is not a 4 digit integer
    try {
      ExceptionalShoppingCart.lookupProductById(1);
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("Expected Error: " + e.getMessage());
    } catch (Exception unexpectedException) {
      System.out.println("!!!Unexpected Error: " + unexpectedException.getMessage());
      return false;
    }

    //Check if produces error for a key that is not in market catalog
    try {
      ExceptionalShoppingCart.lookupProductById(1000);
      return false;
    } catch (NoSuchElementException e) {
      System.out.println("Expected Error: " + e.getMessage());
    } catch (Exception unexoectedException) {
      System.out.println("!!!Unexpected Error: " + unexoectedException.getMessage());
      return false;
    }

    try {
      String expectedOuput = "4390 Apple $1.59";
      if (!ExceptionalShoppingCart.lookupProductById(4390).equals(expectedOuput)) {
        System.out.println("!!!Unexpected Error: Did not return the right product");
        return false;
      }
    } catch (Exception unexoectedException) {
      System.out.println("!!!Unexpected Error: " + unexoectedException.getMessage());
      return false;
    }


    //lookupProductByName

    //Throws error because it cannot find the product in market catalog
    try {
      ExceptionalShoppingCart.lookupProductByName("");
      return false;
    } catch (NoSuchElementException e) {
      System.out.println("Expected Error: " + e.getMessage());
    } catch (Exception unexoectedException) {
      System.out.println("!!!Unexpected Error: " + unexoectedException.getMessage());
      return false;
    }

    try {
      String expectedOuput = "4390 Apple $1.59";
      if (!ExceptionalShoppingCart.lookupProductByName("Apple").equals(expectedOuput)) {
        System.out.println("!!!Unexpected Error: Did not return the right product");
        return false;
      }
    } catch (Exception unexoectedException) {
      System.out.println("!!!Unexpected Error: " + unexoectedException.getMessage());
      return false;
    }

    return true;
  }

  /**
   * Checks if the addItemToMarketCatalog methods work and produce the right
   * error when necessary
   *
   * @return true when this test verifies a correct functionality,
   * and false otherwise
   */

  //Make sure to test last
  public static boolean testAddItemToMarketCatalog() {

    try {
      ExceptionalShoppingCart.addItemToMarketCatalog("4000", "hello", "wrong");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("Expected Error Error: " + e.getMessage());
    } catch (Exception unexpectedException) {
      System.out.println("!!!Unexpected Error: " + unexpectedException.getMessage());
      return false;
    }

    try {
      ExceptionalShoppingCart.addItemToMarketCatalog("4000", "hello", "$16.3");
    } catch (IllegalArgumentException e) {
      System.out.println("!!! Unexpected Error: " + e.getMessage());
      return false;
    } catch (Exception unexpectedException) {
      System.out.println("!!!Unexpected Error: " + unexpectedException.getMessage());
      return false;
    }


    try {
      String expectedOuput = "4000 test $12.09";
      ExceptionalShoppingCart.addItemToMarketCatalog("4000", "test", "$12.09");
      if (!(ExceptionalShoppingCart.lookupProductByName("test").equals(expectedOuput))) {
        System.out.println("Error: Did not return the correct product");
        return false;
      }
    } catch (Exception unexoectedException) {
      System.out.println("!!!Unexpected Error: " + unexoectedException.getMessage());
      return false;
    }

    //Make sure it adds indexs if exceeds catalog length
    try {
      String expectedOuput = "8000 test4 $12.09";
      ExceptionalShoppingCart.addItemToMarketCatalog("4000", "test", "$12.09");
      ExceptionalShoppingCart.addItemToMarketCatalog("5000", "test1", "$12.09");
      ExceptionalShoppingCart.addItemToMarketCatalog("6000", "test2", "$12.09");
      ExceptionalShoppingCart.addItemToMarketCatalog("7000", "test3", "$12.09");
      ExceptionalShoppingCart.addItemToMarketCatalog("8000", "test4", "$12.09");
      if (!(ExceptionalShoppingCart.lookupProductByName("test4").equals(expectedOuput))) {
        System.out.println("Error: Did not return the correct product");
        return false;
      }
    } catch (Exception unexoectedException) {
      System.out.println("!!!Unexpected Error: " + unexoectedException.getMessage());
      return false;
    }


    return true;
  }


  /**
   * Checks if the testSaveCartSummary methods works and produce the right
   * error when necessary
   *
   * @return true when this test verifies a correct functionality,
   * and false otherwise
   */
  public static boolean testSaveCartSummary() {
    //Correctly saves the cart summary
    File file0 = new File("file0.txt");
    String[] smallCart =
      new String[] {"Apple", "Grape", "Milk", "Pepper", "Banana", null, null, null, null, null};
    int size = 5;
    ExceptionalShoppingCart.saveCartSummary(smallCart, size, file0);

    String text = "";
    try {
      Scanner scnr = new Scanner(file0);
      while (scnr.hasNextLine()) {
        text += scnr.nextLine() + " ";
      }
    } catch (FileNotFoundException e) {
      System.out.println("Could not find file");
    }
    String expectedOutput = "( 1 ) Apple ( 1 ) Grape ( 1 ) Milk ( 1 ) Pepper ( 1 ) Banana ";
    if (!(text.equals(expectedOutput))) {
      System.out.println("The saved file did not meet the expectedOuput");
      return false;
    }

    //Produces error when cart size is less than 0

    try {
      ExceptionalShoppingCart.saveCartSummary(smallCart, -1, file0);
      return false;
    } catch (IllegalArgumentException exception) {
      System.out.println("Expected Error: " + exception.getMessage());
    }

    return true;

  }

  /**
   * Checks if the loadCartSummary methods work and produce the right
   * error when necessary
   *
   * @return true when this test verifies a correct functionality,
   * and false otherwise
   */
  public static boolean testLoadCartSummary() {
    File file1 = new File("file1.txt");

    //Loads the cart summary correctly
    try {
      FileWriter myWriter = new FileWriter("file1.txt");
      myWriter.write(
        "( 2 ) Apple\n" + "( 1 ) Milk\n" + "( 1 )\n" + "( one ) Chocolate\n" + "( 3 ) Pizza\n"
          + "( 1 ) Orange\n" + "( 1 ) Grape\n");
      myWriter.close();
    } catch (IOException filenotfound) {
      System.out.println(filenotfound.getMessage());
    }
    String[] smallCart = new String[] {null, null, null, null, null, null, null, null, null, null};
    int size = 0;

    try {
      size = ExceptionalShoppingCart.loadCartSummary(file1, smallCart, size);
    } catch (IllegalArgumentException e) {
      System.out.println("!!!Unexpected Error: " + e.getMessage());
      return false;
    } catch (IllegalStateException capacity) {
      System.out.println("!!! Unexpected Error: OverCapacity:" + capacity.getMessage());
      return false;
    } catch (Exception unexpectedException) {
      System.out.println("!!!Unexpected Error: " + unexpectedException.getMessage());
      return false;
    }
    String expectedOutput = "( 2 ) Apple\n" + "( 1 ) Milk\n" + "( 3 ) Pizza\n" + "( 1 ) Grape";
    if (!ExceptionalShoppingCart.getCartSummary(smallCart, size).equals(expectedOutput)) {
      System.out.println("Not correct cart");
      return false;
    }


    //Max Capacity Exception
    File file2 = new File("file2.txt");
    try {
      FileWriter myWriter = new FileWriter("file2.txt");
      myWriter.write(
        "( 2 ) Apple\n" + "( 1 ) Milk\n" + "( 1 )\n" + "( one ) Chocolate\n" + "( 3 ) Pizza\n"
          + "( 1 ) Orange\n" + "( 5 ) Grape\n");
      myWriter.close();
    } catch (IOException filenotfound) {
      System.out.println(filenotfound.getMessage());
    }

    String[] midCart =
      new String[] {"Apple", "Milk", null, null, null, null, null, null, null, null};
    size = 2;

    try {
      size = ExceptionalShoppingCart.loadCartSummary(file2, smallCart, size);
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("!!!Unexpected Error: " + e.getMessage());
      return false;
    } catch (IllegalStateException capacity) {
      System.out.println("Expected Error: over capacity: " + capacity.getMessage());
    } catch (Exception unexpectedException) {
      System.out.println("!!!Unexpected Error: " + unexpectedException.getMessage());
      return false;
    }

    //Size of cart is less than 0, should produce error
    try {
      size = ExceptionalShoppingCart.loadCartSummary(file2, smallCart, -1);
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("Expected Error: " + e.getMessage());
    } catch (IllegalStateException capacity) {
      System.out.println("Error: over capacity: " + capacity.getMessage());
      return false;
    } catch (Exception unexpectedException) {
      System.out.println("!!!Unexpected Error: " + unexpectedException.getMessage());
      return false;
    }

    return true;
  }

  //  public static boolean testParseCartSummary() {
  //
  //
  //    String[] smallCart = new String[] {null, null, null, null, null, null};
  //    int size = 0;
  //    String expectedOutput = "( 1 ) Apple";
  //    try {
  //      ExceptionalShoppingCart.parseCartSummaryLine(expectedOutput, smallCart, size);
  //    } catch (IllegalArgumentException e) {
  //      System.out.println("Error: " + e.getMessage());
  //    } catch (Exception unexpectedException) {
  //      System.out.println("Unexpected Error: " + unexpectedException.getMessage());
  //      return false;
  //    }
  //
  //
  //    return true;
  //  }



}
