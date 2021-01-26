package com.github.andreyshcherbin.action;

import com.github.andreyshcherbin.entity.CustomArray;

public class CustomArrayAction {

	public int getMinElement(CustomArray customArray) {
		int min = Integer.MAX_VALUE;
		int[] array = customArray.getArray();
		for (int i = 0; i < array.length; i++) {
			if (array[i] < min) {
				min = array[i];
			}
		}
		return min;
	}

	public int getMaxElement(CustomArray customArray) {
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

	public double getAverageValue(CustomArray customArray) {
		double average = 0;
		int[] array = customArray.getArray();
		for (int i = 0; i < array.length; i++) {
			average += array[i];
		}
		average /= array.length;
		return average;
	}

	public int getSum(CustomArray customArray) {
		int sum = 0;
		int[] array = customArray.getArray();
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum;
	}

	public int getNumberPositiveElements(CustomArray customArray) {
		int counter = 0;
		int[] array = customArray.getArray();
		for (int i = 0; i < array.length; i++) {
			if (array[i] > 0) {
				counter++;
			}
		}
		return counter;
	}

	public int getNumberNegativeElements(CustomArray customArray) {
		int counter = 0;
		int[] array = customArray.getArray();
		for (int i = 0; i < array.length; i++) {
			if (array[i] < 0) {
				counter++;
			}
		}
		return counter;
	}

	public int[] bubbleSort(CustomArray customArray) {
		int[] array = customArray.getArray();
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - i - 1; j++) {
				if (array[j] > array[j + 1]) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
		return array;
	}

	public int[] quickSort(CustomArray customArray, int[] array, int low, int high) {
		array = customArray.getArray();
		if (low < high) {
			int pi = partition(array, low, high);
			quickSort(customArray, array, low, pi - 1);
			quickSort(customArray, array, pi + 1, high);
		}
		return array;
	}

	private int partition(int[] arr, int low, int high) {
		int pivot = arr[high];
		int i = (low - 1);
		for (int j = low; j < high; j++) {
			if (arr[j] < pivot) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;

		return i + 1;
	}

	public int[] mergeSort(CustomArray customArray, int[] array, int l, int r) {
		array = customArray.getArray();
		if (l < r) {
			int m = (l + r) / 2;

			mergeSort(customArray, array, l, m);
			mergeSort(customArray, array, m + 1, r);

			merge(array, l, m, r);
		}
		return array;
	}

	private void merge(int[] array, int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;

		int[] L = new int[n1];
		int[] R = new int[n2];

		for (int i = 0; i < n1; ++i)
			L[i] = array[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = array[m + 1 + j];

		int i = 0;
		int j = 0;

		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				array[k] = L[i];
				i++;
			} else {
				array[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			array[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) {
			array[k] = R[j];
			j++;
			k++;
		}
	}
}
