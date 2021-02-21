package com.epam.andreyshcherbin.validation;

import com.epam.andreyshcherbin.entity.CustomPoint;
import com.epam.andreyshcherbin.entity.Sphere;

public class SphereValidator {

	public boolean isSphere(Sphere sphere) {		
		double radius = sphere.getRadius();		
		return radius > 0;
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
}
