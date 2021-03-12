package com.epam.multithreading.parser;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.multithreading.entity.Car.CarType;
import com.epam.multithreading.exception.ResourceException;

public class DataParser {

	private static Logger logger = LogManager.getLogger();
	private static final String REGULAR_DELIMETER_SPACE = "\\s+";

	public List<Object[]> parseData(List<String> cars) throws ResourceException {
		if (cars == null) {
			logger.error("invalid argument {}", cars);
			throw new ResourceException("invalid argument: " + cars);
		}
		List<Object[]> carsData = new ArrayList<>();
		for (String carString : cars) {
			String[] data = carString.split(REGULAR_DELIMETER_SPACE);
			int weight;
			int area;
			CarType type;
			weight = Integer.parseInt(data[0]);
			area = Integer.parseInt(data[1]);
			type = Integer.parseInt(data[2]) == 0 ? CarType.LIGHT_WEIGHT : CarType.HEAVY_WEIGHT;
			Object[] carData = new Object[3];
			carData[0] = weight;
			carData[1] = area;
			carData[2] = type;
			carsData.add(carData);
		}
		return carsData;
	}
}