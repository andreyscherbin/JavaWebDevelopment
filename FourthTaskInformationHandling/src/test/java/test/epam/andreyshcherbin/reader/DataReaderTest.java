package test.epam.andreyshcherbin.reader;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.epam.andreyshcherbin.reader.DataReader;
import com.epam.andreyshcherbin.exception.TextException;
import static org.testng.Assert.*;
import test.epam.andreyshcherbin.TestListener;

@Listeners(TestListener.class)
public class DataReaderTest {

	private static final String TEST_FILE = "data\\text.txt";
	private static final String TEST_FILE_ERROR = "data\\no_such_file.txt";
	
	DataReader reader;
	
	@BeforeClass
	public void setUp() {		
		reader = new DataReader();
	}

	@Test
	public void testRead() throws TextException {		
		String actual;
		actual = reader.read(TEST_FILE);		
	    String expected = "	It has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the \"Динамо\" (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum!\n"
	    		+ "	It is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English?\n"
	    		+ "	It is a established fact that a reader will be of a page when looking at its layout...\n"
	    		+ "	Bye бандерлоги.";
		assertEquals(actual, expected);
	}

	@Test(expectedExceptionsMessageRegExp = "resource exception")
	public void testReadExceptionMessage() {		
		try {				
			reader.read(TEST_FILE_ERROR);			
			fail("Test for read " + TEST_FILE_ERROR + " should have thrown a TextException");
		} catch (TextException e) {
			assertEquals(e.getMessage(), "resource exception: " + TEST_FILE_ERROR);
		}
	}
	
	@AfterClass
	public void tierDown(){
		reader = null;
	}
}