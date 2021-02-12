package test.epam.andreyshcherbin.validation;

import org.testng.annotations.Test;

import com.epam.andreyshcherbin.validation.DataFilter;
import org.testng.annotations.BeforeClass;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class DataFilterTest {

	DataFilter filter;

	@Test
	public void testFilter() {
		List<String> actual = filter.filterDouble("0.0 0.0 0.0 10.0 0.0 0.0 10.0,"
	                                         + "0a.0 0.0 0.0 10.0 0.0 0.0 10.0,"
			                                 + "0.0 0.0 0.0 10.0 0.0 0.0 -10.0,"
			                                 + "10.0 10.0 10.0 20.0 10.0 10.0 10.0");
		
        List<String> expected = Arrays.asList("0.0 0.0 0.0 10.0 0.0 0.0 10.0",        		                             
        		                             "10.0 10.0 10.0 20.0 10.0 10.0 10.0");         		
        Assert.assertEquals(actual, expected);
	}

	@BeforeClass
	public void beforeClass() {
		filter = new DataFilter();
	}

	@AfterClass
	public void afterClass() {
		filter = null;
	}

}
