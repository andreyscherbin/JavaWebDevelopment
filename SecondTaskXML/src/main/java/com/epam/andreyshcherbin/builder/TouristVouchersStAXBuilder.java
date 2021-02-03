package com.epam.andreyshcherbin.builder;

import java.util.Set;
import javax.xml.stream.XMLInputFactory;

import com.epam.andreyshcherbin.entity.HotelCharacteristic;
import com.epam.andreyshcherbin.entity.TouristVoucher;
import com.epam.andreyshcherbin.exception.ParserException;
import com.epam.andreyshcherbin.parsing.DateParser;
import com.epam.andreyshcherbin.parsing.TouristVoucherEnum;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
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
					if (name.equals(TouristVoucherEnum.TOURISTVOUCHER.getValue())) {
						TouristVoucher touristVoucher = buildTouristVoucher(reader);
						touristVouchers.add(touristVoucher);
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

	private TouristVoucher buildTouristVoucher(XMLStreamReader reader) throws XMLStreamException, ParserException {
		TouristVoucher touristVoucher = new TouristVoucher();
		String transport = TouristVoucherEnum.TRANSPORT.getValue();
		String attributeValue = reader.getAttributeValue(null, transport);
		touristVoucher.setTransport(attributeValue);
		String typeTouristVoucher = TouristVoucherEnum.TYPE.getValue();
		attributeValue = reader.getAttributeValue(null, typeTouristVoucher);
		touristVoucher.setType(attributeValue);
		String name;
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				String tagText;
				switch (TouristVoucherEnum.valueOf(name.toUpperCase())) {
				case NUMBERVOUCHER:
					tagText = getXMLText(reader);
					touristVoucher.setNumberVoucher(tagText);
					break;
				case COUNTRY:
					tagText = getXMLText(reader);
					touristVoucher.setCountry(tagText);
					break;
				case NUMBERDAYS:
					tagText = getXMLText(reader);
					Integer numberDays = Integer.parseInt(tagText);
					touristVoucher.setNumberDays(numberDays);
					break;
				case NUMBERNIGHTS:
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
					Date voucherDate = DateParser.parseString(tagText);
					touristVoucher.setDate(voucherDate);
					break;
				case HOTELCHARACTERISTIC:
					HotelCharacteristic hotelCharacteristic = getXMLHotelCharacteristic(reader);
					touristVoucher.setHotelCharacteristic(hotelCharacteristic);
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (TouristVoucherEnum.valueOf(name.toUpperCase()) == TouristVoucherEnum.TOURISTVOUCHER) {
					return touristVoucher;
				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element in tag TouristVoucher");
	}

	private HotelCharacteristic getXMLHotelCharacteristic(XMLStreamReader reader) throws XMLStreamException {
		HotelCharacteristic hotelCharacteristic = new HotelCharacteristic();
		String typeFood = TouristVoucherEnum.TYPEFOOD.getValue();
		String attributeValue = reader.getAttributeValue(null, typeFood);
		if (attributeValue != null) {
			hotelCharacteristic.setTypeFood(attributeValue);
		} else {
			hotelCharacteristic.setTypeFood("");
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
				switch (TouristVoucherEnum.valueOf(name.toUpperCase())) {
				case STARS:
					tagText = getXMLText(reader);
					Integer stars = Integer.parseInt(tagText);
					hotelCharacteristic.setStars(stars);
					break;
				case ISFOOD:
					tagText = getXMLText(reader);
					result = tagText.equals("true");
					hotelCharacteristic.setFood(result);
					break;
				case ISTV:
					tagText = getXMLText(reader);
					result = tagText.equals("true");
					hotelCharacteristic.setTV(result);
					break;
				case ISCONDITIONER:
					tagText = getXMLText(reader);
					result = tagText.equals("true");
					hotelCharacteristic.setConditioner(result);
					break;
				case NUMBERPLACE:
					tagText = getXMLText(reader);
					Integer numberPlace = Integer.parseInt(tagText);
					hotelCharacteristic.setNumberPlace(numberPlace);
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (TouristVoucherEnum.valueOf(name.toUpperCase()) == TouristVoucherEnum.HOTELCHARACTERISTIC) {
					return hotelCharacteristic;
				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element in tag HotelCharacteristic");
	}

	private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
		String text = null;
		if (reader.hasNext()) {
			reader.next();
			text = reader.getText();
		}
		return text;
	}
}
