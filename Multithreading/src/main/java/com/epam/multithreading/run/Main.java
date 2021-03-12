package com.epam.multithreading.run;

import java.util.List;
import com.epam.multithreading.entity.Car;
import com.epam.multithreading.entity.Car.CarType;
import com.epam.multithreading.exception.ResourceException;
import com.epam.multithreading.parser.DataParser;
import com.epam.multithreading.reader.DataReader;

public class Main {

	private static final String DATA = "data\\cars.txt";

	public static void main(String[] args) throws ResourceException {		
				
		DataReader reader = new DataReader();
		DataParser parser = new DataParser();
		List<String> carsString = reader.read(DATA);
		System.out.println(carsString);
		List<Object[]> carsObjects = parser.parseData(carsString);
		for (Object[] carObject : carsObjects) {
			int weight = (int) carObject[0];
			int area = (int) carObject[1];
			CarType type = (CarType) carObject[2];
			new Car(weight, area, type).start();
		}
	}
}
