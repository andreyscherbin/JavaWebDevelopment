package com.epam.andreyshcherbin.comparator;

import java.util.Comparator;
import com.epam.andreyshcherbin.composite.TextComponent;

public enum TextComparator implements Comparator<TextComponent> {
	NUMBER_ELEMENTS {
		@Override
		public int compare(TextComponent component1, TextComponent component2) {
			return Integer.compare(component1.getComponents().size(), component2.getComponents().size());
		}
	},
}
