package java_fundamentals.week11_class_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Problem 2: Warehouse Bin Grid Scan
 *
 * Computes total item count and finds coordinates of the maximum bin.
 * Retains the first encountered coordinate in row-major order on tie.
 * Time Complexity: O(m * n)
 * Additional Space Complexity: O(1)
 */
public class L2_WarehouseBinGridScan {

    public static class WarehouseResult {
        public final int total;
        public final int maxRow;
        public final int maxCol;

        public WarehouseResult(int total, int maxRow, int maxCol) {
            this.total = total;
            this.maxRow = maxRow;
            this.maxCol = maxCol;
        }

        @Override
        public String toString() {
            return "total = " + total + ", maxCoordinate = (" + maxRow + ", " + maxCol + ")";
        }
    }

    /**
     * Traverses the grid, calculating total and first maximum bin coordinates.
     */
    public static WarehouseResult warehouseSummary(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return new WarehouseResult(0, 0, 0);
        }

        int total = 0;
        int maxVal = Integer.MIN_VALUE;
        int maxRow = 0;
        int maxCol = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                int val = grid[r][c];
                total += val;
                // Strict inequality retains first encountered on tie
                if (val > maxVal) {
                    maxVal = val;
                    maxRow = r;
                    maxCol = c;
                }
            }
        }

        return new WarehouseResult(total, maxRow, maxCol);
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

        int[][] grid = parseGrid(input);
        WarehouseResult result = warehouseSummary(grid);
        System.out.println(result);
    }

    private static int[][] parseGrid(String input) {
        if (input.contains("[")) {
            List<int[]> rowList = new ArrayList<>();
            Pattern pattern = Pattern.compile("\\[([^\\[\\]]+)\\]");
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String rowContent = matcher.group(1).trim();
                String[] tokens = rowContent.split("[,\\s]+");
                List<Integer> nums = new ArrayList<>();
                for (String t : tokens) {
                    if (!t.isEmpty()) {
                        nums.add(Integer.parseInt(t));
                    }
                }
                int[] row = new int[nums.size()];
                for (int i = 0; i < nums.size(); i++) {
                    row[i] = nums.get(i);
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
            int m = sc.nextInt();
            int n = sc.nextInt();
            int[][] grid = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }
            return grid;
        }
    }
}
