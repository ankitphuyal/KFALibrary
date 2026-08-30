package kfa.concurrency;

public class Librarian implements Runnable {

    private final ReturnsCart cart;
    private final int numberOfReturns;

    public Librarian(
            ReturnsCart cart,
            int numberOfReturns) {

        this.cart = cart;
        this.numberOfReturns = numberOfReturns;
    }

    @Override
    public void run() {

        for (int i = 0; i < numberOfReturns; i++) {

            String title = cart.collectReturn();

            if (title != null) {

                System.out.println(
                        "Librarian processed: "
                                + title
                );

                // Short processing delay
                try {
                    Thread.sleep(100);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        System.out.println(
                "Librarian finished processing returns."
        );
    }
}