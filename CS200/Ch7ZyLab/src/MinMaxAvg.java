///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           MinMaxAvgZylab
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

public class MinMaxAvg {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int i = 0;
        double total = 0;

        int currVal = 0;
        int minVal = 0;
        int maxVal = 0;


        for (i = 0; i < 10; ++i) {
            currVal = scnr.nextInt();
            total += currVal;
            if (i==0){
                maxVal = currVal;
                minVal = currVal;
            }
            if (currVal > maxVal) {
                maxVal = currVal;
            }
            if (currVal < minVal) {
                minVal = currVal;
            }
            if (i==9){
                total += - minVal - maxVal;
            }
        }

        System.out.print(minVal + " ");
        System.out.print(maxVal + " ");
        System.out.println(total/8);

    }
}
