package com.epam.andreyshcherbin.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Sphere implements Serializable {

	private static final long serialVersionUID = 2L;

	private Point center;
	private int radius;

	public Sphere() {

	}
	
	public Sphere(Point center, int radious) {
		super();
		this.center = center;
		this.radius = radious;
	}
	
	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radious) {
		this.radius = radious;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((center != null) ? center.hashCode() : 0);
		result = prime * result + radius;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sphere other = (Sphere) obj;
		if (center == null) {
			if (other.center != null)
				return false;
		} else if (!center.equals(other.center))
			return false;
		if (radius != other.radius)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sphere [center=");
		builder.append(center);
		builder.append(", radious=");
		builder.append(radius);
		builder.append("]");
		return builder.toString();
	}
}
