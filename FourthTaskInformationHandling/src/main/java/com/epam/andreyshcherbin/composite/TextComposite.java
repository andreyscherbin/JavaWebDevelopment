package com.epam.andreyshcherbin.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextComposite implements TextComponent {
	private TypeComponent type;
	private List<TextComponent> components = new ArrayList<>();

	public TextComposite() {
	}

	public TextComposite(TypeComponent type) {
		this.type = type;
	}

	public TypeComponent getType() {
		return type;
	}

	public void setType(TypeComponent type) {
		this.type = type;
	}

	public List<TextComponent> getComponents() {
		return Collections.unmodifiableList(components);
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
			if (component.getType() == TypeComponent.SYMBOL) {
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

			if (component.getType() == TypeComponent.PARAGRAPH) {
				text.append("\t");
			}
			text.append(component);
			if (component.getType() != TypeComponent.SYMBOL) {
				text.append("\s");
			}
			if (component.getType() == TypeComponent.PARAGRAPH) {
				text.append("\n");
			}
		}
		return text.toString();
	}
}