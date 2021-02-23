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
			counter += component.countSymbol();
		}
		return counter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((components == null) ? 0 : components.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		StringBuilder builder = new StringBuilder();
		builder.append("CompositeText [type=");
		builder.append(type);
		builder.append(", components=");
		builder.append(components);
		builder.append("]");
		return builder.toString();
	}
}