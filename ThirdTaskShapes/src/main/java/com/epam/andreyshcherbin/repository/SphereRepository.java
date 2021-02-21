package com.epam.andreyshcherbin.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.epam.andreyshcherbin.entity.Sphere;

public class SphereRepository {

	private List<Sphere> shapes = new ArrayList<>();

	public List<Sphere> getShapes() {
		return Collections.unmodifiableList(shapes);
	}

	public void addShape(Sphere shape) {
		shapes.add(shape);
	}

	public void removeShape(Sphere shape) {
		shapes.remove(shape);
	}

	public Sphere get(int index) {
		return shapes.get(index);
	}

	public Sphere set(int index, Sphere element) {
		return shapes.set(index, element);
	}

	public List<Sphere> query(Specification specification) {
		List<Sphere> list = shapes.stream().filter(specification::specify).collect(Collectors.toList());
		return list;
	}

	public void sortByParameter(Comparator<Sphere> comparator) {
        shapes.sort(comparator);
    }
}