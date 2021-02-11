package test.epam.andreyshcherbin.reader;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.epam.andreyshcherbin.reader.DataReader;
import com.epam.andreyshcherbin.exception.ResourceException;

import static org.testng.Assert.*;

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
		reader = new DataReader();
	}

	@Test
	public void testRead() throws ResourceException {		
		String actual;
		actual = reader.read(TEST_FILE);
		String expected = "0.0 0.0 0.0 10.0 0.0 0.0 10.0," 
		               + "0a.0 0.0 0.0 10.0 0.0 0.0 10.0,"
				       + "0.0 0.0 0.0 10.0 0.0 0.0 -10.0,"
				       + "10.0 10.0 10.0 20.0 10.0 10.0 10.0";
		assertEquals(actual, expected);
	}

	@Test(expectedExceptionsMessageRegExp = "resource exception")
	public void testReadExceptionMessage() {		
		try {				
			reader.read(TEST_FILE_ERROR);			
			fail("Test for read " + TEST_FILE_ERROR + " should have thrown a ResourceException");
		} catch (ResourceException e) {
			assertEquals(e.getMessage(), "resource exception: " + TEST_FILE_ERROR);
		}
	}
	
	@AfterClass
	public void tierDown(){
		reader = null;
	}
}