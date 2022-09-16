///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           MessedUpTeamLab
// Course:          CS200 Winter 2021
//
// Authors:          Aneesh Pandoh , Kevin Ju
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

//Finding letterGrade
public class MessedUp {
    //Converting rawScore to letterGrade
    //@param rawScore - number
    //@return letterGrade - coverted from rawScore (A-F)
    public static String assignGrade(int rawScore) {
        String letterGrade;
        if (rawScore >= 93) letterGrade = "A";
        else if (rawScore >= 89) letterGrade = "AB";
        else if (rawScore >= 82) letterGrade = "B";
        else if (rawScore >= 76) letterGrade = "BC";
        else if (rawScore >= 68) letterGrade = "C";
        else if (rawScore >= 55) letterGrade = "D";
        else letterGrade = "F";
        return letterGrade;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your score: ");
        int rawScore = input.nextInt();
        System.out.println("Grade: " + assignGrade(rawScore));
    }
}
