package kfa.concurrency;

import kfa.model.LibraryItem;

import java.util.concurrent.Callable;

public class CopyCountTask implements Callable<Integer> {

    private final LibraryItem[] catalogue;

    public CopyCountTask(LibraryItem[] catalogue) {
        this.catalogue = catalogue;
    }

    @Override
    public Integer call() {

        int totalCopies = 0;

        for (LibraryItem item : catalogue) {
            totalCopies += item.getCopiesAvailable();
        }

        return totalCopies;
    }
}