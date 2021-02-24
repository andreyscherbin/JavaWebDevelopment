package com.epam.andreyshcherbin.chain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.andreyshcherbin.composite.Symbol;
import com.epam.andreyshcherbin.composite.TextComposite;
import com.epam.andreyshcherbin.composite.TypeComponent;

public class LexemeParser extends AbstractParser {

	private static final String LEXEMA = "[\\w\\p{Punct}А-Яа-я“”]+";

	public LexemeParser(AbstractParser nextParser) {
		super(nextParser);
	}

	@Override
	public void parse(String lexeme, TextComposite lexemeComposite) {
		Pattern pattern = Pattern.compile(LEXEMA);
		Matcher matcher = pattern.matcher(lexeme);
		while (matcher.find()) {
			String word = matcher.group();
			char[] symbols = word.toCharArray();
			for (int i = 0; i < symbols.length; i++) {
				if (i == symbols.length - 1
						&& (symbols[i] == ',' || symbols[i] == '.' || symbols[i] == '!' || symbols[i] == '?')) {
					Symbol sb = new Symbol(TypeComponent.PUNCTUATION_MARK, symbols[i]);
					lexemeComposite.addComponent(sb);
				} else {
					Symbol sb = new Symbol(TypeComponent.SYMBOL, symbols[i]);
					lexemeComposite.addComponent(sb);
				}
			}
		}
	}
}
