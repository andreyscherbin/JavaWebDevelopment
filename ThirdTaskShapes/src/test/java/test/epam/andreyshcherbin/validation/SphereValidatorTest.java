package test.epam.andreyshcherbin.validation;

import org.testng.annotations.Test;

import com.epam.andreyshcherbin.entity.Point;
import com.epam.andreyshcherbin.entity.Sphere;
import com.epam.andreyshcherbin.validation.SphereValidator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;

public class SphereValidatorTest {

	private SphereValidator sphereValidator;
	
	@Test(dataProvider = "isSphereData")
	public void isSphereTest(Sphere sphere, boolean expected) {
		boolean actual = sphereValidator.isSphere(sphere);		
		assertEquals(actual, expected);
	}

	@BeforeClass
	public void beforeClass() {
		sphereValidator = new SphereValidator();
	}

	@AfterClass
	public void afterClass() {
		sphereValidator = null;
	}
	
	@DataProvider(name = "isSphereData")
	public Object[][] isSphereData() {
		return new Object[][] { { new Sphere(new Point(10, 10, 10), new Point(20, 10, 10), 10), true },
				                { new Sphere(new Point(0, 0, 0), new Point(20, 0, 0), 20), true } };
	}

}
