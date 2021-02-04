package com.epam.andreyshcherbin.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestVoucher extends TouristVoucher implements Serializable {

	private static final long serialVersionUID = 5L;

	private List<HotelCharacteristic> listHotelCharacteristic;
	
	{
		listHotelCharacteristic = new ArrayList<HotelCharacteristic>();
	}

	public RestVoucher() {
	}

	public RestVoucher(String transport, String numberVoucher, String country, int numberDays, int numberNights,
			int cost, LocalDateTime date, List<HotelCharacteristic> listHotelCharacteristic) {
		super(transport, numberVoucher, country, numberDays, numberNights, cost, date);
		this.listHotelCharacteristic = listHotelCharacteristic;
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
		result = prime * result + ((listHotelCharacteristic != null) ? listHotelCharacteristic.hashCode() : 0);
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
		RestVoucher other = (RestVoucher) obj;
		if (listHotelCharacteristic == null) {
			if (other.listHotelCharacteristic != null)
				return false;
		} else if (!listHotelCharacteristic.equals(other.listHotelCharacteristic))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RestVoucher [listHotelCharacteristic=");
		builder.append(listHotelCharacteristic);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
}
