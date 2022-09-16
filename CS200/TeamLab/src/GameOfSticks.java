///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           GameOfSticks
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

//TODO Make the improvements listed in the lab

/**
 * Command Line game, where the user and computer pick up sticks and whoever picks up the last
 * one loses
 * It prompts the user if they want to start first, then the user and computer take turns
 * picking up sticks until they reach 0
 * @author Aneesh Pandoh
 * @author Jim Williams
 */

public class GameOfSticks {

    /**
     * Prompts the user for the number of sticks they want to pull ranging from 1-3
     * @param input - the Scanner that has been declared in the main method
     * @param prompt - the string that is going to be printed out that prints the remaining
     *               sticks and how many the user wants to pick up
     * @param min - minimum user choice allowed
     * @param max - maximum userchoice allowed
     * @return - the number the user chose
     */
    public static int promptUser(Scanner input, String prompt, int min, int max) {
        int userChoice = 0;
        do {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                userChoice = input.nextInt();
                input.nextLine();
                if (userChoice < min || userChoice > max) {
                    System.out.println("Please enter a number from " + min + " to " + max + ".");
                }
            }
            else {
                input.nextLine();
            }
        } while (userChoice < min || userChoice > max);
        return userChoice;
    }

    /**
     *
     * @param remainingSticks - the amount of sticks remaining for the game
     * @return - the amount that the computer picks up
     */
    public static int computerChoice(int remainingSticks) {
        int pickUp = (remainingSticks - 1) % 4;
        return pickUp > 0 ? pickUp : 1;
    }

    /**
     * The game welcomes you and asks if you want to go first
     * Then you take turns drawing sticks until someone loses
     * @param args - commandLine arguments
     * @see - computerChoice
     * @see -  promptUser
     */
    public static void main(String[] args) {
        int startingSticks = 21;
        int maxSticksPulled = 3;
        int minSticksPulled = 1;
        Scanner scnr = new Scanner(System.in);
        System.out.println("Welcome to the Game of Sticks");
        int numSticks = startingSticks;
        System.out.print("Would you like to go first? (y/n): ");
        boolean userStarts = scnr.nextLine().trim().equalsIgnoreCase("y");
        int numUserPicked = 0;
        int numComputerPicked = 0;
        while (numSticks > 0) {
            if (userStarts) {
                String prompt = "There are " + numSticks + " remaining sticks. How many do you " +
                        "pick up (" + minSticksPulled + "-" + Math.min(maxSticksPulled,
                        numSticks) + ")? ";
                numUserPicked = promptUser(scnr, prompt, minSticksPulled, maxSticksPulled);
                numSticks -= numUserPicked;
                if (numSticks <= 0) {
                    System.out.println("You lost!");
                }
            }
            else {
                numComputerPicked = computerChoice(numSticks);
                System.out.println("Computer picks up " + numComputerPicked + " sticks");
                numSticks -= numComputerPicked;
                if (numSticks <= 0) {
                    System.out.println("You won!");
                }
            }
            userStarts = !userStarts;
        }
    }
}
