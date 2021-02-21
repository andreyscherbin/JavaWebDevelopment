package com.epam.andreyshcherbin.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.epam.andreyshcherbin.observer.Observable;
import com.epam.andreyshcherbin.observer.ShapeObserver;
import com.epam.andreyshcherbin.observer.SphereEvent;

public class Sphere extends AbstractShape implements Serializable, Observable {

	private static final long serialVersionUID = 2L;

	private CustomPoint center;
	private double radius;
	private transient List<ShapeObserver> observers = new ArrayList<>();

	public Sphere() {
	}

	public Sphere(CustomPoint center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	public CustomPoint getCenter() {
		return center;
	}

	public void setCenter(CustomPoint center) {
		this.center = center;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
		notifyObservers();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((center != null) ? center.hashCode() : 0);
		long temp;
		temp = Double.doubleToLongBits(radius);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sphere other = (Sphere) obj;
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
		builder.append(super.toString());
		builder.append("Sphere [center=");
		builder.append(center);
		builder.append(", radius=");
		builder.append(radius);
		builder.append("]\n");
		return builder.toString();
	}

	@Override
	public void attach(ShapeObserver observer) {
		if (observer != null) {
			observers.add(observer);
		}
	}

	@Override
	public void detach(ShapeObserver observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		SphereEvent event = new SphereEvent(this);
		observers.forEach(e -> e.valueChanged(event));
	}
}
