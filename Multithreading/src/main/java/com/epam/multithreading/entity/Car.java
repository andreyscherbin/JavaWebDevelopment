package com.epam.multithreading.entity;

import java.util.concurrent.CyclicBarrier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.multithreading.exception.ResourceException;
import com.epam.multithreading.generator.IdGenerator;
import java.util.concurrent.BrokenBarrierException;

public class Car extends Thread {
	
	private static Logger logger = LogManager.getLogger();
	
	private long carId;	
	private int weight;
	private int area;
	private CyclicBarrier barrier;
	private CarType type;
	
	public enum CarType {
		LIGHT_WEIGHT,
		HEAVY_WEIGHT
	}
	
	{
		barrier = FerryBoat.barrier;	
		carId = IdGenerator.getId();		
	}

	public Car() {
		carId = IdGenerator.getId();	
	}

	public Car(int weight, int area, CarType type) {		
		this.weight = weight;
		this.area = area;
		this.type = type;		
	}
	
	@Override	
	public long getId() {
		return carId;
	}
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	@Override
	public void run() {		
		logger.info("Car # {} goes to ferry boat", this.getId());
		ParkingSpace space = null;
		FerryBoat boat = FerryBoat.getInstance();
		try {			
			space = boat.getResource(this);
			this.barrier.await();			
		} catch (BrokenBarrierException | ResourceException | InterruptedException e) {
			System.err.println("Car #" + this.getId() + " lost ->" + e.getMessage()); // решить эту проблему
		} finally {
			if (space != null) {
				boat.releaseSpace(this, space);
			}
		}		
		logger.info("Car # {} after crossing river", this.getId());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + area;
		result = prime * result + (int) (carId ^ (carId >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (carId != other.carId)
			return false;
		if (type != other.type)
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
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}
}
