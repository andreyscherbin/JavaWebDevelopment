package com.epam.andreyshcherbin.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextComposite implements TextComponent {

	private static final String TAB = "\t";
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	private ComponentType type;
	private List<TextComponent> components = new ArrayList<>();

	public TextComposite() {
	}

	public TextComposite(ComponentType type) {
		this.type = type;
	}

	public ComponentType getType() {
		return type;
	}

	public void setType(ComponentType type) {
		this.type = type;
	}

	public List<TextComponent> getComponents() {
		return Collections.unmodifiableList(components);
	}

	public void setComponents(List<TextComponent> components) {
		this.components = components;
	}

	public boolean addComponent(TextComponent componentText) {
		return components.add(componentText);
	}

	public boolean removeComponent(TextComponent componentText) {
		return components.remove(componentText);
	}

	@Override
	public int countSymbol() {
		int counter = 0;
		for (TextComponent component : this.components) {
			if (component.getType() == ComponentType.SYMBOL) {
				counter += component.countSymbol();
			}
		}
		return counter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((components != null) ? components.hashCode() : 0);
		result = prime * result + ((type != null) ? type.hashCode() : 0);
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
		TextComposite other = (TextComposite) obj;
		if (components == null) {
			if (other.components != null)
				return false;
		} else if (!components.equals(other.components))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder text = new StringBuilder();
		for (TextComponent component : components) {
			int index = components.indexOf(component);
			int size = components.size();
			if (component.getType() == ComponentType.PARAGRAPH) {
				text.append(TAB);
			}
			if (component.getType() == ComponentType.SENTENCE && index != 0) {
				text.append(SPACE);
			}
			text.append(component);
			if (component.getType() == ComponentType.PARAGRAPH && index != size - 1) {
				text.append(NEW_LINE);
			}
			if (component.getType() == ComponentType.LEXEME && index != size - 1) {
				text.append(SPACE);
			}
		}
		return text.toString();
	}
}