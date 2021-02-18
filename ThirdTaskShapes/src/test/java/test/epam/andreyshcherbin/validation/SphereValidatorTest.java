package test.epam.andreyshcherbin.validation;

import org.testng.annotations.Test;

import com.epam.andreyshcherbin.entity.CustomPoint;
import com.epam.andreyshcherbin.entity.Sphere;
import com.epam.andreyshcherbin.generator.IdGenerator;
import com.epam.andreyshcherbin.validation.SphereValidator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;

public class SphereValidatorTest {

	private SphereValidator sphereValidator;
	
	@BeforeClass
	public void beforeClass() {
		IdGenerator.reset();
		sphereValidator = new SphereValidator();
	}
	
	@Test(dataProvider = "isSphereData")
	public void isSphereTest(Sphere sphere, boolean expected) {
		boolean actual = sphereValidator.isSphere(sphere);		
		assertEquals(actual, expected);
	}
	
	@DataProvider(name = "isSphereData")
	public Object[][] isSphereData() {
		return new Object[][] { { new Sphere(new CustomPoint(10, 10, 10), new CustomPoint(20, 10, 10), 10), true },
				                { new Sphere(new CustomPoint(0, 0, 0), new CustomPoint(20, 0, 0), 20), true }, 
								{ new Sphere(new CustomPoint(-200, 300, 400), new CustomPoint(-200, 300, 450), 50), true },
				                { new Sphere(new CustomPoint(-222.222, -333.12231, -4532.2122), new CustomPoint(-900.222, -333.12231, -4532.2122), 678), true }};
	}
	
	@AfterClass
	public void afterClass() {
		sphereValidator = null;
	}

}
