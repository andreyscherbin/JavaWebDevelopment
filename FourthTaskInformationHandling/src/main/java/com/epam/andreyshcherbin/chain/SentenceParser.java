package com.epam.andreyshcherbin.chain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.epam.andreyshcherbin.composite.TextComposite;
import com.epam.andreyshcherbin.composite.TypeComponent;

public class SentenceParser extends AbstractParser {
	
	private static final String LEXEME = "[\\w\\p{Punct}А-Яа-я“”]+";

	public SentenceParser(AbstractParser nextParser) {
		super(nextParser);
	}
	
	@Override
	public void parse(String sentence, TextComposite sentenceComposite) {
		Pattern pattern = Pattern.compile(LEXEME);
		Matcher matcher = pattern.matcher(sentence);
		while (matcher.find()) {
			TextComposite lexemeComposite = new TextComposite(TypeComponent.LEXEME);
			sentenceComposite.addComponent(lexemeComposite);
			AbstractParser nextParser = super.getNextParser();
			String word = matcher.group();
			nextParser.parse(word, lexemeComposite);
		}
	}
}
