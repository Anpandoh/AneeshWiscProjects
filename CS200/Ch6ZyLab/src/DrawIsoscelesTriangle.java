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

public class DrawIsoscelesTriangle {

    public static void bottomHalf(int amount) {
        for (int x = 0; x < amount; ++x) {
            System.out.print("*");
        }
        System.out.println();
    }

    public static void topHalf(int amount) {
        for (int x = 0; x < amount; ++x) {
            System.out.print("*");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        int height;
        do {
            System.out.print("Enter triangle height: ");
            height = scnr.nextInt();
        } while(height < 0 || height % 2 == 0);

        System.out.println();
        for (int iterations = 0; iterations < height; ++iterations) {
            topHalf(iterations);
        }

        for (int iterations = height; iterations > 0; --iterations) {
            bottomHalf(iterations);
        }


    }

}
