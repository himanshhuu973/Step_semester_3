package Assignment_Problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Room {
    protected int units;
    public Room(int units) { this.units = units; }
    public abstract double calculateBill();
    public abstract String getTypeName();
}

class SingleRoom extends Room {
    public SingleRoom(int units) { super(units); }
    @Override public double calculateBill() { return units * 8.0; }
    @Override public String getTypeName() { return "SINGLE"; }
}

class SharedRoom extends Room {
    private int occupants;
    public SharedRoom(int units, int occupants) {
        super(units);
        this.occupants = occupants;
    }
    @Override public double calculateBill() {
        return (units * 6.0) / occupants;
    }
    @Override public String getTypeName() { return "SHARED"; }
}

class ACRoom extends Room {
    public ACRoom(int units) { super(units); }
    @Override public double calculateBill() { return (units * 10.0) + 200.0; }
    @Override public String getTypeName() { return "AC"; }
}

public class HostelElectricity {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            if (!scanner.hasNextInt()) return;
            int n = scanner.nextInt();
            List<Room> rooms = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String type = scanner.next();
                int units = scanner.nextInt();
                if (type.equalsIgnoreCase("SINGLE")) {
                    rooms.add(new SingleRoom(units));
                } else if (type.equalsIgnoreCase("SHARED")) {
                    int occupants = scanner.nextInt();
                    rooms.add(new SharedRoom(units, occupants));
                } else if (type.equalsIgnoreCase("AC")) {
                    rooms.add(new ACRoom(units));
                }
            }

            double grandTotal = 0;
            for (Room r : rooms) {
                double bill = r.calculateBill();
                grandTotal += bill;
                System.out.printf("%s: %.2f\n", r.getTypeName(), bill);
            }
            System.out.printf("Total: %.2f\n", grandTotal);
        } finally {
            scanner.close();
        }
    }
}