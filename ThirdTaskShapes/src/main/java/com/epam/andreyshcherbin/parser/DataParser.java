package com.epam.andreyshcherbin.parser;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.andreyshcherbin.entity.CustomPoint;
import com.epam.andreyshcherbin.exception.ShapeException;
import com.epam.andreyshcherbin.validation.DataValidator;

public class DataParser {

	private static Logger logger = LogManager.getLogger();
	private static final String REGULAR_DELIMETER_SPACE = "\\s+";
	private static final int CENTER_X_POSITION_IN_FILE = 0;
	private static final int CENTER_Y_POSITION_IN_FILE = 1;
	private static final int CENTER_Z_POSITION_IN_FILE = 2;	
	private static final int RADIUS_POSITION_IN_FILE = 3;

	public List<Object[]> parseData(List<String> shapes) throws ShapeException {
		if (shapes == null) {
			logger.error("invalid argument {}", shapes);
			throw new ShapeException("invalid argument: " + shapes);
		}		
		List<Object[]> shapesData = new ArrayList<>();
		int counterAllShapeStrings = shapes.size();
		int counterValidShapeStrings = 0;
		for (String shapeString : shapes) {
			if (DataValidator.isSphereDataValid(shapeString)) {
				counterValidShapeStrings++;
				String[] centerRadius = shapeString.split(REGULAR_DELIMETER_SPACE);
				CustomPoint center;				
				double radius;
				double x = Double.parseDouble(centerRadius[CENTER_X_POSITION_IN_FILE]);
				double y = Double.parseDouble(centerRadius[CENTER_Y_POSITION_IN_FILE]);
				double z = Double.parseDouble(centerRadius[CENTER_Z_POSITION_IN_FILE]);
				center = new CustomPoint(x, y, z);				
				radius = Double.parseDouble(centerRadius[RADIUS_POSITION_IN_FILE]);
				Object[] shapeData = new Object[2];
				shapeData[0] = center;				
				shapeData[1] = radius;
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