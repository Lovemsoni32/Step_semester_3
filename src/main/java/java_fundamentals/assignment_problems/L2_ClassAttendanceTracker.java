package java_fundamentals.assignment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Problem 2: Class Attendance Tracker
 *
 * Calculates the total number of days a student was present (1s) and the
 * longest unbroken consecutive run of present days in a single pass.
 * Time Complexity: O(n)
 * Additional Space Complexity: O(1)
 */
public class L2_ClassAttendanceTracker {

    public static class AttendanceResult {
        public final int presentDays;
        public final int longestStreak;

        public AttendanceResult(int presentDays, int longestStreak) {
            this.presentDays = presentDays;
            this.longestStreak = longestStreak;
        }

        @Override
        public String toString() {
            return "Present: " + presentDays + ", Longest streak: " + longestStreak;
        }
    }

    /**
     * Computes the present days and longest streak in a single traversal.
     */
    public static AttendanceResult attendanceSummary(int[] days) {
        if (days == null || days.length == 0) {
            return new AttendanceResult(0, 0);
        }

        int presentCount = 0;
        int currentStreak = 0;
        int maxStreak = 0;

        for (int day : days) {
            if (day == 1) {
                presentCount++;
                currentStreak++;
                if (currentStreak > maxStreak) {
                    maxStreak = currentStreak;
                }
            } else {
                currentStreak = 0;
            }
        }

        return new AttendanceResult(presentCount, maxStreak);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNext()) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()).append("\n");
        }
        String input = sb.toString().trim();

        int[] days;
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
            days = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                days[i] = list.get(i);
            }
        } else {
            Scanner sc = new Scanner(input);
            List<Integer> list = new ArrayList<>();
            while (sc.hasNextInt()) {
                list.add(sc.nextInt());
            }
            if (list.size() > 1 && list.size() - 1 == list.get(0)) {
                days = new int[list.size() - 1];
                for (int i = 1; i < list.size(); i++) {
                    days[i - 1] = list.get(i);
                }
            } else {
                days = new int[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    days[i] = list.get(i);
                }
            }
        }

        AttendanceResult result = attendanceSummary(days);
        System.out.println(result);
    }
}
