package com.epam.andreyshcherbin.chain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.andreyshcherbin.composite.ComponentType;
import com.epam.andreyshcherbin.composite.TextComposite;
import com.epam.andreyshcherbin.exception.TextException;

public class SentenceParser extends AbstractParser {

	private static Logger logger = LogManager.getLogger();
	private static final String LEXEME_REGEX = "[\\w\\p{Punct}\\p{IsCyrillic}]+";

	public SentenceParser(AbstractParser nextParser) {
		super(nextParser);
	}

	@Override
	public void parse(String sentence, TextComposite sentenceComposite) throws TextException {
		if (sentence == null || sentence.isEmpty() || sentenceComposite == null) {
			logger.error("sentence is null or empty {} {}", sentence, sentenceComposite);
			throw new TextException("sentence is null or empty: " + sentence + " " + sentenceComposite);
		}
		Pattern pattern = Pattern.compile(LEXEME_REGEX);
		Matcher matcher = pattern.matcher(sentence);
		while (matcher.find()) {
			TextComposite lexemeComposite = new TextComposite(ComponentType.LEXEME);
			sentenceComposite.addComponent(lexemeComposite);
			AbstractParser nextParser = super.getNextParser();
			String lexeme = matcher.group();
			nextParser.parse(lexeme, lexemeComposite);
		}
	}
}
