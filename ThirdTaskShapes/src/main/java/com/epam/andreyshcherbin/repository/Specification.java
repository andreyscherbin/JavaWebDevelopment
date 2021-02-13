package com.epam.andreyshcherbin.repository;

import com.epam.andreyshcherbin.entity.AbstractShape;

public interface Specification {
	boolean specify(AbstractShape shape);
}
