package com.epam.andreyshcherbin.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataFilter {

	private static final String REGULAR_DELIMETER_COMMA = ",";
	private static final String REGULAR_DIGITS_SPACES = "([+]?\\d*\\.?\\d+\\s*){7}"; //need fix {7} - it's work with 6 elements, NEED REWORK!
	
	public List<String> filterDouble(String data) {
		List<String> strings = new ArrayList<>();
		Arrays.stream(data.split(REGULAR_DELIMETER_COMMA))
		      .filter(s -> s.matches(REGULAR_DIGITS_SPACES))
			  .forEach(d -> strings.add(d));
		return strings;
	}
}
