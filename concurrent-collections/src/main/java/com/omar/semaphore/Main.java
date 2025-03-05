package com.omar.semaphore;

public class Main {
    public static void main(String[] args) {
        LimitedResource limitedResource = new LimitedResource();

        for(int i =0; i<5; i++){
            new Thread(limitedResource::useResource).start();
        }
    }
}
