package com.epam.andreyshcherbin.repository.impl;

import com.epam.andreyshcherbin.action.SphereAction;
import com.epam.andreyshcherbin.entity.AbstractShape;
import com.epam.andreyshcherbin.entity.Sphere;
import com.epam.andreyshcherbin.repository.Specification;

public class VolumeIntervalSpecification implements Specification {
	private double minVolume;
	private double maxVolume;

	public VolumeIntervalSpecification(double minVolume, double maxVolume) {
		this.minVolume = minVolume;
		this.maxVolume = maxVolume;
	}

	@Override
	public boolean specify(AbstractShape shape) {
		SphereAction sphereAction = new SphereAction();
		double volume = sphereAction.volumeSphere((Sphere) shape);
		boolean result = volume >= minVolume && volume <= maxVolume;
		return result;
	}
}
