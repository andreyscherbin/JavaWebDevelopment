package com.epam.andreyshcherbin.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.andreyshcherbin.entity.AbstractShape;
import com.epam.andreyshcherbin.entity.CustomPoint;
import com.epam.andreyshcherbin.entity.Sphere;
import com.epam.andreyshcherbin.exception.ResourceException;

public class ShapeFactory {

	static Logger logger = LogManager.getLogger();

	public static AbstractShape getShapeFromFactory(CustomPoint center, CustomPoint boundary, double radius)
			throws ResourceException {
		if (center == null || boundary == null) {
			logger.error("incalid arguments {} {}", center, boundary);
			throw new ResourceException("invalid arguments: " + center + " " + boundary);
		}
		return new Sphere(center, boundary, radius);
	}
}
