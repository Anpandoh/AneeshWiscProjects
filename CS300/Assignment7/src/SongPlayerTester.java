//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    SongPlayerProject
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
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    SongPlayerProject
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



import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements unit test methods to check the correctness of Song, LinkedNode, SongPlayer
 * ForwardSongIterator and BackwardSongIterator classes in P07 Iterable Song Player assignment.
 */
public class SongPlayerTester {
  /**
   * This method test and make use of the Song constructor, an accessor (getter) method,
   * overridden method toString() and equal() method defined in the Song class.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testSong() {
    Song correct = new Song("Uptown Funk", "Bruno Mars", "4:00");
    // 2. Test getSongName
    if (!correct.getSongName().equals("Uptown Funk")) {
      System.out.println("ERROR: getSongName() does not provide expected result");
      return false;
    }

    // Test getArtist
    if (!correct.getArtist().equals("Bruno Mars")) {
      System.out.println("ERROR: getArtist() does not provide expected result");
      return false;
    }

    // Test getDuration
    if (!correct.getDuration().equals("4:00")) {
      System.out.println("ERROR: getDuration() does not provide expected result");
      return false;
    }
    try {
      Song incorrectDuration = new Song("abcd", "xyz", "123:01");
      System.out.println("ERROR: getDuration() does not provide expected error");
      return false;
    } catch (IllegalArgumentException expected) {
    } catch (Exception e) {
      System.out.println("ERROR: getDuration() does not provide expected error");
    }

    // Test toString
    if (!correct.toString().equals("Uptown Funk---Bruno Mars---4:00")) {
      System.out.println("ERROR: toString() does not provide expected result");
      return false;
    }

    // Test equals

    Song expected = new Song("Uptown Funk", "Bruno Mars", "4:00");
    if (!correct.equals(expected)) {
      System.out.println("ERROR: equals() does not correctly compare songs\n");
      return false;
    }
    return true;
  }

  /**
   * This method test and make use of the LinkedNode constructor, an accessor
   * (getter) method, and a mutator (setter) method defined in the LinkedCart class.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLinkedNode() {
    Song uptownFunk = new Song("uptownFunk", "Bruno Mars", "04:59");
    Song abcd = new Song("abcd", "xyz", "04:59");
    Song cool = new Song("cool", "123", "04:59");

    LinkedNode<Song> head = new LinkedNode<>(null, uptownFunk, null);
    LinkedNode<Song> node1 = new LinkedNode<>(null, abcd, null);
    LinkedNode<Song> node2 = new LinkedNode<>(null, cool, null);

    head.setNext(node1);
    node1.setNext(node2);
    node1.setPrev(head);
    node2.setPrev(node1);


    // Test getPrev and setPrev;
    if (!node1.getPrev().equals(head)) {
      System.out.println("ERROR: getPrev() does not provide correct output");
      return false;
    }

    // Test getNext and setNext;
    if (!node1.getNext().equals(node2)) {
      System.out.println("ERROR: getNext() does not provide correct output");
      return false;
    }


    // Test getData();
    if (!node1.getData().equals(abcd)) {
      System.out.println("ERROR: getData() does not correctly access node");
      return false;
    }

    return true;
  }

  /**
   * This method checks for the correctness of addFirst(), addLast() and add(int index)
   * method in SongPlayer class
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testSongPlayerAdd() {

    SongPlayer songList = new SongPlayer();
    songList.addFirst(new Song("Mojito", "Jay Chou", "3:05"));
    songList.addLast(new Song("Secret", "Jay Chou", "3:05"));
    songList.addFirst(new Song("abcd", "Jay Chou", "3:05"));
    songList.addFirst(new Song("xyz", "Jay Chou", "3:05"));
    songList.add(1, new Song("efg", "Jay Chou", "3:05"));
    //System.out.println(songList.get(0));

    String expectedOutput =
      "xyz---Jay Chou---3:05\n" + "efg---Jay Chou---3:05\n" + "abcd---Jay Chou---3:05\n"
        + "Mojito---Jay Chou---3:05\n" + "Secret---Jay Chou---3:05\n";

    //testing add methods

    if (!(songList.play().equals(expectedOutput))) {
      System.out.println("ERROR: add()/addFirst()/addLast() does not correctly add node");
      return false;
    }

    //testing add methods exceptions

    try {
      songList.addFirst(null);
      System.out.println("ERROR: addFirst() does not throw correct exception");
      return false;
    } catch (NullPointerException e) {
    } catch (Exception e) {
      System.out.println("ERROR: addFirst() does not throw correct exception");
      return false;
    }

    try {
      songList.addLast(null);
      System.out.println("ERROR: addLast() does not throw correct exception");
      return false;
    } catch (NullPointerException e) {
    } catch (Exception e) {
      System.out.println("ERROR: addLast() does not throw correct exception");
      return false;
    }

    try {
      songList.add(1, null);
      System.out.println("ERROR: add() does not throw correct exception with null");
      return false;
    } catch (NullPointerException e) {
    } catch (Exception e) {
      System.out.println("ERROR: add() does not throw correct exception with null");
      return false;
    }

    try {
      songList.add(10, new Song("aereer", "Jay Chou", "3:05"));
      System.out.println("ERROR: add() does not throw correct exception");
      return false;
    } catch (IndexOutOfBoundsException e) {
    } catch (Exception e) {
      System.out.println("ERROR: add() does not throw correct exception");
      return false;
    }

    return true;
  }



  /**
   * This method checks for the correctness of getFirst(), getLast() and get(int index)
   * method in SongPlayer class
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testSongPlayerGet() {
    SongPlayer songList = new SongPlayer();
    songList.addFirst(new Song("Mojito", "Jay Chou", "3:05"));
    songList.addLast(new Song("Secret", "Jay Chou", "3:05"));
    songList.addFirst(new Song("abcd", "Jay Chou", "3:05"));
    songList.addFirst(new Song("xyz", "Jay Chou", "3:05"));
    songList.add(1, new Song("efg", "Jay Chou", "3:05"));


    //Test getFirst()
    String expectedOuput = "xyz---Jay Chou---3:05";
    if (!(songList.getFirst().toString().equals(expectedOuput))) {
      System.out.println("ERROR: getFirst() does not correctly get Node");
      return false;
    }


    //Test getLast()
    String expectedOuput1 = "Secret---Jay Chou---3:05";
    if (!(songList.getLast().toString().equals(expectedOuput1))) {
      System.out.println("ERROR: getLast() does not correctly get Node");
      return false;
    }

    //Test get()
    String expectedOuput2 = "abcd---Jay Chou---3:05";
    if (!(songList.get(2).toString().equals(expectedOuput2))) {
      System.out.println("ERROR: get() does not correctly get node");
      return false;
    }


    //Testing the get methods exceptions
    SongPlayer exceptionList = new SongPlayer();

    try {
      exceptionList.getFirst();
      System.out.println("ERROR: getFirst() does not throw correct exception with null");
      return false;
    } catch (NoSuchElementException e) {
    } catch (Exception e) {
      System.out.println("ERROR: getLast() does not throw correct exception with null");
      return false;
    }

    try {
      exceptionList.getLast();
      System.out.println("ERROR: getLast() does not throw correct exception with null");
      return false;
    } catch (NoSuchElementException e) {
    } catch (Exception e) {
      System.out.println("ERROR: getLast() does not throw correct exception with null");
      return false;
    }

    try {
      exceptionList.get(1);
      System.out.println("ERROR: get() does not throw correct exception with null");
      return false;
    } catch (IndexOutOfBoundsException e) {
    } catch (Exception e) {
      System.out.println("ERROR: get() does not throw correct exception with null");
      return false;
    }

    return true;
  }

  /**
   * This method checks for the correctness of removeFirst(), removeLast() and remove(int index)
   * method in SongPlayer class
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testSongPlayerRemove() {
    SongPlayer songList = new SongPlayer();
    songList.addFirst(new Song("Mojito", "Jay Chou", "3:05"));
    songList.addLast(new Song("Secret", "Jay Chou", "3:05"));
    songList.addFirst(new Song("abcd", "Jay Chou", "3:05"));
    songList.addFirst(new Song("xyz", "Jay Chou", "3:05"));
    songList.addFirst(new Song("fedg", "Jay Chou", "3:05"));
    songList.addFirst(new Song("praise", "Jay Chou", "3:05"));
    songList.add(1, new Song("efg", "Jay Chou", "3:05"));


    songList.removeFirst();
    songList.removeLast();
    songList.remove(2);


    String expectedOutput =
      "efg---Jay Chou---3:05\n" + "fedg---Jay Chou---3:05\n" + "abcd---Jay Chou---3:05\n"
        + "Mojito---Jay Chou---3:05\n";

    //testing remove methods

    if (!(songList.play().equals(expectedOutput))) {
      System.out.println(
        "ERROR: remove()/removeFirst()/removeLast() does not correctly remove nodes");
      return false;
    }


    //testing remove methods exceptions

    SongPlayer exceptionList = new SongPlayer();

    try {
      exceptionList.removeFirst();
      System.out.println("ERROR: removeFirst() does not throw correct exception");
      return false;
    } catch (NoSuchElementException e) {
    } catch (Exception e) {
      System.out.println("ERROR: removeFirst() does not throw correct exception");
      return false;
    }

    try {
      exceptionList.removeLast();
      System.out.println("ERROR: removeLast() does not throw correct exception");
      return false;
    } catch (NoSuchElementException e) {
    } catch (Exception e) {
      System.out.println("ERROR: removeLast() does not throw correct exception");
      return false;
    }

    try {
      exceptionList.remove(10);
      System.out.println(
        "ERROR: remove() does not throw correct exception for index being out list");
      return false;
    } catch (IndexOutOfBoundsException e) {
    } catch (Exception e) {
      System.out.println(
        "ERROR: remove() does not throw correct exception for index being out list");
      return false;
    }

    return true;
  }

  /**
   * This method checks for the correctness of iterator(), switchPlayingDirection() and String play()
   * method in SongPlayer class
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testSongPlayerIterator() {

    {
      SongPlayer songList = new SongPlayer();
      Song uptownFunk = new Song("uptownFunk", "Bruno Mars", "04:59");
      Song abcd = new Song("abcd", "xyz", "04:59");
      Song cool = new Song("cool", "123", "04:59");
      songList.addFirst(uptownFunk);
      songList.addLast(abcd);
      songList.addLast(cool);

      //checks iterator in forward direction
      Iterator<Song> it = songList.iterator();
      if (!it.next().equals(uptownFunk) || !it.next().equals(abcd) || !it.next().equals(cool)) {
        System.out.println("ERROR: with iterator");
        return false;
      }

      //checks iterator in backward direction
      songList.switchPlayingDirection();
      Iterator backwardIt = songList.iterator();
      if (!backwardIt.next().equals(cool) || !backwardIt.next().equals(abcd) || !backwardIt.next()
        .equals(uptownFunk)) {
        System.out.println("ERROR: with iterator in backwards direction");
        return false;
      }
    }

    SongPlayer songList = new SongPlayer();
    songList.addFirst(new Song("Mojito", "Jay Chou", "3:05"));
    songList.addLast(new Song("Secret", "Jay Chou", "3:05"));
    songList.addFirst(new Song("abcd", "Jay Chou", "3:05"));
    songList.addFirst(new Song("xyz", "Jay Chou", "3:05"));
    songList.add(1, new Song("efg", "Jay Chou", "3:05"));

    String expectedOutput =
      "xyz---Jay Chou---3:05\n" + "efg---Jay Chou---3:05\n" + "abcd---Jay Chou---3:05\n"
        + "Mojito---Jay Chou---3:05\n" + "Secret---Jay Chou---3:05\n";

    //testing play()

    if (!(songList.play().equals(expectedOutput))) {
      System.out.println("ERROR: play method does not correctly add node");
      return false;
    }

    //testing switchPlayingDirection() and play()

    try {
      songList.switchPlayingDirection();
      String expectedOutput1 =
        "Secret---Jay Chou---3:05\n" + "Mojito---Jay Chou---3:05\n" + "abcd---Jay Chou---3:05\n"
          + "efg---Jay Chou---3:05\n" + "xyz---Jay Chou---3:05\n";

      if (!(songList.play().equals(expectedOutput1))) {
        System.out.println(
          "ERROR: play method not working when switched backwards does not correctly add node");
        return false;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;

    }

    return true;
  }

  /**
   * This method checks for the correctness of contains(Object song), clear(),
   * isEmpty()and size() method in SongPlayer class
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testSongPlayerCommonMethod() {

    SongPlayer songList = new SongPlayer();
    Song uptownFunk = new Song("uptownFunk", "Bruno Mars", "04:59");
    Song abcd = new Song("abcd", "xyz", "04:59");
    Song cool = new Song("cool", "123", "04:59");
    songList.addFirst(uptownFunk);
    songList.addLast(abcd);
    songList.addLast(cool);

    if (!songList.contains(uptownFunk) || !songList.contains(abcd) || !songList.contains(uptownFunk)
      || songList.size() != 3) {
      System.out.println("ERROR: with contains method");
      return false;
    }

    songList.clear();
    if (!songList.isEmpty() || songList.size() != 0) {
      System.out.println("ERROR: with clear method");
      return false;
    }

    return true;
  }

  /**
   * This method checks for the correctness of ForwardSongIterator class
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testForwardSongIterator() {
    Song uptownFunk = new Song("uptownFunk", "Bruno Mars", "04:59");
    Song abcd = new Song("abcd", "xyz", "04:59");
    Song cool = new Song("cool", "123", "04:59");

    LinkedNode<Song> head = new LinkedNode<>(null, uptownFunk, null);
    LinkedNode<Song> node1 = new LinkedNode<>(null, abcd, null);
    LinkedNode<Song> node2 = new LinkedNode<>(null, cool, null);

    head.setNext(node1);
    node1.setNext(node2);
    node1.setPrev(head);
    node2.setPrev(node1);


    ForwardSongIterator test = new ForwardSongIterator(head);

    // Test hasNext()
    if (!test.hasNext() == true) {
      System.out.println("ERROR: hasNext does not provide correct output");
      return false;
    }

    // Test next() for head
    if (!test.next().toString().equals("uptownFunk---Bruno Mars---04:59")) {
      System.out.println("ERROR: next does not provide correct output for head");
      return false;
    }


    // Test next() for !head
    if (!test.next().toString().equals("abcd---xyz---04:59")) {
      System.out.println("ERROR: next does not provide correct output when not head");
      return false;
    }


    return true;
  }

  /**
   * This method checks for the correctness of BackwardSongIterator class
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testBackwardSongIterator() {
    Song uptownFunk = new Song("uptownFunk", "Bruno Mars", "04:59");
    Song abcd = new Song("abcd", "xyz", "04:59");
    Song cool = new Song("cool", "123", "04:59");

    LinkedNode<Song> node1 = new LinkedNode<>(null, uptownFunk, null);
    LinkedNode<Song> node2 = new LinkedNode<>(null, abcd, null);
    LinkedNode<Song> tail = new LinkedNode<>(null, cool, null);

    node1.setNext(node2);
    node2.setNext(tail);
    node2.setPrev(node1);
    tail.setPrev(node2);


    BackwardSongIterator test = new BackwardSongIterator(tail);

    // Test hasNext()
    if (!test.hasNext() == true) {
      System.out.println("ERROR: hasNext() does not provide correct output");
      return false;
    }

    // Test next() for tail
    if (!test.next().toString().equals("cool---123---04:59")) {
      System.out.println("ERROR: next() does not provide correct output for tail");
      return false;
    }


    // Test next() for !head
    if (!test.next().toString().equals("abcd---xyz---04:59")) {
      System.out.println("ERROR: next() does not provide correct output for not tail");
      return false;
    }

    return true;
  }

  /**
   * This method calls all the test methods defined and implemented in your SongPlayerTester
   * class.
   *
   * @return true if all the test methods defined in this class pass, and false otherwise.
   */
  public static boolean runAllTests() {
    if (testSongPlayerRemove() && testSong() && testLinkedNode() && testForwardSongIterator()
      && testSongPlayerAdd() && testBackwardSongIterator() && testSongPlayerCommonMethod()
      && testSongPlayerGet() && testSongPlayerIterator()) {
      return true;
    }
    System.out.println("ERROR: runAllTests() fails");

    return false;
  }

  /**
   * Driver method defined in this SongPlayerTester class
   *
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    //    //System.out.println(testSong());
    //    //testForwardSongIterator();
    //    testSongPlayerRemove();
    //    testSong();
    //    testLinkedNode();
    //    testForwardSongIterator();
    //    testSongPlayerAdd();
    //    testBackwardSongIterator();
    //    testSongPlayerGet();
    //    testSongPlayerIterator();
    //    testSongPlayerCommonMethod();
    //    testSongPlayerIterator();
    System.out.println("All tests pass: " + runAllTests());

    //    SongPlayer songList = new SongPlayer();
    //    songList.addFirst(new Song("Mojito", "Jay Chou", "3:05"));
    //    songList.addFirst(new Song("Secret", "Jay Chou", "4:56"));
    //    songList.addFirst(new Song("Clear Day", "Jay Chou", "4:59"));
    //    songList.addFirst(new Song("Dragon Fist", "Jay Chou", "4:32"));
    //    songList.addFirst(new Song("Out of Time", "The Weeknd", "3:34"));
    //    songList.addLast(new Song("StarBoy", "The Weeknd", "3:50"));
    //    songList.addLast(new Song("Save Your Tears", "The Weeknd", "3:35"));
    //    songList.add(1, new Song("Simple Love", "Jay Chou", "4:30"));
    //    songList.add(2, new Song("Superman Can’t Fly", "Jay Chou", "4:59"));
    //    songList.addLast(new Song("Oh My God", "Adele", "3:45"));
    //    songList.addLast(new Song("Levitating", "Dua Lipa", "3:23"));
    //    songList.add(6, new Song("Be Kind", "Marshmello & Halsey", "2:53"));
    //    System.out.println("---------------- Play Forward -----------------");
    //    System.out.println(songList.play());
    //    System.out.println("------------------------------------------------");
    //    System.out.println("songList.remove(6) -- Be Kind -- removed\n"
    //      + "songList.removeFirst(); -- Out of Time -- removed\n"
    //      + "songList.removeLast(); -- Levitating -- removed\n");
    //    songList.remove(6);
    //    songList.removeFirst();
    //    songList.removeLast();
    //    System.out.println("---------------- Play Forward -----------------");
    //    System.out.println(songList.play());
    //    System.out.println("------------------------------------------------");
    //    Song oneSong = new Song("Mojito", "Jay Chou", "3:05");
    //    System.out.print("songList.contains(new Song(\"Mojito\", \"Jay Chou\", \"3:05\"): ");
    //    System.out.println(songList.contains(oneSong));
    //    System.out.println();
    //    System.out.println("songList.size(): " + songList.size());
    //    System.out.println();
    //    System.out.print("songList.contains(new Song(\"Be Kind\", \"Marshmello & Halsey\", \"2:53\"): ");
    //    oneSong = new Song("Be Kind", "Marshmello & Halsey", "2:53");
    //    System.out.println(songList.contains(oneSong));
    //    System.out.println();
    //    System.out.println("---------------- Play Forward -----------------");
    //    System.out.println(songList.play());
    //    System.out.println();
    //    System.out.println("---------------- Play Backward -----------------");
    //    songList.switchPlayingDirection();
    //    System.out.println(songList.play());
    //    System.out.println("------------------------------------------------");
  }
}
