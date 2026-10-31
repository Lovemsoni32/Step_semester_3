package java_fundamentals.assignment_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class L2_PrizeTable {

    public static int prizeTable(int[] marks) {
        if (marks == null || marks.length <= 1) {
            return 0;
        }
        int swaps = 0;
        int n = marks.length;
        for (int i = 0; i < n - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (marks[j] > marks[maxIdx]) {
                    maxIdx = j;
                }
            }
            if (maxIdx != i) {
                int temp = marks[i];
                marks[i] = marks[maxIdx];
                marks[maxIdx] = temp;
                swaps++;
            }
        }
        return swaps;
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

        int[] marks;
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
            marks = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                marks[i] = list.get(i);
            }
        } else {
            Scanner sc = new Scanner(input);
            List<Integer> list = new ArrayList<>();
            while (sc.hasNextInt()) {
                list.add(sc.nextInt());
            }
            if (list.size() > 0) {
                if (list.size() > 1 && list.get(0) == list.size() - 1) {
                    marks = new int[list.size() - 1];
                    for (int i = 1; i < list.size(); i++) {
                        marks[i - 1] = list.get(i);
                    }
                } else {
                    marks = new int[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        marks[i] = list.get(i);
                    }
                }
            } else {
                marks = new int[0];
            }
        }

        int swaps = prizeTable(marks);
        System.out.println(Arrays.toString(marks));
        System.out.println("swaps = " + swaps);
    }
}
