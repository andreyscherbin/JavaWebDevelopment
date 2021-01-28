package test.github.andreyshcherbin.validation;

import org.testng.annotations.Test;

import com.github.andreyshcherbin.validation.DataFilter;
import org.testng.annotations.BeforeClass;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class DataFilterTest {

	DataFilter filter;

	@Test
	public void testFilter() {
		List<String> actual = filter.filterInt("1 -5 -3 2 100 -900,"
                                            + "-5 2z 32 43 56 1 7 8 9,"
                                            + "-43 -21 -43 -54 -76 -1,"
                                            + "3 2 1 0 -1 -8b -7 -6 -5");
        List<String> expected = Arrays.asList("1 -5 -3 2 100 -900",
        		                             "-43 -21 -43 -54 -76 -1");         		
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
