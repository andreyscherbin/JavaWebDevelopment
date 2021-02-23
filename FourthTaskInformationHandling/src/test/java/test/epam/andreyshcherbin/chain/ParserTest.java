package test.epam.andreyshcherbin.chain;

import org.testng.annotations.Test;

import com.epam.andreyshcherbin.chain.ParagraphParser;
import com.epam.andreyshcherbin.chain.SentenceParser;
import com.epam.andreyshcherbin.chain.SymbolParser;
import com.epam.andreyshcherbin.chain.TextParser;
import com.epam.andreyshcherbin.chain.LexemeParser;
import com.epam.andreyshcherbin.composite.Symbol;
import com.epam.andreyshcherbin.composite.TextComposite;
import com.epam.andreyshcherbin.exception.TextException;
import com.epam.andreyshcherbin.reader.DataReader;

import org.testng.annotations.BeforeClass;

import static com.epam.andreyshcherbin.composite.TypeComponent.LEXEME;
import static com.epam.andreyshcherbin.composite.TypeComponent.PARAGRAPH;
import static com.epam.andreyshcherbin.composite.TypeComponent.PUNCTUATION_MARK;
import static com.epam.andreyshcherbin.composite.TypeComponent.SENTENCE;
import static com.epam.andreyshcherbin.composite.TypeComponent.SYMBOL;
import static com.epam.andreyshcherbin.composite.TypeComponent.TEXT;
import static com.epam.andreyshcherbin.composite.TypeComponent.WORD;

import org.testng.annotations.AfterClass;

public class ParserTest {

	TextComposite textComposite;
	DataReader reader;
	TextParser textParser;

	@BeforeClass
	public void beforeClass() {
		textComposite = new TextComposite(TEXT);
		reader = new DataReader();
		textParser = new TextParser(new ParagraphParser(new SentenceParser(new LexemeParser(new SymbolParser(null)))));
	}

	@Test
	public void parserTextTest() throws TextException {
		TextComposite text = new TextComposite(TEXT);
		TextComposite paragraph = new TextComposite(PARAGRAPH);
		TextComposite sentence = new TextComposite(SENTENCE);
		TextComposite lexeme = new TextComposite(LEXEME);
		text.addComponent(paragraph);
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
		lexeme = new TextComposite(LEXEME);
		symbol = new Symbol(PUNCTUATION_MARK, ',');
		lexeme.addComponent(symbol);
		sentence.addComponent(lexeme);
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
		lexeme = new TextComposite(LEXEME);
		symbol = new Symbol(PUNCTUATION_MARK, '!');
		lexeme.addComponent(symbol);		
		sentence.addComponent(lexeme);
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
		
		String parseText = reader.read("data\\text.txt");		
		textParser.parse(parseText, textComposite);
		org.testng.Assert.assertEquals(textComposite, text);
	}

	@AfterClass
	public void afterClass() {
		textComposite = null;
		reader = null;
		textParser = null;
	}
}
