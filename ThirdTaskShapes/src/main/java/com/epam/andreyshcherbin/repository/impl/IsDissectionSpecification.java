package com.epam.andreyshcherbin.repository.impl;

import com.epam.andreyshcherbin.entity.AbstractShape;
import com.epam.andreyshcherbin.entity.Sphere;
import com.epam.andreyshcherbin.repository.Specification;
import com.epam.andreyshcherbin.validation.SphereValidator;

public class IsDissectionSpecification implements Specification {

	@Override
	public boolean specify(AbstractShape shape) {
		SphereValidator sphereValidator = new SphereValidator();
		boolean result = sphereValidator.isDissection((Sphere) shape);
		return result;
	}
}
