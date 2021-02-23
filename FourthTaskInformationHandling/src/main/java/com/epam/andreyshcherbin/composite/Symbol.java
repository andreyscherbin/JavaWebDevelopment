package com.epam.andreyshcherbin.composite;

import java.util.List;

public class Symbol implements TextComponent {

	private TypeComponent type;
	private Character value;

	public Symbol() {
	}

	public Symbol(TypeComponent type, Character value) {
		this.type = type;
		this.value = value;
	}

	public TypeComponent getType() {
		return type;
	}

	public void setType(TypeComponent type) {
		this.type = type;
	}

	public Character getValue() {
		return value;
	}

	public void setValue(Character value) {
		this.value = value;
	}

	@Override
	public int countSymbol() {
		return 1;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Symbol other = (Symbol) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Symbol [type=");
		builder.append(type);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public List<TextComponent> getComponents() {
		throw new UnsupportedOperationException("undone");
	}
}