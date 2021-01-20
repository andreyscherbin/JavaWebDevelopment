package test.andreyshcherbin.action;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.andreyshcherbin.action.DataReader;
import com.andreyshcherbin.exception.ResourceException;

import static org.testng.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Listeners(TestListener.class)
public class DataReaderTest {
	static final String TEST_FILE = "data\\arrays.txt";
	static Logger logger = LogManager.getLogger();

	@Test
	public void testRead() {
		DataReader reader = new DataReader();
		String actual = null;
		try {
			actual = reader.read(TEST_FILE);
		} catch (ResourceException e) {						
			logger.error(e.getCause());
			logger.error(e.getClass() + " " + e.getMessage());				
		}
		String expected = "1 -5 -3 2 100 -900,"
				+ "-5 2z 32 43 56 1 7 8 9,"
				+ "-43 -21 -43 -54 -76 -1,"
				+ "3 2 1 0 -1 -8b -7 -6 -5";
		assertEquals(actual, expected);
	}
}