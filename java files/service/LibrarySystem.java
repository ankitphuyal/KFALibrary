package kfa.service;

import kfa.exception.BookNotAvailableException;
import kfa.exception.ItemOverdueException;
import kfa.model.LibraryItem;

public class LibrarySystem {

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
}