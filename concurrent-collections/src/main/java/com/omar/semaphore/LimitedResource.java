package com.omar.semaphore;

import java.util.concurrent.Semaphore;

public class LimitedResource {
//    permits: how many threads can have access to my resource
    private final Semaphore semaphore = new Semaphore(2);

    public void useResource(){
        try{
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " is accessing our shared resource");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally{
            semaphore.release();
        }
    }

}
