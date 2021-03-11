package com.epam.multithreading.entity;

import java.util.concurrent.CyclicBarrier;
import com.epam.multithreading.exception.ResourceException;
import com.epam.multithreading.generator.IdGenerator;
import java.util.concurrent.BrokenBarrierException;

public class Car extends Thread {
	
	private long carId;
	private FerryBoat boat;
	private int weight;
	private int area;
	private CyclicBarrier barrier;
	
	{
		barrier = FerryBoat.barrier;	
		carId = IdGenerator.getId();	
	}

	public Car() {
		carId = IdGenerator.getId();	
	}

	public Car(FerryBoat boat, int weight, int area) {
		this.boat = boat;
		this.weight = weight;
		this.area = area;
	}

	/*private enum Type {
		LIGHT_WEIGHT_CAR, HEAVY_WEIGHT_CAR
	}*/
	
	@Override	
	public long getId() {
		return carId;
	}

	@Override
	public void run() {
		System.out.println("Car #" + this.getId() + " goes to ferry boat");
		ParkingSpace space = null;
		try {
			space = boat.getResource(this);
			this.barrier.await();			
		} catch (BrokenBarrierException | ResourceException | InterruptedException e) {
			System.err.println("Car #" + this.getId() + " lost ->" + e.getMessage());
		} finally {
			if (space != null) {
				boat.releaseSpace(this, space);
			}
		}
		System.out.println("Car #" + this.getId() + " after crossing river");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + area;
		result = prime * result + weight;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (area != other.area)
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Car [weight=");
		builder.append(weight);
		builder.append(", area=");
		builder.append(area);
		builder.append("]");
		return builder.toString();
	}
}
