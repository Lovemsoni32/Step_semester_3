package java_fundamentals.week11_class_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Problem 1: Library Catalog Lookup
 *
 * Uses binary search on a sorted catalog by ISBN string.
 * Preserves leading zeros.
 * Time Complexity: O(log n)
 * Additional Space Complexity: O(1)
 */
public class L1_LibraryCatalogLookup {

    public static class BookRecord {
        public final String isbn;
        public final String title;

        public BookRecord(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }
    }

    /**
     * Binary search on sorted book records by ISBN.
     */
    public static String findBook(BookRecord[] catalog, String targetIsbn) {
        if (catalog == null || catalog.length == 0 || targetIsbn == null) {
            return "Not Found";
        }

        int left = 0;
        int right = catalog.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = catalog[mid].isbn.compareTo(targetIsbn);

            if (cmp == 0) {
                return catalog[mid].title;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return "Not Found";
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

        List<BookRecord> records = new ArrayList<>();
        List<String> targetIsbns = new ArrayList<>();

        if (input.contains("(") && input.contains(")")) {
            Pattern recPattern = Pattern.compile("\\(\\s*\"([^\"]+)\"\\s*,\\s*\"([^\"]+)\"\\s*\\)");
            Matcher recMatcher = recPattern.matcher(input);
            while (recMatcher.find()) {
                records.add(new BookRecord(recMatcher.group(1).trim(), recMatcher.group(2).trim()));
            }

            Pattern targetPattern = Pattern.compile("(?i)targetIsbn\\s*=\\s*\"([^\"]+)\"");
            Matcher targetMatcher = targetPattern.matcher(input);
            while (targetMatcher.find()) {
                targetIsbns.add(targetMatcher.group(1).trim());
            }

            if (targetIsbns.isEmpty()) {
                int closeBracket = input.lastIndexOf(']');
                if (closeBracket != -1) {
                    String after = input.substring(closeBracket + 1);
                    Pattern strPattern = Pattern.compile("\"([^\"]+)\"");
                    Matcher sm = strPattern.matcher(after);
                    while (sm.find()) {
                        targetIsbns.add(sm.group(1).trim());
                    }
                }
            }
        } else {
            Scanner sc = new Scanner(input);
            if (sc.hasNextInt()) {
                int n = sc.nextInt();
                for (int i = 0; i < n; i++) {
                    String isbn = sc.next();
                    String title = sc.nextLine().trim();
                    records.add(new BookRecord(isbn, title));
                }
                while (sc.hasNext()) {
                    targetIsbns.add(sc.next());
                }
            }
        }

        BookRecord[] catalog = records.toArray(new BookRecord[0]);
        for (String target : targetIsbns) {
            String result = findBook(catalog, target);
            if ("Not Found".equals(result)) {
                System.out.println("\"Not Found\"");
            } else {
                System.out.println("\"" + result + "\"");
            }
        }
    }
}
