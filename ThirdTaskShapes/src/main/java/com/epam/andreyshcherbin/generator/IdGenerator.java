package com.epam.andreyshcherbin.generator;

public class IdGenerator {

	private static long nextId = 0L;

	public static long getId() {
		return ++nextId;
	}
}
