///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           CalendarZylab
// Course:          CS200 Winter 2021
//
// Author:          Aneesh Pandoh
// Email:           pandoh@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// Source or Recipient;
// Jim Williams & Devesh Shah:
// Initial Creators of Unmodified Code
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Scanner;

/**
 * This class contains the entire program to print out a calendar and find the day of a week for a
 * given month/date/year.
 *
 * @author Jim Williams
 * @author Devesh Shah
 * @author Aneesh Pandoh
 */

public class Calendar {

    /**
     * This method prints out a given character a given amount of times
     *
     * @param characterType      The type of character that will be printed out
     * @param amountOfCharacters The amount of times the character is printed
     */
    public static void insertCharacter(char characterType, int amountOfCharacters) {
        for (int iterations = 0; iterations < amountOfCharacters; iterations++) {
            System.out.print(characterType);
        }
    }

    /**
     * This returns whether the specified year is a leap year.
     * <p>
     * The algorithm is: Every year that is exactly divisible by four is a leap year, except for
     * years that are exactly divisible by 100, but these centurial years are leap years if they are
     * exactly divisible by 400. For example, the years 1700, 1800, and 1900 are not leap years, but
     * the years 1600 and 2000 are. https://en.wikipedia.org/wiki/Leap_year
     *
     * @param year The year to determine whether it is a leap year.
     * @return true if the year is a leap year, false otherwise.
     */
    public static boolean isLeapYear(int year) {

        boolean isLeapYear = false;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    isLeapYear = true;
                } else {
                    isLeapYear = false;
                }
            } else {
                isLeapYear = true;
            }

        } else {
            isLeapYear = false;
        }
        return isLeapYear;
    }

    /**
     * This returns the number of days in the specified month of year.
     *
     * @param month The month to return the number of days.
     * @param year  The year is used for determining whether it is a leap year.
     * @return The number of days in the specified month of the year.
     */
    public static int getDaysInMonth(int month, int year) {
        int daysInMonth = 0;
        switch (month) {
            //31 days
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                daysInMonth = 31;
                break;

            //30 days
            case 4:
            case 6:
            case 9:
            case 11:
                daysInMonth = 30;
                break;

            case 2:  //28 or 29 days
                if (isLeapYear(year)) {
                    daysInMonth = 29;
                } else {
                    daysInMonth = 28;
                }
                break;
        }
        return daysInMonth;
    }

    /**
     * Returns the name of the month, given the number of the month.
     *
     * @param month The month where 1 is January and 12 is December.
     * @return The name of the month.
     */
    public static String getMonthName(int month) {
        String monthStr;
        switch (month) {
            case 1:
                monthStr = "January";
                break;
            case 2:
                monthStr = "February";
                break;
            case 3:
                monthStr = "March";
                break;
            case 4:
                monthStr = "April";
                break;
            case 5:
                monthStr = "May";
                break;
            case 6:
                monthStr = "June";
                break;
            case 7:
                monthStr = "July";
                break;
            case 8:
                monthStr = "August";
                break;
            case 9:
                monthStr = "September";
                break;
            case 10:
                monthStr = "October";
                break;
            case 11:
                monthStr = "November";
                break;
            case 12:
                monthStr = "December";
                break;
            default:
                monthStr = "unknown";
                break;
        }
        return monthStr;
    }

    /**
     * Prints out the header of the calendar including the date and year along with a hyphen line
     * break and the days of the week for the calendar
     *
     * @param month The month the calendar is for
     * @param year  The year at which the calendar is for
     */

    public static void printHeader(String month, int year) {
        final int TOTAL_WIDTH = 28;
        final char MONTH_HEADER_LINE_CHAR = '-';

        System.out.println();
        String dateHeading = month + " " + year;
        int spacesBeforeDateHeading = (TOTAL_WIDTH - dateHeading.length()) / 2;
        insertCharacter(' ', spacesBeforeDateHeading);
        System.out.println(dateHeading);
        insertCharacter(MONTH_HEADER_LINE_CHAR, TOTAL_WIDTH);
        System.out.println();
        System.out.println("Sun Mon Tue Wed Thu Fri Sat");
    }

    /**
     * This prints out the days of the month in the correct day of the week column.
     *
     * @param startDay    The day of the week of the 1st day of the month, where 0 is Sunday, 1 is
     *                    Monday.
     * @param daysInMonth The number of days in the month.
     */
    public static void printMonthDays(int startDay, int daysInMonth) {
        final char CHAR_BETWEEN_DAYS = ' ';
        final int DAYS_IN_A_WEEK = 7;
        final int LOWEST_SINGLE_DIGIT_DAY = 1;
        final int HIGHEST_SINGLE_DIGIT_DAY = 9;

        insertCharacter(CHAR_BETWEEN_DAYS, startDay * 4);
        for (int day = 1; day <= daysInMonth; day++) {
            if (day >= LOWEST_SINGLE_DIGIT_DAY && day <= HIGHEST_SINGLE_DIGIT_DAY) {
                insertCharacter(CHAR_BETWEEN_DAYS, 2);
            } else {
                insertCharacter(CHAR_BETWEEN_DAYS, 1);
            }
            System.out.print(day);
            insertCharacter(CHAR_BETWEEN_DAYS, 1);
            startDay++;
            if (startDay % DAYS_IN_A_WEEK == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    /**
     * This method calculates the number of days between the two given dates for the same calendar
     * year
     *
     * @param startMonth The initial month(#) that is being subtracted from the end month
     * @param startDay   The initial day(#) that is being subtracted from the end day
     * @param endMonth   The final month(#) that is being subtracted by the start month
     * @param endDay     The final day(#) that is being subtracted by the start day
     * @param year       The year(#) in which the days between is being calculated
     * @return The amount of days in between startDay,startMonth and endDay,endMonth
     */
    public static int daysBetweenDates(int startMonth, int startDay, int endMonth, int endDay,
                                       int year) {

        int daysInBetween = 0;
        if (startMonth == endMonth) {
            daysInBetween = endDay - startDay;
            return daysInBetween;
        }

        // Add number of days for first month
        daysInBetween += getDaysInMonth(startMonth, year) - startDay;

        // Add number of days for in-between months
        for (int inBetweenMonths = (startMonth + 1); inBetweenMonths < endMonth; inBetweenMonths++) {
            int daysInMonth = getDaysInMonth(inBetweenMonths, year);
            daysInBetween += daysInMonth;
        }

        // Add number of days for last month
        daysInBetween += endDay;

        return daysInBetween;
    }

    /**
     * This method determines the name of the day for a certain date in a year
     *
     * @param userDateMonth  The month of the date provided by the user
     * @param userDateDay    The day of the date provided by the user
     * @param userDateYear   The year of the date provided by the user
     * @param startDayOfWeek The day of the week which the year started where 0 is Sunday, 1 is
     *                       Monday, etc.
     * @return The day name of the given date
     */

    public static String dayOfWeekName(int userDateMonth, int userDateDay, int userDateYear,
                                       int startDayOfWeek) {
        String dayName = " ";

        if (userDateMonth <= 0 || userDateMonth > 12 || userDateDay <= 0 || userDateDay > 31) {
            return "Invalid User Input";
        }

        // First calculate number of days between start of year and date
        int daysFromJan1 = daysBetweenDates(1, 1, userDateMonth, userDateDay, userDateYear);

        // Then do math to what day of week this corresponds to
        int dayOfWeekInt = daysFromJan1 % 7;
        dayOfWeekInt = (startDayOfWeek + dayOfWeekInt) % 7;

        switch (dayOfWeekInt) {
            case 0:
                dayName = "Sunday";
                break;
            case 1:
                dayName = "Monday";
                break;
            case 2:
                dayName = "Tuesday";
                break;
            case 3:
                dayName = "Wednesday";
                break;
            case 4:
                dayName = "Thursday";
                break;
            case 5:
                dayName = "Friday";
                break;
            case 6:
                dayName = "Saturday";
                break;
        }

        return dayName;
    }

    /**
     * First prompts for the year and the day of the week of January 1st and then prompts for any
     * given date (month and day) of that same year that user wants to find day of week for. Prints
     * out day of week for given date and a monthly calendar for given month.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        final char FIRST_MONTH = 1;
        final char LAST_MONTH = 12;
        final int DAYS_IN_A_WEEK = 7;

        Scanner input = new Scanner(System.in);
        System.out.print("Enter year: ");
        int year = input.nextInt();
        System.out.print("Enter day of week of Jan 1 (0-Sunday, 1-Monday, etc): ");
        int startDay = input.nextInt();
        int originalStartDay = startDay;

        System.out.println();
        System.out.println("Lets find the day of a week for any given date on this calendar year!");
        System.out.print("Enter Month of Desired Date (1-Jan, 2-Feb, etc): ");
        int monthOfDate = input.nextInt();
        System.out.print("Enter Day of Desired Date (1-1st of month, 2-2nd of month, etc): ");
        int dayOfDate = input.nextInt();
        System.out.println();
        System.out.println(getMonthName(monthOfDate) + " " + dayOfDate + ", " + year + " is a " + dayOfWeekName(monthOfDate, dayOfDate, year, originalStartDay));


        for (int month = FIRST_MONTH; month < monthOfDate; ++month) {
            int daysInMonth = getDaysInMonth(month, year);
            startDay = (startDay + daysInMonth) % DAYS_IN_A_WEEK;
        }
        String monthName = getMonthName(monthOfDate);
        printHeader(monthName, year);
        int daysInMonth = getDaysInMonth(monthOfDate, year);
        printMonthDays(startDay, daysInMonth);

    }
}
