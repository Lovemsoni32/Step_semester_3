package oop.class_problems;

import java.util.Scanner;

public class L4_PaymentProcessing {

    interface PaymentMode {
        double processPayment(double amount);
        String getModeName();
        double calculateFee(double amount);
    }

    static class CashPayment implements PaymentMode {
        @Override
        public double processPayment(double amount) {
            return amount + calculateFee(amount);
        }

        @Override
        public String getModeName() {
            return "Cash";
        }

        @Override
        public double calculateFee(double amount) {
            return 0.0;
        }
    }

    static class CardPayment implements PaymentMode {
        @Override
        public double processPayment(double amount) {
            return amount + calculateFee(amount);
        }

        @Override
        public String getModeName() {
            return "Card";
        }

        @Override
        public double calculateFee(double amount) {
            return amount * 0.02;
        }
    }

    static class UpiPayment implements PaymentMode {
        @Override
        public double processPayment(double amount) {
            return amount + calculateFee(amount);
        }

        @Override
        public String getModeName() {
            return "UPI";
        }

        @Override
        public double calculateFee(double amount) {
            return 0.0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        PaymentMode[] modes = new PaymentMode[n];
        double[] amounts = new double[n];

        for (int i = 0; i < n; i++) {
            String modeType = scanner.next();
            double amt = scanner.nextDouble();
            amounts[i] = amt;

            if (modeType.equalsIgnoreCase("Card")) {
                modes[i] = new CardPayment();
            } else if (modeType.equalsIgnoreCase("UPI")) {
                modes[i] = new UpiPayment();
            } else {
                modes[i] = new CashPayment();
            }
        }

        double totalProcessed = 0.0;
        for (int i = 0; i < n; i++) {
            double processed = modes[i].processPayment(amounts[i]);
            double fee = modes[i].calculateFee(amounts[i]);
            System.out.println(modes[i].getModeName() + ": amount " + amounts[i] 
                    + ", fee " + fee + ", processed " + processed);
            totalProcessed += processed;
        }

        System.out.println("Total Amount Processed: " + totalProcessed);
    }
}

