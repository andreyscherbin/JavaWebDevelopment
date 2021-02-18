package com.epam.andreyshcherbin.comparator;

import java.util.Comparator;
import com.epam.andreyshcherbin.entity.AbstractShape;
import com.epam.andreyshcherbin.entity.Sphere;

public enum ShapeComparator implements Comparator<AbstractShape> {
	ID {
		@Override
		public int compare(AbstractShape s1, AbstractShape s2) {
			return Long.compare(s1.getId(), s2.getId());
		}
	},
	RADIUS {
		@Override
		public int compare(AbstractShape s1, AbstractShape s2) {
			return Double.compare(((Sphere) s1).getRadius(), ((Sphere) s2).getRadius());
		}
	},
	CENTER_X {
		@Override
		public int compare(AbstractShape s1, AbstractShape s2) {
			return Double.compare(((Sphere) s1).getCenter().getX(), ((Sphere) s2).getCenter().getX());
		}
	},
	CENTER_Y {
		@Override
		public int compare(AbstractShape s1, AbstractShape s2) {
			return Double.compare(((Sphere) s1).getCenter().getY(), ((Sphere) s2).getCenter().getY());
		}
	},
	CENTER_Z {
		@Override
		public int compare(AbstractShape s1, AbstractShape s2) {
			return Double.compare(((Sphere) s1).getCenter().getZ(), ((Sphere) s2).getCenter().getZ());
		}
	}
}
