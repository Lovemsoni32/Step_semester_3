package java_fundamentals.week11_class_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Problem 7: Insert Position in a Sorted List
 *
 * Uses Binary Search to find target index or insertion position in sorted distinct array.
 * Time Complexity: O(log n)
 * Additional Space Complexity: O(1)
 */
public class L7_InsertPositionSortedList {

    /**
     * Finds index of target if present, or index where it should be inserted.
     */
    public static int searchInsertPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
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

        if (input.contains("[") && input.contains("]")) {
            Pattern pattern = Pattern.compile("nums\\s*=\\s*\\[([^\\]]+)\\]\\s*,\\s*target\\s*=\\s*(-?\\d+)");
            Matcher matcher = pattern.matcher(input);
            boolean matched = false;
            while (matcher.find()) {
                matched = true;
                String[] tokens = matcher.group(1).split("[,\\s]+");
                int target = Integer.parseInt(matcher.group(2));
                List<Integer> list = new ArrayList<>();
                for (String t : tokens) {
                    if (!t.trim().isEmpty()) {
                        list.add(Integer.parseInt(t.trim()));
                    }
                }
                int[] nums = new int[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    nums[i] = list.get(i);
                }
                System.out.println(searchInsertPosition(nums, target));
            }

            if (!matched) {
                int start = input.indexOf('[');
                int end = input.indexOf(']');
                String[] tokens = input.substring(start + 1, end).split("[,\\s]+");
                List<Integer> list = new ArrayList<>();
                for (String t : tokens) {
                    if (!t.trim().isEmpty()) {
                        list.add(Integer.parseInt(t.trim()));
                    }
                }
                int[] nums = new int[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    nums[i] = list.get(i);
                }

                int target = 0;
                String rem = input.substring(end + 1);
                Pattern tp = Pattern.compile("(-?\\d+)");
                Matcher tm = tp.matcher(rem);
                if (tm.find()) {
                    target = Integer.parseInt(tm.group(1));
                }
                System.out.println(searchInsertPosition(nums, target));
            }
        } else {
            Scanner sc = new Scanner(input);
            if (sc.hasNextInt()) {
                int n = sc.nextInt();
                int target = sc.nextInt();
                int[] nums = new int[n];
                for (int i = 0; i < n; i++) {
                    nums[i] = sc.nextInt();
                }
                System.out.println(searchInsertPosition(nums, target));
            }
        }
    }
}
