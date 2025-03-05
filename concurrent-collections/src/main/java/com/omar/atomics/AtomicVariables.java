package com.omar.atomics;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariables {
    private static int count = 0;
    private static AtomicInteger countAtomic = new AtomicInteger(0);
    private static final Object lock = new Object();

    public static void increase(){
        countAtomic.incrementAndGet();

//        1. retrieving the count variable
//        2. adding 1
//        3. setting the new value

        synchronized (lock){
            count = count +1;

        }
    }

    public static void decrease(){
        countAtomic.decrementAndGet();

        synchronized (lock){
            count--;

        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(()->{
            for(int i =0; i<10000;i++){
                increase();
            }
        });

        Thread thread2 = new Thread(()->{
            for(int i =0; i<10000;i++){
                decrease();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(countAtomic);

    }

}
