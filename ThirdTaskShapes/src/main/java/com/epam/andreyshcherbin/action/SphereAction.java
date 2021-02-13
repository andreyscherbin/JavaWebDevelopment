package com.epam.andreyshcherbin.action;

import com.epam.andreyshcherbin.entity.CustomPoint;
import com.epam.andreyshcherbin.entity.Sphere;
import com.epam.andreyshcherbin.exception.ShapeException;

public class SphereAction {

	private static final double PI = 3.14;

	public double areaSurfaceSphere(Sphere sphere) {
		double radius = sphere.getRadius();
		return 4 * PI * radius * radius;
	}

	public double volumeSphere(Sphere sphere) {
		double radius = sphere.getRadius();
		return (4.0 / 3) * PI * radius * radius * radius;
	}

	public boolean isTouch(Sphere sphere) {
		boolean result = false;
		CustomPoint center = sphere.getCenter();
		double radius = sphere.getRadius();
		double x = center.getX();
		double y = center.getY();
		double z = center.getZ();
		if (x + radius == 0 || x - radius == 0 || 
		    y + radius == 0 || y - radius == 0 ||
		    z + radius == 0	|| z - radius == 0) {
			result = true;
		}
		return result;
	}

	public boolean isDissection(Sphere sphere) {
		boolean result = false;
		CustomPoint center = sphere.getCenter();
		double radius = sphere.getRadius();
		double x = center.getX();
		double y = center.getY();
		double z = center.getZ();
		if (x >= 0 && x - radius < 0 || y >= 0 && y - radius < 0 || z >= 0 && z - radius < 0 ) {			
			result = true;			
		}
		if (x <= 0 && x + radius > 0 || y <= 0 && y + radius > 0 || z <= 0 && z + radius > 0 ) {			
			result = true;			
		}
		return result;
	}

	public double dissectionByXY(Sphere sphere) throws ShapeException {
		CustomPoint center = sphere.getCenter();
		double radius = sphere.getRadius();
		double z = center.getZ();
		if(z >= 0 && z - radius >= 0 || z <= 0 && z + radius <= 0) {
			throw  new ShapeException("sphere : " + sphere + "does not dissection by XY"); 
		}
		double heightSphereSegment = radius - Math.abs(z);
		double heightRestSphereSegment = radius + Math.abs(z);
		double volumeSphereSegment = PI * Math.pow(heightSphereSegment, 2) * (3 * radius - heightSphereSegment) / 3;
		double volumeRestSphereSegment = PI * Math.pow(heightRestSphereSegment, 2)
				* (3 * radius - heightRestSphereSegment) / 3;
		return volumeSphereSegment / volumeRestSphereSegment;
		} 	
}
