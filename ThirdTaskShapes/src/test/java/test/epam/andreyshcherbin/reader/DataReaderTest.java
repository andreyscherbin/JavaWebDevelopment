package test.epam.andreyshcherbin.reader;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.epam.andreyshcherbin.reader.DataReader;
import com.epam.andreyshcherbin.exception.ShapeException;
import com.epam.andreyshcherbin.generator.IdGenerator;

import static org.testng.Assert.*;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import test.epam.andreyshcherbin.TestListener;

@Listeners(TestListener.class)
public class DataReaderTest {

	static final String TEST_FILE = "data\\spheres.txt";
	static final String TEST_FILE_ERROR = "data\\no_such_file.txt";
	
	DataReader reader;
	
	@BeforeClass
	public void setUp() {
		IdGenerator.reset();
		reader = new DataReader();
	}

	@Test
	public void testRead() throws ShapeException {		
		List<String> actual;
		actual = reader.read(TEST_FILE);
		String newLine = System.lineSeparator();
		List<String> expected = List.of("File structure: Sphere",
				"Center            Boundary             Radius",
				"0.0 0.0 0.0       10.0 0.0 0.0         10.0",
				"0XYZ.0 0.0 0.0    10.0 0.0 0.0         10.0",
				"0.0 0.0 0.0       10.0 0.0 0.0         -999.0",
				"10.0 10.0 10.0    20.0 10.0 10.0       10.0",
				"QWEEWQPEWQPQWEPQ  WE@!#@#!!#!#@P@!#P   @!#OPQWEQEWO@#)!",
				"",
				"-4.0 -3.0 -2.0    -14.0 -3.0 -2.0      10.0",
				"-4.0 -2.0 -7.0    -8.0  ____ ____      ____",
				"20.0 30.0 40.0     40.0 30.0 40.0      20.0",
				"-3.0 5.0 7.0       40.0 30.0 40.0      20.0");
		assertEquals(actual, expected);
	}

	@Test(expectedExceptionsMessageRegExp = "resource exception")
	public void testReadExceptionMessage() {		
		try {				
			reader.read(TEST_FILE_ERROR);			
			fail("Test for read " + TEST_FILE_ERROR + " should have thrown a ResourceException");
		} catch (ShapeException e) {
			assertEquals(e.getMessage(), "resource exception: " + TEST_FILE_ERROR);
		}
	}
	
	@AfterClass
	public void tierDown(){
		reader = null;
	}
}