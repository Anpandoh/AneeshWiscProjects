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


import java.util.ArrayList;
import java.util.Scanner;

public class Counter {


    public static void main(String[] args) {
        ArrayList<Integer> dailyCounts = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a hashtag: ");
        String hashtag = input.nextLine().trim();
        hashtag = hashtag.startsWith("#") ? hashtag : "#" + hashtag;

        System.out.println("Enter count of " + hashtag + " tweets per day (negative to end).");

        boolean done = false;
        do {
            int count = input.nextInt();
            if (count < 0) {
                done = true;
            } else {
                dailyCounts.add(count);
            }
        } while (!done);

        //determine total number of days of data
        //determine minimum
        //determine maximum
        //determine average
        //draw a bar chart where maximum / 50 is represented by one *.
        int sum = 0;
        int max = dailyCounts.get(0);
        int min = dailyCounts.get(0);
        for (Integer num: dailyCounts){
            sum += num;
            if (num > max){
                max = num;
            }
            if (num < min){
                min = num;
            }
        }
        double average = sum/((double) dailyCounts.size());
        System.out.println("for " + dailyCounts.size() + " days " + "max = "+ max + ", " + "min =" +
                " "+ min + ", " + "average = "+ average);

        for (Integer num: dailyCounts){
            for (int i =0; i < num/4;++i){
                System.out.print("*");
            }
            System.out.println("("+num+")");
        }
        System.out.println("A * represents 4 tweets.");

    }
}

