package com.omar.collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapImpl {

    public static Map<String,String> cache = new ConcurrentHashMap<>();

    public static String compute(String key){
        System.out.println(key + " is not found in our cache");

        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "value for " + key;
    }

    public static String getCachedValue(String key){
        String value = cache.get(key);

        if(value == null){
            value = compute(key);
            cache.put(key,value);
        }
        return value;
    }


    public static void main(String[] args) {
//        Creating 10 threads total
        for(int i =0; i<10; i++){
            int threadNum = i;
            new Thread(()->{
                String key = "Key @ " + threadNum;
//                for each thread we will try to access the same value 3 times
                for(int j = 0; j<3;j++){
                    String value = getCachedValue(key);
                    System.out.println("Thread " + Thread.currentThread().getName() + ": key= " +
                            key + ", Value= " + value );
                }

            }).start();
        }
    }




}
