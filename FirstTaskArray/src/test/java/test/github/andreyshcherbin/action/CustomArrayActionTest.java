package test.github.andreyshcherbin.action;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.Arrays;
import org.testng.annotations.*;
import com.github.andreyshcherbin.action.CustomArrayAction;
import com.github.andreyshcherbin.action.CustomArraySort;
import com.github.andreyshcherbin.entity.CustomArray;
import test.github.andreyshcherbin.TestListener;

@Listeners(TestListener.class)
public class CustomArrayActionTest {

	CustomArrayAction customArrayAction;
	CustomArraySort customArraySort;
	CustomArray customArray;
	
	@BeforeClass
	public void setUp() {
		customArrayAction = new CustomArrayAction();
		customArray = new CustomArray();
		customArraySort = new CustomArraySort();
	}

	@Test(dataProvider = "arrays")
	public void testCustomArrayEqual(int[] array) {
		customArray.setArray(array);
		boolean result = customArray.equals(customArray);
		assertTrue(result);
	}
	
	@Test(dataProvider = "arrays_min")
	public void testCustomArrayActionMinElement(int[] array, int expectedValue) {
		customArray.setArray(array);
		int actualValue = customArrayAction.findMinElement(customArray);
		assertEquals(actualValue, expectedValue);
	}
	
	@Test(dataProvider = "arrays_max")
	public void testCustomArrayActionMaxElement(int[] array, int expectedValue) {
		customArray.setArray(array);
		int actualValue = customArrayAction.findMaxElement(customArray);
		assertEquals(actualValue,expectedValue);
	}
	
	@Test(dataProvider = "arrays_OddElementsMultiplyByTwo")
	public void testCustomArrayActionOddElementsMultiplyByTwo(int[] array,int[] expectedValue) {
		customArray.setArray(array);		
		int[] actual = customArrayAction.oddElementsMultiplyByTwo(customArray);
		boolean result = Arrays.equals(actual, expectedValue);
		assertTrue(result);
	}
	
	@Test(dataProvider = "arrays_averageValue")
	public void testCustomArrayActionAverageValue(int[] array, double expectedValue) {
		customArray.setArray(array);
		double actualValue = customArrayAction.findAverageValue(customArray);
		assertEquals(actualValue,expectedValue, 0.001);		
	}
	
	@Test(dataProvider = "arrays_sum")
	public void testCustomArrayActionSum(int[] array, int expectedValue) {
		customArray.setArray(array);
		int actualValue = customArrayAction.findSum(customArray);
		assertEquals(actualValue,expectedValue);		
	}
	
	@Test(dataProvider = "arrays_numberPositiveElements")
	public void testCustomArrayActionNumberPositiveElements(int[] array, int expectedValue) {
		customArray.setArray(array);
		int actualValue = customArrayAction.findNumberPositiveElements(customArray);
		assertEquals(actualValue,expectedValue);		
	}
	
	@Test(dataProvider = "arrays_numberNegativeElements")
	public void testCustomArrayActionNumberNegativeElements(int[] array, int expectedValue) {
		customArray.setArray(array);
		int actualValue = customArrayAction.findNumberNegativeElements(customArray);
		assertEquals(actualValue,expectedValue);		
	}
	
	@Test(dataProvider = "arrays_bubbleSort")
	public void testCustomArrayActionBubbleSort(int[] array, int[] expectedValue) {
		customArray.setArray(array);		
		int[] actual = customArraySort.bubbleSort(customArray);
		boolean result = Arrays.equals(actual, expectedValue);
		assertTrue(result);		
	}
	
	@Test(dataProvider = "arrays_quickSort")
	public void testCustomArrayActionQuickSort(int[] array, int[] expectedValue) {
		customArray.setArray(array);
		int length = array.length - 1;
		int[] actual = customArraySort.quickSort(customArray, array, 0 , length);
		boolean result = Arrays.equals(actual, expectedValue);
		assertTrue(result);		
	}
	
	@Test(dataProvider = "arrays_mergeSort")
	public void testCustomArrayActionMergeSort(int[] array, int[] expectedValue) {
		customArray.setArray(array);
		int length = array.length - 1;
		int[] actual = customArraySort.mergeSort(customArray, array, 0 , length);
		boolean result = Arrays.equals(actual, expectedValue);
		assertTrue(result);	
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
	
	@AfterClass
	public void tierDown(){
		customArrayAction = null;
		customArray = null;
	}
}
