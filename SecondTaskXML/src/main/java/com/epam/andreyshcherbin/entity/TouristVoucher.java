package com.epam.andreyshcherbin.entity;

import java.io.Serializable;
import java.util.Date;

public class TouristVoucher implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String transport;
	private String type;
	private String numberVoucher;
	private String country;
	private int numberDays;
	private int numberNights;	
	private int cost;
	private HotelCharacteristic hotelCharacteristic;
	private Date date;

	public TouristVoucher() {
		hotelCharacteristic = new HotelCharacteristic();
	}		

	public TouristVoucher(String transport, String type, String numberVoucher, String country, int numberDays,
			int numberNights, int cost, HotelCharacteristic hotelCharacteristic, Date date) {
		super();
		this.transport = transport;
		this.type = type;
		this.numberVoucher = numberVoucher;
		this.country = country;
		this.numberDays = numberDays;
		this.numberNights = numberNights;
		this.cost = cost;
		this.hotelCharacteristic = hotelCharacteristic;
		this.date = date;
	}

	public String getNumberVoucher() {
		return numberVoucher;
	}

	public void setNumberVoucher(String numberVoucher) {
		this.numberVoucher = numberVoucher;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getNumberDays() {
		return numberDays;
	}

	public void setNumberDays(int numberDays) {
		this.numberDays = numberDays;
	}

	public int getNumberNights() {
		return numberNights;
	}

	public void setNumberNights(int numberNights) {
		this.numberNights = numberNights;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public HotelCharacteristic getHotelCharacteristic() {
		return hotelCharacteristic;
	}

	public void setHotelCharacteristic(HotelCharacteristic hotelCharacteristic) {
		this.hotelCharacteristic = hotelCharacteristic;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cost;
		result = prime * result + ((country != null) ? country.hashCode() : 0);
		result = prime * result + ((date != null) ? date.hashCode() : 0);
		result = prime * result + ((hotelCharacteristic != null) ? hotelCharacteristic.hashCode() : 0);
		result = prime * result + ((numberVoucher != null) ? numberVoucher.hashCode() : 0);
		result = prime * result + numberDays;
		result = prime * result + numberNights;
		result = prime * result + ((transport != null) ? transport.hashCode() : 0);
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
		TouristVoucher other = (TouristVoucher) obj;
		if (cost != other.cost)
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (hotelCharacteristic == null) {
			if (other.hotelCharacteristic != null)
				return false;
		} else if (!hotelCharacteristic.equals(other.hotelCharacteristic))
			return false;
		if (numberVoucher == null) {
			if (other.numberVoucher != null)
				return false;
		} else if (!numberVoucher.equals(other.numberVoucher))
			return false;
		if (numberDays != other.numberDays)
			return false;
		if (numberNights != other.numberNights)
			return false;
		if (transport == null) {
			if (other.transport != null)
				return false;
		} else if (!transport.equals(other.transport))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TouristVoucher [\ntransport=" + transport + "\ntype=" + type + "\nnumberVoucher=" + numberVoucher +  "\ncountry=" + country + "\nnumberDays=" + numberDays
				+ "\nnumberNights=" + numberNights + "\ncost=" + cost
				+ "\nhotelCharacteristic=" + hotelCharacteristic + "\ndate=" + date + "]\n";
	}
}
