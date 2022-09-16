///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           isDivisbleBy3Zylab
// Course:          CS200 Winter 2021
//
// Author:          Aneesh Pandoh
// Email:           pandoh@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// Source or Recipient; Description
// Examples:
// Jane Doe; helped me with for loop in reverse method
// https://docs.oracle.com/javase/tutorial/java/nutsandbolts/for.html; 
//         counting for loop
// John Doe; I helped with switch statement in main method.
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Scanner;
public class IntException {

    public static boolean isDivisibleThree(Scanner scnr) {

        boolean isDivisible = false;
        try {
            int userInput = scnr.nextInt();
            if (userInput % 3 == 0){
                isDivisible = true;
            }
        }
        catch (Exception except){
        }



        return isDivisible;
    }

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        System.out.print(isDivisibleThree(scnr));
    }
}
