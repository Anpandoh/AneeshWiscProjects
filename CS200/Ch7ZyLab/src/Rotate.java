///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           RotateZylab
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

public class Rotate {

    public static int[] rotateLeft3(int[] values){
        int temp  = values[0];
        int temp1 = values[1];
        values[1] = values[2];
        values[0] = temp1;
        values [2] = temp;

        return values;
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int[] nValues = new int[3];
        nValues[0] = scnr.nextInt();
        nValues[1] = scnr.nextInt();
        nValues[2] = scnr.nextInt();

        nValues = rotateLeft3(nValues);

        System.out.println(nValues[0] + " " + nValues[1] + " " + nValues[2]);
    }
}