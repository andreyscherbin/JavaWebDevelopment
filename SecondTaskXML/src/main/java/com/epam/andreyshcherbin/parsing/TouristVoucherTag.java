package com.epam.andreyshcherbin.parsing;

public enum TouristVoucherTag {
	
	TOURIST_VOUCHERS("tourist_vouchers"),		
	TOURIST_VOUCHER("tourist_voucher"),
	EXCURSION_VOUCHER("excursion_voucher"),
	PILIGRIMAGE_VOUCHER("piligrimage_voucher"),
	REST_VOUCHER("rest_voucher"),
	TRANSPORT("transport"),
	NUMBER_VOUCHER("number_voucher"),
	COUNTRY("country"),
	NUMBER_DAYS("number_days"),
	NUMBER_NIGHTS("number_nights"),
	COST("cost"),	
	DATE("date"),
	CITY("city"),
	SHOWPLACE("showplace"),
	STARS("stars"),
	IS_FOOD("is_food"),
	IS_TV("is_tv"),
	IS_CONDITIONER("is_conditioner"),
	NUMBER_PLACE("number_place"),
	TYPE_FOOD("type_food"),	
	HOTEL_CHARACTERISTIC("hotel_characteristic");
	
	private String value;
	
	private TouristVoucherTag(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
