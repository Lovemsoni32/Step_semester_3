package oop.class_problems;

import java.util.Scanner;

public class L6_HostelElectricityBill {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        String[] roomNumbers = new String[n];
        int[] readings = new int[n];

        for (int i = 0; i < n; i++) {
            roomNumbers[i] = scanner.next();
            readings[i] = scanner.nextInt();
        }

        int billedRooms = 0;
        int totalAmount = 0;

        for (int i = 0; i < n; i++) {
            int units = readings[i];
            if (units < 0) {
                System.out.println(roomNumbers[i] + ": invalid reading");
            } else {
                int bill = 50;
                if (units <= 100) {
                    bill += units * 3;
                } else if (units <= 200) {
                    bill += 100 * 3 + (units - 100) * 5;
                } else {
                    bill += 100 * 3 + 100 * 5 + (units - 200) * 8;
                }
                System.out.println(roomNumbers[i] + ": " + units + " units, bill " + bill);
                billedRooms++;
                totalAmount += bill;
            }
        }

        System.out.println("Rooms billed " + billedRooms + ", total " + totalAmount);
    }
}

