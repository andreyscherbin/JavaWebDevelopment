package com.epam.multithreading.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.multithreading.entity.Car;
import com.epam.multithreading.entity.Car.CarType;
import com.epam.multithreading.exception.ResourceException;

public class CarFactory {

	private static Logger logger = LogManager.getLogger();

	public static Car getCarFromFactory(int weight, int area, CarType type) throws ResourceException {
		if (weight == 0 || area == 0 || type == null) {
			logger.error("invalid arguments {} {} {}", weight, area, type);
			throw new ResourceException("invalid arguments: " + weight + " " + area + " " + type);
		}
		return new Car(weight, area, type);
	}
}
