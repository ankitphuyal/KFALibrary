package kfa.exception;

public class ItemOverdueException extends Exception {

    private final int daysOverdue;

    public ItemOverdueException(int daysOverdue) {
        super("Item is overdue by " + daysOverdue + " day(s).");
        this.daysOverdue = daysOverdue;
    }

    public int getDaysOverdue() {
        return daysOverdue;
    }
}