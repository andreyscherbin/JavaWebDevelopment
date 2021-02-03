package com.epam.andreyshcherbin.factory;

import com.epam.andreyshcherbin.builder.AbstractTouristVouchersBuilder;
import com.epam.andreyshcherbin.builder.TouristVouchersDOMBuilder;
import com.epam.andreyshcherbin.builder.TouristVouchersSAXBuilder;
import com.epam.andreyshcherbin.builder.TouristVouchersStAXBuilder;
import com.epam.andreyshcherbin.exception.ParserException;

public class TouristVoucherBuilderFactory {	

		private enum TypeParser {
			SAX, STAX, DOM
		}
		
		public AbstractTouristVouchersBuilder createTouristVoucherBuilder(String typeParser) throws ParserException {
			TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
			switch (type) {
			case DOM:
				return new TouristVouchersDOMBuilder();
			case STAX:
				return new TouristVouchersStAXBuilder();
			case SAX:
				return new TouristVouchersSAXBuilder();
			default:
				throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
			}
		}	
}
