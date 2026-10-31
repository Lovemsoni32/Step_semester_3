package java_fundamentals.class_problems;

import java.util.Scanner;

/**
 * Problem 8: Student Grade Assignment
 *
 * Reads a student's marks (out of 100) and assigns a grade:
 * A: 90 or above
 * B: 75 to 89
 * C: 60 to 74
 * D: 40 to 59
 * F: Below 40
 */
public class L8_StudentGradeAssignment {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int marks = scanner.nextInt();
            if (marks >= 90) {
                System.out.println("Grade A");
            } else if (marks >= 75) {
                System.out.println("Grade B");
            } else if (marks >= 60) {
                System.out.println("Grade C");
            } else if (marks >= 40) {
                System.out.println("Grade D");
            } else {
                System.out.println("Grade F");
            }
        }
    }
}