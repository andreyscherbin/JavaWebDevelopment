package com.epam.andreyshcherbin.validation;

import com.epam.andreyshcherbin.entity.CustomPoint;
import com.epam.andreyshcherbin.entity.Sphere;

public class SphereValidator {

	public boolean isSphere(Sphere sphere) {
		CustomPoint center = sphere.getCenter();
		CustomPoint boundary = sphere.getBoundary();
		double radius = sphere.getRadius();
		double x0 = center.getX();
		double y0 = center.getY();
		double z0 = center.getZ();
		double x = boundary.getX();
		double y = boundary.getY();
		double z = boundary.getZ();
		return Math.pow(x - x0, 2) + Math.pow(y - y0, 2) + Math.pow(z - z0, 2) == Math.pow(radius, 2);
	}
}
