package com.epam.andreyshcherbin.action;

import java.util.ArrayList;
import java.util.List;

import com.epam.andreyshcherbin.comparator.TextComparator;
import com.epam.andreyshcherbin.composite.TextComponent;
import com.epam.andreyshcherbin.composite.TextComposite;

public class TextAction {

	public void sortParagraphsBySentences(TextComposite text) {
		List<TextComponent> paragraphs = text.getComponents();
		paragraphs.sort(TextComparator.NUMBER_ELEMENTS);
		System.out.println(text);
	}

	public void sortSentencesByWords(TextComposite text) {
		List<TextComponent> paragraphs = text.getComponents();
		List<TextComponent> sentences = paragraphs.get(1).getComponents();
		sentences.sort(TextComparator.NUMBER_ELEMENTS);
		System.out.println(text);
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
		List<TextComponent> sentences = new ArrayList<TextComponent>();
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
		System.out.println(sentences);
		return sentences;
	}

	public boolean deleteFromTextSentencesWithWordsLessGivenNumber(TextComposite text, int number) {
		for (TextComponent paragraph : text.getComponents()) {
			for (TextComponent sentence : paragraph.getComponents()) {
				if (sentence.getComponents().size() < number) {
					paragraph.getComponents().remove(sentence);
				}
			}
		}
		System.out.println(text);
		return true;
	}
}
