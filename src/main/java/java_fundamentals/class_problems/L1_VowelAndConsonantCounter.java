package java_fundamentals.class_problems;

import java.util.Scanner;

/**
 * Problem 1: Vowel and Consonant Counter
 *
 * Reads a single word and counts the total number of vowels and consonants.
 * Assumes the input word contains only alphabetic characters (case-insensitive).
 */
public class L1_VowelAndConsonantCounter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNext()) {
            return;
        }
        String word = scanner.next();

        int vowels = 0;
        int consonants = 0;

        for (int i = 0; i < word.length(); i++) {
            char ch = Character.toLowerCase(word.charAt(i));
            if (ch >= 'a' && ch <= 'z') {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }

        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
    }
}