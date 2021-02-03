package com.epam.andreyshcherbin.validation;

import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TouristVoucherErrorHandler extends DefaultHandler {
	
	private static Logger logger = LogManager.getLogger();

	public TouristVoucherErrorHandler() {
	
	}

	@Override
	public void warning(SAXParseException e) {		
		logger.warn("line column {} - ErrorMessage {}",getLineAddress(e),e.getMessage());
	}
	
	@Override
	public void error(SAXParseException e) {
		logger.error("line column {} - ErrorMessage {}",getLineAddress(e),e.getMessage());
	}
	
	@Override
	public void fatalError(SAXParseException e) {
		logger.fatal("line column {} - ErrorMessage {}",getLineAddress(e),e.getMessage());
	}	
	
	private String getLineAddress(SAXParseException e) {		
		return e.getLineNumber() + " : " + e.getColumnNumber();
	}
}
