package com.epam.andreyshcherbin.builder;

import java.util.HashSet;
import java.util.Set;
import com.epam.andreyshcherbin.entity.TouristVoucher;
import com.epam.andreyshcherbin.exception.ParserException;

public abstract class AbstractTouristVouchersBuilder {

	protected Set<TouristVoucher> touristVouchers;

	public AbstractTouristVouchersBuilder() {
		touristVouchers = new HashSet<TouristVoucher>();
	}

	public AbstractTouristVouchersBuilder(Set<TouristVoucher> touristVouchers) {
		this.touristVouchers = touristVouchers;
	}

	public Set<TouristVoucher> getTouristVouchers() {
		return touristVouchers;
	}

	abstract public void buildSetTouristVouchers(String fileName) throws ParserException;
}
