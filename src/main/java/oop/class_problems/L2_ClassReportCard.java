package oop.class_problems;

import java.util.Locale;
import java.util.Scanner;

public class L2_ClassReportCard {

    static class Student {
        private String name;
        private int mark1;
        private int mark2;
        private int mark3;

        public Student(String name) {
            this.name = name;
        }

        public boolean setMarks(int a, int b, int c) {
            if (a < 0 || a > 100 || b < 0 || b > 100 || c < 0 || c > 100) {
                return false;
            }
            this.mark1 = a;
            this.mark2 = b;
            this.mark3 = c;
            return true;
        }

        public String getName() {
            return name;
        }

        public int getTotal() {
            return mark1 + mark2 + mark3;
        }

        public double getAverage() {
            return getTotal() / 3.0;
        }

        public char getGrade() {
            double avg = getAverage();
            if (avg >= 80.0) {
                return 'A';
            } else if (avg >= 70.0) {
                return 'B';
            } else if (avg >= 60.0) {
                return 'C';
            } else if (avg >= 40.0) {
                return 'D';
            } else {
                return 'F';
            }
        }

        @Override
        public String toString() {
            return name + " total=" + getTotal() + " avg=" 
                    + String.format(Locale.US, "%.2f", getAverage()) 
                    + " grade=" + getGrade();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        Student[] tempAccepted = new Student[n];
        int acceptedCount = 0;

        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            int m1 = scanner.nextInt();
            int m2 = scanner.nextInt();
            int m3 = scanner.nextInt();

            Student student = new Student(name);
            if (student.setMarks(m1, m2, m3)) {
                tempAccepted[acceptedCount++] = student;
            } else {
                System.out.println(name + " skipped: marks must be 0 to 100");
            }
        }

        Student[] accepted = new Student[acceptedCount];
        for (int i = 0; i < acceptedCount; i++) {
            accepted[i] = tempAccepted[i];
        }

        int passes = 0;
        for (int i = 0; i < acceptedCount - 1; i++) {
            passes++;
            boolean swapped = false;
            for (int j = 0; j < acceptedCount - 1 - i; j++) {
                if (accepted[j].getTotal() < accepted[j + 1].getTotal()) {
                    Student temp = accepted[j];
                    accepted[j] = accepted[j + 1];
                    accepted[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }

        for (int i = 0; i < acceptedCount; i++) {
            System.out.println((i + 1) + ". " + accepted[i].toString());
        }

        System.out.println("Sorted in " + passes + " passes");
    }
}

