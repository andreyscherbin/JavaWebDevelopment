package com.epam.andreyshcherbin.action;

import com.epam.andreyshcherbin.entity.CustomPoint;
import com.epam.andreyshcherbin.entity.Sphere;
import com.epam.andreyshcherbin.exception.ShapeException;

public class SphereAction {	

	public double areaSurfaceSphere(Sphere sphere) {
		double radius = sphere.getRadius();
		return 4 * Math.PI * Math.pow(radius, 2);
	}

	public double volumeSphere(Sphere sphere) {
		double radius = sphere.getRadius();
		return (4.0 / 3) * Math.PI * Math.pow(radius, 3);
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
		double volumeSphereSegment = Math.PI * Math.pow(heightSphereSegment, 2) * (3 * radius - heightSphereSegment) / 3;
		double volumeRestSphereSegment = Math.PI * Math.pow(heightRestSphereSegment, 2)
				* (3 * radius - heightRestSphereSegment) / 3;
		return volumeSphereSegment / volumeRestSphereSegment;
		} 	
}
