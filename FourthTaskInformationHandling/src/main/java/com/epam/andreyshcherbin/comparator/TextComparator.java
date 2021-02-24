package com.epam.andreyshcherbin.comparator;

import java.util.Comparator;
import com.epam.andreyshcherbin.composite.TextComponent;

public enum TextComparator implements Comparator<TextComponent> {
	NUMBER_ELEMENTS {
		@Override
		public int compare(TextComponent tc1, TextComponent tc2) {			
			return Integer.compare(tc1.getComponents().size(), tc2.getComponents().size());
		}
	},
}
