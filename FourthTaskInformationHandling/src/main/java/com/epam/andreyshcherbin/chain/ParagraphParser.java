package com.epam.andreyshcherbin.chain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.epam.andreyshcherbin.composite.TextComposite;
import com.epam.andreyshcherbin.composite.TypeComponent;

public class ParagraphParser extends AbstractParser {

	private static final String SENTENCE = "([A-Z0-9])(([\\w\\s]|[\\p{Punct}А-Яа-я“”](?!\\s[A-Z0-9]))+)([?!.])";

	public ParagraphParser(AbstractParser nextParser) {
		super(nextParser);
	}

	@Override
	public void parse(String paragraph, TextComposite paragraphComposite) {
		Pattern pattern = Pattern.compile(SENTENCE);
		Matcher matcher = pattern.matcher(paragraph);
		while (matcher.find()) {
			TextComposite sentenceComposite = new TextComposite(TypeComponent.SENTENCE);
			paragraphComposite.addComponent(sentenceComposite);
			AbstractParser nextParser = super.getNextParser();
			String sentence = matcher.group();
			nextParser.parse(sentence, sentenceComposite);
		}
	}
}
