package com.epam.andreyshcherbin.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class TouristVoucher implements Serializable {

	private static final long serialVersionUID = 1L;

	private String transport;	
	private String numberVoucher;
	private String country;
	private int numberDays;
	private int numberNights;
	private int cost;	
	private LocalDateTime date;

	TouristVoucher() {		
	}

	TouristVoucher(String transport, String numberVoucher, String country, int numberDays,
			int numberNights, int cost, LocalDateTime date) {
		super();
		this.transport = transport;		
		this.numberVoucher = numberVoucher;
		this.country = country;
		this.numberDays = numberDays;
		this.numberNights = numberNights;
		this.cost = cost;		
		this.date = date;
	}

	public String getNumberVoucher() {
		return numberVoucher;
	}

	public void setNumberVoucher(String numberVoucher) {
		this.numberVoucher = numberVoucher;
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
	
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cost;
		result = prime * result + ((country != null) ? country.hashCode() : 0);
		result = prime * result + ((date != null) ? date.hashCode() : 0);		
		result = prime * result + ((numberVoucher != null) ? numberVoucher.hashCode() : 0);
		result = prime * result + numberDays;
		result = prime * result + numberNights;
		result = prime * result + ((transport != null) ? transport.hashCode() : 0);		
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
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("TouristVoucher [\n transport=").append(transport)
				.append("\n numberVoucher=").append(numberVoucher).append("\n country=").append(country)
				.append("\n numberDays=").append(numberDays).append("\n numberNights=").append(numberNights)
				.append("\n cost=").append(cost).append("\n date=").append(date).append("]\n");		
		return sb.toString();
	}
}
