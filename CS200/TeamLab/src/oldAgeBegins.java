import java.util.Scanner;


public class oldAgeBegins {


    public static void getInfo(int year){
        String generation;
        int youthEnds;
        int oldAgeBegins;
        int primeOfLife;

        if (year < 1925) {
            generation = "Greatest Generation";
            youthEnds = 35;
            oldAgeBegins = 73;
            primeOfLife = 52;
        }

        else if (year <= 1945) {
            generation = "Silent";
            youthEnds = 35;
            oldAgeBegins = 73;
            primeOfLife = 52;
        }

        else if (year <= 1964) {
            generation = "Boomers";
            youthEnds = 31;
            oldAgeBegins = 73;
            primeOfLife = 50;
        }

        else if (year <= 1980) {
            generation = "Generation X";
            youthEnds = 31;
            oldAgeBegins = 65;
            primeOfLife = 47;
        }

        else if (year <= 1996) {
            generation = "Millennials";
            youthEnds = 40;
            oldAgeBegins = 59;
            primeOfLife = 36;
        }

        else {
            generation = "Generation Z";
            youthEnds = 20;
            oldAgeBegins = 65;
            primeOfLife = 35;
        }


        System.out.println(generation + " " + youthEnds + " " + oldAgeBegins + " " +  primeOfLife);
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Type the year you were born: ");
        int yearBorn = scnr.nextInt();
        getInfo(yearBorn);
    }
}
