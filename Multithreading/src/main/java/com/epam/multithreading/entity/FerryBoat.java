package com.epam.multithreading.entity;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import com.epam.multithreading.exception.ResourceException;

public class FerryBoat {

	private int maxWeight;
	private int maxArea;

	private static final int NUMBER_SPACES = 5;
	public static final CyclicBarrier barrier;
	private Lock lock;
	private Condition condition;
	private Semaphore semaphore;
	private Deque<ParkingSpace> spaces;
	private Deque<Car> cars;

	static {
		barrier = new CyclicBarrier(NUMBER_SPACES, () -> crossRiver());
	}

	{
		spaces = new ArrayDeque<>();
		cars = new ArrayDeque<>();
		semaphore = new Semaphore(NUMBER_SPACES, true);
		lock = new ReentrantLock(true);
		condition = lock.newCondition();
	}

	public FerryBoat() {
	}

	public FerryBoat(int maxWeight, int maxArea, Deque<ParkingSpace> spaces) {
		this.maxWeight = maxWeight;
		this.maxArea = maxArea;
		this.spaces = spaces;
	}

	public static void crossRiver() {
		System.out.println("boat start crossing river");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("boat finish crossing river");
	}

	public ParkingSpace getResource(Car car) throws ResourceException {
		cars.offerLast(car);
		try {
			lock.lock();
			Car firstCar = cars.peekFirst();
			while (firstCar.getId() != car.getId()) {
				condition.await();
				firstCar = cars.peekFirst();
			}

			cars.pollFirst();
			condition.signal();
			lock.unlock();

			semaphore.acquire();

			for (ParkingSpace space : spaces) {
				if (!space.isBusy().get()) {
					AtomicBoolean atomicBoolean = space.isBusy();
					atomicBoolean.set(true);
					System.out.println("Car #" + car.getId() + " took space " + space);
					return space;
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		throw new ResourceException("no resource");
	}

	public void releaseSpace(Car car, ParkingSpace space) {
		AtomicBoolean atomicBoolean = space.isBusy();
		atomicBoolean.set(false);
		System.out.println("Car #" + car.getId() + ": " + space + " --> released");
		semaphore.release();
	}
}
