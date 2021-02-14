package com.epam.andreyshcherbin.entity;

import java.io.Serializable;

import com.epam.andreyshcherbin.action.SphereAction;

public class ShapeValues implements Serializable {
	
	private static final long serialVersionUID = 3L;
	
	double areaSurface;
	double volume;
	
	public ShapeValues(AbstractShape shape) {
		SphereAction sphereAction = new SphereAction();
		this.areaSurface = sphereAction.areaSurfaceSphere((Sphere) shape);
		this.volume = sphereAction.volumeSphere((Sphere) shape);
	}

	public double getAreaSurface() {
		return areaSurface;
	}

	public void setAreaSurface(double areaSurface) {
		this.areaSurface = areaSurface;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(areaSurface);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(volume);
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
		ShapeValues other = (ShapeValues) obj;
		if (Double.doubleToLongBits(areaSurface) != Double.doubleToLongBits(other.areaSurface))
			return false;
		if (Double.doubleToLongBits(volume) != Double.doubleToLongBits(other.volume))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShapeValues [areaSurface=");
		builder.append(areaSurface);
		builder.append(", volume=");
		builder.append(volume);
		builder.append("]");
		return builder.toString();
	}	
}
