package com.epam.andreyshcherbin.parsing;

import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.andreyshcherbin.exception.ParserException;
import java.text.*;

public class DateParser {

	private static Logger logger = LogManager.getLogger();
	private static final String REGULAR_DATE_FORMAT = "yyyy-MM-dd'T'hh:mm:ss";

	public static Date parseString(String date) throws ParserException {
		Date parsingDate = null;
		SimpleDateFormat formatTemplate = new SimpleDateFormat(REGULAR_DATE_FORMAT);
		try {
			parsingDate = formatTemplate.parse(date);
		} catch (ParseException e) {
			logger.error("cannot parse date: {} parseException: {}", date, e);
			throw new ParserException("cannot parse date: " + date, e);
		}
		return parsingDate;
	}
}
