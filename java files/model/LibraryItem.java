package kfa.model;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class LibraryItem {

    private String title;
    private String isbn;
    private double price;
    private boolean available;

    // B3: AtomicInteger starts with 3 copies
    private AtomicInteger copiesAvailable =
            new AtomicInteger(3);

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

    // B3: Get remaining copies
    public int getCopiesAvailable() {
        return copiesAvailable.get();
    }

    // B3: AtomicInteger borrow method
    public boolean borrowCopy() {

        while (true) {

            int current = copiesAvailable.get();

            if (current <= 0) {
                return false;
            }

            if (copiesAvailable.compareAndSet(
                    current,
                    current - 1)) {

                return true;
            }
        }
    }

    /*
     * B2 Explanation:
     *
     * The synchronized keyword uses the object's intrinsic lock (monitor)
     * so only one thread can execute borrowCopy() at a time.
     *
     * Synchronizing too broadly can hurt performance because threads
     * may have to wait even when they could safely execute independently.
     */

    /*
     * B3 Explanation:
     *
     * AtomicInteger uses lock-free atomic operations such as
     * compareAndSet(), while synchronized uses a traditional
     * monitor lock to control access between threads.
     */

    public abstract int getLendingPeriodDays();
}