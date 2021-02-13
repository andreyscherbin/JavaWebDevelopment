package com.epam.andreyshcherbin.validation;

public class DataValidator {
	
	private static final String REGULAR_FILE_STRUCTURE_WITH_DOUBLE_VALUES = "(?<CENTER>([+-]?)(\\d+)(\\.{1})(\\d+)(\\s*)){3}"
			                                                              + "(?<BOUNDARY>([+-]?)(\\d+)(\\.{1})(\\d+)(\\s*)){3}"
			                                                              + "(?<RADIUS>([+]?)(\\d+)(\\.{1})(\\d+)(\\s*)){1}";	
	public boolean isSphereDataValid(String data) {
			return data.matches(REGULAR_FILE_STRUCTURE_WITH_DOUBLE_VALUES);
	}
}
