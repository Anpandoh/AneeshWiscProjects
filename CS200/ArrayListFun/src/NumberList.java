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
import java.util.Random;

public class NumberList {

    /**
     * Creates an ArrayList of size, populated with numbers from 1 to 10 generated using
     * rnd.nextInt().
     *
     * @param rnd  A provided random number generator.
     * @param size The size of the list to create.
     * @return The list of numbers.
     */
    public static ArrayList<Integer> createList(Random rnd, int size) {
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            list.add(rnd.nextInt(10) + 1);
        }
        return list;
    }

    /**
     * The resulting list should not have the same number twice in a row. Checks and removes
     * duplicate values.
     * <p>
     * Example: [3,4,3,3] should result in [3,4,3]
     *
     * @param list A list of numbers
     */
    public static void remove2InARow(ArrayList<Integer> list) {
        for (int i = 0; i < list.size() - 1; ++i) {
            if (list.get(i).equals(list.get(i + 1))) {
                list.remove(i);

                if ((i != list.size() - 1) && (list.get(i).equals(list.get(i + 1)))) {
                    list.remove(i);
                    System.out.println(list);
                }
            }
        }
    }

    public static void main(String[] args) {
        final int COUNT = 10;
        Random rand = new Random(1); //don't change seed

        ArrayList<Integer> list = createList(rand, COUNT);
        System.out.println("initial:\n" + list);

        remove2InARow(list);
        System.out.println("after removing contiguous duplicates:\n" + list);

        System.out.println("last element: " + list.get(list.size() - 1));
    }
}