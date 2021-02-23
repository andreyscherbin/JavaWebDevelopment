package com.epam.andreyshcherbin.run;

import com.epam.andreyshcherbin.composite.Symbol;
import com.epam.andreyshcherbin.composite.TextComponent;
import com.epam.andreyshcherbin.composite.TextComposite;
import com.epam.andreyshcherbin.exception.TextException;
import com.epam.andreyshcherbin.reader.DataReader;

import static com.epam.andreyshcherbin.composite.TypeComponent.*;

import com.epam.andreyshcherbin.chain.ParagraphParser;
import com.epam.andreyshcherbin.chain.SentenceParser;
import com.epam.andreyshcherbin.chain.SymbolParser;
import com.epam.andreyshcherbin.chain.TextParser;
import com.epam.andreyshcherbin.chain.LexemeParser;

public class Main {
	public static void main(String[] args) throws TextException {
		TextComposite paragraph = new TextComposite(PARAGRAPH);
		TextComposite sentence = new TextComposite(SENTENCE);
		TextComposite lexeme = new TextComposite(LEXEME);
		Symbol symbol = new Symbol(SYMBOL, 'H');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 'e');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 'l');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 'l');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 'o');
		lexeme.addComponent(symbol);
		sentence.addComponent(lexeme);
		symbol = new Symbol(PUNCTUATION_MARK, ',');
		sentence.addComponent(symbol);
		lexeme = new TextComposite(LEXEME);
		symbol = new Symbol(SYMBOL, 'f');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 'r');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 'i');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 'e');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 'n');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 'd');
		lexeme.addComponent(symbol);
		sentence.addComponent(lexeme);
		symbol = new Symbol(PUNCTUATION_MARK, '!');
		sentence.addComponent(symbol);
		paragraph.addComponent(sentence);
		sentence = new TextComposite(SENTENCE);
		lexeme = new TextComposite(LEXEME);
		symbol = new Symbol(SYMBOL, 'N');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 'i');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 'c');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 'e');
		lexeme.addComponent(symbol);
		sentence.addComponent(lexeme);
		lexeme = new TextComposite(LEXEME);
		symbol = new Symbol(SYMBOL, 't');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 'o');
		lexeme.addComponent(symbol);
		sentence.addComponent(lexeme);
		lexeme = new TextComposite(LEXEME);
		symbol = new Symbol(SYMBOL, 'm');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 'e');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 'e');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 't');
		lexeme.addComponent(symbol);
		sentence.addComponent(lexeme);
		lexeme = new TextComposite(LEXEME);
		symbol = new Symbol(SYMBOL, 'y');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 'o');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 'u');
		lexeme.addComponent(symbol);
		sentence.addComponent(lexeme);
		symbol = new Symbol(PUNCTUATION_MARK, '.');
		sentence.addComponent(symbol);
		paragraph.addComponent(sentence);
		sentence = new TextComposite(SENTENCE);
		lexeme = new TextComposite(LEXEME);
		symbol = new Symbol(SYMBOL, 'H');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 'o');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 'w');
		lexeme.addComponent(symbol);
		sentence.addComponent(lexeme);
		lexeme = new TextComposite(LEXEME);
		symbol = new Symbol(SYMBOL, 'a');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 'r');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 'e');
		lexeme.addComponent(symbol);
		sentence.addComponent(lexeme);
		lexeme = new TextComposite(LEXEME);
		symbol = new Symbol(SYMBOL, 'y');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 'o');
		lexeme.addComponent(symbol);
		symbol = new Symbol(SYMBOL, 'u');
		lexeme.addComponent(symbol);
		sentence.addComponent(lexeme);
		symbol = new Symbol(PUNCTUATION_MARK, '?');
		sentence.addComponent(symbol);
		paragraph.addComponent(sentence);

		System.out.println(paragraph.toString());		
		System.out.println(paragraph.countSymbol());

		for (TextComponent sentanceComponent : paragraph.getComponents()) {
			for (TextComponent lexemeComponent : sentanceComponent.getComponents()) {
				if (lexemeComponent.countSymbol() == 3) {
					System.out.println(lexemeComponent);
				}
			}
		}
		TextComposite textComposite = new TextComposite(TEXT);
		DataReader reader = new DataReader();
		String text = reader.read("data\\text.txt");
		TextParser textParser = new TextParser(new ParagraphParser(new SentenceParser(new LexemeParser(new SymbolParser(null)))));
		textParser.parse(text, textComposite);
		System.out.println(textComposite);
	}
}
