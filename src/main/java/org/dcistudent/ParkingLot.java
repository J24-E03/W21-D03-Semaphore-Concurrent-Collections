package org.dcistudent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

// Represents the Parking Lot.
class ParkingLot {
  // Semaphore to control access to parking spaces.
  private final Semaphore parkingSpaces;

  // Constructor to initialize the parking lot with a given number of spaces.
  public ParkingLot(int numberOfSpaces) {
    // Initialize semaphore with the number of available parking spaces.
    this.parkingSpaces = new Semaphore(numberOfSpaces, true);
  }

  public void parkCar(Car car) {
    try {
      // Acquire a parking spot (decrement semaphore).
      System.out.println(car.getName() + " is trying to park.");
      this.parkingSpaces.acquire();
      System.out.println(car.getName() + " has parked.");

      // Simulate parking duration.
      int parkingDuration = ThreadLocalRandom.current().nextInt(1, 6);
      Thread.sleep(parkingDuration * 1000L);

      // Car leaves the parking spot.
      System.out.println(car.getName() + " is leaving after " + parkingDuration + " seconds.");
      this.parkingSpaces.release(); // Release the parking spot (increment semaphore).
    } catch (InterruptedException e) {
      System.out.println(car.getName() + " was interrupted.");
      Thread.currentThread().interrupt(); // Restore interrupted status.
    }
  }
}
