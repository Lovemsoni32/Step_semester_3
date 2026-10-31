package java_fundamentals.assignment_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class L5_DiceThrowTally {

    public static int[] diceSort(int[] rolls) {
        if (rolls == null) {
            return new int[0];
        }
        int[] count = new int[7];
        for (int val : rolls) {
            if (val >= 1 && val <= 6) {
                count[val]++;
            }
        }
        StringBuilder sb = new StringBuilder("Counts:");
        for (int v = 1; v <= 6; v++) {
            sb.append(" ").append(v).append(":").append(count[v]);
        }
        System.out.println(sb.toString());

        int[] sorted = new int[rolls.length];
        int idx = 0;
        for (int v = 1; v <= 6; v++) {
            for (int c = 0; c < count[v]; c++) {
                sorted[idx++] = v;
            }
        }
        System.out.println("Sorted: " + Arrays.toString(sorted));
        return sorted;
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

        int[] rolls;
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
            rolls = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                rolls[i] = list.get(i);
            }
        } else {
            Scanner sc = new Scanner(input);
            List<Integer> list = new ArrayList<>();
            while (sc.hasNextInt()) {
                list.add(sc.nextInt());
            }
            if (list.size() > 0) {
                if (list.size() > 1 && list.get(0) == list.size() - 1) {
                    rolls = new int[list.size() - 1];
                    for (int i = 1; i < list.size(); i++) {
                        rolls[i - 1] = list.get(i);
                    }
                } else {
                    rolls = new int[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        rolls[i] = list.get(i);
                    }
                }
            } else {
                rolls = new int[0];
            }
        }

        diceSort(rolls);
    }
}
