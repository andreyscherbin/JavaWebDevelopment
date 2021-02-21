package test.epam.andreyshcherbin.action;

import org.testng.annotations.Test;
import com.epam.andreyshcherbin.action.SphereAction;
import com.epam.andreyshcherbin.entity.CustomPoint;
import com.epam.andreyshcherbin.entity.Sphere;
import com.epam.andreyshcherbin.exception.ShapeException;
import com.epam.andreyshcherbin.generator.IdGenerator;
import com.epam.andreyshcherbin.validation.SphereValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;

public class SphereActionTest {

	private SphereAction sphereAction;	
	
	@BeforeClass
	public void beforeClass() {
		IdGenerator.reset();
		sphereAction = new SphereAction();		
	}

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
	
	@Test(dataProvider = "dissectionByXYData")
	public void dissectionByXYTest(Sphere sphere, double expectedRatio) throws ShapeException {
		double actualRatio = sphereAction.dissectionByXY(sphere);
		assertEquals(actualRatio, expectedRatio, 0.01);
	}
	
	@DataProvider(name = "areaSurfaceSphereData")
	public Object[][] areaSurfaceSphereData() {
		return new Object[][] { { new Sphere(new CustomPoint(0, 0, 0), 10), 1256.637 },
				                { new Sphere(new CustomPoint(0, 0, 0), 20), 5026.548 },
		                        { new Sphere(new CustomPoint(10, 10, 10), 11), 1520.530},
				                { new Sphere(new CustomPoint(-4, -3, -2), 11), 1520.530 }};
	}

	@DataProvider(name = "volumeSphereData")
	public Object[][] volumeSphereData() {
		return new Object[][] { { new Sphere(new CustomPoint(0, 0, 0), 10), 4188.790 },
				                { new Sphere(new CustomPoint(0, 0, 0), 20), 33510.321 } };
	}
	
	@DataProvider(name = "dissectionByXYData")
	public Object[][] dissectionByXYData() {
		return new Object[][] { { new Sphere(new CustomPoint(0, 0, 0), 10), 1.0 },
				                };
	}
	
	@AfterClass
	public void afterClass() {
		sphereAction = null;		
	}
}
