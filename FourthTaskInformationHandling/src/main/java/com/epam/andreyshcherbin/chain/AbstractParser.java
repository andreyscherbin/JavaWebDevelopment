package com.epam.andreyshcherbin.chain;

import com.epam.andreyshcherbin.composite.TextComposite;

public abstract class AbstractParser {
	private AbstractParser nextParser;

	AbstractParser(AbstractParser nextParser) {
		this.nextParser = nextParser;
	}

	public abstract void parse(String part, TextComposite partComposite);

	public AbstractParser getNextParser() {
		return nextParser;
	}

	public boolean hasNext() {
		return nextParser != null;
	}
}
