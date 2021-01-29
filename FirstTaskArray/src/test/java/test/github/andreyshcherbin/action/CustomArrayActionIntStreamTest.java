package test.github.andreyshcherbin.action;

import static org.testng.Assert.*;
import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.github.andreyshcherbin.action.CustomArrayActionIntStream;
import com.github.andreyshcherbin.entity.CustomArray;
import com.github.andreyshcherbin.exception.ResourceException;

import test.github.andreyshcherbin.TestListener;

@Listeners(TestListener.class)
public class CustomArrayActionIntStreamTest {
	
	CustomArrayActionIntStream customArrayActionIntStream;
	CustomArray customArray;

	@BeforeClass
	public void setUp() {
		customArrayActionIntStream = new CustomArrayActionIntStream();
		customArray = new CustomArray();
	}
	
	@Test(dataProvider = "arrays_min")
	public void testCustomArrayActionIntStreamMinElement(int[] array, OptionalInt expectedValue) {
		customArray.setArray(array);		
		OptionalInt actualValue = customArrayActionIntStream.findMinElement(customArray);
		assertEquals(actualValue, expectedValue);		
	}
	
	@Test(dataProvider = "arrays_max")
	public void testCustomArrayActionIntStreamMaxElement(int[] array, OptionalInt expectedValue) {
		customArray.setArray(array);		
		OptionalInt actualValue = customArrayActionIntStream.findMaxElement(customArray);
		assertEquals(actualValue, expectedValue);			
	}
	
	@Test(dataProvider = "arrays_OddElementsMultiplyByTwo")
	public void testCustomArrayActionIntStreamOddElementsMultiplyByTwo(int[] array, int[] expectedValue) {
		customArray.setArray(array);		
		int[] actual = customArrayActionIntStream.oddElementsMultiplyByTwo(customArray);
		boolean result = Arrays.equals(actual, expectedValue);
		assertTrue(result);
	}
	
	@Test(dataProvider = "arrays_averageValue")
	public void testCustomArrayActionIntStreamAverageValue(int[] array, OptionalDouble expectedValue) {
		customArray.setArray(array);		
		OptionalDouble actualValue = customArrayActionIntStream.calculateAverageValue(customArray);
		assertEquals(actualValue, expectedValue);						
	}
	
	@Test(dataProvider = "arrays_sum")
	public void testCustomArrayActionSum(int[] array, int expectedValue) {
		customArray.setArray(array);
		int actualValue = customArrayActionIntStream.calculateSum(customArray);
		assertEquals(actualValue, expectedValue);		
	}
	
	@Test(dataProvider = "arrays_numberPositiveElements")
	public void testCustomArrayActionNumberPositiveElements(int[] array, int expectedValue) {
		customArray.setArray(array);
		int actualValue = customArrayActionIntStream.findNumberPositiveElements(customArray);
		assertEquals(actualValue, expectedValue);		
	}
	
	@Test(dataProvider = "arrays_numberNegativeElements")
	public void testCustomArrayActionNumberNegativeElements(int[] array, int expectedValue) {
		customArray.setArray(array);
		int actualValue = customArrayActionIntStream.findNumberNegativeElements(customArray);
		assertEquals(actualValue, expectedValue);		
	}
	
	@Test(dataProvider = "arrays_sortStream")
	public void testCustomArrayActionSortStream(int[] array, int[] expectedValue) {
		customArray.setArray(array);
		int[] actual = customArrayActionIntStream.sortStream(customArray);
		boolean result = Arrays.equals(actual, expectedValue);
		assertTrue(result);		
	}

	@DataProvider(name = "arrays_min")
	public Object[] createDataMin() {
		return new Object[][] { { new int[] {1, -100, 3, 4, 5}, OptionalInt.of(-100) },
			                    { new int[] { 1, 2, -20, 5, 10 }, OptionalInt.of(-20) } };
	}
	
	@DataProvider(name = "arrays_max")
	public Object[] createDataMax() {
		return new Object[][] { { new int[] {1, -100, 3, 4, 999}, OptionalInt.of(999) },
			                    { new int[] { 1, 2, -20, 5, 10 } , OptionalInt.of(10) } };
	}
	
	@DataProvider(name = "arrays_OddElementsMultiplyByTwo")
	public Object[] createDataOddElementsMultiplyByTwo() {
		return new Object[][] { { new int[] { 1, 2, 5, -1 } , new int[] { 2, 2, 10, -2 }},
			                    { new int[] { -9, 8, 7, 20 } , new int[] { -18, 8, 14, 20 }} };
	}
	
	@DataProvider(name = "arrays_averageValue")
	public Object[] createDataAverageValue() {
		return new Object[][] { { new int[] { } , OptionalDouble.empty() },
			                    { new int[] { 1, 2, -20, 5, 10 } , OptionalDouble.of(-0.4) } };
	}
	
	@DataProvider(name = "arrays_sum")
	public Object[] createDataSum() {
		return new Object[][] { { new int[] { 1, 2, 3, -1 } , 5 },
			                    { new int[] { 1, 2, -20, 5, 10 } , -2 } };
	}
	
	@DataProvider(name = "arrays_numberPositiveElements")
	public Object[] createDataNumberPositiveElements() {
		return new Object[][] { { new int[] { 1, 2, 3, -1 } , 3 },
			                    { new int[] { 1, 2, -20, 5, 10 } , 4 } };
	}
	
	@DataProvider(name = "arrays_numberNegativeElements")
	public Object[] createDataNumberNegativeElements() {
		return new Object[][] { { new int[] { 1, 2, 3, -1 } , 1 },
			                    { new int[] { 1, 2, -20, 5, 10 } , 1 } };
	}
	 
	@DataProvider(name = "arrays_sortStream")
	public Object[] createDataSortStream() {
		return new Object[][] { { new int[] { 1, 2, 5, -1 } , new int[] { -1, 1, 2, 5 }},
			                    { new int[] { -9, 8, 7, 20 } , new int[] { -9, 7, 8, 20 }} };
	}
	
	@AfterClass
	public void tierDown(){
		customArrayActionIntStream = null;
		customArray = null;
	}
}
