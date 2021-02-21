package test.epam.andreyshcherbin.parser;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.epam.andreyshcherbin.parser.DataParser;
import com.epam.andreyshcherbin.entity.CustomPoint;
import com.epam.andreyshcherbin.entity.Sphere;
import com.epam.andreyshcherbin.exception.ShapeException;
import com.epam.andreyshcherbin.generator.IdGenerator;
import test.epam.andreyshcherbin.TestListener;
import java.util.ArrayList;
import java.util.List;

@Listeners(TestListener.class)
public class DataParserTest {
	
	DataParser parser;	
	
	@BeforeClass
	public void setUp() {	
		IdGenerator.reset();
		parser = new DataParser();		
	}
	
	@Test
	public void testParseData() throws ShapeException {
		
		List<String> data = List.of("File structure: Sphere",
				"Center                     Radius",
				"0.0 0.0 0.0                10.0",
				"0XYZ.0 0.0 0.0             10.0",
				"0.0 0.0 0.0                -999.0",
				"10.0 10.0 10.0             10.0",
				"QWEEWQPEWQPQWEPQ           @!#OPQWEQEWO@#)!",
				"",
				"-4.0 -3.0 -2.0             10.0",
				"-4.0 -2.0 -7.0             ____",
				"20.0 30.0 40.0             20.0",
				"-3.0 5.0 7.0               20.0");   
		List<Object[]> actual = parser.parseData(data);	
		List<Sphere> actualButSphere = new ArrayList<>();
		for (Object[] shapeData : actual) {			
			Sphere sphere = new Sphere((CustomPoint)shapeData[0], (double)shapeData[1]);			
				actualButSphere.add(sphere);				
		}
		List<Sphere> expected = List.of(new Sphere(new CustomPoint(0, 0, 0),  10),
                                        new Sphere(new CustomPoint(10, 10, 10), 10),
                                        new Sphere(new CustomPoint(-4, -3, -2), 10),
                                        new Sphere(new CustomPoint(20, 30, 40),  20),
                                        new Sphere(new CustomPoint(-3, 5, 7),  20));
		expected.get(0).setId(1);
		expected.get(1).setId(2);
		expected.get(2).setId(3);
		expected.get(3).setId(4);
		expected.get(4).setId(5);
		Assert.assertEquals(actualButSphere, expected); 
	}
	
	@AfterClass
	public void tierDown(){
		parser = null;		
	}
}