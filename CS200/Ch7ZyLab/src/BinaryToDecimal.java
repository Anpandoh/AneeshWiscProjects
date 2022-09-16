///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           BinarytoDecimalZylab
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

public class BinaryToDecimal {

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        int i;
        int x;
        int decimalVal = 0;
        int numOfBits = scnr.nextInt();
        int[] binaryInput = new int[numOfBits];
        for (i = 0; i < binaryInput.length; ++i) {
            binaryInput[i] = scnr.nextInt();
        }

        int multiplyWeight = 1;
        for (i = binaryInput.length - 1; i >= 0; --i) {
            decimalVal += (binaryInput[i]*multiplyWeight);
            multiplyWeight *= 2;
        }
        System.out.println(decimalVal);
    }
}
