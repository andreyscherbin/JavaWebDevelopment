package com.epam.andreyshcherbin.repository;

import java.util.HashMap;
import java.util.Map;
import com.epam.andreyshcherbin.entity.ShapeValues;

public final class Warehouse {

	private static final Warehouse INSTANCE = new Warehouse();
	private Map<Long, ShapeValues> shapesValues = new HashMap<>();

	private Warehouse() {
	}

	public static Warehouse getInstance() {
		return INSTANCE;
	}

	public void addShapeValues(long id, ShapeValues shapeValues) {
		shapesValues.put(id, shapeValues);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Warehouse [shapesValues=");
		builder.append(shapesValues);
		builder.append("]");
		return builder.toString();
	}
}
