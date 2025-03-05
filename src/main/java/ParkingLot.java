import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class ParkingLot {
    private final Semaphore spaces;

    public ParkingLot(int capacity) {
        this.spaces = new Semaphore(capacity);
    }
    public void parkCar(String carName){
        try {
            System.out.println(carName + " is trying to park...");
            spaces.acquire();
            System.out.println(carName + " has parking...");

            int parkingTime = ThreadLocalRandom.current().nextInt(1000, 5001);
            Thread.sleep(parkingTime);
            System.out.println(carName + " is leaving after " + (parkingTime/1000) + " seconds...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            spaces.release();
        }
    }
}
