// --== CS400 Project One File Header ==--
// Name: Aneesh Pandoh
// CSL Username: pandoh
// Email: pandoh@wisc.edu
// Lecture #: 003 @2:25pm
// Notes to Grader: N/A


import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashtableMapTests {

  public static void main(String[] args) {
    System.out.println(test1());
    System.out.println(test2());
    System.out.println(test3());
    System.out.println(test4());
    System.out.println(test5());
    System.out.println(test6());
  }

  /**
   * Test all getters and constructors
   *
   * @return true if test cases pass, false otherwise
   */
  public static boolean test1() {
    try {
      HashtableMap<Integer, Integer> test = new HashtableMap<>();
      if (test.size() != 0 || test.hashArray.length != 15) {
        System.out.println("error in size method or constructor");
        return false;
      }

      HashtableMap<Integer, Integer> test1 = new HashtableMap<>(10);
      if (test.size() != 0 || test1.hashArray.length != 10) {
        System.out.println("error in size method or constructor");
        return false;
      }



    } catch (Exception unexpectedError) {
      System.out.println(unexpectedError.getMessage());
      return false;
    }
    return true;
  }


  /**
   * Tests the put method
   *
   * @return true if test cases pass, false otherwise
   */
  public static boolean test2() {
    try {
      HashtableMap<Integer, Integer> test = new HashtableMap<>();
      test.put(1, 2);
      test.put(3, 4);
      test.put(6, 5);
      if (test.size() != 3 || !test.get(6).equals(5)) {
        System.out.println("Error in size method, put method or get method");
        return false;
      }
      test.put(1, 7);

      // makes sure only first value stored
      if (!test.get(1).equals(2)) {
        System.out.println("get method returns the wrong value");
        return false;
      }


    } catch (Exception unexpectedError) {
      System.out.println(unexpectedError.getMessage());
      return false;
    }
    try {
      HashtableMap<Integer, Integer> test = new HashtableMap<>();
      test.get(null);
      System.out.println("get method did not properly throw NoSuchElementException error");
      return false;
    } catch (NoSuchElementException e) {
    } catch (Exception unexpectedError) {
      System.out.println(unexpectedError.getMessage());
      return false;
    }



    return true;
  }

  /**
   * Tests the remove method
   *
   * @return true if test cases pass, false otherwise
   */
  public static boolean test3() {

    HashtableMap<Integer, Integer> test = new HashtableMap<>();
    test.put(1, 0);
    test.put(3, 4);
    test.put(6, 5);
    try {
      //Remove key 6
      if (test.size() != 3 || !test.remove(6).equals(5)) {
        System.out.println("Error in size method, put method or remove method");
        return false;
      }
    } catch (Exception unexpectedError) {
      System.out.println(unexpectedError.getMessage());
      return false;
    }

    //try to get key 6 which is removed
    try {
      test.remove(6);
      if (test.size() != 2) {
        System.out.println("Error in size method, or remove method");
        return false;
      }
      test.get(6);
      System.out.println("get method did not properly throw NoSuchElement Exception error");
      return false;
    } catch (NoSuchElementException expectedError) {
    } catch (Exception unexpectedError) {
      System.out.println(unexpectedError.getMessage());
      return false;
    }

    //tries removing a key then adding same key
    try {
      test.remove(1);
      test.put(1, 10);
      if (test.size() != 2 || !test.get(1).equals(10)) {
        System.out.println("Error in size method, or remove method");
        return false;
      }
    } catch (Exception unexpectedError) {
      System.out.println(unexpectedError.getMessage());
      return false;
    }
    //try removing key that is not in array
    try {
      if (test.remove(33) != null) {
        System.out.println("Error in null method");
        return false;
      }
    } catch (Exception unexpectedError) {
      System.out.println(unexpectedError.getMessage());
      return false;
    }


    return true;
  }

  /**
   * Tests clear and contains key
   *
   * @return true if test cases pass, false otherwise
   */
  public static boolean test4() {

    HashtableMap<Integer, Integer> test = new HashtableMap<>();
    test.put(1, 2);
    test.put(3, 4);
    test.put(6, 5);

    try {
      if (!test.containsKey(3)) {
        System.out.println("contains method did not properly return false");
        return false;
      }

      if (test.size() != 3) {
        System.out.println("Wrong size before clear");
        return false;
      }
      test.clear();
      if (test.size() != 0) {
        System.out.println("Error in size method, or clear method");
        return false;
      }
      if (test.containsKey(3)) {
        System.out.println("contains method did not properly return false");
        return false;
      }

    } catch (NoSuchElementException expectedError) {
    } catch (Exception unexpectedError) {
      System.out.println(unexpectedError.getMessage());
      return false;
    }


    return true;
  }


  /**
   * Tests if the function rehashes correctly
   *
   * @return true if test cases pass, false otherwise
   */
  public static boolean test5() {
    HashtableMap<Integer, Integer> test = new HashtableMap<>(3);
    test.put(1, 2);
    test.put(3, 4);
    test.put(6, 5);


    test.put(4, 1000);

    if (!test.get(1).equals(2)) {
      System.out.println("did not rehash properly");
      return false;
    }
    if (!test.get(3).equals(4)) {
      System.out.println("did not rehash properly");
      return false;
    }
    if (!test.get(6).equals(5)) {
      System.out.println("did not rehash properly");
      return false;
    }
    if (!test.get(4).equals(1000)) {
      System.out.println("did not rehash properly");
      return false;
    }


    if (test.size() != 4) {
      System.out.println("did not rehash properly");
      return false;
    }

    if (test.hashArray.length != 6) {
      System.out.println("did not rehash properly");
      return false;
    }

    return true;
  }

  public static boolean test6() {
    IterableHashtableMap<Integer, Integer> test = new IterableHashtableMap<>(3);

    test.put(5, 2);
    test.put(3, 4);
    test.put(6, 5);
    test.put(4, 1000);

    for (Integer integer : test) {
      System.out.println(integer);
    }

//    Iterator iterator = test.iterator();
//
//    while(iterator.hasNext()) {
//      System.out.println(iterator.next());
//    }

    return true;
  }



}
