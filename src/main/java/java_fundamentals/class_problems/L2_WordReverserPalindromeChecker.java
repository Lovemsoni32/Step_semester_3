package java_fundamentals.class_problems;

import java.util.Scanner;

/**
 * Problem 2: Word Reverser and Palindrome Checker
 *
 * Reads a word, reverses it using StringBuilder, and prints the reversed word.
 * Also determines whether the original word reads the same forwards and backwards (palindrome).
 */
public class L2_WordReverserPalindromeChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String word = scanner.next();
            StringBuilder sb = new StringBuilder(word);
            String reversed = sb.reverse().toString();

            boolean isPalindrome = word.equalsIgnoreCase(reversed);
            if (isPalindrome) {
                System.out.println(reversed + " - palindrome");
            } else {
                System.out.println(reversed + " - not a palindrome");
            }
        }
    }
}