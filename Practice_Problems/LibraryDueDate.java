package Practice_Problems;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class LibraryItem {
    protected String title;
    protected LocalDate currentDate;
    public LibraryItem(String title, LocalDate currentDate) {
        this.title = title;
        this.currentDate = currentDate;
    }
    public abstract LocalDate calculateDueDate();
    public String getTitle() { return title; }
}

class BookItem extends LibraryItem {
    public BookItem(String title, LocalDate currentDate) { super(title, currentDate); }
    @Override public LocalDate calculateDueDate() { return currentDate.plusDays(14); }
}

class DVDItem extends LibraryItem {
    public DVDItem(String title, LocalDate currentDate) { super(title, currentDate); }
    @Override public LocalDate calculateDueDate() { return currentDate.plusDays(7); }
}

class MagazineItem extends LibraryItem {
    public MagazineItem(String title, LocalDate currentDate) { super(title, currentDate); }
    @Override public LocalDate calculateDueDate() { return currentDate.plusDays(3); }
}

public class LibraryDueDate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();
        LocalDate currentDate = LocalDate.parse("2023-10-26");
        List<LibraryItem> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String title = scanner.nextLine().trim();
            // Remove surrounding quotes if present
            if (title.startsWith("\"") && title.endsWith("\"")) {
                title = title.substring(1, title.length() - 1);
            }
            if (type.equalsIgnoreCase("BOOK")) items.add(new BookItem(title, currentDate));
            else if (type.equalsIgnoreCase("DVD")) items.add(new DVDItem(title, currentDate));
            else if (type.equalsIgnoreCase("MAGAZINE")) items.add(new MagazineItem(title, currentDate));
        }
        scanner.close();

        for (LibraryItem item : items) {
            System.out.println(item.getTitle() + ": " + item.calculateDueDate());
        }
    }
}