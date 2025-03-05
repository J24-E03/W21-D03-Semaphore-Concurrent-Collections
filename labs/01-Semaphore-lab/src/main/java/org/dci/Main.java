package org.dci;

public class Main {
    private static final int NUMBER_OF_CARS = 15;
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();

        Thread[] cars = new Thread[NUMBER_OF_CARS];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Thread(parkingLot::usingParkingLot, "Car-" + (i + 1));
            try {
                Thread.sleep(500 + (long)(Math.random() * 1500));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cars[i].start();
        }
    }
}