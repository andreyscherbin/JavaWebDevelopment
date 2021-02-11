package com.epam.andreyshcherbin.action;

import com.epam.andreyshcherbin.entity.Sphere;

public class SphereAction {

	private static final double PI = 3.14;

	public double areaSurfaceSphere(Sphere sphere) {
		int radius = sphere.getRadius();
		return 4 * PI * radius * radius;
	}

	public double volumeSphere(Sphere sphere) {
		int radius = sphere.getRadius();
		return (4.0 / 3) * PI * radius * radius * radius;
	}
}
