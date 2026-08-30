
package kfa.concurrency;

public class KioskWorker extends Thread {

    public KioskWorker(String name) {
        super(name);
    }

    @Override
    public void run() {

        System.out.println(
                "Kiosk " + getName() + " started scanning."
        );

        try {
            int delay = 500 + (int)(Math.random() * 1001);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(
                "Kiosk " + getName() + " completed scanning."
        );
    }
}