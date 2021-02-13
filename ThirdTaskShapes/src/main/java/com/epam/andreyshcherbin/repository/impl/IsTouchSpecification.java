package com.epam.andreyshcherbin.repository.impl;

import com.epam.andreyshcherbin.action.SphereAction;
import com.epam.andreyshcherbin.entity.AbstractShape;
import com.epam.andreyshcherbin.entity.Sphere;
import com.epam.andreyshcherbin.repository.Specification;

public class IsTouchSpecification implements Specification {

	@Override
	public boolean specify(AbstractShape shape) {
		boolean result = new SphereAction().isTouch((Sphere) shape);
		return result;
	}
}
