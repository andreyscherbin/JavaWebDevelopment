package com.github.andreyshcherbin.parser;

import java.util.ArrayList;
import java.util.List;
import com.github.andreyshcherbin.entity.CustomArray;

public class DataParser {

	private static final String REGULAR_DELIMETER_SPACE = "\\s";

	public List<CustomArray> parseData(List<String> arrays) {
		List<CustomArray> newArrays = new ArrayList<>();

		for (String string : arrays) {
			String[] str = string.split(REGULAR_DELIMETER_SPACE);
			int size = str.length;
			int[] arr = new int[size];
			for (int i = 0; i < size; i++) {
				arr[i] = Integer.parseInt(str[i]);
			}
			CustomArray customArray = new CustomArray(arr);
			newArrays.add(customArray);
		}
		return newArrays;
	}
}