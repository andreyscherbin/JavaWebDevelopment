package com.epam.andreyshcherbin.repository.impl;

import com.epam.andreyshcherbin.entity.AbstractShape;
import com.epam.andreyshcherbin.entity.Sphere;
import com.epam.andreyshcherbin.repository.Specification;

public class RadiusIntervalSpecification implements Specification {
	
	private double minRadius;
	private double maxRadius;

	public RadiusIntervalSpecification(double minRadius, double maxRadius) {
		this.minRadius = minRadius;
		this.maxRadius = maxRadius;
	}

	@Override
	public boolean specify(AbstractShape shape) {
		Sphere sphere = (Sphere) shape;
		double radius = sphere.getRadius();
		boolean result = radius >= minRadius && radius <= maxRadius;
		return result;
	}
}
