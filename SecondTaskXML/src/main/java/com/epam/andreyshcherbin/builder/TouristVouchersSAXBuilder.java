package com.epam.andreyshcherbin.builder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import com.epam.andreyshcherbin.entity.ExcursionVoucher;
import com.epam.andreyshcherbin.entity.HotelCharacteristic;
import com.epam.andreyshcherbin.entity.PiligrimageVoucher;
import com.epam.andreyshcherbin.entity.RestVoucher;
import com.epam.andreyshcherbin.entity.TouristVoucher;
import com.epam.andreyshcherbin.exception.ParserException;
import com.epam.andreyshcherbin.parsing.TouristVoucherTag;

public class TouristVouchersSAXBuilder extends AbstractTouristVouchersBuilder {

	private static Logger logger = LogManager.getLogger();
	private TouristVoucherHandler touristVoucherHandler;
	private XMLReader reader;
	private static final String DEFAULT_FOOD = "HB";

	public TouristVouchersSAXBuilder() throws ParserException {
		touristVoucherHandler = new TouristVoucherHandler();
		try {
			reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(touristVoucherHandler);
		} catch (SAXException e) {
			logger.error("ошибка SAX парсера: {}", e);
			throw new ParserException("ошибка SAX парсера: ", e);
		}
	}

	public TouristVouchersSAXBuilder(Set<TouristVoucher> touristVouchers) {
		super(touristVouchers);
	}

	@Override
	public void buildSetTouristVouchers(String fileName) throws ParserException {
		try {
			reader.parse(fileName);
		} catch (SAXException e) {
			logger.error("ошибка SAX парсера: {}", e);
			throw new ParserException("ошибка SAX парсера: ", e);
		} catch (IOException e) {
			logger.error("ошибка I/O потока: ", e);
			throw new ParserException("ошибка I/O потока ", e);
		}
		touristVouchers = touristVoucherHandler.getTouristVouchers();
	}

	private static class TouristVoucherHandler extends DefaultHandler {

		private Set<TouristVoucher> touristVouchers;
		private TouristVoucher current;
		private TouristVoucherTag currentTag;
		private EnumSet<TouristVoucherTag> tagWithText;
		private HotelCharacteristic hotelCharacteristic;

		public TouristVoucherHandler() {
			touristVouchers = new HashSet<>();
			tagWithText = EnumSet.range(TouristVoucherTag.NUMBER_VOUCHER, TouristVoucherTag.NUMBER_PLACE);
		}

		public Set<TouristVoucher> getTouristVouchers() {
			return Collections.unmodifiableSet(touristVouchers);
		}

		public void startElement(String uri, String localName, String qName, Attributes attrs) {
			TouristVoucherTag tag = TouristVoucherTag.valueOf(localName.toUpperCase());
			String transport;
			switch (tag) {
			case REST_VOUCHER:
				current = new RestVoucher();
				transport = attrs.getValue(0);
				current.setTransport(transport);
				break;
			case EXCURSION_VOUCHER:
				current = new ExcursionVoucher();
				transport = attrs.getValue(0);
				current.setTransport(transport);
				break;
			case PILIGRIMAGE_VOUCHER:
				current = new PiligrimageVoucher();
				transport = attrs.getValue(0);
				current.setTransport(transport);
				break;
			case HOTEL_CHARACTERISTIC:
				hotelCharacteristic = new HotelCharacteristic();
				if (attrs.getLength() > 0) {
					String typeFood = attrs.getValue(0);
					hotelCharacteristic.setTypeFood(typeFood);
				} else {
					hotelCharacteristic.setTypeFood(DEFAULT_FOOD);
				}
				break;
			}
			String name = localName.toUpperCase();
			TouristVoucherTag temp = TouristVoucherTag.valueOf(name);
			if (tagWithText.contains(temp)) {
				currentTag = temp;
			}
		}

		public void endElement(String uri, String localName, String qName) {
			TouristVoucherTag tag = TouristVoucherTag.valueOf(localName.toUpperCase());
			if (tag.equals(TouristVoucherTag.REST_VOUCHER) || tag.equals(TouristVoucherTag.EXCURSION_VOUCHER)
					|| tag.equals(TouristVoucherTag.PILIGRIMAGE_VOUCHER)) {
				touristVouchers.add(current);
			}
			if (tag.equals(TouristVoucherTag.HOTEL_CHARACTERISTIC)) {
				if (current instanceof ExcursionVoucher) {
					((ExcursionVoucher) current).addHotelCharacteristic(hotelCharacteristic);
				}
				if (current instanceof RestVoucher) {
					((RestVoucher) current).addHotelCharacteristic(hotelCharacteristic);
				}
			}
		}

		public void characters(char[] ch, int start, int length) {
			String tagText = new String(ch, start, length).trim();
			boolean result;
			if (currentTag != null) {
				switch (currentTag) {
				case NUMBER_VOUCHER:
					current.setNumberVoucher(tagText);
					break;
				case COUNTRY:
					current.setCountry(tagText);
					break;
				case NUMBER_DAYS:
					Integer numberDays = Integer.parseInt(tagText);
					current.setNumberDays(numberDays);
					break;
				case NUMBER_NIGHTS:
					Integer numberNights = Integer.parseInt(tagText);
					current.setNumberNights(numberNights);
					break;
				case COST:
					Integer cost = Integer.parseInt(tagText);
					current.setCost(cost);
					break;
				case DATE:
					LocalDateTime voucherDate = LocalDateTime.parse(tagText);
					current.setDate(voucherDate);
					break;
				case CITY:
					if (current instanceof ExcursionVoucher) {
						((ExcursionVoucher) current).setCity(tagText);
					}
					if (current instanceof PiligrimageVoucher) {
						((PiligrimageVoucher) current).setCity(tagText);
					}
					break;
				case SHOWPLACE:
					if (current instanceof ExcursionVoucher) {
						((ExcursionVoucher) current).setShowplace(tagText);
					}
					break;
				case STARS:
					Integer stars = Integer.parseInt(tagText);
					hotelCharacteristic.setStars(stars);
					break;
				case IS_FOOD:
					result = Boolean.parseBoolean(tagText);
					hotelCharacteristic.setFood(result);
					break;
				case IS_TV:
					result = Boolean.parseBoolean(tagText);
					hotelCharacteristic.setTV(result);
					break;
				case IS_CONDITIONER:
					result = Boolean.parseBoolean(tagText);
					hotelCharacteristic.setConditioner(result);
					break;
				case NUMBER_PLACE:
					Integer numberPlace = Integer.parseInt(tagText);
					hotelCharacteristic.setNumberPlace(numberPlace);
					break;
				default:
					throw new EnumConstantNotPresentException(currentTag.getDeclaringClass(), currentTag.name());
				}
			}
			currentTag = null;
		}
	}
}
