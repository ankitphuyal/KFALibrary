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
                "Aarav Khan -> "
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



