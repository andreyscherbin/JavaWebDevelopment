package com.epam.andreyshcherbin.validation;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

public class ValidatorSAX {

	private static Logger logger = LogManager.getLogger();

	public boolean validateXML(String filename, String schemaname) {
		boolean isValid = false;
		Schema schema = null;
		String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
		SchemaFactory factory = SchemaFactory.newInstance(language);
		try {
			schema = factory.newSchema(new File(schemaname));
			SAXParserFactory spf = SAXParserFactory.newInstance();
			spf.setSchema(schema);
			SAXParser parser = spf.newSAXParser();
			parser.parse(filename, new TouristVoucherErrorHandler());
			logger.info("{} is valid", filename);
			isValid = true;
			return isValid;
		} catch (ParserConfigurationException e) {			
			logger.error("{} config error: {}", filename, e.getMessage());
		} catch (SAXException e) {			
			logger.error("{} SAX error: {}", filename, e.getMessage());
		} catch (IOException e) {			
			logger.error("I/O error: {}", e.getMessage());
		}
		return isValid;
	}
}
