package com.epam.andreyshcherbin.parser;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.andreyshcherbin.entity.CustomPoint;
import com.epam.andreyshcherbin.entity.Sphere;
import com.epam.andreyshcherbin.exception.ResourceException;
import com.epam.andreyshcherbin.factory.ShapeFactory;
import com.epam.andreyshcherbin.validation.SphereValidator;

public class DataParser {

	static Logger logger = LogManager.getLogger();
	private static final String REGULAR_DELIMETER_SPACE = "\\s";

	public List<Sphere> parseData(List<String> stringSpheres) throws ResourceException {
		if (stringSpheres == null) {
			logger.error("incalid argument {}", stringSpheres);
			throw new ResourceException("invalid argument: " + stringSpheres);
		}

		SphereValidator sphereValidator = new SphereValidator();
		List<Sphere> spheres = new ArrayList<>();
		for (String stringSphere : stringSpheres) {

			Sphere sphere;
			String[] centerBoundaryRadius = stringSphere.split(REGULAR_DELIMETER_SPACE);
			CustomPoint center;
			CustomPoint boundary;
			double radius;
			double x = Double.parseDouble(centerBoundaryRadius[0]);
			double y = Double.parseDouble(centerBoundaryRadius[1]);
			double z = Double.parseDouble(centerBoundaryRadius[2]);
			center = new CustomPoint(x, y, z);
			x = Double.parseDouble(centerBoundaryRadius[3]);
			y = Double.parseDouble(centerBoundaryRadius[4]);
			z = Double.parseDouble(centerBoundaryRadius[5]);
			boundary = new CustomPoint(x, y, z);
			radius = Double.parseDouble(centerBoundaryRadius[6]);
			sphere = (Sphere) ShapeFactory.getShapeFromFactory(center, boundary, radius);
			if (sphereValidator.isSphere(sphere)) {
				spheres.add(sphere);
			}
		}
		return spheres;
	}
}