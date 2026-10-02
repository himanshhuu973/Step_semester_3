package Practice_Problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Journey {
    protected double distance;
    public Journey(double distance) { this.distance = distance; }
    public abstract double calculateFare();
    public abstract String getTypeName();
}

class BusJourney extends Journey {
    public BusJourney(double distance) { super(distance); }
    @Override public double calculateFare() {
        double fare = 2.0 + (0.10 * distance);
        return Math.min(fare, 10.0);
    }
    @Override public String getTypeName() { return "BUS"; }
}

class TrainJourney extends Journey {
    public TrainJourney(double distance) { super(distance); }
    @Override public double calculateFare() { return 3.0 + (0.15 * distance); }
    @Override public String getTypeName() { return "TRAIN"; }
}

class MetroJourney extends Journey {
    private double peakHourFactor;
    public MetroJourney(double distance, double peakHourFactor) {
        super(distance);
        this.peakHourFactor = peakHourFactor;
    }
    @Override public double calculateFare() {
        return (1.50 + (0.20 * distance)) * peakHourFactor;
    }
    @Override public String getTypeName() { return "METRO"; }
}

public class TransportFareCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();
        List<Journey> journeys = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double distance = scanner.nextDouble();
            if (type.equalsIgnoreCase("BUS")) {
                journeys.add(new BusJourney(distance));
            } else if (type.equalsIgnoreCase("TRAIN")) {
                journeys.add(new TrainJourney(distance));
            } else if (type.equalsIgnoreCase("METRO")) {
                double factor = scanner.nextDouble();
                journeys.add(new MetroJourney(distance, factor));
            }
        }
        scanner.close();

        double grandTotal = 0;
        for (Journey j : journeys) {
            double fare = j.calculateFare();
            grandTotal += fare;
            System.0out.printf("%s: %.2f\n", j.getTypeName(), fare); // Fixed typo to System.out
        }
        System.out.printf("Total: %.2f\n", grandTotal);
    }
}