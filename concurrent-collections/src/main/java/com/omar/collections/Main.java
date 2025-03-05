package com.omar.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        this is thread safe
        List<Integer> numbers = Collections.synchronizedList(new ArrayList<>());
        System.out.println(numbers.getClass());


        Thread thread1 = new Thread(()->{
            for(int i =0; i<5;i++){
                numbers.add(i);
            }
        });

        Thread thread2 = new Thread(()->{
            for(int i =0; i<5;i++){
                numbers.set(i,i+1);
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

        System.out.println(numbers);
    }
}
