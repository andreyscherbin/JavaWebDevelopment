package com.epam.andreyshcherbin.main;

import java.util.List;
import com.epam.andreyshcherbin.entity.CustomPoint;
import com.epam.andreyshcherbin.entity.Sphere;
import com.epam.andreyshcherbin.exception.ShapeException;
import com.epam.andreyshcherbin.factory.ShapeFactory;
import com.epam.andreyshcherbin.parser.DataParser;
import com.epam.andreyshcherbin.reader.DataReader;
import com.epam.andreyshcherbin.repository.ShapeRepository;
import com.epam.andreyshcherbin.repository.impl.AreaSurfaceIntervalSpecification;
import com.epam.andreyshcherbin.repository.impl.IdSpecification;
import com.epam.andreyshcherbin.repository.impl.IsDissectionSpecification;
import com.epam.andreyshcherbin.repository.impl.IsTouchSpecification;
import com.epam.andreyshcherbin.repository.impl.RadiusIntervalSpecification;
import com.epam.andreyshcherbin.repository.impl.VolumeIntervalSpecification;
import com.epam.andreyshcherbin.validation.SphereValidator;

public class Main {

	public static void main(String[] args) throws ShapeException {
		DataReader reader = new DataReader();
		List<String> data = reader.read("data\\spheres.txt");	
		DataParser parser = new DataParser();
		List<Object[]> shapesData = parser.parseData(data);				
		ShapeRepository repository = new ShapeRepository();
		SphereValidator sphereValidator = new SphereValidator();
		for (Object[] shapeData : shapesData) {			
			Sphere sphere = (Sphere) ShapeFactory.getShapeFromFactory((CustomPoint)shapeData[0],
					                                             (CustomPoint) shapeData[1],
					                                             (double)shapeData[2]);
			if(sphereValidator.isSphere(sphere)) {
				repository.addShape(sphere);	
			}
		}
		System.out.println(repository.toString());		
		System.out.println("ById: " + repository.query(new IdSpecification(4)));
		System.out.println("ByAreaSurfaceInterval: " + repository.query(new AreaSurfaceIntervalSpecification(1250,6000)));
		System.out.println("ByVolume: " + repository.query(new VolumeIntervalSpecification(1250,6000)));
		System.out.println("ByRadius: " + repository.query(new RadiusIntervalSpecification(10,20)));
		System.out.println("ByTouch: " + repository.query(new IsTouchSpecification()));
		System.out.println("ByDissection: " + repository.query(new IsDissectionSpecification()));
	}
}
