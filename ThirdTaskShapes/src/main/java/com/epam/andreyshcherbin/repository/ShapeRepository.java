package com.epam.andreyshcherbin.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import com.epam.andreyshcherbin.entity.AbstractShape;

public class ShapeRepository {

	private List<AbstractShape> shapes = new ArrayList<>();

	public List<AbstractShape> getShapes() {
		return Collections.unmodifiableList(shapes);
	}

	public void addShape(AbstractShape shape) {
		shapes.add(shape);
	}
	
	public void removeShape(AbstractShape shape) {
		shapes.remove(shape);
	}
	
	public List<AbstractShape> query(Specification specification) {
		List<AbstractShape> list = shapes.stream().filter(e -> specification.specify(e)).collect(Collectors.toList());
		return list;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShapeRepository [shapes=");
		builder.append(shapes);
		builder.append("]");
		return builder.toString();
	}		
}