package test.andreyshcherbin.action;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.andreyshcherbin.action.MyArrayLogicIntStream;
import com.andreyshcherbin.entity.MyArray;
import com.andreyshcherbin.exception.NotFoundException;

@Listeners(TestListener.class)
public class MyArrayLogicIntStreamTest {

	static Logger logger = LogManager.getLogger();
	MyArrayLogicIntStream myArrayLogicIntStream;
	MyArray myArray;

	@BeforeClass
	public void setUp() {
		myArrayLogicIntStream = new MyArrayLogicIntStream();
		myArray = new MyArray();
	}

	@Test(dataProvider = "arrays_min")
	public void testMyArrayLogicIntStreamMinElement(int[] array, int expectedValue) {
		myArray.setArray(array);
		try {
			assertEquals(expectedValue, myArrayLogicIntStream.getMinElement(myArray));
		} catch (NotFoundException e) {
			logger.error(e.getCause());
			logger.error(e.getClass() + " " + e.getMessage());
		}
	}
	
	@Test(dataProvider = "arrays_max")
	public void testMyArrayLogicIntStreamMaxElement(int[] array, int expectedValue) {
		myArray.setArray(array);
		try {
			assertEquals(expectedValue, myArrayLogicIntStream.getMaxElement(myArray));
		} catch (NotFoundException e) {
			logger.error(e.getCause());
			logger.error(e.getClass() + " " + e.getMessage());
		}
	}
	
	@Test(dataProvider = "arrays_OddElementsMultiplyByTwo")
	public void testMyArrayLogicIntStreamOddElementsMultiplyByTwo(int[] array, int[] expectedValue) {
		myArray.setArray(array);		
		int[] actual = myArrayLogicIntStream.oddElementsMultiplyByTwo(myArray);		
		assertTrue(Arrays.equals(actual, expectedValue));
	}
	
	@Test(dataProvider = "arrays_averageValue")
	public void testMyArrayLogicIntStreamAverageValue(int[] array, double expectedValue) {
		myArray.setArray(array);
		try {
			assertEquals(expectedValue,myArrayLogicIntStream.getAverageValue(myArray), 0.001);
		} catch (NotFoundException e) {			
			logger.error(e.getCause());
			logger.error(e.getClass() + " " + e.getMessage());
		}		
	}
	
	@Test(dataProvider = "arrays_sum")
	public void testMyArrayLogicSum(int[] array, int expectedValue) {
		myArray.setArray(array);
		assertEquals(expectedValue,myArrayLogicIntStream.getSum(myArray));		
	}
	
	@Test(dataProvider = "arrays_numberPositiveElements")
	public void testMyArrayLogicNumberPositiveElements(int[] array, int expectedValue) {
		myArray.setArray(array);
		assertEquals(expectedValue,myArrayLogicIntStream.getNumberPositiveElements(myArray));		
	}
	
	@Test(dataProvider = "arrays_numberNegativeElements")
	public void testMyArrayLogicNumberNegativeElements(int[] array, int expectedValue) {
		myArray.setArray(array);
		assertEquals(expectedValue,myArrayLogicIntStream.getNumberNegativeElements(myArray));		
	}

	@DataProvider(name = "arrays_min")
	public Object[] createDataMin() {
		return new Object[][] { { new int[] {}, Integer.MIN_VALUE },
			                    { new int[] { 1, 2, -20, 5, 10 }, -20 } };
	}
	
	@DataProvider(name = "arrays_max")
	public Object[] createDataMax() {
		return new Object[][] { { new int[] {}, Integer.MAX_VALUE },
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
}
