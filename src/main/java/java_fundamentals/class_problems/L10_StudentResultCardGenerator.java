package java_fundamentals.class_problems;

import java.util.Locale;
import java.util.Scanner;

/**
 * Problem 10: Student Result Card Generator
 *
 * Models a Student with a name and an array of three marks.
 * Calculates average mark and assigns grade:
 * - 'B' for an average of 75 or more
 * - 'C' for an average between 60 and 74 (inclusive)
 * - 'D' for an average between 40 and 59 (inclusive)
 * - 'F' for an average below 40
 * Prints the student's name in uppercase, average mark (1 decimal place), and grade.
 */
public class L10_StudentResultCardGenerator {

    /**
     * Student class containing name and three marks.
     */
    public static class Student {
        private final String name;
        private final int[] marks;

        public Student(String name, int[] marks) {
            this.name = name;
            this.marks = marks;
        }

        public String getName() {
            return name;
        }

        public int[] getMarks() {
            return marks;
        }

        public double calculateAverage() {
            if (marks == null || marks.length == 0) {
                return 0.0;
            }
            int sum = 0;
            for (int mark : marks) {
                sum += mark;
            }
            return (double) sum / marks.length;
        }

        public String determineGrade() {
            double avg = calculateAverage();
            if (avg >= 75.0) {
                return "B";
            } else if (avg >= 60.0) {
                return "C";
            } else if (avg >= 40.0) {
                return "D";
            } else {
                return "F";
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            // Remove brackets and commas to extract tokens cleanly
            String cleaned = line.replace("[", " ").replace("]", " ").replace(",", " ");
            Scanner lineScanner = new Scanner(cleaned);
            if (!lineScanner.hasNext()) {
                continue;
            }

            String name = lineScanner.next();
            int[] marks = new int[3];
            int count = 0;
            while (lineScanner.hasNextInt() && count < 3) {
                marks[count++] = lineScanner.nextInt();
            }

            Student student = new Student(name, marks);
            double avg = student.calculateAverage();
            String grade = student.determineGrade();

            System.out.printf(Locale.US, "%s: Average %.1f, Grade %s%n",
                    student.getName().toUpperCase(), avg, grade);
        }
    }
}