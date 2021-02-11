package test.epam.andreyshcherbin.parser;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.epam.andreyshcherbin.parser.DataParser;
import com.epam.andreyshcherbin.validation.DataFilter;
import com.epam.andreyshcherbin.entity.Sphere;
import test.epam.andreyshcherbin.TestListener;

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
		//need rewrite
		/*List<String> arrays = Arrays.asList("1 -5 -3 2 100 -900"			                                   
	                                       ,"-43 -21 -43 -54 -76 -1");       
		List<Sphere> actual = parser.parseData(arrays);
		List<Sphere> expected = List.of(new Sphere(new int[] { 1, -5, -3, 2, 100, -900}),
				                             new Sphere(new int[] { -43, -21, -43, -54, -76, -1}));
				                        		
		Assert.assertEquals(actual, expected);*/ 
	}
	
	@AfterClass
	public void tierDown(){
		parser = null;		
	}
}