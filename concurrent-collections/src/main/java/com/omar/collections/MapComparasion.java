package com.omar.collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapComparasion {
    public static void main(String[] args) {

        Map<Integer,Integer> synchronousMap = Collections.synchronizedMap(new HashMap<>());
        Map<Integer,Integer> concurrentMap = new ConcurrentHashMap<>();

        System.out.println("SynchronizedMap Time for execution: " + testMap(synchronousMap));
        System.out.println("ConcurrentHashMap Time for execution: " + testMap(concurrentMap));



    }

    public static long testMap(Map<Integer,Integer> map){
        final int THREAD_COUNT = 50;
        final int ITERATION_COUNT = 1000000;
        Thread[] threads = new Thread[THREAD_COUNT];

        long startTime = System.currentTimeMillis();

        for(int i =0; i <THREAD_COUNT; i++){
            threads[i] = new Thread(()->{
                for(int j =0; j<ITERATION_COUNT; j++){
                    int key = (int) (Math.random()*100);
                    map.put(key,map.getOrDefault(key,0)+1);
                }
            });
            threads[i].start();
        }

        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return System.currentTimeMillis() - startTime;
    }
}
