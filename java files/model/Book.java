package kfa.model;

public class Book extends LibraryItem implements Renewable {

    private String author;
    private int renewedDays = 0;

    public Book(
            String title,
            String author,
            String isbn,
            double price) {

        super(title, isbn, price);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int getLendingPeriodDays() {
        return 14 + renewedDays;
    }

    @Override
    public void renew(int extraDays) {

        if (extraDays > 0) {
            renewedDays += extraDays;
        }
    }

    @Override
    public String toString() {

        String status =
                isAvailable()
                        ? "Available"
                        : "Not Available";

        return String.format(
                "[BOOK] %s by %s — Rs %.2f (%s)",
                getTitle(),
                author,
                getPrice(),
                status
        );
    }
}