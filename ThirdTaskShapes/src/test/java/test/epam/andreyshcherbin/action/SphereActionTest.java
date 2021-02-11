package test.epam.andreyshcherbin.action;

import org.testng.annotations.Test;

import com.epam.andreyshcherbin.action.SphereAction;
import com.epam.andreyshcherbin.entity.Point;
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
		assertEquals(actualAreaSurface, expectedAreaSurface);
	}

	@Test(dataProvider = "volumeSphereData")
	public void volumeSphereTest(Sphere sphere, Double expectedVolume) {
		Double actualVolume = sphereAction.volumeSphere(sphere);
		assertEquals(actualVolume, expectedVolume, 0.001);
	}

	@DataProvider(name = "areaSurfaceSphereData")
	public Object[][] areaSurfaceSphereData() {
		return new Object[][] { { new Sphere(new Point(0, 0, 0), 10), 1256.0 },
				{ new Sphere(new Point(0, 0, 0), 20), 5024.0 } };
	}

	@DataProvider(name = "volumeSphereData")
	public Object[][] volumeSphereData() {
		return new Object[][] { { new Sphere(new Point(0, 0, 0), 10), 4186.666 },
				{ new Sphere(new Point(0, 0, 0), 20), 33493.333 } };
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
