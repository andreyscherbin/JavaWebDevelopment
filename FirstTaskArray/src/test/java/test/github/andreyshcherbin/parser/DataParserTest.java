package test.github.andreyshcherbin.parser;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.andreyshcherbin.parser.DataParser;
import com.github.andreyshcherbin.validation.DataFilter;
import com.github.andreyshcherbin.entity.CustomArray;
import test.github.andreyshcherbin.TestListener;

import java.util.Arrays;
import java.util.List;

@Listeners(TestListener.class)
public class DataParserTest {
	
	DataParser parser;	
	
	@BeforeClass
	public void setUp() {	
		parser = new DataParser();		
	}
	
	@Test
	public void testParseData() {
		List<String> arrays = Arrays.asList("1 -5 -3 2 100 -900"			                                   
	                                       ,"-43 -21 -43 -54 -76 -1");       
		List<CustomArray> actual = parser.parseData(arrays);
		List<CustomArray> expected = List.of(new CustomArray(new int[] { 1, -5, -3, 2, 100, -900}),
				                             new CustomArray(new int[] { -43, -21, -43, -54, -76, -1}));
				                        		
		Assert.assertEquals(actual, expected);
	}
	
	@AfterClass
	public void tierDown(){
		parser = null;		
	}
}