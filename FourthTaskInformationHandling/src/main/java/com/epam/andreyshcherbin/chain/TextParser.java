package com.epam.andreyshcherbin.chain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.andreyshcherbin.composite.TextComposite;
import com.epam.andreyshcherbin.composite.ComponentType;
import com.epam.andreyshcherbin.exception.TextException;

public class TextParser extends AbstractParser {

	private static Logger logger = LogManager.getLogger();
	private static final String PARAGRAPH_REGEX = "\t[A-Z][\\w \\p{Punct}А-Яа-я\n]+";

	public TextParser(AbstractParser nextParser) {
		super(nextParser);
	}

	@Override
	public void parse(String text, TextComposite textComposite) throws TextException {
		if (text == null || text.isEmpty() || textComposite == null) {
			logger.error("text is null or empty {} {}", text, textComposite);
			throw new TextException("text is null or empty: " + text + " " + textComposite);
		}
		Pattern pattern = Pattern.compile(PARAGRAPH_REGEX);
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			TextComposite paragraphComposite = new TextComposite(ComponentType.PARAGRAPH);
			textComposite.addComponent(paragraphComposite);
			AbstractParser nextParser = super.getNextParser();
			String paragraph = matcher.group();
			nextParser.parse(paragraph, paragraphComposite);
		}
	}
}
