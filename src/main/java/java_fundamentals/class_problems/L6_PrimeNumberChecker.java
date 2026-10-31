package java_fundamentals.class_problems;

import java.util.Scanner;

/**
 * Problem 6: Prime Number Checker
 *
 * Reads a number greater than 1 and determines whether it is a prime number.
 */
public class L6_PrimeNumberChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            boolean isPrime = true;

            if (number <= 1) {
                isPrime = false;
            } else {
                for (int i = 2; i <= (int) Math.sqrt(number); i++) {
                    if (number % i == 0) {
                        isPrime = false;
                        break;
                    }
                }
            }

            if (isPrime) {
                System.out.println(number + " is prime");
            } else {
                System.out.println(number + " is not prime");
            }
        }
    }
}