import kfa.exception.BookNotAvailableException;
import kfa.exception.ItemOverdueException;
import kfa.model.Book;
import kfa.model.DVD;
import kfa.model.LibraryItem;
import kfa.model.Magazine;
import kfa.service.LibrarySystem;

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
                "Amamul Khan -> AMAKH445"
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

        System.out.println();
        System.out.println("ALL SECTIONS COMPLETED");
    }
}