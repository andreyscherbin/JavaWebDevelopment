package com.andreyshcherbin.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.andreyshcherbin.entity.MyArray;

public class DataParser {
	public List<MyArray> parseData(String data) {
		final String REGULAR_DELIMETER_COMMA = ",";
		final String REGULAR_DELIMETER_SPACE = "\\s";
		final String REGULAR_DIGITS_SPACES = "^[0-9-\\s]+$";

		List<String> strings = new ArrayList<>();
		List<MyArray> arrays = new ArrayList<>();
		Arrays.stream(data.split(REGULAR_DELIMETER_COMMA)).filter(s -> s.matches(REGULAR_DIGITS_SPACES))
				.forEach(d -> strings.add(d));
		for (String string : strings) {
			String[] str = string.split(REGULAR_DELIMETER_SPACE);
			int size = str.length;
			int[] arr = new int[size];
			for (int i = 0; i < size; i++) {
				arr[i] = Integer.parseInt(str[i]);
			}
			MyArray myArray = new MyArray(arr);
			arrays.add(myArray);
		}
		return arrays;
	}
}