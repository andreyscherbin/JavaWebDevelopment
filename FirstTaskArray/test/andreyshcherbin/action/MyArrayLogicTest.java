package test.andreyshcherbin.action;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.testng.annotations.*;

import com.andreyshcherbin.action.MyArrayLogic;
import com.andreyshcherbin.entity.MyArray;

@Listeners(TestListener.class)
public class MyArrayLogicTest {

	MyArrayLogic myArrayLogic;
	MyArray myArray;
	
	@BeforeClass
	public void setUp() {
		myArrayLogic = new MyArrayLogic();
		myArray = new MyArray();
	}

	@Test(dataProvider = "arrays")
	public void testMyArrayEqual(int[] array) {
		myArray.setArray(array);
		assertTrue(myArray.equals(myArray));
	}
	
	@Test(dataProvider = "arrays_min")
	public void testMyArrayLogicMinElement(int[] array, int expectedValue) {
		myArray.setArray(array);
		assertEquals(expectedValue,myArrayLogic.getMinElement(myArray));
	}
	
	@Test(dataProvider = "arrays_max")
	public void testMyArrayLogicMaxElement(int[] array, int expectedValue) {
		myArray.setArray(array);
		assertEquals(expectedValue,myArrayLogic.getMaxElement(myArray));
	}
	
	@Test(dataProvider = "arrays_OddElementsMultiplyByTwo")
	public void testMyArrayLogicOddElementsMultiplyByTwo(int[] array,int[] expectedValue) {
		myArray.setArray(array);		
		int[] actual = myArrayLogic.oddElementsMultiplyByTwo(myArray);
		assertTrue(Arrays.equals(actual, expectedValue));
	}
	
	@Test(dataProvider = "arrays_averageValue")
	public void testMyArrayLogicAverageValue(int[] array, double expectedValue) {
		myArray.setArray(array);
		assertEquals(expectedValue,myArrayLogic.getAverageValue(myArray), 0.001);		
	}
	
	@Test(dataProvider = "arrays_sum")
	public void testMyArrayLogicSum(int[] array, int expectedValue) {
		myArray.setArray(array);
		assertEquals(expectedValue,myArrayLogic.getSum(myArray));		
	}
	
	@Test(dataProvider = "arrays_numberPositiveElements")
	public void testMyArrayLogicNumberPositiveElements(int[] array, int expectedValue) {
		myArray.setArray(array);
		assertEquals(expectedValue,myArrayLogic.getNumberPositiveElements(myArray));		
	}
	
	@Test(dataProvider = "arrays_numberNegativeElements")
	public void testMyArrayLogicNumberNegativeElements(int[] array, int expectedValue) {
		myArray.setArray(array);
		assertEquals(expectedValue,myArrayLogic.getNumberNegativeElements(myArray));		
	}
	
	@Test(dataProvider = "arrays_bubbleSort")
	public void testMyArrayLogicBubbleSort(int[] array, int[] expectedValue) {
		myArray.setArray(array);		
		int[] actual = myArrayLogic.bubbleSort(myArray);
		assertTrue(Arrays.equals(actual, expectedValue));		
	}
	
	@Test(dataProvider = "arrays_quickSort")
	public void testMyArrayLogicQuickSort(int[] array, int[] expectedValue) {
		myArray.setArray(array);		
		int[] actual = myArrayLogic.quickSort(myArray, myArray.getArray(), 0 , myArray.getArray().length -1);
		assertTrue(Arrays.equals(actual, expectedValue));		
	}
	
	@Test(dataProvider = "arrays_mergeSort")
	public void testMyArrayLogicMergeSort(int[] array, int[] expectedValue) {
		myArray.setArray(array);		
		int[] actual = myArrayLogic.mergeSort(myArray, myArray.getArray(), 0 , myArray.getArray().length -1);
		assertTrue(Arrays.equals(actual, expectedValue));		
	}
		
	@DataProvider(name = "arrays")
	public Object[] createData() {
		return new Object[][] { { new int[] { 1, 2, 3, 4 } },
			                    { new int[] { 1, 2, 3, 5 } } };
	}

	@DataProvider(name = "arrays_min")
	public Object[] createDataMin() {
		return new Object[][] { { new int[] { 1, 2, Integer.MIN_VALUE, -1 } , Integer.MIN_VALUE },
			                    { new int[] { 1, 2, -20, 5, 10 } , -20 } };
	}
	
	@DataProvider(name = "arrays_max")
	public Object[] createDataMax() {
		return new Object[][] { { new int[] { 1, 2, Integer.MAX_VALUE, -1 } , Integer.MAX_VALUE },
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
	
	@DataProvider(name = "arrays_bubbleSort")
	public Object[] createDataBubbleSort() {
		return new Object[][] { { new int[] { 1, 2, 3, -1 } , new int[] { -1, 1, 2, 3 } },
			                    { new int[] { 9, 2, -44, -100 } , new int[] { -100, -44, 2, 9 } },
			                    { new int[] { -300, -500, 900, 0 } , new int[] { -500, -300, 0, 900 } },
			                    { new int[] { -4, -3, 9, 7 } , new int[] { -4, -3, 7, 9 } },
			                    { new int[] { 7, 4, 2, -5 } , new int[] { -5, 2, 4, 7 } }};
	}
	
	@DataProvider(name = "arrays_quickSort")
	public Object[] createDataQuickSort() {
		return new Object[][] { { new int[] { 1, 2, 3, -1 } , new int[] { -1, 1, 2, 3 } },
                                { new int[] { 9, 2, -44, -100 } , new int[] { -100, -44, 2, 9 } },
                                { new int[] { -300, -500, 900, 0 } , new int[] { -500, -300, 0, 900 } },
                                { new int[] { -4, -3, 9, 7 } , new int[] { -4, -3, 7, 9 } },
                                { new int[] { 7, 4, 2, -5 } , new int[] { -5, 2, 4, 7 } }};
	}
	
	@DataProvider(name = "arrays_mergeSort")
	public Object[] createDataMergeSort() {
		return new Object[][] { { new int[] { 1, 2, 3, -1 } , new int[] { -1, 1, 2, 3 } },
                                { new int[] { 9, 2, -44, -100 } , new int[] { -100, -44, 2, 9 } },
                                { new int[] { -300, -500, 900, 0 } , new int[] { -500, -300, 0, 900 } },
                                { new int[] { -4, -3, 9, 7 } , new int[] { -4, -3, 7, 9 } },
                                { new int[] { 7, 4, 2, -5 } , new int[] { -5, 2, 4, 7 } }};
	}
}
