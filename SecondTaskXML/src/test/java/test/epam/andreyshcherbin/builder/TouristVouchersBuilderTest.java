package test.epam.andreyshcherbin.builder;

import org.testng.annotations.Test;

import com.epam.andreyshcherbin.builder.AbstractTouristVouchersBuilder;
import com.epam.andreyshcherbin.entity.TouristVoucher;
import com.epam.andreyshcherbin.exception.ParserException;
import com.epam.andreyshcherbin.entity.HotelCharacteristic;
import com.epam.andreyshcherbin.factory.TouristVoucherBuilderFactory;
import com.epam.andreyshcherbin.parsing.DateParser;
import test.epam.andreyshcherbin.TestListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import static org.testng.Assert.assertEquals;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.AfterClass;

@Listeners(TestListener.class)
public class TouristVouchersBuilderTest {
	
	private static final String TEST_XML = "data\\TouristVouchers.xml";
	
	TouristVoucherBuilderFactory sFactory;
	AbstractTouristVouchersBuilder builderSAX;
	AbstractTouristVouchersBuilder builderDOM;
	AbstractTouristVouchersBuilder builderStAX;
	Set<TouristVoucher> expectedTouristVouchersSet;
	
	@Test()
	public void testTouristVoucherDOMBuilder() throws ParserException {
		builderDOM.buildSetTouristVouchers(TEST_XML);
		Set<TouristVoucher> actualTouristVouchersSet = builderDOM.getTouristVouchers();
		assertEquals(actualTouristVouchersSet, expectedTouristVouchersSet);		
	}
	
	@Test()
	public void testTouristVoucherSAXBuilder() throws ParserException {
		builderSAX.buildSetTouristVouchers(TEST_XML);
		Set<TouristVoucher> actualTouristVouchersSet = builderSAX.getTouristVouchers();
		assertEquals(actualTouristVouchersSet, expectedTouristVouchersSet);
	}
	
	@Test()
	public void testTouristVoucherStAXBuilder() throws ParserException {
		builderStAX.buildSetTouristVouchers(TEST_XML);
		Set<TouristVoucher> actualTouristVouchersSet = builderStAX.getTouristVouchers();
		assertEquals(actualTouristVouchersSet, expectedTouristVouchersSet);
		
	}

	@BeforeClass
	public void beforeClass() throws ParserException {
		sFactory = new TouristVoucherBuilderFactory();
		builderSAX = sFactory.createTouristVoucherBuilder("sax");
		builderDOM = sFactory.createTouristVoucherBuilder("dom");
		builderStAX = sFactory.createTouristVoucherBuilder("stax");
		expectedTouristVouchersSet = new HashSet<TouristVoucher>();
		expectedTouristVouchersSet.add(new TouristVoucher("air","rest","A999999999","SPAIN",5,5,100, new HotelCharacteristic("",5,false,false,false,1), DateParser.parseString("2021-01-30T23:43:00")));
		expectedTouristVouchersSet.add(new TouristVoucher("air","rest","A999999998","BRAZIL",10,10,200, new HotelCharacteristic("HB",3,true,true,true,1), DateParser.parseString("2021-01-31T22:43:00")));
		expectedTouristVouchersSet.add(new TouristVoucher("air","excursion","A999999997","JAPAN",10,10,500, new HotelCharacteristic("HB",4,true,true,true,3), DateParser.parseString("2021-03-31T19:43:00")));
		expectedTouristVouchersSet.add(new TouristVoucher("auto","weekend","A999999996","RUSSIA",2,2,50, new HotelCharacteristic("HB",3,true,true,true,1), DateParser.parseString("2021-04-28T17:43:00")));
		expectedTouristVouchersSet.add(new TouristVoucher("auto","pilgrimage","A999999995","TURKEY",365,365,10000, new HotelCharacteristic("",1,false,false,false,1), DateParser.parseString("2021-04-28T17:43:00")));
		expectedTouristVouchersSet.add(new TouristVoucher("air","excursion","A999999994","USA",10,10,9999999, new HotelCharacteristic("AI",4,true,true,true,3), DateParser.parseString("2025-04-28T18:43:00")));
		expectedTouristVouchersSet.add(new TouristVoucher("air","rest","A999999993","SWEDEN",20,20,100000, new HotelCharacteristic("",5,false,false,true,1), DateParser.parseString("2026-01-28T06:00:00")));
		expectedTouristVouchersSet.add(new TouristVoucher("railway","excursion","A999999992","SINGAPORE",20,20,1000000, new HotelCharacteristic("BB",4,true,true,true,2), DateParser.parseString("2023-04-28T07:00:00")));
		expectedTouristVouchersSet.add(new TouristVoucher("air","weekend","A999999991","ITALY",5,5,20000, new HotelCharacteristic("AI",5,true,true,true,1), DateParser.parseString("2022-06-28T00:00:00")));
		expectedTouristVouchersSet.add(new TouristVoucher("railway","weekend","A999999990","GERMANY",20,20,999999, new HotelCharacteristic("BB",4,true,true,true,2), DateParser.parseString("2026-07-28T04:00:00")));
		expectedTouristVouchersSet.add(new TouristVoucher("liner","rest","A999999989","JAMAICA",2,2,444, new HotelCharacteristic("",1,false,false,false,3), DateParser.parseString("2025-12-31T13:00:00")));
		expectedTouristVouchersSet.add(new TouristVoucher("air","pilgrimage","A999999988","GREECE",20,20,20, new HotelCharacteristic("",1,false,false,false,2), DateParser.parseString("2030-11-30T11:00:00")));
		expectedTouristVouchersSet.add(new TouristVoucher("auto","excursion","A999999987","FRANCE",15,15,10000, new HotelCharacteristic("",1,false,false,false,2), DateParser.parseString("2028-06-30T06:00:00")));
		expectedTouristVouchersSet.add(new TouristVoucher("air","rest","A999999986","DENMARK",20,20,9999999, new HotelCharacteristic("AI",5,true,true,true,3), DateParser.parseString("2030-04-30T00:00:00")));
		expectedTouristVouchersSet.add(new TouristVoucher("auto","pilgrimage","A999999985","ISRAEL",999,999,30, new HotelCharacteristic("",1,false,false,false,1), DateParser.parseString("2100-01-01T00:00:00")));
		expectedTouristVouchersSet.add(new TouristVoucher("railway","weekend","A999999984","FINLAND",5,5,9000, new HotelCharacteristic("BB",5,true,true,true,3), DateParser.parseString("2027-05-23T06:00:00")));
	}
	
	@AfterClass
	public void afterClass() {
		sFactory = null;
		builderSAX = null;
		builderDOM = null;
		builderStAX = null;
		expectedTouristVouchersSet = null;
	}	
}
