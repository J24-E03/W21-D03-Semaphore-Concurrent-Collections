package org.dcistudent;

class Car extends Thread {
  private final ParkingLot parkingLot;

  // Constructor to initialize the car with a name and the parking lot it will use.
  public Car(String name, ParkingLot parkingLot) {
    super(name); // Set the name of the thread.
    this.parkingLot = parkingLot;
  }

  // The run method defines the behavior of the car when the thread starts.
  @Override
  public void run() {
    this.parkingLot.parkCar(this);
  }
}
