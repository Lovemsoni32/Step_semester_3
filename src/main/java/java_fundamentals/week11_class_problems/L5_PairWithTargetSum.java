package java_fundamentals.week11_class_problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Problem 5: Pair With Target Sum (Unsorted)
 *
 * Uses HashSet to determine if two distinct elements sum to target.
 * Time Complexity: O(n)
 * Additional Space Complexity: O(n)
 */
public class L5_PairWithTargetSum {

    /**
     * Determines whether any two distinct elements add up to target.
     */
    public static boolean hasPairWithSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            int complement = target - num;
            if (seen.contains(complement)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }

    /**
     * Finds the matching pair [a, b] if exists, else returns null.
     */
    public static int[] findPair(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }

        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            int complement = target - num;
            if (seen.contains(complement)) {
                return new int[]{complement, num};
            }
            seen.add(num);
        }
        return null;
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
            Pattern linePattern = Pattern.compile("nums\\s*=\\s*\\[([^\\]]+)\\]\\s*,\\s*target\\s*=\\s*(-?\\d+)");
            Matcher lineMatcher = linePattern.matcher(input);
            boolean matchedAny = false;
            while (lineMatcher.find()) {
                matchedAny = true;
                String[] tokens = lineMatcher.group(1).split("[,\\s]+");
                int target = Integer.parseInt(lineMatcher.group(2));
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
                printResult(nums, target);
            }

            if (!matchedAny) {
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
                printResult(nums, target);
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
                printResult(nums, target);
            }
        }
    }

    private static void printResult(int[] nums, int target) {
        int[] pair = findPair(nums, target);
        if (pair != null) {
            System.out.println("true (" + pair[0] + " + " + pair[1] + " = " + target + ")");
        } else {
            System.out.println("false");
        }
    }
}
