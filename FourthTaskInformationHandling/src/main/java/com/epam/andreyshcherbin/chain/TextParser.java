package com.epam.andreyshcherbin.chain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.andreyshcherbin.composite.TextComposite;
import com.epam.andreyshcherbin.composite.TypeComponent;

public class TextParser extends AbstractParser {
	
	private static final String PARAGRAPH = "\t[A-Z0-9][\\w \\p{Punct}А-Яа-я“”\n]+";

	public TextParser(AbstractParser nextParser) {
		super(nextParser);
	}

	@Override
	public void parse(String text, TextComposite textComposite) {
		Pattern pattern = Pattern.compile(PARAGRAPH);
		Matcher matcher = pattern.matcher(text);
		while(matcher.find()) {
			TextComposite paragraphComposite = new TextComposite(TypeComponent.PARAGRAPH);
			textComposite.addComponent(paragraphComposite);
			AbstractParser nextParser = super.getNextParser();
			String paragraph = matcher.group();
			nextParser.parse(paragraph, paragraphComposite);
		}
	}
}
