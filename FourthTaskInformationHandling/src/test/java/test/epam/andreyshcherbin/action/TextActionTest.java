package test.epam.andreyshcherbin.action;

import org.testng.annotations.Test;
import com.epam.andreyshcherbin.action.TextAction;
import com.epam.andreyshcherbin.chain.LexemeParser;
import com.epam.andreyshcherbin.chain.ParagraphParser;
import com.epam.andreyshcherbin.chain.SentenceParser;
import com.epam.andreyshcherbin.chain.TextParser;
import com.epam.andreyshcherbin.chain.WordParser;
import com.epam.andreyshcherbin.composite.TextComponent;
import com.epam.andreyshcherbin.composite.TextComposite;
import com.epam.andreyshcherbin.exception.TextException;
import com.epam.andreyshcherbin.reader.DataReader;
import test.epam.andreyshcherbin.TestListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import static com.epam.andreyshcherbin.composite.ComponentType.TEXT;
import static org.testng.Assert.assertEquals;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.AfterClass;

@Listeners(TestListener.class)
public class TextActionTest {

	private static final String TEST_FILE = "data\\text.txt";
	private TextAction textAction;
	private DataReader reader;
	private TextParser textParser;
	private TextComposite text;

	@BeforeClass
	public void beforeClass() throws TextException {
		reader = new DataReader();
		text = new TextComposite(TEXT);
		String data = reader.read(TEST_FILE);
		textParser = new TextParser(new ParagraphParser(new SentenceParser(new LexemeParser(new WordParser(null)))));
		textParser.parse(data, text);
		textAction = new TextAction();

	}

	@Test
	public void sortParagraphsBySentencesTest() throws TextException {
		textAction.sortParagraphsBySentences(text);
		String actual = text.toString();
		String expected = "	It is a established fact that a reader will be of a page when looking at its layout...\n"
				+ "	Bye бандерлоги.\n"
				+ "	It has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the \"Динамо\" (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum!\n"
				+ "	It is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English?";
		assertEquals(actual, expected);
	}

	@Test
	public void numberSymbolsTest() throws TextException {
		int actual = textAction.numberSymbols(text);
		int expected = 633;
		assertEquals(actual, expected);
	}

	@Test
	public void getSentencesWithLongestWord() throws TextException {
		List<TextComponent> sentences = textAction.getSentencesWithLongestWord(text);
		List<String> actual = new ArrayList<>();
		for (TextComponent sentence : sentences) {
			actual.add(sentence.toString());
		}
		List<String> expected = List.of(
				"The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English?");
		assertEquals(actual, expected);
	}

	@Test
	public void deleteFromTextSentencesWithWordsLessGivenNumberTest() throws TextException {
		textAction.deleteFromTextSentencesWithWordsLessGivenNumber(text, 4);
		String actual = text.toString();
		String expected = "	It has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the \"Динамо\" (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum!\n"
				+ "	It is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English?\n"
				+ "	It is a established fact that a reader will be of a page when looking at its layout...\n" + "	";
		assertEquals(actual, expected);
	}

	@Test
	public void findWordsAndCountThemTest() throws TextException {
		Map<String, Integer> actual = textAction.findWordsAndCountThem(text);
		Map<String, Integer> actualButOnlyFirstSix = new LinkedHashMap<>();
		int i = 0;
		for (Map.Entry<String, Integer> entry : actual.entrySet()) {
			if (i++ < 6) {
				Integer count = entry.getValue();
				String word = entry.getKey();
				actualButOnlyFirstSix.put(word, count);
			} else {
				break;
			}
		}
		System.out.println(actual);
		Map<String, Integer> expected = new LinkedHashMap<>();
		expected.put("it", 6);
		expected.put("has", 2);
		expected.put("survived", 1);
		expected.put("not", 1);
		expected.put("only", 1);
		expected.put("five", 1);
		assertEquals(actualButOnlyFirstSix, expected);
	}

	@AfterClass
	public void afterClass() {
		textAction = null;
		reader = null;
		textParser = null;
		text = null;
	}
}
