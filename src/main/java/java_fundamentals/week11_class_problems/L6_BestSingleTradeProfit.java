package java_fundamentals.week11_class_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Problem 6: Best Single Trade Profit
 *
 * Finds maximum profit achievable by buying on one day and selling on a later day.
 * Single pass tracking minimum price so far and maximum achievable profit.
 * Time Complexity: O(n)
 * Additional Space Complexity: O(1)
 */
public class L6_BestSingleTradeProfit {

    public static class TradeResult {
        public final int profit;
        public final int buyPrice;
        public final int sellPrice;

        public TradeResult(int profit, int buyPrice, int sellPrice) {
            this.profit = profit;
            this.buyPrice = buyPrice;
            this.sellPrice = sellPrice;
        }
    }

    /**
     * Calculates maximum profit in a single pass.
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int minPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            int currentPrice = prices[i];
            if (currentPrice < minPrice) {
                minPrice = currentPrice;
            } else if (currentPrice - minPrice > maxProfit) {
                maxProfit = currentPrice - minPrice;
            }
        }

        return maxProfit;
    }

    /**
     * Calculates detailed trade result including buy and sell prices.
     */
    public static TradeResult maxProfitDetail(int[] prices) {
        if (prices == null || prices.length < 2) {
            return new TradeResult(0, 0, 0);
        }

        int minPrice = prices[0];
        int maxProfit = 0;
        int bestBuy = prices[0];
        int bestSell = prices[0];

        for (int i = 1; i < prices.length; i++) {
            int currentPrice = prices[i];
            if (currentPrice < minPrice) {
                minPrice = currentPrice;
            } else if (currentPrice - minPrice > maxProfit) {
                maxProfit = currentPrice - minPrice;
                bestBuy = minPrice;
                bestSell = currentPrice;
            }
        }

        return new TradeResult(maxProfit, bestBuy, bestSell);
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
                List<Integer> list = new ArrayList<>();
                for (String t : tokens) {
                    if (!t.trim().isEmpty()) {
                        list.add(Integer.parseInt(t.trim()));
                    }
                }
                int[] prices = new int[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    prices[i] = list.get(i);
                }
                printResult(prices);
            }
        } else {
            Scanner sc = new Scanner(input);
            List<Integer> list = new ArrayList<>();
            while (sc.hasNextInt()) {
                list.add(sc.nextInt());
            }
            if (list.size() > 0) {
                int[] prices;
                if (list.size() > 1 && list.get(0) == list.size() - 1) {
                    prices = new int[list.size() - 1];
                    for (int i = 1; i < list.size(); i++) {
                        prices[i - 1] = list.get(i);
                    }
                } else {
                    prices = new int[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        prices[i] = list.get(i);
                    }
                }
                printResult(prices);
            }
        }
    }

    private static void printResult(int[] prices) {
        TradeResult res = maxProfitDetail(prices);
        if (res.profit > 0) {
            System.out.println(res.profit + " (buy at " + res.buyPrice + ", sell at " + res.sellPrice + ")");
        } else {
            System.out.println("0");
        }
    }
}
