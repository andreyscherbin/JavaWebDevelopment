package com.epam.andreyshcherbin.chain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.andreyshcherbin.composite.ComponentType;
import com.epam.andreyshcherbin.composite.TextComposite;
import com.epam.andreyshcherbin.exception.TextException;

public class ParagraphParser extends AbstractParser {

	private static Logger logger = LogManager.getLogger();
	private static final String SENTENCE_REGEX = "([\\p{Upper}])(([\\w\\s]|[\\p{Punct}\\p{IsCyrillic}](?!\\s[\\p{Upper}\\d]))+)([?!.{3}])";

	public ParagraphParser(AbstractParser nextParser) {
		super(nextParser);
	}

	@Override
	public void parse(String paragraph, TextComposite paragraphComposite) throws TextException {
		if (paragraph == null || paragraph.isEmpty() || paragraphComposite == null) {
			logger.error("paragraph is null or empty {} {}", paragraph, paragraphComposite);
			throw new TextException("paragraph is null or empty: " + paragraph + " " + paragraphComposite);
		}
		Pattern pattern = Pattern.compile(SENTENCE_REGEX);
		Matcher matcher = pattern.matcher(paragraph);
		while (matcher.find()) {
			TextComposite sentenceComposite = new TextComposite(ComponentType.SENTENCE);
			paragraphComposite.addComponent(sentenceComposite);
			AbstractParser nextParser = super.getNextParser();
			String sentence = matcher.group();
			nextParser.parse(sentence, sentenceComposite);
		}
	}
}
