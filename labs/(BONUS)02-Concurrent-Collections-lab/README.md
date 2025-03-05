# **Lab: Multi-Threaded Word Frequency Counter using ConcurrentHashMap**  

## **Objective**  
In this lab, students will implement a multi-threaded word frequency counter using **ConcurrentHashMap**. The goal is to process a set of words, count the occurrences of each word using multiple threads, and ensure thread safety while improving performance over a traditional synchronized map.

---

## **Lab Requirements**  
- Use **ConcurrentHashMap** to store and update word counts.  
- Process a list of words using multiple threads.  
- Ensure thread safety and better performance than `Collections.synchronizedMap()`.  
- Compare performance with a synchronized `HashMap` to understand efficiency.  

---

## **Instructions for Students**

### **1. Starter Code**

```java
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WordFrequencyCounter {
    private static final int THREAD_COUNT = 4;
    private static final List<String> WORDS = List.of(
            "apple", "banana", "apple", "orange", "banana", "grape", "apple",
            "mango", "peach", "banana", "apple", "grape", "mango", "peach"
    );

    public static void main(String[] args) {
        // Using ConcurrentHashMap
        ConcurrentHashMap<String, Integer> concurrentWordCount = new ConcurrentHashMap<>();
        
        // Using synchronizedMap (for comparison)
        Map<String, Integer> synchronizedWordCount = Collections.synchronizedMap(new HashMap<>());

        // Process words with multiple threads
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> processWords(WORDS, concurrentWordCount));
            threads[i].start();
        }
        
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Word count using ConcurrentHashMap: " + concurrentWordCount);
    }

    private static void processWords(List<String> words, Map<String, Integer> wordCountMap) {
        for (String word : words) {
            // Implement logic to update the word count map in a thread-safe way
        }
    }
}
```

---

## **2. Explanation of the Code**  

### **ConcurrentHashMap vs. SynchronizedMap**  
- `ConcurrentHashMap` allows fine-grained locking, making it more efficient in a multi-threaded environment.  
- `Collections.synchronizedMap(new HashMap<>())` synchronizes at the map level, causing contention when multiple threads update it.  

### **Thread-Safe Updates**  
- Ensure proper thread-safe updates to the word count map using `ConcurrentHashMap`.  

---

## **3. Tasks for Students**  

✅ **Task 1:** Implement the `processWords()` method to update the word count map in a thread-safe way.  
✅ **Task 2:** Run the program and analyze the output. Does `ConcurrentHashMap` perform better?  
✅ **Task 3:** Modify the code to time how long each approach takes. Compare `ConcurrentHashMap` vs. `SynchronizedMap`.  
✅ **Task 4:** Increase the `THREAD_COUNT` to 10 and observe any performance differences.  
✅ **Task 5:** Modify the program to print only the top 5 most frequent words.  

---

## **Expected Learning Outcomes**  

✔ Understand **why `ConcurrentHashMap` is better** than a synchronized `HashMap` in concurrent scenarios.  
✔ Learn to **implement thread-safe data structures** using concurrent collections.  
✔ Compare **performance impact** of different concurrent collections.  


