package org.dci;

import java.util.concurrent.Semaphore;

public class ParkingLot {
    private final Semaphore semaphore = new Semaphore(5);

    public void usingParkingLot() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " has started using Parking.");

            Thread.sleep((long) (Math.random() * 4000) + 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println(Thread.currentThread().getName() + " is about to leave the Parking.");
            semaphore.release();
        }
    }
}
