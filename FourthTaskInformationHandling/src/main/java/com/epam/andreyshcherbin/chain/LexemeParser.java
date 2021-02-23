package com.epam.andreyshcherbin.chain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.andreyshcherbin.composite.Symbol;
import com.epam.andreyshcherbin.composite.TextComposite;
import com.epam.andreyshcherbin.composite.TypeComponent;

public class LexemeParser extends AbstractParser {
	
	private static final String WORD = "\\w+";
	private static final String PUNCTUATION_MARK = "\\p{Punct}";

	public LexemeParser(AbstractParser nextParser) {
		super(nextParser);
	}

	@Override
	public void parse(String lexeme, TextComposite lexemeComposite) {
		Pattern patternWord = Pattern.compile(WORD);
		Pattern patternPunctuation = Pattern.compile(PUNCTUATION_MARK);
		Matcher matcher = patternWord.matcher(lexeme);
		while (matcher.find()) {			
			AbstractParser nextParser = super.getNextParser();
			String word = matcher.group();
			nextParser.parse(word, lexemeComposite);
		}
		matcher = patternPunctuation.matcher(lexeme);
		while (matcher.find()) {
			String symbol = matcher.group();
			Character ch = symbol.charAt(0);
			Symbol sb = new Symbol(TypeComponent.PUNCTUATION_MARK, ch);			
			lexemeComposite.addComponent(sb);			
		}		
	}
}
