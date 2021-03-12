package com.epam.multithreading.entity;

import java.util.concurrent.atomic.AtomicBoolean;

public class ParkingSpace {	
	
	private int parkingSpaceId;
	private AtomicBoolean busy;
	
	public ParkingSpace() {}
	
	public ParkingSpace(int id) {
		this.parkingSpaceId = id;
		this.busy = new AtomicBoolean(false);
	}
	
	public int getParkingSpaceId() {
		return parkingSpaceId;
	}

	public void setParkingSpaceId(int parkingSpaceId) {
		this.parkingSpaceId = parkingSpaceId;
	}
	
	public AtomicBoolean isBusy() {
		return busy;
	}

	public void setBusy(AtomicBoolean busy) {
		this.busy = busy;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((busy == null) ? 0 : busy.hashCode());
		result = prime * result + parkingSpaceId;
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
		ParkingSpace other = (ParkingSpace) obj;
		if (busy == null) {
			if (other.busy != null)
				return false;
		} else if (!busy.equals(other.busy))
			return false;
		if (parkingSpaceId != other.parkingSpaceId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ParkingSpace [parkingSpaceId=");
		builder.append(parkingSpaceId);
		builder.append("]");
		return builder.toString();
	}	
}
