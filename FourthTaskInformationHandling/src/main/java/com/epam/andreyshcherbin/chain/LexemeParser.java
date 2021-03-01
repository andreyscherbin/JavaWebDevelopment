package com.epam.andreyshcherbin.chain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.andreyshcherbin.composite.CustomSymbol;
import com.epam.andreyshcherbin.composite.TextComposite;
import com.epam.andreyshcherbin.composite.ComponentType;
import com.epam.andreyshcherbin.exception.TextException;

public class LexemeParser extends AbstractParser {

	private static Logger logger = LogManager.getLogger();
	private static final String FUNCTION_CALL_REGEX = "\\w+\\.\\w+\\(.*\\)";
	private static final String PUNCTUATION_MARK_REGEX = "(\\p{Punct})";
	private static final String MARK_WORD_MARK_REGEX = "(\\p{Punct}.+\\p{Punct})";
	private static final String MARK_WORD_REGEX = "(\\p{Punct}.+)";
	private static final String FUNCTION_CALL_MARK_REGEX = "\\w+\\.\\w+\\(.*\\)\\p{Punct}";
	private static final String WORD_MARK_REGEX = ".+\\p{Punct}";
	private static final String WORD_MARK_SPLIT_REGEX = "(?=[,.!?)])";

	public LexemeParser(AbstractParser nextParser) {
		super(nextParser);
	}

	@Override
	public void parse(String lexeme, TextComposite lexemeComposite) throws TextException {
		if (lexeme == null || lexeme.isEmpty() || lexemeComposite == null) {
			logger.error("lexeme is null or empty {} {}", lexeme, lexemeComposite);
			throw new TextException("lexeme is null or empty: " + lexeme + " " + lexemeComposite);
		}
		TextComposite wordComposite = new TextComposite(ComponentType.WORD);
		AbstractParser nextParser = super.getNextParser();
		String word;
		CustomSymbol symbol;
		if (lexeme.matches(FUNCTION_CALL_REGEX)) {
			nextParser.parse(lexeme, wordComposite);
			lexemeComposite.addComponent(wordComposite);
		} else if (lexeme.matches(PUNCTUATION_MARK_REGEX)) {
			symbol = new CustomSymbol(ComponentType.PUNCTUATION_MARK, lexeme.charAt(0));
			lexemeComposite.addComponent(symbol);
		} else if (lexeme.matches(MARK_WORD_MARK_REGEX)) {
			Character firstSymbol = lexeme.charAt(0);
			symbol = new CustomSymbol(ComponentType.PUNCTUATION_MARK, firstSymbol);
			lexemeComposite.addComponent(symbol);
			word = lexeme.substring(1, lexeme.length() - 1);
			nextParser.parse(word, wordComposite);
			lexemeComposite.addComponent(wordComposite);
			Character lastSymbol = lexeme.charAt(lexeme.length() - 1);
			symbol = new CustomSymbol(ComponentType.PUNCTUATION_MARK, lastSymbol);
			lexemeComposite.addComponent(symbol);
		} else if (lexeme.matches(MARK_WORD_REGEX)) {
			Character firstSymbol = lexeme.charAt(0);
			symbol = new CustomSymbol(ComponentType.PUNCTUATION_MARK, firstSymbol);
			lexemeComposite.addComponent(symbol);
			word = lexeme.substring(1, lexeme.length());
			nextParser.parse(word, wordComposite);
			lexemeComposite.addComponent(wordComposite);
		} else if (lexeme.matches(FUNCTION_CALL_MARK_REGEX)) {
			word = lexeme.substring(0, lexeme.length() - 1);
			nextParser.parse(word, wordComposite);
			lexemeComposite.addComponent(wordComposite);
			Character lastSymbol = lexeme.charAt(lexeme.length() - 1);
			symbol = new CustomSymbol(ComponentType.PUNCTUATION_MARK, lastSymbol);
			lexemeComposite.addComponent(symbol);
		} else if (lexeme.matches(WORD_MARK_REGEX)) {
			String[] lexemeElements = lexeme.split(WORD_MARK_SPLIT_REGEX);
			for (String element : lexemeElements) {
				if (element.matches(PUNCTUATION_MARK_REGEX)) {
					Character markSymbol = element.charAt(0);
					symbol = new CustomSymbol(ComponentType.PUNCTUATION_MARK, markSymbol);
					lexemeComposite.addComponent(symbol);
				} else {
					nextParser.parse(element, wordComposite);
					lexemeComposite.addComponent(wordComposite);
				}
			}
		} else {
			nextParser.parse(lexeme, wordComposite);
			lexemeComposite.addComponent(wordComposite);
		}
	}
}
