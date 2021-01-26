package test.github.andreyshcherbin.action;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.andreyshcherbin.action.CustomArrayActionIntStream;
import com.github.andreyshcherbin.entity.CustomArray;
import com.github.andreyshcherbin.exception.NotFoundException;
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
	public void testCustomArrayActionIntStreamMinElement(int[] array, int expectedValue) throws NotFoundException {
		customArray.setArray(array);		
		int actualValue = customArrayActionIntStream.getMinElement(customArray);
		assertEquals(actualValue, expectedValue);		
	}
	
	@Test(dataProvider = "arrays_max")
	public void testCustomArrayActionIntStreamMaxElement(int[] array, int expectedValue) throws NotFoundException {
		customArray.setArray(array);		
		int actualValue = customArrayActionIntStream.getMaxElement(customArray);
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
	public void testCustomArrayActionIntStreamAverageValue(int[] array, double expectedValue) throws NotFoundException {
		customArray.setArray(array);		
		double actualValue = customArrayActionIntStream.getAverageValue(customArray);
		assertEquals(actualValue, expectedValue, 0.001);				
	}
	
	@Test(dataProvider = "arrays_sum")
	public void testCustomArrayActionSum(int[] array, int expectedValue) {
		customArray.setArray(array);
		int actualValue = customArrayActionIntStream.getSum(customArray);
		assertEquals(actualValue, expectedValue);		
	}
	
	@Test(dataProvider = "arrays_numberPositiveElements")
	public void testCustomArrayActionNumberPositiveElements(int[] array, int expectedValue) {
		customArray.setArray(array);
		int actualValue = customArrayActionIntStream.getNumberPositiveElements(customArray);
		assertEquals(actualValue, expectedValue);		
	}
	
	@Test(dataProvider = "arrays_numberNegativeElements")
	public void testCustomArrayActionNumberNegativeElements(int[] array, int expectedValue) {
		customArray.setArray(array);
		int actualValue = customArrayActionIntStream.getNumberNegativeElements(customArray);
		assertEquals(actualValue, expectedValue);		
	}

	@DataProvider(name = "arrays_min")
	public Object[] createDataMin() {
		return new Object[][] { { new int[] {1, -100, 3, 4, 5}, -100 },
			                    { new int[] { 1, 2, -20, 5, 10 }, -20 } };
	}
	
	@DataProvider(name = "arrays_max")
	public Object[] createDataMax() {
		return new Object[][] { { new int[] {1, -100, 3, 4, 999}, 999 },
			                    { new int[] { 1, 2, -20, 5, 10 } , 10 } };
	}
	
	@DataProvider(name = "arrays_OddElementsMultiplyByTwo")
	public Object[] createDataOddElementsMultiplyByTwo() {
		return new Object[][] { { new int[] { 1, 2, 5, -1 } , new int[] { 2, 2, 10, -2 }},
			                    { new int[] { -9, 8, 7, 20 } , new int[] { -18, 8, 14, 20 }} };
	}
	
	@DataProvider(name = "arrays_averageValue")
	public Object[] createDataAverageValue() {
		return new Object[][] { { new int[] { 1, 3, -3} , 0.333 },
			                    { new int[] { 1, 2, -20, 5, 10 } , -0.4 } };
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
	
	@AfterClass
	public void tierDown(){
		customArrayActionIntStream = null;
		customArray = null;
	}
}
