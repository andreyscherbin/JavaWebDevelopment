package com.epam.andreyshcherbin.chain;

import com.epam.andreyshcherbin.composite.Symbol;
import com.epam.andreyshcherbin.composite.TextComposite;
import com.epam.andreyshcherbin.composite.TypeComponent;

public class LexemeParser extends AbstractParser {

	private static final String PUNCTUATION_MARK = "[?!.,;:\\-()“”]";

	public LexemeParser(AbstractParser nextParser) {
		super(nextParser);
	}

	@Override
	public void parse(String lexeme, TextComposite lexemeComposite) {
		char[] symbols = lexeme.toCharArray();
		for (Character ch : symbols) {
			String str = ch.toString();
			if (!str.matches(PUNCTUATION_MARK)) {
				Symbol symbol = new Symbol(TypeComponent.SYMBOL, ch);
				lexemeComposite.addComponent(symbol);
			} else {
				Symbol symbol = new Symbol(TypeComponent.PUNCTUATION_MARK, ch);
				lexemeComposite.addComponent(symbol);
			}
		}
	}
}
