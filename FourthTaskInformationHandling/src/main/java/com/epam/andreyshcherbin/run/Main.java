package com.epam.andreyshcherbin.run;

import com.epam.andreyshcherbin.composite.TextComposite;
import com.epam.andreyshcherbin.exception.TextException;
import com.epam.andreyshcherbin.reader.DataReader;
import com.epam.andreyshcherbin.chain.ParagraphParser;
import com.epam.andreyshcherbin.chain.SentenceParser;
import com.epam.andreyshcherbin.chain.TextParser;
import com.epam.andreyshcherbin.chain.LexemeParser;

import static com.epam.andreyshcherbin.composite.TypeComponent.*;

import com.epam.andreyshcherbin.action.TextAction;

public class Main {
	public static void main(String[] args) throws TextException {
		
		TextAction textAction = new TextAction();
		TextComposite text = new TextComposite(TEXT);
		DataReader reader = new DataReader();
		String data = reader.read("data\\text.txt");
		TextParser textParser = new TextParser(new ParagraphParser(new SentenceParser(new LexemeParser(null))));
		textParser.parse(data, text);
		//System.out.println(textComposite.toString().strip().replaceAll(" +", " "));
		System.out.println(text);
		textAction.sortParagraphsBySentences(text);	
		textAction.sortSentencesByWords(text);	
		System.out.println("Number symbols: " +  textAction.numberSymbols(text));
		textAction.getSentencesWithLongestWord(text);
		textAction.deleteFromTextSentencesWithWordsLessGivenNumber(text, 5);
	}
}
