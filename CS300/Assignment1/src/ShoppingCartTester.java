//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Shopping Cart Methods Testing
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

/**
 * Tests the various methods in the ShoppingCart class and checks for errors
 * @author Aneesh Pandoh
 *
 */
public class ShoppingCartTester {

  /**
   * Main method
   *
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    String i = "h";
    System.out.println(i.substring(0,5));
    /*System.out.println("testLookupMethods(): " + testLookupMethods());
    System.out.println("testGetProductPrice(): " + testGetProductPrice());
    System.out.println(
      "testAddItemToCartContainsNbOccurrences(): " + testAddItemToCartContainsNbOccurrences());
    System.out.println("testCheckoutGetCartSummary(): " + testCheckoutGetCartSummary());
    System.out.println("testRemoveItem(): " + testRemoveItem());
    System.out.println("runAllTests(): " + runAllTests());*/
  }

  /**
   * Checks whether ShoppingCart.lookupProductByName() and
   * ShoppingCart.lookupProductById() methods work as expected.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookupMethods() {
    // define test scenarios.
    // 1. The item to find is at index 0 of the marketItems array
    String expectedOutput = "4390 Apple $1.59";
    if (!ShoppingCart.lookupProductByName("Apple").equals(expectedOutput)) {
      System.out.println("Problem detected: Your lookupProductByName() method "
        + "failed to return the expected output when passed Apple as input");
      return false;
    }
    if (!ShoppingCart.lookupProductById(4390).equals(expectedOutput)) {
      System.out.println("Problem detected: Your lookupProductById() method "
        + "failed to return the expected output when passed the id " + "of Apple as input");
      return false;
    }

    // 2. The item to find is at the last non-null position of
    // the marketItems array
    expectedOutput = "4688 Tomato $1.79";
    if (!ShoppingCart.lookupProductByName("Tomato").equals(expectedOutput)) {
      System.out.println("Problem detected: Your lookupProductByName() method "
        + "failed to return the expected output when passed Tomato as input");
      return false;
    }
    if (!ShoppingCart.lookupProductById(4688).equals(expectedOutput)) {
      System.out.println("Problem detected: Your lookupProductById() method "
        + "failed to return the expected output when passed the id " + "of Tomato as input");
      return false;
    }

    // 3. The item to find is at an arbitrary position of the
    // middle of the marketItems array
    expectedOutput = "3560 Cheese $3.49";
    if (!ShoppingCart.lookupProductByName("Cheese").equals(expectedOutput)) {
      System.out.println("Problem detected: Your lookupProductByName() method "
        + "failed to return the expected output when passed Cheese as input");
      return false;
    }
    if (!ShoppingCart.lookupProductById(3560).equals(expectedOutput)) {
      System.out.println("Problem detected: Your lookupProductById() method "
        + "failed to return the expected output when passed the id " + "of Cheese as input");
      return false;
    }

    // 4. The item to find is not found in the market
    expectedOutput = "No match found";
    if (!ShoppingCart.lookupProductByName("NOT FOUND").equals(expectedOutput)) {
      System.out.println("Problem detected: Your lookupProductByName() method "
        + "failed to return the expected output when passed the name of "
        + "a product not found in the market.");
      return false;
    }
    if (!ShoppingCart.lookupProductById(1000).equals(expectedOutput)) {
      System.out.println("Problem detected: Your lookupProductById() method "
        + "failed to return the expected output when passed the identifier"
        + "of a product not found in the market.");
      return false;
    }
    return true; // NO BUGS detected by this tester method
  }

  /**
   * Checks the correctness of ShoppingCart.getProductPrice() method
   *
   * @return true when this test verifies a correct functionality,
   * and false otherwise
   */
  public static boolean testGetProductPrice() {
    // define test scenarios
    // first test scenario: get the price of Apple
    double expectedPrice = 1.59; // price of the product Apple in the market
    // Note that we do not use the == operator to check whether two
    // floating-point numbers (double or float) in java are equal.
    // Two variables a and b of type double are equal if the absolute
    // value of their difference is less or equal to a small threshold epsilon.
    // For instance, if Math.abs(a - b) <= 0.001, then a equals b
    if (Math.abs(ShoppingCart.getProductPrice("Apple") - expectedPrice) > 0.001) {
      System.out.println("Problem detected: Your getProductPrice() method "
        + "failed to return the expected output when passed the product Apple");
      return false;
    }

    // Second test scenario: get the price of Spinach
    expectedPrice = 3.09; // price of the product Spinach in the market

    if (Math.abs(ShoppingCart.getProductPrice("Spinach") - expectedPrice) > 0.001) {
      System.out.println("Problem detected: Your getProductPrice() method "
        + "failed to return the expected output when passed the product Spinach");
      return false;
    }

    //Third test scenario: get the price of not found item in market
    expectedPrice = -1; // the default value of an item not found in the market
    if (Math.abs(ShoppingCart.getProductPrice("NOT FOUND") - expectedPrice) > 0.001) {
      System.out.println("Problem detected: Your getProductPrice() method "
        + "failed to return the expected output when passed the name "
        + "of a product not found in the market");
      return false;
    }
    return true; // No bug detected. The ShoppingCart.getProductPrice()
    // passed this tester.
  }

  /**
   * Checks the correctness of ShoppingCart.nbOccurences and ShoppingCart.addItemToCart method
   *
   * @return true when this test verifies a correct functionality,
   * and false otherwise
   */
  public static boolean testAddItemToCartContainsNbOccurrences() {
    String[] emptyCart = new String[10];
    int size = 0;
    String item = "banana";
    int expectedOccurences = 1;

    size = ShoppingCart.addItemToCart("banana", emptyCart, size);
    if (expectedOccurences != ShoppingCart.nbOccurrences("banana", emptyCart, size + 1))
    {
      System.out.println("Problem detected: Your addItemToCart() method "
        + "failed to return the expected output when having an empty emptyCart");
      return false;
    }

    String[] fullCart = new String[] {"Milk", "Apple", "Banana", "Pizza"};
    size = 4;
    item = "Eggs";
    expectedOccurences = 0;

    size = ShoppingCart.addItemToCart(item, fullCart, size);
    if (expectedOccurences != ShoppingCart.nbOccurrences(item, fullCart, size)) {
      System.out.println("Problem detected: Your addItemToCart() method "
        + "failed to return the expected output when having an fullCart");
      return false;
    }

    item = "Eggs";
    String[] cart = new String[] {"Milk", "Apple", "Banana", "Pizza", null, null};
    size = 4; // 4 items are stored in the emptyCart
    expectedOccurences = 1;
    size = ShoppingCart.addItemToCart(item, cart, size);
    if (expectedOccurences != ShoppingCart.nbOccurrences(item, cart, size)) {
      System.out.println("Problem detected: Your addItemToCart() method "
        + "failed to return the expected output when having an half full cart");
      return false;
    }

    return true; // NO BUGS detected by this tester method
  }

  /**
   * Checks the correctness of ShoppingCart.removeItem() method
   *
   * @return true when this test verifies a correct functionality,
   * and false otherwise
   */
  public static boolean testRemoveItem() {
    String item = "Apple";
    String[] cart = new String[] {"Milk", "Apple", "Banana", "Apple", "Pizza", null, null};
    int size = 5;

    if (ShoppingCart.removeItem(cart, item, size) != 4
      && ShoppingCart.nbOccurrences("Apple", cart, 4) != 1) {
      System.out.println("Problem detected: Your removeItems() method "
        + "failed to return the expected output when having an half full cart");
      return false;
    }

    String[] emptyCart = new String[5];
    size = 0;
    if (ShoppingCart.removeItem(cart, item, size) != 0) {
      System.out.println("Problem detected: Your removeItems() method "
        + "failed to return the expected output when having an empty cart");
      return false;
    }

    item = "Eggs";
    String[] notFoundCart = new String[] {"Milk", "Apple", "Banana", "Pizza", "Milk", null, null};
    size = 5;
    if (ShoppingCart.removeItem(cart, item, size) != 5
      && ShoppingCart.nbOccurrences("Apple", cart, 4) != 0) {
      System.out.println("Problem detected: Your removeItems() method "
        + "failed to return the expected output when having a cart without the item");
      return false;
    }

    return true; // NO BUGS detected by this tester method
  }

  /**
   * Checks the correctness of ShoppingCart.getCartSummary() method
   *
   * @return true when this test verifies a correct functionality,
   * and false otherwise
   */
  public static boolean testCheckoutGetCartSummary() {
    String[] emptyCart = new String[7];
    int size = 0;
    String expectedOutput = "";
    if (!expectedOutput.equals(ShoppingCart.getCartSummary(emptyCart, size))) {
      System.out.println("Problem detected: Your getCartSummary() method "
        + "failed to return the expected output when having an empty cart");
      return false;
    }

    String[] smallCart = new String[] {"Milk", "Apple", "Banana", "Pizza", null, null};
    size = 4;

    expectedOutput = "(1) Apple\n" + "(1) Banana\n" + "(1) Milk\n" + "(1) Pizza\n";


    if (!expectedOutput.equals(ShoppingCart.getCartSummary(smallCart, size))) {
      System.out.println("Problem detected: Your getCartSummary() method "
        + "failed to return the expected output when having a cart with single items");
    }

    String[] cart =
      new String[] {"Tomato", "Milk", "Milk", "Eggs", "Tomato", "Onion", "Eggs", "Milk", "Banana",
        null, null};
    size = 9;

    expectedOutput = "(1) Banana\n" + "(2) Eggs\n" + "(3) Milk\n" + "(1) Onion\n" + "(2) Tomato\n";
    if (!expectedOutput.equals(ShoppingCart.getCartSummary(cart, size))) {
      System.out.println("Problem detected: Your getCartSummary() method "
        + "failed to return the expected output when having a cart with multiple "
        + "of the same items");
      return false;
    }
    return true; // NO BUGS detected by this tester method
  }

  /**
   * Checks if all the test cases pass
   *
   * @return true when this test verifies all tests are passed,
   * and false otherwise
   */
  public static boolean runAllTests() {
    //Run all test methods to verify if true
    if (testCheckoutGetCartSummary() && testGetProductPrice() && testRemoveItem()
      && testAddItemToCartContainsNbOccurrences() && testGetProductPrice()) {
      return true; // NO BUGS detected by this tester method
    } else {
      System.out.println("Problem detected: Your runAllTests() method "
        + "found an error in ShoppingCart.java");
      return false;
    }
  }
}
