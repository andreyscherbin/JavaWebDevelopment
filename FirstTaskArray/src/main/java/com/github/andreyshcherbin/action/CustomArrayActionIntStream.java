package com.github.andreyshcherbin.action;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.andreyshcherbin.entity.CustomArray;
import com.github.andreyshcherbin.exception.ResourceException;
import com.github.andreyshcherbin.validation.CustomArrayValidator;

public class CustomArrayActionIntStream {

	private static Logger logger = LogManager.getLogger();
	private static final String ARRAY_IS_EMPTY = "array is empty";

	public OptionalInt findMinElement(CustomArray customArray) {
		int[] array = customArray.getArray();
		if (CustomArrayValidator.isEmpty(customArray)) {
			logger.info(ARRAY_IS_EMPTY);
			return OptionalInt.empty();
		}
		IntStream stream = IntStream.of(array);
		return stream.min();
	}

	public OptionalInt findMaxElement(CustomArray customArray) {
		int[] array = customArray.getArray();
		if (CustomArrayValidator.isEmpty(customArray)) {
			logger.info(ARRAY_IS_EMPTY);
			return OptionalInt.empty();
		}
		IntStream stream = IntStream.of(array);
		return stream.max();
	}

	public int[] oddElementsMultiplyByTwo(CustomArray customArray) {
		int[] array = customArray.getArray();
		IntStream stream = IntStream.of(array);

		int[] arr = stream.map(intValue -> {
			if (intValue % 2 != 0) {
				return intValue * 2;
			} else {
				return intValue;
			}
		}).toArray();
		customArray.setArray(arr);
		return arr;
	}

	public OptionalDouble calculateAverageValue(CustomArray customArray) {
		int[] array = customArray.getArray();
		if (CustomArrayValidator.isEmpty(customArray)) {
			logger.info(ARRAY_IS_EMPTY);
			return OptionalDouble.empty();
		}
		IntStream stream = IntStream.of(array);
		return stream.average();
	}

	public int calculateSum(CustomArray customArray) {
		int[] array = customArray.getArray();
		IntStream stream = IntStream.of(array);
		return stream.sum();
	}

	public int findNumberPositiveElements(CustomArray customArray) {
		int[] array = customArray.getArray();
		IntStream stream = IntStream.of(array);
		return (int) stream.filter(e -> e > 0).count();
	}

	public int findNumberNegativeElements(CustomArray customArray) {
		int[] array = customArray.getArray();
		IntStream stream = IntStream.of(array);
		return (int) stream.filter(e -> e < 0).count();
	}

}
