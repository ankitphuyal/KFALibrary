package kfa.model;

public class Magazine extends LibraryItem {

    private int issueNumber;

    public Magazine(
            String title,
            String isbn,
            double price,
            int issueNumber) {

        super(title, isbn, price);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    @Override
    public int getLendingPeriodDays() {
        return 7;
    }

    @Override
    public String toString() {

        String status =
                isAvailable()
                        ? "Available"
                        : "Not Available";

        return String.format(
                "[MAGAZINE] %s — Issue %d — Rs %.2f (%s)",
                getTitle(),
                issueNumber,
                getPrice(),
                status
        );
    }

    // Magazine cannot be renewed according to library policy.
}