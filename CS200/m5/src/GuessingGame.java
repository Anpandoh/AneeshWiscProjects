///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           guessingGameZylab
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
import java.util.Random;

public class GuessingGame {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Random rand = new Random(Config.SEED);
        System.out.println("Guess a number between 1 and 5");
        int guessNum1 = scnr.nextInt();
        int num = rand.nextInt(5)+1;

        if (guessNum1 > num) {
            System.out.println("That's too high");
            System.out.println("Guess again!");
            int guessNum2 = scnr.nextInt();
            if (guessNum2 != num) {
                System.out.println("You lose! It was " + num);
            }
            else {
                System.out.println("You win!");
            }
        }


        else if (guessNum1 < num) {
            System.out.println("That's too low");
            System.out.println("Guess again!");
            int guessNum2 = scnr.nextInt();
            if (guessNum2 != num) {
                System.out.println("You lose! It was " + num);
            }
            else {
                System.out.println("You win!");
            }
        }


        else {
            System.out.println("You win!");
        }
    }
}
