package com.github.andreyshcherbin.action;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.andreyshcherbin.entity.CustomArray;
import com.github.andreyshcherbin.exception.NotFoundException;

public class CustomArrayActionIntStream {
	
	static Logger logger = LogManager.getLogger();

	public int getMinElement(CustomArray customArray) throws NotFoundException {
		int[] array = customArray.getArray();
		IntStream stream = IntStream.of(array);
		OptionalInt obj = stream.min();
		if (obj.isPresent()) {
			return obj.getAsInt();
		} else {
			logger.error("min element is missing");
			throw new NotFoundException("min element is missing");
		}
	}

	public int getMaxElement(CustomArray customArray) throws NotFoundException {
		int[] array = customArray.getArray();
		IntStream stream = IntStream.of(array);
		OptionalInt obj = stream.max();
		if (obj.isPresent()) {
			return obj.getAsInt();
		} else {
			logger.error("max element is missing");
			throw new NotFoundException("max element is missing");
		}
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

	public double getAverageValue(CustomArray customArray) throws NotFoundException {
		int[] array = customArray.getArray();
		IntStream stream = IntStream.of(array);
		OptionalDouble obj = stream.average();
		if (obj.isPresent()) {
			return obj.getAsDouble();
		} else {
			logger.error("average is missing");
			throw new NotFoundException("average is missing");
		}
	}

	public int getSum(CustomArray customArray) {
		int[] array = customArray.getArray();
		IntStream stream = IntStream.of(array);
		return stream.sum();
	}

	public int getNumberPositiveElements(CustomArray customArray) {
		int[] array = customArray.getArray();
		IntStream stream = IntStream.of(array);
		return (int) stream.filter(e -> e > 0).count();
	}

	public int getNumberNegativeElements(CustomArray customArray) {
		int[] array = customArray.getArray();
		IntStream stream = IntStream.of(array);
		return (int) stream.filter(e -> e < 0).count();
	}

}
