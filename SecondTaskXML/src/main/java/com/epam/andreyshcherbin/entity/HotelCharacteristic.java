package com.epam.andreyshcherbin.entity;

import java.io.Serializable;

public class HotelCharacteristic implements Serializable {

	private static final long serialVersionUID = 2L;

	private String typeFood;
	private int stars;
	private boolean isFood;
	private boolean isTV;
	private boolean isConditioner;
	private int numberPlace;

	public HotelCharacteristic() {
	}

	public HotelCharacteristic(String typeFood, int stars, boolean isFood, boolean isTV, boolean isConditioner,
			int numberPlace) {
		super();
		this.typeFood = typeFood;
		this.stars = stars;
		this.isFood = isFood;
		this.isTV = isTV;
		this.isConditioner = isConditioner;
		this.numberPlace = numberPlace;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public boolean isFood() {
		return isFood;
	}

	public void setFood(boolean isFood) {
		this.isFood = isFood;
	}

	public String getTypeFood() {
		return typeFood;
	}

	public void setTypeFood(String typeFood) {
		this.typeFood = typeFood;
	}

	public int getNumberPlace() {
		return numberPlace;
	}

	public void setNumberPlace(int numberPlace) {
		this.numberPlace = numberPlace;
	}

	public boolean isTV() {
		return isTV;
	}

	public void setTV(boolean isTV) {
		this.isTV = isTV;
	}

	public boolean isConditioner() {
		return isConditioner;
	}

	public void setConditioner(boolean isConditioner) {
		this.isConditioner = isConditioner;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isConditioner ? 1231 : 1237);
		result = prime * result + (isFood ? 1231 : 1237);
		result = prime * result + (isTV ? 1231 : 1237);
		result = prime * result + numberPlace;
		result = prime * result + stars;
		result = prime * result + ((typeFood != null) ? typeFood.hashCode() : 0);
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
		HotelCharacteristic other = (HotelCharacteristic) obj;
		if (isConditioner != other.isConditioner)
			return false;
		if (isFood != other.isFood)
			return false;
		if (isTV != other.isTV)
			return false;
		if (numberPlace != other.numberPlace)
			return false;
		if (stars != other.stars)
			return false;
		if (typeFood == null) {
			if (other.typeFood != null)
				return false;
		} else if (!typeFood.equals(other.typeFood))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("HotelCharacteristic [\n typeFood=").append(typeFood).append("\n stars=")
				.append(stars).append("\n isFood").append(isFood).append("\n isTV=").append(isTV)
				.append("\n isConditioner=").append(isConditioner).append("\n numberPlace=").append(numberPlace)
				.append("]\n");
		return sb.toString();
	}
}
