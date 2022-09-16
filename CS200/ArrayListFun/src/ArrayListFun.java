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

import java.lang.Math;
import java.util.ArrayList;


public class ArrayListFun {

    public static void main(String[] args) {
        ArrayList<Integer> testArray = new ArrayList<>();
        testArray.add(-1);
        testArray.add(-1);
        testArray.add(-1);
        testArray.add(-1);

        ArrayList<Integer> test2Array = new ArrayList<>();
        test2Array.add(1);
        test2Array.add(1);
        test2Array.add(-1);
        test2Array.add(-1);

        ArrayList<Character> test3Array = new ArrayList<>();
        test3Array.add('a');
        test3Array.add('b');
        test3Array.add('c');
        test3Array.add('a');
        test3Array.add('b');
        test3Array.add('c');

        ArrayList<Character> test4Array = new ArrayList<>();
        test4Array.add('a');
        test4Array.add('b');
        //test4Array.add('c');


        //System.out.println(isPerfectSquare(testArray));
        System.out.println(isRepeated(test3Array, test4Array));
        //System.out.println(countOccurencesInRange(testArray,4, 150));
        //System.out.println(isOrthogonal(testArray, test2Array));

    }

    /**
     * Counts the number of instances of a values in list, that lie within a given range. Returns
     * null if list is null.
     *
     * @param list
     * @param rangeLeft
     * @param rangeRight
     * @return the number of Integer occurrences in list that lie within the given range (both
     * values inclusive)
     */
    public static Integer countOccurencesInRange(ArrayList<Integer> list, int rangeLeft,
                                                 int rangeRight) {
        Integer amountInRange = 0;

        if (rangeLeft > rangeRight || list == null){
            amountInRange = null;
        }
        else {
            for (Integer num : list) {
                if (rangeLeft <= num && num <= rangeRight) {
                    amountInRange += 1;
                }
            }
        }
        return amountInRange;
    }

    /**
     * Checks if the two vectors are orthogonal or not Returns null if list is null. Example : for a
     * = [1, 1, -1, -1] and b = [-1, -1, -1, -1, the running sum will be a[0]*b[0] + a[1]*b[1] +
     * a[2]*b[2] + a[3]*b[3] = -1 -1 + 1 + 1 = 0. Thus, the vectors are orthogonal.
     *
     * @param vectorA
     * @param vectorB
     * @return true or false depending on whether the two vectors are orthogonal or not.
     */
    public static Boolean isOrthogonal(ArrayList<Integer> vectorA, ArrayList<Integer> vectorB) {
        Boolean orthogonal = false;

        if (vectorA == null || vectorB == null) {
            orthogonal = null;
        }
        else if (vectorA.size() != vectorB.size() ){
            orthogonal=null;
        }

        else {
            Integer sum = 0;
            for (int x = 0; x < vectorA.size(); ++x) {
                sum += vectorA.get(x) * vectorB.get(x);
            }
            if (sum == 0){
                orthogonal = true;
            }
        }
        return orthogonal;
    }

    /**
     * Returns true if the input is formed by repeated concatenation of the pattern. Returns null if
     * list is null.
     *
     * @param list
     * @param pattern
     * @return true if the input can be formed by repeated concatenation of the pattern.
     */
    public static Boolean isRepeated(ArrayList<Character> list, ArrayList<Character> pattern) {
        Boolean repeated = false;
        if (list == null || pattern == null){
            repeated = null;
        }
        else {
            int sum = 0;
            for (int x = 0; x < list.size(); ++x) {
                for (Character letter : pattern) {
                    if (list.indexOf(letter) == 0) {
                        sum += 1;
                        list.remove(0);
                    }

                }
            }
            System.out.println(sum);
            System.out.println(pattern.size());
            if ((sum/pattern.size())>1){
                repeated = true;
            }
        }
        return repeated;
    }

    /**
     * Returns whether all the integers in the input list are perfect squares. Hint: Use Math.sqrt
     * and Math.floor function to find the square root of the number has a non-zero fractional
     * part.
     *
     * @param list a list of integers.
     * @return true if all the integers in the input are perfect squares, null if the list is null.
     */
    public static Boolean isPerfectSquare(ArrayList<Integer> list) {
        Boolean areAllSquares = true;
        if (list == null){
            areAllSquares = null;
        }
        else {

            for (Integer num : list) {
                if (Math.floor(Math.sqrt(num)) != Math.sqrt(num)) {
                    areAllSquares = false;
                    break;
                }
            }
        }
        return areAllSquares;
    }
}
