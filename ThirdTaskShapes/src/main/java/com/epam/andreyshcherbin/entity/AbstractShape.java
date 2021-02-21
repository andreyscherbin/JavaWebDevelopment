package com.epam.andreyshcherbin.entity;

import java.io.Serializable;
import com.epam.andreyshcherbin.generator.IdGenerator;

public abstract class AbstractShape implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	protected AbstractShape() {
		id = IdGenerator.getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		AbstractShape other = (AbstractShape) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AbstractShape [id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}
}
