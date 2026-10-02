package Assignment_Problems;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Subscriber {
    protected String name;
    protected LocalDate startDate;
    public Subscriber(String name, LocalDate startDate) {
        this.name = name;
        this.startDate = startDate;
    }
    public abstract LocalDate calculateRenewalDate();
    public String getName() { return name; }
}

class BasicSubscriber extends Subscriber {
    public BasicSubscriber(String name, LocalDate startDate) { super(name, startDate); }
    @Override public LocalDate calculateRenewalDate() { return startDate.plusDays(30); }
}

class StandardSubscriber extends Subscriber {
    public StandardSubscriber(String name, LocalDate startDate) { super(name, startDate); }
    @Override public LocalDate calculateRenewalDate() { return startDate.plusDays(90); }
}

class PremiumSubscriber extends Subscriber {
    public PremiumSubscriber(String name, LocalDate startDate) { super(name, startDate); }
    @Override public LocalDate calculateRenewalDate() { return startDate.plusDays(365); }
}

public class StreamingRenewal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        int n = scanner.nextInt();
        List<Subscriber> subscribers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String name = scanner.next();
            LocalDate start = LocalDate.parse(scanner.next());
            if (type.equalsIgnoreCase("BASIC")) {
                subscribers.add(new BasicSubscriber(name, start));
            } else if (type.equalsIgnoreCase("STANDARD")) {
                subscribers.add(new StandardSubscriber(name, start));
            } else if (type.equalsIgnoreCase("PREMIUM")) {
                subscribers.add(new PremiumSubscriber(name, start));
            }
        }
        scanner.close();

        for (Subscriber s : subscribers) {
            LocalDate renewal = s.calculateRenewalDate();
            System.out.println(s.getName() + ": " + renewal);
        }
    }
}
