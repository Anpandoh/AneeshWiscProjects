///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           RockPaperScissorsZylab
// Course:          CS 200 2021 Sem 1
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

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    public static void didUserWin(String userChoice, String randomChoice, Boolean winner){
        if (winner){
            System.out.println(userChoice + " beats " + randomChoice.toLowerCase() + " - you win!");
        }
        else {
            System.out.println(randomChoice + " beats " + userChoice.toLowerCase() + " - you " +
                    "lose!");
        }
    }

    public static void main(String[] args) {
        Random rand = new Random(Config.SEED);
        System.out.print("Please select one of [R/P/S]: ");
        Scanner scnr = new Scanner(System.in);
        String userChoice = scnr.next().toUpperCase();
        //userChoice
        switch (userChoice){
            case "R": userChoice =  "Rock";
                System.out.println("You chose: " + userChoice);
                break;
            case "S": userChoice = "Scissors";
                System.out.println("You chose: " + userChoice);
                break;
            case "P": userChoice = "Paper";
                System.out.println("You chose: " + userChoice);
                break;
            default: userChoice = "Rock";
                System.out.println("Invalid choice! Defaulting to Rock.");
                break;
        }
        //randomChoice
        int randomNum = rand.nextInt(3);
        String randomChoice = "";
        switch (randomNum){
            case 0: randomChoice =  "Rock";
                System.out.println("I chose: " + randomChoice);
                break;
            case 1: randomChoice = "Paper";
                System.out.println("I chose: " + randomChoice);
                break;
            case 2: randomChoice = "Scissors";
                System.out.println("I chose: " + randomChoice);
                break;
            default:
                break;
        }
        //findingWinner
        if (userChoice.equals(randomChoice)){
            System.out.println("A Tie!");
        }
        else if (userChoice.equals("Rock") && randomChoice.equals("Paper")){
            didUserWin(userChoice, randomChoice, false);
        }
        else if (userChoice.equals("Paper") && randomChoice.equals("Scissors")){
            didUserWin(userChoice, randomChoice, false);
        }
        else didUserWin(userChoice, randomChoice, !userChoice.equals("Scissors") || !randomChoice.equals("Rock"));

    }
}
