package kfa.service;

import kfa.exception.BookNotAvailableException;
import kfa.exception.ItemOverdueException;
import kfa.model.Book;
import kfa.model.LibraryItem;

public class LibrarySystem {

    // B1
    private String[][] shelves = new String[5][10];


    // B1
    public LibrarySystem() {

        for (int i = 0; i < shelves.length; i++) {

            for (int j = 0; j < shelves[i].length; j++) {

                shelves[i][j] = "";
            }
        }
    }
    public void borrowItem(LibraryItem item)
            throws BookNotAvailableException {

        if (!item.isAvailable()) {
            throw new BookNotAvailableException(
                    "Sorry, \"" + item.getTitle()
                            + "\" is currently unavailable."
            );
        }

        item.setAvailable(false);

        System.out.println(
                "Successfully borrowed: "
                        + item.getTitle()
        );
    }

    public void returnItem(
            LibraryItem item,
            int daysLate
    ) throws ItemOverdueException {

        if (daysLate > 0) {
            throw new ItemOverdueException(daysLate);
        }

        item.setAvailable(true);

        System.out.println(
                "Successfully returned: "
                        + item.getTitle()
        );
    }
    // B1
    public int[] placeOnShelf(String isbn) {

        for (int shelf = 0; shelf < shelves.length; shelf++) {

            for (int slot = 0; slot < shelves[shelf].length; slot++) {

                if (shelves[shelf][slot].equals("")) {

                    shelves[shelf][slot] = isbn;

                    return new int[]{shelf, slot};
                }
            }
        }

        return new int[]{-1, -1};
    }
    // B1
    public void printShelves() {

        System.out.println();
        System.out.println("=== B1: SHELF GRID ===");

        for (int shelf = 0; shelf < shelves.length; shelf++) {

            System.out.print("Shelf " + shelf + ": ");

            for (int slot = 0; slot < shelves[shelf].length; slot++) {

                if (shelves[shelf][slot].equals("")) {
                    System.out.print("[empty] ");
                } else {
                    System.out.print(
                            "[" + shelves[shelf][slot] + "] "
                    );
                }
            }

            System.out.println();
        }
    }
// === B2: FIND MOST EXPENSIVE BOOK ===

    public Book findMostExpensive(Book[] books) {

        Book mostExpensive = books[0];

        for (int i = 1; i < books.length; i++) {

            if (books[i].getPrice() > mostExpensive.getPrice()) {
                mostExpensive = books[i];
            }
        }

        return mostExpensive;
    }

// === B2: REVERSE IN PLACE ===

    public void reverseInPlace(Book[] books) {

        int left = 0;
        int right = books.length - 1;

        while (left < right) {

            Book temp = books[left];

            books[left] = books[right];

            books[right] = temp;

            left++;
            right--;
        }
    }
}



