package com.epam.andreyshcherbin.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExcursionVoucher extends TouristVoucher implements Serializable {

	private static final long serialVersionUID = 4L;

	private String city;
	private String showplace;
	private List<HotelCharacteristic> listHotelCharacteristic;
	
	{
		listHotelCharacteristic = new ArrayList<HotelCharacteristic>();
	}

	public ExcursionVoucher() {
	}

	public ExcursionVoucher(String transport, String numberVoucher, String country, int numberDays, int numberNights,
			int cost, LocalDateTime date, String city, String showplace,
			List<HotelCharacteristic> listHotelCharacteristic) {
		super(transport, numberVoucher, country, numberDays, numberNights, cost, date);
		this.city = city;
		this.showplace = showplace;
		this.listHotelCharacteristic = listHotelCharacteristic;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getShowplace() {
		return showplace;
	}

	public void setShowplace(String showplace) {
		this.showplace = showplace;
	}

	public List<HotelCharacteristic> getListHotelCharacteristic() {
		return listHotelCharacteristic;
	}

	public void setListHotelCharacteristic(List<HotelCharacteristic> listHotelCharacteristic) {
		this.listHotelCharacteristic = listHotelCharacteristic;
	}
	
	public void addHotelCharacteristic(HotelCharacteristic hotelCharacteristic) {
		this.listHotelCharacteristic.add(hotelCharacteristic);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((city != null) ? city.hashCode() : 0);
		result = prime * result + ((listHotelCharacteristic != null) ? listHotelCharacteristic.hashCode() : 0);
		result = prime * result + ((showplace != null) ? showplace.hashCode() : 0);
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
		ExcursionVoucher other = (ExcursionVoucher) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (listHotelCharacteristic == null) {
			if (other.listHotelCharacteristic != null)
				return false;
		} else if (!listHotelCharacteristic.equals(other.listHotelCharacteristic))
			return false;
		if (showplace == null) {
			if (other.showplace != null)
				return false;
		} else if (!showplace.equals(other.showplace))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExcursionVoucher [city=");
		builder.append(city);
		builder.append(", showplace=");
		builder.append(showplace);
		builder.append(", listHotelCharacteristic=");
		builder.append(listHotelCharacteristic);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}	
}
