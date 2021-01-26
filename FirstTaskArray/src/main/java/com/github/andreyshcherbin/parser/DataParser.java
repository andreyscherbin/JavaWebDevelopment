package com.github.andreyshcherbin.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.andreyshcherbin.entity.CustomArray;

public class DataParser {

	static final String REGULAR_DELIMETER_COMMA = ",";
	static final String REGULAR_DELIMETER_SPACE = "\\s";
	static final String REGULAR_DIGITS_SPACES = "^[0-9-\\s]+$";

	public List<CustomArray> parseData(String data) { 
		List<String> strings = new ArrayList<>();
		List<CustomArray> arrays = new ArrayList<>();
		
		Arrays.stream(data.split(REGULAR_DELIMETER_COMMA)).filter(s -> s.matches(REGULAR_DIGITS_SPACES))
				                                          .forEach(d -> strings.add(d));
		
		for (String string : strings) {
			String[] str = string.split(REGULAR_DELIMETER_SPACE);
			int size = str.length;
			int[] arr = new int[size];
			for (int i = 0; i < size; i++) {
				arr[i] = Integer.parseInt(str[i]);
			}
			CustomArray customArray = new CustomArray(arr);
			arrays.add(customArray);
		}
		return arrays;
	}
}