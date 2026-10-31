package java_fundamentals.class_problems;

import java.util.Scanner;

/**
 * Problem 3: Even and Odd Numbers in a List
 *
 * Given a list of integers, counts how many numbers are even and how many are odd.
 */
public class L3_EvenOddCounter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) {
            return;
        }

        int evenCount = 0;
        int oddCount = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            line = line.replace("[", " ").replace("]", " ").replace(",", " ");
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNextInt()) {
                int num = lineScanner.nextInt();
                if (num % 2 == 0) {
                    evenCount++;
                } else {
                    oddCount++;
                }
            }
        }

        System.out.println("Even: " + evenCount);
        System.out.println("Odd: " + oddCount);
    }
}