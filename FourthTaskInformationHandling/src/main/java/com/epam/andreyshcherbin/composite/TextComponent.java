package com.epam.andreyshcherbin.composite;

import java.util.List;

public interface TextComponent {

	ComponentType getType();

	List<TextComponent> getComponents();

	int countSymbol();	
}