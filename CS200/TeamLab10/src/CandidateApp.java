///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           
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
public class CandidateApp {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("What is the candidate's name?");
        String name = scnr.next();
        System.out.println("What is the candidate's party?");
        String party = scnr.next();
        Candidate Candidate1 = new Candidate(name, party);

        System.out.println("What office is the candidate running for?");
        String office = scnr.next();
        Candidate1.setOffice(office);

        System.out.println("Is the candidate incumbent?");
        String yesorno = scnr.next();
        if

    }
}
