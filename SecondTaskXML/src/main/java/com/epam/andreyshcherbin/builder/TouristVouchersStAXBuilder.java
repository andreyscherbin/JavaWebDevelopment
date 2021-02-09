package com.epam.andreyshcherbin.builder;

import java.util.Set;
import javax.xml.stream.XMLInputFactory;

import com.epam.andreyshcherbin.entity.ExcursionVoucher;
import com.epam.andreyshcherbin.entity.HotelCharacteristic;
import com.epam.andreyshcherbin.entity.PiligrimageVoucher;
import com.epam.andreyshcherbin.entity.RestVoucher;
import com.epam.andreyshcherbin.entity.TouristVoucher;
import com.epam.andreyshcherbin.exception.ParserException;
import com.epam.andreyshcherbin.parsing.TouristVoucherTag;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TouristVouchersStAXBuilder extends AbstractTouristVouchersBuilder {

	private static Logger logger = LogManager.getLogger();
	private XMLInputFactory inputFactory;

	public TouristVouchersStAXBuilder() {
		inputFactory = XMLInputFactory.newInstance();
	}

	public TouristVouchersStAXBuilder(Set<TouristVoucher> touristVouchers) {
		super(touristVouchers);
	}

	@Override
	public void buildSetTouristVouchers(String fileName) throws ParserException {
		XMLStreamReader reader;
		String name;
		try (FileInputStream inputStream = new FileInputStream(new File(fileName))) {
			reader = inputFactory.createXMLStreamReader(inputStream);
			while (reader.hasNext()) {
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					name = reader.getLocalName();
					TouristVoucherTag tag = TouristVoucherTag.valueOf(name.toUpperCase());
					switch (tag) {
					case REST_VOUCHER:
						TouristVoucher touristVoucher = new RestVoucher();
						buildTouristVoucher(reader, touristVoucher);
						touristVouchers.add(touristVoucher);
						break;
					case EXCURSION_VOUCHER:
						touristVoucher = new ExcursionVoucher();
						buildTouristVoucher(reader, touristVoucher);
						touristVouchers.add(touristVoucher);
						break;
					case PILIGRIMAGE_VOUCHER:
						touristVoucher = new PiligrimageVoucher();
						buildTouristVoucher(reader, touristVoucher);
						touristVouchers.add(touristVoucher);
						break;
					}
				}
			}
		} catch (XMLStreamException | FileNotFoundException e) {
			logger.error("xmlStreamException or fileNotFoundException: {}", e);
			throw new ParserException("xmlStreamException or fileNotFoundException: ", e);
		} catch (IOException e) {
			logger.error("ioexception: {}", e);
			throw new ParserException("ioexception: ", e);
		}
	}

	private TouristVoucher buildTouristVoucher(XMLStreamReader reader, TouristVoucher touristVoucher)
			throws XMLStreamException, ParserException {

		String transport = TouristVoucherTag.TRANSPORT.getValue();
		String attributeValue = reader.getAttributeValue(null, transport);
		touristVoucher.setTransport(attributeValue);
		String name;
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				String tagText;
				switch (TouristVoucherTag.valueOf(name.toUpperCase())) {
				case NUMBER_VOUCHER:
					tagText = getXMLText(reader);
					touristVoucher.setNumberVoucher(tagText);
					break;
				case COUNTRY:
					tagText = getXMLText(reader);
					touristVoucher.setCountry(tagText);
					break;
				case NUMBER_DAYS:
					tagText = getXMLText(reader);
					Integer numberDays = Integer.parseInt(tagText);
					touristVoucher.setNumberDays(numberDays);
					break;
				case NUMBER_NIGHTS:
					tagText = getXMLText(reader);
					Integer numberNights = Integer.parseInt(tagText);
					touristVoucher.setNumberNights(numberNights);
					break;
				case COST:
					tagText = getXMLText(reader);
					Integer cost = Integer.parseInt(tagText);
					touristVoucher.setCost(cost);
					break;
				case DATE:
					tagText = getXMLText(reader);
					LocalDateTime voucherDate = LocalDateTime.parse(tagText);
					touristVoucher.setDate(voucherDate);
					break;
				case CITY:
					tagText = getXMLText(reader);
					if (touristVoucher instanceof ExcursionVoucher) {
						((ExcursionVoucher) touristVoucher).setCity(tagText);
					}
					if (touristVoucher instanceof PiligrimageVoucher) {
						((PiligrimageVoucher) touristVoucher).setCity(tagText);
					}
					break;
				case SHOWPLACE:
					tagText = getXMLText(reader);
					if (touristVoucher instanceof ExcursionVoucher) {
						((ExcursionVoucher) touristVoucher).setShowplace(tagText);
					}
					break;
				case HOTEL_CHARACTERISTIC:
					HotelCharacteristic hotelCharacteristic = getXMLHotelCharacteristic(reader);
					if (touristVoucher instanceof ExcursionVoucher) {
						((ExcursionVoucher) touristVoucher).addHotelCharacteristic(hotelCharacteristic);
					}
					if (touristVoucher instanceof RestVoucher) {
						((RestVoucher) touristVoucher).addHotelCharacteristic(hotelCharacteristic);
					}
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (TouristVoucherTag.valueOf(name.toUpperCase()) == TouristVoucherTag.EXCURSION_VOUCHER || 
					TouristVoucherTag.valueOf(name.toUpperCase()) == TouristVoucherTag.REST_VOUCHER ||
					TouristVoucherTag.valueOf(name.toUpperCase()) == TouristVoucherTag.PILIGRIMAGE_VOUCHER) {
					return touristVoucher;
				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element in tag TouristVoucher");
	}

	private HotelCharacteristic getXMLHotelCharacteristic(XMLStreamReader reader) throws XMLStreamException {
		HotelCharacteristic hotelCharacteristic = new HotelCharacteristic();
		String typeFood = TouristVoucherTag.TYPE_FOOD.getValue();
		String attributeValue = reader.getAttributeValue(null, typeFood);
		if (attributeValue != null) {
			hotelCharacteristic.setTypeFood(attributeValue);
		} else {
			hotelCharacteristic.setTypeFood("HB");
		}
		int type;
		String name;
		while (reader.hasNext()) {
			type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				String tagText;
				boolean result;
				switch (TouristVoucherTag.valueOf(name.toUpperCase())) {
				case STARS:
					tagText = getXMLText(reader);
					Integer stars = Integer.parseInt(tagText);
					hotelCharacteristic.setStars(stars);
					break;
				case IS_FOOD:
					tagText = getXMLText(reader);
					result = Boolean.parseBoolean(tagText);
					hotelCharacteristic.setFood(result);
					break;
				case IS_TV:
					tagText = getXMLText(reader);
					result = Boolean.parseBoolean(tagText);
					hotelCharacteristic.setTV(result);
					break;
				case IS_CONDITIONER:
					tagText = getXMLText(reader);
					result = Boolean.parseBoolean(tagText);
					hotelCharacteristic.setConditioner(result);
					break;
				case NUMBER_PLACE:
					tagText = getXMLText(reader);
					Integer numberPlace = Integer.parseInt(tagText);
					hotelCharacteristic.setNumberPlace(numberPlace);
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (TouristVoucherTag.valueOf(name.toUpperCase()) == TouristVoucherTag.HOTEL_CHARACTERISTIC) {
					return hotelCharacteristic;
				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element in tag HotelCharacteristic");
	}

	private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
		String text = "some string";
		if (reader.hasNext()) {
			reader.next();
			text = reader.getText();
		}
		return text;
	}
}
