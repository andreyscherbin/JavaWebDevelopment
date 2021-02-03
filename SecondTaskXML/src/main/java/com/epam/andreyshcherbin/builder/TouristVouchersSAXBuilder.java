package com.epam.andreyshcherbin.builder;

import java.io.IOException;
import java.util.Date;
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
import com.epam.andreyshcherbin.entity.HotelCharacteristic;
import com.epam.andreyshcherbin.entity.TouristVoucher;
import com.epam.andreyshcherbin.exception.ParserException;
import com.epam.andreyshcherbin.parsing.DateParser;
import com.epam.andreyshcherbin.parsing.TouristVoucherEnum;

public class TouristVouchersSAXBuilder extends AbstractTouristVouchersBuilder {

	private static Logger logger = LogManager.getLogger();
	private TouristVoucherHandler touristVoucherHandler;
	private XMLReader reader;

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
			logger.error("ошибка I/О потока: {}", e);
			throw new ParserException("ошибка I/О потока: ", e);
		}
		touristVouchers = touristVoucherHandler.getTouristVouchers();
	}

	private static class TouristVoucherHandler extends DefaultHandler {

		private Set<TouristVoucher> touristVouchers;
		private TouristVoucher current = null;
		private TouristVoucherEnum currentEnum = null;
		private EnumSet<TouristVoucherEnum> withText;

		public TouristVoucherHandler() {
			touristVouchers = new HashSet<TouristVoucher>();
			withText = EnumSet.range(TouristVoucherEnum.NUMBERVOUCHER, TouristVoucherEnum.DATE);
		}

		public Set<TouristVoucher> getTouristVouchers() {
			return touristVouchers;
		}

		public void startElement(String uri, String localName, String qName, Attributes attrs) {
			if ("TouristVoucher".equals(localName)) {
				current = new TouristVoucher();
				String transport = attrs.getValue(0);
				current.setTransport(transport);
				if (attrs.getLength() == 2) {
					String type = attrs.getValue(1);
					current.setType(type);
				}
			} else {
				if ("hotelCharacteristic".equals(localName)) {
					HotelCharacteristic hotelCharacteristic = current.getHotelCharacteristic();
					String typeFood = attrs.getValue(0);
					if (typeFood != null) {
						hotelCharacteristic.setTypeFood(typeFood);
					} else {
						hotelCharacteristic.setTypeFood("");
					}
				}
				String name = localName.toUpperCase();
				TouristVoucherEnum temp = TouristVoucherEnum.valueOf(name);
				if (withText.contains(temp)) {
					currentEnum = temp;
				}
			}
		}

		public void endElement(String uri, String localName, String qName) {
			if ("TouristVoucher".equals(localName)) {
				touristVouchers.add(current);
			}
		}

		public void characters(char[] ch, int start, int length) {
			String tagText = new String(ch, start, length).trim();
			boolean result;
			if (currentEnum != null) {
				HotelCharacteristic hotelCharacteristic = current.getHotelCharacteristic();
				switch (currentEnum) {
				case NUMBERVOUCHER:
					current.setNumberVoucher(tagText);
					break;
				case COUNTRY:
					current.setCountry(tagText);
					break;
				case NUMBERDAYS:
					Integer numberDays = Integer.parseInt(tagText);
					current.setNumberDays(numberDays);
					break;
				case NUMBERNIGHTS:
					Integer numberNights = Integer.parseInt(tagText);
					current.setNumberNights(numberNights);
					break;
				case COST:
					Integer cost = Integer.parseInt(tagText);
					current.setCost(cost);
					break;
				case DATE:
					Date voucherDate = null;
					try {
						voucherDate = DateParser.parseString(tagText);
						current.setDate(voucherDate);
					} catch (ParserException e) {
						current.setDate(voucherDate);
					}
					break;
				case STARS:
					Integer stars = Integer.parseInt(tagText);
					hotelCharacteristic.setStars(stars);
					break;
				case ISFOOD:
					result = tagText.equals("true");
					hotelCharacteristic.setFood(result);
					break;
				case ISTV:
					result = tagText.equals("true");
					hotelCharacteristic.setTV(result);
					break;
				case ISCONDITIONER:
					result = tagText.equals("true");
					hotelCharacteristic.setConditioner(result);
					break;
				case NUMBERPLACE:
					Integer numberPlace = Integer.parseInt(tagText);
					hotelCharacteristic.setNumberPlace(numberPlace);
					break;
				default:
					Class<TouristVoucherEnum> classObject = currentEnum.getDeclaringClass();
					String name = currentEnum.name();
					throw new EnumConstantNotPresentException(classObject, name);
				}
			}
			currentEnum = null;
		}
	}
}
