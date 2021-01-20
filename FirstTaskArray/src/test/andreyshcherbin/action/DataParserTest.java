package test.andreyshcherbin.action;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.andreyshcherbin.action.DataParser;
import com.andreyshcherbin.entity.MyArray;
import java.util.List;

@Listeners(TestListener.class)
public class DataParserTest {
	
	@Test
	public void testParseData() {
		DataParser parser = new DataParser();
		List<MyArray> actual = parser.parseData("1 -5 -3 2 100 -900,"
			                                   + "-5 2z 32 43 56 1 7 8 9,"
	                                           + "-43 -21 -43 -54 -76 -1,"
				                               + "3 2 1 0 -1 -8b -7 -6 -5");
		List<MyArray> expected = List.of(new MyArray(new int[] { 1, -5, -3, 2, 100, -900}),
				                         new MyArray(new int[] { -43, -21, -43, -54, -76, -1}));
				                        		
		Assert.assertEquals(actual, expected);
	}
}