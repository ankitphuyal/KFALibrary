package kfa.concurrency;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class BorrowCollectionDemo {

    // Thread-safe collection
    private final ConcurrentHashMap<String, String>
            currentlyBorrowed =
            new ConcurrentHashMap<>();

    public void recordBorrow(
            String member,
            String bookIsbn) {

        currentlyBorrowed.put(
                member,
                bookIsbn
        );
    }

    public int getBorrowedCount() {
        return currentlyBorrowed.size();
    }

    public void printBorrowedBooks() {

        System.out.println(
                "Currently borrowed collection:"
        );

        currentlyBorrowed.forEach(
                (member, isbn) ->
                        System.out.println(
                                member
                                        + " -> "
                                        + isbn
                        )
        );
    }

    public static void runTest() {

        System.out.println();
        System.out.println(
                "=== D3: THREAD-SAFE COLLECTION ==="
        );

        BorrowCollectionDemo demo =
                new BorrowCollectionDemo();

        Thread[] kiosks = new Thread[10];

        AtomicInteger successfulBorrows =
                new AtomicInteger(0);

        // Create 10 kiosk threads
        for (int i = 0; i < 10; i++) {

            final int kioskNumber = i + 1;

            kiosks[i] = new Thread(() -> {

                String member =
                        "Member-" + kioskNumber;

                String isbn =
                        "B" + String.format(
                                "%03d",
                                kioskNumber
                        );

                demo.recordBorrow(
                        member,
                        isbn
                );

                successfulBorrows.incrementAndGet();

                System.out.println(
                        Thread.currentThread().getName()
                                + " recorded borrow: "
                                + member
                                + " -> "
                                + isbn
                );
            });
        }

        // Start threads
        for (Thread kiosk : kiosks) {
            kiosk.start();
        }

        // Wait for all threads
        for (Thread kiosk : kiosks) {

            try {

                kiosk.join();

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            }
        }

        System.out.println();

        System.out.println(
                "Successful borrow records: "
                        + successfulBorrows.get()
        );

        System.out.println(
                "Final collection size: "
                        + demo.getBorrowedCount()
        );

        demo.printBorrowedBooks();

        /*
         * D3 Explanation:
         *
         * A normal HashMap or HashSet is not designed for multiple
         * threads modifying it at the same time. Concurrent updates,
         * especially during internal resizing, can cause lost or
         * inconsistent data.
         *
         * ConcurrentHashMap provides thread-safe operations so
         * multiple kiosk threads can safely update the collection.
         */
    }
}