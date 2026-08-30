package kfa.concurrency;

public class ReportWorker implements Runnable {

    private String reportName;

    public ReportWorker(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public void run() {

        System.out.println(
                reportName + " started by "
                        + Thread.currentThread().getName()
        );

        try {
            int delay = 500 + (int)(Math.random() * 1001);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(
                reportName + " completed."
        );
    }
}