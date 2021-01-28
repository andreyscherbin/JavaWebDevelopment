package test.github.andreyshcherbin.reader;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.andreyshcherbin.reader.DataReader;
import com.github.andreyshcherbin.exception.ResourceException;

import static org.testng.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import test.github.andreyshcherbin.TestListener;

@Listeners(TestListener.class)
public class DataReaderTest {

	static final String TEST_FILE = "data\\arrays.txt";
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
		String expected = "1 -5 -3 2 100 -900," 
		               + "-5 2z 32 43 56 1 7 8 9,"
				       + "-43 -21 -43 -54 -76 -1,"
				       + "3 2 1 0 -1 -8b -7 -6 -5";
		assertEquals(actual, expected);
	}

	@Test(expectedExceptionsMessageRegExp = "resource exception")
	public void testReadExceptionMessage() {		
		try {				
			reader.read(TEST_FILE_ERROR);			
			fail("Test for read " + TEST_FILE_ERROR + " should have thrown a ResourceException");
		} catch (ResourceException e) {
			assertEquals(e.getMessage(), "resource exception");
		}
	}
	
	@AfterClass
	public void tierDown(){
		reader = null;
	}
}