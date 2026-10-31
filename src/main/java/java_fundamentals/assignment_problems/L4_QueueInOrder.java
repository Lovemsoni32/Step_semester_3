package java_fundamentals.assignment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class L4_QueueInOrder {

    public static int firstBreak(int[] tokens) {
        if (tokens == null || tokens.length <= 1) {
            return -1;
        }
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i] < tokens[i - 1]) {
                return i;
            }
        }
        return -1;
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

        int[] tokens;
        if (input.contains("[") && input.contains("]")) {
            int start = input.indexOf('[');
            int end = input.indexOf(']');
            String content = input.substring(start + 1, end).trim();
            String[] rawTokens = content.split("[,\\s]+");
            List<Integer> list = new ArrayList<>();
            for (String t : rawTokens) {
                if (!t.trim().isEmpty()) {
                    list.add(Integer.parseInt(t.trim()));
                }
            }
            tokens = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                tokens[i] = list.get(i);
            }
        } else {
            Scanner sc = new Scanner(input);
            List<Integer> list = new ArrayList<>();
            while (sc.hasNextInt()) {
                list.add(sc.nextInt());
            }
            if (list.size() > 0) {
                if (list.size() > 1 && list.get(0) == list.size() - 1) {
                    tokens = new int[list.size() - 1];
                    for (int i = 1; i < list.size(); i++) {
                        tokens[i - 1] = list.get(i);
                    }
                } else {
                    tokens = new int[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        tokens[i] = list.get(i);
                    }
                }
            } else {
                tokens = new int[0];
            }
        }

        System.out.println(firstBreak(tokens));
    }
}
