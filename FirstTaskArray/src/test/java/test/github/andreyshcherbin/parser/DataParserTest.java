package test.github.andreyshcherbin.parser;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.andreyshcherbin.parser.DataParser;
import com.github.andreyshcherbin.entity.CustomArray;
import test.github.andreyshcherbin.TestListener;

import java.util.List;

@Listeners(TestListener.class)
public class DataParserTest {
	
	@BeforeClass
	public void setUp() {		
	}
	
	@Test
	public void testParseData() {
		DataParser parser = new DataParser();
		List<CustomArray> actual = parser.parseData("1 -5 -3 2 100 -900,"
			                                   + "-5 2z 32 43 56 1 7 8 9,"
	                                           + "-43 -21 -43 -54 -76 -1,"
				                               + "3 2 1 0 -1 -8b -7 -6 -5");
		List<CustomArray> expected = List.of(new CustomArray(new int[] { 1, -5, -3, 2, 100, -900}),
				                         new CustomArray(new int[] { -43, -21, -43, -54, -76, -1}));
				                        		
		Assert.assertEquals(actual, expected);
	}
	
	@AfterClass
	public void tierDown(){	 
	}
}