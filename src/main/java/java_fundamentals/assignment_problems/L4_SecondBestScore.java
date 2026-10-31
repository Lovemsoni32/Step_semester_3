package java_fundamentals.assignment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Problem 4: Second-Best Score
 *
 * Finds the second-highest DISTINCT score without sorting the array.
 * Uses highest and second-highest tracking variables.
 * Returns -1 if no second-highest distinct score exists.
 * Time Complexity: O(n)
 * Additional Space Complexity: O(1)
 */
public class L4_SecondBestScore {

    /**
     * Finds the second-highest distinct score in one pass.
     */
    public static int secondHighest(int[] scores) {
        if (scores == null || scores.length < 2) {
            return -1;
        }

        int highest = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int score : scores) {
            if (score > highest) {
                second = highest;
                highest = score;
            } else if (score < highest && score > second) {
                second = score;
            }
        }

        return (second == Integer.MIN_VALUE) ? -1 : second;
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

        int[] scores;
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
        } else {
            Scanner sc = new Scanner(input);
            List<Integer> list = new ArrayList<>();
            while (sc.hasNextInt()) {
                list.add(sc.nextInt());
            }
            if (list.size() > 1 && list.size() - 1 == list.get(0)) {
                scores = new int[list.size() - 1];
                for (int i = 1; i < list.size(); i++) {
                    scores[i - 1] = list.get(i);
                }
            } else {
                scores = new int[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    scores[i] = list.get(i);
                }
            }
        }

        int result = secondHighest(scores);
        System.out.println(result);
    }
}
