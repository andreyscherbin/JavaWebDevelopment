package com.epam.andreyshcherbin.action;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.andreyshcherbin.comparator.TextComparator;
import com.epam.andreyshcherbin.composite.ComponentType;
import com.epam.andreyshcherbin.composite.TextComponent;
import com.epam.andreyshcherbin.composite.TextComposite;
import com.epam.andreyshcherbin.exception.TextException;

public class TextActionStream {
	
	private static Logger logger = LogManager.getLogger();
	
	public void sortParagraphsBySentencesStream(TextComposite text) throws TextException {
		if (text == null || text.getType() != ComponentType.TEXT) {
			logger.error("text is null or not text {}", text);
			throw new TextException("text is null or not text: " + text);
		}				
		List<TextComponent> paragraphs = text.getComponents().stream().sorted(TextComparator.NUMBER_ELEMENTS).collect(Collectors.toList());
		text.setComponents(paragraphs);
	}
	
	public int numberSymbolsStream(TextComposite text) throws TextException {		
		if (text == null || text.getType() != ComponentType.TEXT) {
			logger.error("text is null or not text {}", text);
			throw new TextException("text is null or not text: " + text);
		}
			int number = 0;			
			number = text.getComponents().stream().map(paragraph -> paragraph.getComponents()
					            .stream().map(sentence -> sentence.getComponents()
					            .stream().map(lexem -> lexem.getComponents()
					            .stream().filter(part -> part.getType() == ComponentType.WORD)
					            .map(part -> part.countSymbol())
					            .reduce(0, (n1, n2) -> n1 + n2))
					            .reduce(0, (n1, n2) -> n1 + n2))
					            .reduce(0, (n1, n2) -> n1 + n2))
					            .reduce(0, (n1, n2) -> n1 + n2);				            
			return number;
		}	
}


