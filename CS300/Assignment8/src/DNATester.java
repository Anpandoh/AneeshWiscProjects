//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    DNA Encoder
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
 * Test methods to verify your implementation of the methods for P08.
 */
public class DNATester {


  /**
   * Tests the enqueue() and dequeue() methods
   *
   * @return true if and only if the methods works correctly
   */
  public static boolean testEnqueueDequeue() {

    //Test Enqueue

    LinkedQueue<String> testQueue = new LinkedQueue<>();

    try {
      testQueue.enqueue("a");
      testQueue.enqueue("b");
      testQueue.enqueue("c");

      String expectedOutput = "a b c ";

      if (!(testQueue.toString().equals(expectedOutput))) {
        System.out.println("Error: Enqueue function has an erro");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error: Enqueue function has an erro");
      return false;
    }

    //Test Dequeue
    try {
      testQueue.dequeue();
      testQueue.dequeue();

      String expectedOutput = "c ";
      if (!(testQueue.toString().equals(expectedOutput))) {
        System.out.println("Error: Dequeue function has an error");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error: Dequeue function has an error");
      return false;
    }


    //Test Dequeue on empty queue throwing correct error
    try {
      LinkedQueue<String> testQueue2 = new LinkedQueue<>();
      testQueue2.dequeue();
      System.out.println("Error: Dequeue function has an error");
      return false;
    } catch (NoSuchElementException expected) {
    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * Tests the size() and isEmpty() methods
   *
   * @return true if and only if the methods works correctly
   */
  public static boolean testQueueSize() {

    //testSize
    try {
      LinkedQueue<String> testQueue = new LinkedQueue<>();

      testQueue.enqueue("a");
      testQueue.enqueue("b");
      testQueue.enqueue("c");
      testQueue.enqueue("d");
      testQueue.dequeue();
      testQueue.dequeue();

      int expectedOutput = 2;
      if (testQueue.size() != expectedOutput) {
        System.out.println("Error: size function has an error");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error: size function has an error");
      return false;
    }

    //testIsEmpty
    try {
      LinkedQueue<String> testQueue2 = new LinkedQueue<>();

      if (!(testQueue2.isEmpty())) {
        System.out.println("Error: isEmpty function has an error");
        return false;
      }

      testQueue2.enqueue("a");

      if (testQueue2.isEmpty()) {
        System.out.println("Error: isEmpty function has an error");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error: isEmpty function has an error");
      return false;
    }


    return true;
  }



  /**
   * Tests the transcribeDNA() method
   *
   * @return true if and only if the method works correctly
   */
  public static boolean testTranscribeDNA() {
    DNA testDNA = new DNA("GGAGTCAGTTAAGCGACCGGGACATACTGTCTTGGTAATCTCCGAGCTAGAAAGTCTCTG");
    String mRNA = "CCUCAGUCAAUUCGCUGGCCCUGUAUGACAGAACCAUUAGAGGCUCGAUCUUUCAGAGAC";
    System.out.println(testDNA.transcribeDNA().toString());
    if (testDNA.transcribeDNA().toString().replaceAll(" ", "").equals(mRNA)) {
      return true;
    }
    return false;
  }

  /**
   * Tests the translateDNA() method
   *
   * @return true if and only if the method works correctly
   */
  public static boolean testTranslateDNA() {
    DNA testDNA = new DNA("GGAGTCAGTTAAGCGACCGGGACATACTGTCTTGGTAATCTCCGAGCTAGAAAGTCTCTG");
    System.out.println(testDNA.translateDNA().toString());
    if (testDNA.translateDNA().toString().replaceAll(" ", "").equals("PQSIRWPCMTEPLEARSFRD")) {
      return true;
    }
    return false;
  }

  /**
   * Tests the mRNATranslate() method
   *
   * @return true if and only if the method works correctly
   */
  public static boolean testMRNATranslate() {

    try {
      //test correctly translating mRNA to protein
      DNA testDNA = new DNA("GGAGTCAGTTAAGCGACCGGGACATACTGTCTTGGTAATCTCCGAGCTAGAAAGTCTCTG");
      System.out.println(testDNA.mRNATranslate(testDNA.transcribeDNA()));
      if (!(testDNA.mRNATranslate(testDNA.transcribeDNA()).toString().replaceAll(" ", "")
        .equals("PQSIRWPCMTEPLEARSFRD"))) {
        return false;
      }

      //test correctly translating mRNA to protein with STOP Codon

      DNA testDNA2 = new DNA("GGAATTGTCAGTTAAGCGACCGGGACATACTGTCTTGGTAATCTCCGAGCTAGAAAG");

      if (!(testDNA2.mRNATranslate(testDNA2.transcribeDNA()).toString().replaceAll(" ", "")
        .equals("P"))) {
        return false;
      }

      //test correctly translating mRNA to protein ending with less than 3 nucleotides

      DNA testDNA3 = new DNA("GGAGTCAGTTAAGCGACCGGGACATACTGTCTTGGTAATCTCCGAGCTAGAAAGTCTCT");

      if (!(testDNA3.mRNATranslate(testDNA3.transcribeDNA()).toString().replaceAll(" ", "")
        .equals("PQSIRWPCMTEPLEARSFR"))) {
        return false;
      }

    } catch (Exception e) {
      System.out.println("Error: mRNATranslate function has an error");
      return false;
    }

    return true;
  }


  /**
   * Main method - use this to run your test methods and output the results (ungraded)
   *
   * @param args unused
   */
  public static void main(String[] args) {


    System.out.println("enqueueDequeue: " + testEnqueueDequeue());
    System.out.println("size/IsEmpty: " + testQueueSize());
    System.out.println("transcribeDNA: " + testTranscribeDNA());
    System.out.println("translateDNA: " + testTranslateDNA());
    System.out.println("mRNA: " + testMRNATranslate());


  }

}
