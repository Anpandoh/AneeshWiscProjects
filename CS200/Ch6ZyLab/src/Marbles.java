// testWordsForMarbles created by anpandoh at 11:30 AM, 10/14/21


public class Marbles {

    public static String wordsForMarbles(int numberOfMarbles) {
        if (numberOfMarbles < 0) {
            return "owe marbles";
        } else if (numberOfMarbles > 7) {
            return "a lot of marbles";
        } else {
            switch (numberOfMarbles) {
                case 0:
                    return "no marbles";
                case 1:
                    return "a marble";
                case 2:
                    return "two marbles";
                case 3:
                    return "a few marbles";
                default:
                    return "some marbles";
            }
        }
    }

    public static void testWordsForMarbles() {
        boolean error = false;

        if (!wordsForMarbles(1).equals("a marble")) {
            error = true;
            System.out.println("1 should be 'a marble'");
        }
        if (!wordsForMarbles(2).equals("two marbles")) {
            error = true;
            System.out.println("2 should be 'two marbles'");
        }
        if (!wordsForMarbles(3).equals("a few marbles")) {
            error = true;
            System.out.println("3 should be 'a few marbles'");
        }
        
        if (!wordsForMarbles(0).equals("no marbles")) {
            error = true;
            System.out.println("0 should be 'no marbles'");
        }

        //This testing method is to help verify that wordsForMarbles works
        //correctly.
        //VERIFY the above test cases are reasonable and correct.
        //ADD additional test cases to meet the requirements specified in the lab.
        //IF the test cases indicate errors in the wordsForMarbles method,
        //then fix the wordsForMarbles method and rerun the tests.

        if (error) {
            System.out.println("testWordsForMarbles failed");
        } else {
            System.out.println("testWordsForMarbles passed");
            //System.out.println(wordsForMarbles(3));
        }
    }

    public static void main(String[] args) {
        testWordsForMarbles();
    }
}
