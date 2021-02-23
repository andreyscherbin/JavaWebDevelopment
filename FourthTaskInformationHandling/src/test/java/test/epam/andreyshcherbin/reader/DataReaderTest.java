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

	static final String TEST_FILE = "data\\text.txt";
	static final String TEST_FILE_ERROR = "data\\no_such_file.txt";
	
	DataReader reader;
	
	@BeforeClass
	public void setUp() {		
		reader = new DataReader();
	}

	@Test
	public void testRead() throws TextException {		
		String actual;
		actual = reader.read(TEST_FILE);
		System.lineSeparator();
	    String expected = "	Although the Greek philosopher Heraclitus wasn’t well known as a software developer,\n"
	    		+ "he seemed to have a good handle on the subject. He has been quoted as saying,\n"
	    		+ "“The only constant is change.” That statement captures a foundational truth of\n"
	    		+ "software development.\n"
	    		+ "	The way we develop applications today is different than it was a year ago, 5 years\n"
	    		+ "ago, 10 years ago, and certainly 15 years ago, when an initial form of the Spring\n"
	    		+ "Framework was introduced in Rod Johnson’s book, Expert One-on-One J2EE Design\n"
	    		+ "and Development (Wrox, 2002, http://mng.bz/oVjy).\n"
	    		+ "	Back then, the most common types of applications developed were browserbased\n"
	    		+ "web applications, backed by relational databases. While that type of development\n"
	    		+ "is still relevant, and Spring is well equipped for those kinds of applications,\n"
	    		+ "we’re now also interested in developing applications composed of microservices\n"
	    		+ "destined for the cloud that persist data in a variety of databases. And a new interest\n"
	    		+ "in reactive programming aims to provide greater scalability and improved performance\n"
	    		+ "with non-blocking operations.\n"
	    		+ "	As software development evolved, the Spring Framework also changed to address\n"
	    		+ "modern development concerns, including microservices and reactive programming.\n"
	    		+ "Spring also set out to simplify its own development model by introducing Spring Boot.\n"
	    		+ "	Whether you’re developing a simple database-backed web application or constructing\n"
	    		+ "a modern application built around microservices, Spring is the framework\n"
	    		+ "that will help you achieve your goals. This chapter is your first step in a journey\n"
	    		+ "through modern application development with Spring.";
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