///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           InputMethodsZylab
// Course:          CS200 Winter 2021
//
// Author:          Aneesh Pandoh
// Email:           pandoh@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// Source or Recipient; Description
// Jim Williams and Devesh Shah for template
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

//TODO need file header comment

//import java.util.Collections;

import java.util.Scanner;
//import java.util.ArrayList;

/**
 * Some methods to get user input.
 *
 * @author Jim Williams
 * @author Devesh Shah
 * @author Aneesh Pandoh
 */
public class InputMethods {

    /**
     * Determines if the integer is valid within the bounds and returns Example: If the user enters
     * in 6 with the lowerBound and upperBound being 5 and 10 respectively it will return 6
     *
     * @param scnr         Scanner class declared in main passed into methods, if value is null
     *                     returns -1
     * @param requestInput the string that gets printed out to ask the user what integer they want
     * @param lowerBound   The lower bound which determines the validity of the integer
     * @param upperBound   The upper bound which determines the validity of the integer If
     *                     lowerBound > upperBound returns -2
     * @return the integer that is considered valid, if no valid input returns -3
     */
    public static int getValidInt(Scanner scnr, String requestInput, int lowerBound,
                                  int upperBound) {

        int userInteger = 0;

        for (int i = 0; i < 3; ++i) {
            System.out.println(requestInput);

            if (scnr == null) {
                userInteger = -1;
                break;
            } else if (lowerBound > upperBound) {
                userInteger = -2;
                break;
            } else if (scnr.hasNextInt()) {
                userInteger = scnr.nextInt();
                if (userInteger > lowerBound && userInteger <= upperBound) {
                    break;
                } else {
                    scnr.nextLine();
                    userInteger = -3;

                }
            } else {
                scnr.nextLine();
                userInteger = -3;
            }
        }
        return userInteger;
    }

}
