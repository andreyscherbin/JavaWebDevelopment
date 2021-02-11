package com.epam.andreyshcherbin.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Sphere implements Serializable {

	private static final long serialVersionUID = 2L;

	//need add id;
	private Point center;
	private Point boundary;
	private double radius;

	public Sphere() {

	}
	
	public Sphere(Point center, Point boundary, double radius) {
		super();
		this.center = center;
		this.boundary = boundary;
		this.radius = radius;
	}

	public Point getBoundary() {
		return boundary;
	}

	public void setBoundary(Point boundary) {
		this.boundary = boundary;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(int radious) {
		this.radius = radious;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boundary == null) ? 0 : boundary.hashCode());
		result = prime * result + ((center == null) ? 0 : center.hashCode());
		long temp;
		temp = Double.doubleToLongBits(radius);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (boundary == null) {
			if (other.boundary != null)
				return false;
		} else if (!boundary.equals(other.boundary))
			return false;
		if (center == null) {
			if (other.center != null)
				return false;
		} else if (!center.equals(other.center))
			return false;
		if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sphere [center=");
		builder.append(center);
		builder.append(", boundary=");
		builder.append(boundary);
		builder.append(", radius=");
		builder.append(radius);
		builder.append("]");
		return builder.toString();
	}
}
