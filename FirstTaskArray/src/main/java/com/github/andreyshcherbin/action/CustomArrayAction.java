package com.github.andreyshcherbin.action;

import com.github.andreyshcherbin.entity.CustomArray;

public class CustomArrayAction {

	public int minElement(CustomArray customArray) {
		int min = Integer.MAX_VALUE;
		int[] array = customArray.getArray();
		for (int i = 0; i < array.length; i++) {
			if (array[i] < min) {
				min = array[i];
			}
		}
		return min;
	}

	public int maxElement(CustomArray customArray) {
		int max = Integer.MIN_VALUE;
		int[] array = customArray.getArray();
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}

	public int[] oddElementsMultiplyByTwo(CustomArray customArray) {
		int[] array = customArray.getArray();
		for (int i = 0; i < array.length; i++) {
			if (array[i] % 2 != 0) {
				array[i] = array[i] * 2;
			}
		}
		return array;
	}

	public double averageValue(CustomArray customArray) {
		double average = 0;
		int[] array = customArray.getArray();
		for (int i = 0; i < array.length; i++) {
			average += array[i];
		}
		average /= array.length;
		return average;
	}

	public int sum(CustomArray customArray) {
		int sum = 0;
		int[] array = customArray.getArray();
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum;
	}

	public int numberPositiveElements(CustomArray customArray) {
		int counter = 0;
		int[] array = customArray.getArray();
		for (int i = 0; i < array.length; i++) {
			if (array[i] > 0) {
				counter++;
			}
		}
		return counter;
	}

	public int numberNegativeElements(CustomArray customArray) {
		int counter = 0;
		int[] array = customArray.getArray();
		for (int i = 0; i < array.length; i++) {
			if (array[i] < 0) {
				counter++;
			}
		}
		return counter;
	}

}
