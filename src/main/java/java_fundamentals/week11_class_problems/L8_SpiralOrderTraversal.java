package java_fundamentals.week11_class_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Problem 8: Spiral Order Traversal
 *
 * Traverses a 2D grid in spiral clockwise order using four boundaries.
 * Supports rectangular and square matrices without duplicate elements.
 * Time Complexity: O(m * n)
 * Additional Space Complexity: O(m * n) for output list
 */
public class L8_SpiralOrderTraversal {

    /**
     * Returns flat list of elements in clockwise spiral order.
     */
    public static List<Integer> spiralOrder(int[][] grid) {
        List<Integer> result = new ArrayList<>();
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return result;
        }

        int top = 0;
        int bottom = grid.length - 1;
        int left = 0;
        int right = grid[0].length - 1;

        while (top <= bottom && left <= right) {
            // Traverse from Left to Right along top boundary
            for (int col = left; col <= right; col++) {
                result.add(grid[top][col]);
            }
            top++;

            // Traverse from Top to Bottom along right boundary
            for (int row = top; row <= bottom; row++) {
                result.add(grid[row][right]);
            }
            right--;

            // Traverse from Right to Left along bottom boundary if still valid
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    result.add(grid[bottom][col]);
                }
                bottom--;
            }

            // Traverse from Bottom to Top along left boundary if still valid
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    result.add(grid[row][left]);
                }
                left++;
            }
        }

        return result;
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
        List<Integer> result = spiralOrder(grid);
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
