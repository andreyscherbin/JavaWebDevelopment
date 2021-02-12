package test.epam.andreyshcherbin.action;

import org.testng.annotations.Test;

import com.epam.andreyshcherbin.action.SphereAction;
import com.epam.andreyshcherbin.entity.CustomPoint;
import com.epam.andreyshcherbin.entity.Sphere;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;

public class SphereActionTest {

	private SphereAction sphereAction;

	@Test(dataProvider = "areaSurfaceSphereData")
	public void areaSurfaceSphereTest(Sphere sphere, Double expectedAreaSurface) {
		Double actualAreaSurface = sphereAction.areaSurfaceSphere(sphere);
		assertEquals(actualAreaSurface, expectedAreaSurface, 0.001);
	}

	@Test(dataProvider = "volumeSphereData")
	public void volumeSphereTest(Sphere sphere, Double expectedVolume) {
		Double actualVolume = sphereAction.volumeSphere(sphere);
		assertEquals(actualVolume, expectedVolume, 0.001);
	}
	
	@Test(dataProvider = "isTouchData")
	public void isTouchTest(Sphere sphere, boolean expected) {
		boolean actual = sphereAction.isTouch(sphere);
		assertEquals(actual, expected);
	}
	
	@Test(dataProvider = "dissectionByXYData")
	public void dissectionByXYTest(Sphere sphere, double expectedRatio) {
		double actualRatio = sphereAction.dissectionByXY(sphere);
		assertEquals(actualRatio, expectedRatio, 0.01);
	}
	
	@DataProvider(name = "areaSurfaceSphereData")
	public Object[][] areaSurfaceSphereData() {
		return new Object[][] { { new Sphere(new CustomPoint(0, 0, 0), new CustomPoint(10, 0, 0), 10), 1256.0 },
				                { new Sphere(new CustomPoint(0, 0, 0), new CustomPoint(20, 0, 0), 20), 5024.0 } };
	}

	@DataProvider(name = "volumeSphereData")
	public Object[][] volumeSphereData() {
		return new Object[][] { { new Sphere(new CustomPoint(0, 0, 0), new CustomPoint(10, 0, 0), 10), 4186.666 },
				                { new Sphere(new CustomPoint(0, 0, 0), new CustomPoint(20, 0, 0), 20), 33493.333 } };
	}
	
	@DataProvider(name = "isTouchData")
	public Object[][] isTouchData() {
		return new Object[][] { { new Sphere(new CustomPoint(10, 10, 10), new CustomPoint(20, 10, 10), 10), true },
				                { new Sphere(new CustomPoint(20, 20, 20), new CustomPoint(40, 20, 20), 20), true } };
	}
	
	@DataProvider(name = "dissectionByXYData")
	public Object[][] dissectionByXYData() {
		return new Object[][] { { new Sphere(new CustomPoint(0, 0, 0), new CustomPoint(10, 0, 0), 10), 1.0 },
				                };
	}

	@BeforeClass
	public void beforeClass() {
		sphereAction = new SphereAction();
	}

	@AfterClass
	public void afterClass() {
		sphereAction = null;
	}

}
