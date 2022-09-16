///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           drawingTriangleZyLab
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

public class DrawRightTriangle {

    public static void printCharacters(char character, int amount) {
        for (int x = amount; x > 0; --x) {
            System.out.print(character + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
//        System.out.println("Enter a character:");
//        char userChar = scnr.next().charAt(0);
//        System.out.println("Enter triangle height (1-10):");
//        int height = scnr.nextInt();
        int height;
        char userChar;
        System.out.println("Enter a character: ");
        userChar = scnr.next().charAt(0);
        System.out.println("Enter triangle height (1-10): ");
        do {
            height = scnr.nextInt();
            if (height > 10 || height < 0) {
                System.out.println("Please enter height between 1 and 10.");
            }
        } while(height > 10 || height < 0);

        System.out.println();
        for (int iterations = height; iterations > 0; --iterations) {
            printCharacters(userChar, iterations);
        }

    }

}
