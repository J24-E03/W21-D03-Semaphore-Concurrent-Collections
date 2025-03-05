package org.dcistudent;

import java.util.concurrent.ThreadLocalRandom;

public class ParkingLotSimulation {
  public static void main(String[] args) {
    // Define the number of parking spaces.
    final int numberOfSpaces = 5;
    // Create a parking lot with the specified number of spaces.
    ParkingLot parkingLot = new ParkingLot(numberOfSpaces);

    // Number of cars to simulate.
    final int numberOfCars = 10;

    // Create and start car threads.
    for (int i = 1; i <= numberOfCars; i++) {
      Car car = new Car("Car " + i, parkingLot);
      car.start();

      // Simulate random arrival times for cars
      try {
        int arrivalDelay = ThreadLocalRandom.current().nextInt(1, 4);
        Thread.sleep(arrivalDelay * 1000L);
      } catch (InterruptedException e) {
        System.out.println("Simulation interrupted.");
        Thread.currentThread().interrupt(); // Restore interrupted status.
      }
    }
  }
}
