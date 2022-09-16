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

public class Quilt {

    public static void main(String[] args) {
        //fillPatternBlock();

        char[][][][] quiltArray = createQuilt();

        displayPattern(quiltArray);
    }

    public static void displayPattern(char[][][][] array) {
        for (int r = 0; r < array.length; r++) {
            //System.out.println();
            for (int c = 0; c < array[r].length; c++) {
                System.out.println();
                for (int x = 0; x < array[r][c].length; x++) {
                    //System.out.println();
                    for (int y = 0; y < array[r][c][x].length; y++) {
                        System.out.print(array[r][c][x][y] + " ");
                    }
                }
            }

        }
    }

    public static void fillPatternBlock(char[][][][] createQuilt, int r, int c){
        //char[][] patternBlock = new char[4][5];
        for (int x = 0; x < createQuilt[r][c].length; x++) {
            for (int y = 0; y < createQuilt[r][c][x].length; y++) {
                createQuilt[r][c][x][y] = 'x';
            }
        }
        //System.out.print(patternBlock[1][1]);
    }

    public static char[][][][] createQuilt() {
        char[][][][] quiltArray = new char[3][4][4][5];
        for (int r = 0; r < quiltArray.length; r++) {
            for (int c = 0; c < quiltArray[r].length; c++) {
                fillPatternBlock(quiltArray, r, c);
            }
        }
        return quiltArray;
    }
}
