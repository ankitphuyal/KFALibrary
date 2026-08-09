package kfa.model;

public abstract class LibraryItem {

    private String title;
    private String isbn;
    private double price;
    private boolean available;

    public LibraryItem(String title, String isbn, double price) {
        this.title = title;
        this.isbn = isbn;
        setPrice(price);
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException(
                    "Price cannot be negative."
            );
        }

        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract int getLendingPeriodDays();
}