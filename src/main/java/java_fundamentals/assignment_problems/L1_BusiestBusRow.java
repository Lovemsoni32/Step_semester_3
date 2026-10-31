package java_fundamentals.assignment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Problem 1: Busiest Bus Row
 *
 * Finds the index of the row in a 2D seat grid with the largest total number of students.
 * In case of a tie, returns the row with the smaller index.
 * Time Complexity: O(r * c)
 * Additional Space Complexity: O(1)
 */
public class L1_BusiestBusRow {

    public static class RowResult {
        public final int rowIndex;
        public final int total;

        public RowResult(int rowIndex, int total) {
            this.rowIndex = rowIndex;
            this.total = total;
        }

        @Override
        public String toString() {
            return "Row " + rowIndex + ", Total " + total;
        }
    }

    /**
     * Determines the row with the maximum sum of seats.
     */
    public static RowResult busiestRow(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return new RowResult(0, 0);
        }

        int bestRow = 0;
        int maxTotal = Integer.MIN_VALUE;

        for (int i = 0; i < grid.length; i++) {
            int currentTotal = 0;
            if (grid[i] != null) {
                for (int j = 0; j < grid[i].length; j++) {
                    currentTotal += grid[i][j];
                }
            }
            // Update only when strictly larger to preserve smaller index on tie
            if (currentTotal > maxTotal) {
                maxTotal = currentTotal;
                bestRow = i;
            }
        }

        return new RowResult(bestRow, maxTotal);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNext()) {
            return;
        }

        StringBuilder fullInput = new StringBuilder();
        while (scanner.hasNextLine()) {
            fullInput.append(scanner.nextLine()).append("\n");
        }

        int[][] grid = parseGrid(fullInput.toString().trim());
        RowResult result = busiestRow(grid);
        System.out.println(result);
    }

    private static int[][] parseGrid(String input) {
        if (input.contains("[")) {
            List<int[]> rowList = new ArrayList<>();
            // Match inner bracket rows: [ ... ]
            Pattern pattern = Pattern.compile("\\[([^\\[\\]]+)\\]");
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String rowContent = matcher.group(1).trim();
                String[] tokens = rowContent.split("[,\\s]+");
                List<Integer> numbers = new ArrayList<>();
                for (String t : tokens) {
                    if (!t.isEmpty()) {
                        numbers.add(Integer.parseInt(t));
                    }
                }
                int[] row = new int[numbers.size()];
                for (int i = 0; i < numbers.size(); i++) {
                    row[i] = numbers.get(i);
                }
                rowList.add(row);
            }

            int[][] grid = new int[rowList.size()][];
            for (int i = 0; i < rowList.size(); i++) {
                grid[i] = rowList.get(i);
            }
            return grid;
        } else {
            Scanner sc = new Scanner(input);
            int r = sc.nextInt();
            int c = sc.nextInt();
            int[][] grid = new int[r][c];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }
            return grid;
        }
    }
}