import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {

    private static final int THREADS_NUMBER = 10;

    public static void main(String[] args) throws InterruptedException {
        try (ExecutorService executor = Executors.newFixedThreadPool(THREADS_NUMBER)) {
            for (int i = 0; i < THREADS_NUMBER; i++) {
                executor.submit(ParkingLot::parkCar);
                Thread.sleep((long) (Math.random() * 5000));
            }
        }
    }

    static class ParkingLot {
        private static final Semaphore semaphore = new Semaphore(5);

        public static void parkCar() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " has parked a car.");
                Thread.sleep((long) (Math.random() * 5000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                semaphore.release();
                System.out.println(Thread.currentThread().getName() + " has removed a car.");
            }
        }
    }
}
