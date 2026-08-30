package kfa.concurrency;

import java.util.LinkedList;
import java.util.List;

public class ReturnsCart {

    // Maximum number of returns the cart can hold
    private final int capacity;

    // Shared buffer
    private final List<String> returns;

    public ReturnsCart(int capacity) {

        this.capacity = capacity;
        this.returns = new LinkedList<>();
    }

    // ========================================
    // C1: ADD RETURN - PRODUCER
    // ========================================

    public synchronized void addReturn(String title) {

        // Wait while the cart is full
        while (returns.size() >= capacity) {

            try {
                wait();

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
                return;
            }
        }

        // Add the returned book
        returns.add(title);

        System.out.println(
                Thread.currentThread().getName()
                        + " added: "
                        + title
                        + " | Cart size: "
                        + returns.size()
        );

        // Wake up waiting threads
        notifyAll();
    }

    // ========================================
    // C1: COLLECT RETURN - CONSUMER
    // ========================================

    public synchronized String collectReturn() {

        // Wait while the cart is empty
        while (returns.isEmpty()) {

            try {
                wait();

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
                return null;
            }
        }

        // Remove the first returned book
        String title = returns.remove(0);

        System.out.println(
                Thread.currentThread().getName()
                        + " collected: "
                        + title
                        + " | Cart size: "
                        + returns.size()
        );

        // Wake up waiting threads
        notifyAll();

        return title;
    }

    /*
     * C1 IMPORTANT:
     *
     * wait() and notifyAll() are used inside synchronized methods.
     *
     * The conditions are checked inside while loops rather than
     * if statements because a thread must re-check the condition
     * after waking up. Another thread may have changed the cart
     * before the awakened thread gets the lock again.
     */
}