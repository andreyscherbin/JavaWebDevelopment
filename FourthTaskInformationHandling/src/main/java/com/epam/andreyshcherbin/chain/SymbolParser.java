package com.epam.andreyshcherbin.chain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.andreyshcherbin.composite.Symbol;
import com.epam.andreyshcherbin.composite.TextComposite;
import com.epam.andreyshcherbin.composite.TypeComponent;

public class SymbolParser extends AbstractParser {

	private static final String SYMBOL = ".";
	
	public SymbolParser(AbstractParser nextParser) {
		super(nextParser);
	}

	@Override
	public void parse(String word, TextComposite lexemeComposite) {
		Pattern pattern = Pattern.compile(SYMBOL);		
		Matcher matcher = pattern.matcher(word);
		while (matcher.find()) {
			String sb = matcher.group();
			Character ch = sb.charAt(0);
			Symbol symbol = new Symbol(TypeComponent.SYMBOL, ch);
			lexemeComposite.addComponent(symbol);			
		}		
	}
}
