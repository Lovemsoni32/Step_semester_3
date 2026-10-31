package java_fundamentals.assignment_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Problem 3: Duty Roster Rotation
 *
 * Rotates an array of names to the right by k positions in O(n) time and O(n) space.
 * Uses k % n to eliminate full rotations and avoids repeated 1-step shifting.
 * Time Complexity: O(n)
 * Additional Space Complexity: O(n)
 */
public class L3_DutyRosterRotation {

    /**
     * Rotates the names array to the right by k places.
     */
    public static String[] rotateRoster(String[] names, long k) {
        if (names == null || names.length <= 1) {
            return names;
        }

        int n = names.length;
        int effectiveK = (int) (k % n);
        if (effectiveK == 0) {
            return names.clone();
        }

        String[] rotated = new String[n];
        for (int i = 0; i < n; i++) {
            rotated[(i + effectiveK) % n] = names[i];
        }

        return rotated;
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

        String[] names;
        long k = 0;

        if (input.contains("[") && input.contains("]")) {
            int start = input.indexOf('[');
            int end = input.indexOf(']');
            String arrayContent = input.substring(start + 1, end).trim();

            String[] rawTokens = arrayContent.split("[,\\s]+");
            List<String> list = new ArrayList<>();
            for (String t : rawTokens) {
                String clean = t.replaceAll("[\"'\\s]", "").trim();
                if (!clean.isEmpty()) {
                    list.add(clean);
                }
            }
            names = list.toArray(new String[0]);

            // Find k in the remaining text
            String remainder = input.substring(end + 1);
            Pattern kPattern = Pattern.compile("(?i)k\\s*=\\s*(\\d+)");
            Matcher kMatcher = kPattern.matcher(remainder);
            if (kMatcher.find()) {
                k = Long.parseLong(kMatcher.group(1));
            } else {
                Pattern anyNum = Pattern.compile("(\\d+)");
                Matcher anyMatcher = anyNum.matcher(remainder);
                if (anyMatcher.find()) {
                    k = Long.parseLong(anyMatcher.group(1));
                }
            }
        } else {
            Scanner sc = new Scanner(input);
            int n = sc.nextInt();
            k = sc.nextLong();
            names = new String[n];
            for (int i = 0; i < n; i++) {
                names[i] = sc.next();
            }
        }

        String[] result = rotateRoster(names, k);
        System.out.println(Arrays.toString(result));
    }
}