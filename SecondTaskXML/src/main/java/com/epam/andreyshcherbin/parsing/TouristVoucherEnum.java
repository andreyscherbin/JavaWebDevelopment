package com.epam.andreyshcherbin.parsing;

public enum TouristVoucherEnum {
	
	TOURISTVOUCHERS("TouristVouchers"),
	TRANSPORT("transport"),
	TYPE("type"),
	TOURISTVOUCHER("TouristVoucher"),
	NUMBERVOUCHER("numberVoucher"),
	COUNTRY("country"),
	NUMBERDAYS("numberDays"),
	NUMBERNIGHTS("numberNights"),
	COST("cost"),
	STARS("stars"),
	ISFOOD("isFood"),
	ISTV("isTv"),
	ISCONDITIONER("isConditioner"),
	NUMBERPLACE("numberPlace"),
	DATE("date"),
	TYPEFOOD("typeFood"),	
	HOTELCHARACTERISTIC("hotelCharacteristic");
	
	private String value;
	
	private TouristVoucherEnum(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
