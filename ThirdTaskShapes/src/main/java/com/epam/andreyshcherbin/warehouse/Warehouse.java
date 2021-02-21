package com.epam.andreyshcherbin.warehouse;

import java.util.Collections;
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

	public Map<Long, ShapeValues> getShapesValues() {
		return Collections.unmodifiableMap(shapesValues);
	}

	public void putShapeValues(long id, ShapeValues shapeValues) {
		shapesValues.put(id, shapeValues);
	}

	public ShapeValues getShapeValues(long id) {
		return shapesValues.get(id);
	}

	public boolean removeShapeValues(long id, ShapeValues shapeValues) {
		return shapesValues.remove(id, shapeValues);
	}
}
