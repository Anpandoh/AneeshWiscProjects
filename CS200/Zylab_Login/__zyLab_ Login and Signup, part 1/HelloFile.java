///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           ZylabUserFile
// Course:          cCS 2000, fall 20201
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
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HelloFile {
    public static void main(String[] args) {
        // TODO: Create a PrintWriter
        // TODO: Get user input using Scanner
        // TODO: Write valid user input to the PrintWriter, catch any exceptions
        PrintWriter printWriter;
        try {
            Scanner scnr = new Scanner(System.in);
            System.out.println("Enter the file name:");

            String fileName = scnr.nextLine();

            if(fileName.trim().isEmpty()) {
                throw new FileNotFoundException();
            }

            System.out.println("Enter something to write in " + fileName + ":");
            String words = scnr.nextLine();
            while (words.trim().isEmpty()) {
                System.out.println("Enter something to write in " + fileName + ":");
                words = scnr.nextLine();
            }
            File file = new File(fileName);

            printWriter = new PrintWriter(file);
            printWriter.print(words.trim());
            printWriter.close();
        } catch (Exception io) {
            System.out.println("Exception occurred: PrintWriter failed");
        }

    }
}