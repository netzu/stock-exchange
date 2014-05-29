package buy.signal.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import utils.MocksForTests;
import buy.signal.test.BuySignalTester;
import buy.signal.test.ProfitsFromSignal;
import data.collector.StockExchangeIllegalStateException;
import data.collector.StockTickerHistory;

public class ProfitsFromSignalTest {
	
	DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");	
	final static String PATH = new String("buySignal/TestProfitsFromSignal/");
	
	MocksForTests mock = new MocksForTests();
	
	@Test
	public void noBuySignalHasBeenGenerated() throws ParseException{
		
		StockTickerHistory history = mock.readTickerData(PATH + "noBuySignalHasBeenGenerated.mst");
		DateTime buySignalDate = null;
		int dayToTest = 5;		
		
		List <ProfitsFromSignal> listOfProfits = new ArrayList<ProfitsFromSignal>();
		BuySignalTester test = new BuySignalTester();
		
		String expectedErrorMessage = "Cannot find ticker by date if date is null";
		
		try{
			listOfProfits = test.calculateProfitsForOneSignal(buySignalDate, history, dayToTest);
			fail("No exception has been found, expected: " + expectedErrorMessage);
		}catch(StockExchangeIllegalStateException e){
			assertTrue(e.getMessage().equals(expectedErrorMessage));
		}			
	}
	

	@Test
	public void notExistingBuySignalInTicker() throws ParseException{
		
		StockTickerHistory history = mock.readTickerData(PATH + "notExistingBuySignalInTicker.mst");
		DateTime buySignalDate = dateFormater.parseDateTime("20100109");
		int dayToTest = 5;	
		
		if((history.getStockTickerDataList().size()==0)){
			fail("StockTicker is empty");
		}
		
		List <ProfitsFromSignal> listOfProfits = new ArrayList<ProfitsFromSignal>();
		BuySignalTester test = new BuySignalTester();
		
		String expectedErrorMessage = "Could not find a stock in given day";
		
		try{
			listOfProfits = test.calculateProfitsForOneSignal(buySignalDate, history, dayToTest);
			fail("No exception has been found, expected: " + expectedErrorMessage);
		}catch(StockExchangeIllegalStateException e){
			assertTrue(e.getMessage().equals(expectedErrorMessage));
		}
	}
	
	@Test
	public void moreNumberOfDataInTickerThanDaysToTest() throws ParseException{
		StockTickerHistory history = mock.readTickerData(PATH + "moreNumberOfDataInTickerThanDaysToTest.mst");
		DateTime buySignalDate = dateFormater.parseDateTime("20100102");
		int dayToTest = 5;
		
		List <ProfitsFromSignal> listOfPRofits = new ArrayList<ProfitsFromSignal>();
		BuySignalTester test = new BuySignalTester();
		
		try{
			listOfPRofits = test.calculateProfitsForOneSignal(buySignalDate, history, dayToTest);
			assertTrue("Expeted diffrent number of elements, expected " + dayToTest + ", got: " + listOfPRofits.size(),(listOfPRofits.size()==dayToTest));
		}catch(Exception e){
			fail("Exception has been thrown when not expected: \"" + e.getMessage() + "\"");
		}	
	}
	
	@Test
	public void sameNumberOfDaysToTestAsDataInTicker() throws ParseException{
		StockTickerHistory history = mock.readTickerData(PATH + "sameNumberOfDaysToTestAsDataInTicker.mst");
		DateTime buySignalDate = dateFormater.parseDateTime("20100103");
		int dayToTest = 5;
		
		List <ProfitsFromSignal> listOfPRofits = new ArrayList<ProfitsFromSignal>();
		BuySignalTester test = new BuySignalTester();
		
		try{
			listOfPRofits = test.calculateProfitsForOneSignal(buySignalDate, history, dayToTest);
			assertTrue("Expeted diffrent number of elements, expected " + dayToTest + ", got: " + listOfPRofits.size(),(listOfPRofits.size()==dayToTest));
		}catch(Exception e){
			fail("Exception has been thrown when not expected: \"" + e.getMessage() + "\"");
		}
	}
	
	@Test
	public void oneMoreNumberOfDaysToTestThanDataInTicker() throws ParseException{
		StockTickerHistory history = mock.readTickerData(PATH + "moreNumberOfDaysToTestThanDataInTicker.mst");
		DateTime buySignalDate = dateFormater.parseDateTime("20100104");
		int dayToTest = 5;
		int numberOFDaysLessInTicker = 1;
		int expectedSizeOfTheProfitList = dayToTest-numberOFDaysLessInTicker;
		
		List <ProfitsFromSignal> listOfPRofits = new ArrayList<ProfitsFromSignal>();
		BuySignalTester test = new BuySignalTester();
		
		try{
			listOfPRofits = test.calculateProfitsForOneSignal(buySignalDate, history, dayToTest);
			assertTrue("Expeted diffrent number of elements, expected " + dayToTest + ", got: " + listOfPRofits.size(),(listOfPRofits.size()==expectedSizeOfTheProfitList));
		}catch(Exception e){
			fail("Exception has been thrown when not expected: \"" + e.getMessage() + "\"");
		}
	}
	
	@Test
	public void signalToBuyGeneratedLastDay() throws ParseException{
		StockTickerHistory history = mock.readTickerData(PATH + "moreNumberOfDaysToTestThanDataInTicker.mst");
		DateTime buySignalDate = dateFormater.parseDateTime("20100108");
		int dayToTest = 5;
		int numberOFDaysLessInTicker = 5;
		int expectedSizeOfTheProfitList = dayToTest-numberOFDaysLessInTicker;
		
		List <ProfitsFromSignal> listOfPRofits = new ArrayList<ProfitsFromSignal>();
		BuySignalTester test = new BuySignalTester();
		
		try{
			listOfPRofits = test.calculateProfitsForOneSignal(buySignalDate, history, dayToTest);
			assertTrue("Expeted diffrent number of elements, expected " + expectedSizeOfTheProfitList + ", got: " + listOfPRofits.size(),(listOfPRofits.size()==expectedSizeOfTheProfitList));
		}catch(Exception e){
			fail("Exception has been thrown when not expected: \"" + e.getMessage() + "\"");
		}
	}
	
	@Test
	public void zeroDaysToTestInParameterForOneSignal() throws ParseException{
		StockTickerHistory history = mock.readTickerData(PATH + "moreNumberOfDaysToTestThanDataInTicker.mst");
		DateTime buySignalDate = dateFormater.parseDateTime("20100108");
		int dayToTest = 0;
		
		List <ProfitsFromSignal> listOfProfits = new ArrayList<ProfitsFromSignal>();
		BuySignalTester test = new BuySignalTester();
		
		String expectedErrorMessage = "Number days to test cannot by 0";
		
		try{
			listOfProfits = test.calculateProfitsForOneSignal(buySignalDate, history, dayToTest);
			fail("No exception has been found, expected: " + expectedErrorMessage);
		}catch(IllegalArgumentException e){
			assertTrue(e.getMessage().equals(expectedErrorMessage));
		}
	}
	
	@Test
	public void emptyBuyList() throws IOException, ParseException{
		List <List<ProfitsFromSignal>> result = new ArrayList<List<ProfitsFromSignal>>();
		
		StockTickerHistory history = mock.readTickerData(PATH + "emptyBuyList.mst");
		List<DateTime> buySignal = mock.getBuysignal(PATH + "emptyBuyList.txt");
		int dayToTest = 5;		
		
		List <ProfitsFromSignal> listOfProfits = new ArrayList<ProfitsFromSignal>();
		BuySignalTester test = new BuySignalTester();

		
		try{
			result = test.calculateProfitsForTicker(buySignal, history, dayToTest);
			assertTrue(result.isEmpty());
		}catch(Exception e){
			fail("Got exception when not expected: " + e.getMessage());
		}	
	}
	
	@Test
	public void oneBuySignalForTicker() throws ParseException, IOException{
		
		List <List<ProfitsFromSignal>> result = new ArrayList<List<ProfitsFromSignal>>();
		
		StockTickerHistory history = mock.readTickerData(PATH + "oneBuySignalForTicker.mst");
		List<DateTime> buySignal = mock.getBuysignal(PATH + "oneBuySignalForTicker.txt");
		
		int dayToTest = 5;	
		
		BuySignalTester test = new BuySignalTester();
		
		try{
			result = test.calculateProfitsForTicker(buySignal, history, dayToTest);
			assertTrue("Got wrong nmber of expected results", (result.size()==1));
			assertTrue((result.get(0).size()==dayToTest));
		}catch(Exception e){
			fail("Got exception when not expected: " + e.getMessage());
		}		
	}
	
	@Test
	public void oneBuySignalForTickerGeneratedLastDay() throws ParseException, IOException{
		
		List <List<ProfitsFromSignal>> result = new ArrayList<List<ProfitsFromSignal>>();
		
		StockTickerHistory history = mock.readTickerData(PATH + "oneBuySignalForTickerGeneratedLastDay.mst");
		List<DateTime> buySignal = mock.getBuysignal(PATH + "oneBuySignalForTickerGeneratedLastDay.txt");
		
		int dayToTest = 5;	
		
		BuySignalTester test = new BuySignalTester();
		
		try{
			result = test.calculateProfitsForTicker(buySignal, history, dayToTest);
			assertTrue(result.isEmpty());
		}catch(Exception e){
			fail("Got exception when not expected: " + e.getMessage());
		}		
	}
	
	@Test
	public void multipleBuySignals_01() throws ParseException, IOException{
		List <List<ProfitsFromSignal>> result = new ArrayList<List<ProfitsFromSignal>>();
		
		StockTickerHistory history = mock.readTickerData(PATH + "multipleBuySignals_01.mst");
		List<DateTime> buySignal = mock.getBuysignal(PATH + "multipleBuySignals_01.txt");
		
		int dayToTest = 5;	
		
		BuySignalTester test = new BuySignalTester();
		
		try{
			result = test.calculateProfitsForTicker(buySignal, history, dayToTest);
			assertTrue(result.size()==2);
			assertTrue(result.get(0).size()==5);
			assertTrue(result.get(1).size()==5);
		}catch(Exception e){
			fail("Got exception when not expected: " + e.getMessage());
		}
	}
	
	@Test
	public void multipleBuySignals_02() throws ParseException, IOException{
		List <List<ProfitsFromSignal>> result = new ArrayList<List<ProfitsFromSignal>>();
		
		StockTickerHistory history = mock.readTickerData(PATH + "multipleBuySignals_02.mst");
		List<DateTime> buySignal = mock.getBuysignal(PATH + "multipleBuySignals_02.txt");
		
		int dayToTest = 5;	
		
		BuySignalTester test = new BuySignalTester();
		
		try{
			result = test.calculateProfitsForTicker(buySignal, history, dayToTest);
			assertTrue(result.size()==2);
			assertTrue(result.get(0).size()==4);
			assertTrue(result.get(1).size()==3);
		}catch(Exception e){
			fail("Got exception when not expected: " + e.getMessage());
		}
	}
	
	@Test
	public void multipleBuySignals_03() throws ParseException, IOException{
		List <List<ProfitsFromSignal>> result = new ArrayList<List<ProfitsFromSignal>>();
		
		StockTickerHistory history = mock.readTickerData(PATH + "multipleBuySignals_03.mst");
		List<DateTime> buySignal = mock.getBuysignal(PATH + "multipleBuySignals_03.txt");
		
		int dayToTest = 5;	
		
		BuySignalTester test = new BuySignalTester();
		
		try{
			result = test.calculateProfitsForTicker(buySignal, history, dayToTest);
			assertTrue(result.size()==1);
			assertTrue(result.get(0).size()==3);
		}catch(Exception e){
			fail("Got exception when not expected: " + e.getMessage());
		}
	}
	
	@Test
	public void zeroDaysToTestInParameterForTicker() throws ParseException, IOException{
		List <List<ProfitsFromSignal>> result = new ArrayList<List<ProfitsFromSignal>>();
		
		StockTickerHistory history = mock.readTickerData(PATH + "multipleBuySignals_03.mst");
		List<DateTime> buySignal = mock.getBuysignal(PATH + "multipleBuySignals_03.txt");
		int dayToTest = 0;

		BuySignalTester test = new BuySignalTester();
		
		String expectedErrorMessage = "Number days to test cannot by 0";
		
		try{
			result = test.calculateProfitsForTicker(buySignal, history, dayToTest);
			fail("No exception has been found, expected: " + expectedErrorMessage);
		}catch(IllegalArgumentException e){
			assertTrue(e.getMessage().equals(expectedErrorMessage));
		}
	}
}
