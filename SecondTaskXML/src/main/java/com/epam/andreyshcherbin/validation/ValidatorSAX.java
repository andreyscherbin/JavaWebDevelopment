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
		Schema schema = null;
		String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
		SchemaFactory factory = SchemaFactory.newInstance(language);
		try {
			schema = factory.newSchema(new File(schemaname));
			SAXParserFactory spf = SAXParserFactory.newInstance();
			spf.setSchema(schema);
			SAXParser parser = spf.newSAXParser();
			parser.parse(filename, new TouristVoucherErrorHandler());		
			logger.info("{} is valid",filename);
		} catch (ParserConfigurationException e) {			
			logger.error("{} config error: {}",filename,e.getMessage());
			return false;
		} catch (SAXException e) {			
			logger.error("{} SAX error: {}",filename,e.getMessage());
			return false;
		} catch (IOException e) {			
			logger.error("I/O error: {}",e.getMessage());
			return false;
		}
		return true;
	}
}
