//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    ArtGalleryP09
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
 * This class checks the correctness of the implementation of the methods defined in the class
 * ArtworkGallery.
 *
 * @author Aneesh Pandoh
 */

public class ArtGalleryTester {

  /**
   * Checks the correctness of the implementation of both compareTo() and equals() methods defined
   * in the Artwork class.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testArtworkCompareToEquals() {
    Artwork a = new Artwork("a", 2000, 1000);
    Artwork b = new Artwork("b", 2000, 2000);
    Artwork A = new Artwork("a", 2000, 1000);

    try {
      if (!(a.equals(A))) {
        System.out.println("Error: equals() method does not work");
        return false;
      }
      if ((a.equals(b))) {
        System.out.println("Error: equals() method does not work");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error: equals() method does not work");
      return false;
    }

    try {
      if (!(a.compareTo(b) < 0)) {
        System.out.println("Error: compareTo() method does not work");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error: compareTo() method does not work");
      return false;
    }
    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Checks the correctness of the implementation of both addArtwork() and toString() methods
   * implemented in the ArtworkGallery class. This unit test considers at least the following
   * scenarios. (1) Create a new empty ArtworkGallery, and check that its size is 0, it is empty,
   * and that its string representation is an empty string "". (2) try adding one artwork and then
   * check that the addArtwork() method call returns true, the tree is not empty, its size is 1, and
   * the .toString() called on the tree returns the expected output. (3) Try adding another artwork
   * which is smaller that the artwork at the root, (4) Try adding a third artwork which is greater
   * than the one at the root, (5) Try adding at least two further artwork such that one must be
   * added at the left subtree, and the other at the right subtree. For all the above scenarios, and
   * more, double check each time that size() method returns the expected value, the add method call
   * returns true, and that the .toString() method returns the expected string representation of the
   * contents of the binary search tree in an increasing order from the smallest to the greatest
   * artwork with respect to year, cost, and then name. (6) Try adding a artwork already stored in
   * the tree. Make sure that the addArtwork() method call returned false, and that the size of the
   * tree did not change.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddArtworkToStringSize() {
    Artwork a = new Artwork("a", 2000, 1000);
    Artwork b = new Artwork("b", 2000, 2000);
    Artwork d = new Artwork("d", 2000, 2500);
    Artwork e = new Artwork("e", 1000, 500);
    Artwork g = new Artwork("g", 1000, 1200);


    try {
      ArtGallery z = new ArtGallery();
      if (z.size() != 0) {
        System.out.println("Error: size() method does not work");
        return false;
      }

      String expectedOutput1 = "[(Name: a) (Year: 2000) (Cost: $1000.0)]";
      if (!(z.addArtwork(a)) || z.isEmpty() || z.size() != 1 || !(z.toString()
        .equals(expectedOutput1))) {
        System.out.println(
          "Error: size(), isEmpty(), toString() or addArtwork() method does not work");
        return false;
      }


      String expectedOutput2 =
        "[(Name: g) (Year: 1000) (Cost: $1200.0)]\n" + "[(Name: a) (Year: 2000) (Cost: $1000.0)]";
      if (!(z.addArtwork(g)) || z.isEmpty() || z.size() != 2 || !(z.toString()
        .equals(expectedOutput2))) {
        System.out.println(

          "Error: size(), isEmpty(), toString() or addArtwork() method does not work");
        return false;
      }

      String expectedOutput3 =
        "[(Name: g) (Year: 1000) (Cost: $1200.0)]\n" + "[(Name: a) (Year: 2000) (Cost: $1000.0)]\n"
          + "[(Name: b) (Year: 2000) (Cost: $2000.0)]";
      if (!(z.addArtwork(b)) || z.isEmpty() || z.size() != 3 || !(z.toString()
        .equals(expectedOutput3))) {
        System.out.println(
          "Error: size(), isEmpty(), toString() or addArtwork() method does not work");
        return false;
      }

      String expectedOutput4 =
        "[(Name: e) (Year: 1000) (Cost: $500.0)]\n" + "[(Name: g) (Year: 1000) (Cost: $1200.0)]\n"
          + "[(Name: a) (Year: 2000) (Cost: $1000.0)]\n"
          + "[(Name: b) (Year: 2000) (Cost: $2000.0)]\n"
          + "[(Name: d) (Year: 2000) (Cost: $2500.0)]";
      if (!(z.addArtwork(e)) || !(z.addArtwork(d)) || z.isEmpty() || z.size() != 5 || !(z.toString()
        .equals(expectedOutput4))) {
        System.out.println(
          "Error: size(), isEmpty(), toString() or addArtwork() method does not work");
        return false;
      }

      if ((z.addArtwork(e)) || z.isEmpty() || z.size() != 5 || !(z.toString()
        .equals(expectedOutput4))) {
        System.out.println(
          "Error: size(), isEmpty(), toString() or addArtwork() method does not work");
        return false;
      }
    } catch (Exception error) {
      System.out.println(
        "Error: size(), isEmpty(), toString() or addArtwork() method does not work");
      return false;
    }



    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * This method checks mainly for the correctness of the ArtworkGallery.lookup() method. It must
   * consider at least the following test scenarios. (1) Create a new ArtworkGallery. Then, check
   * that calling the lookup() method on an empty ArtworkGallery returns false. (2) Consider a
   * ArtworkGallery of height 3 which lookup at least 5 artwork. Then, try to call lookup() method
   * to search for the artwork having a match at the root of the tree. (3) Then, search for a
   * artwork at the right and left subtrees at different levels considering successful and
   * unsuccessful search operations. Make sure that the lookup() method returns the expected output
   * for every method call.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookup() {

    Artwork a = new Artwork("a", 2000, 1000);
    Artwork b = new Artwork("b", 2000, 2000);
    Artwork d = new Artwork("d", 2000, 2500);
    Artwork e = new Artwork("e", 1000, 500);
    Artwork g = new Artwork("g", 1000, 1200);
    try {
      ArtGallery z = new ArtGallery();
      if (z.lookup("a", 1000, 2000)) {
        System.out.println("Error: lookUp() method doesn't work");
        return false;
      }

      z.addArtwork(a);
      z.addArtwork(b);
      z.addArtwork(d);
      z.addArtwork(e);
      z.addArtwork(g);

      if (!(z.lookup("a", 2000, 1000))) {
        System.out.println("Error: lookUp() method doesn't work");
        return false;
      }

      if (!(z.lookup("d", 2000, 2500))) {
        System.out.println("Error: lookUp() method doesn't work");
        return false;
      }

      if (!(z.lookup("g", 1000, 1200))) {
        System.out.println("Error: lookUp() method doesn't work");
        return false;
      }

      if ((z.lookup("a", 1100, 1200))) {
        System.out.println("Error: lookUp() method doesn't work");
        return false;
      }
    } catch (Exception error) {
      System.out.println("Error: lookUp() method doesn't work");
      return false;
    }
    return true;
  }

  /**
   * Checks for the correctness of ArtworkGallery.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty artwork tree is zero. (2) ensures
   * that the height of a tree which consists of only one node is 1. (3) ensures that the height of
   * a ArtworkGallery with the following structure for instance, is 4.
   * (*)
   * /  \
   * (*)  (*)
   * \   / \
   * (*) (*) (*)
   * /
   * (*)
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    Artwork a = new Artwork("a", 2000, 1000);
    Artwork b = new Artwork("b", 2000, 2000);
    Artwork d = new Artwork("d", 2000, 2500);
    Artwork e = new Artwork("e", 1000, 500);
    Artwork g = new Artwork("g", 1000, 1200);
    try {
      ArtGallery z = new ArtGallery();
      if (z.height() != 0) {
        System.out.println("Error: lookUp() method doesn't work");
        return false;
      }
      z.addArtwork(a);
      if (z.height() != 1) {
        System.out.println("Error: lookUp() method doesn't work");
        return false;
      }
      z.addArtwork(b);
      z.addArtwork(d);
      z.addArtwork(e);
      z.addArtwork(g);
      if (z.height() != 3) {
        System.out.println("Error: lookUp() method doesn't work");
        return false;
      }
    } catch (Exception error) {
      System.out.println("Error: lookUp() method doesn't work");
      return false;
    }
    return true;
  }

  /**
   * Checks for the correctness of ArtworkGallery.getBestArtwork() method.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetBestArtwork() {
    Artwork a = new Artwork("a", 2000, 1000);
    Artwork b = new Artwork("b", 2000, 2000);
    Artwork c = new Artwork("c", 2000, 3000);
    Artwork d = new Artwork("d", 2000, 2500);
    Artwork e = new Artwork("e", 2000, 500);
    Artwork f = new Artwork("f", 3000, 5000);
    Artwork g = new Artwork("g", 1000, 1200);
    try {
      ArtGallery z = new ArtGallery();
      if (z.getBestArtwork() != null) {
        System.out.println("Error: getBestArtwork() method doesn't work");
        return false;
      }
      z.addArtwork(a);
      z.addArtwork(b);
      z.addArtwork(c);
      z.addArtwork(d);
      z.addArtwork(e);
      z.addArtwork(f);
      z.addArtwork(g);
      if (!(z.getBestArtwork().equals(f))) {
        System.out.println("Error: getBestArtwork() method doesn't work");
        return false;
      }
    } catch (Exception error) {
      System.out.println("Error: getBestArtwork() method doesn't work");
      return false;
    }
    return true;
  }


  /**
   * Checks for the correctness of ArtworkGallery.lookupAll() method. This test must consider at
   * least 3 test scenarios. (1) Ensures that the ArtworkGallery.lookupAll() method returns an
   * empty arraylist when called on an empty tree. (2) Ensures that the
   * ArtworkGallery.lookupAll() method returns an array list which contains all the artwork satisfying
   * the search criteria of year and cost, when called on a non empty artwork tree with one match,
   * and two matches and more. Vary your search criteria such that the lookupAll() method must check
   * in left and right subtrees. (3) Ensures that the ArtworkGallery.lookupAll() method returns an
   * empty arraylist when called on a non-empty artwork tree with no search results found.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookupAll() {
    Artwork a = new Artwork("a", 2000, 1000);
    Artwork b = new Artwork("b", 2000, 2000);
    Artwork c = new Artwork("c", 2000, 3000);
    Artwork d = new Artwork("d", 2000, 2500);
    Artwork e = new Artwork("e", 2000, 500);
    Artwork f = new Artwork("f", 3000, 5000);
    Artwork g = new Artwork("g", 1000, 1200);
    ArtGallery z = new ArtGallery();



    try {
      if (!(z.lookupAll(2000, 5000).isEmpty())) {
        System.out.println("Error: lookUpAll() method does not work");
        return false;
      }
      z.addArtwork(a);
      z.addArtwork(b);
      z.addArtwork(c);
      z.addArtwork(d);
      z.addArtwork(e);
      z.addArtwork(f);
      z.addArtwork(g);
      //System.out.println(z.lookupAll(2000, 5000).toString());
      String expectedOutput =
        "[[(Name: a) (Year: 2000) (Cost: $1000.0)], " + "[(Name: e) (Year: 2000) (Cost: $500.0)], "
          + "[(Name: b) (Year: 2000) (Cost: $2000.0)], "
          + "[(Name: c) (Year: 2000) (Cost: $3000.0)], "
          + "[(Name: d) (Year: 2000) (Cost: $2500.0)]]";

      if (!(z.lookupAll(2000, 5000).toString().equals(expectedOutput))) {
        System.out.println("Error: lookUpAll() method does not work");
        return false;
      }

      if (!(z.lookupAll(2217, 5000).isEmpty())) {
        System.out.println("Error: lookUpAll() method does not work");
        return false;
      }

    } catch (Exception error) {
      System.out.println("Error: lookUpAll() method does not work");
      return false;
    }

    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Checks for the correctness of ArtworkGallery.buyArtwork() method. This test must consider at
   * least 3 test scenarios. (1) Buying artwork that is at leaf node (2) Buying artwork at non-leaf
   * node (3) ensures that the ArtworkGallery.buyArtwork() method throws a NoSuchElementException
   * when called on an artwork that is not present in the BST
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testBuyArtwork() {
    Artwork a = new Artwork("a", 2000, 1000);
    Artwork b = new Artwork("b", 2000, 2000);
    Artwork c = new Artwork("c", 2000, 3000);
    Artwork d = new Artwork("d", 2000, 2500);
    Artwork e = new Artwork("e", 2000, 500);
    Artwork f = new Artwork("f", 3000, 5000);
    Artwork g = new Artwork("g", 1000, 1200);
    ArtGallery z = new ArtGallery();
    z.addArtwork(a);
    z.addArtwork(b);
    z.addArtwork(c);
    z.addArtwork(d);
    z.addArtwork(e);
    z.addArtwork(f);
    z.addArtwork(g);


    try {
      z.buyArtwork("f", 3000, 5000);
      String expectedOutput1 =
        "[(Name: g) (Year: 1000) (Cost: $1200.0)]\n" + "[(Name: e) (Year: 2000) (Cost: $500.0)]\n"
          + "[(Name: a) (Year: 2000) (Cost: $1000.0)]\n"
          + "[(Name: b) (Year: 2000) (Cost: $2000.0)]\n"
          + "[(Name: d) (Year: 2000) (Cost: $2500.0)]\n"
          + "[(Name: c) (Year: 2000) (Cost: $3000.0)]";
      if (z.size() != 6 || !(z.toString().equals(expectedOutput1))) {
        System.out.println("Error: buyArtwork() method does not work");
        return false;
      }

      z.buyArtwork("a", 2000, 1000);

      String expectedOutput2 =
        "[(Name: g) (Year: 1000) (Cost: $1200.0)]\n" + "[(Name: e) (Year: 2000) (Cost: $500.0)]\n"
          + "[(Name: b) (Year: 2000) (Cost: $2000.0)]\n"
          + "[(Name: d) (Year: 2000) (Cost: $2500.0)]\n"
          + "[(Name: c) (Year: 2000) (Cost: $3000.0)]";

      if (z.size() != 5 || !(z.toString().equals(expectedOutput2))) {
        System.out.println("Error: buyArtwork() method does not work");
        return false;
      }

    } catch (Exception error) {
      System.out.println("Error: buyArtwork() method does not work");
      return false;
    }
    try {
      z.buyArtwork("abcd", 2000, 1000);
    } catch (NoSuchElementException expected) {
    } catch (Exception error) {
      System.out.println("Error: buyArtwork() method does not work");
    }

    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Returns false if any of the tester methods defined in this tester class fails.
   *
   * @return false if any of the tester methods defined in this tester class fails, and true if all
   * tests pass
   */
  public static boolean runAllTests() {
    if (!(testAddArtworkToStringSize() || testArtworkCompareToEquals() || testLookup()
      || testHeight() || testGetBestArtwork() || testLookupAll() || testBuyArtwork())) {
      System.out.println("Error: error found by tester");
      return false;
    }
    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Calls the test methods
   *
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testArtworkCompareToEquals(): " + testArtworkCompareToEquals());
    System.out.println("testAddArtworkToStringSize(): " + testAddArtworkToStringSize());
    System.out.println("testLookup(): " + testLookup());
    System.out.println("testHeight(): " + testHeight());
    System.out.println("testGetBestArtwork(): " + testGetBestArtwork());
    System.out.println("testLookupAll(): " + testLookupAll());
    System.out.println("testBuyArtwork(): " + testBuyArtwork());
    System.out.println("runAllTests(): " + runAllTests());
    //
    //
    //    Artwork a = new Artwork("a", 2000, 1000);
    //    Artwork b = new Artwork("b", 2000, 2000);
    //    Artwork c = new Artwork("c", 2000, 3000);
    //    Artwork d = new Artwork("d", 2000, 2500);
    //    Artwork e = new Artwork("e", 2000, 500);
    //    Artwork f = new Artwork("f", 3000, 5000);
    //    Artwork g = new Artwork("g", 1000, 1200);
    //
    //    //    Artwork a = new Artwork("a", 2000, 1000);
    //    //    Artwork b = new Artwork("b", 2000, 2000);
    //    //    Artwork d = new Artwork("d", 2000, 2500);
    //    //    Artwork e = new Artwork("e", 1000, 500);
    //    //    Artwork g = new Artwork("g", 1000, 1200);
    //
    //
    //
    //    ArtGallery z = new ArtGallery();
    //
    //    z.addArtwork(a);
    //    z.addArtwork(b);
    //    z.addArtwork(c);
    //    z.addArtwork(d);
    //    z.addArtwork(e);
    //    z.addArtwork(f);
    //    z.addArtwork(g);
    //
    //    //System.out.println(z.getBestArtwork());
    //    //    System.out.println(z.size());
    //    //System.out.println(z.height());
    //    //System.out.println(z.lookupAll(2000, 5000));
    //    z.buyArtwork("a", 2000, 1000);
    //    // z.buyArtwork("a",2000,1000);
    //
    //    System.out.println(z.toString());
    //
    //    //    System.out.println(z.toString());
    /**
     ArtGallery gallery = new ArtGallery();
     System.out.println("Size: " + gallery.size() + " Height: " + gallery.height() + "\nGallery:");
     System.out.println(gallery);
     gallery.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000));
     gallery.addArtwork(new Artwork("Starry Night, Van Gogh", 1889, 2000));
     System.out.println("==============================================================");
     System.out.println("Size: " + gallery.size() + " Height: " + gallery.height() + "\nGallery:");
     System.out.println(gallery);

     gallery.addArtwork(new Artwork("NightHawks, Hopper", 1942, 4000));
     gallery.addArtwork(new Artwork("Mona Lisa, DaVinci", 1503, 1000));
     gallery.addArtwork(new Artwork("Whistler, Abbott", 1871, 5000));
     gallery.addArtwork(new Artwork("Amazone, Tsalapatanis", 2021, 6080));

     System.out.println("==============================================================");
     System.out.println("Size: " + gallery.size() + " Height: " + gallery.height() + "\nGallery:");
     System.out.println(gallery);
     gallery.addArtwork(new Artwork("Persistence of Memory, Dali", 1931, 7000));
     gallery.addArtwork(new Artwork("Der Schrei, Silber", 2019, 12160));
     gallery.addArtwork(new Artwork("Gothic, Wood", 1930, 6000));
     gallery.addArtwork(new Artwork("For gourmets, Tuzhilkina", 2021, 1280));
     gallery.addArtwork(new Artwork("Cantabrico, Torices", 2021, 3870));

     System.out.println("==============================================================");
     System.out.println("Size: " + gallery.size() + " Height: " + gallery.height() + "\nGallery:");
     System.out.println(gallery);
     try {
     System.out.println("This gallery contains (Mona Lisa, DaVinci, 1503, 1000): "
     + gallery.lookup("Mona Lisa, DaVinci", 1503, 1000));
     System.out.println("This gallery contains (Whistler, Abbott, 1871, 5000): "
     + gallery.lookup("Whistler, Abbott", 1871, 5000));
     System.out.println();
     System.out.println("This gallery contains (Chaplin, Brainwash\", 2020, 9090): "
     + gallery.lookup("Chaplin, Brainwash", 2020, 9090));
     System.out.println();
     System.out.println("Best (greatest) artwork: " + gallery.getBestArtwork());
     System.out.println();
     System.out.println(
     "Lookup query: search for the artworks of 2021 whose costs do not exceed $5000.00:");
     System.out.println(gallery.lookupAll(2021, 5000));
     System.out.println();
     System.out.println(
     "Lookup query: search for the artworks of 2021 whose costs do not exceed $10000.00:");
     System.out.println(gallery.lookupAll(2021, 10000));
     System.out.println();
     System.out.println("Buy \"Der Schrei, Silber\", 2019, 12160:");
     gallery.buyArtwork("Der Schrei, Silber", 2019, 12160);
     System.out.println("Size: " + gallery.size() + " Height: " + gallery.height() + "\nGallery:");
     System.out.println(gallery);
     System.out.println();
     System.out.println("Buy \"Mona Lisa, DaVinci\", 1503, 1000:");
     gallery.buyArtwork("Mona Lisa, DaVinci", 1503, 1000);
     System.out.println("Size: " + gallery.size() + " Height: " + gallery.height() + "\nGallery:");
     System.out.println(gallery);
     System.out.println();
     System.out.println("Buy \"Guernica, Picasso\", 1937, 3000:");
     gallery.buyArtwork("Guernica, Picasso", 1937, 3000);
     System.out.println("Size: " + gallery.size() + " Height: " + gallery.height() + "\nGallery:");
     System.out.println(gallery);
     System.out.println();
     System.out.println("Buy \"Mona Lisa, DaVinci\", 1503, 1000:");
     gallery.buyArtwork("Mona Lisa, DaVinci", 1503, 1000);
     } catch (NoSuchElementException e) {
     System.out.println(e.getMessage());
     }
     **/

  }

}
