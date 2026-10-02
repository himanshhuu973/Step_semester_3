package Assignment_Problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Vehicle {
    protected int hours;
    public Vehicle(int hours) { this.hours = hours; }
    public abstract double calculateCharge();
    public abstract String getTypeName();
}

class Bike extends Vehicle {
    public Bike(int hours) { super(hours); }
    @Override public double calculateCharge() { return hours * 10.0; }
    @Override public String getTypeName() { return "BIKE"; }
}

class Car extends Vehicle {
    public Car(int hours) { super(hours); }
    @Override public double calculateCharge() {
        if (hours <= 1) return 30.0;
        return 30.0 + (hours - 1) * 20.0;
    }
    @Override public String getTypeName() { return "CAR"; }
}

class Truck extends Vehicle {
    public Truck(int hours) { super(hours); }
    @Override public double calculateCharge() {
        return Math.max(100.0, hours * 50.0);
    }
    @Override public String getTypeName() { return "TRUCK"; }
}

public class ParkingCalculator {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            if (!scanner.hasNextInt()) 
                return;
            int n = scanner.nextInt();
            List<Vehicle> vehicles = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String type = scanner.next();
                int hrs = scanner.nextInt();
                if (type.equalsIgnoreCase("BIKE")) vehicles.add(new Bike(hrs));
                else if (type.equalsIgnoreCase("CAR")) vehicles.add(new Car(hrs));
                else if (type.equalsIgnoreCase("TRUCK")) vehicles.add(new Truck(hrs));
            }

            double grandTotal = 0;
            for (Vehicle v : vehicles) {
                double charge = v.calculateCharge();
                grandTotal += charge;
                System.out.printf("%s: %.2f\n", v.getTypeName(), charge);
            }
            System.out.printf("Total: %.2f\n", grandTotal);
        }
    }
}