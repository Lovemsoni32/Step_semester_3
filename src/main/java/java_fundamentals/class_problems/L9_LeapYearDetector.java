package java_fundamentals.class_problems;

import java.util.Scanner;

/**
 * Problem 9: Leap Year Detector
 *
 * Reads a year and determines if it is a leap year.
 * A year is a leap year if divisible by 4, but not by 100, unless divisible by 400.
 */
public class L9_LeapYearDetector {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int year = scanner.nextInt();
            boolean isLeap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
            if (isLeap) {
                System.out.println("Leap year");
            } else {
                System.out.println("Not a leap year");
            }
        }
    }
}