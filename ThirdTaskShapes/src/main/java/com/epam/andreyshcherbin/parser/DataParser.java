package com.epam.andreyshcherbin.parser;

import java.util.ArrayList;
import java.util.List;
import com.epam.andreyshcherbin.entity.Sphere;

public class DataParser {

	private static final String REGULAR_DELIMETER_SPACE = "\\s";

	//need to rewrite
	/*public List<Sphere> parseData(List<String> arrays) {
		List<Sphere> spheres = new ArrayList<>();

		for (String string : arrays) {
			String[] str = string.split(REGULAR_DELIMETER_SPACE);
			int size = str.length;
			int[] arr = new int[size];
			for (int i = 0; i < size; i++) {
				arr[i] = Integer.parseInt(str[i]);
			}
			Sphere sphere = new Sphere(); // change;
			spheres.add(sphere);
		}
		return spheres;
	}*/
}