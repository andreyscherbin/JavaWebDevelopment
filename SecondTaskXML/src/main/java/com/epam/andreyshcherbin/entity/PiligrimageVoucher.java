package com.epam.andreyshcherbin.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PiligrimageVoucher extends TouristVoucher implements Serializable {

	private static final long serialVersionUID = 3L;
	private String city;

	public PiligrimageVoucher() {
	}

	public PiligrimageVoucher(String transport, String numberVoucher, String country, int numberDays, int numberNights,
			int cost, LocalDateTime date, String city) {
		super(transport, numberVoucher, country, numberDays, numberNights, cost, date);
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((city != null) ? city.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PiligrimageVoucher other = (PiligrimageVoucher) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PiligrimageVoucher [city=");
		builder.append(city);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
}
