package com.epam.andreyshcherbin.repository.impl;

import com.epam.andreyshcherbin.action.SphereAction;
import com.epam.andreyshcherbin.entity.AbstractShape;
import com.epam.andreyshcherbin.entity.Sphere;
import com.epam.andreyshcherbin.repository.Specification;

public class AreaSurfaceIntervalSpecification implements Specification {

	private double minAreaSurface;
	private double maxAreaSurface;

	public AreaSurfaceIntervalSpecification(double minAreaSurface, double maxAreaSurface) {
		this.minAreaSurface = minAreaSurface;
		this.maxAreaSurface = maxAreaSurface;
	}

	@Override
	public boolean specify(AbstractShape shape) {
		double areaSurface = new SphereAction().areaSurfaceSphere((Sphere) shape);
		boolean result = areaSurface >= minAreaSurface && areaSurface <= maxAreaSurface;
		return result;
	}
}
