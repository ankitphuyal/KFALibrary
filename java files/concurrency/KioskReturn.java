package kfa.concurrency;

import java.util.Random;

public class KioskReturn implements Runnable {

    private final ReturnsCart cart;
    private final String kioskName;
    private final String[] bookTitles;

    private final Random random = new Random();

    public KioskReturn(
            ReturnsCart cart,
            String kioskName,
            String[] bookTitles) {

        this.cart = cart;
        this.kioskName = kioskName;
        this.bookTitles = bookTitles;
    }

    @Override
    public void run() {

        for (String title : bookTitles) {

            cart.addReturn(title);

            // Random delay between returns
            try {

                Thread.sleep(
                        50 + random.nextInt(151)
                );

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println(
                kioskName
                        + " finished dropping returns."
        );
    }
}