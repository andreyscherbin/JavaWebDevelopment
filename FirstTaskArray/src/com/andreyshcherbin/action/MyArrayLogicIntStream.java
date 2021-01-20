package com.andreyshcherbin.action;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.andreyshcherbin.entity.MyArray;
import com.andreyshcherbin.exception.NotFoundException;

public class MyArrayLogicIntStream {

	public int getMinElement(MyArray myArray) throws NotFoundException {
		IntStream stream = IntStream.of(myArray.getArray());
		OptionalInt obj = stream.min();
		if (obj.isPresent()) {
			return obj.getAsInt();
		} else {
			throw new NotFoundException("min element is missing");
		}
	}

	public int getMaxElement(MyArray myArray) throws NotFoundException {
		IntStream stream = IntStream.of(myArray.getArray());
		OptionalInt obj = stream.max();
		if (obj.isPresent()) {
			return obj.getAsInt();
		} else {
			throw new NotFoundException("max element is missing");
		}
	}

	public int[] oddElementsMultiplyByTwo(MyArray myArray) {
		IntStream stream = IntStream.of(myArray.getArray());
		
		int[] array = stream.map(intValue -> {
			if (intValue % 2 != 0) {
				return intValue * 2;
			} else {
				return intValue;
			}
		}).toArray();
		myArray.setArray(array);
		return array;
	}

	public double getAverageValue(MyArray myArray) throws NotFoundException {
		IntStream stream = IntStream.of(myArray.getArray());
		OptionalDouble obj = stream.average();
		if (obj.isPresent()) {
			return obj.getAsDouble();
		} else {
			throw new NotFoundException("average is missing");
		}
	}
	
	public int getSum(MyArray myArray) {		
		IntStream stream = IntStream.of(myArray.getArray());		
		return stream.sum();
	}

	public int getNumberPositiveElements(MyArray myArray) {
		IntStream stream = IntStream.of(myArray.getArray());
		return (int) stream.filter(e -> e > 0).count();		
	}

	public int getNumberNegativeElements(MyArray myArray) {
		IntStream stream = IntStream.of(myArray.getArray());
		return (int) stream.filter(e -> e < 0).count();	
	}

}
