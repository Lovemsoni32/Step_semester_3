package oop.class_problems;

import java.util.Scanner;

public class L1_CampusStaffSalary {

    static class Staff {
        protected String name;
        protected double baseSalary;

        public Staff(String name, double baseSalary) {
            this.name = name;
            this.baseSalary = baseSalary;
        }

        public double getBonus() {
            return 0.0;
        }

        public double getTotalSalary() {
            return baseSalary + getBonus();
        }

        public String getType() {
            return "Staff";
        }
    }

    static class Teacher extends Staff {
        public Teacher(String name, double baseSalary) {
            super(name, baseSalary);
        }

        @Override
        public double getBonus() {
            return 5000.0;
        }

        @Override
        public String getType() {
            return "Teacher";
        }
    }

    static class LabAssistant extends Staff {
        public LabAssistant(String name, double baseSalary) {
            super(name, baseSalary);
        }

        @Override
        public double getBonus() {
            return 2000.0;
        }

        @Override
        public String getType() {
            return "LabAssistant";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        Staff[] staffArray = new Staff[n];

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String name = scanner.next();
            double base = scanner.nextDouble();

            if (type.equalsIgnoreCase("Teacher")) {
                staffArray[i] = new Teacher(name, base);
            } else if (type.equalsIgnoreCase("LabAssistant")) {
                staffArray[i] = new LabAssistant(name, base);
            } else {
                staffArray[i] = new Staff(name, base);
            }
        }

        double grandTotal = 0.0;
        for (Staff member : staffArray) {
            System.out.println(member.name + " (" + member.getType() + "): base " 
                    + member.baseSalary + ", bonus " + member.getBonus() 
                    + ", total " + member.getTotalSalary());
            grandTotal += member.getTotalSalary();
        }

        System.out.println("Grand Total Salary: " + grandTotal);
    }
}

