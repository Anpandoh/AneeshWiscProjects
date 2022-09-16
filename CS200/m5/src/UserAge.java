///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           userAgeZylab
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

public class UserAge {

    public static void main(String[] args) {
    Scanner scnr = new Scanner(System.in);
    int userMonth = scnr.nextInt();
    int userDay = scnr.nextInt();
    int userYear = scnr.nextInt();
    int currMonth = scnr.nextInt();
    int currDay = scnr.nextInt();
    int currYear = scnr.nextInt();

    int age = currYear - userYear;

    if (currMonth < userMonth){
        age -= 1;
    }
    else if (currMonth == userMonth) {
            if (currDay < userDay){
                age -= 1;
            }
        }
    if ( age < 1 ) {
        age = 0;
    }
        System.out.println(age);
    }
}
