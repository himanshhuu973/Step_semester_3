package Practice_Problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Delivery {
    protected double weight, distance;
    public Delivery(double weight, double distance) {
        this.weight = weight;
        this.distance = distance;
    }
    public abstract double calculateFee();
    public abstract String getTypeName();
}

class StandardDelivery extends Delivery {
    public StandardDelivery(double weight, double distance) { super(weight, distance); }
    @Override public double calculateFee() { return 5.0 + (0.50 * weight) + (0.10 * distance); }
    @Override public String getTypeName() { return "STANDARD"; }
}

class ExpressDelivery extends Delivery {
    public ExpressDelivery(double weight, double distance) { super(weight, distance); }
    @Override public double calculateFee() { return 15.0 + (1.00 * weight) + (0.20 * distance); }
    @Override public String getTypeName() { return "EXPRESS"; }
}

class InternationalDelivery extends Delivery {
    private double customsFee;
    public InternationalDelivery(double weight, double distance, double customsFee) {
        super(weight, distance);
        this.customsFee = customsFee;
    }
    @Override public double calculateFee() {
        return 25.0 + (2.00 * weight) + (0.50 * distance) + customsFee;
    }
    @Override public String getTypeName() { return "INTERNATIONAL"; }
}

public class DeliveryFeeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();
        List<Delivery> deliveries = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double weight = scanner.nextDouble();
            double distance = scanner.nextDouble();
            if (type.equalsIgnoreCase("STANDARD")) {
                deliveries.add(new StandardDelivery(weight, distance));
            } else if (type.equalsIgnoreCase("EXPRESS")) {
                deliveries.add(new ExpressDelivery(weight, distance));
            } else if (type.equalsIgnoreCase("INTERNATIONAL")) {
                double customs = scanner.nextDouble();
                deliveries.add(new InternationalDelivery(weight, distance, customs));
            }
        }
        scanner.close();

        double grandTotal = 0;
        for (Delivery d : deliveries) {
            double fee = d.calculateFee();
            grandTotal += fee;
            System.out.printf("%s: %.2f\n", d.getTypeName(), fee);
        }
        System.out.printf("Total: %.2f\n", grandTotal);
    }
}
