package com.epam.andreyshcherbin.action;

import java.util.ArrayList;
import java.util.List;
import com.epam.andreyshcherbin.comparator.TextComparator;
import com.epam.andreyshcherbin.composite.TextComponent;
import com.epam.andreyshcherbin.composite.TextComposite;

//need fix problem with immutable collection
public class TextAction {

	public void sortParagraphsBySentences(TextComposite text) {
		List<TextComponent> paragraphs = text.getComponents();
		paragraphs.sort(TextComparator.NUMBER_ELEMENTS);
	}

	public int numberSymbols(TextComposite text) {
		int number = 0;
		for (TextComponent paragraph : text.getComponents()) {
			for (TextComponent sentence : paragraph.getComponents()) {
				for (TextComponent lexem : sentence.getComponents())
					number += lexem.countSymbol();
			}
		}
		return number;
	}

	public List<TextComponent> getSentencesWithLongestWord(TextComposite text) {
		TextComponent longestLexem = new TextComposite();
		List<TextComponent> sentences = new ArrayList<>();
		for (TextComponent paragraph : text.getComponents()) {
			for (TextComponent sentence : paragraph.getComponents()) {
				for (TextComponent lexem : sentence.getComponents()) {
					int sizeLexem = lexem.countSymbol();
					if (sizeLexem > longestLexem.countSymbol()) {
						longestLexem = lexem;
					}
				}
			}
		}
		for (TextComponent paragraph : text.getComponents()) {
			for (TextComponent sentence : paragraph.getComponents()) {
				for (TextComponent lexem : sentence.getComponents()) {
					if (longestLexem.equals(lexem)) {
						sentences.add(sentence);
						break;
					}
				}
			}
		}
		return sentences;
	}

	public boolean deleteFromTextSentencesWithWordsLessGivenNumber(TextComposite text, int number) {
		return true;
	}

	public int findWordsAndCountThem(TextComposite text) {
		return 0;
	}
}
