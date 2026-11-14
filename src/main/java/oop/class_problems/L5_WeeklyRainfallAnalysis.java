package oop.class_problems;

import java.util.Locale;
import java.util.Scanner;

public class L5_WeeklyRainfallAnalysis {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] rainfall = new double[7];

        for (int i = 0; i < 7; i++) {
            if (scanner.hasNextDouble()) {
                rainfall[i] = scanner.nextDouble();
            }
        }

        double total = 0.0;
        double maxRainfall = -1.0;
        int wettestDay = 1;
        int dryDays = 0;

        StringBuilder sb = new StringBuilder("Rainfall (mm):");
        for (int i = 0; i < 7; i++) {
            sb.append(" ").append(rainfall[i]);
            total += rainfall[i];

            if (rainfall[i] > maxRainfall) {
                maxRainfall = rainfall[i];
                wettestDay = i + 1;
            }

            if (rainfall[i] == 0.0) {
                dryDays++;
            }
        }

        double average = total / 7.0;

        System.out.println(sb.toString());
        System.out.println("Total Rainfall: " + total + " mm");
        System.out.println("Average Rainfall: " + String.format(Locale.US, "%.2f", average) + " mm");
        System.out.println("Wettest Day: Day " + wettestDay + " (" + maxRainfall + " mm)");
        System.out.println("Total Dry Days: " + dryDays);
    }
}

