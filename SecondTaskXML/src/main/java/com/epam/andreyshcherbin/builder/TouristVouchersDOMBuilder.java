package com.epam.andreyshcherbin.builder;

import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import com.epam.andreyshcherbin.entity.TouristVoucher;
import com.epam.andreyshcherbin.exception.ParserException;
import com.epam.andreyshcherbin.parsing.TouristVoucherTag;
import com.epam.andreyshcherbin.entity.ExcursionVoucher;
import com.epam.andreyshcherbin.entity.HotelCharacteristic;
import com.epam.andreyshcherbin.entity.PiligrimageVoucher;
import com.epam.andreyshcherbin.entity.RestVoucher;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class TouristVouchersDOMBuilder extends AbstractTouristVouchersBuilder {

	private static Logger logger = LogManager.getLogger();
	private DocumentBuilder docBuilder;

	public TouristVouchersDOMBuilder() throws ParserException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			logger.error("error of configuration parser: {}", e);
			throw new ParserException("error of configuration parser: ", e);
		}
	}

	public TouristVouchersDOMBuilder(Set<TouristVoucher> touristVouchers) {
		super(touristVouchers);
	}

	@Override
	public void buildSetTouristVouchers(String fileName) throws ParserException {
		Document doc;
		try {
			doc = docBuilder.parse(fileName);
			Element root = doc.getDocumentElement();
			fillTouristVouchersSet(root, TouristVoucherTag.PILIGRIMAGE_VOUCHER);
			fillTouristVouchersSet(root, TouristVoucherTag.REST_VOUCHER);
			fillTouristVouchersSet(root, TouristVoucherTag.EXCURSION_VOUCHER);

		} catch (IOException e) {
			logger.error("File error or I/O error: {}", e);
			throw new ParserException("File error or I/O error: ", e);
		} catch (SAXException e) {
			logger.error("Parsing failure:  {}", e);
			throw new ParserException("Parsing failure: ", e);
		}
	}

	private void fillTouristVouchersSet(Element root, TouristVoucherTag tag) {
		String value = tag.getValue();
		NodeList touristVouchersList = root.getElementsByTagName(value);
		for (int i = 0; i < touristVouchersList.getLength(); i++) {
			Element touristVoucherElement = (Element) touristVouchersList.item(i);
			TouristVoucher touristVoucher = buildTouristVoucher(touristVoucherElement, tag);
			touristVouchers.add(touristVoucher);
		}
	}

	private TouristVoucher buildTouristVoucher(Element touristVoucherElement, TouristVoucherTag tag) {
		TouristVoucher touristVoucher = null;	
		switch (tag) {
		case REST_VOUCHER:
			touristVoucher = new RestVoucher();
			break;
		case EXCURSION_VOUCHER:
			touristVoucher = new ExcursionVoucher();
			break;
		case PILIGRIMAGE_VOUCHER:
			touristVoucher = new PiligrimageVoucher();
			break;		
		}
		String transport = touristVoucherElement.getAttribute(TouristVoucherTag.TRANSPORT.getValue());
		touristVoucher.setTransport(transport);
		String numberVoucher = getElementTextContent(touristVoucherElement, TouristVoucherTag.NUMBER_VOUCHER.getValue());
		touristVoucher.setNumberVoucher(numberVoucher);
		String country = getElementTextContent(touristVoucherElement, TouristVoucherTag.COUNTRY.getValue());
		touristVoucher.setCountry(country);
		String numberDaysText = getElementTextContent(touristVoucherElement, TouristVoucherTag.NUMBER_DAYS.getValue());
		Integer numberDays = Integer.parseInt(numberDaysText);
		touristVoucher.setNumberDays(numberDays);
		String numberNightsText = getElementTextContent(touristVoucherElement, TouristVoucherTag.NUMBER_NIGHTS.getValue());
		Integer numberNights = Integer.parseInt(numberNightsText);
		touristVoucher.setNumberNights(numberNights);
		String costText = getElementTextContent(touristVoucherElement, TouristVoucherTag.COST.getValue());
		Integer cost = Integer.parseInt(costText);
		touristVoucher.setCost(cost);
		String date = getElementTextContent(touristVoucherElement, TouristVoucherTag.DATE.getValue());
		LocalDateTime voucherDate = LocalDateTime.parse(date);
		touristVoucher.setDate(voucherDate);
		if (tag.equals(TouristVoucherTag.REST_VOUCHER) || tag.equals(TouristVoucherTag.EXCURSION_VOUCHER)) {

			NodeList hotelCharacteristicList = touristVoucherElement.getElementsByTagName(TouristVoucherTag.HOTEL_CHARACTERISTIC.getValue());
			for (int i = 0; i < hotelCharacteristicList.getLength(); i++) {
				Element hotelCharacteristicElement = (Element) hotelCharacteristicList.item(i);
				HotelCharacteristic hotelCharacteristic = new HotelCharacteristic();
				if (hotelCharacteristicElement.hasAttributes()) {
					String typeFood = hotelCharacteristicElement.getAttribute(TouristVoucherTag.TYPE_FOOD.getValue());
					hotelCharacteristic.setTypeFood(typeFood);
				} else {
					hotelCharacteristic.setTypeFood("HB");
				}
				String starsText = getElementTextContent(hotelCharacteristicElement, TouristVoucherTag.STARS.getValue());
				Integer stars = Integer.parseInt(starsText);
				hotelCharacteristic.setStars(stars);
				String isFood = getElementTextContent(hotelCharacteristicElement, TouristVoucherTag.IS_FOOD.getValue());
				boolean result = Boolean.parseBoolean(isFood);
				hotelCharacteristic.setFood(result);
				String isTV = getElementTextContent(hotelCharacteristicElement, TouristVoucherTag.IS_TV.getValue());
				result = Boolean.parseBoolean(isTV);
				hotelCharacteristic.setTV(result);
				String isConditioner = getElementTextContent(hotelCharacteristicElement, TouristVoucherTag.IS_CONDITIONER.getValue());
				result = Boolean.parseBoolean(isConditioner);
				hotelCharacteristic.setConditioner(result);
				String numberPlaceText = getElementTextContent(hotelCharacteristicElement, TouristVoucherTag.NUMBER_PLACE.getValue());
				Integer numberPlace = Integer.parseInt(numberPlaceText);
				hotelCharacteristic.setNumberPlace(numberPlace);
				if (touristVoucher instanceof ExcursionVoucher) {
					((ExcursionVoucher) touristVoucher).addHotelCharacteristic(hotelCharacteristic);
				}
				if (touristVoucher instanceof RestVoucher) {
					((RestVoucher) touristVoucher).addHotelCharacteristic(hotelCharacteristic);
				}
			}
		}
		if (tag.equals(TouristVoucherTag.EXCURSION_VOUCHER)) {
			String city = getElementTextContent(touristVoucherElement, TouristVoucherTag.CITY.getValue());
			((ExcursionVoucher) touristVoucher).setCity(city);
			String showplace = getElementTextContent(touristVoucherElement, TouristVoucherTag.SHOWPLACE.getValue());
			((ExcursionVoucher) touristVoucher).setShowplace(showplace);
		}
		if (tag.equals(TouristVoucherTag.PILIGRIMAGE_VOUCHER)) {
			String city = getElementTextContent(touristVoucherElement, TouristVoucherTag.CITY.getValue());
			((PiligrimageVoucher) touristVoucher).setCity(city);
		}
		return touristVoucher;
	}

	private static String getElementTextContent(Element element, String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		Node node = nList.item(0);
		String text = node.getTextContent();
		return text;
	}
}
