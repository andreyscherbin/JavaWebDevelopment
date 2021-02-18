package test.epam.andreyshcherbin.warehouse;

import org.testng.annotations.Test;
import com.epam.andreyshcherbin.entity.AbstractShape;
import com.epam.andreyshcherbin.entity.CustomPoint;
import com.epam.andreyshcherbin.entity.ShapeValues;
import com.epam.andreyshcherbin.entity.Sphere;
import com.epam.andreyshcherbin.generator.IdGenerator;
import com.epam.andreyshcherbin.observer.ShapeObserver;
import com.epam.andreyshcherbin.observer.impl.SphereObserver;
import com.epam.andreyshcherbin.warehouse.Warehouse;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertEquals;
import java.util.List;
import java.util.Map;
import org.testng.annotations.AfterClass;

public class WarehouseTest {

	Warehouse warehouse;
	List<AbstractShape> spheres;
	ShapeObserver shapeObserver;

	@BeforeClass
	public void beforeClass() {
		IdGenerator.reset();
		shapeObserver = new SphereObserver();
		spheres = List.of(new Sphere(new CustomPoint(0, 0, 0), new CustomPoint(10, 0, 0), 10),
				          new Sphere(new CustomPoint(10, 10, 10), new CustomPoint(20, 10, 10), 10),
				          new Sphere(new CustomPoint(-4, -3, -2), new CustomPoint(-14, -3, -2), 10),
				          new Sphere(new CustomPoint(20, 30, 40), new CustomPoint(40, 30, 40), 20));
		((Sphere)spheres.get(0)).attach(shapeObserver);
		((Sphere)spheres.get(1)).attach(shapeObserver);
		((Sphere)spheres.get(2)).attach(shapeObserver);
		((Sphere)spheres.get(3)).attach(shapeObserver);
		warehouse = Warehouse.getInstance();
		warehouse.addShapeValues(spheres.get(0).getId(), new ShapeValues(spheres.get(0)));
		warehouse.addShapeValues(spheres.get(1).getId(), new ShapeValues(spheres.get(1)));
		warehouse.addShapeValues(spheres.get(2).getId(), new ShapeValues(spheres.get(2)));
		warehouse.addShapeValues(spheres.get(3).getId(), new ShapeValues(spheres.get(3)));
	}

	@Test(dataProvider = "radiusChangedData")
	public void radiusChangedTest(double radius, Map<Long, ShapeValues> expected) {
		Sphere sphere = (Sphere) spheres.get(0);	
		sphere.setRadius(radius);		
		Map<Long, ShapeValues> actual = warehouse.getShapesValues();
		assertEquals(actual, expected);
	}

	@DataProvider(name = "radiusChangedData")
	public Object[][] radiusChangedData() {
		return new Object[][] { { 100 , Map.of(spheres.get(0).getId(), new ShapeValues(125663.70614359173, 4188790.2047863905),
				                               spheres.get(1).getId(), new ShapeValues(spheres.get(1)),
				                               spheres.get(2).getId(), new ShapeValues(spheres.get(2)),
				                               spheres.get(3).getId(), new ShapeValues(spheres.get(3)))
			                    }
		                      };
	}	

	@AfterClass
	public void afterClass() {
		warehouse = null;
		spheres = null;
		shapeObserver = null;
	}
}
