///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           
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

public class GPS {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        //Problem 1: uncomment the next line to always have the same input to the program.
        //Scanner scnr = new Scanner("456\nMain St.\n");

        int streetNumber = 0;
        String streetName = "";
        boolean haveNumber = false;
        do {
            System.out.println("Enter street number:");
            if (scnr.hasNextInt()) {
                streetNumber = scnr.nextInt();
                haveNumber = true;
                System.out.println("Enter street name:");
                scnr.nextLine();
                streetName = scnr.nextLine();
            } else {
                System.out.println("Error, not a number");
                scnr.nextLine();
            }
        } while (!haveNumber);
        System.out.println("Address: " + streetNumber + " " + streetName);
    }
}