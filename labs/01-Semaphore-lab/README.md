# Parking Lot Simulation

## Objective:
Implement a thread-safe parking lot simulation using **Semaphore** in Java.

## Requirements:
- The parking lot has **5 available spaces**.
- Each car (represented as a thread) waits until a parking spot is free.
- When a parking spot is available, the car parks.
- The car stays parked for a **random duration (between 1-5 seconds)**.
- After the parking duration, the car leaves, **freeing up the parking spot** for another car.

## Guidelines:
- Use **Semaphore** to limit the number of cars parking at the same time.
- Simulate **multiple cars arriving at random intervals**.
- Ensure **thread safety** so that no more than **5 cars are parked at once**.
