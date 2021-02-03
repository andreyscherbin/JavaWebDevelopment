package com.epam.andreyshcherbin.builder;

import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import com.epam.andreyshcherbin.entity.TouristVoucher;
import com.epam.andreyshcherbin.exception.ParserException;
import com.epam.andreyshcherbin.entity.HotelCharacteristic;
import com.epam.andreyshcherbin.parsing.DateParser;
import java.io.IOException;
import java.util.Date;
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
		Document doc = null;
		try {
			doc = docBuilder.parse(fileName);
			Element root = doc.getDocumentElement();
			NodeList touristVouchersList = root.getElementsByTagName("tns:TouristVoucher");
			for (int i = 0; i < touristVouchersList.getLength(); i++) {
				Element touristVoucherElement = (Element) touristVouchersList.item(i);
				TouristVoucher touristVoucher = buildTouristVoucher(touristVoucherElement);
				touristVouchers.add(touristVoucher);
			}
		} catch (IOException e) {
			logger.error("File error or I/O error: {}", e);
			throw new ParserException("File error or I/O error: ", e);
		} catch (SAXException e) {
			logger.error("Parsing failure:  {}", e);
			throw new ParserException("Parsing failure: ", e);
		}
	}

	private TouristVoucher buildTouristVoucher(Element touristVoucherElement) throws ParserException {

		TouristVoucher touristVoucher = new TouristVoucher();
		String transport = touristVoucherElement.getAttribute("transport");
		touristVoucher.setTransport(transport);
		String type = touristVoucherElement.getAttribute("type");
		touristVoucher.setType(type);
		String numberVoucher = getElementTextContent(touristVoucherElement, "tns:numberVoucher");
		touristVoucher.setNumberVoucher(numberVoucher);
		String country = getElementTextContent(touristVoucherElement, "tns:country");
		touristVoucher.setCountry(country);
		String numberDaysText = getElementTextContent(touristVoucherElement, "tns:numberDays");
		Integer numberDays = Integer.parseInt(numberDaysText);
		touristVoucher.setNumberDays(numberDays);
		String numberNightsText = getElementTextContent(touristVoucherElement, "tns:numberNights");
		Integer numberNights = Integer.parseInt(numberNightsText);
		touristVoucher.setNumberNights(numberNights);
		String costText = getElementTextContent(touristVoucherElement, "tns:cost");
		Integer cost = Integer.parseInt(costText);
		touristVoucher.setCost(cost);
		String date = getElementTextContent(touristVoucherElement, "tns:date");
		Date voucherDate = DateParser.parseString(date);
		touristVoucher.setDate(voucherDate);
		HotelCharacteristic hotelCharacteristic = touristVoucher.getHotelCharacteristic();
		Element hotelCharacteristicElement = (Element) touristVoucherElement
				.getElementsByTagName("tns:hotelCharacteristic").item(0);
		String typeFood = hotelCharacteristicElement.getAttribute("typeFood");
		hotelCharacteristic.setTypeFood(typeFood);
		String starsText = getElementTextContent(hotelCharacteristicElement, "tns:stars");
		Integer stars = Integer.parseInt(starsText);
		hotelCharacteristic.setStars(stars);
		String isFood = getElementTextContent(hotelCharacteristicElement, "tns:isFood");
		boolean result = isFood.equals("true");
		hotelCharacteristic.setFood(result);
		String isTV = getElementTextContent(hotelCharacteristicElement, "tns:isTv");
		result = isTV.equals("true");
		hotelCharacteristic.setTV(result);
		String isConditioner = getElementTextContent(hotelCharacteristicElement, "tns:isConditioner");
		result = isConditioner.equals("true");
		hotelCharacteristic.setConditioner(result);
		String numberPlaceText = getElementTextContent(hotelCharacteristicElement, "tns:numberPlace");
		Integer numberPlace = Integer.parseInt(numberPlaceText);
		hotelCharacteristic.setNumberPlace(numberPlace);
		return touristVoucher;
	}

	private static String getElementTextContent(Element element, String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		Node node = nList.item(0);
		String text = node.getTextContent();
		return text;
	}
}
