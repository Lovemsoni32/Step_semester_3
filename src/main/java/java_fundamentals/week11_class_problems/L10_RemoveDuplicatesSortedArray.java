package java_fundamentals.week11_class_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Problem 10: Remove Duplicates From a Sorted Array In-Place
 *
 * Modifies sorted array in-place using two pointers so each unique value appears once.
 * Time Complexity: O(n)
 * Additional Space Complexity: O(1)
 */
public class L10_RemoveDuplicatesSortedArray {

    /**
     * Modifies nums in-place and returns the count of unique elements.
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int insertIndex = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[insertIndex] = nums[i];
                insertIndex++;
            }
        }

        return insertIndex;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()).append("\n");
        }
        String input = sb.toString().trim();

        int[] nums;
        if (input.contains("[") && input.contains("]")) {
            int start = input.indexOf('[');
            int end = input.indexOf(']');
            String content = input.substring(start + 1, end).trim();
            String[] tokens = content.split("[,\\s]+");
            List<Integer> list = new ArrayList<>();
            for (String t : tokens) {
                if (!t.trim().isEmpty()) {
                    list.add(Integer.parseInt(t.trim()));
                }
            }
            nums = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                nums[i] = list.get(i);
            }
        } else {
            Scanner sc = new Scanner(input);
            List<Integer> list = new ArrayList<>();
            while (sc.hasNextInt()) {
                list.add(sc.nextInt());
            }
            if (list.size() > 0) {
                if (list.size() > 1 && list.get(0) == list.size() - 1) {
                    nums = new int[list.size() - 1];
                    for (int i = 1; i < list.size(); i++) {
                        nums[i - 1] = list.get(i);
                    }
                } else {
                    nums = new int[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        nums[i] = list.get(i);
                    }
                }
            } else {
                nums = new int[0];
            }
        }

        int uniqueCount = removeDuplicates(nums);
        System.out.println("length = " + uniqueCount);

        int[] uniquePortion = Arrays.copyOfRange(nums, 0, uniqueCount);
        if (uniqueCount > 0) {
            System.out.println("nums[0.." + (uniqueCount - 1) + "] = " + Arrays.toString(uniquePortion));
        } else {
            System.out.println("nums[] = []");
        }
    }
}
