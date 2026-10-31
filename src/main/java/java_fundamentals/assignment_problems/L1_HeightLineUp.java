package java_fundamentals.assignment_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class L1_HeightLineUp {

    public static void lineUp(int[] heights) {
        if (heights == null || heights.length <= 1) {
            return;
        }
        int n = heights.length;
        for (int i = 1; i <= n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1; j++) {
                if (heights[j] > heights[j + 1]) {
                    int temp = heights[j];
                    heights[j] = heights[j + 1];
                    heights[j + 1] = temp;
                    swapped = true;
                }
            }
            System.out.println("Pass " + i + ": " + Arrays.toString(heights));
            if (!swapped) {
                break;
            }
        }
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
            int[] heights = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                heights[i] = list.get(i);
            }
            lineUp(heights);
        } else {
            Scanner sc = new Scanner(input);
            List<Integer> list = new ArrayList<>();
            while (sc.hasNextInt()) {
                list.add(sc.nextInt());
            }
            if (list.size() > 0) {
                int[] heights;
                if (list.size() > 1 && list.get(0) == list.size() - 1) {
                    heights = new int[list.size() - 1];
                    for (int i = 1; i < list.size(); i++) {
                        heights[i - 1] = list.get(i);
                    }
                } else {
                    heights = new int[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        heights[i] = list.get(i);
                    }
                }
                lineUp(heights);
            }
        }
    }
}
