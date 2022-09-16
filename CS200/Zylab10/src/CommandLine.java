///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           CommandLineZylab
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

public class CommandLine {
    public static void main(String[] args) {

        int factorialValue = 1;


        if (args.length != 1){
            System.out.print("Incorrect number of arguments passed");
        }

        else if (Integer.parseInt(args[0]) < 0) {
            System.out.print("Argument is a negative number");
        }

        else {
            int userInput = Integer.parseInt(args[0]);
            for (int i = 1; i < userInput; ++i) {
                int temp = i + 1;
                factorialValue *= temp;

            }
            System.out.print("The factorial of " + args[0] + " is " + factorialValue);
        }
    }
}
