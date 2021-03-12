package com.epam.multithreading.generator;

public class IdGenerator {

	private static long nextId = 0;

	public static long getId() {
		return nextId++;
	}
	
	public static void reset() {
		nextId = 0;
	}
}
