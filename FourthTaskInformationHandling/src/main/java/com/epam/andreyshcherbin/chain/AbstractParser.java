package com.epam.andreyshcherbin.chain;

import com.epam.andreyshcherbin.composite.TextComposite;
import com.epam.andreyshcherbin.exception.TextException;

public abstract class AbstractParser {
	private AbstractParser nextParser;

	AbstractParser(AbstractParser nextParser) {
		this.nextParser = nextParser;
	}

	public abstract void parse(String part, TextComposite partComposite) throws TextException;

	public AbstractParser getNextParser() {
		return nextParser;
	}
}
