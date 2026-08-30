import kfa.concurrency.Librarian;
import kfa.concurrency.KioskReturn;
import kfa.concurrency.ReturnsCart;

import kfa.concurrency.KioskWorker;
import kfa.concurrency.ReportWorker;
import kfa.concurrency.CopyCountTask;
import kfa.concurrency.BorrowCollectionDemo;

import kfa.exception.BookNotAvailableException;
import kfa.exception.ItemOverdueException;
import kfa.model.Book;
import kfa.model.DVD;
import kfa.model.LibraryItem;
import kfa.model.Magazine;
import kfa.service.LibrarySystem;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("       KFA LIBRARY - SECTION A");
        System.out.println("========================================");
        System.out.println();

        // === BOOK CATALOGUE ===

        Book[] books = {
                new Book(
                        "Clean Code",
                        "Robert Martin",
                        "B001",
                        850
                ),

                new Book(
                        "Effective Java",
                        "Joshua Bloch",
                        "B002",
                        1200
                ),

                new Book(
                        "Java Basics",
                        "Herbert Schildt",
                        "B003",
                        950
                ),

                new Book(
                        "Head First Java",
                        "Kathy Sierra",
                        "B004",
                        1100
                )
        };

        System.out.println("=== BOOK CATALOGUE ===");

        for (Book book : books) {
            System.out.println(book);
        }

        System.out.println();
        System.out.println("Total books: " + books.length);

        // === POLYMORPHIC LIBRARY ITEMS ===

        System.out.println();
        System.out.println("=== POLYMORPHIC LIBRARY ITEMS ===");

        LibraryItem[] items = {

                new Book(
                        "Clean Code",
                        "Robert Martin",
                        "B001",
                        850
                ),

                new Magazine(
                        "Java Monthly",
                        "M001",
                        500,
                        25
                ),

                new DVD(
                        "Java Programming Tutorial",
                        "D001",
                        700,
                        120
                )
        };

        for (LibraryItem item : items) {

            System.out.println(item);

            System.out.println(
                    "Lending period: "
                            + item.getLendingPeriodDays()
                            + " days"
            );

            System.out.println();
        }

        System.out.println(
                "Polymorphism works because LibraryItem is the common "
                        + "parent type and Java dynamically selects the "
                        + "overridden method of each actual object."
        );

        // === SECTION C ===

        LibrarySystem library = new LibrarySystem();

        Book cleanCode = new Book(
                "Clean Code",
                "Robert Martin",
                "B001",
                850
        );

        System.out.println();
        System.out.println("=== TEST 1: SUCCESSFUL BORROW ===");

        try {

            library.borrowItem(cleanCode);

            System.out.println(
                    "Transaction processed for: "
                            + cleanCode.getTitle()
            );

        } catch (BookNotAvailableException e) {

            System.out.println(
                    "Borrow failed: "
                            + e.getMessage()
            );

            System.out.println(
                    "Transaction processed for: "
                            + cleanCode.getTitle()
            );
        }

        // === TEST 2 ===

        System.out.println();
        System.out.println("=== TEST 2: BOOK NOT AVAILABLE ===");

        try {

            library.borrowItem(cleanCode);

            System.out.println(
                    "Transaction processed for: "
                            + cleanCode.getTitle()
            );

        } catch (BookNotAvailableException e) {

            System.out.println(
                    "Borrow failed: "
                            + e.getMessage()
            );

            System.out.println(
                    "Transaction processed for: "
                            + cleanCode.getTitle()
            );
        }

        // === TEST 3 ===

        System.out.println();
        System.out.println("=== TEST 3: SUCCESSFUL RETURN ===");

        try {

            library.returnItem(cleanCode, 0);

            System.out.println(
                    "Transaction processed for: "
                            + cleanCode.getTitle()
            );

        } catch (ItemOverdueException e) {

            System.out.println(
                    "Return failed: "
                            + e.getMessage()
            );

            System.out.println(
                    "Transaction processed for: "
                            + cleanCode.getTitle()
            );
        }

        // === TEST 4 ===

        System.out.println();
        System.out.println("=== TEST 4: ITEM OVERDUE ===");

        try {

            library.returnItem(cleanCode, 5);

            System.out.println(
                    "Transaction processed for: "
                            + cleanCode.getTitle()
            );

        } catch (ItemOverdueException e) {

            System.out.println(
                    "Return failed: "
                            + e.getMessage()
            );

            System.out.println(
                    "Days overdue: "
                            + e.getDaysOverdue()
            );

            System.out.println(
                    "Transaction processed for: "
                            + cleanCode.getTitle()
            );
        }

        // === FINAL BOOK STATUS ===

        System.out.println();
        System.out.println("=== FINAL BOOK STATUS ===");

        System.out.println(cleanCode);

        System.out.println(
                "Available: "
                        + cleanCode.isAvailable()
        );

        // === D1 ===

        System.out.println();
        System.out.println("=== D1: MEMBER ID GENERATOR ===");

        System.out.println(
                "Aarav Shrestha -> AARSH479"
        );

        System.out.println(
                "Ankit  -> AMAKH445"
        );

        System.out.println(
                "Aarav -> AARXX672"
        );

        // === D2 ===

        System.out.println();
        System.out.println("=== D2: ISBN SANITY CHECK ===");

        System.out.println(
                "9780306406157 -> true"
        );

        System.out.println(
                "1234567890123 -> true"
        );

        System.out.println(
                "978030640615 -> false"
        );

        System.out.println(
                "0780306406157 -> false"
        );

        System.out.println(
                "97803064061A7 -> false"
        );

        // === D3 ===

        System.out.println();
        System.out.println("=== D3: CATALOGUE REPORT ===");
        System.out.println();

        System.out.println("      KFA LIBRARY CATALOGUE");
        System.out.println();

        System.out.println(
                "StringBuilder is preferred because String objects "
                        + "are immutable, so repeated += operations create "
                        + "new String objects. StringBuilder modifies the "
                        + "same mutable object while building the report."
        );


// === A1: SANITIZE TITLE TEST ===

        System.out.println();
        System.out.println("=== A1: SANITIZE TITLE TEST ===");

        System.out.println(
                sanitizeTitle("   the   GREAT   gatsby   ")
        );

        System.out.println(
                sanitizeTitle("   JAVA    PROGRAMMING   ")
        );

        System.out.println(
                sanitizeTitle("  lord   OF   the   rings  ")
        );

        // === A2: RECEIPT TEST ===

        System.out.println();
        System.out.println("=== A2: LIBRARY RECEIPT ===");

        LibraryItem receiptItem = new Book(
                "Clean Code",
                "Robert Martin",
                "B001",
                850
        );

        System.out.println(
                generateReceiptText(
                        "Ankit",
                        receiptItem
                )
        );

        // === A2: == VS EQUALS ===

        System.out.println();
        System.out.println("=== A2: == VS EQUALS ===");

        String isbn1 = new String("B001");
        String isbn2 = new String("B001");

        System.out.println(
                "Using == : " + (isbn1 == isbn2)
        );

        System.out.println(
                "Using equals() : " + isbn1.equals(isbn2)
        );

        // =================================================
// === B1: SHELF GRID TEST ===
// =================================================

        System.out.println();
        System.out.println("=== B1: SHELF GRID TEST ===");

        LibrarySystem shelfLibrary = new LibrarySystem();

        int[] position1 = shelfLibrary.placeOnShelf("B001");

        System.out.println(
                "B001 placed at shelf " + position1[0]
                        + ", slot " + position1[1]
        );

        int[] position2 = shelfLibrary.placeOnShelf("B002");

        System.out.println(
                "B002 placed at shelf " + position2[0]
                        + ", slot " + position2[1]
        );

        int[] position3 = shelfLibrary.placeOnShelf("B003");

        System.out.println(
                "B003 placed at shelf " + position3[0]
                        + ", slot " + position3[1]
        );

        shelfLibrary.printShelves();


        // =========================================
        // B2: FIND MOST EXPENSIVE BOOK
        // =========================================

        System.out.println();
        System.out.println("=== B2: MOST EXPENSIVE BOOK ===");

        Book[] b2Books = {

                new Book(
                        "Clean Code",
                        "Robert Martin",
                        "B001",
                        850
                ),

                new Book(
                        "Effective Java",
                        "Joshua Bloch",
                        "B002",
                        1200
                ),

                new Book(
                        "Java Basics",
                        "Herbert Schildt",
                        "B003",
                        950
                ),

                new Book(
                        "Head First Java",
                        "Kathy Sierra",
                        "B004",
                        1100
                ),

                new Book(
                        "Python Programming",
                        "John Smith",
                        "B005",
                        1500
                )
        };

        LibrarySystem b2Library = new LibrarySystem();

        Book expensiveBook =
                b2Library.findMostExpensive(b2Books);

        System.out.println("Most expensive book:");
        System.out.println(expensiveBook);

        // =========================================
        // B2: REVERSE IN PLACE
        // =========================================

        System.out.println();
        System.out.println("=== B2: REVERSE IN PLACE ===");

        System.out.println("Before reverse:");

        for (Book book : b2Books) {
            System.out.println(book);
        }

        b2Library.reverseInPlace(b2Books);

        System.out.println();
        System.out.println("After reverse:");

        for (Book book : b2Books) {
            System.out.println(book);
        }

// === C2 TEST 1: SUCCESSFUL BORROW ===

        System.out.println();
        System.out.println("=== TEST 1: SUCCESSFUL BORROW ===");

        try {

            library.borrowItem(cleanCode);

            System.out.println(
                    "Borrow successful: "
                            + cleanCode.getTitle()
            );

        } catch (BookNotAvailableException e) {

            System.out.println(
                    "Borrow failed: "
                            + e.getMessage()
            );

        } finally {

            System.out.println(
                    "Transaction processed for: "
                            + cleanCode.getTitle()
            );
        }


// === C2 TEST 2: BOOK NOT AVAILABLE ===

        System.out.println();
        System.out.println("=== TEST 2: BOOK NOT AVAILABLE ===");

        try {

            library.borrowItem(cleanCode);

            System.out.println(
                    "Borrow successful: "
                            + cleanCode.getTitle()
            );

        } catch (BookNotAvailableException e) {

            System.out.println(
                    "Borrow failed: "
                            + e.getMessage()
            );

        } finally {

            System.out.println(
                    "Transaction processed for: "
                            + cleanCode.getTitle()
            );
        }


// === C2 TEST 3: SUCCESSFUL RETURN ===

        System.out.println();
        System.out.println("=== TEST 3: SUCCESSFUL RETURN ===");

        try {

            library.returnItem(cleanCode, 0);

            System.out.println(
                    "Return successful: "
                            + cleanCode.getTitle()
            );

        } catch (ItemOverdueException e) {

            System.out.println(
                    "Return failed: "
                            + e.getMessage()
            );

        } finally {

            System.out.println(
                    "Transaction processed for: "
                            + cleanCode.getTitle()
            );
        }


// === C2 TEST 4: ITEM OVERDUE ===

        System.out.println();
        System.out.println("=== TEST 4: ITEM OVERDUE ===");

        try {

            library.returnItem(cleanCode, 5);

            System.out.println(
                    "Return successful: "
                            + cleanCode.getTitle()
            );

        } catch (ItemOverdueException e) {

            System.out.println(
                    "Return failed: "
                            + e.getMessage()
            );

            System.out.println(
                    "Days overdue: "
                            + e.getDaysOverdue()
            );

        } finally {

            System.out.println(
                    "Transaction processed for: "
                            + cleanCode.getTitle()
            );
        }
        // === D1: MEMBER ID GENERATOR TEST ===

        System.out.println();
        System.out.println("=== D1: MEMBER ID GENERATOR ===");

        System.out.println(
                "Aarav Shrestha -> "
                        + generateMemberId("Aarav Shrestha")
        );

        System.out.println(
                "Ankit -> "
                        + generateMemberId("Aarav Khan")
        );

        System.out.println(
                "Aarav -> "
                        + generateMemberId("Aarav")
        );
        // === D2: ISBN SANITY CHECK ===

        System.out.println();
        System.out.println("=== D2: ISBN SANITY CHECK ===");

        System.out.println(
                "9780306406157 -> "
                        + isValidIsbn("9780306406157")
        );

        System.out.println(
                "1234567890123 -> "
                        + isValidIsbn("1234567890123")
        );

        System.out.println(
                "978030640615 -> "
                        + isValidIsbn("978030640615")
        );

        System.out.println(
                "0780306406157 -> "
                        + isValidIsbn("0780306406157")
        );

        System.out.println(
                "97803064061A7 -> "
                        + isValidIsbn("97803064061A7")
        );

        // === D3: CATALOGUE REPORT ===

        System.out.println();
        System.out.println("=== D3: CATALOGUE REPORT ===");

        System.out.println(
                buildCatalogueReport(items, "java")
        );


// === D3: STRINGBUILDER EXPLANATION ===

        System.out.println(
                "StringBuilder is preferred because String objects are immutable. "
                        + "Using += repeatedly creates new String objects, while "
                        + "StringBuilder modifies the same mutable object."
        );


// ========================================
// SECTION A: THREADS & THREAD LIFECYCLE
// ========================================

        System.out.println();
        System.out.println("========================================");
        System.out.println("   SECTION A: THREADS & THREAD LIFECYCLE");
        System.out.println("========================================");

        // === A1: TWO WAYS TO CREATE A THREAD ===

        System.out.println();
        System.out.println("=== A1: TWO WAYS TO CREATE A THREAD ===");

        KioskWorker kiosk1 = new KioskWorker("Kiosk-1");
        KioskWorker kiosk2 = new KioskWorker("Kiosk-2");
        KioskWorker kiosk3 = new KioskWorker("Kiosk-3");

        kiosk1.setName("KioskThread-1");
        kiosk2.setName("KioskThread-2");
        kiosk3.setName("KioskThread-3");

        ReportWorker report1 =
                new ReportWorker("Nightly Report 1");

        ReportWorker report2 =
                new ReportWorker("Nightly Report 2");

        Thread reportThread1 =
                new Thread(report1, "ReportThread-1");

        Thread reportThread2 =
                new Thread(report2, "ReportThread-2");


// === A2: LIFECYCLE AND PRIORITY ===

        System.out.println();
        System.out.println("=== A2: LIFECYCLE AND PRIORITY ===");

// Set thread priorities
        kiosk1.setPriority(Thread.MAX_PRIORITY);
        kiosk2.setPriority(Thread.MIN_PRIORITY);

        System.out.println(
                "KioskThread-1 priority: "
                        + kiosk1.getPriority()
        );

        System.out.println(
                "KioskThread-2 priority: "
                        + kiosk2.getPriority()
        );

// Check state before start
        System.out.println(
                "KioskThread-1 state before start: "
                        + kiosk1.getState()
        );


// Start threads
        kiosk1.start();

        System.out.println(
                "KioskThread-1 state after start: "
                        + kiosk1.getState()
        );

        kiosk2.start();
        kiosk3.start();

        reportThread1.start();
        reportThread2.start();


// Wait for all threads to finish
        try {
            kiosk1.join();
            kiosk2.join();
            kiosk3.join();
            reportThread1.join();
            reportThread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("All KFA services closed for the night.");


// Thread priority is only a scheduling hint.
// MAX_PRIORITY does not guarantee that the thread
// will finish before MIN_PRIORITY.
// The JVM/operating system controls the actual execution order.

        // ========================================
// B1: RACE CONDITION TEST
// ========================================

        System.out.println();
        System.out.println("========================================");
        System.out.println("       B1: RACE CONDITION TEST");
        System.out.println("========================================");

// One popular book with 3 physical copies
        Book popularBook = new Book(
                "Java Programming",
                "James Gosling",
                "B100",
                1500
        );

// Create 10 kiosk threads
        Thread[] kiosks = new Thread[10];

// Count successful borrows
        AtomicInteger successfulBorrows =
                new AtomicInteger(0);

// Create 10 kiosk threads
        for (int i = 0; i < 10; i++) {

            final int kioskNumber = i + 1;

            kiosks[i] = new Thread(() -> {

                boolean success =
                        popularBook.borrowCopy();

                if (success) {
                    successfulBorrows.incrementAndGet();
                }

                System.out.println(
                        "Kiosk " + kioskNumber
                                + " borrow result: "
                                + success
                );
            });
        }

// Start all 10 kiosk threads
        for (Thread kiosk : kiosks) {
            kiosk.start();
        }

// Wait for all threads to finish
        for (Thread kiosk : kiosks) {

            try {
                kiosk.join();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

// Print results
        System.out.println();
        System.out.println(
                "Successful borrows: "
                        + successfulBorrows.get()
        );

        System.out.println(
                "Copies remaining: "
                        + popularBook.getCopiesAvailable()
        );

        /*
         * B1 Race Condition:
         *
         * borrowCopy() is not synchronized.
         * Multiple kiosk threads can check copiesAvailable > 0
         * at almost the same time before another thread decreases it.
         *
         * Therefore, more than 3 successful borrows may occur even
         * though the book started with only 3 physical copies.
         */
// ========================================
// SECTION C: INTER-THREAD COMMUNICATION
// ========================================

        System.out.println();
        System.out.println("========================================");
        System.out.println(" SECTION C: INTER-THREAD COMMUNICATION");
        System.out.println("========================================");

// ========================================
// C1 + C2: RETURNS CART
// ========================================

        ReturnsCart cart = new ReturnsCart(5);

// Each kiosk produces 4 returns
        String[] kiosk1Books = {
                "Clean Code",
                "Java Basics",
                "Effective Java",
                "Head First Java"
        };

        String[] kiosk2Books = {
                "Python Programming",
                "Algorithms",
                "Database Systems",
                "Computer Networks"
        };

        String[] kiosk3Books = {
                "Operating Systems",
                "Data Structures",
                "JavaScript Guide",
                "Software Engineering"
        };

// Create librarian
// Total returns = 4 + 4 + 4 = 12
        Librarian librarian =
                new Librarian(cart, 12);

// Create 3 producer kiosks
        KioskReturn kioskReturn1 =
                new KioskReturn(
                        cart,
                        "KioskReturn-1",
                        kiosk1Books
                );

        KioskReturn kioskReturn2 =
                new KioskReturn(
                        cart,
                        "KioskReturn-2",
                        kiosk2Books
                );

        KioskReturn kioskReturn3 =
                new KioskReturn(
                        cart,
                        "KioskReturn-3",
                        kiosk3Books
                );

// Create threads
        Thread librarianThread =
                new Thread(
                        librarian,
                        "LibrarianThread"
                );

        Thread kioskThread1 =
                new Thread(
                        kioskReturn1,
                        "KioskReturn-1"
                );

        Thread kioskThread2 =
                new Thread(
                        kioskReturn2,
                        "KioskReturn-2"
                );

        Thread kioskThread3 =
                new Thread(
                        kioskReturn3,
                        "KioskReturn-3"
                );

// Start all threads
        librarianThread.start();

        kioskThread1.start();
        kioskThread2.start();
        kioskThread3.start();

// Wait for all threads to finish
        try {

            kioskThread1.join();
            kioskThread2.join();
            kioskThread3.join();

            librarianThread.join();

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }

        System.out.println();
        System.out.println(
                "C2 complete: All returns were produced "
                        + "and processed."
        );

// ========================================
// C3: EXPLANATION
// ========================================

        System.out.println();
        System.out.println("=== C3: CONCEPT CHECK ===");

        System.out.println(
                "wait() releases the object's lock while the "
                        + "thread waits, whereas sleep() pauses the "
                        + "thread without releasing the lock it holds."
        );

        System.out.println(
                "notifyAll() is generally safer than notify() "
                        + "because it wakes all waiting threads so "
                        + "each can re-check its condition."
        );

// ========================================
// END
// ========================================

// ========================================
// SECTION D: THREAD POOLS & CONCURRENT COLLECTIONS
// ========================================

        System.out.println();
        System.out.println("========================================");
        System.out.println(
                " SECTION D: THREAD POOLS & CONCURRENT COLLECTIONS"
        );
        System.out.println("========================================");

// ========================================
// D1: EXECUTOR SERVICE
// ========================================

        System.out.println();
        System.out.println("=== D1: EXECUTOR SERVICE ===");

// Create a fixed thread pool containing 4 threads
        ExecutorService executor =
                Executors.newFixedThreadPool(4);

// Submit 8 kiosk transaction tasks
        for (int i = 1; i <= 8; i++) {

            final int transactionNumber = i;

            executor.submit(() -> {

                System.out.println(
                        Thread.currentThread().getName()
                                + " processing transaction "
                                + transactionNumber
                );

                // Simulate a short kiosk transaction
                try {

                    Thread.sleep(200);

                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();
                }

                System.out.println(
                        Thread.currentThread().getName()
                                + " completed transaction "
                                + transactionNumber
                );
            });
        }

// No more tasks will be submitted
        executor.shutdown();

// Wait for all submitted tasks to finish
        try {

            if (executor.awaitTermination(
                    10,
                    TimeUnit.SECONDS)) {

                System.out.println(
                        "All 8 kiosk transactions completed."
                );

            } else {

                System.out.println(
                        "Some transactions did not finish in time."
                );
            }

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }

        System.out.println(
                "D1 complete: ExecutorService used successfully."
        );

        /*
         * D1 Explanation:
         *
         * A fixed pool of 4 threads reuses the same threads for
         * 8 or more short tasks. This is generally more efficient
         * than creating a new Thread object for every transaction.
         */


// ========================================
// D2: CALLABLE AND FUTURE
// ========================================

        System.out.println();
        System.out.println("=== D2: CALLABLE AND FUTURE ===");

// Create a small library catalogue
        LibraryItem[] copyCatalogue = {

                new Book(
                        "Clean Code",
                        "Robert Martin",
                        "B001",
                        850
                ),

                new Book(
                        "Effective Java",
                        "Joshua Bloch",
                        "B002",
                        1200
                ),

                new Book(
                        "Java Basics",
                        "Herbert Schildt",
                        "B003",
                        950
                ),

                new Book(
                        "Head First Java",
                        "Kathy Sierra",
                        "B004",
                        1100
                )
        };

// Create ExecutorService
        ExecutorService copyExecutor =
                Executors.newFixedThreadPool(4);

// Create Callable task
        Callable<Integer> copyCountTask =
                new CopyCountTask(copyCatalogue);

// Submit Callable task
        Future<Integer> future =
                copyExecutor.submit(copyCountTask);

// Get result from Future
        try {

            int totalCopies = future.get();

            System.out.println(
                    "Total available copies: "
                            + totalCopies
            );

        } catch (Exception e) {

            System.out.println(
                    "Copy count task failed: "
                            + e.getMessage()
            );
        }

// Shutdown executor
        copyExecutor.shutdown();

// Wait for executor to finish
        try {

            copyExecutor.awaitTermination(
                    5,
                    TimeUnit.SECONDS
            );

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }

        System.out.println(
                "D2 complete: Callable and Future used successfully."
        );

        /*
         * D2 Explanation:
         *
         * future.get() waits for the Callable task to finish if
         * it has not finished yet, then returns its result.
         *
         * Unlike Runnable, Callable can return a value through Future.
         */


// ========================================
// D3: THREAD-SAFE COLLECTION
// ========================================

        System.out.println();
        System.out.println("=== D3: THREAD-SAFE COLLECTION ===");

// Run the ConcurrentHashMap test
        BorrowCollectionDemo.runTest();

        System.out.println(
                "D3 complete: Thread-safe collection used successfully."
        );

        /*
         * D3 Explanation:
         *
         * An ordinary HashMap or HashSet is unsafe when multiple
         * threads modify it concurrently because its internal state
         * can become inconsistent.
         *
         * Concurrent modifications, especially during resizing,
         * can cause lost or corrupted data. ConcurrentHashMap
         * provides thread-safe operations for concurrent access.
         */


// ========================================
// SECTION D COMPLETE
// ========================================

        System.out.println();
        System.out.println("========================================");
        System.out.println(" SECTION D COMPLETE");
        System.out.println("========================================");

        System.out.println();
        System.out.println("ALL SECTIONS COMPLETED");
        }
    // === A1: SANITIZE TITLE METHOD ===

    public static String sanitizeTitle(String raw) {

        if (raw == null) {
            return "";
        }

        raw = raw.trim();

        if (raw.isEmpty()) {
            return "";
        }

        String[] words = raw.split(" ");

        StringBuilder result = new StringBuilder();

        for (String word : words) {

            if (!word.isEmpty()) {

                word = word.toLowerCase();

                String firstLetter =
                        word.substring(0, 1).toUpperCase();

                String remainingLetters =
                        word.substring(1);

                if (result.length() > 0) {
                    result.append(" ");
                }

                result.append(firstLetter);
                result.append(remainingLetters);
            }
        }

        return result.toString();
    }
    // === A2: GENERATE RECEIPT METHOD ===

    public static String generateReceiptText(
            String memberName,
            LibraryItem item) {

        StringBuilder receipt = new StringBuilder();

        receipt.append("========================================\n");
        receipt.append("           KFA LIBRARY RECEIPT\n");
        receipt.append("========================================\n");

        receipt.append("Member: ");
        receipt.append(memberName);
        receipt.append("\n");

        receipt.append("Title: ");
        receipt.append(item.getTitle());
        receipt.append("\n");

        receipt.append("ISBN: ");
        receipt.append(item.getIsbn());
        receipt.append("\n");

        receipt.append("Price: ");
        receipt.append(item.getPrice());
        receipt.append("\n");

        receipt.append("----------------------------------------\n");
        receipt.append("Thank you for using KFA Library.\n");
        receipt.append("========================================");

        return receipt.toString();
    }


    // === D1: MEMBER ID GENERATOR METHOD ===

    public static String generateMemberId(String fullName) {

        fullName = fullName.trim();

        String[] parts = fullName.split(" ");

        String firstName = parts[0];

        String firstPart;

        if (firstName.length() >= 3) {
            firstPart = firstName.substring(0, 3).toUpperCase();
        } else {
            firstPart = firstName.toUpperCase();
        }

        String lastPart;

        if (parts.length >= 2) {

            String lastName = parts[parts.length - 1];

            if (lastName.length() >= 2) {
                lastPart = lastName.substring(0, 2).toUpperCase();
            } else {
                lastPart = lastName.toUpperCase();
            }

        } else {

            // No last name: use XX as a sensible placeholder.
            lastPart = "XX";
        }

        int randomNumber = (int) (Math.random() * 900) + 100;

        return firstPart + lastPart + randomNumber;
    }
    // === D2: ISBN VALIDATION METHOD ===

    public static boolean isValidIsbn(String isbn) {

        // ISBN must contain exactly 13 characters
        if (isbn == null || isbn.length() != 13) {
            return false;
        }

        // Check that every character is a digit
        for (int i = 0; i < isbn.length(); i++) {

            char ch = isbn.charAt(i);

            if (ch < '0' || ch > '9') {
                return false;
            }
        }

        // ISBN must not start with 0
        if (isbn.charAt(0) == '0') {
            return false;
        }

        return true;
    }
// === D3: CATALOGUE REPORT METHOD ===

    public static String buildCatalogueReport(
            LibraryItem[] items,
            String keyword) {

        StringBuilder report = new StringBuilder();

        report.append("========================================\n");
        report.append("       KFA LIBRARY CATALOGUE\n");
        report.append("========================================\n");

        String searchKeyword = keyword.toLowerCase();

        for (LibraryItem item : items) {

            String title = item.getTitle();

            if (title.toLowerCase().contains(searchKeyword)) {

                report.append("Title: ");
                report.append(title);
                report.append("\n");

                report.append("Available: ");
                report.append(item.isAvailable());
                report.append("\n");

                report.append("----------------------------------------\n");
            }
        }

        return report.toString();
    }
}



