package com.github.andreyshcherbin.validation;

import com.github.andreyshcherbin.entity.CustomArray;

public class CustomArrayValidator {

	public static boolean isEmpty(CustomArray customArray) {
		int[] array = customArray.getArray();
		return array.length == 0;
	}
}
