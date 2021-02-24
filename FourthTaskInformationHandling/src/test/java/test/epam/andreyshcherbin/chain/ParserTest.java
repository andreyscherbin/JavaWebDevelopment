package test.epam.andreyshcherbin.chain;

import org.testng.annotations.Test;
import com.epam.andreyshcherbin.chain.ParagraphParser;
import com.epam.andreyshcherbin.chain.SentenceParser;
import com.epam.andreyshcherbin.chain.TextParser;
import com.epam.andreyshcherbin.chain.LexemeParser;
import com.epam.andreyshcherbin.composite.TextComposite;
import com.epam.andreyshcherbin.exception.TextException;
import com.epam.andreyshcherbin.reader.DataReader;
import org.testng.annotations.BeforeClass;
import static com.epam.andreyshcherbin.composite.TypeComponent.TEXT;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterClass;

public class ParserTest {

	TextComposite textComposite;
	DataReader reader;
	TextParser textParser;

	@BeforeClass
	public void beforeClass() {
		textComposite = new TextComposite(TEXT);
		reader = new DataReader();
		textParser = new TextParser(new ParagraphParser(new SentenceParser(new LexemeParser(null))));
	}

	@Test
	public void parserTextTest() throws TextException {

		String parseText = reader.read("data\\text.txt");
		textParser.parse(parseText, textComposite);
		String actual = textComposite.toString().replaceAll(" +", " ");	
		String expected = "	It has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum!\n"
				+ "	It is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English?\n"
				+ "	It is a established fact that a reader will be of a page when looking at its layout...\n"
				+ "	Crash Test System.out.println(\"Привет, Мир\")...";
		assertEquals(actual, expected);
	}

	@AfterClass
	public void afterClass() {
		textComposite = null;
		reader = null;
		textParser = null;
	}
}
