package org.dci;

import javax.crypto.MacSpi;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class WordFrequencyCounter {
    private static final int THREAD_COUNT = 40000;
    private static final List<String> WORDS = List.of(
            "apple", "banana", "apple", "orange", "banana", "grape", "apple",
            "mango", "peach", "banana", "apple", "grape", "mango", "peach"
    );

    public static void main(String[] args) {
        // Using ConcurrentHashMap
        Map<String, Integer> concurrentWordCount = new ConcurrentHashMap<>();

        // Using synchronizedMap (for comparison)
        Map<String, Integer> synchronizedWordCount = Collections.synchronizedMap(new HashMap<>());

        System.out.println("Time taken for ConcurrentHashMap: "
                + doTheProcessAndComputeTime(concurrentWordCount));
        System.out.println("Word count using ConcurrentHashMap: "
                + concurrentWordCount);


        System.out.println("Time taken for SynchronizedHashMap: "
                + doTheProcessAndComputeTime(synchronizedWordCount));
        System.out.println("Word count using SynchronizedHashMap: "
                + synchronizedWordCount);
    }

    public static long doTheProcessAndComputeTime(Map<String, Integer> wordMap) {
        long startTime = System.currentTimeMillis();

        // Process words with multiple threads
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> processWords(WORDS, wordMap));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return System.currentTimeMillis() - startTime;
    }

    private static void processWords(List<String> words, Map<String, Integer> wordCountMap) {
        for (String word : words) {
            // Implement logic to update the word count map in a thread-safe way
                wordCountMap.merge(word, 1, Integer::sum);
        }
    }
}