import java.util.concurrent.ThreadLocalRandom;

public class ParkingLotSimulation {
    public static void main(String[] args) {
        final int NUMBER_OF_ROWS = 6;
        ParkingLot parkingLot = new ParkingLot(NUMBER_OF_ROWS);

        Runnable car = () -> {
            String carName = Thread.currentThread().getName();
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(500, 2001));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            parkingLot.parkCar(carName);
        };
        for(int i = 0; i < 10; i++) {
            new Thread(car, "Car" + i).start();
        }
    }
}
