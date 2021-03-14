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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.multithreading.exception.ResourceException;

public class FerryBoat {

	private static Logger logger = LogManager.getLogger();
	private static final int MAX_WEIGHT = 1000;
	private static final int MAX_AREA = 1000;
	private static final int NUMBER_SPACES = 5;
	public static final CyclicBarrier barrier;
	private static FerryBoat instance;
	private static Lock lock;
	private static Condition condition;
	private static Semaphore semaphore;
	private Deque<ParkingSpace> spaces;
	private Deque<Car> cars;
	private static int currentWeight;
	private static int currentArea;

	static {
		barrier = new CyclicBarrier(NUMBER_SPACES, () -> crossRiver());
		lock = new ReentrantLock(true);
		semaphore = new Semaphore(NUMBER_SPACES, true);
		condition = lock.newCondition();
	}

	{
		spaces = new ArrayDeque<>() {
			{
				this.add(new ParkingSpace(1));
				this.add(new ParkingSpace(2));
				this.add(new ParkingSpace(3));
				this.add(new ParkingSpace(4));
				this.add(new ParkingSpace(5));
			}
		};
		cars = new ArrayDeque<>();
	}

	private FerryBoat() {
	}

	public Deque<ParkingSpace> getSpaces() {
		return spaces;
	}

	public void setSpaces(Deque<ParkingSpace> spaces) {
		this.spaces = spaces;
	}

	public static FerryBoat getInstance() {
		if (instance == null) {
			lock.lock();
			if (instance == null) {
				instance = new FerryBoat();
			}
			lock.unlock();
		}
		return instance;
	}

	public static void crossRiver() {
		logger.info("BOAT START CROSING RIVER");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		logger.info("BOAT FINISH CROSING RIVER");
		clearBoat();
	}

	public ParkingSpace getResource(Car car) throws ResourceException {
		try {
			do {
				lock.lock();
				cars.offerLast(car);
				lock.unlock();

				lock.lock();
				Car firstCar = cars.peekFirst();
				while (firstCar.getId() != car.getId()) {
					condition.await();
					firstCar = cars.peekFirst();
				}
				cars.pollFirst();
				condition.signal();
				lock.unlock();
			} while (!isFreeAreaAndWeight(car));
			addAreaAndWeight(car);

			semaphore.acquire();
			for (ParkingSpace space : spaces) {
				if (!space.isBusy().get()) {
					AtomicBoolean atomicBoolean = space.isBusy();
					atomicBoolean.set(true);
					logger.info("Car # {} took space {}", car.getId(), space);
					return space;
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		throw new ResourceException("no resource, interrupt happen");
	}

	public void releaseSpace(Car car, ParkingSpace space) {
		AtomicBoolean atomicBoolean = space.isBusy();
		atomicBoolean.set(false);
		logger.info("Car # {} --> released {}", car.getId(), space);
		semaphore.release();
	}

	private static boolean isFreeAreaAndWeight(Car car) {
		return currentArea + car.getArea() <= MAX_AREA && currentWeight + car.getWeight() <= MAX_WEIGHT;
	}

	private static void addAreaAndWeight(Car car) {
		currentArea += car.getArea();
		currentWeight += car.getWeight();
	}

	private static void clearBoat() {
		currentArea = 0;
		currentWeight = 0;
	}
}
