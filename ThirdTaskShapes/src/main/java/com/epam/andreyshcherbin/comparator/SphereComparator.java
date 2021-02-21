package com.epam.andreyshcherbin.comparator;

import java.util.Comparator;
import com.epam.andreyshcherbin.entity.Sphere;

public enum SphereComparator implements Comparator<Sphere> {
	ID {
		@Override
		public int compare(Sphere s1, Sphere s2) {
			return Long.compare(s1.getId(), s2.getId());
		}
	},
	RADIUS {
		@Override
		public int compare(Sphere s1, Sphere s2) {
			return Double.compare(s1.getRadius(), s2.getRadius());
		}
	},
	CENTER_X {
		@Override
		public int compare(Sphere s1, Sphere s2) {
			return Double.compare(s1.getCenter().getX(), s2.getCenter().getX());
		}
	},
	CENTER_Y {
		@Override
		public int compare(Sphere s1, Sphere s2) {
			return Double.compare(s1.getCenter().getY(), s2.getCenter().getY());
		}
	},
	CENTER_Z {
		@Override
		public int compare(Sphere s1, Sphere s2) {
			return Double.compare(s1.getCenter().getZ(), s2.getCenter().getZ());
		}
	}
}
