package test.epam.andreyshcherbin.validation;

import org.testng.annotations.Test;
import com.epam.andreyshcherbin.validation.ValidatorSAX;

import test.epam.andreyshcherbin.TestListener;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.AfterClass;

@Listeners(TestListener.class)
public class ValidatorSAXTest {

	private static final String TEST_XML = "data\\tourist_voucher.xml";
	private static final String TEST_XSD = "data\\tourist_voucher.xsd";

	private ValidatorSAX validatorSAX;

	@Test
	public void testValidateXML() {
		boolean result;
		result = validatorSAX.validateXML(TEST_XML, TEST_XSD);
		assertTrue(result);
	}

	@BeforeClass
	public void beforeClass() {
		validatorSAX = new ValidatorSAX();
	}

	@AfterClass
	public void afterClass() {
	}

}
