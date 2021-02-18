package test.epam.andreyshcherbin.repository;

import org.testng.annotations.Test;
import com.epam.andreyshcherbin.entity.AbstractShape;
import com.epam.andreyshcherbin.entity.CustomPoint;
import com.epam.andreyshcherbin.entity.Sphere;
import com.epam.andreyshcherbin.generator.IdGenerator;
import com.epam.andreyshcherbin.repository.ShapeRepository;
import com.epam.andreyshcherbin.repository.impl.AreaSurfaceIntervalSpecification;
import com.epam.andreyshcherbin.repository.impl.IdSpecification;
import com.epam.andreyshcherbin.repository.impl.IsDissectionSpecification;
import com.epam.andreyshcherbin.repository.impl.IsTouchSpecification;
import com.epam.andreyshcherbin.repository.impl.RadiusIntervalSpecification;
import com.epam.andreyshcherbin.repository.impl.VolumeIntervalSpecification;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertEquals;
import java.util.List;
import org.testng.annotations.AfterClass;

public class ShapeRepositoryTest {

	ShapeRepository repository;

	@BeforeClass
	public void beforeClass() {
		IdGenerator.reset();
		repository = new ShapeRepository();	
		repository.addShape(new Sphere(new CustomPoint(0, 0, 0), new CustomPoint(10, 0, 0), 10));
		repository.addShape(new Sphere(new CustomPoint(10, 10, 10), new CustomPoint(20, 10, 10), 10));
		repository.addShape(new Sphere(new CustomPoint(-4, -3, -2), new CustomPoint(-14, -3, -2), 10));
		repository.addShape(new Sphere(new CustomPoint(20, 30, 40), new CustomPoint(40, 30, 40), 20));		
	}	
	
	@Test(dataProvider = "idSpecificationData")
	public void idSpecificationTest(IdSpecification idSpecification, List<AbstractShape> expected) {
		expected.get(0).setId(4);
		List<AbstractShape> actual = repository.query(idSpecification);
		assertEquals(actual, expected);
	}
	
	@Test(dataProvider = "areaSurfaceIntervalSpecificationData")
	public void areaSurfaceIntervalSpecificationTest(AreaSurfaceIntervalSpecification areaSurfaceIntervalSpecification, List<AbstractShape> expected) {       
		expected.get(0).setId(1);
		expected.get(1).setId(2);
		expected.get(2).setId(3);
		expected.get(3).setId(4);
		List<AbstractShape> actual = repository.query(areaSurfaceIntervalSpecification);
		assertEquals(actual, expected);
	}
	
	@Test(dataProvider = "volumeIntervalSpecificationData")
	public void volumeIntervalSpecificationTest(VolumeIntervalSpecification volumeIntervalSpecification, List<AbstractShape> expected) {       
		expected.get(0).setId(1);
		expected.get(1).setId(2);
		expected.get(2).setId(3);		
		List<AbstractShape> actual = repository.query(volumeIntervalSpecification);
		assertEquals(actual, expected);
	}
	
	@Test(dataProvider = "radiusIntervalSpecificationData")
	public void radiusIntervalSpecificationTest(RadiusIntervalSpecification radiusIntervalSpecification, List<AbstractShape> expected) {       
		expected.get(0).setId(1);
		expected.get(1).setId(2);
		expected.get(2).setId(3);
		expected.get(3).setId(4);	
		List<AbstractShape> actual = repository.query(radiusIntervalSpecification);
		assertEquals(actual, expected);
	}
	
	@Test(dataProvider = "isTouchSpecificationData")
	public void isTouchSpecificationTest(IsTouchSpecification isTouchSpecification, List<AbstractShape> expected) {    		
		expected.get(0).setId(2);		
		expected.get(1).setId(4);	
		List<AbstractShape> actual = repository.query(isTouchSpecification);
		assertEquals(actual, expected);
	}
	
	@Test(dataProvider = "isDissectionSpecificationData")
	public void isDissectionSpecificationTest(IsDissectionSpecification isDissectionSpecification, List<AbstractShape> expected) {    		
		expected.get(0).setId(1);		
		expected.get(1).setId(3);	
		List<AbstractShape> actual = repository.query(isDissectionSpecification);
		assertEquals(actual, expected);
	}
	
	@Test(dataProvider = "sortByIDData")
	public void sortByIDTest(List<AbstractShape> expected) {    		
		expected.get(0).setId(1);
		expected.get(1).setId(2);
		expected.get(2).setId(3);
		expected.get(3).setId(4);
		repository.sortByID();
		List<AbstractShape> actual = repository.getShapes();
		assertEquals(actual, expected);
	}
	
	@Test(dataProvider = "sortByRadiusData")
	public void sortByRadiusTest(List<AbstractShape> expected) {    		
		expected.get(0).setId(1);
		expected.get(1).setId(2);
		expected.get(2).setId(3);
		expected.get(3).setId(4);
		repository.sortByRadius();
		List<AbstractShape> actual = repository.getShapes();
		assertEquals(actual, expected);
	}
	
	@Test(dataProvider = "sortByCenterXData")
	public void sortByCenterXTest(List<AbstractShape> expected) {    		
		expected.get(0).setId(3);
		expected.get(1).setId(1);
		expected.get(2).setId(2);
		expected.get(3).setId(4);
		repository.sortByCenterX();
		List<AbstractShape> actual = repository.getShapes();
		assertEquals(actual, expected);
	}
	
	@Test(dataProvider = "sortByCenterYData")
	public void sortByCenterYTest(List<AbstractShape> expected) {    		
		expected.get(0).setId(3);
		expected.get(1).setId(1);
		expected.get(2).setId(2);
		expected.get(3).setId(4);
		repository.sortByCenterY();
		List<AbstractShape> actual = repository.getShapes();
		assertEquals(actual, expected);
	}
	
	@Test(dataProvider = "sortByCenterZData")
	public void sortByCenterZTest(List<AbstractShape> expected) {    		
		expected.get(0).setId(3);
		expected.get(1).setId(1);
		expected.get(2).setId(2);
		expected.get(3).setId(4);
		repository.sortByCenterZ();
		List<AbstractShape> actual = repository.getShapes();
		assertEquals(actual, expected);
	}
	
	@DataProvider(name = "idSpecificationData")
	public Object[][] idSpecificationData() {
		return new Object[][] { { new IdSpecification(4) , List.of(new Sphere(new CustomPoint(20, 30, 40), new CustomPoint(40, 30, 40), 20))}};
	}

	@DataProvider(name = "areaSurfaceIntervalSpecificationData")
	public Object[][] areaSurfaceIntervalSpecificationData() {
		return new Object[][] { { new AreaSurfaceIntervalSpecification(1250,6000) , List.of(new Sphere(new CustomPoint(0, 0, 0), new CustomPoint(10, 0, 0), 10),
                                                                                            new Sphere(new CustomPoint(10, 10, 10), new CustomPoint(20, 10, 10), 10),
                                                                                            new Sphere(new CustomPoint(-4, -3, -2), new CustomPoint(-14, -3, -2), 10),
                                                                                            new Sphere(new CustomPoint(20, 30, 40), new CustomPoint(40, 30, 40), 20))}
		                      };
	}
	
	@DataProvider(name = "volumeIntervalSpecificationData")
	public Object[][] volumeIntervalSpecificationData() {
		return new Object[][] { { new VolumeIntervalSpecification(1250,6000) , List.of(new Sphere(new CustomPoint(0, 0, 0), new CustomPoint(10, 0, 0), 10),
                                                                                       new Sphere(new CustomPoint(10, 10, 10), new CustomPoint(20, 10, 10), 10),
                                                                                       new Sphere(new CustomPoint(-4, -3, -2), new CustomPoint(-14, -3, -2), 10))}
                                                                                          
		                      };
	}
	
	@DataProvider(name = "radiusIntervalSpecificationData")
	public Object[][] radiusIntervalSpecificationData() {
		return new Object[][] { { new RadiusIntervalSpecification(10,20) , List.of(new Sphere(new CustomPoint(0, 0, 0), new CustomPoint(10, 0, 0), 10),
                                                                                   new Sphere(new CustomPoint(10, 10, 10), new CustomPoint(20, 10, 10), 10),
                                                                                   new Sphere(new CustomPoint(-4, -3, -2), new CustomPoint(-14, -3, -2), 10),
																				   new Sphere(new CustomPoint(20, 30, 40), new CustomPoint(40, 30, 40), 20))}
		                      };
	}
	
	@DataProvider(name = "isTouchSpecificationData")
	public Object[][] isTouchSpecificationData() {
		return new Object[][] { { new IsTouchSpecification() , List.of(new Sphere(new CustomPoint(10, 10, 10), new CustomPoint(20, 10, 10), 10),                                                                       
																	   new Sphere(new CustomPoint(20, 30, 40), new CustomPoint(40, 30, 40), 20))}
		                      };
	}
	
	@DataProvider(name = "isDissectionSpecificationData")
	public Object[][] isDissectionSpecificationData() {
		return new Object[][] { { new IsDissectionSpecification() , List.of(new Sphere(new CustomPoint(0, 0, 0), new CustomPoint(10, 0, 0), 10),                                                                       
				                                                            new Sphere(new CustomPoint(-4, -3, -2), new CustomPoint(-14, -3, -2), 10))}
		                      };
	}
	
	@DataProvider(name = "sortByIDData")
	public Object[][] sortByIDData() {
		return new Object[][] { { List.of(new Sphere(new CustomPoint(0, 0, 0), new CustomPoint(10, 0, 0), 10),
                                          new Sphere(new CustomPoint(10, 10, 10), new CustomPoint(20, 10, 10), 10),
                                          new Sphere(new CustomPoint(-4, -3, -2), new CustomPoint(-14, -3, -2), 10),
                                          new Sphere(new CustomPoint(20, 30, 40), new CustomPoint(40, 30, 40), 20))
			                    }
		                      };
	}
	
	@DataProvider(name = "sortByRadiusData")
	public Object[][] sortByRadiusData() {
		return new Object[][] { { List.of(new Sphere(new CustomPoint(0, 0, 0), new CustomPoint(10, 0, 0), 10),
                                          new Sphere(new CustomPoint(10, 10, 10), new CustomPoint(20, 10, 10), 10),
                                          new Sphere(new CustomPoint(-4, -3, -2), new CustomPoint(-14, -3, -2), 10),
                                          new Sphere(new CustomPoint(20, 30, 40), new CustomPoint(40, 30, 40), 20))
			                    }
		                      };
	}
	
	@DataProvider(name = "sortByCenterXData")
	public Object[][] sortByCenterXData() {
		return new Object[][] { { List.of(new Sphere(new CustomPoint(-4, -3, -2), new CustomPoint(-14, -3, -2), 10),
				                          new Sphere(new CustomPoint(0, 0, 0), new CustomPoint(10, 0, 0), 10),
				                          new Sphere(new CustomPoint(10, 10, 10), new CustomPoint(20, 10, 10), 10),
				                          new Sphere(new CustomPoint(20, 30, 40), new CustomPoint(40, 30, 40), 20))
			                    }
		                      };
	}	
	
	@DataProvider(name = "sortByCenterYData")
	public Object[][] sortByCenterYData() {
		return new Object[][] { { List.of(new Sphere(new CustomPoint(-4, -3, -2), new CustomPoint(-14, -3, -2), 10),
				                          new Sphere(new CustomPoint(0, 0, 0), new CustomPoint(10, 0, 0), 10),
				                          new Sphere(new CustomPoint(10, 10, 10), new CustomPoint(20, 10, 10), 10),
				                          new Sphere(new CustomPoint(20, 30, 40), new CustomPoint(40, 30, 40), 20))
			                    }
		                      };
	}	
	
	@DataProvider(name = "sortByCenterZData")
	public Object[][] sortByCenterZData() {
		return new Object[][] { { List.of(new Sphere(new CustomPoint(-4, -3, -2), new CustomPoint(-14, -3, -2), 10),
				                          new Sphere(new CustomPoint(0, 0, 0), new CustomPoint(10, 0, 0), 10),
				                          new Sphere(new CustomPoint(10, 10, 10), new CustomPoint(20, 10, 10), 10),
				                          new Sphere(new CustomPoint(20, 30, 40), new CustomPoint(40, 30, 40), 20))
			                    }
		                      };
	}	
	
	@AfterClass
	public void afterClass() {
		repository = null;
	}
}
