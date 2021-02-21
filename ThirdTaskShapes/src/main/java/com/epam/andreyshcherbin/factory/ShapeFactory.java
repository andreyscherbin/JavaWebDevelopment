package com.epam.andreyshcherbin.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.andreyshcherbin.entity.AbstractShape;
import com.epam.andreyshcherbin.entity.CustomPoint;
import com.epam.andreyshcherbin.entity.Sphere;
import com.epam.andreyshcherbin.exception.ShapeException;

public class ShapeFactory {

	private static Logger logger = LogManager.getLogger();

	public static AbstractShape getShapeFromFactory(CustomPoint center, double radius)
			throws ShapeException {
		if (center == null || radius == 0) {
			logger.error("invalid arguments {} {} {}", center, radius);
			throw new ShapeException("invalid arguments: " + center + " " + radius);
		}
		return new Sphere(center, radius);
	}
}
