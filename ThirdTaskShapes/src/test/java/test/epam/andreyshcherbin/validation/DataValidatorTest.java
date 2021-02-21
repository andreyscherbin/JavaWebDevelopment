package test.epam.andreyshcherbin.validation;

import org.testng.annotations.Test;
import com.epam.andreyshcherbin.validation.DataValidator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import static org.testng.Assert.assertEquals;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class DataValidatorTest {	

	@Test(dataProvider = "validatorData")
	public void testValidator(String data, boolean expected) {
		boolean actual = DataValidator.isSphereDataValid(data);
		assertEquals(actual, expected);
		Assert.assertEquals(actual, expected);
	}
	
	@BeforeClass
	public void beforeClass() {		
	}

	@DataProvider(name = "validatorData")
	public Object[][] validatorData() {
		return new Object[][] { { "File structure: Sphere" , false },
		                    	{"Center            Radius", false},
			                    {"0.0 0.0 0.0       10.0" , true},
			                    {"0XYZ.0 0.0 0.0    10.0", false},
			                   	{"0.0 0.0 0.0       -999.0", false},
			                   	{"10.0 10.0 10.0    10.0", true},
			                    {"QWEEWQPEWQPQWEPQ  @!#OPQWEQEWO@#)!" , false},
			                   	{"\n" , false},
			                   	{"-4.0 -3.0 -2.0    10.0", true},
			                   	{"-4.0 -2.0 -7.0    ____", false},
			                   	{"20.0 30.0 40.0    20.0", true},
			                   	{"-3.0 5.0 7.0      20.0", true}
				                };
	}
	
	@AfterClass
	public void afterClass() {		
	}
}
