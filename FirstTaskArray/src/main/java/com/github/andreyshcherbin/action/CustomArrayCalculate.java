package com.github.andreyshcherbin.action;

import com.github.andreyshcherbin.entity.CustomArray;

public class CustomArrayCalculate {

	public int[] oddElementsMultiplyByTwo(CustomArray customArray) {
		int[] array = customArray.getArray();
		for (int i = 0; i < array.length; i++) {
			if (array[i] % 2 != 0) {
				array[i] = array[i] * 2;
			}
		}
		return array;
	}

	public double calculateAverageValue(CustomArray customArray) {
		double average = 0;
		int[] array = customArray.getArray();
		for (int i = 0; i < array.length; i++) {
			average += array[i];
		}
		average /= array.length;
		return average;
	}

	public int calculateSum(CustomArray customArray) {
		int sum = 0;
		int[] array = customArray.getArray();
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum;
	}
}
