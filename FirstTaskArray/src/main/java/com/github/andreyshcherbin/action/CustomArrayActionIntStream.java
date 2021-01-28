package com.github.andreyshcherbin.action;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.andreyshcherbin.entity.CustomArray;
import com.github.andreyshcherbin.exception.ResourceException;

public class CustomArrayActionIntStream {

	private static Logger logger = LogManager.getLogger();
	private static final String ARRAY_IS_EMPTY = "array is empty";  

	public int findMinElement(CustomArray customArray) throws ResourceException {
		int[] array = customArray.getArray();
		if (array.length == 0) {
			logger.error(ARRAY_IS_EMPTY);
			throw new ResourceException(ARRAY_IS_EMPTY);
		}
		IntStream stream = IntStream.of(array);
		OptionalInt obj = stream.min();
		return obj.getAsInt();
	}

	public int findMaxElement(CustomArray customArray) throws ResourceException {
		int[] array = customArray.getArray();
		if (array.length == 0) {
			logger.error(ARRAY_IS_EMPTY);
			throw new ResourceException(ARRAY_IS_EMPTY);
		}
		IntStream stream = IntStream.of(array);
		OptionalInt obj = stream.max();
		return obj.getAsInt();
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

	public double calculateAverageValue(CustomArray customArray) throws ResourceException {
		int[] array = customArray.getArray();
		if (array.length == 0) {
			logger.error(ARRAY_IS_EMPTY);
			throw new ResourceException(ARRAY_IS_EMPTY);
		}
		IntStream stream = IntStream.of(array);
		OptionalDouble obj = stream.average();
		return obj.getAsDouble();
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
