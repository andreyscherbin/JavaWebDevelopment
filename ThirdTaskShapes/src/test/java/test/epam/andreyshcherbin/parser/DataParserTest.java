package test.epam.andreyshcherbin.parser;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.epam.andreyshcherbin.parser.DataParser;
import com.epam.andreyshcherbin.validation.DataFilter;
import com.epam.andreyshcherbin.entity.CustomPoint;
import com.epam.andreyshcherbin.entity.Sphere;
import com.epam.andreyshcherbin.exception.ResourceException;

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
	public void testParseData() throws ResourceException {
		
		List<String> spheres = Arrays.asList("0.0 0.0 0.0 10.0 0.0 0.0 10.0"			                                   
	                                       ,"10.0 10.0 10.0 20.0 10.0 10.0 10.0");       
		List<Sphere> actual = parser.parseData(spheres);
		List<Sphere> expected = List.of(new Sphere(new CustomPoint(0, 0, 0), new CustomPoint(10, 0, 0), 10),
				                        new Sphere(new CustomPoint(10, 10, 10), new CustomPoint(20, 10, 10), 10));
				                        		
		Assert.assertEquals(actual, expected); 
	}
	
	@AfterClass
	public void tierDown(){
		parser = null;		
	}
}