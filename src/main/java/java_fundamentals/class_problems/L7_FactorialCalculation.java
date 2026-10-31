package java_fundamentals.class_problems;

import java.util.Scanner;

/**
 * Problem 7: Factorial Calculation
 *
 * Reads a non-negative integer and calculates its factorial.
 * Special case: 0! = 1.
 */
public class L7_FactorialCalculation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            long factorial = 1;
            for (int i = 1; i <= n; i++) {
                factorial *= i;
            }
            System.out.println(factorial);
        }
    }
}