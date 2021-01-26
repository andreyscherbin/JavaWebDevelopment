package com.github.andreyshcherbin.entity;

import java.io.Serializable;
import java.util.Arrays;

public class CustomArray implements Serializable {

	private static final long serialVersionUID = 1L;

	private int[] array;

	public CustomArray() {

	}

	public CustomArray(int[] array) {
		this.array = array;
	}

	public int getSize() {
		return array.length;
	}

	public void setArray(int[] array) {
		this.array = array;
	}

	public int[] getArray() {
		return array;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((array == null) ? 0 : Arrays.hashCode(array));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CustomArray customArray = (CustomArray) obj;

		if (array == null) {
			if (customArray.array != null) {
				return false;
			}
		} else if (!Arrays.equals(customArray.array, this.array)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MyArray: " + Arrays.toString(array);
	}
}
