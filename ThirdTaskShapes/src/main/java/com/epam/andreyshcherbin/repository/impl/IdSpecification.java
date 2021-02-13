package com.epam.andreyshcherbin.repository.impl;

import com.epam.andreyshcherbin.entity.AbstractShape;
import com.epam.andreyshcherbin.repository.Specification;

public class IdSpecification implements Specification {

	private long id;

	public IdSpecification(long id) {
		this.id = id;
	}

	@Override
	public boolean specify(AbstractShape shape) {
		boolean result = shape.getId() == id;
		return result;
	}
}
