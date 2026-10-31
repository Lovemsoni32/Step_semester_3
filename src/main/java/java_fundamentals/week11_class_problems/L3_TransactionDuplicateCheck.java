package java_fundamentals.week11_class_problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Problem 3: Transaction Duplicate Check
 *
 * Checks if any transaction ID appears more than once using HashSet.
 * Time Complexity: O(n) average
 * Additional Space Complexity: O(n)
 */
public class L3_TransactionDuplicateCheck {

    /**
     * Returns true if any duplicate transaction ID exists, false otherwise.
     */
    public static boolean hasDuplicate(String[] transactionIds) {
        if (transactionIds == null || transactionIds.length <= 1) {
            return false;
        }

        Set<String> seen = new HashSet<>();
        for (String id : transactionIds) {
            if (!seen.add(id)) {
                return true;
            }
        }
        return false;
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
            Pattern pattern = Pattern.compile("\\[([^\\]]+)\\]");
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                String content = matcher.group(1);
                String[] tokens = content.split("[,\\s]+");
                List<String> list = new ArrayList<>();
                for (String t : tokens) {
                    String clean = t.replaceAll("[\"'\\s]", "").trim();
                    if (!clean.isEmpty()) {
                        list.add(clean);
                    }
                }
                String[] ids = list.toArray(new String[0]);
                System.out.println(hasDuplicate(ids));
            }
        } else {
            Scanner sc = new Scanner(input);
            List<String> list = new ArrayList<>();
            while (sc.hasNext()) {
                list.add(sc.next());
            }
            if (list.size() > 0) {
                try {
                    int n = Integer.parseInt(list.get(0));
                    if (list.size() - 1 == n) {
                        String[] ids = new String[n];
                        for (int i = 0; i < n; i++) {
                            ids[i] = list.get(i + 1);
                        }
                        System.out.println(hasDuplicate(ids));
                        return;
                    }
                } catch (NumberFormatException ignored) {}

                String[] ids = list.toArray(new String[0]);
                System.out.println(hasDuplicate(ids));
            }
        }
    }
}
