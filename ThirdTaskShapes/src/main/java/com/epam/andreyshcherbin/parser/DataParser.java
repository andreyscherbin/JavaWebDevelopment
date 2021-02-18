package com.epam.andreyshcherbin.parser;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.andreyshcherbin.entity.CustomPoint;
import com.epam.andreyshcherbin.exception.ShapeException;
import com.epam.andreyshcherbin.validation.DataValidator;

public class DataParser {

	static Logger logger = LogManager.getLogger();
	private static final String REGULAR_DELIMETER_SPACE = "\\s+";
	private static final int CENTER_X_POSITION_IN_FILE = 0;
	private static final int CENTER_Y_POSITION_IN_FILE = 1;
	private static final int CENTER_Z_POSITION_IN_FILE = 2;
	private static final int BOUNDARY_X_POSITION_IN_FILE = 3;
	private static final int BOUNDARY_Y_POSITION_IN_FILE = 4;
	private static final int BOUNDARY_Z_POSITION_IN_FILE = 5;
	private static final int RADIUS_POSITION_IN_FILE = 6;

	public List<Object[]> parseData(List<String> shapes) throws ShapeException {
		if (shapes == null) {
			logger.error("invalid argument {}", shapes);
			throw new ShapeException("invalid argument: " + shapes);
		}
		DataValidator dataValidator = new DataValidator();
		List<Object[]> shapesData = new ArrayList<>();
		int counterAllShapeStrings = shapes.size();
		int counterValidShapeStrings = 0;
		for (String shapeString : shapes) {
			if (dataValidator.isSphereDataValid(shapeString)) {
				counterValidShapeStrings++;
				String[] centerBoundaryRadius = shapeString.split(REGULAR_DELIMETER_SPACE);
				CustomPoint center;
				CustomPoint boundary;
				double radius;
				double x = Double.parseDouble(centerBoundaryRadius[CENTER_X_POSITION_IN_FILE]);
				double y = Double.parseDouble(centerBoundaryRadius[CENTER_Y_POSITION_IN_FILE]);
				double z = Double.parseDouble(centerBoundaryRadius[CENTER_Z_POSITION_IN_FILE]);
				center = new CustomPoint(x, y, z);
				x = Double.parseDouble(centerBoundaryRadius[BOUNDARY_X_POSITION_IN_FILE]);
				y = Double.parseDouble(centerBoundaryRadius[BOUNDARY_Y_POSITION_IN_FILE]);
				z = Double.parseDouble(centerBoundaryRadius[BOUNDARY_Z_POSITION_IN_FILE]);
				boundary = new CustomPoint(x, y, z);
				radius = Double.parseDouble(centerBoundaryRadius[RADIUS_POSITION_IN_FILE]);
				Object[] shapeData = new Object[3];
				shapeData[0] = center;
				shapeData[1] = boundary;
				shapeData[2] = radius;
				shapesData.add(shapeData);
			}
		}
		if (shapesData.isEmpty()) {
			logger.error("empty list {}", shapesData);
			throw new ShapeException("emty list: " + shapesData);
		}
		logger.info("Number valid shape strings =  {} Number all shape strings {}", counterValidShapeStrings,
				counterAllShapeStrings);
		return shapesData;
	}
}