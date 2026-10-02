package Assignment_Problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Customer {
    protected double amount;
    public Customer(double amount) {
        this.amount = amount;
    }
    public abstract double calculateFinalAmount();
    public abstract String getTypeName();
}

class Student extends Customer {
    public Student(double amount) { super(amount); }
    @Override public double calculateFinalAmount() { return amount * 0.90; }
    @Override public String getTypeName() { return "STUDENT"; }
}

class Staff extends Customer {
    public Staff(double amount) { super(amount); }
    @Override public double calculateFinalAmount() { return amount * 0.95; }
    @Override public String getTypeName() { return "STAFF"; }
}

class Guest extends Customer {
    public Guest(double amount) { super(amount); }
    @Override public double calculateFinalAmount() { return amount + 10; }
    @Override public String getTypeName() { return "GUEST"; }
}

public class CanteenBilling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            if (!scanner.hasNextInt()) return;
            int n = scanner.nextInt();
            List<Customer> customers = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String type = scanner.next();
                double amt = scanner.nextDouble();
                if (type.equalsIgnoreCase("STUDENT")) {
                    customers.add(new Student(amt));
                } else if (type.equalsIgnoreCase("STAFF")) {
                    customers.add(new Staff(amt));
                } else if (type.equalsIgnoreCase("GUEST")) {
                    customers.add(new Guest(amt));
                }
            }

            double grandTotal = 0;
            for (Customer c : customers) {
                double finalAmt = c.calculateFinalAmount();
                grandTotal += finalAmt;
                System.out.printf("%s: %.2f\n", c.getTypeName(), finalAmt);
            }
            System.out.printf("Total: %.2f\n", grandTotal);
        } finally {
            scanner.close();
        }
    }
}