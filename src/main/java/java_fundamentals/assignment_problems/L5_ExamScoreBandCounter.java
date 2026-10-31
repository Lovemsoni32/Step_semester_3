package java_fundamentals.assignment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Problem 5: Exam Score Band Counter
 *
 * Counts scores within [low, high] inclusive in a sorted array using two modified binary searches.
 * Finds the first position where score >= low (lower bound) and first position where score > high (upper bound).
 * Time Complexity: O(log n)
 * Additional Space Complexity: O(1)
 */
public class L5_ExamScoreBandCounter {

    /**
     * Counts scores within [low, high] inclusive in O(log n) time.
     */
    public static int countInBand(int[] scores, int low, int high) {
        if (scores == null || scores.length == 0 || low > high) {
            return 0;
        }

        int lowerIndex = findFirstGreaterOrEqual(scores, low);
        int upperIndex = findFirstGreater(scores, high);

        return Math.max(0, upperIndex - lowerIndex);
    }

    /**
     * Returns the first index where scores[i] >= target.
     */
    public static int findFirstGreaterOrEqual(int[] scores, int target) {
        int left = 0;
        int right = scores.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (scores[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * Returns the first index where scores[i] > target.
     */
    public static int findFirstGreater(int[] scores, int target) {
        int left = 0;
        int right = scores.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (scores[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
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

        int[] scores;
        int low = 0;
        int high = 0;

        if (input.contains("[") && input.contains("]")) {
            int start = input.indexOf('[');
            int end = input.indexOf(']');
            String arrayContent = input.substring(start + 1, end).trim();

            String[] rawTokens = arrayContent.split("[,\\s]+");
            List<Integer> list = new ArrayList<>();
            for (String t : rawTokens) {
                if (!t.trim().isEmpty()) {
                    list.add(Integer.parseInt(t.trim()));
                }
            }
            scores = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                scores[i] = list.get(i);
            }

            // Extract low and high from remaining input
            String remainder = input.substring(end + 1);
            Pattern lowPat = Pattern.compile("(?i)low\\s*=\\s*(\\d+)");
            Matcher lowMat = lowPat.matcher(remainder);
            Pattern highPat = Pattern.compile("(?i)high\\s*=\\s*(\\d+)");
            Matcher highMat = highPat.matcher(remainder);

            if (lowMat.find() && highMat.find()) {
                low = Integer.parseInt(lowMat.group(1));
                high = Integer.parseInt(highMat.group(1));
            } else {
                // If not explicitly labeled with low= and high=, extract the next two numbers
                Pattern anyNum = Pattern.compile("(\\d+)");
                Matcher anyMatcher = anyNum.matcher(remainder);
                if (anyMatcher.find()) {
                    low = Integer.parseInt(anyMatcher.group(1));
                }
                if (anyMatcher.find()) {
                    high = Integer.parseInt(anyMatcher.group(1));
                }
            }
        } else {
            Scanner sc = new Scanner(input);
            int n = sc.nextInt();
            low = sc.nextInt();
            high = sc.nextInt();
            scores = new int[n];
            for (int i = 0; i < n; i++) {
                scores[i] = sc.nextInt();
            }
        }

        int result = countInBand(scores, low, high);
        System.out.println(result);
    }
}