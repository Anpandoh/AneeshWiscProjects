///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           TestInputMethodsZylab
// Course:          CS200 Winter 2021
//
// Author:          Aneesh Pandoh
// Email:           pandoh@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// Source or Recipient; Jim Williams and Devesh Shah for template
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////


//import java.util.ArrayList;

import java.util.Scanner;


/**
 * This contains testing methods for the InputMethods class.
 *
 * @author Jim Williams
 * @author Devesh Shah
 * @author Aneesh Pandoh
 */
public class TestInputMethods {

    /**
     * This main method runs the selected tests.  Comment out a test if you don't want it to run.
     *
     * @param args unused
     */
    public static void main(String[] args) {

        //Scanner scnr = new Scanner(System.in);
        //InputMethods method = new InputMethods();
        //System.out.println(method.getValidInt(scnr, "What is your integer?", 5, 7));
        testGetValidInt();
    }

    /**
     * Tests that the getValidInt method handles all the cases described in the assignment.
     */
    private static void testGetValidInt() {
        boolean error = false;


        //Test Case 1, testing if 9 is valid between 5 and 10
        { //creates a new scope so that the variables declared within it don't affect others in
            // this same method.

            //simulates a user typing 9 then Enter.
            Scanner input = new Scanner("9\n");

            //the value expected to be returned by getValidInt for this test case.
            int expected = 9;

            int actual = InputMethods.getValidInt(input,
                    "Enter a number between 5 and 10:", 5, 10);

            //if the actual value returned doesn't equal the expected then this is an error.
            if (actual != expected) {
                error = true;

                //a message describing the error so that the programmer can find this test and
                //see what the values were.
                System.out.println("testGetValidInt 1) Error, expected: " + expected + ", actual" +
                        ":" + " " + actual);
            }
        }


        //Test Case 2, test if scanner = null
        { //creates a new scope so that the variables declared within it don't affect others in
            // this same method.

            //Scanner variable is null
            Scanner input = null;

            //the value expected to be returned by getValidInt for this test case.
            int expected = -1;

            int actual = InputMethods.getValidInt(input,
                    "Enter a number between 5 and 10:", 5, 10);

            //if the actual value returned doesn't equal the expected then this is an error.
            if (actual != expected) {
                error = true;

                //a message describing the error so that the programmer can find this test and
                //see what the values were.
                System.out.println("testGetValidInt 1) Error, expected: " + expected + ", actual" +
                        ":" + " " + actual);
            }
        }

        //Test Case 3, if lowerBound is greater than upperBound
        { //creates a new scope so that the variables declared within it don't affect others in
            // this same method.

            //simulates a user typing 9 then Enter.
            Scanner input = new Scanner("9\n");

            //the value expected to be returned by getValidInt for this test case.
            int expected = -2;

            int actual = InputMethods.getValidInt(input,
                    "Enter a number between 10 and 5:", 10, 5);

            //if the actual value returned doesn't equal the expected then this is an error.
            if (actual != expected) {
                error = true;

                //a message describing the error so that the programmer can find this test and
                //see what the values were.
                System.out.println("testGetValidInt 1) Error, expected: " + expected + ", actual" +
                        ":" + " " + actual);
            }
        }

        //Test Case 4, check if only the first token is user and that the minimum valid value is
        // accepted
        { //creates a new scope so that the variables declared within it don't affect others in
            // this same method.

            //simulates a user typing hi then 9 then Enter then 6.
            Scanner input = new Scanner("hi 9\n 6");

            //the value expected to be returned by getValidInt for this test case.
            int expected = 6;

            int actual = InputMethods.getValidInt(input,
                    "Enter a number between 5 and 10:", 5, 10);

            //if the actual value returned doesn't equal the expected then this is an error.
            if (actual != expected) {
                error = true;

                //a message describing the error so that the programmer can find this test and
                //see what the values were.
                System.out.println("testGetValidInt 1) Error, expected: " + expected + ", actual" +
                        ":" + " " + actual);
            }
        }


        //Test Case 5, check that the first line starts with a word that is ignored and returns a
        // valid integer, the maximum valid value, at the beginning of the second line.
        { //creates a new scope so that the variables declared within it don't affect others in
            // this same method.

            //simulates a user typing hi then Enter then 10 then Enter.
            Scanner input = new Scanner("hi\n" + "10\n");

            //the value expected to be returned by getValidInt for this test case.
            int expected = 10;

            int actual = InputMethods.getValidInt(input,
                    "Enter a number between 5 and 10:", 5, 10);

            //if the actual value returned doesn't equal the expected then this is an error.
            if (actual != expected) {
                error = true;

                //a message describing the error so that the programmer can find this test and
                //see what the values were.
                System.out.println("testGetValidInt 1) Error, expected: " + expected + ", actual" +
                        ":" + " " + actual);
            }

        }

        //Test Case 6, check that the first token on the third line is the first valid integer and
        // is within the range of valid values but not an edge case.
        { //creates a new scope so that the variables declared within it don't affect others in
            // this same method.

            //simulates a user typing 11 then Enter then hi then Enter then 9 then Enter.
            Scanner input = new Scanner("11\n" + "hi\n" + "9\n");

            //the value expected to be returned by getValidInt for this test case.
            int expected = 9;

            int actual = InputMethods.getValidInt(input,
                    "Enter a number between 5 and 10:", 5, 10);

            //if the actual value returned doesn't equal the expected then this is an error.
            if (actual != expected) {
                error = true;

                //a message describing the error so that the programmer can find this test and
                //see what the values were.
                System.out.println("testGetValidInt 1) Error, expected: " + expected + ", actual" +
                        ":" + " " + actual);
            }

        }

        //Test Case 7, check that when the first tokens on the first three lines are not valid,
        // using values just outside acceptable values, then the appropriate value is returned.
        { //creates a new scope so that the variables declared within it don't affect others in
            // this same method.

            //simulates a user typing 11 then Enter then 5 then Enter then 12 then Enter
            Scanner input = new Scanner("11\n" + "5\n" + "12\n");

            //the value expected to be returned by getValidInt for this test case.
            int expected = -3;

            int actual = InputMethods.getValidInt(input,
                    "Enter a number between 5 and 10:", 5, 10);

            //if the actual value returned doesn't equal the expected then this is an error.
            if (actual != expected) {
                error = true;

                //a message describing the error so that the programmer can find this test and
                //see what the values were.
                System.out.println("testGetValidInt 1) Error, expected: " + expected + ", actual" +
                        ":" + " " + actual);
            }

        }

        if (error) {
            System.out.println("testGetValidInt failed");
        } else {
            System.out.println("testGetValidInt passed; it is expected that the prompt and " +
                    "getValidInt error messages are printed out.");
        }
    }
}
