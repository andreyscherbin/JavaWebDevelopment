package com.andreyshcherbin.entity;

import java.io.Serializable;
import java.util.Arrays;

public class MyArray implements Serializable {

	private static final long serialVersionUID = 1L;

	private int[] array;

	public MyArray() {

	}

	public MyArray(int[] array) {
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
		result = prime * result + ((array == null) ? 0 : array.hashCode());
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
		MyArray myArray = (MyArray) obj;

		if (array == null) {
			if (myArray.array != null) {
				return false;
			}
		} else if (!Arrays.equals(myArray.array, this.array)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MyArray: " + Arrays.toString(array);
	}
}
