package java_fundamentals.class_problems;

import java.util.Scanner;

/**
 * Problem 5: Digit Sum and Number Reversal
 *
 * Reads a positive integer, calculates and prints the sum of its digits,
 * and prints the number with its digits reversed.
 */
public class L5_DigitSumNumberReversal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }

        int number = scanner.nextInt();
        int temp = number;
        int sumOfDigits = 0;
        int reversed = 0;

        while (temp > 0) {
            int digit = temp % 10;
            sumOfDigits += digit;
            reversed = reversed * 10 + digit;
            temp /= 10;
        }

        System.out.println("Sum of digits: " + sumOfDigits);
        System.out.println("Reverse: " + reversed);
    }
}