package java_fundamentals.class_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Problem 4: In-Place Array Reversal
 *
 * Reverses the order of elements stored in an array using two pointers in-place.
 */
public class L4_InPlaceArrayReversal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) {
            return;
        }

        String line = scanner.nextLine().trim();
        line = line.replace("[", " ").replace("]", " ").replace(",", " ");
        Scanner lineScanner = new Scanner(line);
        List<Integer> list = new ArrayList<>();
        while (lineScanner.hasNextInt()) {
            list.add(lineScanner.nextInt());
        }

        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }

        // Two-pointer in-place reversal
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }

        System.out.println(Arrays.toString(arr));
    }
}