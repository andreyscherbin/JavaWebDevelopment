package com.epam.andreyshcherbin.observer.impl;

import com.epam.andreyshcherbin.entity.ShapeValues;
import com.epam.andreyshcherbin.entity.Sphere;
import com.epam.andreyshcherbin.observer.ShapeObserver;
import com.epam.andreyshcherbin.observer.SphereEvent;
import com.epam.andreyshcherbin.warehouse.Warehouse;

public class SphereObserver implements ShapeObserver {

	@Override
	public void valueChanged(SphereEvent event) {
		Sphere sphere = event.getSource();
		long id = sphere.getId();
		ShapeValues shapeValues = new ShapeValues(sphere);
		Warehouse warehouse = Warehouse.getInstance();
		warehouse.addShapeValues(id, shapeValues);
	}
}
