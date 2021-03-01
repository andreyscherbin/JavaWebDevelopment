package com.epam.andreyshcherbin.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.andreyshcherbin.comparator.TextComparator;
import com.epam.andreyshcherbin.composite.ComponentType;
import com.epam.andreyshcherbin.composite.TextComponent;
import com.epam.andreyshcherbin.composite.TextComposite;
import com.epam.andreyshcherbin.exception.TextException;

public class TextAction {

	private static Logger logger = LogManager.getLogger();

	public void sortParagraphsBySentences(TextComposite text) throws TextException {
		if (text == null || text.getType() != ComponentType.TEXT) {
			logger.error("text is null or not text {}", text);
			throw new TextException("text is null or not text: " + text);
		}
		List<TextComponent> paragraphs = text.getComponents();
		List<TextComponent> paragraphsCopy = new ArrayList<>(paragraphs);
		paragraphsCopy.sort(TextComparator.NUMBER_ELEMENTS);
		text.setComponents(paragraphsCopy);
	}

	public int numberSymbols(TextComposite text) throws TextException {
		if (text == null || text.getType() != ComponentType.TEXT) {
			logger.error("text is null or not text {}", text);
			throw new TextException("text is null or not text: " + text);
		}
		int number = 0;
		for (TextComponent paragraph : text.getComponents()) {
			for (TextComponent sentence : paragraph.getComponents()) {
				for (TextComponent lexem : sentence.getComponents()) {
					for (TextComponent part : lexem.getComponents()) {
						if (part.getType() == ComponentType.WORD) {
							number += part.countSymbol();
						}
					}
				}
			}
		}
		return number;
	}

	public List<TextComponent> getSentencesWithLongestWord(TextComposite text) throws TextException {
		if (text == null || text.getType() != ComponentType.TEXT) {
			logger.error("text is null or not text {}", text);
			throw new TextException("text is null or not text: " + text);
		}
		TextComponent longestWord = new TextComposite();
		List<TextComponent> sentences = new ArrayList<>();
		for (TextComponent paragraph : text.getComponents()) {
			for (TextComponent sentence : paragraph.getComponents()) {
				for (TextComponent lexem : sentence.getComponents()) {
					for (TextComponent part : lexem.getComponents()) {
						if (part.getType() == ComponentType.WORD) {
							int sizeWord = part.countSymbol();
							if (sizeWord > longestWord.countSymbol()) {
								longestWord = part;
							}
						}
					}

				}
			}
		}
		for (TextComponent paragraph : text.getComponents()) {
			for (TextComponent sentence : paragraph.getComponents()) {
				for (TextComponent lexem : sentence.getComponents()) {
					for (TextComponent part : lexem.getComponents()) {
						if (part.getType() == ComponentType.WORD && longestWord.equals(part)) {
							sentences.add(sentence);
							break;
						}
					}
				}
			}
		}
		return sentences;
	}

	public void deleteFromTextSentencesWithWordsLessGivenNumber(TextComposite text, int number) throws TextException {
		if (text == null || text.getType() != ComponentType.TEXT) {
			logger.error("text is null or not text {}", text);
			throw new TextException("text is null or not text: " + text);
		}
		List<TextComponent> removeSentences = new ArrayList<>();
		for (TextComponent paragraph : text.getComponents()) {
			for (TextComponent sentence : paragraph.getComponents()) {
				int numberWords = 0;
				for (TextComponent lexem : sentence.getComponents()) {
					for (TextComponent part : lexem.getComponents()) {
						if (part.getType() == ComponentType.WORD) {
							numberWords++;
						}
					}
				}
				if (numberWords < number) {
					removeSentences.add(sentence);
				}
			}
		}
		for (TextComponent paragraph : text.getComponents()) {
			List<TextComponent> sentences = paragraph.getComponents();
			List<TextComponent> sentencesCopy = new ArrayList<>(sentences);
			sentencesCopy.removeAll(removeSentences);
			((TextComposite) paragraph).setComponents(sentencesCopy);
		}
	}

	public Map<String, Integer> findWordsAndCountThem(TextComposite text) throws TextException {
		if (text == null || text.getType() != ComponentType.TEXT) {
			logger.error("text is null or not text {}", text);
			throw new TextException("text is null or not text: " + text);
		}
		Map<String, Integer> identicalWords = new LinkedHashMap<>();
		for (TextComponent paragraph : text.getComponents()) {
			for (TextComponent sentence : paragraph.getComponents()) {
				for (TextComponent lexem : sentence.getComponents()) {
					for (TextComponent part : lexem.getComponents()) {
						if (part.getType() == ComponentType.WORD) {
							String word = part.toString().toLowerCase();
							if (!identicalWords.containsKey(word)) {
								identicalWords.put(word, 1);
							} else {
								int count = identicalWords.get(word);
								identicalWords.put(word, count + 1);
							}
						}
					}
				}
			}
		}
		return identicalWords;
	}
}