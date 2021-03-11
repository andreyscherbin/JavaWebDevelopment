package com.epam.multithreading.run;

import java.util.ArrayDeque;
import java.util.Deque;

import com.epam.multithreading.entity.Car;
import com.epam.multithreading.entity.FerryBoat;
import com.epam.multithreading.entity.ParkingSpace;

public class Main {

	public static void main(String[] args) {
		Deque<ParkingSpace> spaces = new ArrayDeque<>() {
			{
				this.add(new ParkingSpace(1));
				this.add(new ParkingSpace(2));
				this.add(new ParkingSpace(3));
				this.add(new ParkingSpace(4));
				this.add(new ParkingSpace(5));
			}
		};
		FerryBoat boat = new FerryBoat(1000,1000,spaces);
		for (int i = 0; i < 20; i++) {
			new Car(boat, 100, 200).start();
		}
	}
}
