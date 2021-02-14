package com.epam.andreyshcherbin.observer;

import java.util.EventObject;

import com.epam.andreyshcherbin.entity.Sphere;

public class SphereEvent extends EventObject {

	public SphereEvent(Sphere source) {
		super(source);
	}

	@Override
	public Sphere getSource() {
		return (Sphere) super.getSource();
	}
}
