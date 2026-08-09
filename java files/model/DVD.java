package kfa.model;

public class DVD extends LibraryItem implements Renewable {

    private int durationMinutes;
    private int renewedDays = 0;

    public DVD(
            String title,
            String isbn,
            double price,
            int durationMinutes) {

        super(title, isbn, price);
        this.durationMinutes = durationMinutes;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    @Override
    public int getLendingPeriodDays() {
        return 5 + renewedDays;
    }

    @Override
    public void renew(int extraDays) {

        if (extraDays > 0) {
            renewedDays += extraDays;
        }
    }

    @Override
    public String toString() {

        String status =
                isAvailable()
                        ? "Available"
                        : "Not Available";

        return String.format(
                "[DVD] %s — %d minutes — Rs %.2f (%s)",
                getTitle(),
                durationMinutes,
                getPrice(),
                status
        );
    }
}