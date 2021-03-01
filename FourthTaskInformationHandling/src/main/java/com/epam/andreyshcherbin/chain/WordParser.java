package com.epam.andreyshcherbin.chain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.andreyshcherbin.composite.ComponentType;
import com.epam.andreyshcherbin.composite.CustomSymbol;
import com.epam.andreyshcherbin.composite.TextComposite;
import com.epam.andreyshcherbin.exception.TextException;

public class WordParser extends AbstractParser {

	private static Logger logger = LogManager.getLogger();

	public WordParser(AbstractParser nextParser) {
		super(nextParser);
	}

	@Override
	public void parse(String word, TextComposite wordComposite) throws TextException {
		if (word == null || word.isEmpty() || wordComposite == null) {
			logger.error("word is null or empty {} {}", word, wordComposite);
			throw new TextException("word is null or empty: " + word + " " + wordComposite);
		}
		char[] symbols = word.toCharArray();
		CustomSymbol symbolComponent;
		for (char symbol : symbols) {
			symbolComponent = new CustomSymbol(ComponentType.SYMBOL, symbol);
			wordComposite.addComponent(symbolComponent);
		}
	}
}
