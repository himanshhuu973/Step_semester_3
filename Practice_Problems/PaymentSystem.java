package Practice_Problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Payment {
    protected double amount;
    public Payment(double amount) { this.amount = amount; }
    public abstract double calculateAdjustedAmount();
    public abstract String getTypeName();
}

class CardPayment extends Payment {
    public CardPayment(double amount) { super(amount); }
    @Override public double calculateAdjustedAmount() { return amount * 1.02; }
    @Override public String getTypeName() { return "CARD"; }
}

class WalletPayment extends Payment {
    public WalletPayment(double amount) { super(amount); }
    @Override public double calculateAdjustedAmount() { return amount * 1.01; }
    @Override public String getTypeName() { return "WALLET"; }
}

class BankTransferPayment extends Payment {
    public BankTransferPayment(double amount) { super(amount); }
    @Override public double calculateAdjustedAmount() { return amount; }
    @Override public String getTypeName() { return "BANKTRANSFER"; }
}

public class PaymentSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();
        List<Payment> payments = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double amt = scanner.nextDouble();
            if (type.equalsIgnoreCase("CARD")) payments.add(new CardPayment(amt));
            else if (type.equalsIgnoreCase("WALLET")) payments.add(new WalletPayment(amt));
            else if (type.equalsIgnoreCase("BANKTRANSFER")) payments.add(new BankTransferPayment(amt));
        }
        scanner.close();

        double grandTotal = 0;
        for (Payment p : payments) {
            double adj = p.calculateAdjustedAmount();
            grandTotal += adj;
            System.out.printf("%s: %.2f\n", p.getTypeName(), adj);
        }
        System.out.printf("Total: %.2f\n", grandTotal);
    }
}
